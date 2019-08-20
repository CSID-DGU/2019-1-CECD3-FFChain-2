package kr.ac.dgu.base.cmm.web;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

/**
 * @Class Name : BaseController.java
 * @Description :
 * 공통 Controller
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 *            <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 03. 06.		DGU			신규생성
 * </pre>
 */	
public class BaseController {
    @Resource(name="messageSource")
	protected MessageSource egovMessageSource;

}
