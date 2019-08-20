package kr.ac.dgu.base.cmm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.vo.FileVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : HttpUtil.java
 * @Description : HTTP Protocol 처리 관련 유틸 Class
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
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class HttpUtil {
    /** LOGGER */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    
    /**
     * HTTP Attachment Header에 설정될 다운로드 파일명 생성.
     * 브라우저에 따라서 파일명을 인코딩한다.
     * 
     * @param request HttpServletRequest
     * @param fileName 파일명
     * @return 다운로드 파일명
     * @throws UnsupportedEncodingException
     */
    public static String makeAttachmentFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String attachmentFileName = "";
        String browser = request.getHeader("User-Agent");
        if (browser.contains("MSIE") || browser.contains("Chrome") || browser.contains("Trident") || browser.contains("Firefox")) {
            attachmentFileName = makeUtf8FileName(request, fileName);
        } else {
            attachmentFileName = fileName;
        }

        return attachmentFileName;
    }
    
    /**
     * 파일명을 UTF8을 인코딩한다.
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String makeUtf8FileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String utf8FileName = "";
        
        String browser = request.getHeader("User-Agent");
        if (browser.contains("MSIE") || browser.contains("Chrome") || browser.contains("Trident") || browser.contains("Firefox")) {
            utf8FileName =  URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } else {
            utf8FileName =  URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        }
        
        return utf8FileName;
    }
    
    /**
     * 파일명 Content-Disposition 을 설정한다.
     * @param request
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String makeContentDisposition(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String attachmentFileName = HttpUtil.makeAttachmentFileName(request, fileName);
        String urf8Filename = makeUtf8FileName(request, fileName);
        String contentDisposition = "";
        
        String browser = request.getHeader("User-Agent");
        if (browser.contains("MSIE") || browser.contains("Chrome") || browser.contains("Trident") || browser.contains("Firefox")) {
            contentDisposition = "attachment;filename=\"" + attachmentFileName + "\";filename*=UTF-8''" + urf8Filename;
        } else {
            contentDisposition = "attachment; filename*=UTF-8''" + urf8Filename + "; filename=\"" + attachmentFileName + "\"";
        }
        
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("browser : " + browser);
            LOGGER.debug("fileName : " + fileName);
            LOGGER.debug("attachmentFileName : " + attachmentFileName);
            LOGGER.debug("urf8Filename : " + urf8Filename);
            LOGGER.debug("contentDisposition : " + contentDisposition);
        }
        
        return contentDisposition;
    }
    
    /**
     * 파일 다운로드 HTTP Attachment Header 생성
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param fileName 파일명
     * @param contentType contentType
     * @param contentTrasferEncoding contentTrasferEncoding
     * @param contentLength 파일사이즈
     * @param bufferSize bufferSize
     * @throws UnsupportedEncodingException
     */
    public static void makeAttachmentHeader(HttpServletRequest request, HttpServletResponse response, String fileName, String contentType, String contentTrasferEncoding, int contentLength, int bufferSize) throws UnsupportedEncodingException {
        String contentDisposition = makeContentDisposition(request, fileName);
        
        response.setHeader("Content-Disposition", contentDisposition);
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Transfer-Encoding", contentTrasferEncoding);
        if(contentLength > 0) {
            response.setContentLength(contentLength);
        }
        if(bufferSize > 0) {
            response.setBufferSize(bufferSize);
        }
    }
    
    /**
     * Excel 디운로드 HTTP Attachment Header 생성
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param fileName 파일명
     * @throws UnsupportedEncodingException
     */
    public static void makeAttachmentHeaderForExcel(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        String contentType = "application/vnd.ms-excel;charset=UTF-8";
        String contentTrasferEncoding = "binary";
        int contentLength = 0;
        int bufferSize = 0;
        makeAttachmentHeader(request, response, fileName, contentType, contentTrasferEncoding, contentLength, bufferSize);
    }
    
    /**
     * 파일 다운로드 HTTP Attachment Header 생성
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param fileName 파일명
     * @param contentLength 파일사이즈
     * @throws UnsupportedEncodingException
     */
    public static void makeAttachmentHeaderForFile(HttpServletRequest request, HttpServletResponse response, String fileName, int contentLength) throws UnsupportedEncodingException {
        String contentType = "application/octet-stream";
        String contentTrasferEncoding = "binary";
        int bufferSize = 0;
        makeAttachmentHeader(request, response, fileName, contentType, contentTrasferEncoding, contentLength, bufferSize);
    }
    
    /**
     * 파일 다운로드 parameter를 조합한다.
     * 
     * @param fileVoParam
     * @return
     */
    public static String makeFileVoParam(FileVO fileVoParam) {
        String result = "";
        String atchFilePtcsNo = "atchFilePtcsNo=" + fileVoParam.getAtchFilePtcsNo();
        String atchSeq = "atchSeq=" + fileVoParam.getAtchSeq();
        String thnlNm = "thnlNm=" + fileVoParam.getThnlNm();
        String thnImgDownloadFlag = "thnImgDownloadFlag=" + fileVoParam.getThnImgDownloadFlag();
        String downloadImageKind = "DownloadImageKind=" + fileVoParam.getDownloadImageKind();
        
        result = atchFilePtcsNo + "&" + atchSeq + "&" + thnlNm + "&" + thnImgDownloadFlag + "&" + downloadImageKind;

        return result;
    }
}
