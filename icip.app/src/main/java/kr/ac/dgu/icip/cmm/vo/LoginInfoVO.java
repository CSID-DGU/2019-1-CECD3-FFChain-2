package kr.ac.dgu.icip.cmm.vo;

import java.util.List;

import kr.ac.dgu.base.cmm.vo.DefaultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2018.08.19.	DGU			신규생성
 * </pre>
 */
public class LoginInfoVO extends DefaultVO{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInfoVO.class);
	
    private static final long serialVersionUID = 1L;
    
    /**
     * 사번 ss_sno
     */
    String sno               ;    
    /**
     * 부서코드 ssmember_deptcd
     */
    String deptcode          ;
    /**
     * 부서코드 LEVEL3 ssmember_deptcd2 
     */
    String deptcode2         ;
    /**
     * 사용자명 ssuser_nm
     */
    String username          ;
    /**
     * 지부코드 ssmember_jibucd
     */
    String jibucd            ;
    /**
     * 지부명 ssmember_jibunm
     */
    String jibunm            ;
    /**
     * 권한 코드 ssuser_auth
     */
    String authgcode         ;
    /**
     * 사용자ID ssuser_id
     */
    String userid ;
    
    /**
     * 
     */
    String ssmemberId = "";   
    
    /**
     * 사용자 권한 정보
     */
    List<AuthInfoVO> icipAuthInfoVOList ;

    
    /**
     * 현재 권한 정보
     */
    AuthInfoVO currAuthInfoVO;

    /**
     * Comment   : 
     * @return the ssmemberId
     */
    public String getSsmemberId() {
        return ssmemberId;
    }


    /**
     * Comment   : 
     * @param ssmemberId the ssmemberId to set
     */
    public void setSsmemberId(String ssmemberId) {
        this.ssmemberId = ssmemberId;
    }


    /**
     * Comment   : 
     * @return the sno
     */
    public String getSno() {
        return sno;
    }

    
    /**
     * Comment   : 
     * @return the currAuthInfoVO
     */
    public AuthInfoVO getCurrAuthInfoVO() {
        return currAuthInfoVO;
    }


    /**
     * Comment   : 
     * @param currAuthInfoVO the currAuthInfoVO to set
     */
    public void setCurrAuthInfoVO(AuthInfoVO currAuthInfoVO) {
        this.currAuthInfoVO = currAuthInfoVO;
    }


    /**
     * Comment   : 
     * @return the icipAuthInfoVOList
     */
    public List<AuthInfoVO> geticipAuthInfoVOList() {
        return icipAuthInfoVOList;
    }


    /**
     * Comment   : 
     * @param icipAuthInfoVOList the icipAuthInfoVOList to set
     */
    public void seticipAuthInfoVOList(List<AuthInfoVO> icipAuthInfoVOList) {
        this.icipAuthInfoVOList = icipAuthInfoVOList;
    }


    /**
     * Comment   : 
     * @return the deptcode
     */
    public String getDeptcode() {
        return deptcode;
    }


    /**
     * Comment   : 
     * @return the deptcode2
     */
    public String getDeptcode2() {
        return deptcode2;
    }


    /**
     * Comment   : 
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Comment   : 
     * @return the jibucd
     */
    public String getJibucd() {
        return jibucd;
    }


    /**
     * Comment   : 
     * @return the jibunm
     */
    public String getJibunm() {
        return jibunm;
    }


    /**
     * Comment   : 
     * @return the authgcode
     */
    public String getAuthgcode() {
        return authgcode;
    }


    /**
     * Comment   : 
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }


    /**
     * Comment   : 
     * @param sno the sno to set
     */
    public void setSno(String sno) {
        this.sno = sno;
    }


    /**
     * Comment   : 
     * @param deptcode the deptcode to set
     */
    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }


    /**
     * Comment   : 
     * @param deptcode2 the deptcode2 to set
     */
    public void setDeptcode2(String deptcode2) {
        this.deptcode2 = deptcode2;
    }


    /**
     * Comment   : 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Comment   : 
     * @param jibucd the jibucd to set
     */
    public void setJibucd(String jibucd) {
        this.jibucd = jibucd;
    }


    /**
     * Comment   : 
     * @param jibunm the jibunm to set
     */
    public void setJibunm(String jibunm) {
        this.jibunm = jibunm;
    }


    /**
     * Comment   : 
     * @param authgcode the authgcode to set
     */
    public void setAuthgcode(String authgcode) {
        this.authgcode = authgcode;
    }


    /**
     * Comment   : 
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginInfoVO [");
        if (sno != null) {
            builder.append("sno=");
            builder.append(sno);
            builder.append(", ");
        }
        if (deptcode != null) {
            builder.append("deptcode=");
            builder.append(deptcode);
            builder.append(", ");
        }
        if (deptcode2 != null) {
            builder.append("deptcode2=");
            builder.append(deptcode2);
            builder.append(", ");
        }
        if (username != null) {
            builder.append("username=");
            builder.append(username);
            builder.append(", ");
        }
        if (jibucd != null) {
            builder.append("jibucd=");
            builder.append(jibucd);
            builder.append(", ");
        }
        if (jibunm != null) {
            builder.append("jibunm=");
            builder.append(jibunm);
            builder.append(", ");
        }
        if (authgcode != null) {
            builder.append("authgcode=");
            builder.append(authgcode);
            builder.append(", ");
        }
        if (userid != null) {
            builder.append("userid=");
            builder.append(userid);
            builder.append(", ");
        }
        if (ssmemberId != null) {
            builder.append("ssmemberId=");
            builder.append(ssmemberId);
            builder.append(", ");
        }
        if (icipAuthInfoVOList != null) {
            builder.append("icipAuthInfoVOList=");
            builder.append(icipAuthInfoVOList);
            builder.append(", ");
        }
        if (currAuthInfoVO != null) {
            builder.append("currAuthInfoVO=");
            builder.append(currAuthInfoVO);
        }
        builder.append("]");
        return builder.toString();
    }
    
}
