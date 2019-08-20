<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>비밀번호 초기화 > 2. 통신사인증</title>
	<link href="/css/normalize.css" rel="stylesheet" type="text/css" />
	<link href="/css/layout.css" rel="stylesheet" type="text/css" />
	<!--[if lt IE 9]>
		<script src="/js/html5shiv.js"></script>
		<![endif]-->
	<script type="text/javascript">
		function MM_swapImgRestore() { //v3.0
		  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}
		function MM_preloadImages() { //v3.0
		  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
		}

		function MM_findObj(n, d) { //v4.01
		  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
		  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
		  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
		  if(!x && d.getElementById) x=d.getElementById(n); return x;
		}

		function MM_swapImage() { //v3.0
		  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
		   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		}
	</script>
</head>

<body>
	<div id="wrapper" class="wrap">
		<div class="inner_wrap">
			<header>
				<div id="logowrap" class="logo_wrap">
					<div class="logo_left">
						<img src="/images/top_left_logo.gif" width="260" height="58" />
					</div>
					<div class="logo_right">
						<img src="/images/top_right_logo.gif" width="86" height="58" />
					</div>
				</div>
			</header>
					
			<div id="cont" class="content">
				<h2 class="title">장애사항</h2>
				<div class="box">
					<div class="step2-2">
						<div class="message">
							<p>요청하신 페이지를 찾을 수 없습니다.</p>
							<p>이용에 불편을 드려 죄송합니다. </p>
						</div>
					</div>
				</div>
			</div>
				
			<footer>
				<img src="/images/footer_left_logo.gif" width="338" height="28" />
			</footer>
		</div>
	</div>
    </body>
</html>
