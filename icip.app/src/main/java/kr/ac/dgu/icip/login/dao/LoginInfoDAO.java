
package kr.ac.dgu.icip.login.dao;

import java.util.List;
import java.util.Map;

import kr.ac.dgu.icip.cmm.vo.AuthInfoVO;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
/**
 * <pre>
 * kr.ac.dgu.icip.login.dao
 * @Class Name : LoginDAO.java
 * @Description :  class
 * @Modification Information
 * </pre>
 * @author : dgu
 * @date : 2017. 8. 9. 오후 2:18:33
 * @version : 1.0
 *
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 *
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2017. 8. 9.		dgu				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Mapper("loginInfoDAO")
public interface LoginInfoDAO {

    /**
     * LoginInfoVO을 조회한다.
     * @param vo - 조회할 정보가 담긴 LoginInfoVO
     * @return 조회한 LoginInfoVO
     * @exception Exception
     */
    public LoginInfoVO selectLoginInfo(LoginInfoVO vo) throws Exception;

    /**
     * <pre>
     * 개요 : 권한 정보 조회
     * </pre>
     * @Method Name : selectLoginAuth
     * @author : dgu
     * @date : 2017. 8. 21.
     * @param vo
     * @return
     * @throws Exception
     */
    public List<AuthInfoVO> selectLoginAuth(LoginInfoVO vo) throws Exception;

    /**
     * 그룹웨이 ID롤 조건으로 net id를 조회한다.
     * <pre>
     * 1. 개요 :
     * </pre>
     * @Method Name : selectNetId
     * @author : dgu
     * @date : 2017. 10. 24.
     * @see kr.ac.dgu.icip.login.service.LoginInfoService#selectNetId(java.lang.String)
     * @param args : 그룹웨어ID
     * @return
     * @throws Exception
     */
    public Map selectNetId(Map args);

}
