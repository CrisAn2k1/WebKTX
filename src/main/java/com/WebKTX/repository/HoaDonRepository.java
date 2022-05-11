package com.WebKTX.repository;

import com.WebKTX.model.Hoadon;
import com.WebKTX.model.PhongNoithat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HoaDonRepository extends JpaRepository<Hoadon,Integer> {
    @Query("SELECT p FROM Hoadon p WHERE p.idPhong.id=?1")
    List<Hoadon> findByIdPhong(String idPhong);

}
