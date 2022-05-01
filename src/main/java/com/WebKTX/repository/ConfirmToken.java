package com.WebKTX.repository;


import com.WebKTX.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmToken extends JpaRepository<ConfirmationToken, Long> {

}



