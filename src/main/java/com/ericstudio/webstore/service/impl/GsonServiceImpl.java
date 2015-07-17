package com.ericstudio.webstore.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ericstudio.webstore.domain.KaoBus;
import com.ericstudio.webstore.service.GsonService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class GsonServiceImpl implements GsonService {

	@Override
	public List<KaoBus> getKaoBusInRealTime() throws Exception {
		BufferedReader reader = null;

		URL url = new URL("http://ibus.tbkc.gov.tw/xmlbus/GetBusData");

		InputStream is = url.openStream();
		reader = new BufferedReader(new InputStreamReader(is,
				Charset.forName("UTF-8")));
		StringBuffer buffer = new StringBuffer();
		int read;
		char[] chars = new char[1024];
		while ((read = reader.read(chars)) != -1) {
			buffer.append(chars, 0, read);
		}
		String jsonStr = buffer.toString();
		Type listType = new TypeToken<ArrayList<KaoBus>>() {
		}.getType();

		// KaoBus[] result = new Gson().fromJson(jsonStr, KaoBus[].class);
		//
		// List<KaoBus> mcList = new ArrayList<>(Arrays.asList(result));
		List<KaoBus> result = new Gson().fromJson(jsonStr, listType);

		reader.close();
		return result;

	}
}
