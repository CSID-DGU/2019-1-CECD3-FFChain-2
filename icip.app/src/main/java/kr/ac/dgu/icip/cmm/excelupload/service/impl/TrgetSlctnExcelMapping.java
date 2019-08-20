package kr.ac.dgu.icip.cmm.excelupload.service.impl;

import java.text.SimpleDateFormat;
import java.util.Locale;

import kr.ac.dgu.base.cmm.util.EgovExcelUtil;
import kr.ac.dgu.icip.cmm.excelupload.service.EgovExcelMapping;
import kr.ac.dgu.icip.cmm.excelupload.vo.TrgetSlctnExcelRegistVO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * Excel   매핑 클래스
 * @author
 * @since
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 *
 * </pre>
 */
public class TrgetSlctnExcelMapping extends EgovExcelMapping {

	/**
	 *   엑셀파일 맵핑
	 */
	@SuppressWarnings("deprecation")
	public Object mappingColumn(HSSFRow row) {
    	HSSFCell cell2 = row.getCell((int) 2);
    	HSSFCell cell3 = row.getCell((int) 3);
    	HSSFCell cell4 = row.getCell((int) 4);
    	HSSFCell cell5 = row.getCell((int) 5);
    	HSSFCell cell6 = row.getCell((int) 6);
        HSSFCell cell7 = row.getCell((int) 7);
        HSSFCell cell8 = row.getCell((int) 8);
        HSSFCell cell9 = row.getCell((int) 9);
        HSSFCell cell10 = row.getCell((int) 10);
        HSSFCell cell11 = row.getCell((int) 11);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        TrgetSlctnExcelRegistVO vo = new TrgetSlctnExcelRegistVO();
        
        int cloidx = 0;
        
        cloidx++;

        vo.setMemberId(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
        vo.setCompNo(EgovExcelUtil.getStringValue(row.getCell(cloidx++)));
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

		return vo;
	}

    @Override
    public Object mappingColumn(Row row) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}
