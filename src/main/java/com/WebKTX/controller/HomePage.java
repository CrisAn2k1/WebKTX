package com.WebKTX.controller;

import com.WebKTX.model.User;
import com.WebKTX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Controller
class HomePage {
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/homepage")
	public String homePage(Model model) {
		List<User> listUser = userRepo.findAll();
		model.addAttribute("listUser",listUser);

		return "index";
	}







}
