package com.ericstudio.webstore.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.xml.sax.SAXException;

import com.ericstudio.webstore.domain.brick.v2.GetSets;
import com.ericstudio.webstore.domain.brick.v2.GetSetsResponse;
import com.ericstudio.webstore.domain.brick.v2.Login;
import com.ericstudio.webstore.domain.brick.v2.LoginResponse;
import com.ericstudio.webstore.domain.brick.v2.Sets;
import com.ericstudio.webstore.parser.LoginResponseParserHandler;
import com.ericstudio.webstore.parser.SetsParserHandler;
import com.ericstudio.webstore.service.BrickService;
import com.ericstudio.webstore.utils.LoginUtils;

@Service
public class BrickServiceImpl implements BrickService {

	Logger logger = Logger.getLogger(this.getClass());

	// private static final String SYSTEM_CONFIG = "systemConfig.properties";
	//
	// private static final String API_KEY = "system.api.key";
	//
	// private static final String USERNAME = "system.username";
	//
	// private static final String PASSWORD = "system.password";

	@Value("${system.api.key}")
	private String apiKey;
	@Value("${system.username}")
	private String username;
	@Value("${system.password}")
	private String password;

	// private WebServiceTemplate webServiceTemplate;

	// @Autowired
	// public BrickServiceImpl(WebServiceTemplate webServiceTemplate) {
	// this.webServiceTemplate = webServiceTemplate;
	// }

	@Override
	public List<Sets> getSetsByYear(String year) {

		String empty = "";
		URL url;
		try {

			url = new URL("http://brickset.com/api/v2.asmx/getSets?apiKey="
					+ URLEncoder.encode(apiKey, "ISO-8859-1") + "&userHash="
					+ this.getUserHash() + "&query=" + empty + "&theme="
					+ empty + "&subtheme=" + empty + "&setNumber=" + empty
					+ "&year=" + year + "&owned=" + empty + "&wanted=" + empty
					+ "&orderBy=" + empty + "&pageSize=" + empty
					+ "&pageNumber=" + empty + "&userName=" + empty + "");

			logger.info(url.toString());
			InputStream is = url.openStream();
			/* XML paser */
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser;
			parser = parserFactor.newSAXParser();
			SetsParserHandler handler = new SetsParserHandler();
			parser.parse(is, handler);
			logger.info("list size: " + handler.getResultList().size());
			return handler.getResultList();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* soap way */
		// GetSetsResponse rs = new GetSetsResponse();
		// try {
		// setRequest.setApiKey(API_KEY);
		// rs = (GetSetsResponse) webServiceTemplate
		// .marshalSendAndReceive(setRequest);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// return rs.getGetSetsResult().getSets();
		return new ArrayList<Sets>();
	}

	@Override
	public String getUserHash() {

		LoginUtils.getInstance();
		return LoginUtils.getUserHash();
		/* soap way */
		// Properties prop = new Properties();
		// InputStream is = getClass().getClassLoader().getResourceAsStream(
		// SYSTEM_CONFIG);
		//
		// if (is != null) {
		// try {
		// prop.load(is);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } else {
		// throw new FileNotFoundException("property file '" + SYSTEM_CONFIG
		// + "' not found in the classpath");
		// }
		//
		// Login login = new Login();
		// login.setApiKey(apiKey);
		// login.setUsername(username);
		// login.setPassword(password);
		//
		// LoginResponse rs = (LoginResponse) webServiceTemplate
		// .marshalSendAndReceive(login);
		//
		// return rs.getLoginResult();
	}

}
