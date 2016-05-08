package com.asynclife.wx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx")
public class WxJsController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}
