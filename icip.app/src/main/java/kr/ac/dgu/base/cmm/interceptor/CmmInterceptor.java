package kr.ac.dgu.base.cmm.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.base.cmm.session.SessionManageAdminUtil;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : CmmInterceptor.java
 * @Description :
 * 모든 프로젝트에서 공통적으로 처리 할 기능을 넣을 Interceptor
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
public class CmmInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CmmInterceptor.class);

	/**     작업 시작 시간 저장    */
	//private static Date workStartTime = null;
    /**     작업 종료 시간 저장    */
    //private static Date workEndtime = null;

    //private static int minute = 0;
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


	/**
	 * pre handler
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param handler	Object
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 모든 프로젝트에서 공통적으로 먼저 처리될 것을 체크한다
	    try {
    		if(LOGGER.isDebugEnabled()) {
    	        // client에 IP와 요청 URI를 찍는다.
                String requestIp = request.getRemoteAddr();
                String requestUri = request.getRequestURI();
    		    if(SessionManageAdminUtil.isValidSession(request)){
    		        LoginInfoVO loginInfoVO = SessionManageAdminUtil.getLoginInfo(request);
    		        LOGGER.debug("ip["+requestIp+"],url["+requestUri+"] "+loginInfoVO);
    		    }else 
                    LOGGER.debug("ip["+requestIp+"],url["+requestUri+"]");
    		}
	    } catch (NullPointerException e) {
	        throw new BaseException(e);
	    } catch (Exception e) {
            throw new BaseException(e);
        }

		return true;
	}
}
