package kr.ac.dgu.base.cmm.web;

import javax.servlet.ServletRequest;

import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

/**
 * <pre>
 * kr.ac.dgu.base.cmm.web 
 *    |_ AbstractCommonCodeTag.java
 * 1. 개요 : 공통 코드 Tag 추상화 Class
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 13. 오후 1:49:23
 * @version : 
 * @author : DGU
 * @history : 
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용  
 *  ----------- ------------------- ---------------------------------------
 *  2019. 03. 13.       DGU             최초 작성 
 *  -----------------------------------------------------------------------
 *  
 */
public abstract class AbstractCodeTag extends HtmlEscapingAwareTag{

    /** serialVersionUID */
    private static final long serialVersionUID = -901291918382005305L;
    
    /** HTML Name */
    private String name;

    /** HTML Id */
    private String id;

    /** HTML 제목 */
    private String title;

    /** HTML Type */
    private String type;
    
    /** HTML CSS CLASS명 */
    private String cssClass;

    /** HTML CSS style */
    private String cssStyle;

    /** 대상코드 */
    private String targetCd;

    /** 기본 선택값 */
    private String selectedVal;

    /** 기본 선택값 */
    private String isSelectedVal;

    /** 선택옵션 표시여부 */
    private String emptyOptionYn;

    /** 선택옵션 텍스트 */
    private String emptyOptionText;  
    /**
     * Comment   : targetCd
     * @return the targetCd
     */
    public String getTargetCd() {
        return targetCd;
    }


    /**
     * Comment   : id
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * Comment   : targetCd
     * @param targetCd the targetCd to set
     */
    public void setTargetCd(String targetCd) {
        this.targetCd = targetCd;
    }


    /**
     * Comment   : id
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * 자원을 반환한다.
     */
    @Override
    public void release() {
        super.release();
    }


    /** request 정보를 세팅한다.
     * @param request
     * @return
     * @throws Exception
     */
    public ServletRequest setReqAttr(ServletRequest request) throws Exception {
        request.setAttribute("cmcd_name", this.name);
        request.setAttribute("cmcd_id", this.id);
        request.setAttribute("cmcd_title", this.title);
        request.setAttribute("cmcd_type", this.type);
        request.setAttribute("cmcd_cssClass", this.cssClass);
        request.setAttribute("cmcd_cssStyle", this.cssStyle);
        request.setAttribute("cmcd_targetCd", this.targetCd);
        request.setAttribute("cmcd_selectedVal", this.selectedVal);
        request.setAttribute("cmcd_isSelectedVal", this.isSelectedVal);
        request.setAttribute("cmcd_emptyOptionYn", this.emptyOptionYn);
        request.setAttribute("cmcd_emptyOptionText", this.emptyOptionText);        
        return request;
    }


    protected abstract int doStartTagInternal() throws Exception;


    /**
     * Comment   : 
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Comment   : 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Comment   : 
     * @return the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Comment   : 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Comment   : 
     * @return the type
     */
    public String getType() {
        return type;
    }


    /**
     * Comment   : 
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Comment   : 
     * @return the cssClass
     */
    public String getCssClass() {
        return cssClass;
    }


    /**
     * Comment   : 
     * @param cssClass the cssClass to set
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }


    /**
     * Comment   : 
     * @return the cssStyle
     */
    public String getCssStyle() {
        return cssStyle;
    }


    /**
     * Comment   : 
     * @param cssStyle the cssStyle to set
     */
    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }


    /**
     * Comment   : 
     * @return the selectedVal
     */
    public String getSelectedVal() {
        return selectedVal;
    }


    /**
     * Comment   : 
     * @param selectedVal the selectedVal to set
     */
    public void setSelectedVal(String selectedVal) {
        this.selectedVal = selectedVal;
    }


    /**
     * Comment   : 
     * @return the emptyOptionYn
     */
    public String getEmptyOptionYn() {
        return emptyOptionYn;
    }


    /**
     * Comment   : 
     * @param emptyOptionYn the emptyOptionYn to set
     */
    public void setEmptyOptionYn(String emptyOptionYn) {
        this.emptyOptionYn = emptyOptionYn;
    }


    /**
     * Comment   : 
     * @return the emptyOptionText
     */
    public String getEmptyOptionText() {
        return emptyOptionText;
    }


    /**
     * Comment   : 
     * @param emptyOptionText the emptyOptionText to set
     */
    public void setEmptyOptionText(String emptyOptionText) {
        this.emptyOptionText = emptyOptionText;
    }


    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : toString
     * @author : dgu
     * @date : 2019. 10. 13.
     * @see java.lang.Object#toString()
     * @return
     */ 	
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractCodeTag [name=");
        builder.append(name);
        builder.append(", id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", type=");
        builder.append(type);
        builder.append(", cssClass=");
        builder.append(cssClass);
        builder.append(", cssStyle=");
        builder.append(cssStyle);
        builder.append(", targetCd=");
        builder.append(targetCd);
        builder.append(", selectedVal=");
        builder.append(selectedVal);
        builder.append(", emptyOptionYn=");
        builder.append(emptyOptionYn);
        builder.append(", emptyOptionText=");
        builder.append(emptyOptionText);
        builder.append("]");
        return builder.toString();
    }


    /**
     * Comment   : 
     * @return the isSelectedVal
     */
    public String getIsSelectedVal() {
        return isSelectedVal;
    }


    /**
     * Comment   : 
     * @param isSelectedVal the isSelectedVal to set
     */
    public void setIsSelectedVal(String isSelectedVal) {
        this.isSelectedVal = isSelectedVal;
    }
}