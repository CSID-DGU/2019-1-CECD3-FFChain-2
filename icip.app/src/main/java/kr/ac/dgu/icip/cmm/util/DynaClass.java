/*------------------------------------------------------------
*        FileName : DynaClass.java
*        Desc     : DB 데이터를 복제를 위해 반드시 구현해야하는 interface 클래스
*        Author   : The Apache Software Foundation.
*        Date     : 2005.07.21(목)
*        Update   : -
*------------------------------------------------------------*/
package kr.ac.dgu.icip.cmm.util;

/**
 * <pre>
 * 1. 개요 : DB 데이터를 복제를 위해 반드시 구현해야하는 interface 클래스
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
public interface DynaClass {
    /**
     * Property명을 리턴하는 메서드
     *
     * @return String
     */
    public String getName();

    /**
     * DynaProperty을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return DynaProperty
     */
    public DynaProperty getDynaProperty(String name);

    /**
     * DynaProperty배열을 리턴하는 메서드
     *
     * @return DynaProperty[]
     */
    public DynaProperty[] getDynaProperties();

    /**
     * DynaBean의 인스턴스를 리턴하는 메서드
     *
     * @return DynaBean
     */
    public DynaBean newInstance()
            throws IllegalAccessException, InstantiationException;
}
