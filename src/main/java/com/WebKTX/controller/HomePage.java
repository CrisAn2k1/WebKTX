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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
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

	@Autowired
	private DangKyKtxService dangKyKtxService;

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
	public String updateInformation( User user,ModelMap model) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		User newUserKTX = userRepo.findById(userId).orElse(null);
		dangKyKtxService.registerKTX(user.getId(), user );
		if(user.getFile().isEmpty()){
			return "/thong-tin-sinh-vien";
		}
		Path path = Paths.get("src/main/resources/static/assets/avatar");
		try{
			InputStream inputStream = user.getFile().getInputStream();
			Files.copy(inputStream,path.resolve(user.getFile().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			newUserKTX.setAvatar("./assets/avatar/" + user.getFile().getOriginalFilename().toLowerCase());
			model.addAttribute("infoUser",newUserKTX);
			userRepo.save(newUserKTX);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/thong-tin-sinh-vien";
	}



}
