package kr.ac.dgu.base.cmm.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.ac.dgu.icip.cmm.vo.AuthInfoVO;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : SessionManageAdminUtil.java
 * @Description : 관리자세션관리
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 *
 *            <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일             수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU         신규생성
 * </pre>
 */
public class SessionManageAdminUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionManageAdminUtil.class);
    /**
     * 세션에 로그인정보를 설정한다.
     *
     * @param request HttpServletRequest
     * @param loginInfoVO 로그인정보
     */
    public static void setLoginInfo(HttpServletRequest request, LoginInfoVO loginInfoVO) {
        HttpSession session = request.getSession(true);
        if(session!=null&&loginInfoVO!=null) {
            session.setAttribute("loginInfoVO", loginInfoVO);
        }
    }

    /**
     * 세션에 로그인정보를 설정한다.
     *
     * @param session HttpSession
     * @param loginInfoVO 로그인정보
     */ 	
    public static void setLoginInfo(HttpSession session, LoginInfoVO loginInfoVO) {
        if(session!=null&&loginInfoVO!=null) {
            session.setAttribute("loginInfoVO", loginInfoVO);
        }
    }

    /**
     * 세션에서 로그인정보를 가져온다.
     *
     * @param request HttpServletRequest
     * @return 로그인정보
     */
    public static LoginInfoVO getLoginInfo(HttpServletRequest request ) {
    	LoginInfoVO loginInfoVO = null;

        HttpSession session = request.getSession(false);
        if(session != null){
	        Object obj = session.getAttribute("loginInfoVO");
	        if(obj!=null) loginInfoVO = (LoginInfoVO)obj;
        }
    	else LOGGER.debug("Session is null");
        return loginInfoVO;
    }

    /**
     * 세션에서 로그인정보를 가져온다.
     *
     * @param session HttpSession
     * @return 로그인정보
     */
    public static LoginInfoVO getLoginInfo(HttpSession session ) {
    	LoginInfoVO loginInfoVO = null;

        if(session != null){
            Object obj = session.getAttribute("loginInfoVO");
            if(obj!=null) loginInfoVO = (LoginInfoVO)obj;
        }
        else LOGGER.debug("Session is null");

        return loginInfoVO;
    }

    /**
     * 세션에 LoginInfoVO가 존재하는지 체크
     *
     * @param request HttpServletRequest
     * @return true: 로그인된경우, false: 로그아웃된경우
     */
    public static boolean isValidSession(HttpServletRequest request) {
        boolean isValid = false;

        LoginInfoVO loginInfoVO = SessionManageAdminUtil.getLoginInfo(request);
        if (loginInfoVO != null) {
            isValid = true;
        }

        return isValid;
    }

    /**
     * 세션에 권한정보를 설정한다.
     *
     * @param request HttpServletRequest
     * @param AuthInfoVO 권한정보
     */
    public static void setCurrAuthInfo(HttpServletRequest request, AuthInfoVO authInfoVO) {
        HttpSession session = request.getSession(true);
        if(session!=null&&authInfoVO!=null) {
            session.setAttribute("authInfoVO", authInfoVO);
        }
    }    
    /**
     * 세션에서 권한정보를 가져온다.
     *
     * @param session HttpSession
     * @return 권한정보
     */
    public static AuthInfoVO getCurrAuthInfo(HttpServletRequest request ) {
        AuthInfoVO authInfoVO = null;
        HttpSession session = request.getSession();
        if(session != null){
            Object obj = session.getAttribute("authInfoVO");
            if(obj!=null) authInfoVO = (AuthInfoVO)obj;
        }
        else LOGGER.debug("Session is null");

        return authInfoVO;
    }   
}
