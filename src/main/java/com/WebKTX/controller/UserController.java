package com.WebKTX.controller;

import com.WebKTX.model.ConfirmationToken;
import com.WebKTX.model.User;

import com.WebKTX.repository.ConfirmationTokenRepository;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.EmailSenderService;
import com.WebKTX.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
    private UserDetailService userDetailService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository repo;

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());

        return "signup-form";
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user)
    {

        User existingUser = repo.findByEmail(user.getEmail());
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
            repo.save(user);

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
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = repo.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            repo.save(user);
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

}

