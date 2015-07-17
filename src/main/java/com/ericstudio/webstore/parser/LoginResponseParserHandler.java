package com.ericstudio.webstore.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ericstudio.webstore.domain.brick.v2.LoginResponse;

public class LoginResponseParserHandler extends DefaultHandler {

	LoginResponse loginResponse = null;
	String content = null;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		// Create a LoginResponse object when the start tag is found
		case "string":
			loginResponse = new LoginResponse();
			loginResponse.setLoginResult(content);
			break;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName) {

		// Add the employee to list once end tag is found
		case "string":
			loginResponse.setLoginResult(content);
			break;

		}

	}

	public LoginResponse getLoginResponse() {
		return loginResponse;
	}

	

}
