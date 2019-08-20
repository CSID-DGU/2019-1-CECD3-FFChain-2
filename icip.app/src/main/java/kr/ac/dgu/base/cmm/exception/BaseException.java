package kr.ac.dgu.base.cmm.exception;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/**
 * @Class Name : BaseException.java
 * @Description : Base Exception
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
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 03. 06.		DGU			예외 Logging 처리
 * </pre>
 */
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseException.class);

    /** message source */
    private MessageSource messageSource;
    /** mesaage key */
    private String messageKey;
    /**message arguments */
    private String[] messageArgs;
    /** message에 arguments를 처리한 최종message */
    private String detailMessage;

    /**
     * 생성자
     */
    public BaseException() {
        super();
    }

    /**
     * @param messageSource springframework messageSource
     * @param messageKey message key
     * @param messageArgs message arguments
     */
    public BaseException(MessageSource messageSource, String messageKey, String... messageArgs) {
        this(null, messageSource, messageKey, messageArgs);
    }

    /**
     * @param messageSource springframework messageSource
     * @param messageKey message key
     */
    public BaseException(MessageSource messageSource, String messageKey) {
        this(null, messageSource, messageKey, (String[]) null);
    }

    /**
     * @param cause 예외객체
     * @param messageSource springframework messageSource
     * @param messageKey message key
     */
    public BaseException(Throwable cause, MessageSource messageSource, String messageKey) {
        this(cause, messageSource, messageKey, (String[]) null);
    }

    /**
     * @param cause 예외객체
     * @param messageSource springframework messageSource
     * @param messageKey message key
     * @param messageArgs message arguments
     */
    public BaseException(Throwable cause, MessageSource messageSource, String messageKey, String... messageArgs) {
        super(messageKey, cause);
        processMsgException(messageSource, messageKey, messageArgs);
    }

    /**
     * @param msg 예외메세지
     */
    public BaseException(String msg) {
        this((Throwable) null, msg);
    }

    /**
     * @param cause 예외객체
     */
    public BaseException(Throwable cause) {
        this(cause, cause.getMessage());
    }

    /**
     * @param cause 예외객체
     * @param msg 예외메세지
     */
    public BaseException(Throwable cause, String msg) {
        super(msg, cause);
        processException(msg);
    }
    
    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
        processException(msg);
    }

    /**
     * 단순 메세지 예외 처리
     * 
     * @param msg 예외메세지
     */
    private void processException(String msg) {
        this.detailMessage = msg;

        // 로거가 없는 경우는 패스.
        Logger logger = getLogger();
        if (logger != null) {
        	Object cause = getCause();
        	//예외의 원인이 BaseException이 아닌 경우에만 ERROR 로깅
        	if( cause != null && !(cause instanceof BaseException) ) {
        		logger.error(detailMessage, this);
        	} else if( cause == null) {
        		logger.error(detailMessage);
        	}
        }
    }

    /**
     * message source 가 지정된 경우의 예외 처리
     * 
     * @param messageSource springframework messageSource
     * @param messageKey message key
     * @param messageArgs message arguments
     */
    private void processMsgException(MessageSource messageSource, String messageKey, String... messageArgs) {
        this.messageSource = messageSource;
        this.messageKey = messageKey;
        this.messageArgs = messageArgs;

        String convertedMsg = null;
        try {
            convertedMsg = this.messageSource.getMessage(this.messageKey, this.messageArgs, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            convertedMsg = this.messageKey;
        }

        this.detailMessage = convertedMsg;

        // 로거가 없는 경우는 패스.
        Logger logger = getLogger();
        if (logger != null) {
        	Object cause = getCause();
        	//예외의 원인이 BaseException이 아닌 경우에만 ERROR 로깅
        	if( cause != null && !(cause instanceof BaseException) ) {
        		logger.error("[" + messageKey + "] " + this.detailMessage, this);
        	} else if( cause == null ) {
        		logger.error("[" + messageKey + "] " + this.detailMessage);
        	}
        }
    }

    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : getMessageKey
     * @date : 2019. 03. 14.
     * @author : DGU
     * @return
     */ 	
    public String getMessageKey() {
        return this.messageKey;
    }

    /**
     * <pre>
     * 1. 개요 : 
     * </pre>
     * @Method Name : getMessageArgs
     * @date : 2019. 03. 14.
     * @author : DGU
     * @return
     */ 	
    public String[] getMessageArgs() {
        return messageArgs.clone();
    }

    @Override
    public String getMessage() {
        return this.detailMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    /**
     * 로거를 얻어온다. BaseException 확장시 자체 로거를 사용해야하면 override 한다.
     * 
     * @return 로그를 출력할 logger. null 을 반환하면 로그를 출력하지 않는다.
     */
    protected Logger getLogger() {
        return BaseException.LOGGER;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
