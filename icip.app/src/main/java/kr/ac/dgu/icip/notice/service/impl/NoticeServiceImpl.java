
package kr.ac.dgu.icip.notice.service.impl;


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
import kr.ac.dgu.icip.notice.vo.NoticeVO;
import kr.ac.dgu.icip.notice.dao.NoticeDAO;
import kr.ac.dgu.icip.notice.service.NoticeService;



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

@Service("noticeService")
public class NoticeServiceImpl extends EgovAbstractServiceImpl implements  NoticeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);



    @Resource(name="noticeDAO")
    private NoticeDAO noticeDAO;


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
    
    public List<NoticeVO> selectNoticeList(NoticeVO vo) {
        List<NoticeVO> noticevo = null;
        try{      
            noticevo = noticeDAO.selectNoticeList(vo);
            LOGGER.info(noticevo + "@#@#");
            
            
        }catch(Exception ex){
            LOGGER.error("",ex);
        }
        return noticevo;
    }
    public NoticeVO insertNotice(NoticeVO vo) {
        NoticeVO noticevo = new NoticeVO();
        try{
            //code 관련 코드를 넣고, vo.setCode로 설정
            noticevo = noticeDAO.insertNotice(vo);
            LOGGER.info(noticevo + "@@@@-----insert");
        }catch(Exception ex){
            LOGGER.error("",ex);
        }
        return noticevo;
    }
    public NoticeVO selectNotice(NoticeVO vo) {
        NoticeVO noticevo = new NoticeVO();
        try{      
            noticevo = noticeDAO.selectNotice(vo);
            LOGGER.info(noticevo + "@@@@-----select");
        }catch(Exception ex){
            LOGGER.error("",ex);
        }
        return noticevo;
    }
    
    
    
    
}