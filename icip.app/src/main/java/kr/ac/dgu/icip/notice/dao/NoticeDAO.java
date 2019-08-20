package kr.ac.dgu.icip.notice.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.icip.cmm.vo.AuthInfoVO;
import kr.ac.dgu.icip.cmm.vo.LoginInfoVO;
import kr.ac.dgu.icip.notice.vo.NoticeVO;//이름에 VO를 import
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
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2017. 8. 9.     dgu             최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Mapper("noticeDAO")
public interface NoticeDAO {
    public List<NoticeVO> selectNoticeList(NoticeVO vo);
    public NoticeVO insertNotice(NoticeVO vo);
    public NoticeVO selectNotice(NoticeVO vo) ;

}
