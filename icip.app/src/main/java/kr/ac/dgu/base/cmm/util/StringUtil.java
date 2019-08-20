package kr.ac.dgu.base.cmm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * @Class Name : StringUtil.java
 * @Description :
 * String에 대한 Utility 클래스
 * @author DGU
 * @시스템 동국대학교 ICIP
 * @since 2019. 3. 7.
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
public class StringUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";

    /**
     * <p>Padding을 할 수 있는 최대 수치</p>
     */
    // private static final int PAD_LIMIT = 8192;
    /**
     * <p>An array of <code>String</code>s used for padding.</p>
     * <p>Used for efficient space padding. The length of each String expands as needed.</p>
     */
    /*
	private static final String[] PADDING = new String[Character.MAX_VALUE];

	static {
		// space padding is most common, start with 64 chars
		PADDING[32] = "                                                                ";
	}
     */

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else
                returnVal = source;
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str 입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }

    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }


    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     *
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr  = source;

        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }

        return srcStr;
    }

    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }


    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo", "bar")= "foo"
     * StringUtil.decode("", null, "foo", "bar") = "bar"
     * StringUtil.decode(null, "", "foo", "bar") = "bar"
     * StringUtil.decode("하이", "하이", null, "bar") = null
     * StringUtil.decode("하이", "하이  ", "foo", null) = null
     * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        if (sourceStr == null && compareStr == null) {
            return returnStr;
        }

        if (sourceStr == null && compareStr != null) {
            return defaultStr;
        }

        if (sourceStr.trim().equals(compareStr)) {
            return returnStr;
        }

        return defaultStr;
    }

    /**
     * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
     * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
     * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
     * </p>
     *
     * <pre>
     * StringUtil.decode(null, null, "foo") = "foo"
     * StringUtil.decode("", null, "foo") = ""
     * StringUtil.decode(null, "", "foo") = null
     * StringUtil.decode("하이", "하이", "foo") = "foo"
     * StringUtil.decode("하이", "하이 ", "foo") = "하이"
     * StringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     *
     * @param sourceStr 비교할 문자열
     * @param compareStr 비교 대상 문자열
     * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
     *         <br/>다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";

        if (object != null) {
            string = object.toString().trim();
        }

        return string;
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 defStr 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object, String defStr) {
        String str = isNullToString(object);
        return (str.equals("") ? isNullToString(defStr) : str);
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(Object src) {
	//if (src != null && src.getClass().getName().equals("java.math.BigDecimal")) {
	if (src != null && src instanceof java.math.BigDecimal) {
	    return ((BigDecimal)src).toString();
	}

	if (src == null || src.equals("null")) {
	    return "";
	} else {
	    return ((String)src).trim();
	}
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static String nullConvert(String src) {

	if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
	    return "";
	} else {
	    return src.trim();
	}
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(Object src) {
        int ret = 0;
        try {
            if (src == null || src.equals("null")) {
                return ret;
            } else {
                return Integer.parseInt(((String)src).trim());
            }
        } catch (Exception e) {
           return ret;
        }	
    }

    /**
     *<pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     *</pre>
     */
    public static int zeroConvert(String src) {
        int ret = 0;
    	try {
            if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
                return ret;
            } else {
                return Integer.parseInt(src.trim());
            }
        } catch (Exception e) {
           return ret;
        }
    }

    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }

        return new String(chs, 0, count);
    }

    /**
     * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
     *
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
	public static String checkHtmlView(String strString) {
		String strNew = "";

		StringBuffer strTxt = new StringBuffer("");

		char chrBuff;
		int len = strString.length();

		for (int i = 0; i < len; i++) {
			chrBuff = (char) strString.charAt(i);

			switch (chrBuff) {
				case '<':
					strTxt.append("&lt;");
					break;
				case '>':
					strTxt.append("&gt;");
					break;
				case '"':
					strTxt.append("&quot;");
					break;
				case 10:
					strTxt.append("<br>");
					break;
				case ' ':
					strTxt.append("&nbsp;");
					break;
				//case '&' :
				//strTxt.append("&amp;");
				//break;
				default:
					strTxt.append(chrBuff);
			}
		}

		strNew = strTxt.toString();
		return strNew;
	}


    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }

    /**
     * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }

        return str.substring(start);
    }


    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }

        return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
	if (isEmpty(str)) {
	    return str;
	}

	String srcStr = str;
	srcStr = stripStart(srcStr, stripChars);

	return stripEnd(srcStr, stripChars);
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }

        return returnVal;
    }

    /**
     * 문자열 A에서 Z사이의 랜덤 문자열을 구하는 기능을 제공 시작문자열과 종료문자열 사이의 랜덤 문자열을 구하는 기능
     *
     * @param startChr
     *            - 첫 문자
     * @param endChr
     *            - 마지막문자
     * @return 랜덤문자
     * @exception MyException

     */
	public static String getRandomStr(char startChr, char endChr) {

		int randomInt;
		String randomStr = null;

		// 시작문자 및 종료문자를 아스키숫자로 변환한다.
		int startInt = Integer.valueOf(startChr);
		int endInt = Integer.valueOf(endChr);

		// 시작문자열이 종료문자열보가 클경우
		if (startInt > endInt) {
			throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
		}

		// 랜덤 객체 생성
		SecureRandom rnd = new SecureRandom();

		do {
			// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
			randomInt = rnd.nextInt(endInt + 1);
		} while (randomInt < startInt); // 입력받은 문자 'A'(65)보다 작으면 다시 랜덤 숫자 발생.

		// 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
		randomStr = (char) randomInt + "";

		// 랜덤문자열를 리턴
		return randomStr;
	}

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     *
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException

     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

	String rtnStr = null;

	if (srcString == null)
	    return null;

	try {
	    rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
	} catch (UnsupportedEncodingException e) {
	    rtnStr = null;
	}

	return rtnStr;
    }

/**
     * 특수문자를 웹 브라우저에서 정상적으로 보이기 위해 특수문자를 처리('<' -> & lT)하는 기능이다
     * @param 	srcString 		- '<'
     * @return 	변환문자열('<' -> "&lt"
     * @exception MyException

     */
    public static String getSpclStrCnvr(String srcString) {

	String rtnStr = null;

	try {
	    StringBuffer strTxt = new StringBuffer("");

	    char chrBuff;
	    int len = srcString.length();

	    for (int i = 0; i < len; i++) {
		chrBuff = (char)srcString.charAt(i);

		switch (chrBuff) {
		case '<':
		    strTxt.append("&lt;");
		    break;
		case '>':
		    strTxt.append("&gt;");
		    break;
		case '&':
		    strTxt.append("&amp;");
		    break;
		default:
		    strTxt.append(chrBuff);
		}
	    }

	    rtnStr = strTxt.toString();

	} catch (NullPointerException e) {
		LOGGER.debug("{}", e);
	}catch (Exception e) {
		LOGGER.debug("{}", e);
	}

	return rtnStr;
    }

    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException

     */
    public static String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
    }
    /**
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException

     */
    public static String getPITimeStamp() {

		String rtnStr = null;

		String pattern = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
    }    

    /**
     * html의 특수문자를 표현하기 위해
     *
     * @param srcString
     * @return String
     * @exception Exception

     */
    public static String getHtmlStrCnvr(String srcString) {

    	String tmpString = srcString;

		tmpString = tmpString.replaceAll("&lt;", "<");
		tmpString = tmpString.replaceAll("&gt;", ">");
		tmpString = tmpString.replaceAll("&amp;", "&");
		tmpString = tmpString.replaceAll("&nbsp;", " ");
		tmpString = tmpString.replaceAll("&apos;", "\'");
		tmpString = tmpString.replaceAll("&quot;", "\"");

		return  tmpString;

	}

    /**
     * <p>날짜 형식의 문자열 내부에 마이너스 character(-)를 추가한다.</p>
     *
     * <pre>
     *   StringUtil.addMinusChar("20100901") = "2010-09-01"
     * </pre>
     *
     * @param date  입력받는 문자열
     * @return " - "가 추가된 입력문자열
     */
	public static String addMinusChar(String date) {
		if(date.length() == 8)
		    return date.substring(0,4).concat("-").concat(date.substring(4,6)).concat("-").concat(date.substring(6,8));
		else return "";
	}
	/*사업자번호용*/
   public static String addMinusCharForCompNo(String compNo) {
        if(compNo.length() == 10)
            return compNo.substring(0,3).concat("-").concat(compNo.substring(3,5)).concat("-").concat(compNo.substring(5));
        else return "";
    }

	/**
	 * msg 를 보여주고 전 페이지로 이동시킨다.
	 * @param response
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	public static void alertRedirect(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html; charset=\"utf-8\"");
        response.getWriter().println("<script type=\"text/javascript\">alert('"+msg+"'); history.back();</script>");
        response.getWriter().flush();
        response.getWriter().close();
    }

	/**
     * msg 를 보여주고 url 페이지로 리다이렉트
     * @param response
     * @param msg
     * @return
     * @throws IOException
     */
    public static String alertRedirect(HttpServletResponse response, String msg, String url) throws IOException {
        response.setContentType("text/html; charset=\"utf-8\"");
        response.getWriter().println("<script type=\"text/javascript\">alert('"+msg+"'); history.back();</script>");
        response.getWriter().flush();
        response.getWriter().close();

        return  "redirect:/"+url;
    }

    /**
     * 패턴에 따른 현재 날짜, 시간 리턴
     * @param pattern
     * @return
     */
    public static String getTimeStamp(String pattern) {

        String rtnStr = "";

        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        rtnStr = sdfCurrent.format(ts.getTime());

        return rtnStr;
    }

	/**
	 * 트랜잭션 테이블의 PK값을 생성한다.
	 * @return
	 */
	public static String getTranId(){
		String tranId = DateUtil.getDatetimeString() + getOtherNum();
		String temp = "";
		if(tranId == null){
			tranId = DateUtil.getDatetimeString() + getOtherNum();
		}else if(tranId.length() < 26 ){
			temp = padding(26 - tranId.length() , "0");
			tranId = tranId + temp;
		}else if(tranId.length() > 26 ){
			tranId = tranId.substring(0,26);
		}
		return tranId ;
	}

	public static String getNumStr(String number) {
        return getNumStr(number, "###,###,###,###.##");
    }

	public static String getNumStr(String number, String format) {
        String str = null;
        try {
            double num = Double.parseDouble(number);
            DecimalFormat formatter = new DecimalFormat(format);
            str = formatter.format(num);
        } catch(NumberFormatException e) {
            str = "0";
        } catch(Exception e) {
            str = "0";
        }

        return str;
     }

	public static String getOtherNum() {

		  Random generator = new Random();
		  long num1 = 0;
		  long num2 = 0;
		  generator.setSeed(System.currentTimeMillis());
		  num1 = generator.nextInt(1000000);
		  num2 = generator.nextInt(1000000);
		  String str = num1 +""+ num2 ;
		  if(str.length() > 12)
			  str = str.substring(0,12);

		  return  str ;
	}

	/**
	 * 해당 문자를 원하는만큼 반복하여 문자열을 반환
	 *
	 * @param repeat
	 *            반복할 수
	 * @param padString
	 *            해당 문자열
	 * @return String
	 */
	public static String padding(int repeat, String padString) {
		int inx = 0;
		StringBuffer str = new StringBuffer();
		while (inx++ < repeat) {
			str.append(padString);
		}
		return str.toString();
	}

	/**
	 * 문자열에서 HTML Tag를 삭제한다.
	 * @param tagString
	 * @return
	 */
	public static String removeHtmlTag(String tagString) {
	    if(StringUtils.isEmpty(tagString)) {
	        return "";
	    }

	    return tagString.replaceAll("\\<.*?\\>", "");
	}

	/**
	 * 엑셀저장 시 태그제거
	 * @param str 대상문자열
	 * @return 태그제거된 문자열
	 */
	public static String removeHtmlTagForExcel(String argStr) {
	    String str = argStr;

	    //a, map, area, image, script 태그는 유지
	    str = str.replaceAll("<(/)?([mM][aA][pP])", "&lt;map");
	    str = str.replaceAll("<(/)?([aA])", "&lt;a");
	    str = str.replaceAll("<(/)?([iI][mM][gG])", "&lt;img");
	    str = str.replaceAll("<(/)?([sS][cC][rR][iI][pP][tT])", "&lt;script");

	    //<br>,<p>,<tr>,<hr> 태그에 관하여 개행문자 추가
	    str = str.replaceAll("<(/)?[bB][rR](//s)*(/)?>", "\n").replaceAll("<(/)?[hH][rR](//s)*(/)?>", "\n").replaceAll("</[pP]>", "\n").replaceAll("</[tT][rR]>", "</tr>\n");
	    //태그제거
	    str = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
	            .replaceAll("&nbsp;"," ")
	            .replaceAll("&lt;","<")
	            .replaceAll("&gt;",">")
	            .replaceAll("&quot;","\"")
	            .replaceAll("&amp;","&");

	    str = str.replaceAll("\\<!--[^>][\\s\\S]*?--\\>", "");

	    //반복되는 공백을 하나의 공백으로 변경
	    str = str.replaceAll("[ ]+", " ").replaceAll("\\p{Z}{2,}", " ").replaceAll("(?m)^ +", "");
	    //엑셀저장 시 케리지 리턴 값이 ♪로 표기됨으로 수정
	    str = str.replaceAll("\r","\n").replaceAll("\n\r","\n");
        //빈줄 제거
	    str = str.replaceAll("\r\n{2,}|\n{2,}|\r{2,}","\n");

	    return str;
	}

	/**
     * HTML 테그 제거
     * @param htmlStr 문자열
     * @param cho 범위
     * @return String
     */
    public static String stripHTML(String htmlStr, String cho) {
        // 정규식 표현 미리 정의해놓음
        final Pattern HTML_SCRIPT = Pattern.compile("\\<script[^>]*?>[\\s\\S]*?\\<\\/script\\>", Pattern.CASE_INSENSITIVE);
        final Pattern HTML_STYLE = Pattern.compile("\\<style[^>]*?>[\\s\\S]*?\\<\\/style\\>", Pattern.CASE_INSENSITIVE);
        final Pattern HTML_OPTION = Pattern.compile("\\<option[^>]*?>[\\s\\S]*?\\<\\/option\\>", Pattern.CASE_INSENSITIVE);
        final Pattern HTML_HEAD = Pattern.compile("\\<head[^>]*?>[\\s\\S]*?\\<\\/head\\>", Pattern.CASE_INSENSITIVE);
        final Pattern HTML_TAG = Pattern.compile("\\<.*?\\>", Pattern.CASE_INSENSITIVE);
        final Pattern HTML_ANNOTATION = Pattern.compile("\\<!--[^>][\\s\\S]*?--\\>", Pattern.CASE_INSENSITIVE);
        final Pattern HTML_CDATA = Pattern.compile("\\<!\\[CDATA[\\s\\S]*?\\]\\>", Pattern.CASE_INSENSITIVE);

        String rtnHtml = "";

        try {
            rtnHtml = htmlStr.replaceAll("\r\n", " ");

            if (cho.indexOf("/1") >= 0) {  // 스크립트(script) 테그  및 내용 제거
                rtnHtml = HTML_SCRIPT.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/2") >= 0) {  // 스타일(style) 테그 및 내용 제거
                rtnHtml = HTML_STYLE.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/3") >= 0) {  // 옵션(option) 테그 및 내용 제거
                rtnHtml = HTML_OPTION.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/4") >= 0) {  // 헤더(head) 테그 제거
                rtnHtml = HTML_HEAD.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/5") >= 0) {  // 모든 테그 및 내용 제거
                rtnHtml = HTML_TAG.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/6") >= 0) {  // 모든 HTML 주석 및 내용 제거 (<!-- -->)
                rtnHtml = HTML_ANNOTATION.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/7") >= 0) {  // 모든 CDATA 및 주석 제거
                rtnHtml = HTML_CDATA.matcher(rtnHtml).replaceAll("");
            }
            if (cho.indexOf("/8") >= 0) {    // nbsp 태그 제거
                rtnHtml = rtnHtml.replace("&nbsp;", "");
            }
            if (cho.indexOf("/9") >= 0) {    // nbsp 태그 공백으로 변경
                rtnHtml = rtnHtml.replace("&nbsp;", " ");
            }
            if (cho.indexOf("/p") >= 0) {    // nbsp 태그 공백으로 변경
                rtnHtml = rtnHtml.replace("<p>", "");
                rtnHtml = rtnHtml.replace("</p>", "");
            }
            if (cho.indexOf("/^") >= 0) {    // nbsp 태그 공백으로 변경
                rtnHtml = rtnHtml.replace("&lt;", "<");
                rtnHtml = rtnHtml.replace("&gt;", ">");
            }

            rtnHtml = rtnHtml.trim();

        } catch(NullPointerException e) {
            return null;
        }catch(Exception e) {
            return null;
        }
        return rtnHtml;
    }

    /**
     * 특정문자를 HTML espace 문자로 변환한다.
     * @param parameter
     * @param argValues
     * @return
     */
    public static String [] escapeHTMLChar(String parameter, String [] argValues) {
        String [] values = argValues;

        if (values == null) {
            return null;
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("before : " + parameter + "- " + Arrays.toString(values));
        }

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                StringBuffer strBuff = new StringBuffer();
                for (int j = 0; j < values[i].length(); j++) {
                    char c = values[i].charAt(j);
                    switch (c) {
                        case '<':
                            strBuff.append("&lt;");
                            break;
                        case '>':
                            strBuff.append("&gt;");
                            break;
                        case '&':
                            strBuff.append("&amp;");
                            break;
                        case '"':
                            strBuff.append("&quot;");
                            break;
                        case '\'':
                            strBuff.append("&apos;");
                            break;
                        default:
                            strBuff.append(c);
                            break;
                    }
                }
                values[i] = strBuff.toString();
            } else {
                values[i] = null;
            }
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("after : " + parameter + " - " + Arrays.toString(values));
        }

        return values;
    }

    /**
     * 특정문자를 HTML espace 문자로 변환한다.
     * @param parameter
     * @param argValue
     * @return
     */
    public static String escapeHTMLChar(String parameter, String argValue) {
        String value = argValue;

        if (value == null) {
            return null;
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("before : " + parameter + " - " + value);
        }

        StringBuffer strBuff = new StringBuffer();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '<':
                    strBuff.append("&lt;");
                    break;
                case '>':
                    strBuff.append("&gt;");
                    break;
                case '&':
                    strBuff.append("&amp;");
                    break;
                case '"':
                    strBuff.append("&quot;");
                    break;
                case '\'':
                    strBuff.append("&apos;");
                    break;
                default:
                    strBuff.append(c);
                    break;
            }
        }

        value = strBuff.toString();

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("after : " + parameter + " - " + value);
        }

        return value;
    }

    /**
     * HTML espace를 원문자로 변환한다.
     * @param argValue
     * @return
     */
    public static String unescapeHTMLChar(String argValue) {
        String value = argValue;

        if (value == null) {
            return null;
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("before : " + value);
        }

        value = value.replaceAll("&lt;", "<");
        value = value.replaceAll("&gt;", ">");
        value = value.replaceAll("&amp;", "&");
        value = value.replaceAll("&quot;", "\"");
        value = value.replaceAll("&apos;", "'");

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("after : " + value);
        }

        return value;
    }
    /**
     * 줄바꿈 등을 삭제 한다.
     * @param argValue
     * @return
     */
    public static String replaceLineFeed(String argValue) {
        String value = argValue;

        if (value == null) {
            return null;
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("before : " + value);
        }

        value = value.replaceAll("\r", "");
        value = value.replaceAll("\n", "");
        value = value.replaceAll("\r\n", "");
        value = value.replaceAll("\"", "");

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("after : " + value);
        }

        return value;
    }
    /**
     * 전화번호를 포멧팅한다.
     * @param argValue
     * @return
     */
    public static String getPhoneNumberLocal(String argValue) {
    	String value = "";

        if (argValue != null) {
            try {
            	 PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            	 PhoneNumber swissNumberProto = phoneUtil.parse(argValue, "KR");
                 value = phoneUtil.format(swissNumberProto, PhoneNumberFormat.NATIONAL);
                 if(value.indexOf("-")<0) value =  argValue;
            } catch (NumberParseException e) {
            	LOGGER.error("getPhoneNumberLocal NumberParseException was thrown: " + e.toString()); 
            }
        }
        return value;
    } 
    
    public static List<String> getPhoneNumberLocalDevide(String argValue) {
        List<String> phoneArr = new ArrayList<String>();

        if (argValue != null) {
            try {
                 PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                 PhoneNumber swissNumberProto = phoneUtil.parse(argValue, "KR");
                 phoneArr.add(""+swissNumberProto.getNationalNumber());
                 phoneArr.add(""+swissNumberProto.getCountryCode());
                 phoneArr.add(""+swissNumberProto.getExtension());
            } catch (Exception e) {
                LOGGER.error("getPhoneNumberLocal NumberParseException was thrown: " + e.toString()); 
            }
        }
        return phoneArr;
    }        
    
    /**
     * XSS 공격 취약 문자열을 변환한다.
     * 대상 문자열을 ""로 변환
     * @param argValue
     * @return
     */
    public static String forceAntiXSS(String argValue) {
        String value = argValue;

        if (value == null) {
            return null;
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("before : " + value);
        }

        Pattern p = Pattern.compile("<script\\b([^>]*)>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("</script\\b\\s*>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("<form\\b([^>]*)>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("</form\\b\\s*>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("<input\\b([^>]*)>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("</input\\b\\s*>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("<textarea\\b([^>]*)>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("</textarea\\b\\s*>", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("[\\ |\\t]+(on\\w+=[^\\ |\\t|/|>]*)", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("mailto\\:", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("wss\\:", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        p = Pattern.compile("alert\\s*\\([^\\)]*\\)", Pattern.CASE_INSENSITIVE);
        m = p.matcher(value);
        value = m.replaceAll("");

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("after : " + value);
        }

        return value;
    }
    /**
     * Object type 변수가 비어있는지 체크
     * 
     * @param obj 
     * @return Boolean : true / false
     */
    public static Boolean empty(Object obj) {
     if (obj instanceof String) return obj == null || "".equals(obj.toString().trim());
     else if (obj instanceof List) return obj == null || ((List) obj).isEmpty();
     else if (obj instanceof Map) return obj == null || ((Map) obj).isEmpty();
     else if (obj instanceof Object[]) return obj == null || Array.getLength(obj) == 0;
     else return obj == null;
    }
    
    /**
     * Object type 변수가 비어있지 않은지 체크
     * 
     * @param obj
     * @return Boolean : true / false
     */
    public static Boolean notEmpty(Object obj) {
     return !empty(obj);
    }
    
    /**
     * <pre>
     * 개요 : ArrayList 를 쿼리 in String으로 변환 리턴한다.
     * </pre>
     * @Method Name : getStringArrayList
     * @author : dgu
     * @date : 2018. 9. 11.
     * @param arrlist
     * @return
     */     
    public static String getStringArrayList(ArrayList<String> arrlist)
    {
        StringBuilder sb = new StringBuilder();
        
        if(arrlist!=null && arrlist.size()>0){
            Iterator<String> it = arrlist.iterator();
            for (;;) {
                String e = it.next();
                sb.append("'").append(e).append("'");
                if (it.hasNext()) sb.append(',');
            }
            
        }
        return sb.toString();
    }    
    public static String getStringArrayList(List<String> arrlist)
    {
        StringBuilder sb = new StringBuilder();
        
        if(arrlist!=null && arrlist.size()>0){
            Iterator<String> it = arrlist.iterator();
            for (;;) {
                String e = it.next();
                sb.append("'").append(e).append("'");
                if (it.hasNext()) sb.append(',');
            }
            
        }
        return sb.toString();
    }
    /**
     * <pre>
     * 개요 : clob 데이터를 String속성으로 변환
     * </pre>
     * @Method Name : clobToString
     * @author : Administrator
     * @date : 2018. 9. 12.
     * @param argValue
     * @return
     */ 	
    public static String clobToString(Clob argValue){
        
        StringBuffer strOut = new StringBuffer();
        try {
                String str = "";
    
                BufferedReader br;
                br = new BufferedReader(argValue.getCharacterStream());

                while ((str = br.readLine()) != null) {
                    strOut.append(str);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        return strOut.toString();
    }

    /**
     * <pre>
     * 개요 : 다중 스페이스 제거
     * </pre>
     * @Method Name : singleSpaceReturn
     * @author : dgu
     * @date : 2018. 9. 18.
     * @param text
     * @return
     */ 	
    public static String singleSpaceReturn(String text) { 
        char[] toCharArray = text.toCharArray(); 
        int index = 1; 

        for (int i = 1; i < toCharArray.length; i++) { 
            toCharArray[index] = toCharArray[i]; 
            if (toCharArray[index] != ' ') { 
                index++; 
            } else if (toCharArray[index - 1] != ' ') { 
                index++; 
            } 
        } 
        
        return new String(toCharArray, 0, index).trim(); 
    }     
  
    /**
     * <pre>
     * 개요 : 유니코드 한글 변환
     * </pre>
     * @Method Name : unicodeConvert
     * @author : dgu
     * @date : 2018. 10. 24.
     * @param str
     * @return
     */ 	
    public static String unicodeConvert(String str) {
        StringBuilder sb = new StringBuilder();
        char ch;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            if (ch == '\\' && str.charAt(i+1) == 'u') {
                sb.append((char) Integer.parseInt(str.substring(i+2, i+6), 16));
                i+=5;
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
    
}
