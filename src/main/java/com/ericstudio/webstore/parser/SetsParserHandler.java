package com.ericstudio.webstore.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ericstudio.webstore.domain.brick.v2.Sets;

public class SetsParserHandler extends DefaultHandler {

	List<Sets> resultList = new ArrayList<>();
	Sets sets = null;
	String content = "";

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		// Create a LoginResponse object when the start tag is found
		case "sets":
			sets = new Sets();
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

		// Add the set to list once end tag is found
		case "sets":
			resultList.add(sets);
		case "setID":
			if (!StringUtils.equals(content, "")) {
				sets.setSetID(Integer.valueOf(content));
			}
			break;
		case "number":
			sets.setNumber(content);
			break;
		case "numberVariant":
			sets.setNumberVariant(Integer.valueOf(content));
			break;
		case "name":
			sets.setName(content);
			break;
		case "year":
			sets.setYear(content);
			break;
		case "theme":
			sets.setTheme(content);
			break;
		case "themeGroup":
			sets.setThemeGroup(content);
			break;
		case "subtheme":
			sets.setSubtheme(content);
			break;
		case "pieces":
			sets.setPieces(content);
			break;
		case "minifigs":
			sets.setMinifigs(content);
			break;
		case "image":
			sets.setImage(Boolean.valueOf(content));
			break;
		case "imageFilename":
			sets.setImageFilename(content);
			break;
		case "thumbnailURL":
			sets.setThumbnailURL(content);
			break;
		case "largeThumbnailURL":
			sets.setLargeThumbnailURL(content);
			break;
		case "imageURL":
			sets.setImageURL(content);
			break;
		case "bricksetURL":
			sets.setBricksetURL(content);
			break;
		case "owned":
			sets.setOwned(Boolean.valueOf(content));
			break;
		case "wanted":
			sets.setWanted(Boolean.valueOf(content));
			break;
		case "qtyOwned":
			sets.setQtyOwned(Integer.valueOf(content));
			break;
		case "userNotes":
			sets.setUserNotes(content);
			break;
		case "ACMDataCount":
			sets.setACMDataCount(Integer.valueOf(content));
			break;
		case "ownedByTotal":
			sets.setOwnedByTotal(Integer.valueOf(content));
			break;
		case "wantedByTotal":
			sets.setWantedByTotal(Integer.valueOf(content));
			break;
		case "UKRetailPrice":
			sets.setUKRetailPrice(content);
			break;
		case "USRetailPrice":
			sets.setUSRetailPrice(content);
			break;
		case "CARetailPrice":
			sets.setCARetailPrice(content);
			break;
		case "EURetailPrice":
			sets.setEURetailPrice(content);
			break;
		case "rating":
			sets.setRating(new BigDecimal(content));
			break;
		case "reviewCount":
			sets.setReviewCount(Integer.valueOf(content));
			break;
		case "packagingType":
			sets.setPackagingType(content);
			break;
		case "availability":
			sets.setAvailability(content);
			break;
		case "instructionsCount":
			sets.setInstructionsCount(Integer.valueOf(content));
			break;
		case "additionalImageCount":
			sets.setAdditionalImageCount(Integer.valueOf(content));
			break;
		case "EAN":
			sets.setEAN(content);
			break;
		case "UPC":
			sets.setUPC(content);
			break;
		case "description":
			sets.setDescription(content);
			break;
		case "lastUpdated":
			break;

		}

	}

	public List<Sets> getResultList() {
		return resultList;
	}

}
