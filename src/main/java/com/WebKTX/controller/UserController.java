package com.WebKTX.controller;

import com.WebKTX.model.Hosochuyenphong;
import com.WebKTX.model.User;
import com.WebKTX.repository.HoSoChuyenPhongRepository;
import com.WebKTX.repository.PhongNoiThatRepository;
import com.WebKTX.repository.RoleRepository;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.HoSoChuyenPhongService;
import com.WebKTX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Service
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private HoSoChuyenPhongRepository hosoChuyenPhongRepo;

    @Autowired
    @Qualifier("hscpService")
    private HoSoChuyenPhongService hosoChuyenPhongService;
//    //================================
    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());

        return "signup-form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        userService.register(user, getSiteURL(request));
        return "/signup-success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify-success";
        } else {
            return "verify-fail";
        }
    }

//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    public ModelAndView registerUser(ModelAndView modelAndView, User user){
//
//        User existingUser = userRepo.findByEmail(user.getEmail());
//        if(existingUser != null)
//        {
//            modelAndView.addObject("message","This email already exists!");
//            modelAndView.setViewName("error");
//        }
//        else
//        {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword);
//
//            user.setRoles(roleRepo.findByName("user"));
//            userRepo.save(user);
//
//            modelAndView.addObject("emailId", user.getEmail());
//
//            modelAndView.setViewName("signup-success");
//        }
//
//        return modelAndView;
//    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/thong-tin-sinh-vien")
    @PreAuthorize("hasAnyAuthority('user')")
    public String indexPage(){
        return "thong-tin-sinh-vien";
    }

    @GetMapping("/thong-tin-lien-he")
    public String infoPage(){
        return "thong-tin-lien-he";
    }

    @GetMapping("/thong-bao")
    public String noticePage(){
        return "thong-bao";
    }

    @GetMapping({"/","/signup_success","/login-success"})
    public String welcome() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/dang-ky-chuyen-phong")
    public String registerChuyenPhong(Model model) {
        model.addAttribute("newCP", new Hosochuyenphong());
        return "form-chuyen-phong";
    }

    @PostMapping("/process_chuyenphong")
    public String processChuyenPhong(Hosochuyenphong hosochuyenphong) {
        hosoChuyenPhongService.processChuyenPhong(hosochuyenphong.getId(), hosochuyenphong);
        return "dangkychuyenphong-success";
    }
}

