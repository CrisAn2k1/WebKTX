package com.WebKTX.service;

import com.WebKTX.model.Hoadon;
import com.WebKTX.model.User;
import com.WebKTX.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    HoaDonRepository hoaDonRepo;

    @Override
    public void updateInvoice(Integer id, Hoadon hoadon) {
        Hoadon invoiceEdit = hoaDonRepo.findById(id).orElse(null);
        invoiceEdit.setTrangthaithanhtoan(hoadon.getTrangthaithanhtoan());
        hoaDonRepo.save(invoiceEdit);
    }

    @Override
    public void removeInvoice(Integer id) {
        Hoadon invoiceRemove = hoaDonRepo.findById(id).orElse(null);
        hoaDonRepo.delete(invoiceRemove);
    }
}
