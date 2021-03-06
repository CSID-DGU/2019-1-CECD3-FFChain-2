<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>

<!-- ------------------------------------------------------------------------------------------------------------ -->
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
       <title>타 이 틀</title>
<!-- ------------------------------------------------------------------------------------------------------------ -->       
       <style type = "text/css">
        #container {width:100%; height:auto; overflow:hidden;}
       .content {float:left; width:50px; margin-left : 40px; margin-top:50px;}
      .content2 {width:100%; margin-top:50px;}
      
       div { display: block; }
       div.cont_tit { width : 840px; height: 33px; top: 8px;}
      div.cont_tit .posi {width : 840px; height: 33px; top: 9px;}
       ol, ul, li { list-style:none; }
       html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, del, dfn, em, img, ins, kbd, q, samp, small, strong, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, table, tbody, tfoot, thead, tr, th, td, article, aside, footer, header, nav, section {
       margin: 0;
       padding: 0; border: 0;
       border-radius: 0;
       outline:0;
       font-size:100%;
       }
        
       .posi {position:absolute; top: 46px; right: 0;}
      .posi a, .posi strong {display:inline-block; font-size:13px; padding:0 10px 0 15px; color:#999999; font-weight:normal;}
      .posi a, .posi strong {background:url(../images/common/bul_02.png) no-repeat left center;}
      .posi span:nth-child(1) a { padding-left:15px; padding-right:10px; background:url(/images/a_home.png) no-repeat center 2px; text-indent: -99999px;}
      .posi span:nth-child(1) a img {margin-top:-5px}
      
      
       .lnb {
       float:left;
       width:200px;
       margin-top:50px;
       }
       .lnb:after {
       content:"";
       display:block;
       clear:both;
       }
       
      .lnb h2 {
      margin: 0px 0px;
      padding:58px 0 57px 23px;
      border-bottom:1px dotted #aeaeae;
      font-size:28px;
      font-weight:bold;
      color:#414141;
      background:#fde585;
      }
       
       .lnb ul li a {
       display:block;
       font-size:17px;
       padding:17px 25px;
       color:#777777;
       font-weight: bold;
       }
       
       .lnb ul li a:hover, .lnb ul li a.on {
       color:#fff;
       background:#666666 no-repeat 180px center;
       }
       
       .lnb ul li a {
       display:block;
       font-size:17px;
       padding:17px 25px;
       color:#777777;
       }
       
       .lnb .lnb_dep02 {
       padding:10px 0 20px 25px;
       }
       
       .lnb .lnb_dep02 li {
       border:0;
       background:none;
       }
       
       .lnb .lnb_dep02 li a {
       font-size:14px;
       padding:5px;
       background:none;
       }
       
       .lnb .lnb_dep02 li a.on, .lnb .lnb_dep02 li a:hover {
       color:#fde585;
       background:none;
       }
       
       .lnb02 .lnb_dep02 li a.on, .lnb02 .lnb_dep02 li a:hover {
       color:#c5a957;
       background:none;
       }
       
       .lnb h2 {
       margin: 0px 0px;
       padding:58px 0 57px 23px;
       border-bottom:1px dotted #aeaeae;
       font-size:28px;
       font-weight:bold;
       color:#414141;
       background:#fde585;
       }
       

      
       
      .lnb01 h2 {background:#7c6e68;}
      .lnb02 h2 {background:#dccb98;}
      .lnb04 h2 {background:#80c0b1;}
      .lnb05 h2 {background:#bea086;}
       
       *, *:before, *:after {
       -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
       box-sizing: border-box;
       }
       
       body {
       font-family:NanumGothic, Dotum, Gulim, Apple SD Gothic Neo, Arial, Tahoma, sans-serif;
       font-size: 12px;
       line-height: 100%;
       color: #666;
       background-color:#ffffff;
       width:100%;
       min-width:320px;
       -webkit-font-smoothing: antialiased;
       -webkit-text-size-adjust: none;
       *word-break:break-all;
       margin:0 auto;
       min-width:320px;
       }
       
       a, img {
       border:0;
       outline:none;
       selector-dummy:expression(this.hideFocus=true);
       padding:0;
       margin:0;
       }
       
       a:link {
       color: #333333;
       text-decoration: none;
       }
       
      a:visited {
      color: #333333;
      text-decoration: none;
      }
      
      a:hover, a:focus {
      color: #333333;
      text-decoration: none;
      }
      
      a:active {
      color: #333333;
      text-decoration: none;
      }
      
      hr {
      color : #f7f7f7;
      }
       </style>
       
       <!-- ------------------------------------------------------------------------------------------------------------ -->
 
    </head> 
      <!-- ------------------------------------------------------------------------------------------------------------ -->
    <body>
       <div class = "lnb lnb03" style = "margin: 0 auto; width = 300px;    padding-left: 20px;
    width: 200px;">
          <h2><img src = "/images/1_myinformation.png"></h2>
          <ul>
             <li><a href = "/icip/studentinfo/viewStudentinfo.do">학적부 조회</a></li>
             <div id = "toblock">
                <li><a href = "/icip/notice/noticeList.do">공지사항</a>
                   <ul class = "lnb_dep02" style = "display:none;">
                      <li id = "toblock2"><a href = "#none">조회</a></li>
                      <li id = "toblock2"><a href = "#none">등록</a></li>
                   </ul>
                </li>
             </div>
          </ul>
       </div>
       
       <div class = "content">
       </div>
       
      <script>
      $(document).ready(function() {
          $('#toblock').click(function() {
              var state = $('#toblock2').css('display'); // state 변수에 ID가 moreMenu인 요소의 display의 속성을 '대입'
              if(state == 'none'){ // state가 none 상태일경우 
                  $('#toblock2').show(); // ID가 moreMenu인 요소를 show();
              }else{ // 그 외에는
                  $('#toblock2').hide(); // ID가 moreMenu인 요소를 hide();         
              }
          });
      });
      </script>
    </body> 
 </html>