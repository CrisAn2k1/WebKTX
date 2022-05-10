package com.WebKTX.repository;

import com.WebKTX.model.Hoadon;
import com.WebKTX.model.PhongNoithat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoaDonRepository extends JpaRepository<Hoadon,Integer> {
}
