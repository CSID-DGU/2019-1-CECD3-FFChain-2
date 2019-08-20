
package kr.ac.dgu.icip.system.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.dgu.icip.cmm.vo.SystemInfoVO;
import kr.ac.dgu.icip.system.service.SystemInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : SystemInfoServiceImpl.java
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

@Service("systemInfoService")
public class SystemInfoServiceImpl extends EgovAbstractServiceImpl implements SystemInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemInfoServiceImpl.class);

	/**
	 * <pre>
	 * 1. 개요 : 시스템 정보를 조회 한다. 
	 * </pre>
	 * @Method Name : getSystemInfo
	 * @author : dgu
	 * @date : 2019. 3. 14.
	 * @see kr.ac.dgu.icip.system.service.SystemInfoService#getSystemInfo(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */ 
	public SystemInfoVO getSystemInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SystemInfoVO svo = new SystemInfoVO();
		try{
			HttpSession session = request.getSession();
			svo.setSessionSize(getSessionSize(session));
			svo.setServerIp(request.getLocalAddr());
			svo.setSessionKey(session.getId());
			
			svo.setServerName        (request.getServerName()         );
			svo.setProtocol          (request.getProtocol()           );
			svo.setServerPort        (request.getServerPort()+""         );
			svo.setMethod            (request.getMethod()             );
			svo.setPathInfo          (request.getPathInfo()           );
			svo.setPathTranslated    (request.getPathTranslated()     );
			svo.setServletPath       (request.getServletPath()        );
			svo.setRealPath          (request.getRealPath("/")        );
			svo.setQueryString       (request.getQueryString()        );
			svo.setRemoteHost        (request.getRemoteHost()         );
			svo.setRemoteAddr        (request.getRemoteAddr()         );
			svo.setAuthType          (request.getAuthType()           );
			svo.setRemoteUser        (request.getRemoteUser()         );
			svo.setContentType       (request.getContentType()        );
			svo.setContentLength     (request.getContentLength()+""      );
			svo.setHeaderAccept      (request.getHeader("Accept")     );
			svo.setHeaderUserAgent  (request.getHeader("User-Agent") );
			svo.setHeaderReferer     (request.getHeader("Referer")    );
			svo.setSessionMaxInactiveInterval(session.getMaxInactiveInterval());
			long creationtime = session.getCreationTime();
			long timeUsed = (session.getLastAccessedTime() -creationtime)/60000;
			svo.setSessionCreationTime(session.getCreationTime());
			svo.setTimeUsed(timeUsed);
		}catch(NullPointerException ex)
		{
			LOGGER.error("",ex);
		}catch(Exception ex)
		{
			LOGGER.error("",ex);
		}
		return svo;
	}

	/**
	 * <pre>
	 * 1. 개요 : 세션 사이즈 측정
	 * </pre>
	 * @Method Name : getSessionSize
	 * @author : dgu
	 * @date : 2019. 3. 14.
	 * @param session
	 * @return
	 */ 	
	private int getSessionSize(HttpSession session) {
		Enumeration en = session.getAttributeNames();
		String name = null;
		Object obj = null;
		ByteArrayOutputStream bastream = null;
		ObjectOutputStream objOut = null;
		int objSize;
		int totalSize = 0;
		while (en.hasMoreElements()) {
			name = (String) en.nextElement();
			obj = session.getAttribute(name);

			try {
				bastream = new ByteArrayOutputStream();
				objOut = new ObjectOutputStream(bastream);
				objOut.writeObject(obj);

				// objSize
				objSize = bastream.size();
			} catch (IOException ex) {
				objSize = 0;
			}
			if (LOGGER.isErrorEnabled()) {
				LOGGER.debug("Session Name : " + name + "\t\t Size : "
						+ objSize);
			}
			// calculate
			totalSize += objSize;
		}
		return totalSize;

	} // End getSessionSize().

}
