package com.gui.security.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginAction {

	@GetMapping({"/login","/"})
	public String login() {
		return "login";
	}

	@RequestMapping({"/index"})
	public String index(@RequestParam(name = "token") String name,Model model) {
		System.out.println(" ----  "+ name);
		model.addAttribute("token", name);
		return "index";
	}

}
