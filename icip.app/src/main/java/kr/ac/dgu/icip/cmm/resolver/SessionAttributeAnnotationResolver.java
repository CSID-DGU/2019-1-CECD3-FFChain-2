package kr.ac.dgu.icip.cmm.resolver;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.ac.dgu.base.cmm.session.SessionManageAdminUtil;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Class Name : NosSessionAttributeAnnotationResolver.java
 * @Description : Session Attribute를 Controller Parameter에 설정
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class SessionAttributeAnnotationResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionAttributeAnnotationResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Annotation[] parameterAnnotations = parameter.getParameterAnnotations();
        for (Annotation parameterAnnotation : parameterAnnotations) {
            if (SessionAttribute.class.isInstance(parameterAnnotation)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception {
        String methodName = parameter.getMethod().getName();
        Annotation[] parameterAnnotations = parameter.getParameterAnnotations();
        String paramName = parameter.getParameterName();
        Class<?> parameterType = parameter.getParameterType();
        
        for (Annotation parameterAnnotation : parameterAnnotations) {
            if (SessionAttribute.class.isInstance(parameterAnnotation)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(methodName + "." + paramName + "[" + parameterType + "]");
                }
                
                SessionAttribute sessionAttribute = (SessionAttribute) parameterAnnotation;
                boolean required = sessionAttribute.required();
                
                HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
                HttpSession session = request.getSession(false);
                Object result = BeanUtils.instantiate(parameterType);
                
                if (session != null) {
                	LoginInfoVO loginInfoVO =  SessionManageAdminUtil.getLoginInfo(request);
                    if(loginInfoVO != null) {
                        BeanUtils.copyProperties(loginInfoVO, result);
                    }
                }
                if (required && session == null)
                    raiseSessionRequiredException(methodName, paramName, parameterType);
                if (required && result == null)
                    raiseMissingParameterException(methodName, paramName, parameterType);
                
                return result;
            } 
        }
        return null;
    }
    
    protected void raiseMissingParameterException(String methodName, String paramName, Class<?> paramType) throws Exception {
        throw new IllegalStateException(methodName + "." + paramName + "[" + paramType.getName() + "]");
    }

    protected void raiseSessionRequiredException(String methodName, String paramName, Class<?> paramType) throws Exception {
        throw new HttpSessionRequiredException(methodName + "." + paramName + "[" + paramType.getName() + "]");
    }
}
