
package kr.ac.dgu.icip.login.service.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.util.AuthCheckUtil;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;
import kr.ac.dgu.icip.cmm.vo.SystemInfoVO;
import kr.ac.dgu.icip.login.vo.LoginVO;
import kr.ac.dgu.icip.login.dao.LoginDAO;
import kr.ac.dgu.icip.login.service.LoginService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;






import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 




import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @Class Name : LoginInfoServiceImpl.java
 * @Description : 로그인 정보 생성
 * @author 동국대학교 ICIP
 * @since 2017. 08. 06.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2017. 8. 7.     DGU         신규생성
 * </pre>
 */

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements  LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);



    @Resource(name="loginDAO")
    private LoginDAO loginDAO;


    /**
     * <pre>
     * 1. 개요 : 로그인 데이터 확인
     * </pre>
     * @Method Name : selectLogin
     * @author : dgu
     * @date : 2017. 8. 14.
     * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectLoginInfo(kr.ac.dgu.icip.cmm.vo.LoginInfoVO)
     * @param vo
     * @return
     * @throws Exception
     */
    
   public LoginVO checkId(LoginVO vo) {
       LoginVO loginvo = new LoginVO();
       try{      
           loginvo = loginDAO.checkId(vo);
           LOGGER.info(loginvo + "@@");
           
           
       }catch(Exception ex){
           LOGGER.error("",ex);
       }
       return loginvo;
   }
   
   
   public LoginVO selectLogin(LoginVO vo) {
       LoginVO loginvo = new LoginVO();
       try{      
           loginvo = loginDAO.selectLogin(vo);
           LOGGER.info(loginvo + "이쪽이다@@@@");//DB에서 뽑아온거
           LOGGER.info(String.valueOf(vo) + "알랄랄랄라@@@@");//입력한거
           //서비스단으로 뽑아서 처리할 수 있음.
           //기능 재사용을 위해서 서비스단에서 처리한다.
       }catch(Exception ex){
           LOGGER.error("",ex);
       }
       return loginvo;
   }
   
   
    public LoginVO insertMember(LoginVO vo) {
        LoginVO loginvo = new LoginVO();
        try{      
            loginvo = loginDAO.insertMember(vo);
            LOGGER.info(String.valueOf(loginvo) + "@@@@@@");
        }catch(Exception ex){
            LOGGER.error("",ex);
        }
        return loginvo;
    }
}
