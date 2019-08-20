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
package kr.ac.dgu.icip.cmm.excelupload.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.exception.BaseException;
import kr.ac.dgu.icip.cmm.excelupload.service.EgovExcelService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

import egovframework.rte.fdl.filehandling.EgovFileUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * 엑셀 서비스를 처리하는 비즈니스 구현 클래스.
 * 
 * <p><b>NOTE:</b> 엑셀 서비스를 제공하기 위해 구현한 클래스이다.</p>
 * 
 * @author 실행환경 개발팀 윤성종
 * @since 2009.06.01
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  ----------- --------    ---------------------------
 *   2009.06.01  윤성종			최초 생성
 *   2013.05.22  이기하 		XSSF, SXSSF형식 추가(xlsx 지원)
 *   2013.05.29  한성곤			mapBeanName property 추가 및 코드 정리
 *   2014.05.14  이기하			코드 refactoring 및 mybatis 서비스 추가
 *
 * </pre>
 */
public class EgovExcelServiceImpl implements EgovExcelService, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovExcelServiceImpl.class);

    @Resource(name = "egovIdGnrService")
    protected EgovIdGnrService egovIdGnrService;

    @Resource(name = "messageSource")
    protected MessageSource egovMessageSource;

    protected MessageSource messageSource;
    protected ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.messageSource = (MessageSource) applicationContext.getBean("messageSource");
    }

    /**
     * @return the messageSource
     */
    protected MessageSource getMessageSource() {
        return messageSource;
    }

    /*public void setSqlMapClient(SqlMapClient sqlMapClient) throws Exception {
        this.sqlMapClient = sqlMapClient;
        dao = new EgovExcelServiceDAO(this.sqlMapClient);
    }*/
 

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#loadExcelTemplate(java.lang.String)
     */
    public HSSFWorkbook loadExcelTemplate(String templateName) throws BaseException, FileNotFoundException, IOException {

        FileInputStream fileIn = new FileInputStream(templateName);
        HSSFWorkbook wb = null;

        LOGGER.debug("EgovExcelServiceImpl.loadExcelTemplate : templatePath is " + templateName);

        try {
            LOGGER.debug("ExcelServiceImpl loadExcelTemplate ...");

            POIFSFileSystem fs = new POIFSFileSystem(fileIn);
            wb = new HSSFWorkbook(fs);

        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadExcelTemplate" }, Locale.getDefault()), e);
        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadExcelTemplate" }, Locale.getDefault()), e);
        } finally {
            LOGGER.debug("ExcelServiceImpl loadExcelTemplate end...");
            fileIn.close();
        }
        return wb;

    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#loadXSSFExcelTemplate(java.lang.String)
     */
    public XSSFWorkbook loadXSSFExcelTemplate(String templateName)
            throws BaseException, FileNotFoundException, IOException {

        FileInputStream fileIn = new FileInputStream(templateName);
        XSSFWorkbook wb = null;

        LOGGER.debug("EgovExcelServiceImpl.loadXSSFExcelTemplate : templatePath is " + templateName);

        try {
            LOGGER.debug("ExcelServiceImpl loadXSSFExcelTemplate ...");

            wb = new XSSFWorkbook(fileIn);
        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadXSSFExcelTemplate" }, Locale.getDefault()), e);

        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadXSSFExcelTemplate" }, Locale.getDefault()), e);

        } finally {
            LOGGER.debug("ExcelServiceImpl loadXSSFExcelTemplate end...");
            fileIn.close();
        }
        return wb;

    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#loadWorkbook(java.lang.String)
     */
    public HSSFWorkbook loadWorkbook(String filepath) throws BaseException, FileNotFoundException, IOException {

        FileInputStream fileIn = new FileInputStream(filepath);
        HSSFWorkbook wb = null;

        try {
            wb = loadWorkbook(fileIn);
        } catch (BaseException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadXSSFExcelTemplate" }, Locale.getDefault()), e);

        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadXSSFExcelTemplate" }, Locale.getDefault()), e);

        } finally {
            fileIn.close();
        }

        return wb;
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#loadXSSFWorkbook(java.lang.String)
     */
    public XSSFWorkbook loadXSSFWorkbook(String filepath) throws BaseException, FileNotFoundException, IOException {

        FileInputStream fileIn = new FileInputStream(filepath);
        XSSFWorkbook wb = null;
        try {
            wb = loadXSSFWorkbook(fileIn);
        } catch (BaseException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadXSSFExcelTemplate" }, Locale.getDefault()), e);

        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadXSSFExcelTemplate" }, Locale.getDefault()), e);

        } finally {
            fileIn.close();
        }

        return wb;
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#loadWorkbook(java.io.InputStream)
     */
    public HSSFWorkbook loadWorkbook(InputStream fileIn) throws BaseException {
        HSSFWorkbook wb = new HSSFWorkbook();

        try {
            LOGGER.debug("ExcelServiceImpl loadWorkbook ...");

            POIFSFileSystem fs = new POIFSFileSystem(fileIn);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"loadWorkbook" }, Locale.getDefault()), e);
        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"loadWorkbook" }, Locale.getDefault()), e);
        }

        return wb;
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#loadXSSFWorkbook(java.io.InputStream)
     */
    public XSSFWorkbook loadXSSFWorkbook(InputStream fileIn) throws BaseException {
        XSSFWorkbook wb = new XSSFWorkbook();

        try {
            LOGGER.debug("ExcelServiceImpl loadXSSFWorkbook ...");

            wb = new XSSFWorkbook(fileIn);

        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"loadXSSFWorkbook" }, Locale.getDefault()), e);
        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"loadXSSFWorkbook" }, Locale.getDefault()), e);
        }

        return wb;
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#writeWorkbook(org.apache.poi.hssf.usermodel.HSSFWorkbook)
     */
    public void writeWorkbook(HSSFWorkbook workbook) throws BaseException, IOException {

        workbook.write(null);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#writeWorkbook(org.apache.poi.hssf.usermodel.HSSFWorkbook)
     */
    public void writeXSSFWorkbook(XSSFWorkbook workbook) throws BaseException, IOException {

        workbook.write(null);
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#writeWorkbook(org.apache.poi.hssf.usermodel.HSSFWorkbook)
     */
    public void writeSXSSFWorkbook(SXSSFWorkbook workbook) throws BaseException, IOException {

        workbook.write(null);

    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#createWorkbook(org.apache.poi.hssf.usermodel.HSSFWorkbook, java.lang.String)
     */
    public HSSFWorkbook createWorkbook(HSSFWorkbook wb, String filepath) throws BaseException, FileNotFoundException, IOException {

        String fullFileName = filepath;

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is " + FilenameUtils.getFullPath(fullFileName));

        // 작업 디렉토리 생성
        if (!EgovFileUtil.isExistsFile(FilenameUtils.getFullPath(fullFileName))) {
            LOGGER.debug("make dir " + FilenameUtils.getFullPath(fullFileName));

            try {
                FileUtils.forceMkdir(new File(FilenameUtils.getFullPath(fullFileName)));
            } catch (IOException e) {
                throw new IOException("Cannot create directory for path: " + FilenameUtils.getFullPath(fullFileName));
            }
        }

        FileOutputStream fileOut = new FileOutputStream(fullFileName);

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is " + fullFileName);

        try {
            LOGGER.debug("ExcelServiceImpl loadExcelObject ...");

            wb.write(fileOut);

        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"createWorkbook" }, Locale.getDefault()), e);
        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"createWorkbook" }, Locale.getDefault()), e);
        } finally {
            LOGGER.debug("ExcelServiceImpl loadExcelObject end...");
            fileOut.close();
        }

        return wb;
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#createWorkbook(org.apache.poi.xssf.usermodel.XSSFWorkbook, java.lang.String)
     */
    public XSSFWorkbook createXSSFWorkbook(XSSFWorkbook wb, String filepath) throws BaseException, FileNotFoundException, IOException {

        String fullFileName = filepath;

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is " + FilenameUtils.getFullPath(fullFileName));

        // 작업 디렉토리 생성
        if (!EgovFileUtil.isExistsFile(FilenameUtils.getFullPath(fullFileName))) {
            LOGGER.debug("make dir " + FilenameUtils.getFullPath(fullFileName));

            try {
                FileUtils.forceMkdir(new File(FilenameUtils.getFullPath(fullFileName)));
            } catch (IOException e) {
                throw new IOException("Cannot create directory for path: " + FilenameUtils.getFullPath(fullFileName));
            }
        }

        FileOutputStream fileOut = new FileOutputStream(fullFileName);

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is " + fullFileName);

        try {
            LOGGER.debug("ExcelServiceImpl loadExcelObject ...");

            wb.write(fileOut);

        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] { "createXSSFWorkbook" }, Locale.getDefault()), e);
        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] { "createXSSFWorkbook" }, Locale.getDefault()), e);
        } finally {
            LOGGER.debug("ExcelServiceImpl loadExcelObject end...");
            fileOut.close();
        }

        return wb;
    }

    /*
     * (non-Javadoc)
     * @see egovframework.rte.fdl.excel.EgovExcelService#createSXSSFWorkbook(org.apache.poi.xssf.streaming.SXSSFWorkbook, java.lang.String)
     */
    public SXSSFWorkbook createSXSSFWorkbook(SXSSFWorkbook wb, String filepath) throws BaseException, FileNotFoundException, IOException {

        String fullFileName = filepath;

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is " + FilenameUtils.getFullPath(fullFileName));

        // 작업 디렉토리 생성
        if (!EgovFileUtil.isExistsFile(FilenameUtils.getFullPath(fullFileName))) {
            LOGGER.debug("make dir " + FilenameUtils.getFullPath(fullFileName));

            try {
                FileUtils.forceMkdir(new File(FilenameUtils.getFullPath(fullFileName)));
            } catch (IOException e) {
                throw new IOException("Cannot create directory for path: " + FilenameUtils.getFullPath(fullFileName));
            }
        }

        FileOutputStream fileOut = new FileOutputStream(fullFileName);

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is " + fullFileName);

        try {
            LOGGER.debug("ExcelServiceImpl loadExcelObject ...");

            wb.write(fileOut);

        } catch (IOException e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] { "createSXSSFWorkbook" }, Locale.getDefault()), e);

        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] { "createSXSSFWorkbook" }, Locale.getDefault()), e);

        } finally {
            LOGGER.debug("ExcelServiceImpl loadExcelObject end...");
            fileOut.close();
        }

        return wb;
    }

    /**
     * Workbook 객체를 생성하여 엑셀파일을 생성한다.
     * 
     * @param wb
     * @param filepath
     * @return
     * @throws Exception
     */
    public Workbook createWorkbook(Workbook wb, String filepath) throws BaseException, FileNotFoundException, IOException {

        String fullFileName = filepath;

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is {}", FilenameUtils.getFullPath(fullFileName));

        // 작업 디렉토리 생성
        if (!EgovFileUtil.isExistsFile(FilenameUtils.getFullPath(fullFileName))) {
            LOGGER.debug("make dir {}", FilenameUtils.getFullPath(fullFileName));

            try {
                FileUtils.forceMkdir(new File(FilenameUtils.getFullPath(fullFileName)));
            } catch (IOException e) {
                throw new IOException("Cannot create directory for path: " + FilenameUtils.getFullPath(fullFileName));
            }
        }

        FileOutputStream fileOut = new FileOutputStream(fullFileName);

        LOGGER.debug("EgovExcelServiceImpl.createWorkbook : templatePath is {}", fullFileName);

        try {
            LOGGER.debug("ExcelServiceImpl loadExcelObject ...");

            wb.write(fileOut);

        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"createWorkbook" }, Locale.getDefault()), e);
        } finally {
            LOGGER.debug("ExcelServiceImpl loadExcelObject end...");
            fileOut.close();
        }

        return wb;
    }


    /**
     * xlsx 엑셀 Template를 로딩하여 엑셀파일을 생성한다.
     * 
     * @param templateName
     * @param wb
     * @return
     * @throws Exception
     */

    public XSSFWorkbook loadExcelTemplate(String templateName, XSSFWorkbook wb) throws BaseException, FileNotFoundException, IOException {

        FileInputStream fileIn = new FileInputStream(templateName);

        LOGGER.debug("EgovExcelServiceImpl.loadExcelTemplate(XSSF) : templatePath is {}", templateName);

        try {
            LOGGER.debug("ExcelServiceImpl loadExcelTemplate(XSSF) ...");

            wb = new XSSFWorkbook(fileIn);

        } catch (Exception e) {
            LOGGER.error(getMessageSource().getMessage("error.excel.runtime.error", new Object[] {"EgovExcelServiceImpl loadExcelTemplate(XSSF)" }, Locale.getDefault()), e);

        } finally {
            LOGGER.debug("ExcelServiceImpl loadExcelTemplate(XSSF) end...");
            fileIn.close();
        }
        return wb;

    }
}
