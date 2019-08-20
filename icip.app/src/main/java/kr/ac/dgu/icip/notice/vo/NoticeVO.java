package kr.ac.dgu.icip.notice.vo;

import java.util.List;

import kr.ac.dgu.base.cmm.vo.DefaultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * @Class Name : AdminloginidVO.java
 * @Description : 관리로그인계정 VO class
 * @Modification Information
 *
 * @author DGU
 * @since 2018.08.19
 * @version 1.0

 *  
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2018.08.19. DGU         신규생성
 * </pre>
 */
public class NoticeVO extends DefaultVO{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeVO.class);
    
    private static final long serialVersionUID = 1L;
    
   int code;
   String title;
   String content;
   String writer;
   String datetime;
    /**
 * Comment   : 
 * @return the code
 */
public int getCode() {
    return code;
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
 * @return the content
 */
public String getContent() {
    return content;
}
/**
 * Comment   : 
 * @return the writer
 */
public String getWriter() {
    return writer;
}
/**
 * Comment   : 
 * @return the datetime
 */
public String getDatetime() {
    return datetime;
}
/**
 * Comment   : 
 * @param code the code to set
 */
public void setCode(int code) {
    this.code = code;
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
 * @param content the content to set
 */
public void setContent(String content) {
    this.content = content;
}
/**
 * Comment   : 
 * @param writer the writer to set
 */
public void setWriter(String writer) {
    this.writer = writer;
}
/**
 * Comment   : 
 * @param datetime the datetime to set
 */
public void setDatetime(String datetime) {
    this.datetime = datetime;
}
    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : toString
     * @author : dgu
     * @date : 2018. 9. 7.
     * @see java.lang.Object#toString()
     * @return
     */     
    @Override
    public String toString() { //스트링으로 묶어서 출력한다. =>로그 보려고 만들어둠
        StringBuilder builder = new StringBuilder();
        builder.append("NoticeVO [");
        if (code != 0) {
            builder.append("code=");
            builder.append(code);
            builder.append(", ");
        }
        if (title != null) {
            builder.append("title=");
            builder.append(title);
            builder.append(", ");
        }
        if (content != null) {
            builder.append("content=");
            builder.append(content);
            builder.append(", ");
        }
        if (writer != null) {
            builder.append("writer=");
            builder.append(writer);
            builder.append(", ");
        }
        if (datetime != null) {
            builder.append("datetime=");
            builder.append(datetime);
            builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
    
}
