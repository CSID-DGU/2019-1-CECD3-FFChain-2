
package kr.ac.dgu.icip.cmm.taglib; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import kr.ac.dgu.base.cmm.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * kr.ac.dgu.icip.cmm.taglib 
 *    |_ FormatTelTag.java
 * 1. 개요 : 
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 13. 오후 1:49:23
 * @version : 
 * @author : DGU
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 03. 13.		DGU				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class FormatTelTag extends TagSupport {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FormatTelTag.class);

	private String value;

	public FormatTelTag() {
	}

	public int doStartTag() throws JspException {
		//LOGGER.debug("FormatTelTag start");
		if (value != null && !value.isEmpty()) {
	        JspWriter out = pageContext.getOut();
	        try {
	            out.write(formatTelephone(value));
	        } catch (IOException e) {
	        	try {
					out.write(e.getMessage());
				} catch (IOException e1) {
					LOGGER.error("FormatTelTag IOException",e1);
				}
	        }
	    }
	    return EVAL_PAGE;	
	}

	public static String formatTelephone(final String tel) {
		String formatted = null;
		if (tel == null || tel.isEmpty()) {
			formatted = tel;
		} else {
			formatted = "";
			formatted = StringUtil.getPhoneNumberLocal(tel);
			/*
			for (char c : tel.toCharArray()) {
				if (Character.isDigit(c)) {
					formatted = formatted + c;
					if (formatted.length() == 3 || formatted.length() == 8) {
						formatted = formatted + "-";
					}
				}
			}
			*/
		}
		return formatted;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
