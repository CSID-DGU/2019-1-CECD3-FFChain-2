package kr.ac.dgu.icip.cmm.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.util.FileMngUtil;
import kr.ac.dgu.base.cmm.util.HttpUtil;

import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name :DownloadController.java
 * @Description : 공통 다운로드 Controller
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 31.     DGU         신규생성
 * </pre>
 */
@Controller("DownloadController")
public class DownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

    /** propertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** fileMngUtil */
    @Resource(name = "fileMngUtil")
    private FileMngUtil fileMngUtil;
    
    /**
     * 다운로드
     * 
     * @param FileVO 다운로드될 file(실제 저장된 파일경로와 파일명)
     * @param FileVO 다운로드될 원 file명
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     */
    @RequestMapping(value = { "/cmm/cmmn/downloadFile.do" })
    public String downloadFile(String targetPathName,String fileName,String outFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String targetPhysPath = propertiesService.getString(targetPathName);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("downloadFile().fileVO : " + targetPathName+"|"+targetPhysPath+"|"+fileName+"==>"+outFileName);
        }
        File uFile = new File(targetPhysPath, fileName);
        int fSize = (int) uFile.length();
        int drmFileSize = 0;
        if (fSize > 0) {
            BufferedInputStream in = null;
            FileInputStream fi = null;
            try{
                if(outFileName==null||outFileName.equals(""))  outFileName = fileName;
                drmFileSize = (int) uFile.length();
                fi = new FileInputStream(uFile);
                in = new BufferedInputStream(fi);
                HttpUtil.makeAttachmentHeaderForFile(request, response, outFileName, drmFileSize);
                IOUtils.copy(in, response.getOutputStream());
                
                response.getOutputStream().flush();
                
            } catch(FileNotFoundException e){
                LOGGER.error(e.getMessage());
            } catch(IOException e){
                LOGGER.error(e.getMessage());
            } finally {
                if(fi != null){
                    fi.close();
                }
                if(in != null){
                    in.close();
                }
                if(response.getOutputStream() != null){
                    response.getOutputStream().close();
                }
            }
            
        } else {
            // setContentType을 프로젝트 환경에 맞추어 변경
            response.setContentType("application/x-msdownload");
            PrintWriter printwriter = response.getWriter();
            printwriter.println("<html>");
            printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fileName + "</h2>");
            printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
            printwriter.println("<br><br><br>&copy; 동국대학교");
            printwriter.println("</html>");
            printwriter.flush();
            printwriter.close();
        }

        return null;
    }

}
