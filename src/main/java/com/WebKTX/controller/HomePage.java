package com.WebKTX.controller;

import com.WebKTX.model.User;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.DangKyKtxService;
import com.WebKTX.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

	@GetMapping("/huong-dan-dang-ky-o-ktx")
	public String guideRegister(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		model.addAttribute("idUser", userId);
		return "/guide-register-ktx";
	}

	@GetMapping("/form-dang-ky-o-ktx/{id}/update")
	public String formRegister(@PathVariable("id") Integer id, Model model){
		User newUserKTX = userRepo.findById(id).orElse(null);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		User user = userRepo.findById(userId).orElse(null);
		model.addAttribute("newUserKTX",newUserKTX);
		return "form-register-ktx";
	}

	@PostMapping("/form-dang-ky-o-ktx/update")
	public String updateInformation(
	@RequestParam("avatar")MultipartFile avatar, ModelMap model) throws IOException {
//		dangKyKtxService.registerKTX(user.getId(), user );
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		User user = userRepo.findById(userId).orElse(null);
		if(avatar.isEmpty()){
			return "/thong-tin-sinh-vien";
		}
		Path path = Paths.get("src/main/resources/static/assets/avatar");
		try{
			InputStream inputStream = avatar.getInputStream();
			Files.copy(inputStream,path.resolve(avatar.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			user.setAvatar("./assets/avatar/" + avatar.getOriginalFilename().toLowerCase());
			model.addAttribute("infoUser",user);
			userRepo.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/thong-tin-sinh-vien";
	}



}
