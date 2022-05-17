package com.WebKTX.controller;

import com.WebKTX.model.Hosodangky;
import com.WebKTX.model.User;
import com.WebKTX.repository.HoSoDangKyRepository;
import com.WebKTX.repository.PhongRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

@Service
@Controller
class HomePage {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DangKyKtxService dangKyKtxService;

	@Autowired
	private HoSoDangKyRepository hoSoDangKyRepo;

	@Autowired
	private PhongRepository phongRepo;

	public User getCurrentUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetail currentUser = (UserDetail) auth.getPrincipal();
		Integer userId = currentUser.id();
		User user = userRepo.findById(userId).orElse(null);
		return user;
	}

	@GetMapping("/huong-dan-dang-ky-o-ktx")
	public String guideRegister(Model model) {
		if(hoSoDangKyRepo.findByTrangthai(false).isEmpty()){
			model.addAttribute("allow",true);
			return "guide-register-ktx";
		}
		model.addAttribute("allow",false);
		return "guide-register-ktx";
	}

	@GetMapping("/form-dang-ky-o-ktx")
	public String formRegister(Model model){

		model.addAttribute("newUserKTX",getCurrentUser());
		return "form-register-ktx";
	}

	@GetMapping("/test")
	public String test(){

		return "testDelete";
	}
	@PostMapping("/form-dang-ky-o-ktx/update")
	public String updateInformation(User user, ModelMap model, RedirectAttributes redirect) throws IOException {
		dangKyKtxService.registerKTX(user.getId(), user );
		if(user.getFile().isEmpty() || user.getHoten().equals("")){
			redirect.addFlashAttribute("error","Vui lòng điền đầy đủ thông tin!");
			return "redirect:/form-dang-ky-o-ktx";
		}
		Path path = Paths.get("src/main/resources/static/assets/avatar");
		try{
			InputStream inputStream = user.getFile().getInputStream();
			Files.copy(inputStream,path.resolve(user.getFile().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			getCurrentUser().setAvatar("/assets/avatar/" + user.getFile().getOriginalFilename().toLowerCase());
			model.addAttribute("infoUser",getCurrentUser());

			// Add hồ sơ đăng ký của user
			Hosodangky hosodangky = new Hosodangky();
			hosodangky.setTrangthai(false);
			hosodangky.setNgaytao(Instant.now());
			hosodangky.setIdUser(getCurrentUser());
			//=========================

			hoSoDangKyRepo.save(hosodangky);

			userRepo.save(getCurrentUser());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/thong-tin-sinh-vien";
	}



}
