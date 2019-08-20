package kr.ac.dgu.icip.cmm.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Class Name : ExcelDownloadDatas.java
 * @Description : 공통 Excel Class
 * @author 동국대학교 ICIP
 * @since 2019. 3. 27.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.     DGU         신규생성
 * </pre>
 */
public class ExcelDownloadDatas<E> extends ArrayList<E> {
    private static final long serialVersionUID = -3540238516552791708L;

    /* 일련번호 */
    public static final String NO_COLUNM = "_NO";
    
    /* 셀타잎 */
    public enum FormatType {
        DataFormat,
        DecimalFormat,
        RatioFormat,
        HtmlTagRemoveFormat
    }
    
    /* 셀정렬 */
    public enum Align {
        Left,
        Center,
        Right
    }
    
    /* 셀정보 */
    public static class ColumnInfo {
        /* data field property name */
        String propertyName;
        /* column Title */
        String colunmName;
        /* column width(1/1000 Cm) */
        Integer columnWidth = null;
        /* column align */
        Align colunmAlign = null;
        FormatType formatType = null;
        String sourceFormat = null;
        String displayFormat = null;
        
        /**
         * 셀정보 생성자
         * @param propertyName
         * @param colunmName
         */
        public ColumnInfo(String propertyName, String colunmName) {
            this(propertyName, colunmName, null, null, null, null, null);
        }
        
        /**
         * 셀정보 생성자
         * @param propertyName
         * @param colunmName
         * @param columnWidth
         */
        public ColumnInfo(String propertyName, String colunmName, Integer columnWidth) {
            this(propertyName, colunmName, null, null, null, columnWidth, null);
        }
        
        /**
         * 셀정보 생성자
         * @param propertyName
         * @param colunmName
         * @param columnWidth
         * @param colunmAlign
         */
        public ColumnInfo(String propertyName, String colunmName, Integer columnWidth, Align colunmAlign) {
            this(propertyName, colunmName, null, null, null, columnWidth, colunmAlign);
        }
        
        /**
         * 셀정보 생성자
         * @param propertyName
         * @param colunmName
         * @param colunmAlign
         */
        public ColumnInfo(String propertyName, String colunmName, Align colunmAlign) {
            this(propertyName, colunmName, null, null, null, null, colunmAlign);
        }
        
        /**
         * 셀정보 생성자
         * @param propertyName
         * @param colunmName
         * @param formatType
         * @param sourceFormat
         * @param displayFormat
         */
        public ColumnInfo(String propertyName, String colunmName, FormatType formatType, String sourceFormat, String displayFormat) {
            this(propertyName, colunmName, formatType, sourceFormat, displayFormat, null, null);
        }
        
        /**
         * 셀정보 생성자
         * @param propertyName
         * @param colunmName
         * @param formatType
         * @param sourceFormat
         * @param displayFormat
         * @param columnWidth
         * @param colunmAlign
         */
        public ColumnInfo(String propertyName, String colunmName, FormatType formatType, String sourceFormat, String displayFormat, Integer columnWidth, Align colunmAlign) {
            this.propertyName = propertyName;
            this.colunmName = colunmName;
            this.formatType = formatType;
            this.sourceFormat = sourceFormat;
            this.displayFormat = displayFormat;
            this.columnWidth = columnWidth;
            this.colunmAlign = colunmAlign;
        }
        
        /**
         * 셀타핑지정
         * @param formatType
         * @return
         */
        public ColumnInfo setFormatType(FormatType formatType) {
            this.formatType = formatType;
            return this;
        }
        
        /**
         * 셀타핑넙링지정
         * @param width
         * @return
         */
        public ColumnInfo setColumnWidth(Integer width) {
            this.columnWidth = width;
            return this;
        }
        
        /**
         * 셀정렬지정
         * @param align
         * @return
         */
        public ColumnInfo setColumnAlign(Align align) {
            this.colunmAlign = align;
            return this;
        }
        
        /**
         * 셀표시포맷지정
         * @param displayFormat
         * @return
         */
        public ColumnInfo setDisplayFormat(String displayFormat) {
            this.displayFormat = displayFormat;
            return this;
        }
    }
    
    /* 컬럼리스트 */
    public List<ColumnInfo> columns = null;
    
    /* 시트명 */
    private String sheetName = null;
    
    /* 엑셀파일명 */
    private String excelFileName = null;
    
    /**
     * 엑셀메타데이터 생성자
     */
    public ExcelDownloadDatas() {
        super();
    }
    
    /**
     * 엑셀메타데이터 생성자
     * @param c 컬럼컬렉션
     */
    public ExcelDownloadDatas(Collection<E> c) {
        super(c);
    }
    
    /**
     * 컬럼정보 추가
     * @param columnInfo
     */
    public void addColumnInfo(ColumnInfo columnInfo) {
        if(columns == null) {
            columns = new ArrayList<ColumnInfo>();
        }
        columns.add(columnInfo);
    }

    /**
     * 시트명 취득
     * @return
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * 시트명 지정
     * @param sheetName
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /** 
     * 파일명 취득
     * @return
     */
    public String getExcelFileName() {
        return excelFileName;
    }

    /**
     * 파일명 지정
     * @param excelFileName
     */
    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
    
    
}
