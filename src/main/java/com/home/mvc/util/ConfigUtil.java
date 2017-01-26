package com.home.mvc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
/**
 * һ���Զ��ҵ�config�ļ�Ŀ¼�Ĺ�����
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class ConfigUtil {

    private ConfigUtil() {

    }
    /**
     * ·���ָ���
     */
    public static final String fileSeparator = System.getProperty(
            "file.separator");

    /**
     * ���ݱ������ڵ�λ�ã����Config�ļ���Ŀ¼��·������Ϊ��ȱʡ����/WEB-INF/classes��/WEB-INF/lib��
     * ��config�ļ���ȱʡ�Ƿ���/WEB-INF/��
     * @return Config�ļ���·�������û���ҵ���return null;
     */
    
    public static String defaultConfigPath() {
    	return defaultConfigPath(ConfigUtil.class);
    }
    

    /**
     * ���ȱʡ�İ�װĿ¼
     * @return ȱʡ�İ�װĿ¼
     */
    
    public static String defaultHomePath() {
    	String configPath = defaultConfigPath();
        if (configPath == null) {
            return null;
        }
        int n = configPath.lastIndexOf(fileSeparator);
        if (n > 0) {
            return configPath.substring(0, n);
        }
        return configPath;
    }
    

    /**
     * ���ָ���ļ����ľ���·��������ָ���ļ�������defaultConfPath() + �ָ��� + confFileName
     * @param confFileName ָ��Config�ļ����ļ���
     * @return �����ļ��ľ���·��,���û���ҵ������ļ�Ŀ¼��return null;
     * @see defaultConfigPath()
     */
    
    public static String getDefaultConfFile(String confFileName) {
        String path = defaultConfigPath();
        if (path == null) {
            return null;
        }
        String s = path + fileSeparator + confFileName;
        return s;

    }

    /**
     * ����ָ�������ڵ�λ�ã����Config�ļ���Ŀ¼·������Ϊ��ȱʡ����/WEB-INF/classes��/WEB-INF/lib��
     * ��config�ļ���ȱʡ�Ƿ���/WEB-INF/��
     * @param c ָ����
     * @return ����ָ�������ڵ�λ��,Config�ļ���·�������û���ҵ���return null;
     */
    
    public static String defaultConfigPath(Class c) {
        String startMark = "file:";
        String endMark = "/WEB-INF";
        String s = c.getName().replace('.', '/') + ".class";
        //����Դclass����/WEB-INF/classs��ʱ��
        //url=file:/e:/test/project/defaultroot/WEB-INF/classes/com.world2.util.ConfigUtil.class
        //����Դclass���jar������/WEB-INF/lib��ʱ��
        //url=jar:file:/e:/test/project/defaultroot/WEB-INF/lib/res.jar!/com.world2.util.ConfigUtil.class
        java.net.URL url = c.getClassLoader().getResource(s);
        String upath = url.toString();

        int n1 = upath.indexOf(startMark);
        n1 = n1 < 0 ? 0 : n1 + startMark.length();
        int n2 = upath.lastIndexOf(endMark + "/classes");
        if (n2 < 0) {
            n2 = upath.lastIndexOf(endMark + "/lib");
            if (n2 < 0) {
                return null;
            }
        }
        n2 += endMark.length();

        String path = upath.substring(n1, n2);
        if (path.startsWith("/") && path.indexOf(":") == 2) {
            path = path.substring(1);
        }

        return path.replace('/', fileSeparator.charAt(0));
    }
    
    /**
     * ��ȡtomcat/common/config·��
     * @param c Class
     * @return String
     */
    public static String defaultTomcatCommonConfigPath() {

    	String cpath = "";
    	cpath = ConfigUtil.class.getResource("/").getPath();
    	try {
    		cpath = URLDecoder.decode(cpath, "UTF-8");
    		int posi = cpath.indexOf("webapps");
    		if (posi > 0) {
    			cpath = cpath.substring(0, posi);
    			cpath = cpath + "common/arcgames_config/";
    		}
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	if(cpath.indexOf("WEB-INF")>0){
    		return "D:/apache-tomcat-6.0.20/common/passport_config/";
    	}else{
    		return cpath;
    	}
    }
    
    public static String defaultTomcatLogsPath() {

    	String cpath = "";
    	cpath = ConfigUtil.class.getResource("/").getPath();
    	try {
    		cpath = URLDecoder.decode(cpath, "UTF-8");
    		int posi = cpath.indexOf("webapps");
    		if (posi > 0) {
    			cpath = cpath.substring(0, posi);
    			cpath = cpath + "logs/";
    		}
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	if(cpath.indexOf("WEB-INF")>0){
    		return "D:/apache-tomcat-7.0.47/logs/";
    	}else{
    		return cpath;
    	}
    }
    
    public static String defaultTomcatCacheFilePath() {

    	String cpath = "";
    	cpath = ConfigUtil.class.getResource("/").getPath();
    	try {
    		cpath = URLDecoder.decode(cpath, "UTF-8");
    		int posi = cpath.indexOf("webapps");
    		if (posi > 0) {
    			cpath = cpath.substring(0, posi);
    			cpath = cpath + "cache/";
    		}
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	if(cpath.indexOf("WEB-INF")>0){
    		return "D:/apache-tomcat-6.0.20/cache/";
    	}else{
    		return cpath;
    	}

    }
    private static Properties forwardTypeProp;
    public synchronized static Properties getForwardTypeProp() {
    	if(forwardTypeProp!=null){
    		return forwardTypeProp;
    	}
    	forwardTypeProp = new Properties();
		try {
			forwardTypeProp.loadFromXML(new FileInputStream(ConfigUtil.defaultTomcatCommonConfigPath() + "forwardtype.xml"));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forwardTypeProp;
	}

/*
    public static void main(String[] args) {
        //java.net.URL url = (ConfigUtil.class).getClassLoader().getResource("com/world2/util/ConfigUtil.class");



        String path = ConfigUtil.defaultHomePath();
        String filename = path + ConfigUtil.fileSeparator + "web.xml";

        java.io.File file = new java.io.File(filename);

    }
    */
}
