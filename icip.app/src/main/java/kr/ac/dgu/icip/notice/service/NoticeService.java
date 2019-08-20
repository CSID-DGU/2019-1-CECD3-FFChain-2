package kr.ac.dgu.icip.notice.service;

import java.util.List;

import kr.ac.dgu.icip.notice.vo.NoticeVO;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2017. 8. 7.     DGU         신규생성
 * </pre>
 */
public interface NoticeService {

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
    public List<NoticeVO> selectNoticeList(NoticeVO vo);
    public NoticeVO insertNotice(NoticeVO vo);
    public NoticeVO selectNotice(NoticeVO vo);

}
