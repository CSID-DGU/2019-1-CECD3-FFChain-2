package kr.ac.dgu.icip.cmm.excelupload.service;

import java.io.InputStream;

import kr.ac.dgu.icip.cmm.excelupload.vo.TrgetSlctnRegistVO;

/**
 * @Class Name : TrgetSlctnExcelService.java
 * @Description : 엑셀 등록
 * 
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
 *  2019. 3. 29.        DGU         신규생성
 * </pre>
 */
public interface TrgetSlctnExcelService {

    /**
     * 엑셀파일을 등록한다.
     * @param zip
     * @throws Exception
     */
    int saveExcelLandLoad(InputStream fis, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

    /**
     * 엑셀파일을 등록한다.
     * @param zip
     * @throws Exception
     */
    int saveExcelLandLoadXSS(InputStream fis, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;
}