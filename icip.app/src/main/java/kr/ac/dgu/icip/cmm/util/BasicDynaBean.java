/*------------------------------------------------------------
 *        FileName : BasicDynaBean.java
 *        Desc     : DynaBean을 구현한 실제 DB 데이터를 저장하는 클래스
 *        Author   : The Apache Software Foundation.
 *        Date     : 2005.07.21(목)
 *        Update   : -
 *------------------------------------------------------------*/
package kr.ac.dgu.icip.cmm.util;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.dgu.base.cmm.exception.BaseException;

/**
 * <pre>
 * kr.ac.dgu.icip.cmm.util 
 *    |_ BasicDynaBean.java
 * 1. 개요 : DynaBean을 구현한 실제 DB 데이터를 저장하는 클래스
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
public class BasicDynaBean implements DynaBean, Serializable {
    /**
     * DynaClass를 저장하는 변수
     */
    protected DynaClass dynaClass = null;

    /**
     * 속성들을 저장하는 HashMap 변수
     */
    protected HashMap values = new HashMap();

    /**
     * 생성자
     *
     * @param DynaClass
     */
    public BasicDynaBean(DynaClass dynaClass) {
        super();
        this.dynaClass = dynaClass;
    }

    /**
     * DynaClass를 리턴하는 메서드
     *
     * @return DynaClass를 - 결과
     */
    public DynaClass getDynaClass() {
        return (this.dynaClass);
    }

    /**
     * 원하는 속성명과 속성키값을 포함하는지 여부를 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String key - 키값
     * @return boolean - 결과
     */
    public boolean contains(String name, String key) {
        Object value = values.get(name);

        if (value == null) {
            throw new NullPointerException("No mapped value for '" + name +
                    "(" + key + ")'");
        } else if (value instanceof Map) {
            return (((Map) value).containsKey(key));
        } else {
            throw new IllegalArgumentException("Non-mapped property for '" +
                    name + "(" + key + ")'");
        }
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return Object - 결과
     */
    public Object get(String name) {
        String fieldName = name.toUpperCase();

        if (values != null) {
            Object value = values.get(fieldName);

            return value;
        }

        return null;
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return String - 결과
     */
    public String getString(String name) {
        return getString(name, "");
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String defaultValue - 기본값
     * @return String - 결과
     */
    public String getString(String name, String defaultValue) {
        Object value = this.get(name);

        return ((value == null) ? defaultValue : value.toString());
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return int - 결과
     */
    public int getInt(String name) {
        return getInt(name, 0);
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String defaultValue - 기본값
     * @return int - 결과
     */
    public int getInt(String name, int defaultValue) {
        Object value = this.get(name);

        return (getString(name).equals("") ? defaultValue
                : ((Number) value).intValue());
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return long - 결과
     */
    public long getLong(String name) {
        return getLong(name, 0);
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String defaultValue - 기본값
     * @return long - 결과
     */
    public long getLong(String name, long defaultValue) {
        Object value = this.get(name);

        return (getString(name).equals("") ? defaultValue
                : ((Number) value).longValue());
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return double - 결과
     */
    public double getDouble(String name) {
        return getDouble(name, 0);
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String defaultValue - 기본값
     * @return double - 결과
     */
    public double getDouble(String name, double defaultValue) {
        Object value = this.get(name);

        return (getString(name).equals("") ? defaultValue
                : ((Number) value).doubleValue());
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return float - 결과
     */
    public float getFloat(String name) {
        return getFloat(name, 0);
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String defaultValue - 기본값
     * @return float - 결과
     */
    public float getFloat(String name, float defaultValue) {
        Object value = this.get(name);

        return (getString(name).equals("") ? defaultValue
                : ((Number) value).floatValue());
    }

    /**
     * 원하는 속성명과 인덱스에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param int    index - 인덱스
     * @return Object - 결과
     */
    public Object get(String name, int index) {
        Object value = values.get(name);

        if (value == null) {
            throw new NullPointerException("No indexed value for '" + name +
                    "[" + index + "]'");
        } else if (value.getClass().isArray()) {
            return (Array.get(value, index));
        } else if (value instanceof List) {
            return ((List) value).get(index);
        } else {
            throw new IllegalArgumentException("Non-indexed property for '" +
                    name + "[" + index + "]'");
        }
    }

    /**
     * 원하는 속성명과 키값에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String key - 키값
     * @return Object - 결과
     */
    public Object get(String name, String key) {
        Object value = values.get(name);

        if (value == null) {
            throw new NullPointerException("No mapped value for '" + name +
                    "(" + key + ")'");
        } else if (value instanceof Map) {
            return (((Map) value).get(key));
        } else {
            throw new IllegalArgumentException("Non-mapped property for '" +
                    name + "(" + key + ")'");
        }
    }

    /**
     * 원하는 속성명과 키값에 해당하는 데이터를 삭제하는 메서드
     *
     * @param String name - 속성명
     * @param String key - 키값
     */
    public void remove(String name, String key) {
        Object value = values.get(name);

        if (value == null) {
            throw new NullPointerException("No mapped value for '" + name +
                    "(" + key + ")'");
        } else if (value instanceof Map) {
            ((Map) value).remove(key);
        } else {
            throw new IllegalArgumentException("Non-mapped property for '" +
                    name + "(" + key + ")'");
        }
    }

    /**
     * 원하는 속성명과 객체를 설정하는 메서드
     *
     * @param String name - 속성명
     * @param Object value - 객체
     */
    public void set(String name, Object value) {
        DynaProperty descriptor = getDynaProperty(name);

        if (value == null) {
            if (descriptor.getType().isPrimitive()) {
                throw new NullPointerException("Primitive value for '" + name +
                        "'");
            }
        } else if (!isAssignable(descriptor.getType(), value.getClass())) {
            String message = "Cannot assign value of type '" +
                    value.getClass().getName() + "' to property '" + name +
                    "' of type '" + descriptor.getType().getName() + "'";
            throw new BaseException(message);
        }

        values.put(name, value);
    }

    /**
     * 원하는 속성명에 해당하는 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return String - 결과
     */
    public String getDate(String name) {
        return getDate(name, "yyyy-MM-dd");
    }

    /**
     * 원하는 속성명과 형식에 맞쳐 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String format - 형식
     * @return String - 결과
     */
    public String getDate(String name, String format) {
        return getDate(name, format, "");
    }

    /**
     * 원하는 속성명과 형식에 맞쳐 데이터값을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @param String format - 형식
     * @param String defaultValue - 기본값
     * @return String - 결과
     */
    public String getDate(String name, String format, String defaultValue) {
        Object value = this.get(name);

        if ((value != null) &&
                value.getClass().isAssignableFrom(Timestamp.class)) {
            SimpleDateFormat df = new SimpleDateFormat(format);

            return ((value == null) ? defaultValue : df.format(value));
        } else {
            return ((value == null) ? defaultValue : value.toString());
        }
    }

    /**
     * 원하는 속성명과 인덱스에 객체를 설정하는 메서드
     *
     * @param String name - 속성명
     * @param int    index - 인덱스
     * @param Object value - 객체
     */
    public void set(String name, int index, Object value) {
        Object prop = values.get(name);

        if (prop == null) {
            throw new NullPointerException("No indexed value for '" + name +
                    "[" + index + "]'");
        } else if (prop.getClass().isArray()) {
            Array.set(prop, index, value);
        } else if (prop instanceof List) {
            try {
                ((List) prop).set(index, value);
            } catch (ClassCastException e) {
                throw new BaseException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Non-indexed property for '" +
                    name + "[" + index + "]'");
        }
    }

    /**
     * 원하는 속성명과 인덱스에 객체를 설정하는 메서드
     *
     * @param String name - 속성명
     * @param int    index - 인덱스
     * @param Object value - 객체
     */
    public void set(String name, String key, Object value) {
        Object prop = values.get(name);

        if (prop == null) {
            throw new NullPointerException("No mapped value for '" + name +
                    "(" + key + ")'");
        } else if (prop instanceof Map) {
            ((Map) prop).put(key, value);
        } else {
            throw new IllegalArgumentException("Non-mapped property for '" +
                    name + "(" + key + ")'");
        }
    }

    /**
     * 원하는 속성명에 해당하는 속성을 리턴하는 메서드
     *
     * @param String name - 속성명
     * @return DynaProperty - 속성객체
     */
    protected DynaProperty getDynaProperty(String name) {
        DynaProperty descriptor = getDynaClass().getDynaProperty(name);

        if (descriptor == null) {
            throw new IllegalArgumentException("Invalid property name '" +
                    name + "'");
        }

        return (descriptor);
    }

    /**
     * 원하는 데이터 형식이 있는지를 리턴하는 메서드
     *
     * @param Class dest - 데이터형식
     * @param Class source - 데이터형식
     * @return boolean - 존재유무
     */
    protected boolean isAssignable(Class dest, Class source) {
        if (dest.isAssignableFrom(source) ||
                ((dest == Boolean.TYPE) && (source == Boolean.class)) ||
                ((dest == Byte.TYPE) && (source == Byte.class)) ||
                ((dest == Character.TYPE) && (source == Character.class)) ||
                ((dest == Double.TYPE) && (source == Double.class)) ||
                ((dest == Float.TYPE) && (source == Float.class)) ||
                ((dest == Integer.TYPE) && (source == Integer.class)) ||
                ((dest == Long.TYPE) && (source == Long.class)) ||
                ((dest == Short.TYPE) && (source == Short.class))) {
            return (true);
        } else {
            return (false);
        }
    }
}
