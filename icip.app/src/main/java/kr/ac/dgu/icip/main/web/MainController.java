package kr.ac.dgu.icip.main.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.dgu.base.cmm.web.BaseController;
import kr.ac.dgu.icip.cmm.vo.SystemInfoVO;
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
 * @Class Name : MainController.java
 * @Description : index 진입 메인
 * 
 * @author 동국대학교 ICIP
 * @since 2019. 03. 06.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
@Controller
public class MainController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	/** SystemInfoService */
	@Resource(name = "systemInfoService")
	private SystemInfoService systemInfoService;
    
	/**
     * 메인 페이지
     *
     * @param model Model
     * @param session HttpSession
     * @return "index" 메인 화면
     * @throws Exception
     */
	
	@RequestMapping(value="/icip/main/index.do")
	public String mainIndex(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		EgovPropertyService propertyService = (EgovPropertyService) ContextLoader.getCurrentWebApplicationContext().getBean("propertiesService");
		//model.addAttribute("SystemType", propertyService.getString("solution.application.zone"));
		//model.addAttribute("ServerName", request.getServerName());
		//model.addAttribute("RemoteAddr", request.getRemoteAddr());//담는 과정
        //LOGGER.info("solution.application.zone : "+propertyService.getString("solution.application.zone"));
        //LOGGER.info("request.getServerName() : "+request.getServerName());
        //LOGGER.info("request.getRemoteAddr() : "+request.getRemoteAddr());

        //SystemInfoVO svo = systemInfoService.getSystemInfo(request, response);
        //model.addAttribute("svo", svo);
        
      
        
		return "/icip/main/index";
	}
	@RequestMapping(value="/icip/main/main.do")
    public String main(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
        //로그인 했을 때의 메인
        return "/icip/main/main";
    }
	
	@RequestMapping(value="/cmmn/error/error.do")
	public String error(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		return "/cmmn/error/error";
	}

	@RequestMapping(value="/cmmn/error/error404.do")
	public String error404(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		return "/cmmn/error/error_404";
	}

	@RequestMapping(value="/cmmn/error/error500.do")
	public String error500(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		return "/cmmn/error/error_500";
	}	

	@RequestMapping(value="/cmmn/error/errorhttp.do")
	public String errorhttp(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		return "/cmmn/error/error_http";
	}		


	@RequestMapping(value="/cmmn/error/egovBizException.do")
	public String egovBizException(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		return "/cmmn/error/egovBizException";
	}		


	@RequestMapping(value="/cmmn/error/egovError.do")
	public String egovError(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		return "/cmmn/error/egovError";
	}			


}
