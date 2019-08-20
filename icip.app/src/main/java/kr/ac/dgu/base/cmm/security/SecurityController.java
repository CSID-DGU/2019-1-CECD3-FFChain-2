package kr.ac.dgu.base.cmm.security;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.base.cmm.web.BaseController;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Class Name : SecurityController.java
 * @Description : 보안 Controller
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
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 03. 06.		DGU			신규생성
 * </pre>
 */
@Controller
public class SecurityController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    
    @Resource(name="messageSource")
    protected MessageSource egovMessageSource;

    /**
     * 인증처리 실패시 이동
     * 
     * @return 에러 페이지 화면
     * @throws Exception
     */
    @RequestMapping("/cmmn/security/accessDenied.do")
    public String accessDenied(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	String defaultErrorView = "cmmn/error/error";
    	String ajaxErrorView = "cmmn/error/ajaxError";
    	String ajaxHeaderName = "AJAX";
    	String ajaxHeaderInputName = "_ajax_heder";
        
        String ajaxHeaderValue = request.getHeader(ajaxHeaderName);
        String ieBugFix = request.getParameter(ajaxHeaderInputName);
        boolean isAjaxCall = false;
        
        if(Boolean.TRUE.toString().equalsIgnoreCase(ajaxHeaderValue) || Boolean.TRUE.toString().equalsIgnoreCase(ieBugFix)) {
            isAjaxCall = true;
        }
        
        String message = egovMessageSource.getMessage("fail.common.csrf", null, Locale.getDefault());
        String viewName = defaultErrorView;
        if (isAjaxCall) {
            viewName = ajaxErrorView;
            ObjectMapper om = new ObjectMapper();
            try {
                message = om.writeValueAsString(message);
            } catch (JsonGenerationException e) {
                LOGGER.error("JsonGenerationException", e);
            } catch (JsonMappingException e) {
                LOGGER.error("JsonMappingException", e);
            } catch (IOException e) {
                LOGGER.error("IOException", e);
            }
        }
        
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("isAjaxCall:" + isAjaxCall);
            LOGGER.debug("viewName:" + viewName);
        }
        
        BaseException ex = new BaseException(message);
        model.addAttribute("exception", ex);
        
        return viewName;
    }
}
