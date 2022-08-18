package com.techtree.manytoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtree.manytoone.model.Items;

@Repository
public interface repo extends JpaRepository<Items, Long> {

//	what ?
//			which one is parent Customer or itemclass one sec
//			
//			item is parent na bcoz till has to many 
//			
//			many to one
//			many item  to  onecustome
//			parent          child          
}
