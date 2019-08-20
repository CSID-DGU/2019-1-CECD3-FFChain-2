package kr.ac.dgu.base.cmm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import kr.ac.dgu.base.cmm.vo.FileVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : Constants.java
 * @Description :
 * 공통적으로 사용할 Constants에 대하여 정의
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
public class Constants {

    /**************************************************************************
     * 정적 코드
     **************************************************************************/
    public static final long ORG_BASE_NO = 10000;

    public static final EgovMap CTCRMAP = new EgovMap();

    //##################################sample파일 맵 start##################################
    public static final Map<String , FileVO> SAMPLE_FILE_MAP = new HashMap<String, FileVO>();
    static{

    }
    //##################################sample파일 맵 end##################################
    //##################################고객코드 맵 start##################################
    public static final Map<String , String> MEMBER_CODE_MAP = new HashMap<String, String>();
    static{
        MEMBER_CODE_MAP.put("1", "");
        MEMBER_CODE_MAP.put("2", "");
        MEMBER_CODE_MAP.put("3", "");
        MEMBER_CODE_MAP.put("4", "");
        MEMBER_CODE_MAP.put("5", "");
        MEMBER_CODE_MAP.put("6", "");
        MEMBER_CODE_MAP.put("7", "");
        MEMBER_CODE_MAP.put("8", "");
        MEMBER_CODE_MAP.put("9", "");
        MEMBER_CODE_MAP.put("10", "");
        MEMBER_CODE_MAP.put("11", "");
        MEMBER_CODE_MAP.put("12", "");
        MEMBER_CODE_MAP.put("13", "");
        MEMBER_CODE_MAP.put("14", "");
        MEMBER_CODE_MAP.put("15", "");
        MEMBER_CODE_MAP.put("16", "");
        MEMBER_CODE_MAP.put("17", "");
        MEMBER_CODE_MAP.put("18", "");
        MEMBER_CODE_MAP.put("19", "");
        MEMBER_CODE_MAP.put("20", "");
        MEMBER_CODE_MAP.put("21", "");
        MEMBER_CODE_MAP.put("999", "");
    }

    /**
     * 알림톡 플러스 친구 아이디 : icip default
     */
    public static final String SENDERKEY = "동국대학교";

    /**
     * 실행환경의 지정
     */
    public enum Zone {
        LOCAL("local"), DEV("dev"), OP("op");

        private String code;

        Zone(String code) {
            this.setCode(code);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String toString() {
            return code;
        }

        public boolean equals(String code) {
            return this.code.equals(code);
        }
    }

    // 참가자 등록 파일 업로드 기준
    public static int SIZE_THRESHOLD = 1 * 1024 * 1024;
    // 참가자 등록 파일 업로드 사이즈 제한
    public static int UPLOAD_SIZE_MAX = 10 * 1024 * 1024;
}