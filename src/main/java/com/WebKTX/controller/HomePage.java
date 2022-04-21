package com.WebKTX.controller;

import com.WebKTX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Controller
class HomePage {
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/homepage")
	public String homePage(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		model.addAttribute("currentUser",auth);

		return "thong-tin-sinh-vien";
	}







}
