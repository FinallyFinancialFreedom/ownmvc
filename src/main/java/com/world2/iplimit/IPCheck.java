package com.world2.iplimit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.home.mvc.util.ConfigUtil;

/**
 * 检查地址段
 * @author DevUser
 *
 */
public class IPCheck {
	private static long[] mask_array = {   0x0, 0x80000000, 0xc0000000,
			0xe0000000, 0xf0000000, 0xf8000000, 0xfc000000, 0xfe000000,
			0xff000000, 0xff800000, 0xffc00000, 0xffe00000, 0xfff00000,
			0xfff80000, 0xfffc0000, 0xfffe0000, 0xffff0000, 0xffff8000,
			0xffffc000, 0xffffe000, 0xfffff000, 0xfffff800, 0xfffffc00,
			0xfffffe00, 0xffffff00, 0xffffff80, 0xffffffc0, 0xffffffe0,
			0xfffffff0, 0xfffffff8, 0xfffffffc, 0xfffffffe, 0xffffffff };
	private static List<Long[]> ipRangeList = new ArrayList<Long[]>();
	
	public static Timer timer = null;
	 
	static {
        timer = new Timer();
        timer.schedule(new LoadTask(), 0, 1*1800* 1000);
	}

	static class LoadTask extends java.util.TimerTask {
        public void run() {
        	ipRangeList = new ArrayList<Long[]>();
        	init();
        }
    }
	
	private static void init() {
		BufferedReader in = null;
		try {
//			in = new BufferedReader(new FileReader("c:/robot_ip_phase.conf"));
			in = new BufferedReader(new FileReader(ConfigUtil.defaultTomcatCommonConfigPath()+"/denyIP.conf"));
			String s = null;
			String[] ip_range2Array = new String[2];
			while ((s = in.readLine()) != null) {
				ip_range2Array = s.split("/");
				Long[] a= new Long[2];
				a[0] = convert2Long(ip_range2Array[0]);
				a[1] = Long.valueOf(ip_range2Array[1]);
				ipRangeList.add(a);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean isDenyIp(String ip) {
		for (Long[] ip_range : ipRangeList) {
			if (isSameNetWork(ip, ip_range[0],ip_range[1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断ip和ip_range是否属于同一个网段
	 * 
	 *            点分式表示。例210.0.217.179
	 * @param ip_range
	 *            新网段表示法。例210.0.128.0/17
	 * @return 是否同网段
	 */
	public static boolean isSameNetWork(String ipStr, long ip_range,long mask) {
		long ip = convert2Long(ipStr);
		if (mask < 0 || mask > 32) {
			return false;
		}
		if (mask == 32) {
			return ip==ip_range;
		}
		return (ip & mask_array[(int)mask]) == ip_range;
	}

	public static long convert2Long(String ip) {
		long nIP = 0;
		String[] seg = ip.split("\\.");
		if (seg.length != 4)
			return -1;
		nIP = (Long.valueOf(seg[0].trim()).longValue() << 24)
				| (Long.valueOf(seg[1].trim()).longValue() << 16)
				| (Long.valueOf(seg[2].trim()).longValue() << 8)
				| (Long.valueOf(seg[3].trim()).longValue() << 0);
		return nIP;
	}
	
	public static void main(String[] args) {
		System.out.println(isDenyIp("117.63.161.175"));
	}
}