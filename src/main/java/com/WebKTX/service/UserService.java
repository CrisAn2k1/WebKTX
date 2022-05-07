package com.WebKTX.service;

import com.WebKTX.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
@Service
public interface UserService {

    void updateInfo(Integer id, User user);

    void removeUser(Integer id);

    void register(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String verificationCode);

    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

}
