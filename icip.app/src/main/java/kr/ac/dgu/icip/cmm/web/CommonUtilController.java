package kr.ac.dgu.icip.cmm.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : BindingInitializer.java
 * @Description :
 * CommonUtilController
 * @author 동국대학교 ICIP
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
@Controller("CommonUtilController")
public class CommonUtilController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtilController.class);

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/**
     * 휴일 조회
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/cmmn/selectHoliDay.do")
    public ModelAndView selectHoliDay(@RequestParam String searchYear, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (LOGGER.isDebugEnabled()) {
           LOGGER.debug("휴일 조회 시작");
           LOGGER.debug("searchYear : " + searchYear);
        }
        ModelAndView mav = new ModelAndView("jsonView");

        List<EgovMap> resultList = null; // commonUtilService.selectHoliDay(searchYear);

        mav.addObject("holidayList" , resultList);
        mav.addObject("setcal" , "notset");

        return mav;
    }

    /**
     * <pre>
     * 개요 : 파일 다운로드
     * </pre>
     * @Method Name : fileDownload
     * @author : Administrator
     * @date : 2019. 3. 30.
     * @param filePath
     * @param fileName
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/cmmn/file/download.do")
    public void fileDownload(@RequestParam String fileName, @RequestParam String realName, @RequestParam(required = false) String filePath,
            @RequestParam(required = false) String gu, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (LOGGER.isDebugEnabled()) {
           LOGGER.debug("파일다운로드 시작");
        }

        if(fileName.indexOf("../") > 0 || realName.indexOf("../") > 0){
            throw new Exception("허용되지 않는 확장자입니다.");
        }

        response.reset();
        String realFile = "";//request.getSession().getServletContext().getRealPath("/jsp/") + "/fee/down/" + realName;

        String strPath = "";

        if(StringUtils.isEmpty(filePath)) {
            filePath = strPath;
        }

        realFile = filePath + realName;
        System.out.println("realFile: " + realFile);

        realName = java.net.URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/x-ms-download");
        String browser = request.getHeader("User-Agent");
        if (browser.indexOf("MSIE 5.5") != -1)
            response.setHeader("Content-Disposition", " filename=" + realName);
        else
            response.setHeader("Content-Disposition", "attachment; filename=" + realName);

        InputStream is = null;
        OutputStream buffOs = null;

        try {
            buffOs = new BufferedOutputStream(response.getOutputStream());

            byte[] buf;
            int byteRead;

            buf = new byte[1024];
            while ((byteRead = is.read(buf)) != -1) {
                buffOs.write(buf, 0, byteRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (buffOs != null) {
                    buffOs.close();
                }
            } catch (IOException ie) { // Do Nothing
            }
        }

    }
}
