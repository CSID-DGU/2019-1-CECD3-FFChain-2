package kr.ac.dgu.icip.cmm.excelupload.service.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.icip.cmm.excelupload.service.DkExcelService;
import kr.ac.dgu.icip.cmm.excelupload.service.TrgetSlctnExcelService;
import kr.ac.dgu.icip.cmm.excelupload.vo.TrgetSlctnRegistVO;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : TrgetSlctnExcelServiceImpl.java
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
@Service("trgetSlctnExcelService")
public class TrgetSlctnExcelServiceImpl extends EgovAbstractServiceImpl implements TrgetSlctnExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrgetSlctnExcelServiceImpl.class);

    @Resource(name = "messageSource")
    private MessageSource egovMessageSource;


    @Resource(name = "dkExcelService")
    private DkExcelService dkExcelService;

    /**
     * txManager
     */
    @Resource(name = "txManager")
    private PlatformTransactionManager txManager;
    
    
    /**
     * 커및 단위
     */
    private final int commitCnt = 5000;
    
    /**
     *  엑셀파일을 등록한다.
     * @param
     * @throws Exception
     */
    public int saveExcelLandLoad(InputStream fis, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception {
        
        int ret = -1;
//        
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//      TransactionStatus status = txManager.getTransaction(def);
        final int tREAD_FIRST_ROW = 6;
        try{
            //엑셀 등록
            HSSFSheet sheet = dkExcelService.getHSSFSheet(fis);
            int rowcount = sheet.getPhysicalNumberOfRows();
            for (int i = tREAD_FIRST_ROW; i < rowcount; i=i+commitCnt) {
                int fret = dkExcelService.uploadExcel(sheet, i, (long)( i+commitCnt) ,trgetSlctnRegistVO);
                if(fret > 0) {
                    ret += fret;
                    continue;
                }
                else break;
            }
            //ret += dkExcelService.insertMktTargetList(trgetSlctnRegistVO);
           if(ret>0){
               //txManager.commit(status);
           }else{
              throw new BaseException(egovMessageSource,"errors.goods.excelFile"); 
           }
        } catch(TransactionException e){
            //txManager.rollback(status);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch(Exception e){
            //txManager.rollback(status);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } finally {
            
//            if (!status.isCompleted()) {
//                txManager.rollback(status);
//            }
        }        
        return ret;   
    }

    /**
     * 엑셀파일을 등록한다.
     * @param
     * @throws Exception
     */
    public int saveExcelLandLoadXSS(InputStream fis , TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception {
        
        int ret = -1;
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        final int tREAD_FIRST_ROW = 6;
//        TransactionStatus status = txManager.getTransaction(def);
        try{
            //엑셀 등록
            XSSFSheet sheet = dkExcelService.getXSSFSheet(fis);
            int rowcount = sheet.getPhysicalNumberOfRows();
            for (int i = tREAD_FIRST_ROW; i < rowcount; i=i+commitCnt) {
                int fret = dkExcelService.uploadXSSFExcel( sheet, i, (long) ( i+commitCnt),trgetSlctnRegistVO);
                if(fret > 0) {
                    ret += fret;
                    continue;
                }
                else break;
            }
           //ret += dkExcelService.insertMktTargetList(trgetSlctnRegistVO);
           if(ret>0){
               //txManager.commit(status);
           }else{
              throw new BaseException(egovMessageSource,"errors.goods.excelFile"); 
           }
        } catch(TransactionException e){
            //txManager.rollback(status);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } catch(Exception e){
            //txManager.rollback(status);
            if(e.getMessage() == null){
                throw new BaseException(egovMessageSource, "errors.goods.excelFile", new String[]{});
            }else{
                throw new BaseException(e.getMessage());
            }
        } finally {
//            if (!status.isCompleted()) {
//                txManager.rollback(status);
//            }
        }        
        return ret;   
        
    }


}
