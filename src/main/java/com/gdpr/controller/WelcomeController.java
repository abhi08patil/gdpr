package com.gdpr.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gdpr/v1")
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/login")
	public String login(Map<String, Object> model) {
		model.put("message", this.message);
		return "login";
	}

	@PostMapping("/auth")
	public String auth(@RequestParam("username") String username, @RequestParam("password") String password) {

		String page = "error";

		if (username.equals("gdpr_admin") && password.equals("gdprpassword")) {
				page = "redirect:/gdpr/v1/welcome";
		}
		else {
			page = "redirect:/gdpr/v1/error";
		}

		return page;
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "loginerror";
	}

}