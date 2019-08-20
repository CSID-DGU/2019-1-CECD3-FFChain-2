package kr.ac.dgu.base.cmm.vo;

/**
 * @Class Name : PagingVO.java
 * @Description :
 * Paging VO
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
 *  2019. 03. 06.		DGU			신규생성
 * </pre>
 */	
public class PagingVO extends DefaultVO{

	private static final long serialVersionUID = 6095669120448615611L;

	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = ""; 
	
	/** 현재페이지 */
    private String pageIndex = "1";

    /** 페이지갯수 */
    private String pageUnit = "";

    /** 페이지사이즈 */
    private String pageSize = "";

    /** firstIndex */
    private String firstIndex = "1";

    /** lastIndex */
    private String lastIndex = "1";

    /** recordCountPerPage */
    private String recordCountPerPage = "";    
    
    private String rowcount;
    
    /** 페이징번호를 눌렀을때 호출될 자바스크립트 */
    private String pageJsFunction;
    
    /** 페이징 종류 */
    private String pageType;


	public String getPageIndex() {
		if(pageIndex==null || pageIndex.equals("")) return "1";
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(String pageUnit) {
		this.pageUnit = pageUnit;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getFirstIndex() {
		if(firstIndex==null || firstIndex.equals("")) return "1";
		return firstIndex;
	}

	public void setFirstIndex(String firstIndex) {
		this.firstIndex = firstIndex;
	}

	public String getLastIndex() {
		if(lastIndex==null || lastIndex.equals("")) return "1";
		return lastIndex;
	}

	public void setLastIndex(String lastIndex) {
		this.lastIndex = lastIndex;
	}

	public String getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(String recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getRowcount() {
		return rowcount;
	}

	public void setRowcount(String rowcount) {
		this.rowcount = rowcount;
	}

	public String getPageJsFunction() {
		return pageJsFunction;
	}

	public void setPageJsFunction(String pageJsFunction) {
		this.pageJsFunction = pageJsFunction;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

    /**
     * Comment   : 
     * @return the searchCondition
     */
    public String getSearchCondition() {
        return searchCondition;
    }

    /**
     * Comment   : 
     * @return the searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * Comment   : 
     * @param searchCondition the searchCondition to set
     */
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    /**
     * Comment   : 
     * @param searchKeyword the searchKeyword to set
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : toString
     * @author : dgu
     * @date : 2019. 3. 18.
     * @see java.lang.Object#toString()
     * @return
     */ 	
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PagingVO [searchCondition=");
        builder.append(searchCondition);
        builder.append(", searchKeyword=");
        builder.append(searchKeyword);
        builder.append(", pageIndex=");
        builder.append(pageIndex);
        builder.append(", pageUnit=");
        builder.append(pageUnit);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(", firstIndex=");
        builder.append(firstIndex);
        builder.append(", lastIndex=");
        builder.append(lastIndex);
        builder.append(", recordCountPerPage=");
        builder.append(recordCountPerPage);
        builder.append(", rowcount=");
        builder.append(rowcount);
        builder.append(", pageJsFunction=");
        builder.append(pageJsFunction);
        builder.append(", pageType=");
        builder.append(pageType);
        builder.append("]");
        return builder.toString();
    }	
    
    
}
