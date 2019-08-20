
package kr.ac.dgu.icip.login.service.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.util.AuthCheckUtil;
import kr.ac.dgu.icip.cmm.vo.AuthInfoVO;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;
import kr.ac.dgu.icip.login.dao.LoginInfoDAO;
import kr.ac.dgu.icip.login.service.LoginInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2017. 8. 7.		DGU			신규생성
 * </pre>
 */

@Service("loginInfoService")
public class LoginInfoServiceImpl extends EgovAbstractServiceImpl implements  LoginInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInfoServiceImpl.class);



    @Resource(name="loginInfoDAO")
    private LoginInfoDAO loginInfoDAO;


	/**
	 * <pre>
	 * 1. 개요 : 로그인 정보 조회
	 * </pre>
	 * @Method Name : selectLoginInfo
	 * @author : dgu
	 * @date : 2017. 8. 14.
	 * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectLoginInfo(kr.ac.dgu.icip.cmm.vo.LoginInfoVO)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public LoginInfoVO selectLoginInfo(LoginInfoVO vo) throws Exception {
	    LoginInfoVO logininfovo = new LoginInfoVO();
		try{

		    logininfovo = loginInfoDAO.selectLoginInfo(vo);
		    LOGGER.info(logininfovo + "@@");

		}catch(Exception ex)
		{
			LOGGER.error("",ex);
		}
		return logininfovo;
	}

	/**
	 * 그룹웨이 ID롤 조건으로net id를 조회한다.
	 * <pre>
	 * 1. 개요 :
	 * </pre>
	 * @Method Name : selectNetId
	 * @author : ahnjw
	 * @date : 2017. 10. 24.
	 * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectNetId(java.lang.String)
	 * @param sno : 그룹웨어ID
	 * @return
	 * @throws Exception
	 */
    public String selectNetId(String sno) throws Exception {
        String netId = "";
        try{
            Map args = new HashMap();
            args.put("sno", sno);

            Map result = loginInfoDAO.selectNetId(args);
            netId = (String) result.get("GW_ID");

        }catch(Exception ex)
        {
            LOGGER.error("",ex);
        }
        return netId;
    }

	/**
	 * <pre>
	 * 1. 개요 : 권한 정보 조회
	 * </pre>
	 * @Method Name : selectLoginAuth
	 * @author : dgu
	 * @date : 2017. 8. 21.
	 * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectLoginAuth(kr.ac.dgu.icip.cmm.vo.LoginInfoVO)
	 * @param logininfoReqVo
	 * @return
	 * @throws Exception
	 */
	public List<AuthInfoVO> selectLoginAuth(LoginInfoVO vo) throws Exception {
	    List<AuthInfoVO> authInfoVOList = null;
        try{

            authInfoVOList = loginInfoDAO.selectLoginAuth(vo);

        }catch(Exception ex)
        {
            LOGGER.error("",ex);
        }
        return authInfoVOList;
    }

	/**
	 * <pre>
	 * 1. 개요 :
	 * </pre>
	 * @Method Name : selectLoginAuthByPass
	 * @author : dgu
	 * @date : 2017. 8. 21.
	 * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectLoginAuthByPass(java.lang.String, java.util.List)
	 * @param pass
	 * @param authInfoVOList
	 * @return
	 * @throws Exception
	 */
	public AuthInfoVO selectLoginAuthByPass(String pass,List<AuthInfoVO> authInfoVOList) throws Exception {
	    AuthInfoVO authInfoVO= null;
        try{
            String authCode = AuthCheckUtil.getAuthCode(pass);
            AuthInfoVO authitem = null;
            for (Iterator<AuthInfoVO> iterator = authInfoVOList.iterator(); iterator.hasNext();) {
                authitem = (AuthInfoVO) iterator.next();
                if(authitem.getMenuCode().equals(authCode)){
                    authInfoVO = authitem;
                    break;
                }
            }
        }catch(Exception ex)
        {
            LOGGER.error("selectLoginAuthByPass :  ",ex);
        }
        return authInfoVO;
    }
}
