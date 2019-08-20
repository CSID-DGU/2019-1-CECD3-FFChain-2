package kr.ac.dgu.base.cmm.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.base.cmm.vo.FileVO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : DateUtil.java
 * @Description :
 * 공통 Date Utility
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.        DGU         신규생성
 * </pre>
 */
@Component("fileMngUtil")
public class FileMngUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileMngUtil.class);

    /**  Buffer size  */
    public static final int BUFF_SIZE = 2048;

    /**  propertyService  */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "messageSource")
    private MessageSource egovMessageSource;
 
    
    /**
     * 첨부파일을 저장하고 파일 정보를 취득한다.
     *
     * @param files	저장할 파일정보가 담긴 Map<String, MultipartFile>
     * @param type	type에 따라서 저장경로를 다르게 하기 위해 받는다.
     * @return	List<FileVO>	저장된 FileVO List
     * @throws Exception
     */
    public FileVO uploadFile(FileVO fileVo) throws Exception {

    	String storePathString = fileVo.getFilePthCntn();
    	LOGGER.debug("storePathString : " + storePathString);

    	if (!validateFilePath(storePathString)) {
    	    throw new BaseException(egovMessageSource, "lib.common.upFilePath.error"); //잘못된 경로입니다.
    	}
    	if ( StringUtils.isEmpty(storePathString)){
    	    throw new BaseException(egovMessageSource, "lib.common.upFile.error"); //파일 업로드 처리중 에러가 발생했습니다!
    	}
    	File saveFolder = new File(storePathString);

    	if (!saveFolder.exists() || saveFolder.isFile()) {
    	    saveFolder.mkdirs();
    	}

    	String filePath = storePathString + File.separator + fileVo.getPhysFileNm();

    	fileVo.getFileItem().write(new File(filePath));

    	return fileVo;
    }

    /** 첨부파일을 삭제한다
     * @param fileVo
     * @throws Exception
     */
    public void deleteFile(FileVO fileVo) throws Exception {
        String storePathString = fileVo.getFilePthCntn();

        if ( StringUtils.isEmpty(storePathString)){
            throw new BaseException(egovMessageSource, "lib.common.delFile.error"); //파일 삭제 처리중 에러가 발생했습니다.
        }

        String filePath = storePathString + File.separator + fileVo.getPhysFileNm();

        File file = new File(filePath);
        file.delete();

        //썸네일 삭제
        if (!StringUtil.isEmpty(fileVo.getThnlNm())) {
            filePath = storePathString + File.separator + fileVo.getThnlNm();
            file = new File(filePath);
            file.delete();
        }
        //리스트 삭제
        if (!StringUtil.isEmpty(fileVo.getLstFileNm())) {
            filePath = storePathString + File.separator + fileVo.getLstFileNm();
            file = new File(filePath);
            file.delete();
        }
        //상세 삭제
        if (!StringUtil.isEmpty(fileVo.getDtlFileNm())) {
            filePath = storePathString + File.separator + fileVo.getDtlFileNm();
            file = new File(filePath);
            file.delete();
        }
        //확장 삭제
        if (!StringUtil.isEmpty(fileVo.getElgmFileNm())) {
            filePath = storePathString + File.separator + fileVo.getElgmFileNm();
            file = new File(filePath);
            file.delete();
        }
    }

    /** 첨부파일을 변환 , 분류한다.
     * @param filesMap
     * @param docKind
     * @return
     * @throws Exception
     */
    public List<FileVO> getConvertFileList(Map<String, MultipartFile> filesMap) throws Exception {
        List<FileVO> resultList = new ArrayList<FileVO>();
        FileVO fileVo = null;
        Entry<String, MultipartFile> entry = null;
        String orginFileName = "";
        CommonsMultipartFile commonFile = null;
        DiskFileItem file = null;
        int idx = 0;
        if (!filesMap.isEmpty()) {
            Iterator<Entry<String, MultipartFile>> fileItr = filesMap.entrySet().iterator();
            while (fileItr.hasNext()) {

                fileVo = new FileVO();

                entry = fileItr.next();

                commonFile = (CommonsMultipartFile)entry.getValue();
                orginFileName = commonFile.getOriginalFilename();

                file = (DiskFileItem)commonFile.getFileItem();
                LOGGER.debug("file name : " + orginFileName);
                if ( !StringUtils.isEmpty(orginFileName)){
                    fileVo.setFileItem(file);
                    fileVo.setAtchFileNm(orginFileName);
                    fileVo.setHtmlFieldName(file.getFieldName()); 
                    resultList.add(fileVo);
                    idx++;
                }

            }
        }

        return resultList;
    }
    /**
     * 링크파일이름을 반환한다.
     *
     * @param fileVO
     * @return
     */
    public String getLinkFileName(FileVO fileVO) {
        String eContentFileName = "";
        if(fileVO != null) {
            String filePthCntn = fileVO.getFilePthCntn();
            String physFileNm = fileVO.getPhysFileNm();
            String baseLink = propertiesService.getString("icip.root.path");
            filePthCntn = StringUtils.substringAfter(filePthCntn, baseLink);
            filePthCntn = baseLink + filePthCntn + "/";
            eContentFileName = filePthCntn + physFileNm;
        }
        return eContentFileName;
    }

    /**
     * 파일확장자를 반환한다.
     * ex) abc.jpg -> jpg
     * @param fileVO
     * @return
     */
    public String getFileExtention(FileVO fileVO) {
        String fileExtetion = "";
        if(fileVO != null) {
            String atchFileNm = fileVO.getAtchFileNm();
            if(StringUtils.isNoneEmpty(atchFileNm)) {
                fileExtetion = StringUtils.substringAfterLast(atchFileNm, ".");
            }
        }
        return fileExtetion;
    }

    /**
     * 잘못된 파일 경로 지정인지 체크한다.
     * @param path
     * @return
     */
    public static boolean validateFilePath(String path) {
        StringBuffer sb = new StringBuffer(path);

        if (StringUtils.contains(sb, "./") || StringUtils.contains(sb, "..")) {
            return false;
        }

        return true;
    }

    /**
     * 경로에 포함 불가 문자를 제거한다.
     * @param path
     * @return
     */
    public static String replaceFilePath(String path) {
        String destPath = path;

        destPath = StringUtils.replacePattern(destPath, "\\.\\/", "");
        destPath = StringUtils.replacePattern(destPath, "\\.\\.", "");

        return destPath;
    }

    /**
     * 파일을 복사한다.
     *
     * @param src 복사대상파일
     * @param dest 새파일
     * @throws IOException
     */
    public boolean copyFile(String src, String dest) throws IOException {
        if (!FileMngUtil.validateFilePath(src)) {
            throw new BaseException(egovMessageSource, "lib.common.upFilePath.error"); //잘못된 경로입니다.
        }
        if (!FileMngUtil.validateFilePath(dest)) {
            throw new BaseException(egovMessageSource, "lib.common.upFilePath.error"); //잘못된 경로입니다.
        }

        File srcFile = new File(src);
        File destFile = new File(dest);

        if( srcFile.length() > 0 ) {
            FileUtils.copyFile(srcFile, destFile);
        }
        else {
            return false;
        }

        return true;
    }
}
