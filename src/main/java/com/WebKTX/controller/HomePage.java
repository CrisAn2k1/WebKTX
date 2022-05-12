package com.WebKTX.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Controller
class HomePage {

	@GetMapping("/homepage")
	public String homePage(Model model) {
		return "thong-tin-sinh-vien";
	}

	@GetMapping("/huong-dan-dang-ky-o-ktx")
	public String guideRegister(){
		return "guide-register-ktx";
	}

	@GetMapping("/form-dang-ky-o-ktx")
	public String formRegister(){
		return "form-register-ktx";
	}

	@GetMapping("/form-chuyen-phong")
	public String formChuyenphong(){
		return "form-chuyen-phong";
	}




}
