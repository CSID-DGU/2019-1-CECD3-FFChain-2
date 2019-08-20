package kr.ac.dgu.icip.cmm.vo;

import kr.ac.dgu.base.cmm.vo.DefaultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class Name : AuthInfoVO.java
 * @Description : 계정 권한 VO class
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
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2018.08.19.	DGU			신규생성
 * </pre>
 */
public class AuthInfoVO extends DefaultVO{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInfoVO.class);
	
    private static final long serialVersionUID = 1L;
    
    /** 읽기권한 **/
    String readAuth = "";
    /** 쓰기권한 **/
    String wrtAuth = "";
    /** 관리자 권한 **/
    String admAuth = "";
    /** 권한 G코드 **/
    String authGcode = "";
    /** 메뉴코드 **/
    String menuCode = "";
    /** 서브 메뉴 코드 **/
    String sMenuCode = "";
    /** 메뉴이름 **/
    String menuName = "";
    /** 메뉴이미지 **/
    String menuImage = "";
    /** 메뉴외부이미지 **/
    String menuOutImage = "";
    /** 메뉴URL **/
    String menuUrl = "";
    /**
     * Comment   : 
     * @return the readAuth
     */
    public String getReadAuth() {
        return readAuth;
    }
    /**
     * Comment   : 
     * @return the wrtAuth
     */
    public String getWrtAuth() {
        return wrtAuth;
    }
    /**
     * Comment   : 
     * @return the admAuth
     */
    public String getAdmAuth() {
        return admAuth;
    }
    /**
     * Comment   : 
     * @return the authGcode
     */
    public String getAuthGcode() {
        return authGcode;
    }
    /**
     * Comment   : 
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }
    /**
     * Comment   : 
     * @return the sMenuCode
     */
    public String getsMenuCode() {
        return sMenuCode;
    }
    /**
     * Comment   : 
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * Comment   : 
     * @return the menuImage
     */
    public String getMenuImage() {
        return menuImage;
    }
    /**
     * Comment   : 
     * @return the menuOutImage
     */
    public String getMenuOutImage() {
        return menuOutImage;
    }
    /**
     * Comment   : 
     * @return the menuUrl
     */
    public String getMenuUrl() {
        return menuUrl;
    }
    /**
     * Comment   : 
     * @param readAuth the readAuth to set
     */
    public void setReadAuth(String readAuth) {
        this.readAuth = readAuth;
    }
    /**
     * Comment   : 
     * @param wrtAuth the wrtAuth to set
     */
    public void setWrtAuth(String wrtAuth) {
        this.wrtAuth = wrtAuth;
    }
    /**
     * Comment   : 
     * @param admAuth the admAuth to set
     */
    public void setAdmAuth(String admAuth) {
        this.admAuth = admAuth;
    }
    /**
     * Comment   : 
     * @param authGcode the authGcode to set
     */
    public void setAuthGcode(String authGcode) {
        this.authGcode = authGcode;
    }
    /**
     * Comment   : 
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * Comment   : 
     * @param sMenuCode the sMenuCode to set
     */
    public void setsMenuCode(String sMenuCode) {
        this.sMenuCode = sMenuCode;
    }
    /**
     * Comment   : 
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * Comment   : 
     * @param menuImage the menuImage to set
     */
    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }
    /**
     * Comment   : 
     * @param menuOutImage the menuOutImage to set
     */
    public void setMenuOutImage(String menuOutImage) {
        this.menuOutImage = menuOutImage;
    }
    /**
     * Comment   : 
     * @param menuUrl the menuUrl to set
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : toString
     * @author : dgu
     * @date : 2019. 3. 21.
     * @see java.lang.Object#toString()
     * @return
     */ 	
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthInfoVO [readAuth=");
        builder.append(readAuth);
        builder.append(", wrtAuth=");
        builder.append(wrtAuth);
        builder.append(", admAuth=");
        builder.append(admAuth);
        builder.append(", authGcode=");
        builder.append(authGcode);
        builder.append(", menuCode=");
        builder.append(menuCode);
        builder.append(", sMenuCode=");
        builder.append(sMenuCode);
        builder.append(", menuName=");
        builder.append(menuName);
        builder.append(", menuImage=");
        builder.append(menuImage);
        builder.append(", menuOutImage=");
        builder.append(menuOutImage);
        builder.append(", menuUrl=");
        builder.append(menuUrl);
        builder.append("]");
        return builder.toString();
    }

    
}
