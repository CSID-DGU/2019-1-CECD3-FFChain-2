package kr.ac.dgu.icip.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.util.FileMngUtil;
import kr.ac.dgu.base.cmm.util.StringUtil;
import kr.ac.dgu.base.cmm.vo.FileVO;
import kr.ac.dgu.icip.system.service.CommonMnmtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Class Name : SystemInfoServiceImpl.java
 * @Description : System 정보 생성
 * @author 동국대학교 ICIP
 * @since 2019. 03. 06.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 23.     DGU         신규생성
 * </pre>
 */
@Service("commonMnmtService")
public class CommonMnmtServiceImpl implements CommonMnmtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMnmtServiceImpl.class);
    
    /** fileMngUtil */
    @Resource(name = "fileMngUtil")
    private FileMngUtil fileMngUtil;
 
    /**
     * 첨부파일 정보를 저장한다
     * @param multiRequest MultipartHttpServletRequest
     * @param filePtcsNo 첨부파일내역번호
     * @throws Exception
     */
    @Override
    public List<FileVO> saveAtchFiles(MultipartHttpServletRequest multiRequest, String path) throws Exception {
        LOGGER.debug("ImCommonMnmtServiceImpl saveAtchFiles start.. ");
        // 첨부파일 저장(Insert)
        final Map<String, MultipartFile> filesMap = multiRequest.getFileMap();

        List<FileVO> fileList = fileMngUtil.getConvertFileList(filesMap);
        
        LOGGER.debug("ImCommonMnmtServiceImpl saveAtchFiles fileList.size :  "+fileList.size());

        String fileExt = "";
        String physFileNm = "";
        int tmpSeq = 0;
        for (FileVO fileVo : fileList) {

            fileVo.setFilePthCntn(path);                                                // 파일경로 -  yyyy / Mmdd
            fileVo.setFileSizeVal(Long.toString(fileVo.getFileItem().getSize()));       // 파일용량

            fileExt = fileMngUtil.getFileExtention(fileVo);
            physFileNm = StringUtil.getTimeStamp() + "_" + (++tmpSeq);
            fileVo.setPhysFileNm(physFileNm +  "." + fileExt.toLowerCase());
            fileVo = upload(fileVo);

        }

        return fileList;
    }

    /**
     * 첨부파일을 업로드 하고 정보를 등록한다
     *
     * @param files
     * @param fileParamVo
     * @return
     * @throws Exception
     */
    public FileVO upload(FileVO fileVoParam) throws Exception {

        FileVO fileVo = uploadFile(fileVoParam); // write files

        return fileVo; // insert into db
    }
    /**
     * 첨부파일을 업로드 한다
     *
     * @return
     * @throws Exception
     */
    public FileVO uploadFile(FileVO fileVo) throws Exception {

        return fileMngUtil.uploadFile(fileVo);
    }    
    
    /**
     * 첨부파일을 업로드 한다
     *
     * @return
     * @throws Exception
     */
    public List<FileVO> saveBsnsreqstregistAtchFiles(MultipartHttpServletRequest multiRequest, String path) throws Exception {
        LOGGER.debug("ImCommonMnmtServiceImpl saveBsnsreqstregistAtchFiles start.. ");
        // 첨부파일 저장(Insert)
        final Map<String, MultipartFile> filesMap = multiRequest.getFileMap();

        List<FileVO> fileList = fileMngUtil.getConvertFileList(filesMap);

        for (FileVO fileVo : fileList) {
            fileVo.setFilePthCntn(path);                                                // 파일경로 -  yyyy / Mmdd
            fileVo.setFileSizeVal(Long.toString(fileVo.getFileItem().getSize()));       // 파일용량
            fileVo = upload(fileVo);
        }

        return fileList;
    }
}
