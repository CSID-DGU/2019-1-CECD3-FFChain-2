package kr.ac.dgu.base.cmm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import kr.ac.dgu.base.cmm.exception.BaseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @Class Name : ValidatorUtil.java
 * @Description :
 * Validator Utility
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information
 * ------------------------------------------------------------------
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2015. 4. 27.           DGU         신규생성
 * </pre>
 */
@Component("validatorUtil")
public class ValidatorUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorUtil.class);

    @Resource(name = "messageSource")
    private MessageSource egovMessageSource;

    /**
     * validate 체크
     * @param obj
     * @param check
     * @param msg
     * @throws Exception
     */
    public void valid(Object obj, String check, String msg) throws Exception {
        LOGGER.debug("ValidatorUtil valid start.. ");

        if(check != null && !"".equals(check.trim())) {
            String objVal = obj == null ? "" : obj.toString();
            String[] arrCheck = check.split(",");
            String chkKey = "";
            String extChkVal = "";

            for(int i=0; i<arrCheck.length; i++) {
                chkKey = arrCheck[i] == null ? "" : arrCheck[i];

                if (!"".equals(chkKey)) {
                    //validate 확장형 체크
                    if(chkKey.indexOf(":") != -1) {
                        extChkVal = chkKey.split(":")[1].trim();
                        chkKey = chkKey.split(":")[0].trim();
                    } else {
                        extChkVal = "";
                        chkKey = chkKey.trim();
                    }

                    //required
                    if("required".equals(chkKey)) {
                        if ("".equals(objVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException("필수 입력값입니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.required", new String[]{msg});
                            }
                        }
                    //date
                    } else if ("date".equals(chkKey)) {
                        if (!"".equals(objVal) && !DateUtil.checkDate(objVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException("날짜형식으로 입력해주세요.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.date", new String[]{msg});
                            }
                        }
                    //number
                    } else if ("number".equals(chkKey)) {
                        if (!"".equals(objVal)) {
                            Pattern pattern = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
                            Matcher matcher = pattern.matcher(objVal.replaceAll(",", ""));

                            if (!matcher.matches()) {
                                if (msg == null || msg.trim().length() <= 0) {
                                    throw new BaseException("숫자형식으로 입력해주세요.");
                                } else {
                                    throw new BaseException(egovMessageSource, "lib.common.validate.number", new String[]{msg});
                                }
                            }
                        }
                    //digits
                    } else if ("digits".equals(chkKey)) {
                        if (!"".equals(objVal) && !NumberUtil.getNumberValidCheck(objVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException("숫자만 입력해주세요.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.digits", new String[]{msg});
                            }
                        }
                    //minlength : ex) minlength:4
                    } else if ("minlength".equals(chkKey)) {
                        if (extChkVal == null || extChkVal.trim().length() <= 0) extChkVal = "0";

                        if (objVal.length() < Integer.parseInt(extChkVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException(extChkVal + "자 이상 입력해야 합니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.minlength", new String[]{msg, extChkVal});
                            }
                        }
                    //maxlength : ex) maxlength:10
                    } else if ("maxlength".equals(chkKey)) {
                        if (extChkVal == null || extChkVal.trim().length() <= 0) extChkVal = "0";

                        if (!"0".equals(extChkVal) && objVal.length() > Integer.parseInt(extChkVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException(extChkVal + "자 이내로 입력해야 합니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.maxlength", new String[]{msg, extChkVal});
                            }
                        }
                    //rangelength : ex) rangelength:4~12
                    } else if ("rangelength".equals(chkKey)) {
                        if (extChkVal != null && !"".equals(extChkVal.trim())) {
                            String minVal = extChkVal.split("~")[0].trim();
                            String maxVal = extChkVal.split("~")[1].trim();

                            if (!"".equals(objVal) && (objVal.length() < Integer.parseInt(minVal) || objVal.length() > Integer.parseInt(maxVal))) {
                                if (msg == null || msg.trim().length() <= 0) {
                                    throw new BaseException(minVal + "글자 이상 "+maxVal+"글자 이하로 입력해주세요.");
                                } else {
                                    throw new BaseException(egovMessageSource, "lib.common.validate.rangelength", new String[]{msg, minVal, maxVal});
                                }
                            }
                        }
                    //minbytelength : ex) minbytelength:4
                    } else if ("minbytelength".equals(chkKey)) {
                        if (extChkVal == null || extChkVal.trim().length() <= 0) extChkVal = "0";

                        if (objVal.getBytes("euc-kr").length < Integer.parseInt(extChkVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException("영문 " + extChkVal + "자(한글 " + Integer.parseInt(extChkVal)/2 + "자) 이상 입력해야 합니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.minbytelength", new String[]{msg, extChkVal, String.valueOf(Integer.parseInt(extChkVal)/2)});
                            }
                        }
                    //maxbytelength : ex) minbytelength:10
                    } else if ("maxbytelength".equals(chkKey)) {
                        if (extChkVal == null || extChkVal.trim().length() <= 0) extChkVal = "0";

                        if (!"0".equals(extChkVal) && objVal.getBytes("euc-kr").length > Integer.parseInt(extChkVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException("영문 " + extChkVal + "자(한글 " + Integer.parseInt(extChkVal)/2 + "자) 이내로 입력해야 합니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.maxbytelength", new String[]{msg, extChkVal, String.valueOf(Integer.parseInt(extChkVal)/2)});
                            }
                        }
                    //fixlength : ex) fixlength:5
                    } else if ("fixlength".equals(chkKey)) {
                        if (extChkVal == null || extChkVal.trim().length() <= 0) extChkVal = "0";

                        if (!"".equals(objVal) && objVal.length() != Integer.parseInt(extChkVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException(extChkVal + "자로 입력해야 합니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.fixlength", new String[]{msg, extChkVal});
                            }
                        }
                    //fixbytelength : ex) fixbytelength:5
                    } else if ("fixbytelength".equals(chkKey)) {
                        if (extChkVal == null || extChkVal.trim().length() <= 0) extChkVal = "0";

                        if (!"0".equals(extChkVal) && objVal.getBytes("euc-kr").length != Integer.parseInt(extChkVal)) {
                            if (msg == null || msg.trim().length() <= 0) {
                                throw new BaseException("영문 " + extChkVal + "자(한글 " + Integer.parseInt(extChkVal)/2 + "자)로 입력해야 합니다.");
                            } else {
                                throw new BaseException(egovMessageSource, "lib.common.validate.fixbytelength", new String[]{msg, extChkVal, String.valueOf(Integer.parseInt(extChkVal)/2)});
                            }
                        }
                    //lAlphaDigits : 영문(소문자), 숫자
                    } else if ("lAlphaDigits".equals(chkKey)) {
                        if (!"".equals(objVal)) {
                            Pattern pattern = Pattern.compile("^[a-z0-9]+$");
                            Matcher matcher = pattern.matcher(objVal);

                            if (!matcher.matches()) {
                                if (msg == null || msg.trim().length() <= 0) {
                                    throw new BaseException("영문(소문자)/숫자 조합으로 입력해주세요.");
                                } else {
                                    throw new BaseException(egovMessageSource, "lib.common.validate.lAlphaDigits", new String[]{msg});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}