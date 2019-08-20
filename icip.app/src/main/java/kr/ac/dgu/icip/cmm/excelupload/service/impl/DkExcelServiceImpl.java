/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.ac.dgu.icip.cmm.excelupload.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.base.cmm.util.DateUtil;
import kr.ac.dgu.base.cmm.util.EgovExcelUtil;
import kr.ac.dgu.base.cmm.util.StringUtil;
import kr.ac.dgu.icip.cmm.excelupload.dao.EgovExcelServiceDAO;
import kr.ac.dgu.icip.cmm.excelupload.service.DkExcelService;
import kr.ac.dgu.icip.cmm.excelupload.vo.TrgetSlctnExcelRegistVO;
import kr.ac.dgu.icip.cmm.excelupload.vo.TrgetSlctnRegistVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 엑셀 서비스를 처리하는 비즈니스 구현 클래스
 * <p>
 * <b>NOTE:</b> 엑셀 서비스를 제공하기 위해 구현한 클래스이다.
 * @author 실행환경 개발팀 윤성종
 * @since 2009.06.01
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  ----------- --------    ---------------------------
 *   2009.06.01  윤성종            최초 생성
 *   2013.05.22  이기하        XSSF, SXSSF형식 추가(xlsx 지원)
 *   2013.05.29  한성곤            mapBeanName property 추가 및 코드 정리
 *
 * </pre>
 */
@Service("dkExcelService")
public class DkExcelServiceImpl extends EgovExcelServiceImpl implements DkExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DkExcelServiceImpl.class);
    
    protected String mapClass;
    protected String mapBeanName;

    protected EgovExcelServiceDAO dao;
    protected EgovExcelServiceMapper excelBatchMapper;
    protected SqlMapClient sqlMapClient;
    protected SqlSessionTemplate sqlSessionTemplate;
    
    /**
     * mybatis 적용시 설정.
     *
     * @param sqlSessionTemplate
     * @throws Exception
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) throws Exception {
        this.sqlSessionTemplate = sqlSessionTemplate;
        excelBatchMapper = new EgovExcelServiceMapper(this.sqlSessionTemplate);
    }

    /**
     * Excel Cell과 VO를 mapping 구현 클래스
     *
     * @param mapClass
     * @throws Exception
     */
    public void setMapClass(String mapClass) throws BaseException {
        this.mapClass = mapClass;
        LOGGER.debug("mapClass : " + mapClass);

    }

    /**
     * Excel Cell과 VO를 mapping 구현 Bean name (mapClass보다 우선함)
     *
     * @param mapBeanName
     * @throws BaseException
     */
    public void setMapBeanName(String mapBeanName) throws BaseException {
        this.mapBeanName = mapBeanName;
        LOGGER.debug("mapBeanName : " + mapBeanName);

    }  
    
    /**
     * 엑셀파일을 읽어서 DB upload 한다.
     *
     * @param String queryId
     * @param InputStream fileIn
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    public Integer uploadExcel( InputStream fileIn, int start, long commitCnt,TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        try{
            HSSFWorkbook wb = loadWorkbook(fileIn);
            HSSFSheet sheet = wb.getSheetAt(0);
            return uploadExcel( sheet, start, commitCnt,trgetSlctnRegistVO);
        } catch (BaseException e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
    }
    
    /**
     * <pre>
     * 개요 : HSSFSheet 시트를 얻는다. 
     * </pre>
     * @Method Name : getHSSFSheet
     * @author : dgu
     * @date : 2018. 9. 12.
     * @param fileIn
     * @return
     * @throws Exception
     */ 	
    public HSSFSheet getHSSFSheet(InputStream fileIn) throws Exception {
        HSSFSheet sheet =  null;
        try{
            HSSFWorkbook wb = loadWorkbook(fileIn);
            sheet = wb.getSheetAt(0);
        } catch (BaseException e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
        return sheet;
    }
    
    /**
     * 엑셀파일을 읽어서 DB upload 한다.
     *
     * @param String queryId
     * @param InputStream fileIn
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    public Integer uploadXSSFExcel( InputStream fileIn, int start, long commitCnt,TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        try{
            XSSFWorkbook wb = loadXSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);

            return uploadXSSFExcel( sheet, start, commitCnt,trgetSlctnRegistVO);

        } catch (BaseException e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
    }
    
    /**
     * <pre>
     * 개요 : HSSFSheet 시트를 얻는다. 
     * </pre>
     * @Method Name : getHSSFSheet
     * @author : dgu
     * @date : 2018. 9. 12.
     * @param fileIn
     * @return
     * @throws Exception
     */     
    public XSSFSheet getXSSFSheet(InputStream fileIn) throws Exception {
        XSSFSheet sheet =  null;
        try{
            XSSFWorkbook wb = loadXSSFWorkbook(fileIn);
            sheet = wb.getSheetAt(0);
        } catch (BaseException e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
        return sheet;
    }
    
    /**
     * 엑셀Sheet을 읽어서 DB upload 한다.
     *
     * @param String queryId
     * @param HSSFSheet sheet
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.hssf.usermodel.HSSFSheet, int, long)
     */
    public Integer uploadExcel(HSSFSheet sheet, int start, long commitCnt,TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        final int tREAD_FIRST_COL = 0;
        
        LOGGER.debug("sheet.getPhysicalNumberOfRows() : " + sheet.getPhysicalNumberOfRows());

        
        Integer rowsAffected = 0;
        int  creatRowCnt = 0;
        try {

            long rowCnt = sheet.getPhysicalNumberOfRows();
            long cnt = (commitCnt == 0) ? rowCnt : commitCnt;

            LOGGER.debug("Runtime.getRuntime().totalMemory() : " + Runtime.getRuntime().totalMemory());
            LOGGER.debug("Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());

            long startTime = System.currentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            // 유효성 검증 후 일괄 인서트  
            // List 를 생성하여 담고 데이터베이스 배치로 처리한다. 

            List<TrgetSlctnExcelRegistVO> trgetSlctnExcelRegistVOList = new ArrayList<TrgetSlctnExcelRegistVO>();
            int cloidx = 0;
            for (int i = start; i < rowCnt && i < commitCnt; i++) {
                HSSFRow row = sheet.getRow(i);
                TrgetSlctnExcelRegistVO vo = new TrgetSlctnExcelRegistVO();
                vo.setTradeId(trgetSlctnRegistVO.getTradeId());
                vo.setUserId(trgetSlctnRegistVO.getUserid());
                
                cloidx = tREAD_FIRST_COL + 1;
                vo.setMemberId(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                String compNo = EgovExcelUtil.getStringValue(row.getCell(cloidx++));
                compNo = StringUtil.replace(compNo, "-", "");
                //vo.setCompNo(compNo);
                vo.setCompNo("");
                vo.setCompanyKor(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setPresidentKor(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactNm(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.seticipId(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactEmail(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactEmailYn("N"); //수신
                vo.setBirthDay(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactTel(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactTelYn("N"); //수신
                vo.setContactFax(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactFaxYn("N"); //수신
                vo.setContactCell(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactCellYn("N"); //수신
                if(
                        vo.getContactEmail().equals("")&&
                        vo.getContactFax().equals("")&&
                        vo.getContactCell().equals("")
                        ) {
                    LOGGER.debug("row is null : "+ vo);
                }else{
                    trgetSlctnExcelRegistVOList.add(vo);   
                }
                
            }
            LOGGER.debug("after Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());
            // 디비 배치 처리
            rowsAffected += excelBatchMapper.batchInsert("kr.ac.dgu.icip.cmm.excelupload.dao.TrgetSlctnExcelDAO.insertTrgetSlctnExcelTarget", trgetSlctnExcelRegistVOList);
            rowsAffected = trgetSlctnExcelRegistVOList.size();
            
            List<TrgetSlctnRegistVO> trgetSlctnRegistVOList = new ArrayList<TrgetSlctnRegistVO>();
            //인서트 에러를 막기위한 중복되지 않는 값으로 
            trgetSlctnRegistVO.setPoolSeq(DateUtil.getDatetimeString());
            trgetSlctnRegistVOList.add(trgetSlctnRegistVO);
            excelBatchMapper.batchInsertSelect("kr.ac.dgu.icip.cmm.excelupload.dao.TrgetSlctnExcelDAO.insertMktTargetListExcel", trgetSlctnRegistVOList);
            
            LOGGER.debug("rowsAffected ====>" + rowsAffected);

            LOGGER.debug("batchInsert time is " + (System.currentTimeMillis() - startTime));
        } catch (NumberFormatException e) {
            LOGGER.error(e.toString(),e);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error(e.toString(),e);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
        LOGGER.debug("uploadExcel result count is " + rowsAffected);

        return rowsAffected;
    }

    /**
     * 엑셀Sheet을 읽어서 DB upload 한다.
     *
     * @param String queryId
     * @param HSSFSheet sheet
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadSXSSFExcel(java.lang.String, org.apache.poi.xssf.usermodel.XSSFSheet, int, long)
     */
    public Integer uploadXSSFExcel( XSSFSheet sheet, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        final int tREAD_FIRST_COL = 0;
        
        LOGGER.debug("sheet.getPhysicalNumberOfRows() : " + sheet.getPhysicalNumberOfRows());

        
        Integer rowsAffected = 0;
        int  creatRowCnt = 0;

        try {

            long rowCnt = sheet.getPhysicalNumberOfRows();
            long cnt = (commitCnt == 0) ? rowCnt : commitCnt;

            LOGGER.debug("Runtime.getRuntime().totalMemory() : " + Runtime.getRuntime().totalMemory());
            LOGGER.debug("Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());

            long startTime = System.currentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            // 유효성 검증 후 일괄 인서트  
            // List 를 생성하여 담고 데이터베이스 배치로 처리한다. 

            List<TrgetSlctnExcelRegistVO> trgetSlctnExcelRegistVOList = new ArrayList<TrgetSlctnExcelRegistVO>();
            int cloidx = 0;
            for (int i = start; i < rowCnt && i < commitCnt; i++) {
                XSSFRow row = sheet.getRow(i);
                TrgetSlctnExcelRegistVO vo = new TrgetSlctnExcelRegistVO();
                vo.setTradeId(trgetSlctnRegistVO.getTradeId());
                vo.setUserId(trgetSlctnRegistVO.getUserid());
                vo.setPoolSeq(trgetSlctnRegistVO.getPoolSeq());
                
                cloidx = tREAD_FIRST_COL + 1;
                vo.setMemberId(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));

                String compNo = EgovExcelUtil.getStringValue(row.getCell(cloidx++));
                compNo = StringUtil.replace(compNo, "-", "");
                //vo.setCompNo(compNo);
                vo.setCompNo("");
                
                vo.setCompanyKor(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setPresidentKor(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactNm(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.seticipId(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactEmail(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactEmailYn("N"); //수신
                vo.setBirthDay(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactTel(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactTelYn("N"); //수신
                vo.setContactFax(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactFaxYn("N"); //수신
                vo.setContactCell(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
                vo.setContactCellYn("N"); //수신
                
                if(
                        vo.getContactEmail().equals("")&&
                        vo.getContactFax().equals("")&&
                        vo.getContactCell().equals("")
                        ) {
                    LOGGER.debug("row is null : "+ vo);
                }else{
                    trgetSlctnExcelRegistVOList.add(vo);   
                }
                
            }
            LOGGER.debug("after Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());
            // 디비 배치 처리 
            rowsAffected += excelBatchMapper.batchInsert("kr.ac.dgu.icip.cmm.excelupload.dao.TrgetSlctnExcelDAO.insertTrgetSlctnExcelTarget", trgetSlctnExcelRegistVOList);
            rowsAffected = trgetSlctnExcelRegistVOList.size();

            
            List<TrgetSlctnRegistVO> trgetSlctnRegistVOList = new ArrayList<TrgetSlctnRegistVO>();
            trgetSlctnRegistVOList.add(trgetSlctnRegistVO);
            excelBatchMapper.batchInsertSelect("kr.ac.dgu.icip.cmm.excelupload.dao.TrgetSlctnExcelDAO.insertMktTargetListExcel", trgetSlctnRegistVOList);
            
            LOGGER.debug("rowsAffected ====>" + rowsAffected);

            LOGGER.debug("batchInsert time is " + (System.currentTimeMillis() - startTime));
        } catch (NullPointerException e) {
            LOGGER.error(e.toString(),e);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error(e.toString(),e);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }

        //LOGGER.debug("uploadExcel result count is " + rowsAffected);

        return rowsAffected;
    }


    /**
     * 정규식 패턴 검증
     * @param pattern
     * @param str
     * @return
     */
    public static boolean checkPattern(String pattern, String str){
         boolean okPattern = false;
         String regex = null;

         pattern = pattern.trim();

         //숫자 체크
         if(StringUtils.equals("num", pattern)){
          regex = "^[0-9]*$";
         }

         //영문 체크

         //이메일 체크
         if(StringUtils.equals("email", pattern)){
          regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
         }

         //전화번호 체크
         if(StringUtils.equals("tel", pattern)){
          regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
         }

         //휴대폰번호 체크
         if(StringUtils.equals("phone", pattern)){
          regex = "^01(?:0|1[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
         }

         okPattern = Pattern.matches(regex, str);
         return okPattern;
    }
  
    /**
     * <pre>
     * 개요 : 엑셀파일을 읽어서 행의 개수를 돌려준다.
     * </pre>
     * @Method Name : getExcelRowCount
     * @author : dgu
     * @date : 2018. 9. 11.
     * @param fileIn
     * @return
     * @throws BaseException
     * @throws Exception
     */     
    public int getExcelRowCount(InputStream fileIn) throws BaseException, Exception {
        try{
            HSSFWorkbook wb = loadWorkbook(fileIn);
            HSSFSheet sheet = wb.getSheetAt(0);
            return sheet.getPhysicalNumberOfRows();
        } catch (BaseException e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
    }

    /**
     * <pre>
     * 개요 : 엑셀파일을 읽어서 행의 개수를 돌려준다.
     * </pre>
     * @Method Name : getXSSExcelRowCount
     * @author : dgu
     * @date : 2018. 9. 11.
     * @param fileIn
     * @return
     * @throws BaseException
     * @throws Exception
     */     
    public int getXSSExcelRowCount(InputStream fileIn) throws BaseException, Exception {
        try{
            XSSFWorkbook wb = loadXSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);

            return sheet.getPhysicalNumberOfRows();
            
        } catch (BaseException e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch (Exception e) {
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        }
    } 

    /**
     * 엑셀파일을 읽어서 DB upload 한다. sheet의 인덱스값으로 upload할 sheet를 지정한다.
     *
     * @param String queryId
     * @param InputStream fileIn
     * @param short sheetIndex
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    public Integer uploadExcel( InputStream fileIn, short sheetIndex, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        HSSFWorkbook wb = loadWorkbook(fileIn);
        HSSFSheet sheet = wb.getSheetAt(sheetIndex);

        return uploadExcel( sheet, start, commitCnt,  trgetSlctnRegistVO);
    }

    /**
     * 엑셀파일을 읽어서 DB upload 한다. sheet의 인덱스값으로 upload할 sheet를 지정한다.
     *
     * @param String queryId
     * @param InputStream fileIn
     * @param short sheetIndex
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    public Integer uploadXSSFExcel( InputStream fileIn, short sheetIndex, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        XSSFWorkbook wb = loadXSSFWorkbook(fileIn);
        XSSFSheet sheet = wb.getSheetAt(sheetIndex);

        return uploadXSSFExcel( sheet, start, commitCnt,  trgetSlctnRegistVO);
    }    
    /*** ================================================================= ****/
    
    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.hssf.usermodel.HSSFSheet, int)
     */
    public Integer uploadExcel( HSSFSheet sheet, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( sheet, start, 0,   trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.xssf.usermodel.XSSFSheet, int)
     */
    public Integer uploadXSSFExcel( XSSFSheet sheet, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( sheet, start, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.hssf.usermodel.HSSFSheet, long)
     */
    public Integer uploadExcel( HSSFSheet sheet, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( sheet, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.xssf.usermodel.XSSFSheet, long)
     */
    public Integer uploadXSSFExcel( XSSFSheet sheet, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( sheet, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.hssf.usermodel.HSSFSheet)
     */
    public Integer uploadExcel( HSSFSheet sheet, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( sheet, 0, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, org.apache.poi.hssf.usermodel.HSSFSheet)
     */
    public Integer uploadXSSFExcel( XSSFSheet sheet, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( sheet, 0, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, int)
     */
    public Integer uploadExcel( InputStream fileIn, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, start, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, int)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, start, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream)
     */
    public Integer uploadExcel( InputStream fileIn, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream)
     */
    public Integer uploadExcel( InputStream fileIn, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, 0, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, 0, 0,  trgetSlctnRegistVO);
    }
    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, short, int)
     */
    public Integer uploadExcel( InputStream fileIn, short sheetIndex, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, sheetIndex, start, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, short, int)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, short sheetIndex, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, sheetIndex, start, 0, trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, short, long)
     */
    public Integer uploadExcel( InputStream fileIn, short sheetIndex, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, sheetIndex, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, short, long)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, short sheetIndex, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, sheetIndex, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, short)
     */
    public Integer uploadExcel( InputStream fileIn, short sheetIndex, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, sheetIndex, 0, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, short)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, short sheetIndex, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, sheetIndex, 0, 0,  trgetSlctnRegistVO);
    }

    /**
     * 엑셀파일을 읽어서 DB upload 한다. sheet의 명으로 upload할 sheet를 지정한다.
     *
     * @param String queryId
     * @param InputStream fileIn
     * @param String sheetName
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String, int, long)
     */
    public Integer uploadExcel( InputStream fileIn, String sheetName, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        HSSFWorkbook wb = loadWorkbook(fileIn);
        HSSFSheet sheet = wb.getSheet(sheetName);

        return uploadExcel( sheet, start, commitCnt, trgetSlctnRegistVO);
    }

    /**
     * 엑셀파일을 읽어서 DB upload 한다. sheet의 명으로 upload할 sheet를 지정한다.
     *
     * @param String queryId
     * @param InputStream fileIn
     * @param String sheetName
     * @param int start
     * @param long commitCnt
     * @return
     * @throws Exception
     */
    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String, int, long)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, String sheetName, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        XSSFWorkbook wb = loadXSSFWorkbook(fileIn);
        XSSFSheet sheet = wb.getSheet(sheetName);

        return uploadXSSFExcel( sheet, start, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String, int)
     */
    public Integer uploadExcel( InputStream fileIn, String sheetName, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, sheetName, start, 0, trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String, int)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, String sheetName, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, sheetName, start, 0,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String, long)
     */
    public Integer uploadExcel( InputStream fileIn, String sheetName, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, sheetName, 0, commitCnt, trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String, long)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, String sheetName, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, sheetName, 0, commitCnt,  trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String)
     */
    public Integer uploadExcel( InputStream fileIn, String sheetName, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadExcel( fileIn, sheetName, 0, 0, trgetSlctnRegistVO);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#uploadExcel(java.lang.String, java.io.InputStream, java.lang.String)
     */
    public Integer uploadXSSFExcel( InputStream fileIn, String sheetName, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception {
        return uploadXSSFExcel( fileIn, sheetName, 0, 0, trgetSlctnRegistVO);
    }
/**** =============================================================== ***/


    @Override
    public Integer uploadExcel( HSSFSheet sheet) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
}
