package com.lucas.LAA.domain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.lucas.LAA.config.ApplicationConfiguration;
import com.lucas.LAA.config.RedisConfiguration;
import com.lucas.LAA.domain.entity.Log;
import com.lucas.LAA.domain.entity.RegionCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, RedisConfiguration.class})
@WebAppConfiguration
public class LogServiceTest {
	
	@Autowired
	private LogService logServices;
	
	@Test
	public void insertLogSuccess(){
		/// tiggers/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 3
		Log log = new Log(null, "tiggers/bid/now", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		log = this.logServices.insertLog(log);
		
		Assert.notNull(log.getId(), "Should not be null");
	}
	
	
}
