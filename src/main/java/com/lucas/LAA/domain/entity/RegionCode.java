package com.lucas.LAA.domain.entity;

public enum RegionCode {
	// us-east-1(1), us-west-2(2) and ap-south-1(3).
	US_EAST_1(1),
	US_WEST_2(2),
	AP_SOUTH_1(3);
	
	private int value;
	
	RegionCode(int value){
		this.value = value;
	}
	
	public int getvalue() {
		return this.value;
	}
}
