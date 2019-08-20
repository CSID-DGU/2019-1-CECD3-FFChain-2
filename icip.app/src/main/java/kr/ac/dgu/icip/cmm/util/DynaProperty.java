package kr.ac.dgu.icip.cmm.util;

/*------------------------------------------------------------
*        FileName : DynaProperty.java
*        Desc     : DB 데이터를 복제할 때 DynaBean 개개의 속성을 기술하는 클래스
*        Author   : The Apache Software Foundation.
*        Date     : 2005.07.21(목)
*        Update   : -
*------------------------------------------------------------*/

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 1. 개요 : DB 데이터를 복제할 때 DynaBean 개개의 속성을 기술하는 클래스
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
public class DynaProperty implements Serializable {
    /**
     * BOOLEAN 타입 속성
     */
    private static final int BOOLEAN_TYPE = 1;

    /**
     * BYTE 타입 속성
     */
    private static final int BYTE_TYPE = 2;

    /**
     * CHAR 타입 속성
     */
    private static final int CHAR_TYPE = 3;

    /**
     * DOUBLE 타입 속성
     */
    private static final int DOUBLE_TYPE = 4;

    /**
     * FLOAT 타입 속성
     */
    private static final int FLOAT_TYPE = 5;

    /**
     * INT 타입 속성
     */
    private static final int INT_TYPE = 6;

    /**
     * LONG 타입 속성
     */
    private static final int LONG_TYPE = 7;

    /**
     * SHORT 타입 속성
     */
    private static final int SHORT_TYPE = 8;

    /**
     * 속성명
     */
    protected String name = null;

    /**
     * 속성 타입 클래스 변수
     */
    protected transient Class type = null;

    /**
     * 속성 타입 클래스의 ContentType
     */
    protected transient Class contentType;

    /**
     * 생성자
     *
     * @param String name - 속성명
     */
    public DynaProperty(String name) {
        this(name, Object.class);
    }

    /**
     * 생성자
     *
     * @param String name - 속성명
     * @param Class  type - 속성타입
     */
    public DynaProperty(String name, Class type) {
        super();
        this.name = name;
        this.type = type;
    }

    /**
     * 생성자
     *
     * @param String name       - 속성명
     * @param Class  type       - 속성타입
     * @param Class  contentType - ContentType
     */
    public DynaProperty(String name, Class type, Class contentType) {
        super();
        this.name = name;
        this.type = type;
        this.contentType = contentType;
    }

    /**
     * 속성명을 리턴하는 메서드
     *
     * @return String Name
     */
    public String getName() {
        return (this.name);
    }

    /**
     * 속성 타입 클래스을 리턴하는 메서드
     *
     * @return Class Type
     */
    public Class getType() {
        return (this.type);
    }

    /**
     * 속성 타입 클래스의 ContentType을 리턴하는 메서드
     *
     * @return Class ContentType
     */
    public Class getContentType() {
        return contentType;
    }

    /**
     * 속성 타입들이 인덱스화 되어있는지 여부를 리턴하는 메서드
     *
     * @return boolean
     */
    public boolean isIndexed() {
        if (type == null) {
            return (false);
        } else if (type.isArray()) {
            return (true);
        } else if (List.class.isAssignableFrom(type)) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * 속성 타입들이 Map화 되어있는지 여부를 리턴하는 메서드
     *
     * @return boolean
     */
    public boolean isMapped() {
        if (type == null) {
            return (false);
        } else {
            return (Map.class.isAssignableFrom(type));
        }
    }

    /**
     * 현재 속성 타입 클래스의 정보를 리턴하는 메서드
     *
     * @return String
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("DynaProperty[name=");
        sb.append(this.name);
        sb.append(",type=");
        sb.append(this.type);

        if (isMapped() || isIndexed()) {
            sb.append(" <").append(this.contentType).append(">");
        }

        sb.append("]");

        return (sb.toString());
    }

    /**
     * 속성을 OutputStream으로 Write한다.
     *
     * @param ObjectOutputStream out
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        writeAnyClass(this.type, out);

        if (isMapped() || isIndexed()) {
            writeAnyClass(this.contentType, out);
        }

        out.defaultWriteObject();
    }

    /**
     * 클래스를 OutputStream으로 Write한다.
     *
     * @param Class              clazz
     * @param ObjectOutputStream out
     */
    private void writeAnyClass(Class clazz, ObjectOutputStream out)
            throws IOException {
        // safely write out any class
        int primitiveType = 0;

        if (Boolean.TYPE.equals(clazz)) {
            primitiveType = BOOLEAN_TYPE;
        } else if (Byte.TYPE.equals(clazz)) {
            primitiveType = BYTE_TYPE;
        } else if (Character.TYPE.equals(clazz)) {
            primitiveType = CHAR_TYPE;
        } else if (Double.TYPE.equals(clazz)) {
            primitiveType = DOUBLE_TYPE;
        } else if (Float.TYPE.equals(clazz)) {
            primitiveType = FLOAT_TYPE;
        } else if (Integer.TYPE.equals(clazz)) {
            primitiveType = INT_TYPE;
        } else if (Long.TYPE.equals(clazz)) {
            primitiveType = LONG_TYPE;
        } else if (Short.TYPE.equals(clazz)) {
            primitiveType = SHORT_TYPE;
        }

        if (primitiveType == 0) {
            // then it's not a primitive type
            out.writeBoolean(false);
            out.writeObject(clazz);
        } else {
            // we'll write out a constant instead
            out.writeBoolean(true);
            out.writeInt(primitiveType);
        }
    }

    /**
     * 속성을 InputStream으로 Read한다.
     *
     * @param ObjectInputStream out
     */
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        this.type = readAnyClass(in);

        if (isMapped() || isIndexed()) {
            this.contentType = readAnyClass(in);
        }

        in.defaultReadObject();
    }

    /**
     * 속성을 InputStream으로 Read한다.
     *
     * @param ObjectInputStream out
     * @return Class
     */
    private Class readAnyClass(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        // read back type class safely
        if (in.readBoolean()) {
            // it's a type constant
            switch (in.readInt()) {
                case BOOLEAN_TYPE:
                    return Boolean.TYPE;

                case BYTE_TYPE:
                    return Byte.TYPE;

                case CHAR_TYPE:
                    return Character.TYPE;

                case DOUBLE_TYPE:
                    return Double.TYPE;

                case FLOAT_TYPE:
                    return Float.TYPE;

                case INT_TYPE:
                    return Integer.TYPE;

                case LONG_TYPE:
                    return Long.TYPE;

                case SHORT_TYPE:
                    return Short.TYPE;

                default:

                    // something's gone wrong
                    throw new StreamCorruptedException("Invalid primitive type. " +
                            "Check version of beanutils used to serialize is compatible.");
            }
        } else {
            // it's another class
            return ((Class) in.readObject());
        }
    }
}
