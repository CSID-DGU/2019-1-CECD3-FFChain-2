package kr.ac.dgu.base.cmm.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import kr.ac.dgu.base.cmm.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : HTMLOutTag.java
 * @Description : HTML 특수문자를 기호문자로 변환 Tag
 * @author DGU
 * @시스템 동국대학교 ICIP
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
 *  2019. 3. 7.		DGU         신규생성
 * </pre>
 */
public class HTMLOutTag extends SimpleTagSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(HTMLOutTag.class);

    private String value = "";

    /** HTML Tag Escape 문자를 원래문자로 변환할지 여부. HTML Tag Filter 를 사용할 경우 true로 설정 */
    private boolean unescapeHTMLTag = false;

    /** XSS 공격 취약 문자열 변환여부. script, form, input tag를 ""로 변환 */
    private boolean antiXSS = true;

    /**
     * HtmlTag 문자로 변화하여 출력 한다.
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException  {

        String unescapeValue = value;

        if (unescapeHTMLTag) {
            unescapeValue = StringUtil.unescapeHTMLChar(unescapeValue);
        }

        if (antiXSS) {
            unescapeValue = StringUtil.forceAntiXSS(unescapeValue);
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace(this.toString());
        }
        //html 에디터 전용 클래스로 감싸는 부분입니다.
        unescapeValue = "<div class='editorDetail'>"+unescapeValue+"</div>";
        JspWriter out = getJspContext().getOut();
        out.print(unescapeValue);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isUnescapeHTMLTag() {
        return unescapeHTMLTag;
    }

    public void setUnescapeHTMLTag(boolean unescapeHTMLTag) {
        this.unescapeHTMLTag = unescapeHTMLTag;
    }

    public boolean isAntiXSS() {
        return antiXSS;
    }

    public void setAntiXSS(boolean forceAntiXSS) {
        this.antiXSS = forceAntiXSS;
    }
}