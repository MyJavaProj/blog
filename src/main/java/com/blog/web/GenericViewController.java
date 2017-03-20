package com.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenericViewController {

	@RequestMapping("/jsp/sample")
	public String getSampleJsp(){
		return "SampleJsp";
	}
	
	@RequestMapping("/config/sample.config")
	public String getConfig(){
		return "SampleConfig.config";
	}
	@RequestMapping("/swf/sample.swf")
	public String getSwf(){
		return "SampleSwf.swf";
	}
}
