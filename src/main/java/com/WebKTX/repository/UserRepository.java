package com.WebKTX.repository;


import com.WebKTX.model.User;
import com.WebKTX.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   // @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT p FROM User p WHERE p.idPhong.id=?1")
    List<User> findByIdPhong(String idPhong);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

}



