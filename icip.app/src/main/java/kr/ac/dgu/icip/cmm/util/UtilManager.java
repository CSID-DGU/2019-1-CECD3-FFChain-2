package kr.ac.dgu.icip.cmm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import kr.ac.dgu.base.cmm.util.FileMngUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.postgresql.util.UnixCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * 1. 개요 : UtilManager 클래스 
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
public class UtilManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilManager.class);

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected static EgovPropertyService propertiesService;   
    
    /** fileMngUtil */
    @Resource(name = "fileMngUtil")
    private static FileMngUtil fileMngUtil;
    
    public static final String DEFAULT_DATASOURCE = "datasource";
    //20091208 jrkim 제거
    //private static Logger logger = Logger.getLogger(UtilManager.class);
    private static TimeZone tmpTimeZone = TimeZone.getDefault();

    static {
        tmpTimeZone.setID("Asia/Tokyo");
        TimeZone.setDefault(tmpTimeZone);
    }

    /**
     * 서버의 time zone
     *
     * @see java.util.TimeZone
     */
    public final static TimeZone EBPP_TIMEZONE = TimeZone.getDefault();

    /**
     * 서버 시스템에서 제공하는 시간의 오차값 (in minute) <BR>
     * &nbsp;&nbsp;Servlet 안에서 new java.util.Date()로 얻은 시간은 어떤 방법을 써도 실제 현지 시간과는
     * 오차를 보이고 있다.&nbsp;&nbsp;이상하게도 SimpleDataFormat class의 사용에서 timezone이 어떠한
     * 방법을 써서도 변경되지 않기 때문이다.&nbsp;&nbsp; 현재 timezone 값은 미국동부에 맞추어져
     * 있다.&nbsp;&nbsp;System Property를 변경한다 하여도 실제 SimpleDataFormat 등에서는 변경된 값을
     * 쓰지 않고 있다.&nbsp;&nbsp;미국은 summer-time이 적용되고 있으므로 아래의 값도 여기에 따라 바뀌어야 한다.
     */

    // public final static int EBPP_TIME_OFFSET = 780; // 13시간
    public final static int EBPP_TIME_OFFSET = 0; // 13시간

    public UtilManager() {
    }

    /***************************************************************************
     * 페이지제어 관련 함수모음
     **************************************************************************/

    /**
     * 기능 : 페이지 네비게이션 제어하기
     *
     * @param ls_currentPage 현재 페이지 번호
     * @param ls_totalPage   총 페이지 수
     * @return String 페이지 제어결과를 HTML로 넘김
     */
    public String getPageList(int i, int j) /*
                                             * (int ls_currentPage, int
                                             * ls_totalPage)
                                             */ {
        int k = 1;
        int l = 0;
        byte byte0 = 10;
        StringBuffer stringbuffer = new StringBuffer("");
        k = (((i - 1) / byte0) * byte0) + 1;

        int i1 = k - 1;

        if (i1 < 1) {
            i1 = 1;
        }

        stringbuffer.append("<div class=\"paging\">");

        if (k > 1) {
            if (k > byte0) {
                stringbuffer.append("\n<a href=\"javascript:jsGoPages(1); \"><img src=\"/imgNew/ico_list_first.png\" class=\"vaM\"></a>");
            }

            stringbuffer.append( "\n<a href=\"javascript:jsGoPages(" + i1 + ");\"><img src=\"/imgNew/ico_list_prev.png\" class=\"vaM\"></a>");
        } else {
            stringbuffer.append( "\n<img src=\"/imgNew/ico_list_prev.png\" class=\"vaM\">");
        }

        for (l = k; l < (k + byte0); l++) {
            if (l == (j + 1)) {
                break;
            }

            if (l != k) {
                //s2 = "&nbsp; /";
            }

            if (i == l) { //현재페이지 = 페이징페이지
                stringbuffer.append("\n[<a class=\"onPage\">" + l + "</a>]");
            }
            else {
                stringbuffer.append("\n[<a href=\"javascript:jsGoPages(" + l + ");\">" + l + "</a>]");
            }

            //}
        }

        if (l > j) {
            stringbuffer.append( "\n<img src=\"/imgNew/ico_list_next.png\" class=\"vaM\">");
        } else {
            stringbuffer.append("\n<a href=\"javascript:jsGoPages(" + l + ");\"><img src=\"/imgNew/ico_list_next.png\" class=\"vaM\"></a>");
            stringbuffer.append("\n<a href=\"javascript:jsGoPages(" + j + ");\"><img src=\"/imgNew/ico_list_last.png\" class=\"vaM\"></a>");
        }
        
        stringbuffer.append("</div>");

        return stringbuffer.toString();
    }

    public static String getPageList(int i, int j, String function) {
        int k = 1;
        int l = 0;
        byte byte0 = 10;
        StringBuffer stringbuffer = new StringBuffer("");
        k = (((i - 1) / byte0) * byte0) + 1;

        int i1 = k - 1;

        if (i1 < 1) {
            i1 = 1;
        }

        stringbuffer.append("<div class='paging'>");

        if (k > 1) {
            if (k > byte0) {
                stringbuffer.append("<a href='javascript:" + function + "(1); '><img src='/imgNew/ico_list_first.png' class='vaM'></a>");
            }

            stringbuffer.append( "<a href='javascript:" + function + "(" + i1 + ");'><img src='/imgNew/ico_list_prev.png' class='vaM'></a>");
        } else {
            stringbuffer.append( "<img src='/imgNew/ico_list_prev.png' class='vaM'>");
        }

        for (l = k; l < (k + byte0); l++) {
            if (l == (j + 1)) {
                break;
            }

            if (l != k) {
                //s2 = "&nbsp; /";
            }

            if (i == l) { //현재페이지 = 페이징페이지
                stringbuffer.append("[<a class='onPage'>" + l + "</a>]");
            }
            else {
                stringbuffer.append("[<a href='javascript:" + function + "(" + l + ");'>" + l + "</a>]");
            }

            //}
        }

        if (l >= j) {
            stringbuffer.append( "<img src='/imgNew/ico_list_next.png' class='vaM'>");
        } else {
            stringbuffer.append("<a href='javascript:" + function + "(" + l + ");'><img src='/imgNew/ico_list_next.png' class='vaM'></a>");
            stringbuffer.append("<a href='javascript:" + function + "(" + j + ");'><img src='/imgNew/ico_list_last.png' class='vaM'></a>");
        }
        
        stringbuffer.append("</div>");

        return stringbuffer.toString();
    }
    
    /*
     * History : 2004/08/05, fire73, Created Version : 1.0 Comment : request로 부터
     *
     * parameter값을 가져올때 null exception 발생을 방지
     * -------------------------------------------------- obj_Src : source
     * Object --------------------------------------------------
     */
    public static String safeGetString(Object obj_Src) {
        if (obj_Src == null) {
            return "";
        } else {
            // //System.out.println("not null");
            return replace((String) obj_Src, "'", "''");

            // return (String) obj_Src;
        }
    }

    /**
     * 문자열(s) 중에서 특정 문자열(old)을 찾아서 원하는 문자열(replacement)을 변환한다.
     *
     * @param s   :
     *            원본 String 문자열
     * @param old :
     *            찾는고 하는 문자열
     * @return String : 바뀌 문자열
     */
    public static String replace(String s, String old, String replacement) {
        int i = s.indexOf(old);
        StringBuffer r = new StringBuffer();

        if (i == -1) {
            return s;
        }

        r.append(s.substring(0, i) + replacement);

        if ((i + old.length()) < s.length()) {
            r.append(replace(s.substring(i + old.length(), s.length()), old,
                    replacement));
        }

        return r.toString();
    }

    /**
     * 오늘날짜를 String 형으로 반환
     *
     * @param int
     * @return String
     */
    public static String getToDate(int FormatInt) {
        Date dt = new Date();
        String FormatStr = "yyyy-MM-dd";

        if (FormatInt == 1) {
            FormatStr = "yyyy-MM-dd HH:mm:ss";            
        } else  if (FormatInt == 2) {         	
        	FormatStr = "yyyyMMddHHmmss";       
        }

       	//System.out.println("FormatStr===" + FormatStr  );
       	
       	SimpleDateFormat df = new SimpleDateFormat(FormatStr);

        //System.out.println("df===" + df  );
        
        return df.format(dt);
    }

    /**
     * 지정된 기간 이후의 날짜반환
     *
     * @param String
     * @param int
     * @return String
     */
    public static String getAddToDate(String AddFlag, int AddValue) {
        Calendar rightNow = Calendar.getInstance();

        if (AddFlag.equals("Y")) {
            rightNow.add(Calendar.YEAR, AddValue);
        } else if (AddFlag.equals("M")) {
            rightNow.add(Calendar.MONTH, AddValue);
        } else if (AddFlag.equals("D")) {
            rightNow.add(Calendar.DATE, AddValue);
        }

        return rightNow.get(Calendar.YEAR) + "-" +
                Lenchk(rightNow.get(Calendar.MONTH) + 1) + "-" +
                Lenchk(rightNow.get(Calendar.DATE));
    }

    /**
     * 날짜계산
     *
     * @param String
     * @param String
     * @param int
     * @return String
     */
    public static String getCalToDate(String strDate, String AddFlag,
                                      int AddValue) {
        String strRtn = "";

        Calendar rightNow = Calendar.getInstance();

        if (strDate.length() == 10) {
            rightNow.set(Integer.parseInt(strDate.substring(0, 4)),
                    Integer.parseInt(strDate.substring(5, 7)) - 1,
                    Integer.parseInt(strDate.substring(8, 10)));

            if (AddFlag.equals("Y")) {
                rightNow.add(Calendar.YEAR, AddValue);
            } else if (AddFlag.equals("M")) {
                rightNow.add(Calendar.MONTH, AddValue);
            } else if (AddFlag.equals("D")) {
                rightNow.add(Calendar.DATE, AddValue);
            }

            strRtn = rightNow.get(Calendar.YEAR) + "-" +
                    Lenchk(rightNow.get(Calendar.MONTH) + 1) + "-" +
                    Lenchk(rightNow.get(Calendar.DATE));
        }

        return strRtn;
    }

    /**
     * 날짜계산
     *
     * @param String
     * @param String
     * @param String
     * @param String
     * @return String
     */
    public static String getCalToDate(String strDate, String AddYear,
                                      String AddMonth, String AddDay) {
        String strRtn = "";

        int intYY = 0;
        int intMM = 0;
        int intDD = 0;

        if (!AddYear.equals("")) {
            intYY = Integer.parseInt(AddYear);
        }

        if (!AddMonth.equals("")) {
            intMM = Integer.parseInt(AddMonth);
        }

        if (!AddDay.equals("")) {
            intDD = Integer.parseInt(AddDay);
        }

        Calendar rightNow = Calendar.getInstance();

        if (strDate.length() == 10) {
            rightNow.set(Integer.parseInt(strDate.substring(0, 4)),
                    Integer.parseInt(strDate.substring(5, 7)) - 1,
                    Integer.parseInt(strDate.substring(8, 10)));

            rightNow.add(Calendar.YEAR, intYY);
            rightNow.add(Calendar.MONTH, intMM);
            rightNow.add(Calendar.DATE, intDD);

            strRtn = rightNow.get(Calendar.YEAR) + "-" +
                    Lenchk(rightNow.get(Calendar.MONTH) + 1) + "-" +
                    Lenchk(rightNow.get(Calendar.DATE));
        }

        return strRtn;
    }

    /**
     * 길이체크
     *
     * @param int
     * @return String
     */
    public static String Lenchk(int a) {
        String temp = Integer.toString(a);

        if (temp.length() == 1) {
            temp = "0" + a;
        }

        return temp;
    }

    public static Properties getDefaultSessionValues(HttpSession session,
                                                     Properties props) throws Exception {
        try {
            // props.put(Config .USERID, (String) session
            // .getAttribute(Config.USERID));
            // props.put(Config.USERNAME, (String) session
            // .getAttribute(Config.USERNAME));
            // props.put(Config.SVC_NO,
            // (String)session.getAttribute(Config.SVC_NO));
            // props.put(Config.DIV_CODE,
            // (String)session.getAttribute(Config.DIV_CODE));
            // props.put(Config.BRNCH_CODE,
            // (String)session.getAttribute(Config.BRNCH_CODE));
            // props.put(Config.USER_TYPE,
            // (String)session.getAttribute(Config.USER_TYPE));
        } catch (Exception ex) {
            return props;
        }

        return props;
    }

    /**
     * *기능 : 총페이지 구하기 *@param li_totalCount 게시판 총 건수
     *
     * @param li_max 페이지당 최대건수 *@return int 총페이지
     */
    public int getTotalPage(int li_totalCount, int li_max) {
        int li_totalPage = 1;

        try {
            if (li_totalCount != 0) {
                if ((li_totalCount % li_max) == 0) {
                    li_totalPage = li_totalCount / li_max;
                } else {
                    li_totalPage = (li_totalCount / li_max) + 1;
                }
            }
        } catch (Exception e) {
            li_totalPage = 1;
        } finally {
            return li_totalPage;
        }
    }

    /**
     * 기능:쿼리시 변수가 널이거나 빈문자일경우 조건문을 실행하지 않게함 <br>
     *
     * @param filedName   : 스트링문자
     * @param varString   : 조건문에 들어갈 변수
     * @param dateString: between 일때 날자 변수
     * @param optString   : like나 between일때 사용
     * @return 변환된 스트링문자
     */
    public String getQueryWhere(String filedName, String varString,
                                String optString, String dateString) {
        String queryString = "";

        if ((varString == null) || varString.equals("")) {
            queryString = "";
        } else if (optString.equals("LIKE")) {
            queryString = " AND " + filedName + " " + optString + "  '%" +
                    varString + "%'";
        } else if (optString.equals("BETWEENNO")) {
            if ((dateString != null) && !dateString.equals("")) {
                queryString = " AND (" + filedName + "  BETWEEN  '" +
                        varString + "' AND '" + dateString + "') ";
            }
        } else if (optString.equals("BETWEENKEY")) {
            if ((dateString != null) && !dateString.equals("")) {
                queryString = " AND (" + filedName + "  BETWEEN  " + varString +
                        " AND " + dateString + ") ";
            }
        } else if (optString.equals("BETWEEN")) {
            if ((dateString != null) && !dateString.equals("")) {
                queryString = " AND (to_char(" + filedName +
                        ", 'yyyy-mm-dd') " + optString + " '" + varString +
                        "' AND '" + dateString + "') ";
            }
        } else if (optString.equals("")) {
            queryString = " AND " + filedName + "= '" + varString + "'";
        } else if (optString.equals("FIRST")) {
            if ((varString != null) && varString.equals("2")) {
                queryString = " WHERE " + filedName + " LIKE '%%' ";
            } else {
                queryString = " WHERE " + filedName + " = '" + varString + "'";
            }
        }

        return queryString;
    }

    /***************************************************************************
     * 문자열 관련 함수모음
     **************************************************************************/

    /**
     * 기능:스트링을 일정한 길이이상일때 ".."를 붙여서 잘라줌. <br>
     *
     * @param str 스트링문자
     * @param len 자르고자하는 길이
     * @return 변환된 스트링문자
     */
    public String cutString(String str, int len) {
        if (str.length() < (len + 1)) {
            return str;
        } else {
            return (str.substring(0, len) + "..");
        }
    }

    /**
     * 기능:string의 <enter>값에 html tag <br>
     * 을 붙인다. <br>
     *
     * @param content string
     * @return <br>
     *         이 붙은 새로운 string
     */
    public String replaceString(String content) {
        String tempStr = "";
        byte cmpChar = 0x0d;

        for (int i = 0; i < content.length(); i++) {
            if ((byte) content.charAt(i) == cmpChar) {
                if ((byte) content.charAt(++i) == 0x0a) {
                    tempStr = tempStr + " <br> ";
                } else {
                    --i;
                }
            } else {
                tempStr = tempStr + String.valueOf(content.charAt(i));
            }
        }

        //System.out.println("tempStr:" + tempStr);
        return tempStr;
    }

    /**
     * 기능:string의 <enter>값을 없앤다. <br>
     *
     * @param content string
     * @return 새로운 string
     */
    public String replaceString_enter(String content) {
        String tempStr = "";

        for (int i = 0; i < content.length(); i++) {
            if (((byte) content.charAt(i) == 0x0d) ||
                    ((byte) content.charAt(i) == 0x0a)) {
                continue;
            } else {
                tempStr = tempStr + String.valueOf(content.charAt(i));
            }
        }

        //System.out.println("tempStr:" + tempStr);
        return tempStr;
    }

    public String gettest(String str) {
        //System.out.println("찍는 문자:" + str);

        return "test";
    }

    /**
     * 기능 : Replace String str에서 rep에 해당하는 String을 tok로 replace
     *
     * @param str string
     * @param rep string
     * @param tok string
     * @return 새로운 string
     */
    public String getReplace(String str, String rep, String tok) {
        String retStr = "";

        if (str == null) {
            return "";
        }

        if (str.equals("")) {
            return "";
        }

        //System.out.println("받은문자:" + str);
        //str = uni2Ksc(str);
        //System.out.println("한글로 변환한 문자:" + str);
        for (int i = 0, j = 0; (j = str.indexOf(rep, i)) > -1;
             i = j + rep.length()) {
            retStr += (str.substring(i, j) + tok);
        }

        //System.out.println((str.indexOf(rep) == -1) ? str : retStr
        //		+ str.substring(str.lastIndexOf(rep) + rep.length(), str
        //				.length()));
        return (str.indexOf(rep) == -1) ? str
                : (retStr +
                str.substring(str.lastIndexOf(rep) + rep.length(), str.length()));
    }

    public String getRespace(String str, String rep, String tok) {
        String retStr = "";

        if (str == null) {
            return "";
        }

        if (str.equals("")) {
            return "";
        }

        for (int i = 0, j = 0; (j = str.indexOf(rep, i)) > -1;
             i = j + rep.length()) {
            retStr += (str.substring(i, j) + tok);
        }

        //System.out.println((str.indexOf(rep) == -1) ? str : retStr
        //		+ str.substring(str.lastIndexOf(rep) + rep.length(), str
        //				.length()));
        return (str.indexOf(rep) == -1) ? str
                : (retStr +
                str.substring(str.lastIndexOf(rep) + rep.length(), str.length()));
    }

    /**
     * 기능:string에서 특정문자를 없앤다. <br>
     *
     * @param str   string
     * @param _char string
     * @return 새로운 string
     */
    public String trimChar(String str, char _char) {
        StringBuffer tempStr = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != _char) {
                tempStr = tempStr.append(str.charAt(i));
            }
        }

        return new String(tempStr);
    }

    /**
     * 기능: string에서 전각문자의 SPACE를 없앤다. <br>
     *
     * @param str string
     * @return 새로운 string
     */
    public String trimSpace(String str) {
        String newStr = str.trim();
        byte[] b = newStr.getBytes();
        int nSize = b.length;

        for (int i = (nSize - 1); i >= 0; i--) {
            if ((i > 0) &&
                    (((b[i - 1] == 0xffffffa1) && (b[i] == 0xffffffa1)) ||
                            ((b[i - 1] == 0xffffffe1) && (b[i] == 0xffffffe1)) ||
                            ((b[i - 1] == 0xffffffa1) && (b[i] == 0xffffffe1)))) {
                b[i] = 0x20;
                b[i - 1] = 0x20;
            }
        }

        // 한글반쪽처리
        for (int i = 0; i <= (nSize - 1); i++) {
            if ((i % 2) != 0) {
                if (((b[i - 1] > 0xffffff80) && (b[i - 1] != 0x20)) &&
                        (b[i] == 0x20)) {
                    b[i] = 0xffffffa1;
                }
            }
        }

        return (new String(b)).trim();
    }

    /**
     * 기능:string에서 특정문자를 다른문자로 바꾼다. <br>
     *
     * @param str string
     * @param c1  바꾸고자하는 문자
     * @param c2  바꿀문자
     * @return 새로운 string
     */
    public String replaceChar(String str, char c1, char c2) {
        StringBuffer tempStr = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != c1) {
                tempStr = tempStr.append(str.charAt(i));
            } else {
                tempStr = tempStr.append(c2);
            }
        }

        return new String(tempStr);
    }

    /**
     * 기능 : 국가코드 KOREA에 기준하여 숫자를 (한국통화기준으로)변환하는 함수들입니다.
     *
     * @param num int
     * @return 새로운 string
     */
    public static String dFormat(int num) {
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        String tempStr = nf.format(num);

        return tempStr;
    }

    /**
     * 기능 : 국가코드 KOREA에 기준하여 숫자를 (한국통화기준으로)변환하는 함수들입니다.
     *
     * @param num long
     * @return 새로운 string
     */
    public String dFormat(long num) {
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        String tempStr = nf.format(num);

        return tempStr;
    }

    /**
     * 기능 : 국가코드 KOREA에 기준하여 숫자를 (한국통화기준으로)변환하는 함수들입니다.
     *
     * @param num double
     * @return 새로운 string
     */
    public String dFormat(double num) {
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        String tempStr = nf.format(num);

        return tempStr;
    }

    /**
     * 기능 : 국가코드 KOREA에 기준하여 숫자를 (한국통화기준으로)변환하는 함수들입니다.
     *
     * @param num String
     * @return 새로운 string
     */
    public String dFormat(String str) {
        if (str == null) {
            return "";
        }

        if (str.length() == 0) {
            return "";
        }

        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        Number num = null;
        String ret = "";

        try {
            num = nf.parse(str);
        } catch (java.text.ParseException e) {
        }

        ret = dFormat(num.longValue());

        return ret;
    }

    /**
     * 기능 : 받은 값을 #,###,###,##0.#0 형식으로 바꿈 <br>
     *
     * @param str core로 부터 받은 값
     * @return #,###,###,###
     */
    public String fFormat(String str) {
        if (str == null) {
            return "";
        }

        if (str.length() == 0) {
            return "";
        }

        DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
        String retstr = null;

        try {
            retstr = df.format(Long.parseLong(str));

            return retstr;
        } catch (NumberFormatException nfe) {
            try {
                retstr = df.format(Double.valueOf(str).doubleValue());

                return retstr;
            } catch (Exception ee) {
                return "0";
            }
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 기능 : 받은 값을 #,###,###,##0.#0 형식으로 바꿈 <br>
     *
     * @param str core로 부터 받은 값
     * @param cnt 소수점 자릿수
     * @return #,###,###,###
     */
    public String fFormat(String str, int cnt) {
        if (str == null) {
            return "";
        }

        if (str.length() == 0) {
            return "";
        }

        // String s_format = "#,###,###,##0.";
        StringBuffer sb_format = new StringBuffer("#,###,###,##0.");

        if (cnt <= 0) {
            sb_format.deleteCharAt(13);
        } else {
            for (int i = 0; i < cnt; i++) {
                sb_format.append("0");
            }
        }

        DecimalFormat df = new DecimalFormat(sb_format.toString());
        String retstr = null;

        try {
            retstr = df.format(Long.parseLong(str));

            return retstr;
        } catch (NumberFormatException nfe) {
            try {
                retstr = df.format(Double.valueOf(str).doubleValue());

                return retstr;
            } catch (Exception ee) {
                return "0";
            }
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 기능 : String의 형식을 yyyy년 mm월 dd일 형식으로 바꾸기 <br>
     *
     * @param yyyymmdd date(yyyymmdd)
     * @return yyyy년 mm월 dd일의 date 형식
     */
    public String getDateFormat(String yyyymmdd) {
        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        if (yyyymmdd.trim().length() == 6) {
            yyyymmdd = yyyymmdd.substring(0, 4) + "년 " +
                    yyyymmdd.substring(4, 6) + "월";
        }

        if (yyyymmdd.trim().length() == 8) {
            yyyymmdd = yyyymmdd.substring(0, 4) + "년 " +
                    yyyymmdd.substring(4, 6) + "월 " + yyyymmdd.substring(6, 8) +
                    "일";
        }

        return yyyymmdd;
    }

    /**
     * 기능 : 사업자 등록번호 자리수 나누기
     *
     * @param yyy-yy-yyyyy
     * @return String[]
     */
    public String[] getEnterFormat(String yyyymmdd) {
        //logger.info("\n" + "getEnterFormat: 사업자등록번호");

        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        int j = 0;
        String[] rtnbuf = new String[3];

        rtnbuf = new String[3];

        for (int i = 0; i < yyyymmdd.length(); i++) {
            if (i == 0) {
                rtnbuf[j] = "";
            }

            if ((i == 3) || (i == 5)) {
                //logger.info("\n" + "rtnbuf:" + rtnbuf[j]);
                j++;

                rtnbuf[j] = "";
            }

            //logger.info("\n" + "rtnbuf(" + i + ")" + String.valueOf(yyyymmdd.charAt(i)));
            rtnbuf[j] = rtnbuf[j] + String.valueOf(yyyymmdd.charAt(i));
        }

        return rtnbuf;
    }

    /**
     * 기능 : 우편번호 자리수 나누기
     *
     * @param yyy-yyy
     * @return String[]
     */
    public String[] getPostFormat(String yyyymmdd) {
        //logger.info("\n" + "getPostFormat: 우편번호");

        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        int j = 0;
        String[] rtnbuf = new String[2];

        for (int i = 0; i < yyyymmdd.length(); i++) {
            if (i == 0) {
                rtnbuf[j] = "";
            }

            if (i == 3) {
                //logger.info("\n" + "rtnbuf:" + rtnbuf[j]);

                if (rtnbuf[j] == null) {
                    rtnbuf[j] = "";
                }

                j++;

                rtnbuf[j] = "";
            }

            //logger.info("\n" + "rtnbuf(" + i + ")" + String.valueOf(yyyymmdd.charAt(i)));
            rtnbuf[j] = rtnbuf[j] + String.valueOf(yyyymmdd.charAt(i));
        }

        if (yyyymmdd.length() == 0) {
            rtnbuf[0] = "";
            rtnbuf[1] = "";
        }

        if (yyyymmdd.length() == 1) {
            rtnbuf[1] = "";
        }

        if (rtnbuf[j] == null) {
            rtnbuf[j] = "";
        }

        return rtnbuf;
    }

    /**
     * 기능 : 주민번호 자리수 나누기
     *
     * @param yyyy-yyyy
     * @return String[]
     */
    public String[] getJuminFormat(String yyyymmdd) {
        //logger.info("\n" + "getJuminFormat: 주민번호");

        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        int j = 0;
        String[] rtnbuf = new String[2];
        ;

        for (int i = 0; i < yyyymmdd.length(); i++) {
            if (i == 0) {
                rtnbuf[j] = "";
            }

            if (i == 6) {
                //logger.info("\n" + "rtnbuf:" + rtnbuf[j]);
                j++;

                rtnbuf[j] = "";
            } else {
                //logger.info("\n" + "rtnbuf(" + i + ")" + String.valueOf(yyyymmdd.charAt(i)));
                rtnbuf[j] = rtnbuf[j] + String.valueOf(yyyymmdd.charAt(i));
            }
        }

        return rtnbuf;
    }

    /**
     * 기능 : 법인번호 자리수 나누기
     *
     * @param yyyyyy-yyyyyyy
     * @return String[]
     */
    public String[] getCorpoFormat(String yyyymmdd) {
        //logger.info("\n" + "getCorpoFormat: 법인번호");

        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        int j = 0;
        String[] rtnbuf = new String[2];
        ;

        for (int i = 0; i < yyyymmdd.length(); i++) {
            if (i == 0) {
                rtnbuf[j] = "";
            }

            if (i == 6) {
                //logger.info("\n" + "rtnbuf:" + rtnbuf[j]);
                j++;

                rtnbuf[j] = "";
            }

            //logger.info("\n" + "rtnbuf(" + i + ")" + String.valueOf(yyyymmdd.charAt(i)));
            rtnbuf[j] = rtnbuf[j] + String.valueOf(yyyymmdd.charAt(i));
        }

        return rtnbuf;
    }

    /**
     * 기능 : String의 형식의 날짜와 시간을 yyyy-mm-dd hh:mm:ss 형식으로 만들기 <br>
     *
     * @param yyyymmdd date(yyyymmdd)
     * @param hhmmss   time(hhmmss)
     * @return yyyy-mm-dd hh:mm:ss 의 datetime 형식
     */
    public String getDateTimeFormat(String yyyymmdd, String hhmmss) {
        String ls_return = "";

        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        if (yyyymmdd.trim().length() == 6) {
            yyyymmdd = yyyymmdd.substring(0, 4) + "-" +
                    yyyymmdd.substring(4, 6);
        }

        if (yyyymmdd.trim().length() == 8) {
            yyyymmdd = yyyymmdd.substring(0, 4) + "-" +
                    yyyymmdd.substring(4, 6) + "-" + yyyymmdd.substring(6, 8);
        }

        if (hhmmss == null) {
            hhmmss = "";
        }

        if (hhmmss.trim().length() == 4) {
            hhmmss = hhmmss.substring(0, 2) + ":" + hhmmss.substring(2, 4);
        }

        if (hhmmss.trim().length() == 6) {
            hhmmss = hhmmss.substring(0, 2) + ":" + hhmmss.substring(2, 4) +
                    ":" + hhmmss.substring(4, 6);
        }

        ls_return = yyyymmdd.trim() + " " + hhmmss.trim();

        return ls_return.trim();
    }

    /**
     * 기능 : String의 형식의 시간을 hh:mm:ss 형식으로 만들기 <br>
     *
     * @param hhmmss time(hhmmss)
     * @return hh:mm:ss 의 datetime 형식
     */
    public String getTimeFormat(String hhmmss) {
        String ls_return = "";

        if (hhmmss == null) {
            hhmmss = "";
        }

        if (hhmmss.trim().length() == 4) {
            hhmmss = hhmmss.substring(0, 2) + ":" + hhmmss.substring(2, 4);
        }

        if (hhmmss.trim().length() == 6) {
            hhmmss = hhmmss.substring(0, 2) + ":" + hhmmss.substring(2, 4) +
                    ":" + hhmmss.substring(4, 6);
        }

        ls_return = hhmmss.trim();

        return ls_return.trim();
    }

    /**
     * 기능 : String의 형식을 yyyy/mm/dd, yyyy-mm-dd등으로 바꾸기 <br>
     *
     * @param yyyymmdd  date(yyyymmdd)
     * @param delimeter 년월일 구분표시 문자열 ("/", "-". .....)
     * @return yyyy/mm/dd의 date 형식
     */
    public String getDateFormat(String yyyymmdd, String delimeter) {
        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        if (yyyymmdd.trim().length() == 6) {
            yyyymmdd = yyyymmdd.substring(0, 4) + delimeter +
                    yyyymmdd.substring(4, 6);
        }

        if (yyyymmdd.trim().length() == 8) {
            yyyymmdd = yyyymmdd.substring(0, 4) + delimeter +
                    yyyymmdd.substring(4, 6) + delimeter +
                    yyyymmdd.substring(6, 8);
        }

        if (yyyymmdd.trim().length() == 10) {
            yyyymmdd = yyyymmdd.substring(0, 4) + delimeter +
                    yyyymmdd.substring(5, 7) + delimeter +
                    yyyymmdd.substring(8, 10);
        }

        return yyyymmdd;
    }

    /**
     * 기능 : String의 형식을 yyyy/mm/dd hh:mm:ss 로 바꾸기 <br>
     *
     * @param yyyymmddhhmmss
     * @param 년월일            구분 / 시간구분 :
     * @return yyyy/mm/dd hh:mm:ss 형식
     */
    public String getDateFromat(String yyyymmdd, int i) {
        String date_s = "";
        String time_s = "";

        if (yyyymmdd == null) {
            yyyymmdd = "";
        }

        if (yyyymmdd.trim().length() == 14) {
            date_s = yyyymmdd.substring(0, 8);
            time_s = yyyymmdd.substring(8, 14);

            date_s = date_s.substring(0, 4) + "/" + date_s.substring(4, 6) +
                    "/" + date_s.substring(6, 8);
            time_s = time_s.substring(0, 2) + ":" + time_s.substring(2, 4) +
                    ":" + time_s.substring(4, 6);

            yyyymmdd = date_s + " " + time_s;
        }

        return yyyymmdd;
    }

    /**
     * 기능 : 뒤에서 자리까지 특정문자로 채우기 <br>
     *
     * @param source core로 부터 받은 값
     * @param i      곱할값
     * @return 변환된 문자열 ex) 111-222-333,'X',4 ==> 1111-222XXXX
     */
    public String rightFillChar(String Source, char Fillchar, int Fillcnt) {
        int Xpos;
        int j = 0;
        String TempStr = "";
        char szBuf = 0x00;
        Xpos = 0;

        try {
            for (j = (Source.length() - 2); j > 0; j--) {
                szBuf = Source.charAt(j);

                if (szBuf != ' ') {
                    Xpos = j - 2;

                    break;
                }
            }

            if (Xpos > 0) {
                TempStr = Source.substring(0, Xpos);

                for (int k = 0; k < Fillcnt; k++) {
                    TempStr = TempStr + Fillchar;
                }
            }
        } catch (Exception e) {
            TempStr = "";
        }

        return TempStr;
    }

    /**
     * 기능 : '0'으로 모두 채워진 문자열을 null로 바꾸어줌. <br>
     *
     * @param str 입력 문자열
     * @return 변경된 문자열
     */
    public String allZeroToNull(String str) {
        if (str == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < str.length(); i++) {
                sb.append('0');
            }

            String newStr = new String(sb);

            if (str.equals(newStr)) {
                return "";
            } else {
                return str;
            }
        }
    }

    /**
     * 기능 : '0'으로 모두 채워진 문자열을 &nbsp로 바꾸어줌. <br>
     *
     * @param str 입력 문자열
     * @return 변경된 문자열
     */
    public String allZeroToNbsp(String str) {
        if (str == null) {
            return "&nbsp";
        } else {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < str.length(); i++) {
                sb.append('0');
            }

            String newStr = new String(sb);

            if (str.equals(newStr)) {
                return "&nbsp";
            } else {
                return str;
            }
        }
    }

    /**
     * 기능 : #.# 로 오는 string 값을 #.# float 값으로 변환 <br>
     * (소수점 첫자리까지 display하는 경우에 유용)
     *
     * @param str String
     * @return 변환실수값
     */
    public float fnfStringToFloat(String str) {
        float fTmp;

        try {
            fTmp = Float.valueOf(str).floatValue();
        } catch (Exception e) {
            fTmp = 0;
        }

        return fTmp;
    }

    /**
     * 기능 : 구분자에 따라 문자열 분리 <br>
     *
     * @param str   분리할 문자열
     * @param _char 구분자
     * @return 분리된 문자열 배열
     */
    public String[] getTokenData(String str, char _char) {
        String tempbuf = str;

        if (str == null) {
            return null;
        }

        if (str.length() == 0) {
            return null;
        }

        int j = 0;

        for (int i = 0; i < str.length(); i++) {
            if (tempbuf.charAt(i) == _char) {
                j++;
            }
        }

        String[] rtnbuf = new String[j + 1];

        j = 0;
        rtnbuf[0] = "";

        for (int i = 0; i < str.length(); i++) {
            if (tempbuf.charAt(i) == _char) {
                j++;
                rtnbuf[j] = "";
            } else {
                rtnbuf[j] = rtnbuf[j] + String.valueOf(tempbuf.charAt(i));
            }
        }

        return rtnbuf;
    }

    /**
     * 기능 : 구분자에 따라 문자열 분리 <br>
     *
     * @param str   분리할 문자열
     * @param _char 구분자
     * @return 구분자에 갯수
     */
    public int getTokenData_count(String str, char _char) {
        String tempbuf = str;

        if (str == null) {
            return 0;
        }

        if (str.length() == 0) {
            return 0;
        }

        int j = 0;

        for (int i = 0; i < str.length(); i++) {
            if (tempbuf.charAt(i) == _char) {
                j++;
            }
        }

        return j;
    }

    /**
     * 기능 : 입력된 문자열의 좌축에 임의의 문자를 padding <br>
     *
     * @param str 입력문자열
     * @param len 리턴문자열 전체길이
     * @param ch  padding 문자
     * @return padding 된 문자열
     */
    public String LPad(String str, int len, char ch) {
        String s_strRet = "";
        char[] s_temp = {'0'};

        s_temp[0] = ch;

        String s_ch = new String(s_temp);

        if (str == null) {
            str = "";
        }

        int i_strLen = str.length();

        if (i_strLen >= len) {
            return str;
        } else {
            for (int i_idx = 0; i_idx < (len - i_strLen); i_idx++) {
                s_strRet = s_strRet + s_ch;
            }

            s_strRet = s_strRet + str;
        }

        return s_strRet;
    }

    /**
     * 기능 : 입력된 문자열의 좌축에 임의의 문자를 padding <br>
     *
     * @param str 입력문자열
     * @param len 리턴문자열 전체길이
     * @param ch  padding 문자
     * @return padding 된 문자열
     */
    public String zeroPadL(String str, int len, char ch) {
        return LPad(str, len, ch);
    }

    /**
     * 기능 : 입력된 문자열의 우측에 임의의 문자를 padding <br>
     *
     * @param str 입력문자열
     * @param len 리턴문자열 전체길이
     * @param ch  padding 문자
     * @return padding 된 문자열
     */
    public String RPad(String str, int len, char ch) {
        String s_strRet = "";
        char[] s_temp = {'0'};

        s_temp[0] = ch;

        String s_ch = new String(s_temp);

        if (str == null) {
            str = "";
        }

        int i_strLen = str.length();

        if (i_strLen >= len) {
            return str;
        } else {
            for (int i_idx = 0; i_idx < (len - i_strLen); i_idx++) {
                s_strRet = s_strRet + s_ch;
            }

            s_strRet = str + s_strRet;
        }

        return s_strRet;
    }

    /**
     * 기능 : 입력된 문자열의 우측에 임의의 문자를 padding <br>
     *
     * @param str 입력문자열
     * @param len 리턴문자열 전체길이
     * @param ch  padding 문자
     * @return padding 된 문자열
     */
    public String zeroPadR(String str, int len, char ch) {
        return RPad(str, len, ch);
    }

    /**
     * Double형의 문자를 소수 i_num자리에서 반올림을 한다.
     *
     * @param s_Data Double형 문자
     * @param i_num  반올림할 자리
     * @return String 반올림된 숫자
     */
    public String fn_cutDouble(String s_Data, int i_num) {
        String s_temp = "";
        String s_temp1 = "";
        int i_index = 0;
        double d_temp = 0.0;
        s_Data = convNull(s_Data).trim();

        if ((s_Data.length() != 0) && (!s_Data.equals("0"))) {
            for (int i = 0; i < s_Data.length(); i++) {
                s_temp = s_Data.substring(i, i + 1);

                if (s_temp.equals(".")) {
                    i_index = i;
                }
            }

            if (i_index != 0) {
                if ((s_Data.length() - (i_index + 1)) < i_num) {
                    s_Data = this.setZero(s_Data,
                            i_num - (s_Data.length() - (i_index + 1)));
                }

                s_temp = s_Data.substring(0, i_index) + "." +
                        s_Data.substring(i_index + 1, i_index + i_num);
                s_temp1 = s_Data.substring(i_index + i_num, i_index + i_num +
                        1);

                if (Integer.parseInt(s_temp1) >= 5) {
                    d_temp = Math.pow(0.1, i_num - 1);
                }

                s_Data = new Double(((Double.parseDouble(s_temp) * Math.pow(
                        10.0, i_num - 1)) +
                        (d_temp * Math.pow(10.0, i_num - 1))) / Math.pow(10.0,
                        i_num - 1)).toString();
            }
        }

        return s_Data;
    }

    /**
     * 기능 : 우측에 i_length만큼 0을 추가
     *
     * @param s_Data   문자열
     * @param i_length 추가할 0의 갯수
     * @return String
     */
    public String setZero(String s_Data, int i_length) {
        for (int i_cnt = 0; i_cnt < i_length; i_cnt++)
            s_Data = s_Data + "0";

        return s_Data;
    }

    /**
     * 기능 : String에서 " "를 &nbsp로 변환한다. <BR>
     * (예) String = " 2 " -> String = "&nbsp2&nbsp" <BR>
     *
     * @param String 변환할 String
     * @return String
     */
    public String SpaceToNbsp(String Str) {
        String out = "";

        for (int i = 0; i < Str.length(); i++) {
            if (Str.charAt(i) == ' ') {
                out += "&nbsp;";
            } else {
                out += Str.charAt(i);
            }
        }

        return out;
    }

    /**
     * 기능: 문자열 replace 함수 <BR>
     *
     * @param String text <BR>
     * @param int    start 시작 index <BR>
     * @param String src 바뀔문자열 <BR>
     * @param String dest 새문자열 <BR>
     * @return String 결과 문자열
     */
    public String replaceAll(String text, int start, String src, String dest) {
        if (text == null) {
            return null;
        }

        if ((src == null) || (dest == null)) {
            return text;
        }

        int textlen = text.length();
        int srclen = src.length();
        int diff = dest.length() - srclen;
        int d = 0;

        StringBuffer t = new StringBuffer(text);

        while (start < textlen) {
            start = text.indexOf(src, start);

            if (start < 0) {
                break;
            }

            t.replace(start + d, start + d + srclen, dest);
            start += srclen;
            d += diff;
        }

        return t.toString();
    }

    /**
     * 기능 : 에러대신 Zero String을 리턴하는 substring 함수 <BR>
     * (예) getSubstring("1234",4,2) --> "" <BR>
     *
     * @param String str string source
     * @param int    start substring 시작위치
     * @param int    length substring 길이
     * @return String
     */
    public static String getSubstring(String Str, int start, int len) {
        if (Str == null) {
            return "";
        }

        int slen = Str.length();

        if ((slen < 1) || (start < 0) || (len < 1)) {
            return "";
        }

        if ((slen - 1) < start) {
            return "";
        }

        if (slen < (start + len)) {
            return Str.substring(start, Str.length());
        } else {
            return Str.substring(start, start + len);
        }
    }

    /**
     * 기능 : 숫자로된 금액을 문자로 표시함 <BR>
     * (예) makeIntToKor("2000") --> "이천원" <BR>
     *
     * @param String str 금액String(숫자)
     * @return String 금액String(문자)
     */
    public static String makeIntToKor(String str) {
        String[] arrBigUnit = {"", "만", "억", "조", "경", "해"};
        String[] arrOne = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        String[] arrTwo = {
                "", "십", "이십", "삼십", "사십", "오십", "육십", "칠십", "팔십", "구십"
        };
        String[] arrThr = {
                "", "백", "이백", "삼백", "사백", "오백", "육백", "칠백", "팔백", "구백"
        };
        String[] arrFou = {
                "", "천", "이천", "삼천", "사천", "오천", "육천", "칠천", "팔천", "구천"
        };
        String lastWord = "원";

        String returnValue = "";
        String oneWord = "";
        String unitWord = "";

        int big = 0;

        if ((str == null) || (str.trim().length() > 24)) {
            return "";
        }

        for (int nI = str.length(); str != ""; nI -= 4) {
            int nK = 0;
            String unitKor = "";
            unitWord = getSubstring(str, (nI > 4) ? (nI - 4) : 0, 4);

            for (int nJ = unitWord.length() - 1; nJ >= 0; nJ--) {
                nK++;
                oneWord = getSubstring(unitWord, nJ, 1);

                switch (nK) {
                    case 1:
                        unitKor = arrOne[Integer.parseInt(oneWord)];

                        break;

                    case 2:
                        unitKor = arrTwo[Integer.parseInt(oneWord)] + unitKor;

                        break;

                    case 3:
                        unitKor = arrThr[Integer.parseInt(oneWord)] + unitKor;

                        break;

                    case 4:
                        unitKor = arrFou[Integer.parseInt(oneWord)] + unitKor;

                        break;
                }
            }

            if (!unitKor.equals("")) {
                returnValue = unitKor + arrBigUnit[big++] + returnValue;
            } else {
                big++;
            }

            str = getSubstring(str, 0, str.length() - 4);
        }

        return returnValue + lastWord;
    }

    /**
     * 기능 : 숫자로된 금액을 문자로 표시함 <BR>
     * (예) makeIntToNumKor("2000") --> "2천원" <BR>
     *
     * @param String str 금액String(숫자)
     * @return String 금액String(문자)
     */
    public static String makeIntToNumKor(String str) {
        String[] arrBigUnit = {"", "만", "억", "조", "경", "해"};
        String[] arrOne = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] arrTwo = {
                "", "10", "20", "30", "40", "50", "60", "70", "80", "90"
        };
        String[] arrThr = {
                "", "1백", "2백", "3백", "4백", "5백", "6백", "7백", "8백", "9백"
        };
        String[] arrFou = {
                "", "1천", "2천", "3천", "4천", "5천", "6천", "7천", "8천", "9천"
        };
        String lastWord = "원";

        String returnValue = "";
        String oneWord = "";
        String unitWord = "";

        int big = 0;

        if ((str == null) || (str.trim().length() > 24)) {
            return "";
        }

        for (int nI = str.length(); str != ""; nI -= 4) {
            int nK = 0;
            String unitKor = "";
            unitWord = getSubstring(str, (nI > 4) ? (nI - 4) : 0, 4);

            for (int nJ = unitWord.length() - 1; nJ >= 0; nJ--) {
                nK++;
                oneWord = getSubstring(unitWord, nJ, 1);

                switch (nK) {
                    case 1:
                        unitKor = arrOne[Integer.parseInt(oneWord)];

                        break;

                    case 2:
                        unitKor = arrTwo[Integer.parseInt(oneWord)] + unitKor;

                        break;

                    case 3:
                        unitKor = arrThr[Integer.parseInt(oneWord)] + unitKor;

                        break;

                    case 4:
                        unitKor = arrFou[Integer.parseInt(oneWord)] + unitKor;

                        break;
                }
            }

            if (!unitKor.equals("")) {
                returnValue = unitKor + arrBigUnit[big++] + returnValue;
            } else {
                big++;
            }

            str = getSubstring(str, 0, str.length() - 4);
        }

        return returnValue + lastWord;
    }

    /**
     * 기능 : 두 문자열을 BigDecimal Type으로 덧셈연산하고 결과값을 문자열로 반환 <br>
     *
     * @param String s_str1 문자열
     * @param String s_str2 더해질 문자열
     * @return String (s_str1 + s_str2)의 결과값 String
     */
    public static String bigAdd(String s_str1, String s_str2) {
        // s_str1 + s_str
        BigDecimal B_decimal1 = new BigDecimal(s_str1.trim());
        BigDecimal B_decimal2 = new BigDecimal(s_str2.trim());

        B_decimal1 = B_decimal1.add(B_decimal2);

        return B_decimal1.toString();
    }

    /**
     * 기능 : 두 문자열을 BigDecimal Type으로 뺄셈연산하고 결과값을 문자열로 반환 <br>
     *
     * @param String s_str1 문자열
     * @param String s_str2 마이너스할 문자열
     * @return String (s_str1 - s_str2)의 결과값 String
     */
    public static String bigSubtract(String s_str1, String s_str2) {
        // s_str1 - s_str2
        BigDecimal B_decimal1 = new BigDecimal(s_str1.trim());
        BigDecimal B_decimal2 = new BigDecimal(s_str2.trim());

        B_decimal1 = B_decimal1.subtract(B_decimal2);

        return B_decimal1.toString();
    }

    /**
     * 기능 : 두 문자열을 BigDecimal Type으로 곱셈연산하고 결과값을 문자열로 반환 <br>
     *
     * @param String s_str1 문자열
     * @param String s_str2 곱할 문자열
     * @return (s_str1 * s_str2)의 결과값 String
     */
    public static String bigMultiply(String s_str1, String s_str2) {
        // s_str1 * s_str2
        BigDecimal B_decimal1 = new BigDecimal(s_str1.trim());
        BigDecimal B_decimal2 = new BigDecimal(s_str2.trim());

        B_decimal1 = B_decimal1.multiply(B_decimal2);

        return B_decimal1.toString();
    }

    /**
     * 기능 : 두 문자열을 BigDecimal Type으로 나누기연산하고 결과값을 문자열로 반환
     *
     * @param String s_str1 문자열
     * @param String s_str2 나눌 문자열
     * @param int    scale 반올림할 자리수 (BigDecimal함수의 divide함수 참조)
     * @return (s_str1 / s_str2)를 scale에 맞춰 반올림한 값 String
     */
    public static String bigDivide(String s_str1, String s_str2, int scale) {
        // s_str1 / s_str2
        BigDecimal B_decimal1 = new BigDecimal(s_str1.trim());
        BigDecimal B_decimal2 = new BigDecimal(s_str2.trim());

        B_decimal1 = B_decimal1.divide(B_decimal2, scale,
                B_decimal1.ROUND_HALF_UP);

        return B_decimal1.toString();
    }

    /**
     * 기능 : 두 문자열을 BigDecimal Type으로 나누기연산하고 결과값을 문자열로 반환 <br>
     *
     * @param String s_str1 문자열 <br>
     * @param String s_str2 나눌 문자열 <br>
     * @param int    scale 반올림 또는 결과값이 나올 자리수 (BigDecimal함수의 divide함수 참조) <br>
     * @param int    rnd 반올림 또는 절사 패턴 <br>
     *               0 : ROUND_UP , 1 : ROUND_DOWN <br>
     *               2 : ROUND_CEILING , 3 : ROUND_FLOOR <br>
     *               4 : ROUND_HALF_UP , 5 : ROUND_HALF_DOWN <br>
     *               6 : ROUND_HALF_EVEN , 7 : ROUND_UNNECESSARY <br>
     * @Return (s_str1 / s_str2)를 scale자리수에서 rnd패턴으로 처리한 결과값 String
     */
    public static String bigDivide(String s_str1, String s_str2, int scale,
                                   int rnd) {
        // s_str1 / s_str2
        BigDecimal B_decimal1 = new BigDecimal(s_str1.trim());
        BigDecimal B_decimal2 = new BigDecimal(s_str2.trim());

        B_decimal1 = B_decimal1.divide(B_decimal2, scale, rnd);

        return B_decimal1.toString();
    }

    /**
     * 기능 : 두 개의 문자열을 BigDecimal Type으로 비교하고 결과값을 int형으로 반환 <br>
     *
     * @param String s_str1 문자열 <br>
     * @param String s_str2 비교할 문자열 <br>
     * @return s_str1 와 B값의 비교 결과 int <br>
     *         s_str1 > s_str2 : 양수값 <br>
     *         s_str1 < s_str2 : 음수값 <br>
     *         s_str1 = s_str2 : 0 <br>
     */
    public static int bigCompareTo(String s_str1, String s_str2) {
        // s_str1 compare s_str2
        BigDecimal B_decimal1 = new BigDecimal(s_str1.trim());
        BigDecimal B_decimal2 = new BigDecimal(s_str2.trim());
        int i_rtn = 0;

        i_rtn = B_decimal1.compareTo(B_decimal2);

        return i_rtn;
    }

    /**
     * 기능 : Comma(,)로 분리된 String의 합을 구한다. <br>
     *
     * @param str 전달된 String
     * @return 처리된 String
     */
    public String strSum(String str) {
        String ls_return = "0";

        String[] ls_str = getTokenData(str, ',');
        int i;

        for (i = 0; i < ls_str.length; i++) {
            ls_return = bigAdd(ls_return, ls_str[i]);
        }

        return ls_return;
    }

    /**
     * 기능 : conversion : null --> "" <br>
     *
     * @param str 전달된 String
     * @return 처리된 String
     */
    public String convNull(String str) {
        if (str == null) {
            str = "";
        }

        return str;
    }

    /**
     * 기능 : conversion : null --> "0" <br>
     *
     * @param str 전달된 String
     * @return 처리된 String
     */
    public String convZero(String str) {
        if ((str == null) || str.trim().equals("")) {
            str = "0";
        }

        return str;
    }

    /***************************************************************************
     * HTML 관련 함수모음
     **************************************************************************/

    /**
     * HTML과 관련하여 일부 특수문자를 반환 & --->> &amp; < --->> &lt; > --->> &gt; ' --->>
     * &acute; " --->> &cute; | --->> &brvbar;
     *
     * @param String 변환할 문자열
     * @return String HTML 특수문자가 변환된 문자열
     */
    public String getSpecialCharacters(String str) {
        str = getReplace(str, "&", "&amp;");
        str = getReplace(str, "<", "&lt;");
        str = getReplace(str, ">", "&gt;");
        str = getReplace(str, "'", "&acute;");
        str = getReplace(str, "\"", "&cute;");
        str = getReplace(str, "|", "&brvbar;");

        str = getReplace(str, "\n", "<BR>");

        return str;
    }

    /***************************************************************************
     * 날짜 관련 함수모음
     **************************************************************************/

    /**
     * 기능 : 원하는 형식에 맞는 날짜/시간 문자열을 제공한다. <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"yyyyMM", "yyyyMMddHHmm" 등과 같이 원하는
     * 구분정도와 표현 단위를 지정할 수 있으며 "yyyy/MM/dd", "HH:mm:ss", "MM/dd HH:mm" 등과 같이 구분자를
     * 같이 지정할 수도 있다.
     *
     * @param date    대상 날짜, java.util.Date type instance
     * @param patterm 원하는 형식
     * @retrun 형식에 맞춘 날짜/시간 문자열
     * @see java.text.SimpleDateFormat
     */
    public static String getFormattedDate(java.util.Date date, String pattern) {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd HHmmss",
                Locale.KOREA);
        dtFormat.applyPattern(pattern);

        return dtFormat.format(getLocalizedDate(date));
    }

    /**
     * 기능 : 시스템에서 구한 지역화 되지 않은 Date object를 현지에 맞게 조정하여 되돌려 준다. <BR>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일반적으로 servlet code에서는 new
     * java.util.Date()로 현지 시간을 생성한 다음 다음의 method를 이용한다.
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;시스템에서 얻은 시간과 실제 시간과의 차이는
     * {@link EBPP_TIME_OFFSET EBPP_TIME_OFFSET} 에 지정되어 있다.
     *
     * @param date 대상 날짜
     * @retun 지역화된 날짜
     */
    public static java.util.Date getLocalizedDate(java.util.Date date) {
        Calendar cld = Calendar.getInstance(EBPP_TIMEZONE, Locale.KOREA);
        cld.setTime(date);
        cld.add(Calendar.MINUTE, EBPP_TIME_OFFSET);

        return cld.getTime();
    }

    /**
     * 기능 : 현재일자를 구함 <br>
     *
     * @return 현재일자 YYYYMMDD
     */
    public static String getSysDate() {
        int chrYear;
        int chrMonth;
        int chrDay;
        DecimalFormat dformat = new DecimalFormat("00");
        chrYear = Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.YEAR);
        chrMonth = Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.MONTH) + 1;
        chrDay = Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.DATE);

        return String.valueOf(chrYear) + dformat.format(chrMonth) +
                dformat.format(chrDay);
    }

    /**
     * 기능 : 현재일로부터 이전일를 구함 <br>
     *
     * @return 이전일자 YYYYMMDD
     */
    public static String getSysDate(int day) {
        int chrYear;
        int chrMonth;
        int chrDay;
        DecimalFormat dformat = new DecimalFormat("00");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("JST"));
        calendar.setTime(new Date(System.currentTimeMillis() -
                (day * 86400000)));
        chrYear = calendar.get(Calendar.YEAR);
        chrMonth = calendar.get(Calendar.MONTH) + 1;
        chrDay = calendar.get(Calendar.DATE);

        return String.valueOf(chrYear) + dformat.format(chrMonth) +
                dformat.format(chrDay);
    }

    /**
     * 기능 : 현재시간를 구함 <br>
     *
     * @return 현재시간 HHMISS
     */
    public String getSysTime() {
        int chrHour;
        int chrMinute;
        int chrSecond;
        DecimalFormat dformat = new DecimalFormat("00");
        chrHour = Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.HOUR_OF_DAY);
        chrMinute = Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.MINUTE);
        chrSecond = Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.SECOND);

        return dformat.format(chrHour) + dformat.format(chrMinute) +
                dformat.format(chrSecond);
    }

    /**
     * 기능 : 현재 요일을 리턴한다. <br>
     *
     * @return 요일(1:일요일...7:토요일)
     */
    public int getDay() {
        return Calendar.getInstance(TimeZone.getTimeZone("JST"))
                .get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 기능 : 현재 요일을 한국식으로 리턴한다. <br>
     *
     * @return 요일(일요일...토요일)
     */
    public String getDayKor() {
        int li_day = getDay();
        String ls_week = "";

        switch (li_day) {
            case 1:
                ls_week = "일요일";

                break;

            case 2:
                ls_week = "월요일";

                break;

            case 3:
                ls_week = "화요일";

                break;

            case 4:
                ls_week = "수요일";

                break;

            case 5:
                ls_week = "목요일";

                break;

            case 6:
                ls_week = "금요일";

                break;

            case 7:
                ls_week = "토요일";

                break;
        }

        return ls_week;
    }

    /**
     * 기능 : 들어온 두 날짜의 차이 <BR>
     * (예) diffDate("20000629", "20000630") --> 1 <BR>
     *
     * @param String sFirstDate
     * @param String sSecondDate
     * @return String 날짜의 차이 (sSecondDate - sFirstDate)
     */
    public String diffDate(String date1, String date2) {
        String gapDay = "";

        if ((date1 == null) || (date2 == null)) {
            return gapDay;
        }

        if ((date1.length() == 0) || (date2.length() == 0)) {
            return gapDay;
        }

        int firstYear = Integer.parseInt(date1.substring(0, 4));
        int firstMonth = Integer.parseInt(date1.substring(4, 6));
        int firstDay = Integer.parseInt(date1.substring(6, 8));
        int secondYear = Integer.parseInt(date2.substring(0, 4));
        int secondMonth = Integer.parseInt(date2.substring(4, 6));
        int secondDay = Integer.parseInt(date2.substring(6, 8));

        try {
            Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("JST"));
            Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("JST"));

            cal1.set(firstYear, firstMonth, firstDay);
            cal2.set(secondYear, secondMonth, secondDay);

            long cal1sec = cal1.getTime().getTime();
            long cal2sec = cal2.getTime().getTime();

            long gap = cal2sec - cal1sec;

            gapDay = Long.toString((gap / 86400) / 1000);

            return gapDay;
        } catch (Exception e) {
            //System.out.println("ejb:getDataGap(Error)" + e.toString());
            gapDay = null;

            return gapDay;
        }
    }

    /**
     * 기능 : 입력한 날짜 기준으로 몇일 전,후 (주의)입력날짜는 구분자가 없는 string형
     *
     * @param date String date (19991002)
     * @param Span long 기준이 되는 시간
     * @return String
     */
    public String getDateWithSpan(String date, long Span) {
        boolean isDay = true;
        int Days = (int) Span;

        int year = Integer.valueOf(date.substring(0, 4)).intValue();
        int month = Integer.valueOf(date.substring(4, 6)).intValue();
        int day = Integer.valueOf(date.substring(6, 8)).intValue();

        int total_days = 0;
        int mrest = 0; // 이달의 남은 날수

        if (Days > 0) {
            do {
                // 각 달의 총 날수를 구한다
                if (month == 1) {
                    total_days = 31;
                } else if (month == 2) {
                    // 윤년 조사
                    if ((((year % 4) == 0) && ((year % 100) != 0)) ||
                            ((year % 400) == 0)) {
                        total_days = 29;
                    } else {
                        total_days = 28;
                    }
                } else if (month == 3) {
                    total_days = 31;
                } else if (month == 4) {
                    total_days = 30;
                } else if (month == 5) {
                    total_days = 31;
                } else if (month == 6) {
                    total_days = 30;
                } else if (month == 7) {
                    total_days = 31;
                } else if (month == 8) {
                    total_days = 31;
                } else if (month == 9) {
                    total_days = 30;
                } else if (month == 10) {
                    total_days = 31;
                } else if (month == 11) {
                    total_days = 30;
                } else if (month == 12) {
                    total_days = 31;
                }

                mrest = total_days - day;

                if (mrest == Days) {
                    isDay = false;
                    day = total_days;
                } else if (mrest < Days) {
                    Days = Days - (mrest + 1);
                    month++;
                    day = 1;

                    if (month > 12) {
                        year++;
                        month = 1;
                    }
                } else {
                    isDay = false;
                    day += Days;
                }
            } while (isDay);
        } else if (Days < 0) {
            do {
                int pmonth = month - 1;
                int pyear = year;

                if (pmonth < 1) {
                    pmonth = 12;
                    pyear--;
                }

                // 전 달의 총 날수를 구한다
                if (pmonth == 1) {
                    total_days = 31;
                } else if (pmonth == 2) {
                    // 윤년 조사
                    if ((((pyear % 4) == 0) && ((pyear % 100) != 0)) ||
                            ((pyear % 400) == 0)) {
                        total_days = 29;
                    } else {
                        total_days = 28;
                    }
                } else if (pmonth == 3) {
                    total_days = 31;
                } else if (pmonth == 4) {
                    total_days = 30;
                } else if (pmonth == 5) {
                    total_days = 31;
                } else if (pmonth == 6) {
                    total_days = 30;
                } else if (pmonth == 7) {
                    total_days = 31;
                } else if (pmonth == 8) {
                    total_days = 31;
                } else if (pmonth == 9) {
                    total_days = 30;
                } else if (pmonth == 10) {
                    total_days = 31;
                } else if (pmonth == 11) {
                    total_days = 30;
                } else if (pmonth == 12) {
                    total_days = 31;
                }

                mrest = (day - 1) + Days;

                if (mrest == 0) {
                    isDay = false;
                    day = 1;
                } else if (mrest < 0) {
                    month = pmonth;
                    year = pyear;
                    Days = mrest + 1;
                    day = total_days;
                } else {
                    isDay = false;
                    day += Days;
                }
            } while (isDay);
        }

        String nyear = Integer.toString(year);
        String nmon = null;
        String nday = null;

        if (month < 10) {
            nmon = "0" + Integer.toString(month);
        } else {
            nmon = Integer.toString(month);
        }

        if (day < 10) {
            nday = "0" + Integer.toString(day);
        } else {
            nday = Integer.toString(day);
        }

        return (nyear + nmon + nday);
    }

    /**
     * 기능 : 기준일로부터 앞으로 몇일 이동한 날짜 <BR>
     * (예) moveForward("20000629","1") --> "20000630" <BR>
     *
     * @param String sBaseDay
     * @param String sMoveDay
     * @return String
     */
    public static String moveForward(String sBaseDay, String sMoveDay) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 기준일로 들어온 날짜로부터 연도, 월, 일자 분리
        int year = new Integer(sBaseDay.substring(0, 4)).intValue();
        int month = new Integer(sBaseDay.substring(4, 6)).intValue();
        int day = new Integer(sBaseDay.substring(6, 8)).intValue();

        // 윤년 계산
        if ((((year % 4) == 0) && ((year % 100) != 0)) || ((year % 400) == 0)) {
            days[1] = 29;
        } // end of if
        else {
            days[1] = 28;
        } // end of else

        // 실제로 이동하는 연도, 월, 일자 할당
        int moveRemain = new Integer(sMoveDay).intValue() + day;
        int curMonth = month;
        int curYear = year;

        // 이동하고자 하는 일자가 한달보다 더 큰 동안 반복
        while (moveRemain > days[curMonth - 1]) {
            moveRemain = moveRemain - days[curMonth - 1];

            curMonth = curMonth + 1;

            if (curMonth > 12) {
                curMonth = 1;
                curYear = curYear + 1;

                if ((((curYear % 4) == 0) && ((curYear % 100) != 0)) ||
                        ((curYear % 400) == 0)) {
                    days[1] = 29;
                } // end of if
                else {
                    days[1] = 28;
                } // end of else
            } // end of if
        } // end of while

        // 결과를 문자열로 변환
        String sYear = new Integer(curYear).toString();
        String sMonth = new Integer(curMonth).toString();
        String sDay = new Integer(moveRemain).toString();

        // 월, 일자를 2자리로 고정
        sMonth = (sMonth.length() == 1) ? ("0" + sMonth) : sMonth;
        sDay = (sDay.length() == 1) ? ("0" + sDay) : sDay;

        return sYear + sMonth + sDay;
    } // end of moveForward

    /**
     * 기능 : 기준일로부터 뒤로 몇일 이동한 날짜 <BR>
     * (예) moveBack("20000629","1") --> "20000628" <BR>
     *
     * @param String sBaseDay
     * @param String sMoveDay
     * @return String
     */
    public static String moveBack(String sBaseDay, String sMoveDay) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 기준일로 들어온 날짜로부터 연도, 월, 일자 분리
        int year = new Integer(sBaseDay.substring(0, 4)).intValue();
        int month = new Integer(sBaseDay.substring(4, 6)).intValue();
        int day = new Integer(sBaseDay.substring(6, 8)).intValue();

        if ((((year % 4) == 0) && ((year % 100) != 0)) || ((year % 400) == 0)) {
            days[1] = 29;
        } // end of if
        else {
            days[1] = 28;
        } // end of else

        int moveDay = new Integer(sMoveDay).intValue();

        // 실제로 이동하는 연도, 월, 일자 할당
        int curYear = year;
        int curMonth = month;
        int curDay = day;

        // 이동하고자 하는 일자가 한달보다 더 작은 동안 반복
        while (moveDay >= curDay) {
            moveDay = moveDay - curDay;

            curMonth = curMonth - 1;

            if (curMonth < 1) {
                curMonth = 12;
                curYear = curYear - 1;

                if ((((curYear % 4) == 0) && ((curYear % 100) != 0)) ||
                        ((curYear % 400) == 0)) {
                    days[1] = 29;
                } // end of if
                else {
                    days[1] = 28;
                } // end of else
            } // end of if

            curDay = days[curMonth - 1];
        } // end of while

        // 마지막으로 남은 일자를 뺀다.
        curDay = curDay - moveDay;

        // 결과를 문자열로 변환
        String sYear = new Integer(curYear).toString();
        String sMonth = new Integer(curMonth).toString();
        String sDay = new Integer(curDay).toString();

        // 월, 일자를 2자리로 고정
        sMonth = (sMonth.length() == 1) ? ("0" + sMonth) : sMonth;
        sDay = (sDay.length() == 1) ? ("0" + sDay) : sDay;

        return sYear + sMonth + sDay;
    } // end of moveBack

    /**
     * 그 달의 마지막 일자만 구하기 (일 [31] 리턴)
     *
     * @param s_date (기준일자)
     * @return string (처리결과)
     */
    public String getLastDay(String s_date) {
        int firstYear = Integer.parseInt(s_date.substring(0, 4));
        int firstMonth = Integer.parseInt(s_date.substring(4, 6));

        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("JST"));
        cal1.set(firstYear, firstMonth, 1);
        cal1.setTime(new java.util.Date(cal1.getTime().getTime() - 86400000));

        return String.valueOf(cal1.get(Calendar.DATE));
    }

    /**
     * 그 달의 마지막 날짜 구하기 (년월일 8자리[20020101])리턴)
     *
     * @param s_date (기준일자)
     * @return string (처리결과)
     */
    public String getLastDate(String s_date) {
        int firstYear = Integer.parseInt(s_date.substring(0, 4));
        int firstMonth = Integer.parseInt(s_date.substring(4, 6));
        String s_month = "";

        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("JST"));
        cal1.set(firstYear, firstMonth, 1);
        cal1.setTime(new java.util.Date(cal1.getTime().getTime() - 86400000));

        if (firstMonth < 10) {
            s_month = "0" + String.valueOf(firstMonth);
        }

        return String.valueOf(firstYear) + s_month + cal1.get(Calendar.DATE);
    }

    /***************************************************************************
     * Request 및 Session 관련 함수모음
     **************************************************************************/


    /***************************************************************************
     * Convert CharacterSet 관련 함수모음
     **************************************************************************/

    /**
     * 기능 : 웹에서 받은 데이타를 encoding 8859_1 => KSC5601 <br>
     *
     * @param str browser의 string(8859_1)
     * @return KSC5601 type의 String
     */
    public String uni2Ksc(String str) {
        String retstr = null;

        if (str != null) {
            try {
                retstr = new String(str.getBytes("8859_1"), "KSC5601");
            } catch (Exception e) {
            }

            return retstr;
        } else {
            return "";
        }
    }

    /**
     * 기능 : 데이타를 encoding KSC5601 => 8859_1 <br>
     *
     * @param str string(KSC5601)
     * @return 8859_1 type의 String
     */
    public String ksc2Uni(String str) {
        String retstr = null;

        if (str != null) {
            try {
                retstr = new String(str.getBytes("KSC5601"), "8859_1");
            } catch (Exception e) {
            }

            return retstr;
        } else {
            return "";
        }
    }

    /**
     * 기능 : 소켓 통신해서 데이터를 넘겨줄때 전문 생성
     *
     * @param str 공통정보부 및 데이터부 생성
     * @return 생성된 String
     */
    public String socket_string(String eupmu_code, String d_length,
                                String g_code, String usrid, String s_time, String mesg) {
        String program_name = "DPSMANAGER";
        String program_ver = "V2.0";
        String d_no = "00000000";
        String r_code = "    ";
        String r_field = "        ";
        String str = "";

        if (eupmu_code != null) {
            try {
                d_length = zeroPadL(d_length, 4, '0');
                g_code = zeroPadL(g_code, 3, ' ');
                usrid = zeroPadL(usrid, 13, ' ');
                s_time = zeroPadL(s_time, 14, ' ');

                str = program_name + program_ver + eupmu_code + d_length +
                        d_no + g_code + usrid + s_time + r_code + r_field;

                //System.out.println(str);
                //System.out.println("데이터길이:" + str.length());
            } catch (Exception e) {
            }

            return str;
        } else {
            return "";
        }
    }

    /**
     * 기능 : 소켓 통신해서 데이터를 넘겨줄때 전문 생성
     *
     * @param str 공통정보부 및 데이터부 생성
     * @return 생성된 String
     */
    public String trace_string(String eupmu_code, String d_length,
                               String g_code, String usrid, String s_time, String mesg,
                               String first_day) {
        String program_name = "DPSMANAGER";
        String program_ver = "V2.0";
        String d_no = "00000000";
        String r_code = "    ";
        String r_field = "        ";
        String str = "";

        if (eupmu_code != null) {
            try {
                d_length = zeroPadL(d_length, 4, '0');
                g_code = zeroPadL(g_code, 3, ' ');
                usrid = zeroPadL(usrid, 13, ' ');
                s_time = zeroPadL(s_time, 14, ' ');

                str = program_name + program_ver + eupmu_code + d_length +
                        d_no + g_code + usrid + s_time + r_code + r_field +
                        first_day;

                //System.out.println(str);
                //System.out.println("데이터길이:" + str.length());
            } catch (Exception e) {
            }

            return str;
        } else {
            return "";
        }
    }

    /**
     * 기능 : 소켓 통신해서 데이터를 넘겨줄때 전문 생성
     *
     * @param str 공통정보부 및 데이터부 생성
     * @return 생성된 String
     */
    public String socket_string(String eupmu_code, String d_length,
                                String g_code, String usrid, String s_time, String mesg,
                                String trace_time) {
        String program_name = "DPSMANAGER";
        String program_ver = "V2.0";
        String d_no = "00000000";
        String r_code = "    ";
        String r_field = "        ";
        String str = "";

        if (eupmu_code != null) {
            try {
                d_length = zeroPadL(d_length, 4, '0');
                g_code = zeroPadL(g_code, 3, ' ');
                usrid = zeroPadL(usrid, 13, ' ');
                s_time = zeroPadL(s_time, 14, ' ');

                str = program_name + program_ver + eupmu_code + d_length +
                        d_no + g_code + usrid + s_time + r_code + r_field +
                        trace_time;

                //System.out.println(str);
                //System.out.println("데이터길이:" + str.length());
            } catch (Exception e) {
            }

            return str;
        } else {
            return "";
        }
    }

    public String socket_string(String eupmu_code, String d_length,
                                String g_code, String usrid, String s_time, String mesg, String lpid,
                                String lpsq) {
        String program_name = "DPSMANAGER";
        String program_ver = "V2.0";
        String d_no = "00000000";
        String r_code = "    ";
        String r_field = "        ";
        String str = "";

        if (eupmu_code != null) {
            try {
                d_length = zeroPadL(d_length, 4, '0');
                g_code = zeroPadL(g_code, 3, ' ');
                usrid = zeroPadL(usrid, 13, ' ');
                s_time = zeroPadL(s_time, 14, ' ');
                lpsq = zeroPadL(lpsq, 4, '0');
                str = program_name + program_ver + eupmu_code + d_length +
                        d_no + g_code + usrid + s_time + r_code + r_field + lpid +
                        lpsq;

                //System.out.println(str);
                //System.out.println("데이터길이:" + str.length());
            } catch (Exception e) {
            }

            return str;
        } else {
            return "";
        }
    }

    public String socket_string(String eupmu_code, String d_length,
                                String g_code, String usrid, String s_time, String mesg, String lpid,
                                String lpsq, String ppid) {
        String program_name = "DPSMANAGER";
        String program_ver = "V2.0";
        String d_no = "00000000";
        String r_code = "    ";
        String r_field = "        ";
        String str = "";

        if (eupmu_code != null) {
            try {
                d_length = zeroPadL(d_length, 4, '0');
                g_code = zeroPadL(g_code, 3, ' ');
                usrid = zeroPadL(usrid, 13, ' ');
                s_time = zeroPadL(s_time, 14, ' ');
                lpsq = zeroPadL(lpsq, 4, '0');
                ppid = zeroPadL(ppid, 7, '0');
                str = program_name + program_ver + eupmu_code + d_length +
                        d_no + g_code + usrid + s_time + r_code + r_field + lpid +
                        lpsq + ppid;

                //System.out.println(str);
                //System.out.println("데이터길이:" + str.length());
            } catch (Exception e) {
            }

            return str;
        } else {
            return "";
        }
    }

    public String socket_string() {
        String program_name = "DPSMANAGER";
        String program_ver = "V2.0";
        String d_no = "00000000";
        String r_code = "    ";
        String r_field = "        ";
        String str = "";
        String eupmu_code = "SC";
        String d_length = "0";
        String g_code = "K00";
        String usrid = "kolon";

        try {
            // String s_time = Com_sel.soc_Time();
            String s_time = getSysDate() + getSysTime();
            d_length = zeroPadL(d_length, 4, '0');
            g_code = zeroPadL(g_code, 3, ' ');
            usrid = zeroPadL(usrid, 13, ' ');
            s_time = zeroPadL(s_time, 14, ' ');

            str = program_name + program_ver + eupmu_code + d_length + d_no +
                    g_code + usrid + s_time + r_code + r_field;

            //System.out.println(str);
            //System.out.println("데이터길이:" + str.length());
        } catch (Exception e) {
            //System.out.println("오류가 발생하였습니다.");
        }

        return str;
    }

    /**
     * 기능 : String 데이터를 byte로 변환 substring
     *
     * @param btye
     * @return String
     */
    public String Byte_sub(String str, int f_data, int l_data) {
        int i = 0;
        int k = 0;
        int chai = l_data - f_data;

        byte[] full_data = new byte[l_data];
        full_data = str.getBytes(); // new Byte (str);

        // re_string = new String(re_code);
        byte[] return_byte = new byte[chai];

        if (str != null) {
            try {
                for (i = f_data; i < l_data; i++) {
                    return_byte[k] = full_data[i];
                    k++;
                }
            } catch (Exception e) {
            }

            return new String(return_byte);
        } else {
            return "";
        }
    }

    /**
     * 기능 : 널 체크 " <br>
     *
     * @param Objecgt 전달된 Object
     * @return 처리된 결과 bloolean
     */
    public static boolean isNull(Object str) {
        if ((str == null) || str.toString().toLowerCase().equals("null") ||
                str.toString().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ****************************************************************************************************************
     * 파일이름바꾸기 , 이동 <br>
     *
     * @param String 원본파일경로
     * @param String 원하는 파일경로
     * @return 성공시 true, 실패 false
     *         <p/>
     *         ****************************************************************************************************************
     */
    public boolean moveFile(String srcfilename, String targetfilename) {
        try {
            File fs = new File(srcfilename);
            File ft = new File(targetfilename);

            fs.renameTo(ft);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * argument 'orgValue'가 null이면, arguemnt 'defValue'의 값을 리턴하고,
     * null이 아니면, 'oraValue'를 리턴한다.
     */
    public static String onvl(String orgValue, String defValue) {
        if ((orgValue == null) || orgValue.equals("")) {
            return defValue;
        } else {
            return orgValue;
        }
    }

    /**
     * 전화, 팩스 번호 구분자로 자르기
     *
     * @param telNo
     * @return 구분자로 잘라진 배열
     */
    public static String[] getTelNo(String telNo) {
        String[] ret = new String[3];

        // 전화번호 구분자가 추가되면 여기에 추가...
        if (telNo.length() < 6) {
            telNo = "";
        }

        int delimIndexF = telNo.indexOf("-");
        int delimIndexS = telNo.indexOf("/");

        if (telNo.equals("")) {
            for (int i = 0; i < ret.length; i++) {
                ret[i] = "";
            }

            return ret;
        }

        if ((delimIndexF != -1) && (delimIndexS == -1)) {
            ret = (telNo.trim()).split("-");
        } else if ((delimIndexF == -1) && (delimIndexS != -1)) {
            ret = (telNo.trim()).split("/");
        }

        return ret;
    }

    //	 쿠키 가져오기
    public static String getCookie(HttpServletRequest request, String CookieName)
            throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        String value = "";

        for (int i = 0; i < cookies.length; i++) {
            if (CookieName.equals(cookies[i].getName())) {
                value = cookies[i].getValue();

                break;
            }
        }

        return value;
    }

    /*
    //         쿠키 가져오기
    public static String getCookie(HttpServletRequest request, String CookieName) throws Exception {
             Cookie [] cookies = request.getCookies();
             if(cookies==null) return null;
             String value = "";

             for(int i=0;i<cookies.length;i++) {
              if(CookieName.equals(cookies[i].getName())) {
               value = cookies[i].getValue();
               break;
              }
             }

             return value;
    }
    */

    //	icip.NET Unix 암호화 가져오기 (postgresql.jar : WEB-INF/lib 등록필요)
    public static String encrypt(String s) throws Exception {
        char[] ac = {'N', 'o'};
        char[] ac1 = new char[s.length()];

        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) > '\177') {
                ac1[i] = (char) (s.charAt(i) & 0x7f);
            } else {
                ac1[i] = s.charAt(i);
            }

        if (s.length() > 1) {
            ac[0] = ac1[0];
            ac[1] = ac1[1];
        }

        String s1 = String.valueOf(ac);
        String s2 = String.valueOf(ac1);

        return UnixCrypt.crypt(s1, s2.toString());
    }

    // icip.NET Unix 암호화 가져오기 (postgresql.jar : WEB-INF/lib 등록필요)
    public static String encrypt2(String s) throws Exception {
        char[] ac = {'N', 'o'};
        char[] ac1 = new char[s.length()];

        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) > '\177') {
                ac1[i] = (char) (s.charAt(i) & 0x7f);
            } else {
                ac1[i] = s.charAt(i);
            }

        if (s.length() > 1) {
            ac[0] = ac1[0];
            ac[1] = ac1[1];
        }

        String s1 = String.valueOf(ac);
        String s2 = String.valueOf(ac1);

        return UnixCrypt.crypt(s1, s2.toString());
    }

    // 쿠키 암호화 Crypt 패키지 필요
    public static String CkCrypt(String CookieString) throws Exception {
        //String strPath = "/SERV2/BEA81/user_projects/applications/member/WEB-INF/classes/keygen/";
        String strPath = "";

        System.out.println(System.getProperty("file.separator") +"====");
        
        if (System.getProperty("file.separator").equals("/")) {
            strPath = "운영서버path";
        } else {
        	strPath = "local path";

        }
        System.out.println( "strPath====" + strPath);
        //MyElgConsoleVersion  keygen = new MyElgConsoleVersion();
        //ElgamalPublicKey pubKey = null; // 공개키 객체 생성
        //ElgamalPrivateKey priKey = null; // 개인키 객체 생성
        SecureRandom srdom = null; // 난수 생성

        //System.out.println("파일로부터 키를 로딩합니다.");
        //FileInputStream fis = new FileInputStream(strPath + "public.key");
        /*
        try{
                int i =0;
          while((i=fis.read()) != -1) {
              //System.out.println(i);
           }
        }catch(Exception e){

        }*/
        ObjectInputStream pubIn = new ObjectInputStream(new FileInputStream(strPath + "public.key"));
        ObjectInputStream priIn = new ObjectInputStream(new FileInputStream(strPath + "private.key"));
        ObjectInputStream srdIn = new ObjectInputStream(new FileInputStream(strPath + "securerandom.key"));

        try {
            //pubKey = (ElgamalPublicKey)pubIn.readObject(); // 공개키를 가져온다.
            //pubKey = (ElgamalPublicKey)pubIn.readObject();
            //priKey = (ElgamalPrivateKey)priIn.readObject();
            srdom = (SecureRandom) srdIn.readObject();
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            //System.out.println(e2);
            e2.printStackTrace();
        } finally {
            try {
                pubIn.close();
            } catch (Exception e) {
            }

            try {
                priIn.close();
            } catch (Exception e) {
            }

            try {
                srdIn.close();
            } catch (Exception e) {
            }
        }

        //CookieString = CookieString.trim().replaceAll(" ","").replaceAll("\n", "").replaceAll("\\s", "");
        String tempId = "";
        String tempName = "";
        StringBuffer noBlank1 = new StringBuffer();

        for (int i = 0; i < CookieString.length(); i++) {
            char c = CookieString.charAt(i);

            if ((c != ' ') && (c != '\n')) {
                noBlank1.append(c);
            }
        }

        tempId = noBlank1.toString();
        CookieString = tempId;
        tempId = "";

        try {
            //CookieString = keygen.getPrivKey(CookieString, priKey, srdom);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return CookieString;
    }


    /**
     * 기능 : 사업자 등록번호 자리수 나누기
     *
     * @param YYYYYYYYYY
     * @return String YYY-YY-YYYYY
     */
    public static String getBizFormat(String bizNo) {
        if (bizNo == null || bizNo.length() < 10) {
            return bizNo;
        }

        return bizNo.substring(0, 3) + "-" + bizNo.substring(3, 5) + "-" + bizNo.substring(5, 10);
    }

    /**
     * 년월일 시분초
     *
     * @param YYYYYYYYYY
     * @return String YYYY-MM-DD HH:MM:SS
     */
    public static String getYMD_HMS(String date) {

        if (date == null || date.length() < 14) {
            return date;
        }

        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " "
                + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12,14)   ;
     }

    /**
     * Excel파일을 리스트로 반환
     *
     * @return ArrayList
     */
    public static ArrayList simpleExcelRead(File targetFile, int firstRow, int firstCol) throws Exception {

	  Workbook workbook = null;
	  Sheet sheet = null;

	  ArrayList dataRowList  = null;
	  ArrayList dataColList  = null;

	  ArrayList testRowList  = null;
	  ArrayList testColList  = null;

	  HashMap colData  = null;
	  HashMap testColData  = null;

	  try {
	   workbook = Workbook.getWorkbook(targetFile);     //존재하는 엑셀파일 경로를 지정
	   sheet = workbook.getSheet(0);                       //첫번째 시트를 지정합니다.

	   int rowCount = sheet.getRows();                     //총 로우수를 가져옵니다.
	   int colCount = sheet.getColumns();                  //총 열의 수를 가져옵니다.

	   if(rowCount <= 0) {
	    throw new Exception("Read 할 데이터가 엑셀에 존재하지 않습니다.");
	   }

	   dataRowList = new ArrayList();

	   int recount = 0;
	   //엑셀데이터를 배열에 저장
	   for(int i = firstRow ; i < rowCount ; i++) {
		   dataColList = new ArrayList();
	    for(int k = firstCol ; k < colCount ; k++) {
	     Cell cell =sheet.getCell(k, i);                   // 해당위치의 셀을 가져오는 부분입니다.
	     if(cell == null){
	    	 continue;
	     }
	     colData = new HashMap();
	     colData.put("col"+recount, cell.getContents());   // 가져온 셀의 실제 콘텐츠 즉 데이터(문자열)를 가져오는 부분입니다.
	     dataColList.add(colData);
	     recount++;
	    }

	    dataRowList.add(dataColList);
	    recount = 0;
	   }

	   //데이터 검증 테스트
	   testRowList = dataRowList;
	   for(int r=0 ; r<testRowList.size() ; r++) {
		   testColList = (ArrayList)testRowList.get(r);
	    for(int c=0 ; c<testColList.size() ; c++) {
	    	testColData = (HashMap)testColList.get(c);
	     System.out.print(testColData.get("col"+c));
	    }
	    System.out.println();
	   }

	  } catch (Exception e) {
	   e.printStackTrace();
	   throw e;
	  } finally {
        try {
        if(workbook != null) workbook.close();
        } catch (Exception e) {
        }
	  }

	  return dataRowList;
	 }

    /**
     * 현재 연도를 리턴한다.
    **/
    public static String getYear()
	{
		Calendar today = Calendar.getInstance();

		int year = today.get(Calendar.YEAR);
		int mon = today.get(Calendar.MONTH)+1;

		String str = "";

		str += year;

		return str;
	}

    /**
     * 현재 월을 리턴한다.
    **/
    public static String getMonth()
	{
        Calendar today = Calendar.getInstance();

		int mon = today.get(Calendar.MONTH)+1;

		String str = "";

		if(mon < 10) str += "0";

		str += mon;

		return str;
	}
    
    /**
     * param1 값이 null 이거나 공백이면, param2으로 치환한다.
     * @param param1
     * @param param2
     * @return
     */
    public static String setChangeNull(String param1, String param2){
    	if(param1 == null || "".equals(param1.trim())){
    		if(param2 == null) param2 = "";
    		return param2;
    	}
    	else return param1;
    }
    
    /**
     * 공고모니터링에서 파일업로드
     * @param param1
     * @param param2
     * @return
     */
    public static void fileUpload(Map<String,String> requestParamMap,HttpServletRequest request) throws Exception
    {
        String attachFileName = "";
        String downloadDir = "";

        FileInputStream fisConf= null;
        
        try{
            
            downloadDir = propertiesService.getString("icip.application.path");
            
            File temporaryDir = new File(downloadDir);
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1 * 1024 * 1024);
            factory.setRepository(temporaryDir);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(10 * 1024 * 1024); 
            
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter=items.iterator();
            
            while(iter.hasNext()){
                
                FileItem fileItem = (FileItem) iter.next(); 
                
                if(fileItem.isFormField()){
                    String fname = fileItem.getFieldName();
                    String fitem = fileItem.getString("EUC-KR");
                    requestParamMap.put(fname, fitem);
                    
                } else {
                    if(fileItem.getSize()>0){
                        String fileName = fileItem.getName();
                        String fieldName=fileItem.getFieldName();
                        String contentType=fileItem.getContentType();
                        boolean isInMemory=fileItem.isInMemory();
                        long sizeInBytes=fileItem.getSize();
                        attachFileName = fileName;
                        
                        try {
                            File tgetDir = new File(downloadDir);
                            if(!tgetDir.exists()){
                                if(!tgetDir.mkdirs())
                                    tgetDir.mkdirs();
                            }
                            File uploadedFile=new File(downloadDir, fileName);
                            fileItem.write(uploadedFile);
                            fileItem.delete();
                            
                            String tempFileName = getTempFileName(attachFileName);
                            fileMngUtil.copyFile(downloadDir+attachFileName, downloadDir+tempFileName);
                            requestParamMap.put("DIRECTORY_PATH", downloadDir);
                            requestParamMap.put("TEMP_FILE_NAME", tempFileName);
                            requestParamMap.put("FILE_NAME", attachFileName);
                            
                        }catch(IOException ex) {
                            throw ex;
                        }
                        
                    }
                } 
            }
        }catch(Exception e){
                e.printStackTrace();
            throw e;
        }finally{
            try{ 
                //fisConf.close(); 
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }
    }

    public static String getTempFileName(String fileName) throws Exception {
        
        SimpleDateFormat simpleFormat=new SimpleDateFormat("yyyyMMddHHmmsss");
        Calendar todayMicCal = Calendar.getInstance();

        try{

            String tempFileName = simpleFormat.format(todayMicCal.getTime());
            String tempFileName1 = tempFileName+fileName.substring(fileName.indexOf("."));
            return tempFileName1;
            
        } catch(Exception e) {
            throw e;
        }
        
    }
    
    public static String toJsonString(List<EgovMap> rs){
        StringBuffer sb = new StringBuffer();
        int nListCnt = rs.size();
        try{
            if (nListCnt==0){
                sb.append("{}");
            }else{
                if (nListCnt>0){sb.append("[");}
                
                for (int i = 0; i < nListCnt; i++) {
                    if (i>0){
                        sb.append(",");
                    }
                    //Map<Object, Object> tmpMap= new HashMap<Object, Object>();
                    //tmpMap = (Map<Object, Object>) rs.get(i);
                    Set key = rs.get(i).keySet();
                    int j = 0;
                    sb.append("{");
                    for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                        String keyName = (String) iterator.next();
                        String valueName = (String) rs.get(i).get(keyName);
                        if (j>0){sb.append(",");}
                        sb.append("\"").append(keyName).append("\":").append("\"").append(valueName).append("\"");
                        j++;
                   }
                    sb.append("}");
                }
                if (nListCnt>0){sb.append("]");}                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();   
    }
    

    /**
     * 프로펄티 값을 로드 한다.
    **/
    public static String getDextUploadPath()
    {
        String dextUploadpath = ""; 
        Properties props = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            props.load(classLoader.getResourceAsStream("icip/config/icip.properties"));
            dextUploadpath = props.getProperty("icip.dextUpload.path");
        } catch (IOException e) {
            LOGGER.debug("getDextUploadPath Exception : "+ e.toString());
        }    
        return dextUploadpath;
    }
    
    /**
     * 엑셀의 셀 값을 읽는다.
    **/
    public static String getCell(Sheet sheet, int c, int r){
        Cell cell = sheet.getCell(c, r);

        if(cell == null){
            return "";
        }
        return cell.getContents();
    }
}
