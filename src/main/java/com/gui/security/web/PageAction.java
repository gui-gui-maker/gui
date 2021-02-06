package com.gui.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pages")
public class PageAction {

	@RequestMapping({"home"})
	public String home() {
		System.out.println("home .....");
		return "home";
	}
	@RequestMapping({"user/{id}"})
	public String user(@PathVariable(name = "id") String page) {
		System.out.println("page .....");
		return page;
	}
	@RequestMapping({"user/upload/{id}"})
	public String userUpload(@PathVariable(name = "id") String page) {
		System.out.println("page .....");
		return "upload/"+page;
	}
}
