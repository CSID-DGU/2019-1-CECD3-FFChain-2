package kr.ac.dgu.base.cmm.util;

import java.math.BigDecimal;
import java.util.List;

import kr.ac.dgu.base.cmm.vo.PagingVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

/**
 * @Class Name : PagingUtil.java
 * @Description : Paging에 대한 Utility 클래스
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 *            <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class PagingUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagingUtil.class);
    
    /** 기본페이지 갯수 */
    private static final String PAGE_UNIT = "10";
    /** 기본사이즈 갯수 */
    private static final String PAGE_SIZE = "10";
    
    private static EgovPropertyService propertiesService;
    
    private static PaginationManager paginationManager;
    
    public static boolean init(EgovPropertyService prop, PaginationManager pageMag) {
        if(propertiesService == null) {
            propertiesService = prop;
        }
        
        if(paginationManager == null) {
            paginationManager = pageMag;
        }
        
        return true;
    }

    public PaginationInfo setValue(PagingVO pagingVO) {
        String pageUnit = pagingVO.getPageUnit();
        if (StringUtils.isEmpty(pageUnit)) {
            pageUnit = propertiesService.getString("pageUnit");
        }

        String pageSize = pagingVO.getPageSize();
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = propertiesService.getString("pageSize");
        }
        
        if (StringUtils.isEmpty(pageUnit)) {
            pageUnit = PAGE_UNIT;
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = PAGE_SIZE;
        }

        pagingVO.setPageUnit(pageUnit);
        pagingVO.setPageSize(pageSize);

        /** pageing setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(Integer.parseInt(pagingVO.getPageIndex()));
        paginationInfo.setRecordCountPerPage(Integer.parseInt(pagingVO.getPageUnit()));
        paginationInfo.setPageSize(Integer.parseInt(pagingVO.getPageSize()));

        pagingVO.setFirstIndex(Integer.toString(paginationInfo.getFirstRecordIndex()));
        pagingVO.setLastIndex(Integer.toString(paginationInfo.getLastRecordIndex()));
        pagingVO.setRecordCountPerPage(Integer.toString(paginationInfo.getRecordCountPerPage()));
        /*
        if(LOGGER.isDebugEnabled()) {
 			StringBuffer sb = new StringBuffer();
            sb.append(LoggingUtil.LINE_DELIM);
            //sb.append("\nPaginationInfo.setValue() called ");
            sb.append("\n\tpageUnit:").append(pageUnit);
            sb.append("\tpageSize:").append(pageSize);
            sb.append(LoggingUtil.LINE_DELIM);
            LOGGER.debug(sb.toString());
        }
        */
        return paginationInfo;
    }

    public int getRowCount(List<?> list) {
        if (list == null || list.size() == 0)
            return 0;
        else
            return ((BigDecimal) ((EgovMap) list.get(0)).get("rowcount")).intValue();
    }

    public String getPagingHtml(PaginationInfo paginationInfo, String pagingType, String pagingFn) {
        PaginationManager paginationManagerLocal = null;
        
        StringBuffer sb = new StringBuffer();
        sb.append(LoggingUtil.LINE_DELIM);
        sb.append("\nPaginationInfo.getPagingHtml() called ");
        
        if (paginationManager != null) {
            paginationManagerLocal = paginationManager;
            sb.append("\n\tpaginationManagerLocal : paginationManager");
        } else {
            paginationManagerLocal = new DefaultPaginationManager();
            sb.append("\n\tpaginationManagerLocal : DefaultPaginationManager");
        }
        sb.append(LoggingUtil.LINE_DELIM);
        
        PaginationRenderer paginationRenderer = paginationManagerLocal.getRendererType(pagingType);
        String pagingHtml = paginationRenderer.renderPagination(paginationInfo, pagingFn);
        
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(sb.toString());
        }
        
        return pagingHtml;
    }

}
