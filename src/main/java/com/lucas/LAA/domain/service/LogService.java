package com.lucas.LAA.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.LAA.domain.entity.Log;
import com.lucas.LAA.domain.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	public Log insertLog(Log log) {
		return this.logRepository.save(log);
	}
	
}
