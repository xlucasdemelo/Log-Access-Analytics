package com.lucas.LAA.domain.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.LAA.domain.entity.Log;
import com.lucas.LAA.domain.entity.LogMetric;
import com.lucas.LAA.domain.entity.RegionCode;
import com.lucas.LAA.domain.repository.LogRepositoryImpl;

@Service
public class LogService {

	@Autowired
	private LogRepositoryImpl logRepository;

	public void insertLog(Log log) {
		this.logRepository.save(log, LogRepositoryImpl.ACCESSED_URL_KEY);
		this.logRepository.save(log, LogRepositoryImpl.ACCESSED_URL_REGION_KEY + log.getRegionCode());
		this.logRepository.save(log, LogRepositoryImpl.ACCESSED_URL_TIMESTAMP_KEY + log.getTimestamp());
	}

	public Map<Object, Object> findAll(String key) {
		return this.logRepository.findAll(key);
	}

	public LogMetric getMetrics() {
		LogMetric logMetric = new LogMetric();

		// Sets the metrics for TOP 3 Accessed in whole world
		logMetric
				.setTopThreeAccessedWorld(this.logRepository.findTopAccessedURL(3, LogRepositoryImpl.ACCESSED_URL_KEY));

		//// Sets the metrics for TOP 3 Accessed per region
		logMetric.setTopThreeAccessedPerRegion(this.getTopThreeAccesedPerRegion());

		//// Sets the metrics for TOP 3 Accessed per region
		logMetric.setLessAccessedInWorld(this.logRepository.findLessAccessedURL(1, LogRepositoryImpl.ACCESSED_URL_KEY));

		return logMetric;
	}

	private Map<String, Set<Object>> getTopThreeAccesedPerRegion() {
		Map<String, Set<Object>> map = new HashMap<String, Set<Object>>();

		map.put(RegionCode.AP_SOUTH_1.toString(), this.logRepository.findTopAccessedURL(3,
				LogRepositoryImpl.ACCESSED_URL_REGION_KEY + RegionCode.AP_SOUTH_1));
		map.put(RegionCode.US_EAST_1.toString(), this.logRepository.findTopAccessedURL(3,
				LogRepositoryImpl.ACCESSED_URL_REGION_KEY + RegionCode.US_EAST_1));
		map.put(RegionCode.US_WEST_2.toString(), this.logRepository.findTopAccessedURL(3,
				LogRepositoryImpl.ACCESSED_URL_REGION_KEY + RegionCode.US_WEST_2));

		return map;
	}

}
