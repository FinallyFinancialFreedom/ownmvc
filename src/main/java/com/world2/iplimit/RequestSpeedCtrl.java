package com.world2.iplimit;



public class RequestSpeedCtrl {
	
	SpeedLimit<String> checkuserExist;//control ip 
	SpeedLimit<String> sendsmsip;//control ip 
	SpeedLimit<String> sendsmsdestiny;//control sms destiny 
	SpeedLimit<String> sendemaildestiny;//control sms destiny 
	SpeedLimit<String> sendemailip;//control sms destiny 
	
	public SpeedLimit<String> getCheckuserExist() {
		return checkuserExist;
	}

	public SpeedLimit<String> getSendsmsip() {
		return sendsmsip;
	}

	public SpeedLimit<String> getSendsmsdestiny() {
		return sendsmsdestiny;
	}

	public SpeedLimit<String> getSendemaildestiny() {
		return sendemaildestiny;
	}

	public SpeedLimit<String> getSendemailip() {
		return sendemailip;
	}

	public SpeedLimit<String> getUserExistLimit() {
		return checkuserExist;
	}

	private RequestSpeedCtrl () {
		checkuserExist = new SpeedLimit<String>();
		IpLimitProperty.getInstance().readConfig(checkuserExist, "checkuserExist");
		sendsmsip = new SpeedLimit<String>();
		IpLimitProperty.getInstance().readConfig(sendsmsip, "sendsmsip");
		sendsmsdestiny = new SpeedLimit<String>();
		IpLimitProperty.getInstance().readConfig(sendsmsdestiny, "sendsmsdestiny");
		sendemaildestiny = new SpeedLimit<String>();
		IpLimitProperty.getInstance().readConfig(sendemaildestiny, "sendemaildestiny");
		sendemailip = new SpeedLimit<String>();
		IpLimitProperty.getInstance().readConfig(sendemailip, "sendemailip");
	}
	
	private static RequestSpeedCtrl instance = null; 
	
	public static synchronized RequestSpeedCtrl getInstance(boolean ...isRefresh) {
		if (instance == null||isRefresh.length==1) {
			instance = new RequestSpeedCtrl();
		}
		return instance;
	}
}	