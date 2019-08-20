package kr.ac.dgu.icip.cmm.resolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Class Name : NosSessionAttribute.java
 * @Description : NosSessionAttribute Annotation
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0

 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU		신규생성
 * </pre>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionAttribute {
    
    String value() default "";
    boolean required() default false;
}
