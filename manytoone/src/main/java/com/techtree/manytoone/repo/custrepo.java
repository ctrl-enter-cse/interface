package com.techtree.manytoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtree.manytoone.model.Customer;
@Repository
public interface custrepo extends JpaRepository<Customer, Long> {

}
