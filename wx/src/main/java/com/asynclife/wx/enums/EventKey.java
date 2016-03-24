package com.asynclife.wx.enums;

public enum EventKey {
	
	M2001_COOPERATION("加入我们/合作咨询"),
	M3001_CUSTOMER_SERVICE("关于我们/联系客服");
	
	private String keyName;

	private EventKey(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyName() {
		return keyName;
	}
	
}
