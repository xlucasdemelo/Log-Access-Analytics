package com.lucas.LAA.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.LAA.domain.entity.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, String> {
	
	List<Log> findAll();
	
}
