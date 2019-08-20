package kr.ac.dgu.icip.system.service;

import java.util.List;

import kr.ac.dgu.base.cmm.vo.FileVO;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @Class Name : CommonMnmtService.java
 * @Description : CommonMnmtService
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
public interface CommonMnmtService {

    /** 첨부파일 정보를 저장한다
     * @param multiRequest MultipartHttpServletRequest
     * @param filePtcsNo 첨부파일내역번호
     * @param path 첨부파일경로
     * @return filePtcsNo 첨부파일내역번호
     * @throws Exception
     */
    List<FileVO> saveAtchFiles(MultipartHttpServletRequest multiRequest,  String path) throws Exception;
    
    public List<FileVO> saveBsnsreqstregistAtchFiles(MultipartHttpServletRequest multiRequest, String path) throws Exception;
    
}
