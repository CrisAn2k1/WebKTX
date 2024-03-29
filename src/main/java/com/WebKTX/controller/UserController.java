package com.WebKTX.controller;

import com.WebKTX.model.Hosochuyenphong;
import com.WebKTX.model.User;
import com.WebKTX.repository.HoSoChuyenPhongRepository;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.HoSoChuyenPhongService;
import com.WebKTX.service.UserDetail;
import com.WebKTX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

@Service
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder; // = new BCryptPasswordEncoder();
    @Autowired
    private HoSoChuyenPhongRepository hosoChuyenPhongRepo;

    @Autowired
    @Qualifier("hscpService")
    private HoSoChuyenPhongService hosoChuyenPhongService;

   //================================

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());

        return "signup-form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user,Model model, HttpServletRequest request, RedirectAttributes redirect)
            throws UnsupportedEncodingException, MessagingException {
        if(!user.getPassword().equals(user.getConfirmPassowrd()))
        {
            redirect.addFlashAttribute("error","Mật khẩu không trùng khớp!");
            return "redirect:/register";

        }else if(userRepo.findByEmail(user.getEmail()) != null) {
            redirect.addFlashAttribute("error","Email đã tồn tại!");
            System.out.println("mail đã tồn tại ");
            return "redirect:/register";

        }else if(userRepo.findByUsername(user.getUsername()) != null){
            redirect.addFlashAttribute("error","Username đã tồn tại!!");
            return "redirect:/register";
        }
            userService.register(user, getSiteURL(request));
            model.addAttribute("emailId", user.getEmail());
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

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    //Get current user login
    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetail currentUser = (UserDetail) auth.getPrincipal();
        Integer userId = currentUser.id();
        User user = userRepo.findById(userId).orElse(null);
        return user;
    }

    @GetMapping({"/thong-tin-sinh-vien","/homepage","/"})
    @PreAuthorize("hasAnyAuthority('user')")
    public String indexPage(Model model){
        if(getCurrentUser() != null)
        {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            model.addAttribute("currentYear",year);
            model.addAttribute("currentUser",getCurrentUser());
        }
        return "thong-tin-sinh-vien";
    }

    @GetMapping("/thong-tin-lien-he")
    public String infoPage(Model model){
        if(getCurrentUser() != null)
        {
            model.addAttribute("currentUser",getCurrentUser());
        }
        return "thong-tin-lien-he";
    }

    @GetMapping("/thong-bao")
    public String noticePage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "thong-bao";
    }

    @GetMapping({"/signup_success","/login-success"})
    public String welcome() {
        return "login";
    }


    // Đăng ký chuyển phòng
    @GetMapping("/dang-ky-chuyen-phong")
    public String registerChuyenPhong(Model model) {
        if(getCurrentUser() != null) {
            if (getCurrentUser().getIdPhong()==null){
                model.addAttribute("error",true);
            }
            else {
                model.addAttribute("error",false);
                model.addAttribute("user", getCurrentUser());
                model.addAttribute("newCP", new Hosochuyenphong());
            }
        }
        return "form-chuyen-phong";
    }

    @PostMapping("/process_chuyenphong")
    public String processChuyenPhong(Hosochuyenphong hosochuyenphong) {
        if(getCurrentUser() != null) {
            hosoChuyenPhongService.addChuyenPhong(getCurrentUser().getId(), hosochuyenphong);
            return "dangkychuyenphong-success";
        }
        return "redirect:/login";
    }


    // ========== Đổi mật khẩu
    @GetMapping("/doi-mat-khau")
    public String changePassword(Model model){

        model.addAttribute("currentUser",getCurrentUser());
        model.addAttribute("newPassword",new User());
        return "change-password";
    }

    @PostMapping("/process-change-password")
    public String setChangePassword(User user,RedirectAttributes redirect){
        boolean checkMached = passwordEncoder.matches(user.getOldPassword(),getCurrentUser().getPassword());
        if ( checkMached ){
            if(!user.getPassword().equals(user.getConfirmPassowrd()) ){
                redirect.addFlashAttribute("message","Nhập lại mật khẩu không trùng khớp");
                return "redirect:/doi-mat-khau";
            }
        }
        else{
                redirect.addFlashAttribute("message","Mật khẫu cũ không đúng!");
                return "redirect:/doi-mat-khau";
            }
        String newPassword = passwordEncoder.encode(user.getPassword());
        getCurrentUser().setPassword(newPassword);
        userRepo.save(getCurrentUser());
        return "redirect:/login";
    }
}

