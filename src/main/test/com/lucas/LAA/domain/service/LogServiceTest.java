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
import com.lucas.LAA.domain.entity.LogMetric;
import com.lucas.LAA.domain.entity.RegionCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, RedisConfiguration.class})
@WebAppConfiguration
public class LogServiceTest {
	
	@Autowired
	private LogService logServices;
	
	private void generateData() {
		Log log = new Log(null, "tiggers/bid/nowwow", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.US_EAST_1 );
		Log log1 = new Log(null, "tiggers/bid/nowwow", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		Log log2 = new Log(null, "tiggers/bid/other", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		Log log3 = new Log(null, "tiggers/bid/other", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		Log log4 = new Log(null, "tiggers/bid/nowwow", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		Log log5 = new Log(null, "tiggers/bid/another", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.US_EAST_1 );
		Log log6 = new Log(null, "tiggers/bid/another", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		Log log7 = new Log(null, "tiggers/bid/last", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.US_WEST_2 );
		
		this.logServices.insertLog(log);
		this.logServices.insertLog(log1);
		this.logServices.insertLog(log2);
		this.logServices.insertLog(log3);
		this.logServices.insertLog(log4);
		this.logServices.insertLog(log5);
		this.logServices.insertLog(log6);
		this.logServices.insertLog(log7);
	}
	
	@Test
	public void insertLogSuccess(){
		/// tiggers/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 3
		Log log = new Log(null, "tiggers/bid/nowwow", "1037825323957", "5b019db5-b3d0-46d2-9963-437860af707e", RegionCode.AP_SOUTH_1 );
		this.logServices.insertLog(log);
		
		Assert.notNull(log, "Should not be null");
	}
	
	@Test
	public void testGeneratAllAccessedURLs(){
		this.generateData();
		LogMetric logMetric = this.logServices.getMetrics();
		
		Assert.notNull(logMetric, "Should not be null");
		System.out.println(logMetric);
	}
	
	@Test
	public void testGenerateTopThreePerRegion(){
		this.generateData();
		LogMetric logMetric = this.logServices.getMetrics();
		
		Assert.notNull(logMetric, "Should not be null");
		Assert.notNull(logMetric.getTopThreeAccessedPerRegion(), "SHould not be null");
	}
}
