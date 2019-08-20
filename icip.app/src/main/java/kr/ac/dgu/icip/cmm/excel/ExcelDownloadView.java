package kr.ac.dgu.icip.cmm.excel;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.util.HttpUtil;
import kr.ac.dgu.base.cmm.util.StringUtil;
import kr.ac.dgu.icip.cmm.excel.ExcelDownloadDatas.ColumnInfo;
import kr.ac.dgu.icip.cmm.excel.ExcelDownloadDatas.FormatType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * @Class Name : ExcelDownloadView.java
 * @Description : 공통 Excel Class
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 27.     DGU         신규생성
 * </pre>
 */
public class ExcelDownloadView extends AbstractExcelView {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDownloadView.class);

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ExcelDownloadDatas<?> excelDatas = (ExcelDownloadDatas<?>) model.get("excelDatas");
        if (excelDatas.getExcelFileName() != null) {
            String filename = excelDatas.getExcelFileName() + ".xls";

            HttpUtil.makeAttachmentHeaderForExcel(request, response, filename);
        }
  
        // 5만건씩 분리하여 sheet를 만들어 준다.
        double len =  Math.ceil((double)excelDatas.size()/50000);
        for(int k=0; k<len; k++){
        	
        	int strIdx = k*50000;
        	int endIdx = (k+1)*50000;
        	
        	if(k == len-1) endIdx = excelDatas.size();
        
	        String sheetName = excelDatas.getSheetName();
	        HSSFSheet sheet = workbook.createSheet(sheetName != null ? sheetName : "Sheet_"+k);
	        Map<String, Object> convertMap = new HashMap<String, Object>();
	
	        int rowNum = 0;
	
	        // title
	        HSSFRow header = sheet.createRow(rowNum);
	        List<ColumnInfo> columns = excelDatas.columns;
	        HSSFCellStyle titleStyle = getColumStyle(workbook, "Arial", 11, true, HSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT);
	        HSSFCellStyle[][] cellStyles = new HSSFCellStyle[columns.size()][2];
	        for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
	            ColumnInfo columnInfo = columns.get(columnIndex);
	            HSSFCell cell = header.createCell(columnIndex);
	            if (columnInfo.colunmName.indexOf("\n") > 0) {
	                // 개행문자 사용하기
	                titleStyle.setWrapText(true);
	
	                // row 높이 설정
	                header.setHeight((short) 740);
	            }
	            cell.setCellValue(columnInfo.colunmName);
	            cell.setCellStyle(titleStyle);
	
	            if (columnInfo.columnWidth != null) {
	                sheet.setColumnWidth(columnIndex, columnInfo.columnWidth);
	            }
	
	            short align = HSSFCellStyle.ALIGN_LEFT;
	            if (columnInfo.colunmAlign != null) {
	                switch (columnInfo.colunmAlign) {
	                case Left:
	                    align = HSSFCellStyle.ALIGN_LEFT;
	                    break;
	                case Center:
	                    align = HSSFCellStyle.ALIGN_CENTER;
	                    break;
	                case Right:
	                    align = HSSFCellStyle.ALIGN_RIGHT;
	                    break;
	                default:
	                    align = HSSFCellStyle.ALIGN_LEFT;
	                    break;
	                }
	            }
	            cellStyles[columnIndex][0] = getColumStyle(workbook, "Arial", 10, false, align, null);
	            cellStyles[columnIndex][1] = getColumStyle(workbook, "Arial", 10, false, align, null);
	            //cellStyles[columnIndex][1] = getColumStyle(workbook, "Arial", 10, false, align, IndexedColors.LIGHT_CORNFLOWER_BLUE);
	
	            if (columnInfo.formatType != null) {
	                if (!FormatType.HtmlTagRemoveFormat.equals(columnInfo.formatType)) {
	                    switch (columnInfo.formatType) {
	                    case DataFormat: {
	                        short dataFormet = workbook.createDataFormat().getFormat(columnInfo.displayFormat);
	                        cellStyles[columnIndex][0].setDataFormat(dataFormet);
	                        cellStyles[columnIndex][1].setDataFormat(dataFormet);
	                    }
	                        break;
	
	                    case DecimalFormat: {
	                        short dataFormet = workbook.createDataFormat().getFormat(columnInfo.displayFormat != null ? columnInfo.displayFormat : "#,##0");
	                        cellStyles[columnIndex][0].setDataFormat(dataFormet);
	                        cellStyles[columnIndex][1].setDataFormat(dataFormet);
	                    }
	                        break;
	
	                    default: {
	                        short dataFormet = workbook.createDataFormat().getFormat(columnInfo.displayFormat);
	                        cellStyles[columnIndex][0].setDataFormat(dataFormet);
	                        cellStyles[columnIndex][1].setDataFormat(dataFormet);
	                    }
	                        break;
	                    }
	                }
	            }
	        }
	
	        rowNum++;
	        for (int rowIndex = strIdx; rowIndex < endIdx; rowIndex++) {
	            HSSFRow row = sheet.createRow(rowNum++);
	            Object dataObj = excelDatas.get(rowIndex);
	            int oddANumbeEvenNumber = rowIndex % 2;
	
	            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
	                ColumnInfo columnInfo = columns.get(columnIndex);
	                Object cellValue;
	                String convertKey;
	                int cellType = Cell.CELL_TYPE_STRING;
	                if (ExcelDownloadDatas.NO_COLUNM.equals(columnInfo.propertyName)) {
	                    cellValue = Double.valueOf(excelDatas.size() - rowIndex);
	                    cellType = Cell.CELL_TYPE_NUMERIC;
	                } else {
	                    if (dataObj instanceof Map) {
	                        cellValue = ((Map) dataObj).get(columnInfo.propertyName);
	                        convertKey = columnInfo.propertyName.replace(".", "");
	
	                        convertMap.clear();
	                        convertMap.put(convertKey, cellValue);
	
	                        cellValue = BeanUtils.getProperty(convertMap, convertKey);
	                    } else {
	                        cellValue = BeanUtils.getProperty(dataObj, columnInfo.propertyName);
	                    }
	
	                    if (columnInfo.formatType != null) {
	                        if (!FormatType.HtmlTagRemoveFormat.equals(columnInfo.formatType)) {
	                            switch (columnInfo.formatType) {
	                            case DataFormat: {
	                                cellType = Cell.CELL_TYPE_FORMULA;
	                                if (cellValue != null && ((String) cellValue).length() > 0) {
	                                    try {
	                                        cellValue = new SimpleDateFormat(columnInfo.sourceFormat, Locale.getDefault()).parse((String) cellValue);
	                                    } catch (ParseException e) {
	                                        cellValue = null;
	                                    }
	                                } else {
	                                    cellValue = null;
	                                }
	                            }
	                                break;
	
	                            case DecimalFormat: {
	                                try {
	                                    if (cellValue != null) {
	                                        cellValue = Double.valueOf((String) cellValue);
	                                        cellType = Cell.CELL_TYPE_NUMERIC;
	                                    }
	                                } catch (NumberFormatException e) {
	                                    if (LOGGER.isDebugEnabled()) {
	                                        LOGGER.debug("DecimalFormat - " + columnInfo.colunmName + ":" + cellValue);
	                                    }
	                                }
	                            }
	                                break;
	
	                            case RatioFormat: {
	                                double doubleValue = 0;
	                                try {
	                                    String strValue = (String) cellValue;
	                                    doubleValue = (new Double(strValue)) / 10.0;
	                                    java.text.DecimalFormat format = new java.text.DecimalFormat("##.#");
	                                    cellValue = format.format(doubleValue) + ":" + format.format(10 - doubleValue);
	                                } catch (NumberFormatException e) {
	                                    if (LOGGER.isDebugEnabled()) {
	                                        LOGGER.debug("RatioFormat - " + columnInfo.colunmName + ":" + cellValue);
	                                    }
	                                }
	                            }
	                                break;
	
	                            default: {
	                                cellType = Cell.CELL_TYPE_FORMULA;
	                                if (cellValue != null && ((String) cellValue).length() > 0) {
	                                    try {
	                                        cellValue = new SimpleDateFormat(columnInfo.sourceFormat, Locale.getDefault()).parse((String) cellValue);
	                                    } catch (ParseException e) {
	                                        cellValue = null;
	                                    }
	                                } else {
	                                    cellValue = null;
	                                }
	                            }
	                                break;
	                            }
	                        }
	                    }
	                }
	
	                HSSFCell cell = row.createCell(columnIndex);
	                cell.setCellStyle(cellStyles[columnIndex][oddANumbeEvenNumber]);
	                // cell.setCellType(cellType);
	                if (cellValue != null) {
	                    switch (cellType) {
	                    case Cell.CELL_TYPE_NUMERIC:
	                        cell.setCellValue(((Double) cellValue).doubleValue());
	                        break;
	                    case Cell.CELL_TYPE_FORMULA:
	                        cell.setCellValue((Date) cellValue);
	                        break;
	                    default:
	                        if (FormatType.HtmlTagRemoveFormat.equals(columnInfo.formatType)) {
	                            //cellValue = StringUtil.stripHTML((String) cellValue, "/5/8");
	                            cellValue = StringUtil.removeHtmlTagForExcel((String) cellValue);
	                        }
	
	                        cell.setCellValue((String) cellValue);
	                        break;
	                    }
	                }
	            }
	        }
        }
    }

    /**
     * 컬럼스타일 취득
     * 
     * @param workbook
     * @param fontName
     * @param fontSize
     * @param isBold
     * @param align
     * @param bgColor
     * @return
     */
    private HSSFCellStyle getColumStyle(HSSFWorkbook workbook, String fontName, int fontSize, boolean isBold, short align, IndexedColors bgColor) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(getFont(workbook, fontName, fontSize, isBold));
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setAlignment(align);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        if (bgColor != null) {
            style.setFillForegroundColor(bgColor.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());

        return style;
    }

    /**
     * 폰트 취득
     * 
     * @param workbook
     * @param fontName
     * @param fontSize
     * @param isBold
     * @return
     */
    private HSSFFont getFont(HSSFWorkbook workbook, String fontName, int fontSize, boolean isBold) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setFontName(fontName);
        if (isBold) {
            font.setBoldweight((short) Font.BOLD);
        }
        font.setColor(HSSFColor.BLACK.index);

        return font;
    }

}
