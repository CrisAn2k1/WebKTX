package com.WebKTX.controller;

import com.WebKTX.model.*;
import com.WebKTX.repository.*;
import com.WebKTX.service.PhongNoiThatService;
import com.WebKTX.service.HoSoDangKyService;
import com.WebKTX.service.HoSoChuyenPhongService;
import com.WebKTX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private ConfirmToken confirmToken;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PhongRepository phongRepo;

    @Autowired
    private PhongNoiThatRepository phongNoiThatRepo;

    @Autowired
    private PhongNoiThatService phongNoiThatService;

    @Autowired
    private HoSoDangKyRepository hosoDangKyRepo;

    @Autowired
    @Qualifier("hsdkService")
    private HoSoDangKyService hosoDangKyService;

    @Autowired
    private HoSoChuyenPhongRepository hosoChuyenPhongRepo;

    @Autowired
    @Qualifier("hscpService")
    private HoSoChuyenPhongService hosoChuyenPhongService;
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

    @GetMapping("/quan-ly-sinh-vien")
    public String listUser(Model model){
        List<Phong> listPhong = phongRepo.findAll();
        model.addAttribute("listPhong",listPhong);


        return "phong-list-user";
    }
    @GetMapping("/quan-ly-sinh-vien/{idPhong}")
    public String listUserPhong(@PathVariable("idPhong") String idPhong, Model model){
        System.out.println("Phòng :" + idPhong);
        List<User> listUser = userRepo.findByIdPhong(idPhong);
        model.addAttribute("listUser",listUser);
        model.addAttribute("maphong",idPhong);


        return "list-user";
    }
    // Hàm chỉnh sửa thông tin user
    // (GET: Truyền và hiển thị vào dữ liệu người dùng trước khi chỉnh sửa)
    @GetMapping("/quan-ly-sinh-vien/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User editUser = userRepo.findById(id).orElse(null);
        if(editUser == null){
            return "redirect:/quan-ly-sinh-vien";
        }
        else {
            model.addAttribute("editUser",editUser);
            return "edit";
        }
    }
    // (POST: thực hiện các câu truy vấn và tiến hành set giá trị thay đổi.)
    @PostMapping("/quan-ly-sinh-vien/edit")
    public String updateUser(User user){
        userService.updateInfo(user.getId(), user);
        return "redirect:/admin/quan-ly-sinh-vien";
    }

    // Hàm dùng để xoá user
    @GetMapping("/quan-ly-sinh-vien/{id}/{idToken}/remove")
    public String removeUser(@PathVariable("id") Integer id, @PathVariable("idToken") Long idToken){
        confirmToken.deleteById(idToken);
        userService.removeUser(id);
        return "redirect:/admin/quan-ly-sinh-vien";
    }

    // Quản lý nội thất
    @GetMapping("/furniture-management")
    public String listPhong(Model model){
        List<Phong> listPhong =  phongRepo.findAll();
        model.addAttribute("listPhong",listPhong);
        return "admin/furniture-management";
    }

    @GetMapping("/furniture-management/{idPhong}")
    public String listFurPhong(@PathVariable("idPhong") String idPhong, Model model){
        System.out.println("Phòng :" + idPhong);
        List<PhongNoithat> listFur = phongNoiThatRepo.findByIdPhong(idPhong);
        model.addAttribute("listFur",listFur);
        model.addAttribute("maphong",idPhong);
        return "admin/furniture-item";
    }

    @GetMapping("/furniture-management/{id}/edit")
    public String editFur(@PathVariable("id") Integer idPhongNoiThat, Model model){
        PhongNoithat editFur = phongNoiThatRepo.findById(idPhongNoiThat).orElse(null);
        if(editFur == null){
            System.out.println("Không có bất kỳ kết quả nào ===============");

            return "redirect:/furniture-management";
        }
        else {
            model.addAttribute("editFur",editFur);
            return "admin/edit-furniture";
        }
    }

    @PostMapping("/furniture-management/edit")
    public String updateFurniture(PhongNoithat phongNoithat){
        phongNoiThatService.updatePhongNoithat(phongNoithat.getId(),phongNoithat);
        return "redirect:/admin/furniture-management";
    }

    @GetMapping("/furniture-management/{id}/remove")
    public String removeFurniture(@PathVariable("id") Integer idPhongNoiThat){
        phongNoiThatService.removeFurniture(idPhongNoiThat);
        return "redirect:/admin/furniture-management";
    }
    //================================

    @GetMapping("/hosodangky-management")
    public String listHosodangky(Model model){
        List<Hosodangky> listHosodangky =  hosoDangKyRepo.findAll();
        model.addAttribute("listHosodangky",listHosodangky);
        return "admin/hosodangky-management";
    }

    @GetMapping("/hosodangky-management/{id}/edit")
    public String editHSdangky(@PathVariable("id") Integer idHoSoDangKy, Model model){
        Hosodangky editHSdangky = hosoDangKyRepo.findById(idHoSoDangKy).orElse(null);
        if(editHSdangky == null){
            System.out.println("Không có bất kỳ kết quả nào ===============");

            return "redirect:/hosodangky-management";
        }
        else {
            model.addAttribute("editHSdangky",editHSdangky);
            return "admin/edit-hosodangky";
        }
    }

    @PostMapping("/hosodangky-management/edit")
    public String updateHSdangky(Hosodangky hosodangky){
        hosoDangKyService.updateHosodangky(hosodangky.getId(),hosodangky);
        return "redirect:/admin/hosodangky-management";
    }

    @GetMapping("/hosodangky-management/{id}/remove")
    public String removeHosodangky(@PathVariable("id") Integer idHosodangky){
        hosoDangKyService.removeHosodangky(idHosodangky);
        return "redirect:/admin/hosodangky-management";
    }
    //================================

    @GetMapping("/hosochuyenphong-management")
    public String listHosochuyenphong(Model model){
        List<Hosochuyenphong> listHosochuyenphong =  hosoChuyenPhongRepo.findAll();
        model.addAttribute("listHosochuyenphong",listHosochuyenphong);
        return "admin/hosochuyenphong-management";
    }

    @GetMapping("/hosochuyenphong-management/{id}/edit")
    public String editHSchuyenphong(@PathVariable("id") Integer idHoSoChuyenPhong, Model model){
        Hosochuyenphong editHSchuyenphong = hosoChuyenPhongRepo.findById(idHoSoChuyenPhong).orElse(null);
        if(editHSchuyenphong == null){
            System.out.println("Không có bất kỳ kết quả nào ===============");

            return "redirect:/hosochuyenphong-management";
        }
        else {
            model.addAttribute("editHSchuyenphong",editHSchuyenphong);
            return "admin/edit-hosochuyenphong";
        }
    }

    @PostMapping("/hosochuyenphong-management/edit")
    public String updateHSchuyenphong(Hosochuyenphong hosochuyenphong){
        hosoChuyenPhongService.updateHosochuyenphong(hosochuyenphong.getId(),hosochuyenphong);
        return "redirect:/admin/hosochuyenphong-management";
    }

    @GetMapping("/hosochuyenphong-management/{id}/remove")
    public String removeHosochuyenphong(@PathVariable("id") Integer idHosochuyenphong){
        hosoChuyenPhongService.removeHosochuyenphong(idHosochuyenphong);
        return "redirect:/admin/hosochuyenphong-management";
    }
}
