package com.lucas.LAA.application.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.LAA.domain.entity.Log;
import com.lucas.LAA.domain.entity.LogMetric;
import com.lucas.LAA.domain.service.LogService;

@RestController
public class LogController {
	
	private static final Logger logger = Logger.getLogger(LogController.class);
	
	@Autowired
	private LogService logService;
	
	@GetMapping(value = "/laaa/health", produces = "application/json")
	public ResponseEntity<String> ApplicationHealthCheck() {
		try {
			return new ResponseEntity<>("200", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/laar/ingest", consumes="application/json")
	public Log ingestLog(@RequestBody Log log) {
		logger.info("Ingesting Log");
		return this.logService.insertLog(log);
	}
	
	@GetMapping(value = "/laa/metrics", produces = "application/json")
	public LogMetric getMetrics(){
		logger.info("Getting metrics");
		return this.logService.getMetrics();
	}
}
