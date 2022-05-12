package com.WebKTX.repository;

import com.WebKTX.model.Chitiethoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CTHDRepository extends JpaRepository<Chitiethoadon,Integer> {

    @Query("select c from Chitiethoadon c where c.idHoadon.id=?1")
    List<Chitiethoadon> findByIdHoadon(Integer id);
}
