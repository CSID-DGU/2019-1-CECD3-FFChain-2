/*
 * Copyright 2008-2014 MOPAS(Ministry of Public Administration and Security).
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
package kr.ac.dgu.icip.cmm.excelupload.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 엑셀 서비스를 제공하는 인터페이스.
 * 
 * <p><b>NOTE:</b> 엑셀 서비스를 제공하기 위해 여러 기능들을 선언하는 인터페이스이다.</p>
 * 
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
 *   2014.05.14  이기하           XSSF형식 구분자 추가 및 workbook으로 변경
 *
 * </pre>
 */
public interface EgovExcelService {

	/**
	 * Workbook 객체를 생성하여 엑셀파일을 생성한다.
	 * 
	 * @param wb
	 * @param filepath
	 * @return
	 * @throws Exception
	 */
	public Workbook createWorkbook(Workbook wb, String filepath) throws Exception;

	/**
	 * 엑셀 Template를 로딩하여 엑셀파일을 생성한다.
	 * 
	 * @param templateName
	 * @return
	 * @throws Exception
	 */
	public Workbook loadExcelTemplate(String templateName) throws Exception;

	/**
	 * xlsx 엑셀 Template를 로딩하여 엑셀파일을 생성한다.
	 * 
	 * @param templateName
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public XSSFWorkbook loadExcelTemplate(String templateName, XSSFWorkbook type) throws Exception;

}
