package kr.ac.dgu.icip.cmm.util;

/*------------------------------------------------------------
*        FileName : DynaBean.java
*        Desc     : DB 데이터를 복제하여 데이터를 담고있는 클래스들이 구현하는 interface 클래스
*        Author   : The Apache Software Foundation.
*        Date     : 2005.07.21(목)
*        Update   : -
*------------------------------------------------------------*/
/**
 * <pre>
 * 1. 개요 : DB 데이터를 복제하여 데이터를 담고있는 클래스들이 구현하는 interface 클래스
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
public interface DynaBean {
    /**
     * DB 데이터를 복제하여 데이터를 담고있는 Bean클래스에서 원하는 필드명의 키값이 존재하는지 유무를 리턴하는 메서드
     *
     * @param String 필드명
     * @param String 키값
     * @return boolean
     */
    public boolean contains(String name, String key);

    /**
     * DB 데이터를 복제하여 데이터를 담고있는 Bean클래스에서 원하는 필드명에 데이터를 Object로 리턴하는 메서드
     *
     * @param 필드명
     * @return Object
     */
    public Object get(String name);

    /**
     * DB 데이터를 복제하여 데이터를 담고있는 Bean클래스에서 원하는 필드명에 해당 인덱스에 해당하는 데이터를 Object로 리턴하는 메서드
     *
     * @param 필드명
     * @return Object
     */
    public Object get(String name, int index);

    /**
     * DB 데이터를 복제하여 데이터를 담고있는 Bean클래스에서 원하는 필드명에 해당 키값에 해당하는 데이터를 Object로 리턴하는 메서드
     *
     * @param 필드명
     * @param 인덱스
     * @return Object
     */
    public Object get(String name, String key);

    /**
     * DB 데이터를 복제하여 데이터를 담고있는 Bean클래스에서 원하는 필드명에 해당 키값에 해당하는 데이터를 Object로 리턴하는 메서드
     *
     * @param 필드명
     * @return Object
     */
    public DynaClass getDynaClass();

    /**
     * 원하는 속성명과 키값에 해당하는 데이터를 삭제하는 메서드
     *
     * @param String name         - 속성명
     * @param String key          - 키값
     */
    public void remove(String name, String key);

    /**
     * 원하는 속성명과 객체를 설정하는 메서드
     *
     * @param String name         - 속성명
     * @param Object value        - 객체
     */
    public void set(String name, Object value);

    /**
     * 원하는 속성명과 인덱스에 객체를 설정하는 메서드
     *
     * @param String name         - 속성명
     * @param int    index           - 인덱스
     * @param Object value        - 객체
     */
    public void set(String name, int index, Object value);

    /**
     * 원하는 속성명과 키값에 객체를 설정하는 메서드
     *
     * @param String name         - 속성명
     * @param int    index           - 키값
     * @param Object value        - 객체
     */
    public void set(String name, String key, Object value);

    /**
     * 원하는 속성명과 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @return String             - 결과
     */
    public String getString(String name);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String defaultValue - 기본값
     * @return String             - 결과
     */
    public String getString(String name, String defaultValue);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @return int                - 결과
     */
    public int getInt(String name);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String defaultValue - 기본값
     * @return int                - 결과
     */
    public int getInt(String name, int defaultValue);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @return long               - 결과
     */
    public long getLong(String name);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String defaultValue - 기본값
     * @return long               - 결과
     */
    public long getLong(String name, long defaultValue);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @return double              - 결과
     */
    public double getDouble(String name);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String defaultValue - 기본값
     * @return double             - 결과
     */
    public double getDouble(String name, double defaultValue);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @return float              - 결과
     */
    public float getFloat(String name);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String defaultValue - 기본값
     * @return float              - 결과
     */
    public float getFloat(String name, float defaultValue);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @return String             - 결과
     */
    public String getDate(String name);

    /**
     * 원하는 속성명에 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String defaultValue - 기본값
     * @return String             - 결과
     */
    public String getDate(String name, String defaultValue);

    /**
     * 원하는 속성명과 형식에 맞쳐 데이터값을 리턴하는 메서드
     *
     * @param String name         - 속성명
     * @param String format       - 형식
     * @param String defaultValue - 기본값
     * @return String             - 결과
     */
    public String getDate(String name, String format, String defaultValue);
}
