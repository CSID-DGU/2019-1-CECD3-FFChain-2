package kr.ac.dgu.icip.cmm.excelupload.service;
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

import java.io.InputStream;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.icip.cmm.excelupload.vo.TrgetSlctnRegistVO;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * 엑셀 서비스를 제공하는 인터페이스
 * <p>
 * <b>NOTE:</b> 엑셀 서비스를 제공하기 위해 여러 기능들을 선언하는 인터페이스이다.
 * @author 실행환경 개발팀 윤성종
 * @since 2009.06.01
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.01  윤성종           최초 생성
 *   2013.05.22  이기하           XSSF, SXSSF 형식 추가
 *
 * </pre>
 */
public interface DkExcelService extends EgovExcelService {
    
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
    public HSSFSheet getHSSFSheet(InputStream fileIn) throws Exception;
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
    public XSSFSheet getXSSFSheet(InputStream fileIn) throws Exception;    
    
    /**
     * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
     * 셀부터 업로드한다.
     * @param queryId
     * @param fileIn
     * @param start
     * @param commitCnt
     * @return
     * @throws Exception
     */
    public Integer uploadExcel( InputStream fileIn, int start,
            long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;
 

    /**
     * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
     * 셀부터 업로드한다.
     * @param queryId
     * @param fileIn
     * @param start
     * @param commitCnt
     * @return
     * @throws Exception
     */
    public Integer uploadXSSFExcel( InputStream fileIn, int start,
            long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;
    
    
    /**
     * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
     * 셀부터 업로드한다. commit할 건수를 입력한다.
     * @param queryId
     * @param sheet
     * @param start
     * @param commitCnt
     *        커밋 건수 단위
     * @return
     * @throws Exception
     */
    public Integer uploadExcel( HSSFSheet sheet, int start,
            long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;
   
    /**
     * xlsx 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
     * 셀부터 업로드한다. commit할 건수를 입력한다.
     * @param queryId
     * @param sheet
     * @param start
     * @param commitCnt
     *        커밋 건수 단위
     * @return
     * @throws Exception
     */
    public Integer uploadXSSFExcel( XSSFSheet sheet, int start,
            long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

    
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
  public int getExcelRowCount(InputStream fileIn) throws BaseException, Exception;

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
  public int getXSSExcelRowCount(InputStream fileIn) throws BaseException, Exception;


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
  public Integer uploadExcel( InputStream fileIn, short sheetIndex, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception;

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
  public Integer uploadXSSFExcel( InputStream fileIn, short sheetIndex, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws BaseException, Exception;
  
  /*** =========================================================================== ****/
  

  /**
   * 엑셀파일을 저장한다.
   * @param queryId
   * @param fileIn
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * 엑셀파일을 저장한다.
   * @param queryId
   * @param fileIn
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn, int start, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn, int start, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetIndex
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          short sheetIndex, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetIndex
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          short sheetIndex, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param sheetIndex
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          short sheetIndex, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param sheetIndex
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          short sheetIndex, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          String sheetName, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          String sheetName, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          String sheetName, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          String sheetName, int start, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 저장한다.
   * @param queryId
   * @param fileIn
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 저장한다.
   * @param queryId
   * @param fileIn
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;


  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetIndex
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          short sheetIndex, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetIndex
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          short sheetIndex, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          String sheetName, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          String sheetName, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @param start
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( InputStream fileIn,
          String sheetName, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param fileIn
   * @param sheetName
   * @param start
   * @param commitCnt
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( InputStream fileIn,
          String sheetName, int start, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO) throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param sheet
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( HSSFSheet sheet)
          throws Exception;

  /**
   * xlsx 엑셀파일을 업로드하여 DB에 일괄저장한다.
   * @param queryId
   * @param sheet
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( XSSFSheet sheet, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param sheet
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( HSSFSheet sheet, int start, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * xlsx 엑셀파일을 업로드하여 DB에 일괄저장한다. 업로드할 엑셀의 시작 위치를 정하여 지정한
   * 셀부터 업로드한다.
   * @param queryId
   * @param sheet
   * @param start
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( XSSFSheet sheet, int start, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * 엑셀파일을 업로드하여 DB에 일괄저장한다. commit할 건수를 입력한다.
   * @param queryId
   * @param sheet
   * @param commitCnt
   *        커밋 건수 단위
   * @return
   * @throws Exception
   */
  public Integer uploadExcel( HSSFSheet sheet, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  /**
   * xlsx 엑셀파일을 업로드하여 DB에 일괄저장한다. commit할 건수를 입력한다.
   * @param queryId
   * @param sheet
   * @param commitCnt
   *        커밋 건수 단위
   * @return
   * @throws Exception
   */
  public Integer uploadXSSFExcel( XSSFSheet sheet, long commitCnt, TrgetSlctnRegistVO trgetSlctnRegistVO)
          throws Exception;

  
  
}
