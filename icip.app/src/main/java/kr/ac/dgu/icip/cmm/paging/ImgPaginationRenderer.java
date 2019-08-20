package kr.ac.dgu.icip.cmm.paging;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * @Class Name : ImgPaginationRenderer.java
 * @Description :
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class ImgPaginationRenderer extends AbstractPaginationRenderer {

	/**
	 * 페이징 태그
	 */
	public ImgPaginationRenderer() {

		String strWebDir = "/imgNew/";

		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
							"<image src='" + strWebDir + "ico_list_first.png' class='vaM' border=0/></a>&#160;";
        previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
        						"<image src='" + strWebDir + "ico_list_prev.png' class='vaM' border=0/></a>&#160;";
        currentPageLabel = "[<a class='onPage'>{0}</a>]&#160;";
        otherPageLabel = "[<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>]&#160;";
        nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
        					"<image src='" + strWebDir + "ico_list_next.png' class='vaM' border=0/></a>&#160;";
        lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" +
        					"<image src='" + strWebDir + "ico_list_last.png' class='vaM' border=0/></a>&#160;";
	}
}
