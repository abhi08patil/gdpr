package com.gdpr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gdpr/v1")
public class GdprController {

	
	@RequestMapping("/deletehistory")
	public String deletehistory() {
		return "gdpr/deletehistory";
	}
	
	
	@RequestMapping("/configuration")
	public String configuration() {
		return "gdpr/configuration";
	}
	
	@RequestMapping("/bqview")
	public String bqview() {
		return "gdpr/bqview";
	}
	
	@RequestMapping("/deleteproc")
	public String deleteproc() {
		return "gdpr/deleteproc";
	}
	
	
	
	
	
	
}
