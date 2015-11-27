package com.asynclife.clonegod.template.gtvg.thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gtvg")
public class UserProfileController {

	@RequestMapping("userprofile")
	public String process() {
       return "userprofile";
    }

}
