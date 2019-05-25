package com.lucas.LAA.domain.repository;

import java.util.Map;

import com.lucas.LAA.domain.entity.Log;

public interface IRedisRepository {
	
	public void save(Log object, String key);

	public Object find(String id);

	public Map<Object, Object> findAll(String key);

	public void delete(String id);

	
}
