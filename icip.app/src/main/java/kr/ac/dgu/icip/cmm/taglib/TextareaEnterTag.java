package kr.ac.dgu.icip.cmm.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.Tag;

import kr.ac.dgu.base.cmm.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

/**
 * @Class Name : TextareaEnterTag.java
 * @Description : Textarea 엔터 변환 Class
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 *
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일             수정자         수정내용
 * ------------------------------------------------------------------
 *  2015. 8. 4.         DGU         최초생성
 * </pre>
 */
public class TextareaEnterTag extends HtmlEscapingAwareTag {

    private static final long serialVersionUID = 2125135108906075175L;

    /** The Constant logger. */
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(TextareaEnterTag.class);

    /** INCLUDE JSP: textarea Enter Tag JSP */
    private static final String JSP_TEXTAREA_ENTER_TAG = "/WEB-INF/jsp/cmmn/taglib/textareaEnter.jsp";

    /** XSS 공격 취약 문자열 변환여부. script, form, input tag를 ""로 변환 */
    private boolean antiXSS = true;

    private String contents;

    /**
     * 초기값을 설정한다.
     */
    public TextareaEnterTag() {
        super();
        contents = "";
    }

    @Override
    protected int doStartTagInternal() throws Exception {
        ServletRequest request = pageContext.getRequest();

        if (antiXSS) {
            contents = StringUtil.forceAntiXSS(contents);
        }

        request.setAttribute("taglib_contents", contents);

        pageContext.include(JSP_TEXTAREA_ENTER_TAG);

        return Tag.SKIP_BODY;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isAntiXSS() {
        return antiXSS;
    }

    public void setAntiXSS(boolean forceAntiXSS) {
        this.antiXSS = forceAntiXSS;
    }

}
