package com.lucas.LAA.domain.repository;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.lucas.LAA.domain.entity.Log;

public class LogRepositoryImpl implements IRedisRepository {

	public static String ACCESSED_URL_KEY = "ACCESED-URL-";
	public static String ACCESSED_URL_REGION_KEY = "ACCESED-URL-REGION-";
	public static String ACCESSED_URL_TIMESTAMP_KEY = "ACCESED-URL-TIMESTAMP-";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void save(Log object, String key) {
		this.redisTemplate.opsForZSet().incrementScore(key, object.getUrl(), 1);
	}

	@Override
	public Object find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> findAll(String key) {
		return this.redisTemplate.opsForHash().entries(key);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	public Set<Object> findTopAccessedURL(int quantity, String key) {
		return this.redisTemplate.opsForZSet().reverseRange(key, 0, quantity - 1);
	}
	
	public Set<Object> findLessAccessedURL(int quantity, String key) {
		return this.redisTemplate.opsForZSet().range(key, 0, quantity - 1);
	}
}
