package com.WebKTX.service;

import com.WebKTX.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {

    void updateInfo(Integer id, User user);

    void removeUser(Integer id);
}
