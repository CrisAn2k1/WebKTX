package com.WebKTX.service;

import com.WebKTX.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface DangKyKtxService {
    void registerKTX(Integer id, User user) throws IOException;
}
