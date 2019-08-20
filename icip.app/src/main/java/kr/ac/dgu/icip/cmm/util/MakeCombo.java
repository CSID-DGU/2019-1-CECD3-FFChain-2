package kr.ac.dgu.icip.cmm.util;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 1. 개요 : HTML 콤보 생성 클래스
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 14. 오전 11:22:09
 * @version : 
 * @author : DGU
 * @history : 
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용  
 *  ----------- ------------------- ---------------------------------------
 *  2019. 03. 14.       DGU             최초 작성 
 *  -----------------------------------------------------------------------
 *  
 */
@Component("makeComboUtil")
public class MakeCombo {
    private static Logger LOGGER = Logger.getLogger(MakeCombo.class);

    /**
     * 콤보박스, 라디오버튼, 체크박스등의 Html 태그를 자동생성시켜주는 Class
     */

    /*
     * 날자의 월에 해당하는 콤보박스 생성시 사용하는 콤보명
     */
    public static final String CBMonthName = "1월/2월/3월/4월/5월/6월/7월/8월/9월/10월/11월/12월";

    /**
     * 날자의 월에 해당하는 콤보박스 생성시 사용하는 콤보값
     */
    public static final String CBMonthValue = "01/02/03/04/05/06/07/08/09/10/11/12";

    /**
     * 날자의 날에 해당하는 콤보박스 생성시 사용하는 콤보명
     */
    public static final String CBDayName = "01/02/03/04/05/06/07/08/09/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31";

    /**
     * 날자의 날에 해당하는 콤보박스 생성시 사용하는 콤보값
     */
    public static final String CBDayValue = "01/02/03/04/05/06/07/08/09/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31";

    /**
     * 시간 콤보박스 생성시 사용하는 콤보명
     */
    public static final String CBHourName = "01/02/03/04/05/06/07/08/09/10/11/12/13/14/15/16/17/18/19/20/21/22/23/00";

    /**
     * 시간 콤보박스 생성시 사용하는 콤보값
     */
    public static final String CBHourValue = "01/02/03/04/05/06/07/08/09/10/11/12/13/14/15/16/17/18/19/20/21/22/23/00";

    /**
     * 검색일을 자동셋팅해주는 자바스크립트 함수 Html코드를 자동 생성하여 String 에 담아 리턴하는 Method
     *
     * @param String FormName - Form 이름
     * @param String CBName - 콤보박스명
     * @param String SearchSDName - 검색시작일 Text 태그명
     * @param String SearchEdName - 검색종료일 Text 태그명
     * @param String FuncTailName - 스크립트함수명 접미어
     * @param String AddTime - 추가검색기간
     * @return String - 스크립트 Html 코드
     * @name MkDateScript
     */
    public static String MkDateScript(String FormName, String CBName,
                                      String SearchSDName, String SearchEdName, String FuncTailName,
                                      String AddTime) {
        StringBuffer RtSB = new StringBuffer();

        RtSB.append("<script langage=\"javascript\">" + (char) 13);
        RtSB.append("function MkDateChange" + FuncTailName + "() { " +
                (char) 13);

        RtSB.append("  var StartDate = \"\";  " + (char) 13);
        RtSB.append("  var EndDate = \"\";  " + (char) 13);
        RtSB.append("  var Multichk = false;  " + (char) 13);
        RtSB.append("  var ComboValue = " + FormName + "." + CBName +
                ".value;  " + (char) 13);

        RtSB.append("       EndDate  = \"" + UtilManager.getToDate(0) + "\"; " +
                (char) 13);

        RtSB.append("   if(ComboValue == \"1D\"){ " + (char) 13);
        // 당일
        RtSB.append("       StartDate = \"" + UtilManager.getToDate(0) +
                "\"; " + (char) 13);

        RtSB.append("  } else if ( ComboValue == \"1W\"){ " + (char) 13);
        // 일주
        RtSB.append("       StartDate = \"" +
                UtilManager.getAddToDate("D", -7) + "\"; " + (char) 13);
        RtSB.append("  } else if ( ComboValue == \"1M\"){ " + (char) 13);
        // 한달
        RtSB.append("       StartDate = \"" +
                UtilManager.getAddToDate("M", -1) + "\"; " + (char) 13);
        RtSB.append("  } else if ( ComboValue == \"3M\"){ " + (char) 13);
        // 세달
        RtSB.append("       StartDate = \"" +
                UtilManager.getAddToDate("M", -3) + "\"; " + (char) 13);
        RtSB.append("  } else if ( ComboValue == \"0D\"){ " + (char) 13);

        // 6달
        if (AddTime.indexOf("6M") >= 0) {
            RtSB.append("  } else if ( ComboValue == \"6M\"){ " + (char) 13);
            RtSB.append("       StartDate = \"" +
                    UtilManager.getAddToDate("M", -6) + "\"; " + (char) 13);
        }

        RtSB.append("  }  else { " + (char) 13);
        RtSB.append("      Multichk = true;  " + (char) 13);
        RtSB.append("  }   " + (char) 13);

        RtSB.append("  if(Multichk == true) {  " + (char) 13);
        RtSB.append(FormName + ".MKDateSetIndex" + FuncTailName + ".value = " +
                FormName + "." + CBName + ".selectedIndex;" + (char) 13);
        RtSB.append("  } else { " + (char) 13);
        RtSB.append(FormName + "." + SearchSDName + ".value= StartDate; " +
                (char) 13);
        RtSB.append(FormName + "." + SearchEdName + ".value= EndDate; " +
                (char) 13);
        RtSB.append(FormName + "." + CBName + ".options[" + FormName +
                ".MKDateSetIndex" + FuncTailName + ".value].selected = true;" +
                (char) 13);
        RtSB.append("  }  " + (char) 13);

        RtSB.append("}" + (char) 13);
        RtSB.append("</script>" + (char) 13);

        return RtSB.toString();
    }

    /**
     * 검색일을 자동셋팅해주는 콤보박스Html코드를 자동 생성하여 String 에 담아 리턴하는 Method
     *
     * @param String MenuName - 검색명
     * @param String FormName - Form 이름
     * @param String SearchSDName - 검색시작일 Text 태그명
     * @param String SearchEdName - 검색종료일 Text 태그명
     * @return String - Html 코드
     * @name MKDateSet
     */
    public static String MKDateSet(String MenuName, String FormName,
                                   String SearchSDName, String SearchEdName) {
        StringBuffer RtSB = new StringBuffer();
        RtSB.append(MkDateScript(FormName, "MKDateSet", SearchSDName,
                SearchEdName, "", "") + (char) 13);
        RtSB.append(MKDateSetMake("MKDateSet", MenuName, "0D", "", "", ""));

        return RtSB.toString();
    }

    /**
     * 검색일 셋팅이 두개 이상일때 검색일을 자동셋팅해주는 콤보박스Html코드를 자동 생성하여 String 에 담아 리턴하는 Method
     *
     * @param String MenuName - 검색명
     * @param String FormName - Form 이름
     * @param String SearchSDName - 검색시작일 Text 태그명
     * @param String SearchEdName - 검색종료일 Text 태그명
     * @param String FuncTailName - 스크립트함수, 콤보박스명 접미어
     * @return String - Html 코드
     * @name MKDateSet
     */
    public static String MKDateSet(String MenuName, String FormName,
                                   String SearchSDName, String SearchEdName, String FuncTailName) {
        StringBuffer RtSB = new StringBuffer();
        RtSB.append(MkDateScript(FormName, "MKDateSet" + FuncTailName,
                SearchSDName, SearchEdName, FuncTailName, "") + (char) 13);
        RtSB.append(MKDateSetMake("MKDateSet" + FuncTailName, MenuName, "0D",
                "", FuncTailName, ""));

        return RtSB.toString();
    }

    /**
     * 검색일을 자동셋팅해주는 콤보박스Html코드를 자동 생성하여 String 에 담아 리턴하는 Method
     *
     * @param String MenuName - 검색명
     * @param String FormName - Form 이름
     * @param String SearchSDName - 검색시작일 Text 태그명
     * @param String SearchEdName - 검색종료일 Text 태그명
     * @param String FuncTailName - 스크립트함수, 콤보박스명 접미어
     * @param String AddTime - 추가검색일
     * @return String - Html 코드
     * @name MKDateSet
     */
    public static String MKDateSet(String MenuName, String FormName,
                                   String SearchSDName, String SearchEdName, String FuncTailName,
                                   String AddTime) {
        StringBuffer RtSB = new StringBuffer();
        RtSB.append(MkDateScript(FormName, "MKDateSet" + FuncTailName,
                SearchSDName, SearchEdName, FuncTailName, AddTime) + (char) 13);
        RtSB.append(MKDateSetMake("MKDateSet" + FuncTailName, MenuName, "0D",
                "", FuncTailName, AddTime));

        return RtSB.toString();
    }

    /**
     * 검색일 셋팅에서 의미있는 콤보박스 사용시 검색일을 자동셋팅해주는 콤보박스Html코드를 자동 생성하여 String 에 담아 리턴하는
     * Method
     *
     * @param String CBName - 콤보박스명
     * @param String MenuName - 콤보박스항목명
     * @param String MenuValue - 콤보박스항목값
     * @param String SelectedValue - 콤보박스 선택값
     * @param String FormName - From 이름
     * @param String SearchSDName - 검색시작일 Text 태그명
     * @param String SearchEdName - 검색종료일 Text 태그명
     * @param String FuncTailName - 스크립트함수, 콤보박스명 접미어
     * @return String - Html 코드
     * @name MKDateSet
     */
    public static String MKDateSet(String CBName, String MenuName,
                                   String MenuValue, String SelectedValue, String FormName,
                                   String SearchSDName, String SearchEdName, String FuncTailName) {
        StringBuffer RtSB = new StringBuffer();
        RtSB.append(MkDateScript(FormName, CBName, SearchSDName, SearchEdName,
                FuncTailName, "") + (char) 13);
        RtSB.append(MKDateSetMake(CBName, MenuName, MenuValue, SelectedValue,
                FuncTailName, ""));

        return RtSB.toString();
    }

    /**
     * 검색일 셋팅에 사용되는 콤보박스Html코드를 자동 생성하여 String 에 담아 리턴하는 Method
     *
     * @param String CBName - 콤보박스명
     * @param String MenuName - 콤보박스항목명
     * @param String MenuValue - 콤보박스항목값
     * @param String SelectedValue - 콤보박스 선택값
     * @param String FuncTailName - 콤보박스명 접미어
     * @param String AddTime - 추가검색기간
     * @return String - Html 코드
     * @name MKDateSetMake
     */
    public static String MKDateSetMake(String CBName, String MenuName,
                                       String MenuValue, String SelectedValue, String FuncTailName,
                                       String AddTime) {
        StringBuffer RtSB = new StringBuffer();

        String DateComboName = MenuName + "/당일/일주일/한달/세달";
        String DateComboValue = MenuValue + "/1D/1W/1M/3M";

        // 6달
        if (AddTime.indexOf("6M") >= 0) {
            DateComboName += "/여섯달";
            DateComboValue += "/6M";
        }

        RtSB.append("<input type=\"hidden\" id=\"MKDateSetIndex" +
                FuncTailName + "\"    name=\"MKDateSetIndex" + FuncTailName +
                "\"  value=\"0\">" + (char) 13);
        // style='background:#000251;color:ffffff;'
        RtSB.append("<select id=\"" + CBName + "\"  name=\"" + CBName +
                "\" onChange=\"javascript:MkDateChange" + FuncTailName + "();\">" +
                (char) 13);

        try {
            String[] sAName = DateComboName.split("/");
            String[] sAValue = DateComboValue.split("/");

            for (int i = 0; i < sAName.length; i++) {
                RtSB.append(Mk_Combo_one(sAName[i], sAValue[i], SelectedValue));
            }
        } catch (Exception e) {
            return "Exception : " + e.toString();
        }

        RtSB.append("</select>" + (char) 13);

        return RtSB.toString();
    }

    /**
     * 콤보박스로 사용했던 값의 명칭을 String 으로 Return 하는 Method
     *
     * @param String sComboArgName - 콤보박스 항목명
     * @param String sCobmArgValue - 콤보박스 항목값
     * @param String sSelectValue - 선택값
     * @param String sSeparator - 구분자
     * @return String - 값에 해당하는 항목
     * @name MkComboStrPrint
     */
    public static String MkComboStrPrint(String sComboArgName,
                                         String sCobmArgValue, String sSelectValue, String sSeparator) {
        return MkComboStr("", sComboArgName, sCobmArgValue, sSelectValue,
                sSeparator, "", "");
    }

    /**
     * 입력한 String 항목명과 항목값으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String sComboName - 콤보박스명
     * @param String sComboArgName - 구분자로 구분된 항목명 String [예: 전체/예/아니오 ]
     * @param String sCobmArgValue - 구분자로 구분된 항목값 String [예: All/Y/N ]
     * @param String sSelectValue - 항목선택값
     * @param String sSeparator - 구분자
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboStr
     */
    public static String MkComboStr(String sComboName, String sComboArgName,
                                    String sCobmArgValue, String sSelectValue, String sSeparator,
                                    String sClass) {
        return MkComboStr(sComboName, sComboArgName, sCobmArgValue,
                sSelectValue, sSeparator, sClass, "", "");
    }

    /**
     * 입력한 String 항목명과 항목값으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String sComboName - 콤보박스명
     * @param String sComboArgName - 구분자로 구분된 항목명 String [예: 전체/예/아니오 ]
     * @param String sCobmArgValue - 구분자로 구분된 항목값 String [예: All/Y/N ]
     * @param String sSelectValue - 항목선택값
     * @param String sSeparator - 구분자
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sOnchange - onChange 이벤트 발생시 호출할 스크립트 함수명
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboStr
     */
    public static String MkComboStr(String sComboName, String sComboArgName,
                                    String sCobmArgValue, String sSelectValue, String sSeparator,
                                    String sClass, String sOnchange) {
        return MkComboStr(sComboName, sComboArgName, sCobmArgValue,
                sSelectValue, sSeparator, sClass, sOnchange, "");
    }

    /**
     * 입력한 String 항목명과 항목값으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String sComboName - 콤보박스명
     * @param String sComboArgName - 구분자로 구분된 항목명 String [예: 전체/예/아니오 ]
     * @param String sCobmArgValue - 구분자로 구분된 항목값 String [예: All/Y/N ]
     * @param String sSelectValue - 항목선택값
     * @param String sSeparator - 구분자
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sOnchange - onChange 이벤트 발생시 호출할 스크립트 함수명
     * @param String sInitial - 상단에 들어갈 기본값
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboStr
     */
    public static String MkComboStr(String sComboName, String sComboArgName,
                                    String sCobmArgValue, String sSelectValue, String sSeparator,
                                    String sClass, String sOnchange, String sInitial) {
        return MkComboStr(sComboName, sComboArgName, sCobmArgValue,
                sSelectValue, sSeparator, sClass, sOnchange, sInitial, false);
    }

    /**
     * 입력한 String 항목명과 항목값으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String  sComboName - 콤보박스명
     * @param String  sComboArgName - 구분자로 구분된 항목명 String [예: 전체/예/아니오 ]
     * @param String  sCobmArgValue - 구분자로 구분된 항목값 String [예: All/Y/N ]
     * @param String  sSelectValue - 항목선택값
     * @param String  sSeparator - 구분자
     * @param String  sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String  sOnchange - onChange 이벤트 발생시 호출할 스크립트 함수명
     * @param String  sInitial - 상단에 들어갈 기본값
     * @param boolean sdisabled - 콤보박스 비활성화 여부 [true:비활성화, false:활성화]
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboStr
     */
    public static String MkComboStr(String sComboName, String sComboArgName,
                                    String sCobmArgValue, String sSelectValue, String sSeparator,
                                    String sClass, String sOnchange, String sInitial, boolean sdisabled) {
        int iACount = 0;
        int iVCount = 0;
        StringBuffer sRtnVal = new StringBuffer();

        try {
            String[] sAName = sComboArgName.split(sSeparator);
            String[] sAValue = sCobmArgValue.split(sSeparator);

            iACount = sAName.length;
            iVCount = sAValue.length;

            if (iACount != iVCount) {
                sRtnVal.append("ARG COUNT ERROR");
            } else {
                if (!sComboName.equals("")) {
                    sRtnVal.append("<select id=\"" + sComboName +
                            "\"  name=\"" + sComboName + "\" size=\"1\" ");

                    if (!sOnchange.equals("")) {
                        sRtnVal.append("onChange=\"" + sOnchange + "\" ");
                    }

                    if (!sClass.equals("")) {
                        sRtnVal.append("class=\"" + sClass + "\"");
                    }

                    if (sdisabled) {
                        sRtnVal.append(" disabled=true ");
                    }

                    sRtnVal.append(">" + (char) 13);

                    for (int i = 0; i < iACount; i++) {
                        sRtnVal.append(Mk_Combo_one(sAName[i], sAValue[i],
                                sSelectValue));
                    }

                    sRtnVal.append("</select>" + (char) 13);
                } else // Mk_Combo_Print 사용
                {
                    for (int i = 0; i < iACount; i++) {
                        if (sAValue[i].equals(sSelectValue)) {
                            sRtnVal.append(sAName[i]);

                            break;
                        }
                    }
                }
            }
        } catch (Exception E) {
            // CmnUtil.sClientScript("alert(\"" +
            // CmnUtil.sClientScriptMsgReplace(E.ToString()) + "\");");
        }

        return sRtnVal.toString();
    }

    /**
     * 콤보박스 항목 Html 코드를 자동생성 String 으로 Return 하는 Method
     *
     * @param String sItm_Name - 콤보박스 항목명
     * @param String sItm_Value - 콤보박스 항목값
     * @param String sSelected - 콤보박스 선택값
     * @return String - 생성된 Html 소스코드 String
     * @name Mk_Combo_one
     */
    private static String Mk_Combo_one(String sItm_Name, String sItm_Value,
                                       String sSelected) {
        String sSlt_Msg = null;
        String sRtnVal = null;
        String CompareStr = null;

        //LOGGER.debug("sItm_Name:" + sItm_Name);
        //LOGGER.debug("sItm_Value:" + sItm_Value);
        //LOGGER.debug("sSelected:" + sSelected);
        if (sSelected == null) {
            sSelected = "";
        }

        if (sItm_Value.indexOf(":") != -1) {
            CompareStr = sItm_Value.substring(0, sItm_Value.indexOf(":"));
        } else {
            CompareStr = sItm_Value.trim();
        }

        try {
            if (sSelected.equals(CompareStr)) {
                sSlt_Msg = "Selected";
            } else {
                sSlt_Msg = "";
            }

            sRtnVal = "  <option " + sSlt_Msg + " value=\"" + sItm_Value +
                    "\">" + sItm_Name + "</option>" + (char) 13;

            //LOGGER.debug("sRtnVal:" + sRtnVal);
        } catch (Exception e) {
            //LOGGER.debug("Exception [com.gtwk.common.utils.MkCombo.Mk_Combo_one]" + e.toString());
        }

        return sRtnVal;
    }

    /**
     * request 값이 null 일때 공백을 리턴하는 Method
     *
     * @param HttpServletRequest
     * @param String             -
     *                           request 명
     * @return String - request 값
     * @name StrRequest
     */
    public static String StrRequest(HttpServletRequest request, String ReqName) {
        String ReturnStr = "";

        try {
            if ((request.getParameter(ReqName) != null) &&
                    !request.getParameter(ReqName).equals("")) {
                ReturnStr = request.getParameter(ReqName);
            }
        } catch (Exception e) {
        } finally {
        }

        return ReturnStr;
    }

    /**
     * 입력 String 값이 null 일때 공백을 리턴하는 Method
     *
     * @param String -
     *               입력 String 명
     * @return String - 처리값
     * @name StrRequest
     */
    public static String StrRequest(String ReqName) {
        if (ReqName == null) {
            return "";
        } else {
            return ReqName;
        }
    }

    /**
     * 입력한 List 객체의 내용으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboList
     */
    public static String MkComboList(List ObjList, String pID, String pName,
                                     String sSelected, String sComboName) {
        return MkComboList(ObjList, pID, pName, "", sSelected, sComboName, "",
                "", "");
    }

    /**
     * 입력한 List 객체의 내용으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboList
     */
    public static String MkComboList(List ObjList, String pID, String pName,
                                     String sSelected, String sComboName, String sClass) {
        return MkComboList(ObjList, pID, pName, "", sSelected, sComboName,
                sClass, "", "");
    }

    /**
     * 입력한 List 객체의 내용으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sInitial - 콤보박스에 기본선택값으로 사용할 항목명/항목값
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboList
     */
    public static String MkComboList(List ObjList, String pID, String pName,
                                     String sSelected, String sComboName, String sClass, String sInitial) {
        return MkComboList(ObjList, pID, pName, "", sSelected, sComboName,
                sClass, sInitial, "");
    }

    /**
     * 입력한 List 객체의 내용으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String pAdd - List 객체에서 콤보항목추가값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sInitial - 콤보박스에 기본선택값으로 사용할 항목명/항목값
     * @param String sOnChange - onChange 이벤트 발생시 호출되는 스크립트 함수
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboList
     */
    public static String MkComboList(List ObjList, String pID, String pName,
                                     String pAdd, String sSelected, String sComboName, String sClass,
                                     String sInitial, String sOnChange) {
        return MkComboList(ObjList, pID, pName, pAdd, sSelected, sComboName,
                sClass, sInitial, sOnChange, 0);
    }

    /**
     * 입력한 List 객체의 내용으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String pAdd - List 객체에서 콤보항목추가값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sInitial - 콤보박스에 기본선택값으로 사용할 항목명/항목값
     * @param String sOnChange - onChange 이벤트 발생시 호출되는 스크립트 함수
     * @param int    sNamelength - 콤보항목명의 보여줄 문자열 길이
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboList
     */
    public static String MkComboList(List ObjList, String pID, String pName,
                                     String pAdd, String sSelected, String sComboName, String sClass,
                                     String sInitial, String sOnChange, int sNamelength) {
        return MkComboList(ObjList, pID, pName, pAdd, sSelected, sComboName,
                sClass, sInitial, sOnChange, sNamelength, false);
    }

    /**
     * 입력한 List 객체의 내용으로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List    ObjList - 입력된 List 객체
     * @param String  pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String  pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String  pAdd - List 객체에서 콤보항목추가값에 해당하는 이름
     * @param String  sSelected - 선택값
     * @param String  sComboName - 콤보박스명
     * @param String  sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String  sInitial - 콤보박스에 기본선택값으로 사용할 항목명/항목값
     * @param String  sOnChange - onChange 이벤트 발생시 호출되는 스크립트 함수
     * @param int     sNamelength - 콤보항목명의 보여줄 문자열 길이
     * @param boolean sdisabled - 콤보박스 비활성화 여부 [true: 비활성화, false:활성화]
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboList
     */
    public static String MkComboList(List ObjList, String pID, String pName,
                                     String pAdd, String sSelected, String sComboName, String sClass,
                                     String sInitial, String sOnChange, int sNamelength, boolean sdisabled) {
        StringBuffer sRtnValSB = new StringBuffer();
        String sAName = null;
        String sAValue = null;

        sSelected = StrRequest(sSelected); // Selected String null 체크
        //LOGGER.debug("MkComboList called...!");

        try {
            if (ObjList != null) {
                //LOGGER.debug("ObjList not null...!");
                sRtnValSB.append("<select id=\"" + sComboName + "\"  name=\"" +
                        sComboName + "\" size=\"1\" ");

                if (!sOnChange.equals("")) {
                    sRtnValSB.append("onChange=\"" + sOnChange + "\" ");
                }

                if (!sClass.equals("")) {
                    sRtnValSB.append("class=\"" + sClass + "\"");
                }

                if (sdisabled) {
                    sRtnValSB.append(" disabled=true ");
                }

                sRtnValSB.append(">" + (char) 13);

                if (!sInitial.equals("")) {
                    String[] Strvalues = sInitial.split("/", 2);
                    sRtnValSB.append(Mk_Combo_one(Strvalues[0], Strvalues[1],
                            sSelected));
                }

                Iterator ItDynaObj = ObjList.iterator();

                while (ItDynaObj.hasNext()) {
                    BasicDynaBean MainRS = (BasicDynaBean) ItDynaObj.next();

                    sAValue = "";

                    try {
                        sAValue = (MainRS.get(pID)).toString();
                    } catch (Exception e) {
                        LOGGER.debug("e" + e.toString());
                        sAValue = "0";
                    }

                    sAName = "";

                    try {
                        sAName = (MainRS.get(pName)).toString();

                        if ((sNamelength > 0) &&
                                (sNamelength < sAName.length())) {
                            sAName = sAName.substring(0, sNamelength) + "...";
                        }
                    } catch (Exception e) {
                        LOGGER.debug("e" + e.toString());
                        sAName = "";
                    }

                    try {
                        if (!pAdd.equals("")) {
                            sAValue += (":" + (MainRS.get(pAdd)).toString());
                        }
                    } catch (Exception e) {
                        LOGGER.debug("e" + e.toString());
                        sAValue = "";
                    }

                    MainRS = null;

                    sRtnValSB.append(Mk_Combo_one(sAName, sAValue, sSelected));
                }

                ItDynaObj = null;

                sRtnValSB.append("</select>" + (char) 13);
            }

            //LOGGER.debug("MkComboList out...!");
        } catch (Exception e) {
            LOGGER.debug("e" + e.toString());
            return null;
        } finally {
            ObjList = null;
        }

        return sRtnValSB.toString();
    }

    /**
     * 입력한 List 객체의 내용으로 멀티 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String pAdd - List 객체에서 콤보항목추가값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sInitial - 콤보박스에 기본선택값으로 사용할 항목명/항목값
     * @param String sOnChange - onChange 이벤트 발생시 호출되는 스크립트 함수
     * @param int    sSize - 콤보박스에 보여줄 항목갯수
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboMultiList
     */
    public static String MkComboMultiList(List ObjList, String pID,
                                          String pName, String pAdd, String sSelected, String sComboName,
                                          String sClass, String sInitial, String sOnChange, int sSize) {
        return MkComboMultiList(ObjList, pID, pName, pAdd, sSelected,
                sComboName, sClass, sInitial, sOnChange, sSize, 0);
    }

    /**
     * 입력한 List 객체의 내용으로 멀티 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 콤보항목명에 해당하는 이름
     * @param String pName - List 객체에서 콤보항목값에 해당하는 이름
     * @param String pAdd - List 객체에서 콤보항목추가값에 해당하는 이름
     * @param String sSelected - 선택값
     * @param String sComboName - 콤보박스명
     * @param String sClass - 콤보박스에 적용할 스타일 클래스명
     * @param String sInitial - 콤보박스에 기본선택값으로 사용할 항목명/항목값
     * @param String sOnChange - onChange 이벤트 발생시 호출되는 스크립트 함수
     * @param int    sSize - 콤보박스에 보여줄 항목갯수
     * @param int    sNamelength - 콤보항목명의 보여줄 문자열 길이
     * @return String - 생성된 Html 소스코드 String
     * @name MkComboMultiList
     */
    public static String MkComboMultiList(List ObjList, String pID,
                                          String pName, String pAdd, String sSelected, String sComboName,
                                          String sClass, String sInitial, String sOnChange, int sSize,
                                          int sNamelength) {
        StringBuffer sRtnValSB = new StringBuffer();
        String sAName = null;
        String sAValue = null;

        sSelected = StrRequest(sSelected); // Selected String null 체크

        try {
            if (ObjList != null) {
                sRtnValSB.append("<select multiple  id=\"" + sComboName +
                        "\"  name=\"" + sComboName + "\" size=\"" + sSize + "\"");

                if (!sOnChange.equals("")) {
                    sRtnValSB.append("onChange=\"" + sOnChange + "\" ");
                }

                if (!sClass.equals("")) {
                    sRtnValSB.append("class=\"" + sClass + "\"");
                }

                sRtnValSB.append(">" + (char) 13);

                if (!sInitial.equals("")) {
                    String[] Strvalues = sInitial.split("/", 2);
                    sRtnValSB.append(Mk_Combo_one(Strvalues[0], Strvalues[1],
                            sSelected));
                }

                Iterator ItDynaObj = ObjList.iterator();

                while (ItDynaObj.hasNext()) {
                    BasicDynaBean MainRS = (BasicDynaBean) ItDynaObj.next();

                    sAValue = "";

                    try {
                        sAValue = (MainRS.get(pID)).toString();
                    } catch (Exception e) {
                        LOGGER.debug("e" + e.toString());
                        sAValue = "0";
                    }

                    sAName = "";

                    try {
                        sAName = (MainRS.get(pName)).toString();

                        if ((sNamelength > 0) &&
                                (sNamelength < sAName.length())) {
                            sAName = sAName.substring(0, sNamelength) + "...";
                        }
                    } catch (Exception e) {
                        LOGGER.debug("e" + e.toString());
                        sAName = "";
                    }

                    try {
                        if (!pAdd.equals("")) {
                            sAValue += (":" + (MainRS.get(pAdd)).toString());
                        }
                    } catch (Exception e) {
                        LOGGER.debug("e" + e.toString());
                        sAValue = "";
                    }

                    MainRS = null;

                    sRtnValSB.append(Mk_Combo_one(sAName, sAValue, sSelected));
                }

                ItDynaObj = null;

                sRtnValSB.append("</select>" + (char) 13);
            }
        } catch (Exception e) {
            LOGGER.debug("e" + e.toString());
            return null;
        } finally {
            ObjList = null;
        }

        return sRtnValSB.toString();
    }

    /**
     * List 객체를 입력 받아 MkCombo 스트링에 이용할 String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String getName - 변환할 List Value 값
     * @return String - 반환할 String [데이터1/데이터2 ....]
     * @name ListToString
     */
    public static String ListToString(List objList, String getName) {
        StringBuffer sRtnValSB = new StringBuffer();
        int loopCnt = 0;

        if (objList != null) {
            Iterator ItDynaObj = objList.iterator();

            while (ItDynaObj.hasNext()) {
                loopCnt++;

                BasicDynaBean MainRS = (BasicDynaBean) ItDynaObj.next();

                if (loopCnt == 1) {
                    sRtnValSB.append(MainRS.getString(getName, ""));
                } else {
                    sRtnValSB.append("/" + MainRS.getString(getName, ""));
                }

                MainRS = null;
            }
        } else {
            sRtnValSB.append("리스트값 추출실패");
        }

        return sRtnValSB.toString();
    }

    /**
     * 문자열을 입력받아 공백문자거나 Null 문자일경우 True 를 리턴하는 Method
     *
     * @param String sValue - 입력 String 객체
     * @return boolean - 처리 Boolean 값
     * @name StrEmptyChk
     */
    public static boolean StrEmptyChk(String sValue) {
        boolean bResult = false;

        if (sValue == null) {
            bResult = true;
        } else {
            if (sValue.trim().length() == 0) {
                bResult = true;
            }
        }

        return bResult;
    }

    /**
     * 입력한 List 객체의 내용으로 라디오버튼 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param List   ObjList - 입력된 List 객체
     * @param String pID - List 객체에서 라디오버튼 명에 해당하는 이름
     * @param String pName - List 객체에서 라디오버튼 값에 해당하는 이름
     * @param String sComboName - 라디오버튼명
     * @param String sSelectValue - 체크값
     * @param String sClass - 라디오버튼에 적용할 스타일 클래스명
     * @param String sOnclick - onClick 이벤트 발생시 호출되는 스크립트 함수
     * @return String - 생성된 Html 소스코드 String
     * @name MkRadioList
     */
    public static String MkRadioList(List ObjList, String pID, String pName,
                                     String sComboName, String sSelectValue, String sClass, String sOnclick) {
        StringBuffer sRtnValSB = new StringBuffer();
        String sAName = null;
        String sAValue = null;

        try {
            if (!sComboName.equals("")) {
                // Radio 생성
                if (ObjList != null) {
                    Iterator ItDynaObj = ObjList.iterator();

                    while (ItDynaObj.hasNext()) {
                        BasicDynaBean MainRS = (BasicDynaBean) ItDynaObj.next();

                        sAValue = "";

                        try {
                            sAValue = (MainRS.get(pID)).toString();
                        } catch (Exception e) {
                            LOGGER.debug("e" + e.toString());
                            sAValue = "0";
                        }

                        sAName = "";

                        try {
                            sAName = (MainRS.get(pName)).toString();
                        } catch (Exception e) {
                            LOGGER.debug("e" + e.toString());
                            sAName = "";
                        }

                        if (sSelectValue.equals(sAValue)) {
                            sRtnValSB.append("<input id=\"" + sComboName +
                                    "\"  name=\"" + sComboName +
                                    "\"  type=\"radio\"  value=\"" + sAValue +
                                    "\"  class=\"" + sClass + "\"  checked");
                        } else {
                            sRtnValSB.append("<input id=\"" + sComboName +
                                    "\"  name=\"" + sComboName +
                                    "\"  type=\"radio\"  value=\"" + sAValue +
                                    "\"  class=\"" + sClass + "\"");
                        }

                        if (!sOnclick.equals("")) {
                            sRtnValSB.append("  onClick=\"" + sOnclick + "\" ");
                        }

                        sRtnValSB.append(">" + sAName + "&nbsp;");
                        MainRS = null;
                    } // while END
                }
            } else {
                // 프린트
                if (ObjList != null) {
                    Iterator ItDynaObj = ObjList.iterator();

                    while (ItDynaObj.hasNext()) {
                        BasicDynaBean MainRS = (BasicDynaBean) ItDynaObj.next();

                        sAValue = "";

                        try {
                            sAValue = (MainRS.get(pID)).toString();
                        } catch (Exception e) {
                            LOGGER.debug("e" + e.toString());
                            sAValue = "0";
                        }

                        sAName = "";

                        try {
                            sAName = (MainRS.get(pName)).toString();
                        } catch (Exception e) {
                            LOGGER.debug("e" + e.toString());
                            sAName = "";
                        }

                        if (sSelectValue.equals(sAValue)) {
                            sRtnValSB.append(sAName);
                        }

                        MainRS = null;
                    } // while END
                }
            }

            return sRtnValSB.toString();
        } catch (Exception e) {
            LOGGER.debug("e" + e.toString());
            return null;
        } finally {
            ObjList = null;
        }
    }

    /**
     * 입력한 String 항목명과 항목값으로 라디오버튼 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String sComboName - 라디오버튼 객체명
     * @param String sComboArgName - 라디오버튼 항목명
     * @param String sCobmArgValue - 라디오보튼 항목값
     * @param String sSelectValue - 체크값
     * @param String sSeparator - 구분자
     * @param String sClass - 적용할 스크립트 클래스명
     * @param String sOnclick - onClick 이벤트 발생시 호출할 스크립트 함수명
     * @return String - 생성된 Html 소스코드 String
     * @name MkRadioStr
     */
    public static String MkRadioStr(String sComboName, String sComboArgName,
                                    String sCobmArgValue, String sSelectValue, String sSeparator,
                                    String sClass, String sOnclick) {
        int iACount = 0;
        int iVCount = 0;
        StringBuffer sRtnVal = new StringBuffer();

        try {
            String[] sAName = sComboArgName.split(sSeparator);
            String[] sAValue = sCobmArgValue.split(sSeparator);

            iACount = sAName.length;
            iVCount = sAValue.length;

            if (iACount != iVCount) {
                sRtnVal.append("ARG COUNT ERROR");
            } else {
                if (!sComboName.equals("")) {
                    for (int i = 0; i < iACount; i++) {
                        if (sSelectValue.equals(sAValue[i])) {
                            sRtnVal.append("<input id=\"" + sComboName +
                                    "\"  name=\"" + sComboName +
                                    "\"  type=\"radio\"  value=\"" + sAValue[i] +
                                    "\"  class=\"" + sClass + "\"  checked");
                        } else {
                            sRtnVal.append("<input id=\"" + sComboName +
                                    "\"  name=\"" + sComboName +
                                    "\"  type=\"radio\"  value=\"" + sAValue[i] +
                                    "\"  class=\"" + sClass + "\"");
                        }

                        if (!sOnclick.equals("")) {
                            sRtnVal.append("  onClick=\"" + sOnclick + "\" ");
                        }

                        sRtnVal.append(">" + sAName[i] + "&nbsp;");
                    }
                } else // Mk_Combo_Print 사용
                {
                    for (int i = 0; i < iACount; i++) {
                        if (sAValue[i].equals(sSelectValue)) {
                            sRtnVal.append(sAName[i]);

                            break;
                        }
                    }
                }
            }
        } catch (Exception E) {
            // CmnUtil.sClientScript("alert(\"" +
            // CmnUtil.sClientScriptMsgReplace(E.ToString()) + "\");");
        }

        return sRtnVal.toString();
    }

    /**
     * 입력한 String 항목명과 항목값으로 체크박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String sComboName - 라디오버튼 객체명
     * @param String sComboArgName - 라디오버튼 항목명
     * @param String sCobmArgValue - 라디오보튼 항목값
     * @param String sSelectValue - 체크값
     * @param String sSeparator - 구분자
     * @param String sClass - 적용할 스크립트 클래스명
     * @param String sOnchange - onChange 이벤트 발생시 호출할 스크립트 함수명
     * @return String - 생성된 Html 소스코드 String
     * @name MkChkBoxStr
     */
    public static String MkChkBoxStr(String sComboName, String sComboArgName,
                                     String sCobmArgValue, String sSelectValue, String sSeparator,
                                     String sClass, String sOnchange) {
        return MkChkBoxStr(sComboName, sComboArgName, sCobmArgValue,
                sSelectValue, sSeparator, sClass, sOnchange, 0);
    }

    /**
     * 입력한 String 항목명과 항목값으로 체크박스 Html 코드를 자동생성, String 으로 Return 하는 Method
     *
     * @param String sComboName - 라디오버튼 객체명
     * @param String sComboArgName - 라디오버튼 항목명
     * @param String sCobmArgValue - 라디오보튼 항목값
     * @param String sSelectValue - 체크값
     * @param String sSeparator - 구분자
     * @param String sClass - 적용할 스크립트 클래스명
     * @param String sOnchange - onChange 이벤트 발생시 호출할 스크립트 함수명
     * @param int    sBRCnt - 한줄에 표현될 항목수
     * @return String - 생성된 Html 소스코드 String
     * @name MkChkBoxStr
     */
    public static String MkChkBoxStr(String sComboName, String sComboArgName,
                                     String sCobmArgValue, String sSelectValue, String sSeparator,
                                     String sClass, String sOnchange, int sBRCnt) {
        int iACount = 0;
        int iVCount = 0;
        int iSCount = 0;
        StringBuffer sRtnVal = new StringBuffer();
        boolean ChkValue = false;

        try {
            String[] sAName = sComboArgName.split(sSeparator);
            String[] sAValue = sCobmArgValue.split(sSeparator);
            String[] sSValue = sSelectValue.split(sSeparator);

            iACount = sAName.length;
            iVCount = sAValue.length;
            iSCount = sSValue.length;

            if (iACount != iVCount) {
                sRtnVal.append("ARG COUNT ERROR");
            } else {
                if (!sComboName.equals("")) {
                    for (int i = 0; i < iACount; i++) {
                        ChkValue = false;

                        for (int j = 0; j < iSCount; j++) {
                            if (sSValue[j].equals(sAValue[i])) {
                                ChkValue = true;

                                break;
                            }
                        }

                        if (sBRCnt != 0) {
                            if ((i % sBRCnt) == 0) {
                                sRtnVal.append("<br>");
                            }
                        }

                        if (ChkValue) {
                            sRtnVal.append("<input id=\"" + sComboName +
                                    "\"   name=\"" + sComboName +
                                    "\"  type=\"checkbox\"  value=\"" + sAValue[i] +
                                    "\" class=\"" + sClass + "\"  checked");
                        } else {
                            sRtnVal.append("<input id=\"" + sComboName +
                                    "\"  name=\"" + sComboName +
                                    "\"  type=\"checkbox\"   value=\"" +
                                    sAValue[i] + "\" class=\"" + sClass + "\"");
                        }

                        if (!sOnchange.equals("")) {
                            sRtnVal.append("onClick=\"" + sOnchange + "\" ");
                        }

                        sRtnVal.append(">" + sAName[i] + "&nbsp;");
                    }
                } else // Mk_Combo_Print 사용
                {
                    for (int i = 0; i < iACount; i++) {
                        if (sAValue[i].equals(sSelectValue)) {
                            sRtnVal.append(sAName[i]);

                            break;
                        }
                    }
                }
            }
        } catch (Exception E) {
            // CmnUtil.sClientScript("alert(\"" +
            // CmnUtil.sClientScriptMsgReplace(E.ToString()) + "\");");
        }

        return sRtnVal.toString();
    }

    /**
     * 입력한 정보로 Query 를 생성해 DB정보로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method(DB2사용)
     *
     * @name MkComboDB
     * @param String
     *            sTable - 테이블명
     * @param String
     *            sName - 항목명에 해당하는 필드명
     * @param String
     *            sValue - 항목값에 해당하는 필드명
     * @param String
     *            sWhere - Where 조건
     * @param String
     *            sOrderBy - Order By
     * @param String
     *            sSelected - 선택값
     * @param String
     *            sComboName - 콤보박스명
     * @param int
     *            iVl_size - 콤보박스크기
     * @param String
     *            sInitial - 콤보박스 상단에 들어갈 기본항목명과 항목값
     * @return String - 생성된 Html 소스코드 String
     */

    //	public static String MkComboDB2(String sTable, String sName, String sValue,
    //			String sWhere, String sOrderBy, String sSelected,
    //			String sComboName, int iVl_size, String sInitial) {
    //		return MkComboDB2(sTable, sName, sValue, sWhere, sOrderBy, sSelected,
    //				sComboName, iVl_size, sInitial, "");
    //	}

    /**
     * 입력한 정보로 Query 를 생성해 DB정보로 콤보박스 Html 코드를 자동생성, String 으로 Return 하는 Method(DB2사용)
     *
     * @name MkComboDB
     * @param String
     *            sTable - 테이블명
     * @param String
     *            sName - 항목명에 해당하는 필드명
     * @param String
     *            sValue - 항목값에 해당하는 필드명
     * @param String
     *            sWhere - Where 조건
     * @param String
     *            sOrderBy - Order By
     * @param String
     *            sSelected - 선택값
     * @param String
     *            sComboName - 콤보박스명
     * @param int
     *            iVl_size - 콤보박스크기
     * @param String
     *            sInitial - 콤보박스 상단에 들어갈 기본항목명과 항목값
     * @param String
     *            sOnChange - onChange 이벤트 발생시 호출되는 스크립트함수명
     * @return String - 생성된 Html 소스코드 String
     */

    //	public static String MkComboDB2(String sTable, String sName, String sValue,
    //			String sWhere, String sOrderBy, String sSelected,
    //			String sComboName, int iVl_size, String sInitial, String sOnChange) {
    //		Connection DBCONN = null;
    //		PreparedStatement DBPSTMT = null;
    //		ResultSet DBRS = null;
    //
    //		StringBuffer SqlSB = new StringBuffer();
    //		StringBuffer sRtnValSB = new StringBuffer();
    //		DBConnectionManager connMgr = new DBConnectionManager();
    //
    //		SqlSB.append(" SELECT ");
    //		SqlSB.append("         " + sName + " as CbName ");
    //		SqlSB.append("       , " + sValue + " as CbValue \n");
    //		SqlSB.append(" FROM \n");
    //		SqlSB.append("         " + sTable);
    //
    //		if (!StrEmptyChk(sWhere)) {
    //			SqlSB.append(" WHERE \n");
    //			SqlSB.append("         " + sWhere);
    //		}
    //
    //		if (!StrEmptyChk(sOrderBy)) {
    //			SqlSB.append(" ORDER BY \n");
    //			SqlSB.append("         " + sOrderBy);
    //		}
    //		SqlSB.append("\n WITH UR ");
    //
    //		 //logger.info("\n" + "MkComboDB2 call...:" + SqlSB.toString());
    //
    //		try {
    //
    //			DBCONN = connMgr.getDB2Connection();
    //			DBPSTMT = DBCONN.prepareStatement(SqlSB.toString());
    //			DBRS = DBPSTMT.executeQuery();
    //
    //			if (StrEmptyChk(sOnChange))
    //				sRtnValSB.append("<select id=\"" + sComboName + "\"  name=\""
    //						+ sComboName + "\" size=\"1\">" + (char) 13);
    //			else
    //				sRtnValSB.append("<select id=\"" + sComboName + "\"  name=\""
    //						+ sComboName + "\" size=\"1\" onChange=\"" + sOnChange
    //						+ "\">" + (char) 13);
    //
    //
    //			//LOGGER.debug("sInitial:" + sInitial);
    //			if (!sInitial.equals("")) {
    //				String[] Strvalues = sInitial.split("/", 2);
    //				//LOGGER.debug("Strvalues[0]:" + Strvalues[0]);
    //				//LOGGER.debug("Strvalues[1]:" + Strvalues[1]);
    //				sRtnValSB.append(Mk_Combo_one(Strvalues[0], Strvalues[1],
    //						sSelected));
    //			}
    //			//LOGGER.debug("FFFFFFFFFFFFFF");
    //
    //			while (DBRS.next()) {
    //				//LOGGER.debug("DBRS.getString(CbName):"+DBRS.getString("CbName"));
    //				sRtnValSB.append(Mk_Combo_one(DBRS.getString("CbName"), DBRS.getString("CbValue"), sSelected));
    //				//LOGGER.debug("rrrrrrrrrrrrrrrr");
    //			}
    //
    //			sRtnValSB.append("</select>" + (char) 13);
    //
    //			if (DBRS != null)
    //				try {
    //					DBRS.close();
    //				} catch (Exception E_DBRS) {
    //				}
    //			if (DBPSTMT != null)
    //				try {
    //					DBPSTMT.close();
    //				} catch (Exception E_DBPSTMT) {
    //				}
    //			if (DBCONN != null)
    //				try {
    //					DBCONN.close();
    //				} catch (Exception E_DBCONN) {
    //				}
    //		} catch (SQLException e) {
    //			 //logger.info("\n" + "MKCombo2 SQLException : " + e.toString());
    //		} catch (NullPointerException e) {
    //			 //logger.info("\n" + "MKCombo2 NullPointerException : " + e.toString());
    //		} catch (Exception e) {
    //			 //logger.info("\n" + "MKCombo2 Exception : " + e.toString());
    //		} finally {
    //			 //logger.info("\n" + "MKCombo2 finally ----------------- ");
    //			if (DBRS != null)
    //				try {
    //					 //logger.info("\n" + "MKCombo2 finally DBRS.close() ");
    //					DBRS.close();
    //				} catch (Exception E_DBRS) {
    //				}
    //			if (DBPSTMT != null)
    //				try {
    //					 //logger.info("\n" + "MKCombo2 finally DBPSTMT.close() ");
    //					DBPSTMT.close();
    //				} catch (Exception E_DBPSTMT) {
    //				}
    //			if (DBCONN != null)
    //				try {
    //					 //logger.info("\n" + "MKCombo2 finally DBCONN.close() ");
    //					DBCONN.close();
    //				} catch (Exception E_DBCONN) {
    //				}
    //
    //
    //		}
    //
    //		return sRtnValSB.toString();
    //	}
}
