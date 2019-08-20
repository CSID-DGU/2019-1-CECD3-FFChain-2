package kr.ac.dgu.icip.system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.icip.cmm.vo.SystemInfoVO;

/**
 * @Class Name : SystemInfoService.java
 * @Description : System 정보 생성 
 * @author 동국대학교 ICIP
 * @since 2019. 03. 06.
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
public interface SystemInfoService {

	/**
	 * <pre>
	 * 1. 개요 : 시스템 정보를 조회 한다. 
	 * </pre>
	 * @Method Name : getSystemInfo
	 * @author : dgu
	 * @date : 2019. 3. 14.
	 * @param request
	 * @param response
	 * @return 시스템 정보
	 * @throws Exception
	 */ 	
	SystemInfoVO getSystemInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
}
