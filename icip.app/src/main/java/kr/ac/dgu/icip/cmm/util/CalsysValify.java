
package kr.ac.dgu.icip.cmm.util; 

import java.util.HashMap;

import org.slf4j.*;

/**
 * <pre>
 * kr.ac.dgu.icip.cmm.util 
 *    |_ CalsysValify.java
 * 1. 개요 : 
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 14. 오후 1:24:24
 * @version : 
 * @author : DGU
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 03. 14.		DGU				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class CalsysValify {


	private static final Logger LOGGER = LoggerFactory.getLogger(CalsysValify.class);

	private static final String sysCalsys = "icip";
	
	/**
	 * 접속 시스템 정의
	 */
	private static HashMap<String, String> calsysarr = new HashMap<String, String>();
	
	static {
		calsysarr.put("icip","동국대학교 ICIP");
	}
	
	public CalsysValify() {
		
	}
	
	
	/**
	 * @return the syscalsys
	 */
	public static String getSyscalsys() {
		return sysCalsys;
	}


	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 접속 시스템 확인 
	 * </pre>
	 * @Method Name : isValidSystem
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param calsys
	 * @return true => 해당 사이트 있음
	 */ 	
	public static Boolean isValidSystem(String calsys){
		return calsysarr.containsKey(calsys);
	}	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 접속 시스템 풀 이름 반환
	 * </pre>
	 * @Method Name : getSystemDesc
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param calsys
	 * @return 접속 시스템 풀 이름
	 */ 	
	public static String getSystemDesc(String calsys){
		return calsysarr.get(calsys);
	}
}
