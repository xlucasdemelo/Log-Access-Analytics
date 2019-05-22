package com.lucas.LAA.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Log")
@ToString
public class Log implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1019205957678683154L;

	@Id
	protected Long id;
	
	@NotNull
	private String url;
	
	@NotNull
	private String timestamp;
	
	@NotNull
	private String uuid;
	
}
