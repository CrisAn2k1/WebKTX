package com.WebKTX.repository;

import com.WebKTX.model.Dichvudiennuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuRepository extends JpaRepository<Dichvudiennuoc,Integer> {
    Dichvudiennuoc findByTendichvu(String tendichvu);
}
