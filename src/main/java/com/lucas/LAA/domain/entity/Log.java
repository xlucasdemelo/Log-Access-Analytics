package com.lucas.LAA.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Log  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1019205957678683154L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	private String url;
	
	private String timestamp;
	
	private String uuid;
	
	@JsonProperty("region_code")
	private Integer regionCode;
	
}

