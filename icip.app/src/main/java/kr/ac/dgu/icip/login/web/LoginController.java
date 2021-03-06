package kr.ac.dgu.icip.login.web;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.web.BaseController;
import kr.ac.dgu.icip.cmm.vo.SystemInfoVO;
import kr.ac.dgu.icip.login.dao.LoginDAO;
import kr.ac.dgu.icip.login.service.LoginService;
import kr.ac.dgu.icip.login.vo.LoginVO;
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
public class LoginController extends BaseController {
    public static final int MEMBER_JOIN_FAIL = 0;
    public static final int MEMBER_JOIN_SUCCESS = 1;
    public static final int MEMBER_LOGIN_FAIL_ID = 0;
    public static final int MEMBER_LOGIN_FAIL_PW = -1;
    public static final int MEMBER_LOGIN_SUCCESS = 1;
    public int pageFlag;
    public int loginFlag;
    public boolean sessionInfo = false;//세션 정보
    public String userName = null; // 유저의 정보를 저장하기 위한 변수
    ///////////싹다 properties행 + 세션 유지는 cmm

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    /** LoginServiceService */
    @Resource(name = "loginService")
    private LoginService loginService;
    
    /**
     * 회원가입 페이지
     *
     * @param model Model
     * @param session HttpSession
     * @return "joinMain" 메인 화면
     * @throws Exception
     */
    @RequestMapping(value="/icip/login/joinMain.do")
    public String joinMain(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/icip/login/joinMain";
    }
    @RequestMapping(value="/icip/login/joinCheck.do")
    public String joinCheck(Model model, LoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
       //loginService =(LoginService) ContextLoader.getCurrentWebApplicationContext().getBean("loginService");
        if(loginService.checkId(vo) == null) {
            pageFlag = MEMBER_JOIN_SUCCESS;
            loginService.insertMember(vo);//해당 검증을 한 뒤에 jsp파일에서 바로 통신하는것은 힘들 것 같기 때문에 일단은 미리 처리하고 뷰단에서 결과에 따라 화면 이동
        }
        else {
            pageFlag = MEMBER_JOIN_FAIL;
        }
        model.addAttribute("pageFlag", pageFlag);//뷰단으로 pageFlag를 넘긴다.
        return "/icip/login/joinCheck";
        
    }
    
    @RequestMapping(value="/icip/login/loginMain.do")
    public String loginMain(Model model, LoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //loginService.selectLogin(vo);
        return "/icip/login/loginMain";
        
    }
    
    @RequestMapping(value="/icip/login/loginCheck.do")
    public String loginCheck(Model model, LoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //LOGGER.info(loginService.selectLogin(vo).getPw() +"디비값");
        //LOGGER.info(vo.getPw() +"입력");
        if(vo != null && loginService.selectLogin(vo) != null){//NPE 제거 //서비스
            if(vo.getId().equals(loginService.selectLogin(vo).getId())) { 
                if(loginService.selectLogin(vo).getPw().equals(vo.getPw())) {
                    loginFlag = MEMBER_LOGIN_SUCCESS;//1
                    sessionInfo = true;
                    userName = vo.getName();
                    
                }
                else {
                    loginFlag = MEMBER_LOGIN_FAIL_PW;//-1
                }
            }
            else {
                loginFlag = MEMBER_LOGIN_FAIL_ID;//0
            }
        }
        else {
            loginFlag = MEMBER_LOGIN_FAIL_ID;//0
        }
        model.addAttribute("loginFlag", loginFlag);
        model.addAttribute("sessionInfo", sessionInfo);//세션을 넘김
        model.addAttribute("userName", userName);//들어가는 로직 서비스단으로 넘길 수 있음을 확인
        return "/icip/login/loginCheck";
    }
    @RequestMapping(value="/icip/login/logout.do")
    public String logout(Model model, LoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return "/icip/login/logout";
    }
}
