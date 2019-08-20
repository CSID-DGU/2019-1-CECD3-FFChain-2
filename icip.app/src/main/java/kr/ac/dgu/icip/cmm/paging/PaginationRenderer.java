package kr.ac.dgu.icip.cmm.paging;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;


/**
 * @Class Name : PaginationRenderer.java
 * @Description :
 * 페이징 Renderer 
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2015. 1. 30.		DGU			신규생성
 * </pre>
 */
public class PaginationRenderer extends AbstractPaginationRenderer {

    /**
    * PaginationRenderer
    */
	public PaginationRenderer() {
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1});\" class=\"btn prevend\" title=\"목록처음\">" +
							"<span class=\"screen_out\">처음</span></a>";
        previousPageLabel = "<a href=\"#\" onclick=\"{0}({1});\" class=\"btn prev\" title=\"이전목록\">" +
        		            "<span class=\"screen_out\">이전</span></a>";         
        currentPageLabel = "<a href=\"#\"><span class=\"active\">{0}</span></a>";
        otherPageLabel = "<a href=\"#\" onclick=\"{0}({1});\"><span>{2}</span></a>";
        nextPageLabel = "<a href=\"#\" onclick=\"{0}({1});\" class=\"btn next\" title=\"다음목록\">" +
        		            "<span class=\"screen_out\">다음</span></a>";
        lastPageLabel = "<a href=\"#\" onclick=\"{0}({1});\" class=\"btn nextend\" title=\"목록마지막\">" +
				            "<span class=\"screen_out\">마지막</span></a>";
 	}
}
