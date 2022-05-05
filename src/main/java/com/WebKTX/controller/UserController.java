package com.WebKTX.controller;

import com.WebKTX.model.ConfirmationToken;
import com.WebKTX.model.User;

import com.WebKTX.repository.*;
import com.WebKTX.service.EmailSenderService;
import com.WebKTX.service.PhongNoiThatService;
import com.WebKTX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Service
@Controller
public class UserController {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmToken confirmToken;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PhongNoiThatRepository furnitureRepo;

    @Autowired
    private PhongNoiThatService furnitureService;


    @Autowired
    private UserService userService;

//    @GetMapping("/testUser")
//    public String listUser(Model model){
//        List<User> listUser = (List<User>) userRepo.findAll();
//        model.addAttribute("listUser",listUser);
//
//
//        return "testUser";
//    }
//    // Hàm chỉnh sửa thông tin user
//    // (GET: Truyền và hiển thị vào dữ liệu người dùng trước khi chỉnh sửa)
//    @GetMapping("/testUser/{id}/edit")
//    public String editUser(@PathVariable("id") Integer id, Model model){
//        User editUser = userRepo.findById(id).orElse(null);
//        if(editUser == null){
//            return "redirect:/testUser";
//        }
//        else {
//            model.addAttribute("editUser",editUser);
//            return "edit";
//        }
//    }
//    // (POST: thực hiện các câu truy vấn và tiến hành set giá trị thay đổi.)
//    @PostMapping("/testUser/edit")
//    public String updateUser(User user){
//        userService.updateInfo(user.getId(), user);
//        return "redirect:/testUser";
//    }
//
//    // Hàm dùng để xoá user
//    @GetMapping("/testUser/{id}/{idToken}/remove")
//    public String removeUser(@PathVariable("id") Integer id, @PathVariable("idToken") Long idToken){
//        confirmToken.deleteById(idToken);
//        userService.removeUser(id);
//        return "redirect:/testUser";
//    }
//
//    // Furniture Management
//
//    @GetMapping("/furniture-management")
//    public String listFur(Model model){
//        List<Danhmucnoithat> listFur = (List<Danhmucnoithat>) furnitureRepo.findAll();
//        model.addAttribute("listFur",listFur);
//        return "furniture-management";
//    }
//
//    @GetMapping("/furniture-management/{id}/edit")
//    public String editFur(@PathVariable("id") Integer id, Model model){
//        Danhmucnoithat editFur = furnitureRepo.findById(id).orElse(null);
//        if(editFur == null){
//            return "redirect:/furniture-management";
//        }
//        else {
//            model.addAttribute("editFur",editFur);
//            return "edit-furniture";
//        }
//    }
//
//    @PostMapping("/furniture-management/edit")
//    public String updateFurniture(Danhmucnoithat danhmucnoithat){
//        furnitureService.updateFurniture(danhmucnoithat.getId(), danhmucnoithat);
//        return "redirect:/furniture-management";
//    }
//
//    @GetMapping("/furniture-management/{id}/remove")
//    public String removeFurniture(@PathVariable("id") Integer id){
//
//        furnitureService.removeFurniture(id);
//        return "redirect:/furniture-management";
//    }
//    //================================
    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());

        return "signup-form";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user){

        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            user.setRoles(roleRepo.findByName("user"));
            userRepo.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("nhbtoan1503@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getEmail());

            modelAndView.setViewName("signup-success");
        }

        return modelAndView;
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepo.findByEmail(token.getUser().getEmail());
            user.setIsEnabled(true);
            userRepo.save(user);
            modelAndView.setViewName("login");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

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


}

