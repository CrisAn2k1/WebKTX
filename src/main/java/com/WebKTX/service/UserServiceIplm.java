package com.WebKTX.service;

import com.WebKTX.model.User;
import com.WebKTX.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class UserServiceIplm implements UserService {
    @Autowired
    UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void updateInfo(Integer id, User user) {
        User userEdit = userRepository.findById(id).orElse(null);
        userEdit.setEmail(user.getEmail());
        userEdit.setHoten(user.getHoten());
        userEdit.setNgaysinh(user.getNgaysinh());
        userEdit.setGioitinh(user.getGioitinh());
        userEdit.setPhone(user.getPhone());
        userEdit.setDiachicutru(user.getDiachicutru());
        userEdit.setTruonghoc(user.getTruonghoc());
        userEdit.setNienkhoa(user.getNienkhoa());
        userEdit.setTinh(user.getTinh());
        userEdit.setHuyen(user.getHuyen());
        userEdit.setXa(user.getXa());
        userRepository.save(userEdit);
    }

    @Override
    public void removeUser(Integer id) {
        User userRemove = userRepository.findById(id).orElse(null);
        userRepository.delete(userRemove);
    }

    @Override
    public void register(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        userRepository.save(user);

        sendVerificationEmail(user, siteURL);
    }

    @Override
    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "nhbtoan1503@gmail.com";
        String senderName = "Nguyễn Huy Bảo Toàn";
        String subject = "Xác nhận đăng kí tài khoản";
        String content = "Dear [[name]],<br>"
                + "Nhấp vào link bên dưới để kích hoạt tài khoản của bạn:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "<br>"
                + "Nguyễn Huy Bảo Toàn";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }

    }


}
