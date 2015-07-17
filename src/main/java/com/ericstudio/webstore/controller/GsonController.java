package com.ericstudio.webstore.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericstudio.webstore.domain.KaoBus;
import com.ericstudio.webstore.service.GsonService;
import com.ericstudio.webstore.utils.ListUtils;

@Controller
@RequestMapping(value = "/gson")
public class GsonController {

	@Autowired
	private GsonService gsonService;

	private List<KaoBus> resultlist = null;

	@RequestMapping
	public String index(Model model) {
		//
		// try {
		// resultlist = gsonService.getKaoBusInRealTime();
		// int[] index = ListUtils.getSublistRange(new BigDecimal(10),
		// new BigDecimal(1), new BigDecimal(resultlist.size()));
		//
		// model.addAttribute("buses", resultlist.subList(index[0], index[1]));
		// } catch (Exception e) {
		// System.out.println(ExceptionUtils.getStackTrace(e));
		// }
		return "gson";
	}

	@RequestMapping(value = "/d3")
	public String d3(Model model) {

		return "d3";
	}

	@RequestMapping(value = "/d3/BulletCharts")
	public String Bullet(Model model) {

		return "BulletCharts";
	}

	@RequestMapping(value = "/d3/BulletData")
	public @ResponseBody
	String BulletData() {
		String str = "[{\"title\":\"Revenue\",\"subtitle\":\"US$, in thousands\",\"ranges\":[150,225,300],\"measures\":[220,270],\"markers\":[250]},{\"title\":\"Profit\",\"subtitle\":\"%\",\"ranges\":[20,25,30],\"measures\":[21,23],\"markers\":[26]},{\"title\":\"Order Size\",\"subtitle\":\"US$, average\",\"ranges\":[350,500,600],\"measures\":[100,320],\"markers\":[550]},{\"title\":\"New Customers\",\"subtitle\":\"count\",\"ranges\":[1400,2000,2500],\"measures\":[1000,1650],\"markers\":[2100]},{\"title\":\"Satisfaction\",\"subtitle\":\"out of 5\",\"ranges\":[3.5,4.25,5],\"measures\":[3.2,4.7],\"markers\":[4.4]}]";
		// JSONObject obj = new
		// JSONObject("[{\"title\":\"Revenue\",\"subtitle\":\"US$, in thousands\",\"ranges\":[150,225,300],\"measures\":[220,270],\"markers\":[250]},{\"title\":\"Profit\",\"subtitle\":\"%\",\"ranges\":[20,25,30],\"measures\":[21,23],\"markers\":[26]},{\"title\":\"Order Size\",\"subtitle\":\"US$, average\",\"ranges\":[350,500,600],\"measures\":[100,320],\"markers\":[550]},{\"title\":\"New Customers\",\"subtitle\":\"count\",\"ranges\":[1400,2000,2500],\"measures\":[1000,1650],\"markers\":[2100]},{\"title\":\"Satisfaction\",\"subtitle\":\"out of 5\",\"ranges\":[3.5,4.25,5],\"measures\":[3.2,4.7],\"markers\":[4.4]}]");
		return str;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public @ResponseBody
	List<KaoBus> changeListPage(@RequestParam(value = "pageNum") String pageNum) {
		int[] index = new int[2];
		try {
			// resultlist = gsonService.getKaoBusInRealTime();
			resultlist = ListUtils.getKaoBusList();
			index = ListUtils.getSublistRange(new BigDecimal(10),
					new BigDecimal(pageNum), new BigDecimal(resultlist.size()));

		} catch (Exception e) {
			System.out.println(ExceptionUtils.getStackTrace(e));
		}
		return resultlist.subList(index[0], index[1]);
	}
}
