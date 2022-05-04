package com.WebKTX.controller;

import com.WebKTX.model.Danhmucnoithat;
import com.WebKTX.model.User;
import com.WebKTX.repository.ConfirmToken;
import com.WebKTX.repository.FurnitureRepository;
import com.WebKTX.repository.RoleRepository;
import com.WebKTX.repository.UserRepository;
import com.WebKTX.service.FurnitureService;
import com.WebKTX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private ConfirmToken confirmToken;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private FurnitureRepository furnitureRepo;

    @Autowired
    private FurnitureService furnitureService;


    @Autowired
    private UserService userService;
    @Bean
    public ClassLoaderTemplateResolver secondaryTemplateResolver() {
        ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
        secondaryTemplateResolver.setPrefix("templates/admin/");
        secondaryTemplateResolver.setSuffix(".html");
        secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
        secondaryTemplateResolver.setCharacterEncoding("UTF-8");
        secondaryTemplateResolver.setOrder(1);
        secondaryTemplateResolver.setCheckExistence(true);

        return secondaryTemplateResolver;
    }

    @GetMapping("/testUser")
    public String listUser(Model model){
        List<User> listUser = (List<User>) userRepo.findAll();
        model.addAttribute("listUser",listUser);


        return "testUser";
    }
    // Hàm chỉnh sửa thông tin user
    // (GET: Truyền và hiển thị vào dữ liệu người dùng trước khi chỉnh sửa)
    @GetMapping("/testUser/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User editUser = userRepo.findById(id).orElse(null);
        if(editUser == null){
            return "redirect:/testUser";
        }
        else {
            model.addAttribute("editUser",editUser);
            return "edit";
        }
    }
    // (POST: thực hiện các câu truy vấn và tiến hành set giá trị thay đổi.)
    @PostMapping("/testUser/edit")
    public String updateUser(User user){
        userService.updateInfo(user.getId(), user);
        return "redirect:/testUser";
    }

    // Hàm dùng để xoá user
    @GetMapping("/testUser/{id}/{idToken}/remove")
    public String removeUser(@PathVariable("id") Integer id, @PathVariable("idToken") Long idToken){
        confirmToken.deleteById(idToken);
        userService.removeUser(id);
        return "redirect:/testUser";
    }

    // Furniture Management

    @GetMapping("/furniture-management")
    public String listFur(Model model){
        List<Danhmucnoithat> listFur = (List<Danhmucnoithat>) furnitureRepo.findAll();
        model.addAttribute("listFur",listFur);
        return "furniture-management";
    }

    @GetMapping("/furniture-management/{id}/edit")
    public String editFur(@PathVariable("id") Integer id, Model model){
        Danhmucnoithat editFur = furnitureRepo.findById(id).orElse(null);
        if(editFur == null){
            return "redirect:/furniture-management";
        }
        else {
            model.addAttribute("editFur",editFur);
            return "edit-furniture";
        }
    }

    @PostMapping("/furniture-management/edit")
    public String updateFurniture(Danhmucnoithat danhmucnoithat){
        furnitureService.updateFurniture(danhmucnoithat.getId(), danhmucnoithat);
        return "redirect:/furniture-management";
    }

    @GetMapping("/furniture-management/{id}/remove")
    public String removeFurniture(@PathVariable("id") Integer id){

        furnitureService.removeFurniture(id);
        return "redirect:/furniture-management";
    }
    //================================
}
