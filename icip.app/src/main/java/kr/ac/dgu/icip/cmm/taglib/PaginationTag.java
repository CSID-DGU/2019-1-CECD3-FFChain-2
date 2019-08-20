
package kr.ac.dgu.icip.cmm.taglib; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

/**
 * <pre>
 * kr.ac.dgu.icip.cmm.taglib 
 * @Class Name : PaginationTag.java
 * @Description :  class
 * @Modification Information
 * </pre>
 * @author : dgu
 * @date : 2019. 3. 8. 오전 10:15:13
 * @version : 1.0
 *  
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 3. 8.		dgu				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class PaginationTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private PaginationInfo paginationInfo;
	private String type;
	private String jsFunction;

	public int doEndTag() throws JspException {

		try {

			JspWriter out = pageContext.getOut();

			PaginationManager paginationManager;

			// WebApplicationContext에 id 'paginationManager'로 정의된 해당 Manager를 찾는다.
			WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(pageContext.getRequest(), pageContext.getServletContext());

			if (ctx.containsBean("paginationManager")) {
				paginationManager = (PaginationManager) ctx.getBean("paginationManager");
			} else {
				//bean 정의가 없다면 DefaultPaginationManager를 사용. 빈설정이 없으면 기본 적인 페이징 리스트라도 보여주기 위함.
				paginationManager = new DefaultPaginationManager();
			}

			PaginationRenderer paginationRenderer = paginationManager.getRendererType(type);

			String contents = paginationRenderer.renderPagination(paginationInfo, jsFunction);

			out.println(contents);

			return EVAL_PAGE;

		} catch (IOException e) {
			throw new JspException();
		}
	}

	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public void setType(String type) {
		this.type = type;
	}
}
