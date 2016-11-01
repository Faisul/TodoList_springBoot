package com.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	@Override
	List<Task> findAll();
	Task findById(Long id);
	List<Task> findByUserId(Long id);
}
