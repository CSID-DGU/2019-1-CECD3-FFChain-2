package kr.ac.dgu.base.cmm.security;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfSecurityRequestMatcher.class);

    /** 허용 HTTP Mehtod */
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    /** skipList 사용여부 */
    private boolean useSkipList = false;
    /** 체크 생략 URL */
    private List<String> skipList = null;
    /** 체크 URL */
    private List<String> checkList = null;

    @Override
    public boolean matches(HttpServletRequest request) {
        String requestMethod = request.getMethod();
        String requestUrl = request.getRequestURI();
        boolean csrfProtected = true;

        if (allowedMethods.matcher(requestMethod).matches()) {
            if (useSkipList) {
                if(skipList == null || skipList.size() < 1) {
                    csrfProtected = true;
                } else {
                    csrfProtected = !isMatchRequest(skipList, request);
                }
            } else {
                csrfProtected = isMatchRequest(checkList, request);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("requestUrl:" + requestUrl + ", requestMethod: " + requestMethod + ", csrfProtected:" + csrfProtected + " [useSkipList:" + useSkipList + "]");
        }

        return csrfProtected;
    }

    /**
     * ANT 표현식을 이용하여 Request 매칭 여부를 체크한다. 
     * 
     * @param request
     * @return
     */
    private boolean isMatchRequest(List<String> list, HttpServletRequest request) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("isMatchRequest started.");
        }
        boolean matched = false;
        RequestMatcher matcher = null;
        
        for (String pattern : list) {
            matcher = new AntPathRequestMatcher(pattern);
            if (matcher.matches(request)) {
                matched = true;
                break;
            }
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("isMatchRequest ended.");
        }
        
        return matched;
    }

    public Pattern getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(Pattern allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public boolean isUseSkipList() {
        return useSkipList;
    }

    public void setUseSkipList(boolean useSkipList) {
        this.useSkipList = useSkipList;
    }

    public List<String> getSkipList() {
        return skipList;
    }

    public void setSkipList(List<String> skipList) {
        this.skipList = skipList;
    }

    public List<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<String> checkList) {
        this.checkList = checkList;
    }

}
