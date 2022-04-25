package com.WebKTX.repository;


import com.WebKTX.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")

    User findUserName(String username);

    User findByEmail(String email);
}



