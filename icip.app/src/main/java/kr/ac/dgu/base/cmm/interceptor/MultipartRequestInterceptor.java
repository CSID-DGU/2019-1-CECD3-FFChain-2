package kr.ac.dgu.base.cmm.interceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.base.cmm.exception.HtmlEditorException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class Name : MultipartRequestInterceptor.java
 * @Description : Mulitipart Request 를 체크하여 파일확장자 사이즈를 체크한다.
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
 *  2019. 3. 7.		DGU		    신규생성
 * </pre>
 */
public class MultipartRequestInterceptor extends HandlerInterceptorAdapter {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MultipartRequestInterceptor.class);

    /** 업로드 가능 파일 확장자 List */
    private Map<String, Long> allowExt;

    /** HTML Editor 업로드 가능 파일 확장자 List */
    private Map<String, Long> htmlEditorAllowExt;

    /** 최대업로드 파일 크기 */
    private Long maxSize;

    /** HTML Editor Upload URL 문지열 */
    private String htmlEditorUploadHandler;

    @Resource(name = "messageSource")
    protected MessageSource egovMessageSource;

    /**
     * pre handler
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler Object
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("_attach_allowExt", this.allowExt);
        request.setAttribute("_attach_maxSize", this.maxSize);
        
        try {
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (isMultipartContent) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("MultipartRequestInterceptor.preHandle() : isMultipartContent");
                }

                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                MultiValueMap<String, MultipartFile> fileMap = multiRequest.getMultiFileMap();

                Iterator<String> keys = fileMap.keySet().iterator();
                String key = "";
                List<MultipartFile> fileList = null;
                String fileName = "";
                String ext = "";
                long fileSize = 0;
                long maxSize = 0;
                String strFileSize = "0";
                String strMaxSize = "0";
                String requestURI = request.getRequestURI();
                while (keys.hasNext()) {
                    key = keys.next();
                    fileList = fileMap.get(key);

                    for (MultipartFile multipartFile : fileList) {
                        fileName = multipartFile.getOriginalFilename();
                        ext = StringUtils.substringAfterLast(fileName, ".");
                        fileSize = multipartFile.getSize();
                        strFileSize = FileUtils.byteCountToDisplaySize(fileSize);

                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("parameter:" + key + ", filename:" + fileName + "[" + strFileSize + ":" + fileSize + "bytes]");
                        }

                        // 파일명이 공백이면 체크 안함.
                        if (StringUtils.isEmpty(fileName)) {
                            continue;
                        }

                        String errorMsg = "";
                        errorMsg = egovMessageSource.getMessage("lib.common.upFile.ext.error", new String[] { ext }, Locale.getDefault());
                        if (StringUtils.contains(requestURI, htmlEditorUploadHandler)) {
                            if (!checkHtmlEditorAllowExt(ext)) {
                                throw new HtmlEditorException(errorMsg);
                            }
                        } else {
                            if (!checkAllowExt(ext)) {
                                throw new BaseException(errorMsg);
                            }
                        }

                        
                        if (StringUtils.contains(requestURI, htmlEditorUploadHandler)) {
                            maxSize = getMaxSize(ext, this.htmlEditorAllowExt);
                            strMaxSize = FileUtils.byteCountToDisplaySize(maxSize);
                            errorMsg = egovMessageSource.getMessage("lib.common.upFile.size.error", new String[] { strMaxSize }, Locale.getDefault());
                            
                            if (!checkHtmlEditorMaxSize(maxSize, fileSize)) {
                                throw new HtmlEditorException(errorMsg);
                            }

                        } else {
                            maxSize = getMaxSize(ext, this.allowExt);
                            strMaxSize = FileUtils.byteCountToDisplaySize(maxSize);
                            errorMsg = egovMessageSource.getMessage("lib.common.upFile.size.error", new String[] { strMaxSize }, Locale.getDefault());
                            
                            if (!checkMaxSize(maxSize, fileSize)) {
                                throw new BaseException(errorMsg);
                            }
                        }
                    }
                }
            }

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException(e);
        }

        return true;
    }

    private boolean checkAllowExt(String ext) {
        String lowerExt = ext.toLowerCase();
        Set<String> keyExt = this.allowExt.keySet();

        return keyExt.contains(lowerExt);
    }

    private boolean checkHtmlEditorAllowExt(String ext) {
        String lowerExt = ext.toLowerCase();
        Set<String> keyExt = this.htmlEditorAllowExt.keySet();

        return keyExt.contains(lowerExt);
    }
    
    private long getMaxSize(String ext, Map<String, Long> extMap) {
        long maxSize = extMap.get(ext.toLowerCase());
        maxSize = maxSize != 0 ? maxSize : this.maxSize;
        
        return maxSize;
    }

    private boolean checkMaxSize(long argMaxSize, long fileSize) {
        return fileSize <= argMaxSize;
    }

    private boolean checkHtmlEditorMaxSize(long argMaxSize, long fileSize) {
        return fileSize <= argMaxSize;
    }

    public void setAllowExt(Map<String, Long> allowExt) {
        this.allowExt = allowExt;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public void setHtmlEditorAllowExt(Map<String, Long> htmlEditorAllowExt) {
        this.htmlEditorAllowExt = htmlEditorAllowExt;
    }

    public String getHtmlEditorUploadHandler() {
        return htmlEditorUploadHandler;
    }

    public void setHtmlEditorUploadHandler(String htmlEditorUploadHandler) {
        this.htmlEditorUploadHandler = htmlEditorUploadHandler;
    }
}
