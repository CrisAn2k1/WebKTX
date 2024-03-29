package com.WebKTX.controller;

import com.WebKTX.model.*;
import com.WebKTX.repository.*;
import com.WebKTX.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ToaNhaRepository toanhaRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PhongRepository phongRepo;

    @Autowired
    private PhongNoiThatRepository phongNoiThatRepo;

    @Autowired
    private HoaDonRepository hoaDonRepo;

    @Autowired
    private NoiThatRepository noiThatRepo;

    @Autowired
    private PhongNoiThatService phongNoiThatService;

    @Autowired
    private HoSoDangKyRepository hosoDangKyRepo;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DichVuRepository dichVuRepo;

    @Autowired
    private CTHDRepository cthdRepo;

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

    @GetMapping("")
    public String admin(){
        return "redirect:/admin/quan-ly-sinh-vien";
    }

    @GetMapping("/quan-ly-sinh-vien")
    public String listUser(Model model){
        List<Toanha> listToaNha = toanhaRepo.findAll();
        model.addAttribute("listToaNha",listToaNha);
        model.addAttribute("titles","sinh viên");
        model.addAttribute("url","quan-ly-sinh-vien");
        model.addAttribute("currentUser",getCurrentUser());
        return "admin/list-phong";
    }
    @GetMapping("/quan-ly-sinh-vien/{idToaNha}/{idPhong}")
    public String listUserPhong(@PathVariable("idPhong") String idPhong,@PathVariable("idToaNha") String idToaNha, Model model){
        List<User> listUser = userRepo.findByIdPhong(idPhong);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        model.addAttribute("listUser",listUser);
        model.addAttribute("currentYear",year);
        model.addAttribute("matoa",idToaNha);
        model.addAttribute("maphong",idPhong);
        model.addAttribute("currentUser",getCurrentUser());

        return "admin/list-user";
    }

    @GetMapping("/quan-ly-sinh-vien/{idtoa}/{idphong}/{id}/chi-tiet-sinh-vien")
    public String detailUser(@PathVariable("id") Integer id, Model model){
        User detailUser = userRepo.findById(id).orElse(null);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        model.addAttribute("currentYear",year);
        model.addAttribute("matoa",detailUser.getIdPhong().getIdToanha().getId());
        model.addAttribute("maphong",detailUser.getIdPhong().getId());
        model.addAttribute("detailUser",detailUser);
        model.addAttribute("currentUser",getCurrentUser());

        return "admin/chi-tiet-sinh-vien";
    }
    // Hàm chỉnh sửa thông tin user
    // (GET: Truyền và hiển thị vào dữ liệu người dùng trước khi chỉnh sửa)
    @GetMapping("/quan-ly-sinh-vien/{idtoa}/{idphong}/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User editUser = userRepo.findById(id).orElse(null);
        if(editUser == null){
            return "redirect:/quan-ly-sinh-vien";
        }
        else {
            model.addAttribute("matoa",editUser.getIdPhong().getIdToanha().getId());
            model.addAttribute("maphong",editUser.getIdPhong().getId());
            model.addAttribute("editUser",editUser);
            model.addAttribute("currentUser",getCurrentUser());

            return "admin/edit-user";
        }
    }

    //Get current user login
    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetail currentUser = (UserDetail) auth.getPrincipal();
        Integer userId = currentUser.id();
        User user = userRepo.findById(userId).orElse(null);
        return user;
    }


    // (POST: thực hiện các câu truy vấn và tiến hành set giá trị thay đổi.)
    @PostMapping("/quan-ly-sinh-vien/edit")
    public String updateUser(User user, RedirectAttributes redirect){

        if(user.getFile().isEmpty()){
            userService.updateInfo(user.getId(), user);
            return "redirect:/admin/quan-ly-sinh-vien";
        }
        Path path = Paths.get("src/main/resources/static/assets/avatar");
        try{
            InputStream inputStream = user.getFile().getInputStream();
            Files.copy(inputStream,path.resolve(user.getFile().getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            user.setAvatar("/assets/avatar/" + user.getFile().getOriginalFilename().toLowerCase());
            System.out.println("=================="+user.getAvatar());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.updateInfo(user.getId(), user);
        User user_Phong = userRepo.findById(user.getId()).orElse(null);
        return "redirect:/admin/quan-ly-sinh-vien/"+user_Phong.getIdPhong().getIdToanha().getId()+"/"+user_Phong.getIdPhong().getId();
    }

    // Hàm dùng để xoá user
    @GetMapping("/quan-ly-sinh-vien/{id}/remove")
    public String removeUser(@PathVariable("id") Integer id, RedirectAttributes redirect){
        User deleteUser = userRepo.findById(id).orElse(null);
        String url = "redirect:/admin/quan-ly-sinh-vien/"+deleteUser.getIdPhong().getIdToanha().getId()+"/"+deleteUser.getIdPhong().getId();
        if (getCurrentUser().getId()==id){
            return url;
        }
        userRepo.delete(deleteUser);
        return url;
    }



    // Quản lý nội thất
    @GetMapping("/furniture-management")
    public String listPhong(Model model){
        List<Toanha> listToaNha = toanhaRepo.findAll();
        model.addAttribute("listToaNha",listToaNha);
        model.addAttribute("titles"," nội thất");
        model.addAttribute("url","furniture-management");
        model.addAttribute("currentUser",getCurrentUser());
        return "admin/list-phong";
    }

    @GetMapping("/furniture-management/{idToaNha}/{idPhong}")
    public String listFurPhong(@PathVariable("idPhong") String idPhong,@PathVariable("idToaNha") String idToaNha, Model model){
        List<PhongNoithat> listFur = phongNoiThatRepo.findByIdPhong(idPhong);
        model.addAttribute("listFur",listFur);
        model.addAttribute("matoa",idToaNha);
        model.addAttribute("maphong",idPhong);
        model.addAttribute("currentUser",getCurrentUser());
        return "admin/furniture-management";
    }

    @GetMapping("/furniture-management/{idtoa}/{idphong}/{id}/edit")
    public String editFur(@PathVariable("id") Integer idPhongNoiThat, Model model){
        PhongNoithat editFur = phongNoiThatRepo.findById(idPhongNoiThat).orElse(null);
        if(editFur == null){
            return "redirect:/furniture-management";
        }
        else {
            model.addAttribute("matoa",editFur.getIdPhong().getIdToanha().getId());
            model.addAttribute("maphong",editFur.getIdPhong().getId());
            model.addAttribute("editFur",editFur);
            model.addAttribute("currentUser",getCurrentUser());
            return "admin/edit-furniture";
        }
    }

    @PostMapping("/furniture-management/edit")
    public String updateFurniture(PhongNoithat phongNoithat,RedirectAttributes redirect){
        PhongNoithat phongNT_Phong = phongNoiThatRepo.findById(phongNoithat.getId()).orElse(null);
        if (phongNoithat.getSoluong()==null)
        {
            redirect.addFlashAttribute("error","Vui lùng nhập số lượng!");
            return "redirect:/admin/furniture-management/"+phongNT_Phong.getIdPhong().getIdToanha().getId()+"/"+phongNT_Phong.getIdPhong().getId()+"/"+phongNoithat.getId()+"/edit";
        }

        phongNoiThatService.updatePhongNoithat(phongNoithat.getId(),phongNoithat);
        return "redirect:/admin/furniture-management/"+phongNT_Phong.getIdPhong().getIdToanha().getId()+"/"+phongNT_Phong.getIdPhong().getId();
    }

    @GetMapping("/furniture-management/{id}/remove")
    public String removeFurniture(@PathVariable("id") Integer idPhongNoiThat){
        PhongNoithat phongNT_Phong = phongNoiThatRepo.findById(idPhongNoiThat).orElse(null);
        phongNoiThatService.removeFurniture(idPhongNoiThat);
        return "redirect:/admin/furniture-management/"+phongNT_Phong.getIdPhong().getIdToanha().getId()+"/"+phongNT_Phong.getIdPhong().getId();
    }

    @GetMapping("/furniture-management/{idtoa}/{idphong}/new")
    public String formAddNoiThat(@PathVariable("idphong") String idPhong,Model model){
        Phong phongNT = phongRepo.findById(idPhong).orElse(null);
        PhongNoithat newphongNT = new PhongNoithat();
        newphongNT.setIdPhong(phongNT);
        model.addAttribute("newPhongNT",newphongNT);
        model.addAttribute("listNT",noiThatRepo.findAll());
        return "admin/addFurniture";
    }
    @PostMapping("/furniture-management/new")
    public String addNoiThat(PhongNoithat phongNoithat, RedirectAttributes redirect){
        String urlNew = "redirect:/admin/furniture-management/"+phongNoithat.getIdPhong().getIdToanha().getId()+"/"+phongNoithat.getIdPhong().getId()+"/new";
        PhongNoithat exist = phongNoiThatRepo.findByIdNoithatAndIdPhong(phongNoithat.getIdNoithat(),phongNoithat.getIdPhong());
        if(exist!=null)
        {
            redirect.addFlashAttribute("error","Đã có nội thất \"" + phongNoithat.getIdNoithat().getTen() + "\" trong phòng này!");
            return urlNew;
        }else if(phongNoithat.getSoluong()==null){
            redirect.addFlashAttribute("error","Vui lòng nhập số lượng!");
            return urlNew;
        }
        else
        {
            redirect.addFlashAttribute("success","Thêm nội thất \"" + phongNoithat.getIdNoithat().getTen() + "\" thành công!" ) ;
            phongNoiThatRepo.save(phongNoithat);
        }
        return urlNew;
    }





    //================================
    // Quan ly hoa don
    private Double tienDien;
    private Double tienNuoc;

    @GetMapping("/invoice-management")
    public String listPhong_HD(Model model){
        List<Toanha> listToaNha = toanhaRepo.findAll();
        model.addAttribute("listToaNha",listToaNha);
        model.addAttribute("titles","hoá đơn");
        model.addAttribute("url","invoice-management");
        model.addAttribute("currentUser",getCurrentUser());
        return "admin/list-phong";
    }
    @GetMapping("/invoice-management/{idToaNha}/{idPhong}")
    public String listHD(@PathVariable("idPhong") String idPhong,@PathVariable("idToaNha") String idToaNha, Model model){
        List<Hoadon> listHD = hoaDonRepo.findByIdPhong(idPhong);
        model.addAttribute("listHD",listHD);
        model.addAttribute("matoa",idToaNha);
        model.addAttribute("maphong",idPhong);
        model.addAttribute("currentUser",getCurrentUser());
        return "admin/invoice-management";
    }

    @GetMapping("/invoice-management/{idtoa}/{idphong}/{id}/edit")
    public String editHD(@PathVariable("id") Integer id, Model model){
        Hoadon editHD = hoaDonRepo.findById(id).orElse(null);
        if(editHD == null){
            return "redirect:/admin/invoice-management";
        }
        else {
            for (var cthd :editHD.getChitiethoadons())
            {
                if(cthd.getIdDichvu().getTendichvu().equals("Điện") ){
                    model.addAttribute("chisodien",cthd.getChisotieuthu());
                }
                else {
                    model.addAttribute("chisonuoc",cthd.getChisotieuthu());

                }
            }
            model.addAttribute("matoa",editHD.getIdPhong().getIdToanha().getId());
            model.addAttribute("maphong",editHD.getIdPhong().getId());
            model.addAttribute("editHD",editHD);
            model.addAttribute("currentUser",getCurrentUser());
            return "admin/edit-invoice";
        }
    }

    @PostMapping("/invoice-management/edit")
    public String updateInvoice(Hoadon hoadon, RedirectAttributes redirect){

        Hoadon hoaDon_Phong = hoaDonRepo.findById(hoadon.getId()).orElse(null);

        invoiceService.updateInvoice(hoadon.getId(),hoadon);
        return  "redirect:/admin/invoice-management/"+hoaDon_Phong.getIdPhong().getIdToanha().getId()+"/"+hoaDon_Phong.getIdPhong().getId();
    }

    @GetMapping("/invoice-management/{id}/remove")
    public String removeInvoice(@PathVariable("id") Integer id){
        Hoadon hoaDon_Phong = hoaDonRepo.findById(id).orElse(null);

        invoiceService.removeInvoice(id);
        return "redirect:/admin/invoice-management/"+hoaDon_Phong.getIdPhong().getIdToanha().getId()+"/"+hoaDon_Phong.getIdPhong().getId();
    }


    @GetMapping("/invoice-management/{idtoa}/{idphong}/new")
    public String formAddHd(@PathVariable("idphong") String idPhong,Model model){
        Phong phongHD = phongRepo.findById(idPhong).orElse(null);
        Hoadon newHD= new Hoadon();
        newHD.setIdPhong(phongHD);
        model.addAttribute("hoaDon",newHD);
        return "admin/addInvoice";
    }




    public void addCTHD(Hoadon item, Integer chiso, Double money, Dichvudiennuoc dv){
        Chitiethoadon newCTHD = new Chitiethoadon();
        newCTHD.setIdHoadon(item);
        newCTHD.setChisotieuthu(chiso);
        newCTHD.setThanhtien(money);
        newCTHD.setIdDichvu(dv);
        cthdRepo.save(newCTHD);
    }



    @PostMapping("/invoice-management/new")
    public String addHD(Hoadon hoadon, RedirectAttributes redirect){

        String url = "redirect:/admin/invoice-management/"+hoadon.getIdPhong().getIdToanha().getId()+"/"+hoadon.getIdPhong().getId();
        if (hoadon.getChisodien()==null){
            redirect.addFlashAttribute("error","Vui lòng nhập chỉ số điện!");
            return url+"/new";
        }else if (hoadon.getChisonuoc()==null){
            redirect.addFlashAttribute("error","Vui lòng nhập chỉ số nước!");
            return url+"/new";
        }

        Dichvudiennuoc dichVuDien = dichVuRepo.findByTendichvu("Điện");
        Dichvudiennuoc dichVuNuoc = dichVuRepo.findByTendichvu("Nước");

        tienDien = hoadon.getChisodien()*dichVuDien.getDongia();
        tienNuoc = hoadon.getChisonuoc()*dichVuNuoc.getDongia();

        hoadon.setNgayxuatHD(Instant.now());
        hoadon.setTrangthaithanhtoan(false);
        hoadon.setTongtien(tienDien+tienNuoc);

        hoaDonRepo.save(hoadon);
        addCTHD(hoadon,hoadon.getChisodien(),tienDien,dichVuDien);
        addCTHD(hoadon,hoadon.getChisonuoc(),tienNuoc,dichVuNuoc);


        return "redirect:/admin/invoice-management/"+hoadon.getIdPhong().getIdToanha().getId()+"/"+hoadon.getIdPhong().getId();
    }


    //================================

    //Quản lý hồ sơ
    //================================

    @GetMapping("/hosodangky-management")
    public String listHosodangky(Model model){
        List<Hosodangky> listHosodangky =  hosoDangKyRepo.findAll();
        model.addAttribute("listHosodangky",listHosodangky);
        model.addAttribute("currentUser",getCurrentUser());

        return "admin/hosodangky-management";
    }

    @GetMapping("/hosodangky-management/{id}/edit")
    public String editHSdangky(@PathVariable("id") Integer idHoSoDangKy, Model model){
        Hosodangky editHSdangky = hosoDangKyRepo.findById(idHoSoDangKy).orElse(null);
        if(editHSdangky == null){
            return "redirect:/hosodangky-management";
        }
        else {
            model.addAttribute("listPhong",phongRepo.findAll());
            model.addAttribute("editHSdangky",editHSdangky);
            model.addAttribute("currentUser",getCurrentUser());
            return "admin/edit-hosodangky";
        }
    }

    @PostMapping("/hosodangky-management/edit")
    public String updateHSdangky(Hosodangky hosodangky,RedirectAttributes redirect ){
        User setPhongUser = userRepo.findById(hosodangky.getIdUser().getId()).orElse(null);
        if(hosodangky.getNgaytraphong()==null||hosodangky.getNgaynhanphong()==null){
            redirect.addFlashAttribute("error","Vui lòng chọn ngày nhận/trả phòng!");
            return "redirect:/admin/hosodangky-management/" +hosodangky.getId()+ "/edit";
        }
        hosoDangKyService.updateHosodangky(hosodangky.getId(),hosodangky);
        setPhongUser.setIdPhong(hosodangky.getPhong());
        userRepo.save(setPhongUser);
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
        model.addAttribute("currentUser",getCurrentUser());
        return "admin/hosochuyenphong-management";
    }

    @GetMapping("/hosochuyenphong-management/{id}/edit")
    public String editHSchuyenphong(@PathVariable("id") Integer idHoSoChuyenPhong, Model model){
        Hosochuyenphong editHSchuyenphong = hosoChuyenPhongRepo.findById(idHoSoChuyenPhong).orElse(null);
        if(editHSchuyenphong == null){
            return "redirect:/hosochuyenphong-management";
        }
        else {
            model.addAttribute("editHSchuyenphong",editHSchuyenphong);
            model.addAttribute("currentUser",getCurrentUser());
            model.addAttribute("listPhong",phongRepo.findAll());
            return "admin/edit-hosochuyenphong";
        }
    }

    @PostMapping("/hosochuyenphong-management/edit")
    public String updateHSchuyenphong(Hosochuyenphong hosochuyenphong){
        User setPhong = userRepo.findById(hosochuyenphong.getIdUser().getId()).orElse(null);
        setPhong.setIdPhong(hosochuyenphong.getPhong());

        hosoChuyenPhongService.updateHosochuyenphong(hosochuyenphong.getId(),hosochuyenphong);
        return "redirect:/admin/hosochuyenphong-management";
    }

    @GetMapping("/hosochuyenphong-management/{id}/remove")
    public String removeHosochuyenphong(@PathVariable("id") Integer idHosochuyenphong){
        hosoChuyenPhongService.removeHosochuyenphong(idHosochuyenphong);
        return "redirect:/admin/hosochuyenphong-management";
    }

}
