package com.WebKTX.controller;

import com.WebKTX.model.User;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Controller
class HomePage {

	@Autowired
	private UserRepository userRepo;

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

	@GetMapping("/upload")
	public String formUpload(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		User user = userRepo.findById(userId).orElse(null);
		model.addAttribute(user);
		return "upload";
	}

	@PostMapping("/save")
	public String save(@RequestParam("avatar")MultipartFile avatar, ModelMap model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		User user = userRepo.findById(userId).orElse(null);
		if(avatar.isEmpty()){
			return "upload";
		}
		Path path = Paths.get("src/main/resources/static/assets/avatar");
		try{
			InputStream inputStream = avatar.getInputStream();
			Files.copy(inputStream,path.resolve(avatar.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			user.setAvatar("./assets/avatar/" + avatar.getOriginalFilename().toLowerCase());
			model.addAttribute("user",user);
			userRepo.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "view";
	}

}
