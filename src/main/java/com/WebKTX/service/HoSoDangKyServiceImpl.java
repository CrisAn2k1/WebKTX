package com.WebKTX.service;

import com.WebKTX.model.Hosodangky;
import com.WebKTX.repository.HoSoDangKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("hsdkService")
public class HoSoDangKyServiceImpl implements HoSoDangKyService {
    @Autowired
    HoSoDangKyRepository hosoDangKyRepo;
    @Override
    public void updateHosodangky(Integer id, Hosodangky hosodangky) {
        Hosodangky hosodangkyEdit = hosoDangKyRepo.findById(id).orElse(null);
        hosodangkyEdit.setTrangthai(hosodangky.getTrangthai());
        hosoDangKyRepo.save(hosodangkyEdit);

    }

    @Override
    public void removeHosodangky(Integer id) {
        Hosodangky hosodangkyRemove = hosoDangKyRepo.findById(id).orElse(null);
        hosoDangKyRepo.delete(hosodangkyRemove);
    }
}
