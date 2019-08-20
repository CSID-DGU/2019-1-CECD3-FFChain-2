package kr.ac.dgu.icip.login.service;

import java.util.List;

import kr.ac.dgu.icip.cmm.vo.AuthInfoVO;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;

/**
 * @Class Name : SystemInfoService.java
 * @Description : System 정보 생성
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
public interface LoginInfoService {

    /**
     * <pre>
     * 1. 개요 : 로그인 정보를 조회한다.
     * </pre>
     * @Method Name : selectLoginInfo
     * @author : dgu
     * @date : 2017. 8. 14.
     * @param vo
     * @return
     * @throws Exception
     */
    LoginInfoVO selectLoginInfo(LoginInfoVO vo) throws Exception;

    /**
     * 그룹웨이 ID롤 조건으로 net id를 조회한다.
     * <pre>
     * 1. 개요 :
     * </pre>
     * @Method Name : selectNetId
     * @author : dgu
     * @date : 2017. 10. 24.
     * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectNetId(java.lang.String)
     * @param sno : 그룹웨어ID
     * @return
     * @throws Exception
     */
    String selectNetId(String sno) throws Exception;

    /**
     * <pre>
     * 개요 : 권한 정보 조회
     * </pre>
     * @Method Name : selectLoginAuth
     * @author : dgu
     * @date : 2017. 8. 21.
     * @param logininfoReqVo
     * @return
     * @throws Exception
     */
    List<AuthInfoVO> selectLoginAuth(LoginInfoVO logininfoReqVo) throws Exception;

    /**
     * <pre>
     * 개요 : 권한정보를 링크 기준으로 체크 한다.
     * </pre>
     * @Method Name : selectLoginAuthByPass
     * @author : dgu
     * @date : 2017. 8. 21.
     * @param pass
     * @return
     * @throws Exception
     */
    AuthInfoVO selectLoginAuthByPass(String pass,List<AuthInfoVO> authInfoVOList) throws Exception;

}
