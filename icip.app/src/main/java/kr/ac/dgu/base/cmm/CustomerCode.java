package kr.ac.dgu.base.cmm;

/**
 *  개인고객 코드
 */
public class CustomerCode {

	/** VALUE - BLANK */
	public final static String VALUE_BLANK = "";
	/** VALUE - YES */
	public final static String VALUE_YES   = "Y";
	/** VALUE - NO */
	public final static String VALUE_NO    = "N";
	/** VALUE - 0 */
	public final static String VALUE_ZERO  = "0";
	
	/** 페이지당 테이블 라인 수 */
	public final static int DEFAULT_LINE_COUNT_PER_PAGE = 10;
	
	/** 고객구분 - 개인 */
	public final static String CUSTOMER_KIND_PRIVATE = "P";
	/** 고객구분 - 법인 */
	public final static String CUSTOMER_KIND_COMPANY = "C";

	/** 데이터 원천 구분 010 */
	public final static String SYSTEM_TP_CD_010 = "010";

	/** 누계 */
	public final static String TRADE_ACM_KIND_SUM   = "001";
	/** 당월 */
	public final static String TRADE_ACM_KIND_MONTH = "002";
	
	/** 수신거부 테이블 등록 코드(T_REJECT - C_MEDIA_CODE) - 전화 */
	public final static String REJECT_MEDIA_CODE_PHONE = "1";
	/** 수신거부 테이블 등록 코드(T_REJECT - C_MEDIA_CODE) - 핸드폰 */
	public final static String REJECT_MEDIA_CODE_CELL  = "2";
	/** 수신거부 테이블 등록 코드(T_REJECT - C_MEDIA_CODE) - 이메일 */
	public final static String REJECT_MEDIA_CODE_EMAIL = "3";
	
}
