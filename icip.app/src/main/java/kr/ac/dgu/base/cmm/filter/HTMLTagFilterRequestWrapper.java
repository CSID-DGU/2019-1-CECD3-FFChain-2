package kr.ac.dgu.base.cmm.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import kr.ac.dgu.base.cmm.util.StringUtil;

public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {

    public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);


        return StringUtil.escapeHTMLChar(parameter, values);
    }

    public String getParameter(String parameter) {

        String value = super.getParameter(parameter);


        return StringUtil.escapeHTMLChar(parameter, value);
    }

}