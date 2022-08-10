package com.techtree.messager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtree.messager.entity.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

}
