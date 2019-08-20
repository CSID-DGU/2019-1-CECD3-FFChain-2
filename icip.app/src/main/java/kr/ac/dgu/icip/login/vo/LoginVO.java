package kr.ac.dgu.icip.login.vo;

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
public class LoginVO extends DefaultVO{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginVO.class);
    
    private static final long serialVersionUID = 1L;
    
   String id;
   String pw;
   String name;
   String eMail;
   String address;
    /**
 * Comment   : 
 * @return the id
 */
public String getId() {
    return id;
}
/**
 * Comment   : 
 * @return the pw
 */
public String getPw() {
    return pw;
}
/**
 * Comment   : 
 * @return the name
 */
public String getName() {
    return name;
}
/**
 * Comment   : 
 * @return the eMail
 */
public String geteMail() {
    return eMail;
}
/**
 * Comment   : 
 * @return the address
 */
public String getAddress() {
    return address;
}
/**
 * Comment   : 
 * @param id the id to set
 */
public void setId(String id) {
    this.id = id;
}
/**
 * Comment   : 
 * @param pw the pw to set
 */
public void setPw(String pw) {
    this.pw = pw;
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
 * @param eMail the eMail to set
 */
public void seteMail(String eMail) {
    this.eMail = eMail;
}
/**
 * Comment   : 
 * @param address the address to set
 */
public void setAddress(String address) {
    this.address = address;
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
        builder.append("LoginVO [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (pw != null) {
            builder.append("pw=");
            builder.append(pw);
            builder.append(", ");
        }
        if (name != null) {
            builder.append("name=");
            builder.append(name);
            builder.append(", ");
        }
        if (eMail != null) {
            builder.append("eMail=");
            builder.append(eMail);
            builder.append(", ");
        }
        if (address != null) {
            builder.append("address=");
            builder.append(address);
            builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
    
}
