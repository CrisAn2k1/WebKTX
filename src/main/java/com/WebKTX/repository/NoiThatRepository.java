package com.WebKTX.repository;

import com.WebKTX.model.Danhmucnoithat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoiThatRepository extends JpaRepository<Danhmucnoithat,Integer> {
}
