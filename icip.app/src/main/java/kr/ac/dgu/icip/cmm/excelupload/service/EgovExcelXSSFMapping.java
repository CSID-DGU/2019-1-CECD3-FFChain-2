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

import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * xlsx 엑셀파일의 DB업로드용 VO매핑 클래스
 * <p>
 * <b>NOTE:</b> 엑셀파일의 DB 업로드 기능을 제공하기 위한 사용자 VO 매핑
 * 추상클래스이다.
 * @author 유지보수팀 이기하
 * @since 2013.05.22
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2013.05.22  이기하           최초 생성
 *
 * </pre>
 */
public abstract class EgovExcelXSSFMapping {

    /**
     * xlsx 엑셀파일의 DB 업로드를 위한 사용자 VO 매핑 메소드
     * @param row
     * @return
     * @throws Exception
     */
    public abstract Object mappingColumn(XSSFRow row) throws Exception;
}
