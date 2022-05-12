package com.WebKTX.repository;

import com.WebKTX.model.Toanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToaNhaRepository extends JpaRepository<Toanha,String> {
}
