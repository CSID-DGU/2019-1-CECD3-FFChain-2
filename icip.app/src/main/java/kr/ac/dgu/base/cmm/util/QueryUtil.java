package kr.ac.dgu.base.cmm.util;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : QueryUtil.java
 * @Description :
 * Query에 대한 Utility 클래스
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class QueryUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryUtil.class);

    /**
     * <pre>
     * 개요 : In 절의 배열을 생성한다.
     * </pre>
     * @Method Name : makeInQuery
     * @author : dgu
     * @date : 2018. 9. 8.
     * @param arrList
     * @return
     */ 	
    public static String makeInQuery(List<String> arrList) {
        String retQyery = "";
        try {
            if (arrList != null) {
                for (Iterator iterator = arrList.iterator(); iterator.hasNext();) {
                    String itemCode = (String) iterator.next();
                    retQyery += itemCode + ",";
                }
                if (!retQyery.equals(""))
                    retQyery = retQyery.substring(0, retQyery.length() - 1);
            }
        } catch (Exception e) {
            LOGGER.debug("makeInQuery Error : "+e.toString());
        }
        return retQyery;
    }

    /**
     * <pre>
     * 개요 : 지역 코드 배열을 체크 한다.
     * </pre>
     * @Method Name : checkArrayCityCode
     * @author : dgu
     * @date : 2018. 9. 8.
     * @param chk01CityCd
     * @param string
     * @return
     */ 	
    public static String checkArrayCityCode(List arrList, String cityCode) {
        String isContainCode = "N";
        try {
            if (arrList != null) {
                for (Iterator iterator = arrList.iterator(); iterator.hasNext();) {
                    String itemCode = (String) iterator.next();
                    if(cityCode.equals(itemCode)){
                        isContainCode = "Y";
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.debug("checkArrayCityCode Error : "+e.toString());
        }        
        return isContainCode;
    }

    /**
     * <pre>
     * 개요 : 지역 코드 배열이 적당한지 확인
     * </pre>
     * @Method Name : isCityQuery
     * @author : dgu
     * @date : 2018. 9. 8.
     * @param chk01CityCd
     * @return
     */ 	
    public static String isCityQuery(List arrList) {
        String isCode = "N";
        int codeCnt = 0;
        try {
            if (arrList != null) {
                
                for (Iterator iterator = arrList.iterator(); iterator.hasNext();) {
                    String itemCode = (String) iterator.next();
                    if(itemCode.equals("12")||itemCode.equals("21")){
                        continue;
                    }
                    else codeCnt++;
                }
            }
        } catch (Exception e) {
            LOGGER.debug("arrList : "+arrList);
            LOGGER.debug("isCode Error : "+e.toString());
        } 
        if(codeCnt>0) isCode = "Y";
        return isCode;
    }

    /**
     * <pre>
     * 개요 : 배열의 첫번째 값 리턴
     * </pre>
     * @Method Name : getFirstArray
     * @author : dgu
     * @date : 2018. 9. 11.
     * @param interestArea
     * @return
     */ 	
    public static String getFirstArray(List<String> arrList) {
        String code = "";
        try {
            if (arrList != null) {
                code = arrList.get(0);
            }
        } catch (Exception e) {
            LOGGER.debug("isCode Error : "+e.toString());
        } 
        return code;
    }
    /**
     * <pre>
     * 개요 : 쿼리 내의 쌍따음표 제거
     * </pre>
     * @Method Name : queryReplace
     * @author : dgu
     * @date : 2018. 10. 14.
     * @param query
     * @return
     */ 	
    public static String queryReplace(String query){
        String retquery = "";
        try {
            retquery = query.replaceAll("\"", "'");
        } catch (Exception e) {
            retquery = "";
        }
        return retquery;
    }
 
}
