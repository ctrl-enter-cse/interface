package com.techtree.messager.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtree.messager.entity.User;

@Repository
public interface MessagerRepo extends JpaRepository<User, Long> {

}
