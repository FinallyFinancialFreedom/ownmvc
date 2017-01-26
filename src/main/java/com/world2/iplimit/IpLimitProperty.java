package com.world2.iplimit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.home.mvc.util.ConfigUtil;

public class IpLimitProperty {
	
	private static Properties p = new Properties();
	private static IpLimitProperty instance = null;
	public static IpLimitProperty getInstance(boolean...isReload){
		if(instance==null||isReload.length==1){
			instance = new IpLimitProperty();
		}
		return instance;
	}
	private IpLimitProperty(){
		String propsfilename =ConfigUtil.defaultTomcatCommonConfigPath()+"iplimit.properties";
		InputStream is = null;
		try {
			is = new FileInputStream(propsfilename);
			p.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void readConfig(SpeedLimit limit,String limitName){
		try{
			String all = p.getProperty(limitName);
			if(all!=null&&all.length()>0){
				String[] pairs = all.split(",");
				for(String pair:pairs){
					String a[] = pair.split(":");
					limit.config(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
				}
			}else{
				limit.config(60, 10);
			}
		}catch (Exception e){
			limit.config(60, 5);
			e.printStackTrace();
		}
	}
	
	public boolean isControl(){
		String control = p.getProperty("isControl");
		return "1".equals(control);
	}
}
