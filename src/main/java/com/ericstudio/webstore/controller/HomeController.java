package com.ericstudio.webstore.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("greeting", "Chart");
		model.addAttribute("tagline", "");

		return "forward:/products";
	}

	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "welcome";
	}

	@RequestMapping("/barChart")
	public String barChart() {
		return "barChart";
	}

	@RequestMapping("/pieChart")
	public String pieChart() {
		return "pieChart";
	}

	
}