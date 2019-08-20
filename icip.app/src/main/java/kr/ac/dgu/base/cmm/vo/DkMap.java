package kr.ac.dgu.base.cmm.vo;

import java.sql.Clob;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.psl.dataaccess.util.CamelUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : DkMap.java
 * @Description :
 * DkMap VO
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
 *   수정일                수정자         수정내용
 * ------------------------------------------------------------------
 *  2019. 03. 06.       DGU         신규생성
 * </pre>
 */ 
public class DkMap extends EgovMap {
    private static final long serialVersionUID = -5152790603864761052L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DkMap.class);

    @Override
    public Object put(Object key, Object value) {
        Object convertValue = value;
        if (value instanceof Clob) {
            Clob clob = (Clob) value;
            
            if (clob != null) {
                int size;
                try {
                    size = (int) clob.length();
                    convertValue = clob.getSubString(1, size);
                } catch (SQLException e) {
                    LOGGER.error("DkMap Coverting Clob", e);
                }
              }
        }

        return super.put(CamelUtil.convert2CamelCase((String) key), convertValue);
    }
}
