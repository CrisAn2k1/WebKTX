package com.WebKTX.repository;

import com.WebKTX.model.Danhmucnoithat;
import com.WebKTX.model.PhongNoithat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongNoiThatRepository extends JpaRepository<PhongNoithat, Integer> {
}
