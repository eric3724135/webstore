package com.ericstudio.webstore.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.xml.sax.SAXException;

import com.ericstudio.webstore.parser.LoginResponseParserHandler;

public class LoginUtils {

	Logger logger = Logger.getLogger(this.getClass());

	private static LoginUtils rs = null;

	private static final String SYSTEM_CONFIG = "systemConfig.properties";

	private static final String API_KEY = "system.api.key";

	private static final String USERNAME = "system.username";

	private static final String PASSWORD = "system.password";

	private static String userHash = "";

	private LoginUtils() {
		BufferedReader reader = null;

		URL url;
		try {
			/* 取得properties */
			Properties prop = new Properties();
			InputStream propis = getClass().getClassLoader()
					.getResourceAsStream(SYSTEM_CONFIG);
			prop.load(propis);
			url = new URL("http://brickset.com/api/v2.asmx/login?apiKey="
					+ prop.getProperty(API_KEY) + "&username="
					+ prop.getProperty(USERNAME) + "&password="
					+ prop.getProperty(PASSWORD));
			
			logger.info(url.toString());
			InputStream is = url.openStream();
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser;
			parser = parserFactor.newSAXParser();
			LoginResponseParserHandler handler = new LoginResponseParserHandler();
			parser.parse(is, handler);
			userHash = handler.getLoginResponse().getLoginResult().replace("#", "%23");
			logger.info("LoginUtils userHash :"+userHash);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static LoginUtils getInstance() {
		if (rs == null) {
			synchronized (LoginUtils.class) {
				if (rs == null) {
					rs = new LoginUtils();
				}
			}
		}
		return rs;
	}

	public static String getUserHash() {
		return userHash;
	}

}
