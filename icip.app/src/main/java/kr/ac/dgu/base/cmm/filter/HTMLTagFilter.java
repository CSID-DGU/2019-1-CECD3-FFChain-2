package kr.ac.dgu.base.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTMLTagFilter implements Filter{
    /** LOGGER */
    private static final Logger LOGGER = LoggerFactory.getLogger(HTMLTagFilter.class);
    
    @SuppressWarnings("unused")
    private FilterConfig config;    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(httpRequest);
        
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("isMultipartContent : " + isMultipartContent);
        }
        
        if (!isMultipartContent) {
            httpRequest = new HTMLTagFilterRequestWrapper(httpRequest);
        } 
        
        chain.doFilter(httpRequest, response);     
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;       
    }

    @Override
    public void destroy() {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("destroy called");
        }
    }
}
