package com.gdpr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gdpr/v1")
public class DataRetentionController {

	
	
	
	@RequestMapping("/datahold")
	public String datahold() {
		return "datahold/datahold";
	}
	
	
	@RequestMapping("/dataholdmapping")
	public String dataHoldMapping() {
		return "datahold/dataholdmapping";
	}
}
