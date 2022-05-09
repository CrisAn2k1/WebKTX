package com.WebKTX.repository;

import com.WebKTX.model.Hosochuyenphong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoSoChuyenPhongRepository extends JpaRepository<Hosochuyenphong, Integer> {
    @Query("SELECT h FROM Hosochuyenphong h WHERE h.id=?1")
    List<Hosochuyenphong> findByIdHosochuyenphong(String id);
}
