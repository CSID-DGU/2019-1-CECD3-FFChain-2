package kr.ac.dgu.icip.cmm.interceptor;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.session.SessionManageAdminUtil;
import kr.ac.dgu.icip.cmm.vo.AuthInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : AuthenticInterceptor.java
 * @Description :
 * 세션 체크및 권한 체크 인터셉터.
 * @author 동국대학교 ICIP
 * @since 2015. 1. 30.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2015. 1. 30.		DGU			신규생성
 * </pre>
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticInterceptor.class);
    
    /** Request Parmate value 최대 디버그 출력값 */
    private static final int MAX_LENGTH_VALUE = 100;

    /** MessageSource */
    @Resource(name="messageSource")
    protected MessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** check Url 사용여부 */
    private boolean useIncludeUrl;

    /** 제외 URL */
    private List<String> exceptUrl;

    /** 체크 URL */
    private List<String> includeUrl;

    /** AJAX Request Header 이름 */
    private String ajaxHeaderName;


    /** AJAX Request Header INPUT 이름:IE9이하 버그 대응 */
    private String ajaxHeaderInputName;

    @Autowired
    private ApplicationContext appContext;

	/**
	* 인터셉터 preHandle
	* @param request
	* @param response
	* @param handler
	* @return boolean
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}



    /**
     * <pre>
     * 1. 개요 : 인터셉터 포스트핸들
     * </pre>
     * @Method Name : postHandle
     * @author : dgu
     * @date : 2019. 3. 21.
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {

        AuthInfoVO authInfoVO = SessionManageAdminUtil.getCurrAuthInfo(request);
        model.addObject("authInfoVO", authInfoVO);
        //LOGGER.debug("authInfoVO : " + model.getModel() +"\n"+authInfoVO);
    }

    public static boolean isRedirectView(ModelAndView mv) {
        String viewName = mv.getViewName();
        if (viewName.startsWith("redirect:/")) {
            return true;
        }
        View view = mv.getView();
        return (view != null && view instanceof SmartView
          && ((SmartView) view).isRedirectView());
    }




    public String getAjaxHeaderName() {
        return ajaxHeaderName;
    }

    public void setAjaxHeaderName(String ajaxHeaderName) {
        this.ajaxHeaderName = ajaxHeaderName;
    }

    public String getAjaxHeaderInputName() {
        return ajaxHeaderInputName;
    }

    public void setAjaxHeaderInputName(String ajaxHeaderInputName) {
        this.ajaxHeaderInputName = ajaxHeaderInputName;
    }

    public boolean getUseIncludeUrl() {
        return useIncludeUrl;
    }

    public void setUseIncludeUrl(boolean useIncludeUrl) {
        this.useIncludeUrl = useIncludeUrl;
    }

    public List<String> getExceptUrl() {
        return exceptUrl;
    }

    public void setExceptUrl(List<String> exceptUrl) {
        this.exceptUrl = exceptUrl;
    }

    public List<String> getIncludeUrl() {
        return includeUrl;
    }

    public void setIncludeUrl(List<String> includeUrl) {
        this.includeUrl = includeUrl;
    }

    private boolean checkAuthRequiiredUrl(String url) {
        boolean needCheck = false;
        if(useIncludeUrl) {
            needCheck = includeUrl.contains(url);
        } else {
            needCheck = !exceptUrl.contains(url);
        }

        if(LOGGER.isDebugEnabled()) {
            StringBuffer logBuffer = new StringBuffer();

            logBuffer.append("\n=====================================================");
            logBuffer.append("\n권한대상 여부 체크");
            logBuffer.append("\nurl            : ").append(url);
            logBuffer.append("\nuseIncludeUrl  : ").append(useIncludeUrl);
            logBuffer.append("\nneedCheck      : ").append(needCheck);
            logBuffer.append("\n=====================================================\n");

            LOGGER.debug(logBuffer.toString());
        }

        return needCheck;
    }

    private static void interceptorRedirect(HttpServletResponse response, String msg, String url) throws IOException {
        String resultMsg = ("".equals(msg) ? "" : "alert('"+msg+"');");
        String resultUrl = ("".equals(url) ? "history.back();" : "location.href='"+url+"';");

        response.setContentType("text/html; charset=\"utf-8\"");
        response.getWriter().println("<script type=\"text/javascript\">"+resultMsg+resultUrl+"</script>");
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * <pre>
     * 개요 : 사용자 로그인 페이지로 리다이렉트 처리 한다.
     * Spring forward
     * </pre>
     * @Method Name : interceptorRedirect
     * @author : ahnjw
     * @date : 2018. 10. 26.
     * @param zone 기동환경
     * @param msg 리다이렉트 메시지
     * @param targeturl 리다이렉트 URL
     * @throws ModelAndViewDefiningException
     */
    private static void interceptorRedirect(String zone, String msg, String targeturl) throws ModelAndViewDefiningException {
        ModelAndView mav = new ModelAndView("cmmn/error/sessionError");
        mav.addObject("zone", zone);
        mav.addObject("msg", msg);
        mav.addObject("targeturl", targeturl); // 세션값이 맞지 않으면 에러를 뱉으면서 메인으로 리다이렉트

        throw new ModelAndViewDefiningException(mav);
    }

    /**
     *
     * <pre>
     * 개요 : 사용자 로그인 페이지로 리다이렉트 처리 한다.
     * Response forward
     * </pre>
     * @Method Name : interceptorRedirect
     * @author : ahnjw
     * @date : 2018. 10. 26.
     * @param request
     * @param response
     * @param zone 기동환경
     * @param msg 리다이렉트 메시지
     * @param targeturl 리다이렉트 URL
     * @throws ModelAndViewDefiningException
     */
    private static void interceptorRedirect(HttpServletRequest request, HttpServletResponse response, String zone, String msg, String targeturl,
            boolean isMisMatchSsoId, boolean isEmptySsoId)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cmmn/error/sessionError.jsp");
        request.setAttribute("zone", zone);
        request.setAttribute("msg", msg);
        request.setAttribute("targeturl", targeturl);
        request.setAttribute("isMisMatchSsoId", isMisMatchSsoId);
        request.setAttribute("isEmptySsoId", isEmptySsoId);

        requestDispatcher.forward(request, response);
    }
}
