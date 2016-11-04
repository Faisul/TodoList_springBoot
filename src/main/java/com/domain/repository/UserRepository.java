package com.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	@Override
	List<User> findAll();
	User findById(Long id);
	User findByUserName(String name);
	User findByFirstName(String name);
}
