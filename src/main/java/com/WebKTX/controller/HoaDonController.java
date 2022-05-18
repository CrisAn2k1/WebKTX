package com.WebKTX.controller;

import com.WebKTX.model.Chitiethoadon;
import com.WebKTX.model.Hoadon;
import com.WebKTX.model.User;
import com.WebKTX.repository.CTHDRepository;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Service
@Controller
public class HoaDonController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private CTHDRepository cthdRepo;

    @Autowired
    private UserRepository userRepo;
    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetail currentUser = (UserDetail) auth.getPrincipal();
        Integer userId = currentUser.id();
        User user = userRepo.findById(userId).orElse(null);
        return user;
    }

    @GetMapping("/hoadon")
    public String hoadon(Model model){
        if(getCurrentUser() != null && getCurrentUser().getIdPhong() != null)
        {
            Set<Hoadon> listHoaDon = getCurrentUser().getIdPhong().getHoadons();
            model.addAttribute("infoUser", getCurrentUser());
            model.addAttribute("listHoaDon",listHoaDon);
            model.addAttribute("error",false);
        }
        if (getCurrentUser().getIdPhong()==null){
            model.addAttribute("error",true);
        }

        return "/hoadon";
    }

    @GetMapping("/hoadon/{idHD}")
    public String chiTietHD(@PathVariable("idHD") Integer idHD, Model model){

        List<Chitiethoadon> listCTHD = cthdRepo.findByIdHoadon(idHD);
        model.addAttribute("listCTHD",listCTHD);
        model.addAttribute("infoUser", getCurrentUser());
        return "chi-tiet-hoa-don";
    }
}
