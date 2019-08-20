<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : index.jsp
  * @Description : index 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2017.08.07.	DGU		신규생성
  *
  * author DGU
  * since 2017.08.07
  *
  * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
  */
%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META http-equiv="Expires" content="-1">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="No-Cache">
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<title>동국대학교</title>
<script type="text/javascript">
</script>
</head>
<body>
<c:out value="${SystemType}"/><br/>
<c:out value="${ServerName}"/><br/>
<c:out value="${RemoteAddr}"/><br/>

sessionSize : <c:out value="${svo.sessionSize}"/><br/>
serverIp                  = <c:out value="${svo.serverIp                  }"/><br/>
sessionKey                = <c:out value="${svo.sessionKey                }"/><br/>
remoteAddr                = <c:out value="${svo.remoteAddr                }"/><br/>
remoteHost                = <c:out value="${svo.remoteHost                }"/><br/>
serverName                = <c:out value="${svo.serverName                }"/><br/>
protocol                  = <c:out value="${svo.protocol                  }"/><br/>
serverPort                = <c:out value="${svo.serverPort                }"/><br/>
method                    = <c:out value="${svo.method                    }"/><br/>
pathInfo                  = <c:out value="${svo.pathInfo                  }"/><br/>
pathTranslated            = <c:out value="${svo.pathTranslated            }"/><br/>
servletPath               = <c:out value="${svo.servletPath               }"/><br/>
realPath                  = <c:out value="${svo.realPath                  }"/><br/>
queryString               = <c:out value="${svo.queryString               }"/><br/>
authType                  = <c:out value="${svo.authType                  }"/><br/>
remoteUser                = <c:out value="${svo.remoteUser                }"/><br/>
contentType               = <c:out value="${svo.contentType               }"/><br/>
contentLength             = <c:out value="${svo.contentLength             }"/><br/>
headerAccept              = <c:out value="${svo.headerAccept              }"/><br/>
headerUserAgent           = <c:out value="${svo.headerUserAgent           }"/><br/>
headerReferer             = <c:out value="${svo.headerReferer             }"/><br/>
sessionMaxInactiveInterval= <c:out value="${svo.sessionMaxInactiveInterval}"/><br/>
sessionCreationTime       = <c:out value="${svo.sessionCreationTime       }"/><br/>
timeUsed       = <c:out value="${svo.timeUsed       }"/><br/>

<%
   Enumeration headerNames = request.getHeaderNames();
   while(headerNames.hasMoreElements()) {
      String paramName = (String)headerNames.nextElement();
      out.print("<tr><td>" + paramName + "</td>\n");
      String paramValue = request.getHeader(paramName);
      out.println("<td> " + paramValue + "</td></tr>\n");
   }
%>

</body>
</html>