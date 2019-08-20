package kr.ac.dgu.icip.cmm.vo;

import kr.ac.dgu.base.cmm.vo.DefaultVO;

/**
 * @Class Name : SystemInfoVO.java
 * @Description : System 정보 
 * @author 동국대학교 ICIP
 * @since 2019. 3. 7.
 * @version 1.0
 
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * <pre>
 * ------------------------------------------------------------------
 * Modification Information 
 * ------------------------------------------------------------------
 *   수정일		 		수정자			수정내용
 * ------------------------------------------------------------------
 *  2019. 3. 7.		DGU			신규생성
 * </pre>
 */
public class SystemInfoVO extends DefaultVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6181308761968415126L;
	/**
	 * 세션 사이즈
	 */
	int sessionSize = 0;
	String serverIp = "";
	String sessionKey = "";
	String remoteAddr = "";
	String remoteHost = "";
	String serverName = "";
	String protocol = "";
	String serverPort = "";
	String method = "";
	String pathInfo = "";
	String pathTranslated = "";
	String servletPath = "";
	String realPath = "";
	String queryString = "";
	String authType = "";
	String remoteUser = "";
	String contentType = "";
	String contentLength = "";
	String headerAccept = "";
	String headerUserAgent = "";
	String headerReferer = "";
	int sessionMaxInactiveInterval = 0;
	long sessionCreationTime = 0;
	long timeUsed = 0;
	/**
	 * @return the sessionSize
	 */
	public int getSessionSize() {
		return sessionSize;
	}
	/**
	 * @param sessionSize the sessionSize to set
	 */
	public void setSessionSize(int sessionSize) {
		this.sessionSize = sessionSize;
	}
	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}
	/**
	 * @param serverIp the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	/**
	 * @return the sessionKey
	 */
	public String getSessionKey() {
		return sessionKey;
	}
	/**
	 * @param sessionKey the sessionKey to set
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	/**
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}
	/**
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}
	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}
	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}
	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the pathInfo
	 */
	public String getPathInfo() {
		return pathInfo;
	}
	/**
	 * @param pathInfo the pathInfo to set
	 */
	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}
	/**
	 * @return the pathTranslated
	 */
	public String getPathTranslated() {
		return pathTranslated;
	}
	/**
	 * @param pathTranslated the pathTranslated to set
	 */
	public void setPathTranslated(String pathTranslated) {
		this.pathTranslated = pathTranslated;
	}
	/**
	 * @return the servletPath
	 */
	public String getServletPath() {
		return servletPath;
	}
	/**
	 * @param servletPath the servletPath to set
	 */
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	/**
	 * @return the realPath
	 */
	public String getRealPath() {
		return realPath;
	}
	/**
	 * @param realPath the realPath to set
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}
	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}
	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	/**
	 * @return the remoteUser
	 */
	public String getRemoteUser() {
		return remoteUser;
	}
	/**
	 * @param remoteUser the remoteUser to set
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the contentLength
	 */
	public String getContentLength() {
		return contentLength;
	}
	/**
	 * @param contentLength the contentLength to set
	 */
	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}
	/**
	 * @return the headerAccept
	 */
	public String getHeaderAccept() {
		return headerAccept;
	}
	/**
	 * @param headerAccept the headerAccept to set
	 */
	public void setHeaderAccept(String headerAccept) {
		this.headerAccept = headerAccept;
	}
	/**
	 * @return the headerUserAgent
	 */
	public String getHeaderUserAgent() {
		return headerUserAgent;
	}
	/**
	 * @param headerUserAgent the headerUserAgent to set
	 */
	public void setHeaderUserAgent(String headerUserAgent) {
		this.headerUserAgent = headerUserAgent;
	}
	/**
	 * @return the headerReferer
	 */
	public String getHeaderReferer() {
		return headerReferer;
	}
	/**
	 * @param headerReferer the headerReferer to set
	 */
	public void setHeaderReferer(String headerReferer) {
		this.headerReferer = headerReferer;
	}
	/**
	 * @return the sessionMaxInactiveInterval
	 */
	public int getSessionMaxInactiveInterval() {
		return sessionMaxInactiveInterval;
	}
	/**
	 * @param sessionMaxInactiveInterval the sessionMaxInactiveInterval to set
	 */
	public void setSessionMaxInactiveInterval(int sessionMaxInactiveInterval) {
		this.sessionMaxInactiveInterval = sessionMaxInactiveInterval;
	}
	/**
	 * @return the sessionCreationTime
	 */
	public long getSessionCreationTime() {
		return sessionCreationTime;
	}
	/**
	 * @param sessionCreationTime the sessionCreationTime to set
	 */
	public void setSessionCreationTime(long sessionCreationTime) {
		this.sessionCreationTime = sessionCreationTime;
	}
	/**
	 * @return the timeUsed
	 */
	public long getTimeUsed() {
		return timeUsed;
	}
	/**
	 * @param timeUsed the timeUsed to set
	 */
	public void setTimeUsed(long timeUsed) {
		this.timeUsed = timeUsed;
	}


}
