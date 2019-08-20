package kr.ac.dgu.icip.notice.web;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.web.BaseController;
import kr.ac.dgu.icip.cmm.vo.SystemInfoVO;
import kr.ac.dgu.icip.notice.dao.NoticeDAO;
import kr.ac.dgu.icip.notice.service.NoticeService;
import kr.ac.dgu.icip.notice.vo.NoticeVO;
import kr.ac.dgu.icip.system.service.SystemInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : LoginController.java
 * @Description : Login 진입
 * 
 * @author 동국대학교 ICIP
 * @since 2019. 03. 06.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.     DGU         신규생성
 * </pre>
 */
@Controller
public class NoticeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);
    
    /** LoginServiceService */
    @Resource(name = "noticeService")
    private NoticeService noticeService;
    int code = 1;
    
    /**
     * 회원가입 페이지
     *
     * @param model Model
     * @param session HttpSession
     * @return "notice" 메인 화면
     * @throws Exception
     */
    @RequestMapping(value="/icip/notice/noticeList.do")
    public String noticeList(Model model,  NoticeVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("noticeList 호출@@@@");
        List<NoticeVO> list = noticeService.selectNoticeList(vo);
        model.addAttribute("list", list);
        
        return "/icip/notice/noticeList";
    }
    @RequestMapping(value="/icip/notice/noticeWrite.do")
    public String noticeWrite(Model model, NoticeVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("noticeWrite 호출@@@@");
        return "/icip/notice/noticeWrite";
    }
    @RequestMapping(value="/icip/notice/writeCheck.do")
    public String writeCheck(Model model,  NoticeVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("writeCheck 호출@@@@");
        vo.setCode(code);
        code++;
        noticeService.insertNotice(vo);
        
        return "/icip/notice/writeCheck";
    }
    @RequestMapping(value="/icip/notice/noticeView.do")
    public String noticeView(Model model, NoticeVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("noticeView 호출@@@@");
        model.addAttribute("result", noticeService.selectNotice(vo));
        return "/icip/notice/noticeView";
    }
   
}
