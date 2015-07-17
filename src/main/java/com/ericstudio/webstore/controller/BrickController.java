package com.ericstudio.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericstudio.webstore.domain.brick.v2.Sets;
import com.ericstudio.webstore.service.BrickService;

@Controller
@RequestMapping(value = "/bricks")
public class BrickController {
	@Autowired
	private BrickService brickService;

	@RequestMapping
	public String list(Model model) {
		List<Sets> result = brickService.getSetsByYear("2015");

		model.addAttribute("bricks", result);

		return "bricks";
	}

}
