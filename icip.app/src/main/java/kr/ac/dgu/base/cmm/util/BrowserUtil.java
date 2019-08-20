
package kr.ac.dgu.base.cmm.util; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * kr.ac.dgu.base.cmm.util 
 *    |_ BrowserUtil.java
 * 1. 개요 : 브라우져 확이 유틸
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 3. 7. 오후 2:25:39
 * @version : 
 * @author : DGU
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 3. 7.		DGU				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class BrowserUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagingUtil.class);

	public BrowserUtil() {
		LOGGER.debug("BrowserUtil ==================================");
	}

	/**
	 * <pre>
	 * 1. 개요 : 브라우져 타입별 리턴 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getBrowserType
	 * @date : 2019. 3. 7.
	 * @author : DGU
	 * @param ua
	 * @return
	 */ 	
	public static String getBrowserType(String ua)
	{
		String browser = "";
		try {
			if(ua==null) ua="";
			if (ua.indexOf("Trident") > 0 || ua.indexOf("MSIE") > 0) {
				browser = "IE";
				if(ua.indexOf("Trident") > 0){
					//IE8~IE11
					/*
					Trident/7.0	IE11 Preview
					Trident/6.0	Internet Explorer 10
					Trident/5.0	Internet Explorer 9
					Trident/4.0	Internet Explorer 8
					 * */
					if(ua.indexOf("Trident/7.0") > 0) browser = browser + " 11";
					else if(ua.indexOf("Trident/6.0") > 0) browser = browser + " 10";
					else if(ua.indexOf("Trident/5.0") > 0) browser = browser + " 9";
					else if(ua.indexOf("Trident/4.0") > 0) browser = browser + " 8";
					
				}else if(ua.indexOf("MSIE") > 0){
					//IE6~IE7
					/**
					MSIE 10.0	Internet Explorer 10
					MSIE 9.0	Internet Explorer 9
					MSIE 8.0	Internet Explorer 8 or IE8 Compatibility View/Browser Mode
					MSIE 7.0	Windows Internet Explorer 7 or IE7 Compatibility View/Browser Mode
					MSIE 6.0	Microsoft Internet Explorer 6				 
					 * */

					if(ua.indexOf("MSIE 10.0") > 0) browser = browser + " 10";
					else if(ua.indexOf("MSIE 9.0") > 0) browser = browser + " 9";
					else if(ua.indexOf("MSIE 8.0") > 0) browser = browser + " 8";
					else if(ua.indexOf("MSIE 7.0") > 0) browser = browser + " 7";
					else if(ua.indexOf("MSIE 6.0") > 0) browser = browser + " 6";	
				}
			} else if (ua.indexOf("Opera") > 0 || ua.indexOf("OPR") > 0) {
				browser = "Opera";
			} else if (ua.indexOf("Firefox") > 0) {
				browser = "Firefox";
			} else if (ua.indexOf("Safari") > 0) {
				if (ua.indexOf("Chrome") > 0) {
					browser = "Chrome";
				} else {
					browser = "Safari";
				}
			}else{
				browser = "UnKnown";
			}
		} catch (NullPointerException e) {
			LOGGER.error("ua analysis fail", e);
		}
		browser = browser + " : " + ua;
		if(browser!=null&&browser.length()>95){
			browser = browser.substring(0,95);
       	}
		return browser;
	}

	public static String getClientOS(String ua) {

		String os = "";

		ua = ua.toLowerCase();

		if (ua.indexOf("windows nt 6.1") > -1) {
			os = "Windows7";
		} else if (ua.indexOf("windows nt 6.2") > -1
				|| ua.indexOf("windows nt 6.3") > -1) {
			os = "Windows8";
		} else if (ua.indexOf("windows nt 6.0") > -1) {
			os = "WindowsVista";
		} else if (ua.indexOf("windows nt 5.1") > -1) {
			os = "WindowsXP";
		} else if (ua.indexOf("windows nt 5.0") > -1) {
			os = "Windows2000";
		} else if (ua.indexOf("windows nt 4.0") > -1) {
			os = "WindowsNT";
		} else if (ua.indexOf("windows 98") > -1) {
			os = "Windows98";
		} else if (ua.indexOf("windows 95") > -1) {
			os = "Windows95";
		}
		// window 외
		else if (ua.indexOf("iphone") > -1) {

			os = "iPhone";
		} else if (ua.indexOf("ipad") > -1) {
			os = "iPad";
		} else if (ua.indexOf("android") > -1) {
			os = "android";
		}

		else if (ua.indexOf("mac") > -1) {

			os = "mac";
		} else if (ua.indexOf("linux") > -1) {
			os = "Linux";
		} else {
			os = "undefine";
		}

		return os;
	}

}
