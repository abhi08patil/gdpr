package com.gdpr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gdpr/v1")
public class ReportsController {

	
	
	
	@RequestMapping("/predeletion")
	public String datahold() {
		return "report/predeletion";
	}
	
	
	@RequestMapping("/future")
	public String dataHoldMapping() {
		return "report/future";
	}
}
