package com.lucas.LAA.domain.entity;

import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class LogMetric {
	
	private Set<Object> topThreeAccessedWorld;
	private Map<String, Set<Object>> topThreeAccessedPerRegion;
	private Set<Object> lessAccessedInWorld;
	
}
