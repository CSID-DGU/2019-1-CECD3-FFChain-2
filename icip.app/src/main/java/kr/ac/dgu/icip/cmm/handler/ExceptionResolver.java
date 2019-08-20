package kr.ac.dgu.icip.cmm.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.base.cmm.exception.HtmlEditorException;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.ParameterParser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @Class Name : BaseExceptionResolver.java
 * @Description : SimpleMappingExceptionResolver를 확장하여 Ajax Error Message 처리
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
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    /** AJAX Request Header 이름 */
    private String ajaxHeaderName;

    /** AJAX Request Header INPUT 이름:IE9이하 버그 대응 */
    private String ajaxHeaderInputName;

    /** AJAX Exception 발생시 처리할 JSP */
    private String ajaxErrorView;

    /** HTML Editor Upload Exception 발생시 처리할 JSP */
    private String htmlEditorErrorView;

    @Resource(name = "messageSource")
    protected MessageSource egovMessageSource;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("handler: " + handler);
            LOGGER.debug("exception:" + exception);
        }
        
        String ajaxHeaderValue = request.getHeader(getAjaxHeaderName());
        String ieBugFix = request.getParameter(getAjaxHeaderInputName());
        // MaxUploadSizeExceededException 경우 request에서 Prameter 인출
        if (exception instanceof MaxUploadSizeExceededException) {
            if (StringUtils.isEmpty(ajaxHeaderValue) && StringUtils.isEmpty(ieBugFix)) {
                ieBugFix = getMutipartParameter(request, getAjaxHeaderInputName());
            }
        }

        String viewName = determineViewName(exception, request, ajaxHeaderValue, ieBugFix);
        Exception ex = exception;
        String message = getExceptionMessage(exception, request, ajaxHeaderValue, ieBugFix);
        if (viewName != null) {
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }

            // Common Upload에서 Throw한 경우 NosHtmlEditorException로 Wrapping
            if (exception instanceof MaxUploadSizeExceededException) {
                ex = new HtmlEditorException(message);
            } else if (!(exception instanceof BaseException)) {
                if(exception.getCause() == null) {
                    ex = new BaseException(exception, message);
                } else {
                    ex = new BaseException(exception.getCause(), message);
                }
            } else {
                ex = new BaseException(exception, message);
            }

            return getModelAndView(viewName, ex, request);
        } else {
            return null;
        }
    }

    /**
     * 에러를 표시할 JSP를 결정.
     * @param ex
     * @param request
     * @param ajaxHeaderValue
     * @param ieBugFix
     * @return
     */
    protected String determineViewName(Exception ex, HttpServletRequest request, String ajaxHeaderValue, String ieBugFix) {
        String viewName = null;
        String requestURI = request.getRequestURI();

        // Ajax 호출인 경우, json 처리 view리턴
        if (Boolean.TRUE.toString().equalsIgnoreCase(ajaxHeaderValue) || Boolean.TRUE.toString().equalsIgnoreCase(ieBugFix)) {
            viewName = getAjaxErrorView();
        } else {
            viewName = super.determineViewName(ex, request);
        }

        return viewName;
    }

    /**
     * Exception에 따라 메시지를 설정하고, Ajax 호출인 경우 json 으로 변환한다.
     * 
     * @param ex
     * @param request
     * @return
     */
    protected String getExceptionMessage(Exception ex, HttpServletRequest request, String ajaxHeaderValue, String ieBugFix) {
        String message = ex.getMessage();

        // Common Upoload Exception인 경우 업무메시지로 대체
        if (ex instanceof MaxUploadSizeExceededException) {
            message = egovMessageSource.getMessage("lib.common.sizelimitexceededexception.error", null, Locale.getDefault());
        }

        // Ajax 호출인 경우, json 메시지로 변환
        if (Boolean.TRUE.toString().equalsIgnoreCase(ajaxHeaderValue) || Boolean.TRUE.toString().equalsIgnoreCase(ieBugFix)) {
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

        return message;
    }

    /**
     * Mutltipart Request에서 Parmeter값을 인출.
     * 
     * @param request
     * @param paramName
     * @return
     */
    private String getMutipartParameter(HttpServletRequest request, String paramName) {
        String paramValue = "";

        ByteArrayOutputStream output = null;
        InputStream input = null;
        try {
            input = request.getInputStream();
            String contentType = request.getContentType();
            byte[] boundary = getBoundary(contentType);

            MultipartStream multipartStream = new MultipartStream(input, boundary);
            boolean nextPart = multipartStream.skipPreamble();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Mutilpart Parsing Start =================================");
            }
            String header = "";
            while (nextPart) {
                header = multipartStream.readHeaders();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("\tHeader : " + header);
                }

                if (StringUtils.contains(header, paramName)) {
                    output = new ByteArrayOutputStream();
                    multipartStream.readBodyData(output);
                    
                    paramValue = output.toString();
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("\t** " + header + ":" + paramValue);
                    }
                    break;
                } else {
                    multipartStream.readBodyData(null);
                }
                
                nextPart = multipartStream.readBoundary();
                IOUtils.closeQuietly(output);
            }
        } catch (MultipartStream.MalformedStreamException e) {
            LOGGER.error("MultipartStream.MalformedStreamException", e);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }

        return paramValue;
    }

    /**
     * Mutltipart Request에서 Boundary 인출.
     * 
     * @param contentType
     * @return
     */
    protected byte[] getBoundary(String contentType) {
        ParameterParser parser = new ParameterParser();
        parser.setLowerCaseNames(true);
        // Parameter parser can handle null input
        Map<String, String> params = parser.parse(contentType, new char[] { ';', ',' });
        String boundaryStr = params.get("boundary");

        if (boundaryStr == null) {
            return null;
        }
        byte[] boundary;
        try {
            boundary = boundaryStr.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            boundary = boundaryStr.getBytes(); // Intentionally falls back to
                                               // default charset
        }
        return boundary;
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

    public String getAjaxErrorView() {
        return ajaxErrorView;
    }

    public void setAjaxErrorView(String ajaxErrorView) {
        this.ajaxErrorView = ajaxErrorView;
    }

    public String getHtmlEditorErrorView() {
        return htmlEditorErrorView;
    }

    public void setHtmlEditorErrorView(String htmlEditorErrorView) {
        this.htmlEditorErrorView = htmlEditorErrorView;
    }

}
