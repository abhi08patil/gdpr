package com.gdpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gdpr.service.ReportService;

@Controller
@RequestMapping("/gdpr/v1")
public class ReportsController  {

	
	@Autowired
	ReportService reportService;

	
	
	@GetMapping("/predeletion")
	public ModelAndView getCycleDates(@RequestParam(value = "list" , required = false) List<String> list) {
		ModelAndView modelAndView = new ModelAndView("report/predeletion");
		modelAndView.addObject("list",list);
		modelAndView.addObject("map", reportService.getCycleDates());
		return modelAndView;
	}
	
	@PostMapping("/generatePreDeletionReport")
	public ModelAndView generatePreDeletionReport(@RequestParam("tableName") String cycleName ,ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("report/future");
		model.addAttribute("list",reportService.getGeneratedReportList(cycleName));
		return modelAndView;
	}
	
	
	@RequestMapping("/future")
	public String dataHoldMapping() {
		return "report/future";
	}
}
