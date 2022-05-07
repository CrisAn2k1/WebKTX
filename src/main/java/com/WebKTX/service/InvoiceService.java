package com.WebKTX.service;

import com.WebKTX.model.Hoadon;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    void updateInvoice(Integer id, Hoadon hoadon);

    void removeInvoice(Integer id);
}
