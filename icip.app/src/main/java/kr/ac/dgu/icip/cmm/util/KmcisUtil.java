
package kr.ac.dgu.icip.cmm.util; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * kr.ac.dgu.icip.cmm.util 
 *    |_ KmcisUtil.java
 * 1. 개요 : 
 * 2. 처리내용 : 
 * </pre>
 * @date : 2019. 03. 14. 오전 11:22:09
 * @version : 
 * @author : DGU
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 03. 14.		DGU				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */
public class KmcisUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(KmcisUtil.class);

	public KmcisUtil() {
	}

	/**
	 * <pre>
	 * 1. 개요 : 패턴 매칭 비교
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : paramChk
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param patn
	 * @param param
	 * @return
	 */ 	
	public static Boolean paramChk(String patn, String param){
		boolean b = true;
		Pattern pattern = Pattern.compile(patn);
		Matcher matcher = pattern.matcher(param);
		b = matcher.matches();
		return b;
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 스트링 객체를 비교 한다. 
	 * </pre>
	 * @Method Name : isEquals
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param res
	 * @param ori
	 * @return
	 */ 	
	public static Boolean isEquals(String res,String ori){
		boolean ret  = false;
		if(res!=null&&ori!=null)
		{
			if(res.equals(ori)) ret = true;
		}
		return ret;
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 주민등록번호로 년도 포함된 생년원일 생성
			//성별 : 2000년 이후 여자는 4, 남자는 3 , 2000년 이전 여자는 2, 남자는 1
			//외국인 주민등록번호 뒷자리 첫번째 부여 방법
		    //1900년 ~ 1999년에 태어난 외국인 남자 5, 외국인여자 6
		    //2000년 ~ 2099년에 태어난 외국인 남자 7, 외국인여자 8	 * 
	 * </pre>
	 * @Method Name : getRegnotoBirthDay
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param reg_birth
	 * @param sexbit
	 * @return
	 */ 	
	public static String getRegnotoBirthDay(String reg_birth,String sex){
		String birthday = reg_birth;
		String yearYY = "19";
		char sexbit = sex.charAt(0);
		switch(sexbit){
			case '1':
			case '2':
			case '5':
			case '6':
				yearYY = "19";
				break;
			case '3':
			case '4':
			case '7':
			case '8':
				yearYY = "20";
				break;
			default :
				break;
		}
		return yearYY+birthday;
	}

	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 주민번호 성별을 0 남 1 여 로 표현
	 * </pre>
	 * @Method Name : getRegnotoSex
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param sexbit
	 * @return
	 */ 	
	public static String getRegnotoSex(String sex){
		//sex = sex==null?"0":sex;
		if(sex!=null&&sex.length()>0) {
			sex = getRegnotoSexMF(sex);
			if(sex.equals("M")) sex = "0";
			else sex = "1";
		}else{
			sex = "";
		}
		return sex;
	}	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 주민번호 성별을 F/M 으로 처리
	 * </pre>
	 * @Method Name : getRegnotoSexMF
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param sexbit
	 * @return
	 */ 	
	public static String getRegnotoSexMF(String sex){
		try {
			char sexbit = sex.charAt(0);
			switch(sexbit){
				case '1':
				case '3':
				case '5':
				case '7':
					sex = "M";
					break;
				case '2':
				case '4':
				case '6':
				case '8':
					sex = "F";
					break;
				default :
					break;
			}
		} catch (NullPointerException e) {
			sex = "-";
		}
		return sex;
	}		
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 주민번호 성별을 F/M 으로 처리
	 * </pre>
	 * @Method Name : getRegnotoSexMF
	 * @date : 2019. 03. 14.
	 * @author : DGU
	 * @param sexbit
	 * @return
	 */ 	
	public static String getRegnotoSexKor(String sex){
		try {
			char sexbit = sex.charAt(0);
			switch(sexbit){
				case '1':
				case '3':
				case '5':
				case '7':
					sex = "남";
					break;
				case '2':
				case '4':
				case '6':
				case '8':
					sex = "여";
					break;
				default :
					break;
			}
		} catch (NullPointerException e) {
			sex = "-";
		}
		return sex;
	}			
}
