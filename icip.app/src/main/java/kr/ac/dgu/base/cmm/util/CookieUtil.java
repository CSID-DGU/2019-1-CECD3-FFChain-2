
package kr.ac.dgu.base.cmm.util; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.ac.dgu.base.cmm.util 
 * @Class Name : CookieUtil.java
 * @Description :  class
 * @Modification Information
 * </pre>
 * @author : dgu
 * @date : 2019. 3. 9. 오전 11:17:13
 * @version : 1.0
 *  
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 3. 9.		dgu				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class CookieUtil {

    private Logger LOGGER = Logger.getLogger(this.getClass());

    /**
     * <pre>
     * 1. 개요 : 쿠키 가져오기
     * </pre>
     * @Method Name : getCookie
     * @author : dgu
     * @date : 2019. 3. 9.
     * @param request
     * @param CookieName
     * @return
     * @throws Exception
     */ 	
    public static String getCookie(HttpServletRequest request, String CookieName)
            throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        String value = "";

        for (int i = 0; i < cookies.length; i++) {
            if (CookieName.equals(cookies[i].getName())) {
                value = cookies[i].getValue();

                break;
            }
        }

        return value;
    }    
    
}
