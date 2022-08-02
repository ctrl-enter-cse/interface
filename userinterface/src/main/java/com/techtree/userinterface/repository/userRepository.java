package com.techtree.userinterface.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtree.userinterface.entity.User;
@Repository
public interface userRepository extends JpaRepository<User, Long> {
	
	
	
}
