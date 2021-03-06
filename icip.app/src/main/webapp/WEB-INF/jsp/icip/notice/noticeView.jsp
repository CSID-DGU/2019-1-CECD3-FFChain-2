<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<style>
#listBox div.listContent > dd > ul > li 
{
    float: left;
    margin-right: 20px;
}
#listBox div.listContent dd.info {
    padding: 0 10px;
    border-bottom: 1px dotted #cdcdcd;
    height:15px;
    line-height:15px;
}
#listBox div.listContent dt {
    padding: 5px 10px;
    border-bottom: 1px solid #cdcdcd;
    line-height: 22px;
    min-height: 25px;
    background: #e6e6e6;
    background: -webkit-gradient(linear, 0 0, 0 bottom, from(#e6e6e6), to(#f6f6f6));
    background: -webkit-linear-gradient(#e6e6e6, #f6f6f6);
    background: -moz-linear-gradient(#e6e6e6, #f6f6f6);
    background: -ms-linear-gradient(#e6e6e6, #f6f6f6);
    background: -o-linear-gradient(#e6e6e6, #f6f6f6);
    background: linear-gradient(#e6e6e6, #f6f6f6);
}
div.cont
{
padding:20px;	
}

#listBox div.listContent {
    clear: both;
    margin-bottom: 20px;
    border: 1px solid #cdcdcd;
    border-top: 1px solid #565860;
}
.boardListBasic {
    width: 100%;
    border: 1px solid #cdcdcd;
    border-top: 1px solid #565860;
}

table.boardListBasic {
    border-collapse: separate;
    border-spacing: 0;
    text-align: left;
    border-top: 1px solid #ccc;
    border-left: 1px solid #ccc;
}

table.boardListBasic th {
    font-weight: bold;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    border-top: 1px solid #fff;
    border-left: 1px solid #fff;
    background: #eee;
}
table.boardListBasic td {
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}

</style>
<body>
<div style = "padding-top: 10px; font-size:30px; padding-bottom:25px; color : #fde585; font-weight:bold " >
공지사항
</div>
<div id="listBox">
				<div class="listContent">
				<dt>
						 [안내] 수강신청/정정 정보 이클래스 반영시간 안내
				</dt>
						<dd class="info">
							<ul class="fl ml10">
							</ul>
							<ul class="fr mr10" style="float:right;">
							<li><b>작성자 :</b> <span id="suserinfo" style="" onclick="javascript:learnerInfo('admin1');">관리자</span></li>
							<li><b>작성일 :</b> 2018-02-27 11:15:03</li>
							<li class="m0" style="margin-right:0px"><b>조회수 :</b> 4425</li>
							</ul>
						</dd>
						<dd>
							<div class="cont"><p>안녕하세요. </p><p><br>수강신청/정정 기간 중 <strong><span style="color: rgb(255, 0, 0);">유드림스의 수강 내역은 </span><font color="#ff0000">이클래스에 다음날 반영</font></strong>되오니 이점 참고 부탁 드립니다.</p><p><br></p><p>감사합니다.<br></p></div>
							
						</dd>
						</dl>
							
							<div class="answerArea">
								
						</div>
					</div>
					
						<div id="listBox">
						</div><table class="boardListBasic" summary="게시판 리스트입니다.">
							<caption>게시판 리스트</caption>
							<thead>
								<tr class="odd-row">
									<th class="first">제 목</th>
									<th>작성자</th>
									<th class="last">작성일</th>
								</tr>
							</thead>
							<tbody>
								
									<tr class="odd-row">
											<td class="textLeft first">
												<div style="margin-left: 0px;" align="absmiddle" alt="">
													<a href="#;" onclick="javascript:moveBoardView('BOAD_171216110316331d7655', '0')">
													
															[공지] 2017.12.16 서버 장애 안내
													</a>
												</div>
											</td>
											<td>
												관리자
											</td>
											<td class="last">
												2017-12-16
											</td>
										</tr>
									
										<tr>
											<td class="textLeft first">
												<div style="margin-left: 0px;" align="absmiddle" alt="">
													<a href="#;" onclick="javascript:moveBoardView('BOAD_170809131412cfe20003', '0')">
													
															[공지] 신규이클래스 오픈 일정 관련
													</a>
												</div>
											</td>
											<td>
												관리자
											</td>
											<td class="last">
												2017-08-09
											</td>
										</tr>
										<tr class="odd-row">
											<td class="textLeft first">
												<div style="margin-left: 0px;" align="absmiddle" alt="">
													<a href="#;" onclick="javascript:moveBoardView('BOAD_180227111503550900e1', '0')">
													
															<b><i class="icon-arrow-small-black"></i>[안내] 수강신청/정정 정보 이클래스 반영시간 안내</b>
													</a>
												</div>
											</td>
											<td>
												관리자
											</td>
											<td class="last">
												2018-02-27
											</td>
										</tr>
									
										<tr>
											<td class="textLeft first">
												<div style="margin-left: 0px;" align="absmiddle" alt="">
													<a href="#;" onclick="javascript:moveBoardView('BOAD_170905182259ef57201b', '0')">
													
															[안내] 학습자 업로드 자료실 관련
													</a>
												</div>
											</td>
											<td>
												관리자
											</td>
											<td class="last">
												2017-09-05
											</td>
										</tr>
										
										<tr class="odd-row">
											<td class="textLeft first">
												<div style="margin-left: 0px;" align="absmiddle" alt="">
													<a href="#;" onclick="javascript:moveBoardView('BOAD_170904164429ea1c03c0', '0')">
													
															[안내] 인터넷 익스플로러 온라인강의 오류 관련
													</a>
												</div>
											</td>
											<td>
												관리자
											</td>
											<td class="last">
												2017-09-04
											</td>
										</tr>
							</tbody>
						</table>
						<ul class="btnBox">
								<li style =" padding-left : 500px">
									<a class="btn small" href="noticeList.do" style="color: white; padding: 5px 22px;">
										<i class="icon-report-small-color"></i>
										목 록
									</a>
								</li>
							</ul>
<script>
//목록
function fn_cancel(){
    
    var form = document.getElementById("viewForm");
    
    form.action = "<c:url value='noticeList.do'/>";
    form.submit();
    
}
 
//수정
function fn_update(){
    
    var form = document.getElementById("viewForm");
    
    form.action = "<c:url value='updateNotice.do'/>";
    form.submit();
}
</script>
</div>
</body>
</html>
