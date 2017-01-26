package com.home.mvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBase {

	public static final String CHECK_PASSWORD = "[a-zA-Z0-9]{6,16}";
	public static final String CHECK_EMAIL = "[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+";
	public static final String CHECK_MOBILE = "1[0-9]{10}";
	public static final String CHECK_CHINESE = "[\u4e00-\u9fa5]{2,5}";
	public static final String CHECK_DIGIT = "[0-9]*";
	public static final String CHECK_PASSPORT = "[a-z]{1}[a-z0-9]{5,15}";
	protected static HashMap<Integer,Pattern> PATTERNMAP = new HashMap<Integer,Pattern>(); 
	private static RegexBase regex = null;
	private RegexBase() {
		Pattern pt = null;
		pt = Pattern.compile(CHECK_PASSWORD);
		PATTERNMAP.put(CHECK_PASSWORD.hashCode(), pt);
		pt = Pattern.compile(CHECK_EMAIL);
		PATTERNMAP.put(CHECK_EMAIL.hashCode(), pt);
		pt = Pattern.compile(CHECK_MOBILE);
		PATTERNMAP.put(CHECK_MOBILE.hashCode(), pt);
		pt = Pattern.compile(CHECK_CHINESE);
		PATTERNMAP.put(CHECK_CHINESE.hashCode(), pt);
		pt = Pattern.compile(CHECK_DIGIT);
		PATTERNMAP.put(CHECK_DIGIT.hashCode(), pt);
		pt = Pattern.compile(CHECK_PASSPORT);
		PATTERNMAP.put(CHECK_PASSPORT.hashCode(), pt);
	}
	
	public static RegexBase getInstance(){
		if(regex!=null){
			return regex;
		}else{
			return new RegexBase();
		}
	}
	/**
	 * get Pattern by given pattern 
	 * @param pt: patter name
	 * @return the initialization patter
	 */
	protected static Pattern getPattern(String pattern)
	{
		Integer hashcode = pattern.hashCode();
		Pattern pt = PATTERNMAP.get(hashcode);
		if(null == pt)
		{
			pt = Pattern.compile(pattern);
			PATTERNMAP.put(hashcode, pt);
		}
		return pt;
	}
	
	private static String[] forbiddenEmail = {"webmaster@","postmaster@","support@","service@","administrator@","root@","admin@","auto_reply@","abuse@","anti-spam@","antispam@","@cbl.abuseat.org","@anti-spam.cn"};
	/**
	 * check whether a string is email 
	 * @param s
	 * @return
	 */
	public static boolean checkEmail(String s)
	{
		if(null == s || s.length() == 0||s.length()>60)
		{
			return false;
		}
		for(String d:forbiddenEmail){
			if(s.indexOf(d)==0){
				return false;
			}
		}
		Pattern p = getPattern(CHECK_EMAIL);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	public static boolean checkMobile(String s){
		if(s==null) return false;
		Pattern p = getPattern(CHECK_MOBILE);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean checkPwd(String s){
		if(s==null) return false;
		Pattern p = getPattern(CHECK_PASSWORD);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	public static boolean checkChineseName(String s){
		if(s==null) return false;
		Pattern p = getPattern(CHECK_CHINESE);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean checkQQ(String s){
		if(s==null||s.length()<5) return false;
		Pattern p = getPattern(CHECK_DIGIT);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean checkPOSTCODE(String s){
		if(s.length()!=6) return false;
		Pattern p = getPattern(CHECK_DIGIT);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean checkPassport(String s){
		Pattern p = getPattern(CHECK_PASSPORT);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean checkIdentity(String s)
	{
		boolean bl = false;
		String year = "";
		String month = "";
		String day = "";
		if(s == null)
			return false;
		if(s.length() == 15)
			s = per15To18(s);
		else
		if(s.length() == 18)
		{
			if(!check18Identity(s))
				return false;
		} else
		{
			return false;
		}
		s = s.substring(0, 16);
		year = s.substring(6, 10);
		month = s.substring(10, 12);
		day = s.substring(12, 14);
		return checkDigit(s) && checkDate(year, month, day);
	}
	public static boolean checkDigit(String s) {
		if(s==null) return false;
		Pattern p = getPattern(CHECK_DIGIT);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean check18Identity(String perIDSrc)
	{

		boolean bl = false;
		int iS = 0;
		int iW[] = {
			7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 
			7, 9, 10, 5, 8, 4, 2
		};
		String LastCode = "10X98765432";
		for(int i = 0; i < 17; i++)
			iS += Integer.parseInt(perIDSrc.substring(i, i + 1)) * iW[i];

		int iY = iS % 11;
		String checkup = LastCode.substring(iY, iY + 1);

		if(checkup.equalsIgnoreCase(perIDSrc.substring(perIDSrc.length() - 1, perIDSrc.length())))
			bl = true;

		return bl;
	}
	
	public static String per15To18(String perIDSrc)
	{
		int iS = 0;
		int iW[] = {
			7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 
			7, 9, 10, 5, 8, 4, 2
		};
		String LastCode = "10X98765432";
		String perIDNew = perIDSrc.substring(0, 6);
		perIDNew = (new StringBuilder()).append(perIDNew).append("19").toString();
		perIDNew = (new StringBuilder()).append(perIDNew).append(perIDSrc.substring(6, perIDSrc.length())).toString();
		for(int i = 0; i < 17; i++)
			iS += Integer.parseInt(perIDNew.substring(i, i + 1)) * iW[i];

		int iY = iS % 11;
		perIDNew = (new StringBuilder()).append(perIDNew).append(LastCode.substring(iY, iY + 1)).toString();
		return perIDNew;
	}
	
	/**
	 * check whether the date is legal
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean checkDate(String year, String month, String day)
	{
		if(year == null || month == null || day == null)
			return false;
		if(year.length() == 0 || month.length()==0 || day.length()==0)
			return false;
		String date = year + "-" + month + "-" + day;
		return checkDate(date);
	}
	/**
	 * check the date according to pattern
	 * @param date:yyyy-mm-dd
	 * @return
	 */
	public static boolean checkDate(String date)
	{
		if(null == date || date.length() == 0)
			return false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);	
		try {
			Date tdate = sdf.parse(date);
			Calendar cal = new GregorianCalendar();
			int nowyear = cal.get(1); // now year
			
			cal.setTime(tdate);
			int year = cal.get(1); //input year
			if(nowyear<year)
				return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
