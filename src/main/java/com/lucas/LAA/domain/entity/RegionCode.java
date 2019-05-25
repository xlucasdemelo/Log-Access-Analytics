package com.lucas.LAA.domain.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum RegionCode {
	// us-east-1(1), us-west-2(2) and ap-south-1(3).
	US_EAST_1(1),
	US_WEST_2(2),
	AP_SOUTH_1(3);
	
	private int value;
	private static Map map = new HashMap<>();
	
	RegionCode(int value){
		this.value = value;
	}
	
	static {
        for (RegionCode regionCode : RegionCode.values()) {
            map.put(regionCode.value, regionCode);
        }
    }
	
	@JsonValue
	public int getvalue() {
		return this.value;
	}
	
	public static RegionCode valueOf(int pageType) {
        return (RegionCode) map.get(pageType);
    }
	
	
}
