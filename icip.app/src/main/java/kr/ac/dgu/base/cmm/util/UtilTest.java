
package kr.ac.dgu.base.cmm.util; 

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * kr.ac.dgu.base.cmm.util 
 *    |_ UtilTest.java
 * 1. 개요 : 
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 5. 오후 5:19:36
 * @version : 
 * @author : DGU
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 03. 5.		DGU				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class UtilTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilTest.class);
	
	public UtilTest() {
		LOGGER.debug("UtilTest ==================================");
	}
/*
	public static void main(String[] args) {
		
    	String phonenum = "1032456243";
    	phonenum = StringUtil.getPhoneNumberLocal(phonenum);
    	System.out.println("phonenum:"+phonenum);
    	phonenum = "01032456242";
    	phonenum = StringUtil.getPhoneNumberLocal(phonenum);
    	System.out.println("phonenum:"+phonenum);
    	phonenum = "324562432";
    	phonenum = StringUtil.getPhoneNumberLocal(phonenum);
    	System.out.println("phonenum:"+phonenum);
    	phonenum = "8201032456243";
    	phonenum = StringUtil.getPhoneNumberLocal(phonenum);
    	System.out.println("phonenum:"+phonenum);
	}
*/
	   public static void main(String[] args) {
	       Properties pro = System.getProperties();
	       System.out.println("pro : "+pro.toString());
	   }
}
