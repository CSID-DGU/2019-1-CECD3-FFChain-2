package kr.ac.dgu.base.cmm.util;

import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : AuthCheckUtil.java
 * @Description :
 * AuthCheckUtil Utility
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2015. 4. 27.           DGU         신규생성
 * </pre>
 */
public class AuthCheckUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCheckUtil.class);

    private static final HashMap<String, String> authpassMatchList =  new HashMap<String, String>(10);
    
    static {
        authpassMatchList.put("1", "500");  //
        authpassMatchList.put("2", "300");  //
        authpassMatchList.put("3", "800");  //
        authpassMatchList.put("4", "200");  //
        authpassMatchList.put("5", "500");  //
        authpassMatchList.put("6", "300");  //
        authpassMatchList.put("7", "100");  //
        authpassMatchList.put("8", "400");  //
        authpassMatchList.put("9", "600");  //
    }
    
    /**
     * authCheck 체크
     * @param obj
     * @param check
     * @param msg
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String getAuthCode(String checkpass) throws Exception {
        String authcode = "";
        LOGGER.debug("AuthCheckUtil valid start.. ");
        
        java.util.Set<String> keys = (java.util.Set<String>) authpassMatchList.keySet();
        
        Iterator<String> itr = keys.iterator();
        
        while (itr.hasNext()) {
            String authpasskey = (String) itr.next();
            if(checkpass.indexOf("/"+authpasskey+"/")>0){
                authcode = (String) authpassMatchList.get(authpasskey);
                break;
            }
        }
        return authcode;
    }

}