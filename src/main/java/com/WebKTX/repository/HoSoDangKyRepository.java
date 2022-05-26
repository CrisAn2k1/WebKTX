package com.WebKTX.repository;

import com.WebKTX.model.Hosodangky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoSoDangKyRepository extends JpaRepository<Hosodangky, Integer> {
    @Query("SELECT h FROM Hosodangky h WHERE h.id=?1")
    List<Hosodangky> findByIdHosodangky(String id);

    List<Hosodangky> findByTrangthai(Boolean trangthai);
}
