/**
 * 팝업페이지 (POST 방식)
 * requestURL 의 파라미터값으로 Submit 까지 완료 (팝업)
 * requestUrl : http://test.com/sample/send.do?param1=1&param2=2
 */
function popupP(requestUrl, title, width, height) {
    var title = title.replace(/\s/g, "_");
    var wLeft = window.screenLeft ? window.screenLeft : window.screenX;
    var wTop = window.screenTop ? window.screenTop : window.screenY;

    var left = wLeft + (window.innerWidth / 2) - (width / 2);
    var top = wTop + (window.innerHeight / 2) - (height / 2);

    var p = requestUrl.replace(/&amp;/gi,'&').split('?');
    var action = p[0];
    var $form = $('<form/>', {target:title, action:action, method:'post'}).appendTo('body');
    if(p[1] != null && p[1] != '') {
        var params = p[1].split('&');
        for (var i in params) {
            var tmp = params[i].split('=');
            var key = tmp[0], value = tmp[1];
            $('<input/>', {type:'hidden', name:key, value:value}).appendTo($form);
        }
    }
    
    window.open("", title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no, width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
    
    $form.submit();
    $form.remove();
}



//--------------------------------------------
//날짜 계산
//--------------------------------------------
function getCalToDate(strDate, intYY, intMM, intDD)
{
    var year  = strDate.substr(0,4);
    var month = strDate.substr(5,2) - 1; // 1월=0,12월=11
    var day   = strDate.substr(8,2);
    var hour  = "00";
    var min   = "00";
    var varYY = 0;
    var varMM = 0;
    var varDD = 0;
    
    if(intYY != "")
        varYY = parseInt(intYY,10);
   
    
    if(intMM != "")
        varMM = parseInt(intMM,10);
        
    if(intDD != "")
        varDD = parseInt(intDD,10);
        
    varYY =  parseInt(year,10) + varYY;
    varMM =  parseInt(month,10) + varMM;
    varDD = parseInt(day,10)*1 + varDD*1; // 날짜 계산
   // month 
    //varMM--; // 월은 0~11 이므로 하나 빼준다
   oDate = new Date(varYY, varMM, varDD) // 계산된 날짜 객체 생성 (객체에서 자동 계산)
      year  = oDate.getFullYear() ;
    
     month = oDate.getMonth() + 1 ; // 1월=0,12월=11이므로 1 더함
     day   = oDate.getDate();
      if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
   // alert(year);
  //  alert(month);
   // alert(day);
      var temp =  year + "-" + month + "-" + day ;
    //alert(temp);
    
    return temp;
    
  
  //  var str = toTimeString(new Date(year,month,day,hour,min), intYY, intMM, intDD);
    
 //   return str;
}


/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeString(date, intYY, intMM, intDD) { //formatTime(date)

    var varYY = 0;
    var varMM = 0;
    var varDD = 0;
    
    if(intYY != "")
        varYY = parseInt(intYY);
   
    
    if(intMM != "")
        varMM = parseInt(intMM);
        
    if(intDD != "")
        varDD = parseInt(intDD);
    
    alert(varDD);
    
    var year  = date.getFullYear() +varYY  ;
    
    var month = date.getMonth() + 1 + varMM; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate() + varDD;
    var hour  = date.getHours();
    var min   = date.getMinutes();
       
    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }
     alert(year);
     alert(month);
    alert(day);
   
    var oDate = new Date(year, month, day)
   
    year  = oDate.getFullYear() ;
    month = oDate.getMonth() +1 ;
    day =  oDate.getDate();
    
    var temp =  year + "-" + month + "-" + day ;
    
    return temp;
}
  
  



//--------------------------------------------
//수량 입력 유효성을 체크한다...김광일 추가
//--------------------------------------------
function fnInputCheckDown(obj, num){
        
    var ls_value  = obj.value;
    var ls_res    = ls_value.indexOf(".");
        
    if (parseInt(num)==0){
        if (ls_res>=0){
            alert ("정수만 입력하십시요.");
            obj.value = "";
            return false;
        }
        if (ls_value.substring(0,1)=="0"){
            alert ("정수만 입력하십시요.");
            obj.value = "";
            return false;
        }
    }  
        
    if (event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40 || event.keyCode == 8 || event.keyCode == 9) return true;  
    if (( (event.keyCode != 47) && (event.keyCode > 45) && (event.keyCode <= 57)) || event.keyCode==190 || event.keyCode==110 ||
        ( (event.keyCode != 47) && (event.keyCode > 95) && (event.keyCode <= 105))){        
        if (event.keyCode==190 || event.keyCode==110){
            
            if (ls_res>-1) return false;
        }
    }
    else{ 
        return false;
    }   
    
}

//--------------------------------------------
//숫자만 체크(alert 제거) 김기범 추가
//--------------------------------------------
function fnNumCheck(obj, num){
    var ls_value  = obj.value;
    var ls_res    = ls_value.indexOf(".");
    
    if (parseInt(num)==0){
        if (ls_res>=0){
            obj.value = "";
            return false;
        }
        if (ls_value.substring(0,1)=="0"){
            obj.value = "";
            return false;
        }
    }
        
    if (event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40 || event.keyCode == 8 || event.keyCode == 9) return true;  
    if (( (event.keyCode != 47) && (event.keyCode > 45) && (event.keyCode <= 57)) || event.keyCode==190 ||
        ( (event.keyCode != 47) && (event.keyCode > 95) && (event.keyCode <= 105))){        
        if (event.keyCode==190 || event.keyCode==110){
            
            if (ls_res>-1) return false;
        }
    }
    else{ 
        return false;
    }   
    
}
//--------------------------------------------
//수량 입력 유효성을 체크한다...김광일 추가
//--------------------------------------------
function fnInputCheck(obj, num){
    
    var ls_value  = obj.value;
    var ls_res    = ls_value.indexOf(".");
    
    if (parseInt(num)==0){
        if (ls_value.indexOf(".")>=0){
            alert ("정수만 입력하십시요.");
            
            if (ls_value.length>1){
                obj.value = ls_value.substring(0,ls_value.length-1);
            }
            else{ obj.value = ""; }
            return false;
        }
        if (ls_value.substring(0,1)=="0"){
            alert ("정수만 입력하십시요.");
            obj.value = "";
            return false;
        }
    }
            
    if (event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40 || event.keyCode == 8) return true;    
    if (( (event.keyCode != 47) && (event.keyCode > 45) && (event.keyCode <= 57)) || event.keyCode==190 || event.keyCode==110 ||
        ( (event.keyCode != 47) && (event.keyCode > 95) && (event.keyCode <= 105))){
                
        if (event.keyCode==190 || event.keyCode==110){              
            if (ls_value=='.' || ls_res==0) {obj.value = '0.';}                                 
        }
    }
    else{ 
        return false;
    }
    
    var ls_result = checkDecimals(obj, num);
    return ls_result;   
}
//---------------------------------------
//소수점 입력 체크...김광일 추가
//---------------------------------------
function checkDecimals(fieldName, num){ 
    
    var decallowed = eval(num); 
    var fieldValue = fieldName.value;
    var ls_res     = fieldValue.indexOf(".");
    
    if (fieldValue!=null && fieldValue!=""){
        
        chkpoint(fieldName);    
    
        if (fieldValue.indexOf('.') == -1) fieldValue += ".";
    
        var dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
        if (dectext.length > decallowed){
            fieldName.value=fieldValue.substring(0,fieldValue.indexOf('.')) + '.' + fieldValue.substring(fieldValue.indexOf('.')+1,fieldValue.indexOf('.')+(decallowed+1));
            alert ("소수점 " + decallowed + " 자리까지만 입력할 수 있습니다");
            return false;
        }
    }
    return true;
}
//---------------------------------------
//맨앞자리 0 입력 한개만 처리...김광일 추가
//---------------------------------------
function chkpoint(key){
    keyvalue = key.value;
    comp = keyvalue.indexOf(".");   
    if (comp==0){
        tempkey = keyvalue.substring(1,keyvalue.length);
        if(tempkey.length == 0)
        {resultkey = '0.';}
        else
        {resultkey = '0.' + tempkey;}
    }
    if(comp>0){
        if((keyvalue.length)-1==keyvalue.indexOf("."))
        {
            resultkey = keyvalue;
        }else{
            resultkey = parseInt(keyvalue.substring(0,comp),10) + "." + keyvalue.substring(comp+1,keyvalue.length);
        }
    }
    if(comp<0){
        resultkey =  parseInt(keyvalue,10);
    }
    key.value = resultkey;
}
//---------------------------------------
//checkbox array 처리...김기범 추가
//---------------------------------------
function CheckboxToHidden(f,ele) {
    var ele_h;
    var val;
    
    if (typeof(ele.length) != "undefined") {
        for (var i = 0; i < ele.length; i++) {
            ele_h = document.createElement("input");
            ele_h.setAttribute("type","hidden");
            ele_h.setAttribute("name",ele[i].name);
            ele[i].checked ? val = ele[i].value : val = "";
            ele_h.setAttribute("value",val);
            f.appendChild(ele_h);
    
            ele[i].checked = false;
            ele[i].setAttribute("name",ele[i].name + "_dummy");
        }
    } else {
            ele_h = document.createElement("input");
            ele_h.setAttribute("type","hidden");
            ele_h.setAttribute("name",ele.name);
            ele.checked ? val = ele.value : val = "";
            ele_h.setAttribute("value",val);
            f.appendChild(ele_h);
    
            ele.checked = false;
            ele.setAttribute("name",ele.name + "_dummy");   
    }
}

//---------------------------------------
//Calendar 호출.. 
//---------------------------------------
var inpst;
function openCalendar(calDate) 
{
    
   
    var intWidth = 226;
    var intHeight = 255;
    var url = "/Common/calendar.HTML";
    var winPosLeft = (screen.width - intWidth) / 2;
    var winPosTop  = (screen.height - intHeight) / 2;
    
    var ls_Date = window.showModalDialog(url, "달력", "dialogTop:"+winPosTop+"; dialogLeft:"+winPosLeft+"; dialogWidth:226px; dialogHeight:255px; Raised; resizable: no; help: no; scroll: no; status: yes;");
    
    inpst = calDate;
    
    if (ls_Date != null)
    {
        setDate( ls_Date );     
    }
}

function setDate( dateString )
{
    if ( dateString.length > 0 )
    {
        eval(inpst).value = dateString.substring(0, 4) + '-' + dateString.substring(5, 7) + '-' + dateString.substring(8);
    }
    else
    {
        eval(inpst).value = dateString;
    }
}

//  숫자만 가능하게
function NumberChk()
{
    if((event.keyCode<48) || (event.keyCode > 57)) {
        event.returnValue=false;
    }
    
}


function nextFocus(where)
{
    if(event.keyCode == 13)
    //alert(where.value);
    where.focus();
     //alert(where.value);
     
    
}

function nextFocus1(where)
{
    if(event.keyCode == 9)
    //alert(where.value);
    where.focus();
     //alert(where.value);
     
    
}



function replaceDate(obj)
{   
    if(obj.value.length == 4)
    {   
        obj.value = obj.value + "-";
    }
    else if(obj.value.length == 7)
    {
        obj.value = obj.value + "-";
    }
}

function isNumberOnly()
{
    var keyCode = event.keyCode;
    if(keyCode < 48 || keyCode > 57){

        event.returnValue = false;
    }
}

// 숫자인지 점검  - 한선경 추가
function isNumber(obj) 
{
    var result = true;
    for(j = 0; result && (j < obj.value.length); j++)
    {
        if( (obj.value.substring(j, j+1) < "0") || (obj.value.substring(j, j+1) > "9"))
            result = false;
    }
    return result;
}
//숫자가 0에서 23사이의 숫자인지 점검- 한선경 추가
function isTime(obj, comment)
{
    var result = true;
    if (!isNumber(obj)) { 
        result = false;
    }else{
        if ( ( (obj.value.length ==1) || (obj.value.length ==2) ) && (0 <= obj.value) && (obj.value <=23) ) result = true;
        else result = false;
    }
    
    if (result) {
        return result; 
    }else {
        alert(comment);
        obj.focus();
        obj.select();
        return false;
    }

}
//---------------------------------------
//문자열에 있는 특정문자패턴을 다른 문자패턴으로 바꾸는 함수...  
//---------------------------------------

    function replace(targetStr, searchStr, replaceStr)
    {
        var len, i, tmpstr;

        len = targetStr.length;
        tmpstr = "";

        for ( i = 0 ; i < len ; i++ ) {
            if ( targetStr.charAt(i) != searchStr ) {
                tmpstr = tmpstr + targetStr.charAt(i);
            }
            else {
                tmpstr = tmpstr + replaceStr;
            }
        }
        return tmpstr;
    }

//---------------------------------------
//문자열에서 좌우 공백제거... 
//---------------------------------------

    function trim(str)
    {
        //return replace(str," ","");
        var strlen = str.length;
        var temp = '';
        for (var i = 0; i < strlen; i++) {
                if(str.charAt(i) != ' ')
                        break;
        }
        str = str.substring(i, strlen);

        for(i = str.length - 1; i >= 0; i--) {
                if(str.charAt(i) != ' ')
                        break;
        }
        str = str.substring(0, i+1);
        return str;
    }

//---------------------------------------
//콤마설정... 
//---------------------------------------
    
    function putComma(input) {
        var num = input;

        if (num < 0) {
            num *= -1;
            var minus = true
        }else{
            var minus = false
        }

        var dotPos = (num+"").split(".")
        var dotU = dotPos[0]
        var dotD = dotPos[1]
        var commaFlag = dotU.length%3

        if(commaFlag) {
            var out = dotU.substring(0, commaFlag)
            if (dotU.length > 3) out += ","
        }
        else var out = ""

        for (var i=commaFlag; i < dotU.length; i+=3) {
            out += dotU.substring(i, i+3)
            if( i < dotU.length-3) out += ","
        }

        if(minus) out = "-" + out
        if(dotD) return out + "." + dotD
        else return out
    }

//---------------------------------------
//월의 끝 일자 얻기... 
//---------------------------------------

    function getEndDate(datestr){

        //널인지?
        if(isEmpty(datestr)){
            return null;
        }

        //숫자인지?
        if(!isNum(datestr)){
            return null;
        }

        //길이가 8자리?
        if(datestr.length != 6){
            return null;
        }

        var yy = Number(datestr.substring(0,4));
        var mm = Number(datestr.substring(4,6));

        //윤년 검증
        var boundDay = "";

        if(mm != 2){
            var mon=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
            boundDay = mon[mm-1];
        }
        else{
            if (yy%4 == 0 && yy%100 != 0 || yy%400 == 0){
                boundDay = 29;
            }
            else{
                boundDay = 28;
            }
        }

        return boundDay;
    }
    
//---------------------------------------
//Left 빈자리 만큼 padStr 을 붙인다.... 
//---------------------------------------

    function lpad(src, len, padStr){
        var retStr = "";
        var padCnt = Number(len) - String(src).length;
        for(var i=0;i<padCnt;i++) retStr += String(padStr);
        return retStr+src;
    }

    // Right 빈자리 만큼 padStr 을 붙인다.
    function rpad(src, len, padStr){
        var retStr = "";
        var padCnt = Number(len) - String(src).length;
        for(var i=0;i<padCnt;i++) retStr += String(padStr);
        return src+retStr;
    }

    
//---------------------------------------
//대문자변환..
//---------------------------------------
    function toUpperCase(str){

        if(isEmpty(str)) return str;
        return str.toUpperCase();
    }

//---------------------------------------
//숫자검증...
//---------------------------------------

    function isNum(str){

        if(isEmpty(str)) return false;

        for(var idx=0;idx < str.length;idx++){
            if(str.charAt(idx) < '0' || str.charAt(idx) > '9'){
                return false;
            }
        }
        return true;
    }

//---------------------------------------
//영문자검증... 
//---------------------------------------

    function isAlpha(str){

        if(isEmpty(str)) return false;

        for(var idx=0;idx < str.length;idx++){
            if(!((str.charAt(idx) >='a' && str <= 'z') || (str.charAt(idx) >= 'A' && str <= 'Z'))){
                return false;
            }
        }
        return true;
    }

//---------------------------------------
//한글검증... 
//---------------------------------------

    function isHangul(str){

        if(isEmpty(str)) return false;

        for(var idx=0;idx < str.length;idx++){
          var c = escape(str.charAt(idx));
          if ( c.indexOf("%u") == -1 ) {
                return false;
            }
        }

        return true;
    }

//---------------------------------------
//실제길이 반환( 한글 2byte 계산 )...
//---------------------------------------

    function getByteLength(s){

       var len = 0;
       if ( s == null ) return 0;
       for(var i=0;i<s.length;i++){
          var c = escape(s.charAt(i));
          if ( c.length == 1 ) len ++;
          else if ( c.indexOf("%u") != -1 ) len += 2;
          else if ( c.indexOf("%") != -1 ) len += c.length/3;
       }
       return len;
    }

//---------------------------------------
//빈값인지 리턴한다.... 
//---------------------------------------

    function isEmpty(pValue){

        if( (pValue == "") || (pValue == null) ){
            return true;
        }
        return false;
    }



//---------------------------------------
//검색날짜 유효기간....
//---------------------------------------

    function getBoundDate1(yy,mm,dd,stdDate)
    {
        var today = new Date();
        today.setYear(stdDate.substring(0,4));
        today.setMonth(stdDate.substring(4,6)-1);
        today.setDate(stdDate.substring(6,8));
        today.setHours(today.getHours());
        today.setMinutes(today.getMinutes());
        today.setSeconds(today.getSeconds());

        yy = Number(yy);
        mm = Number(mm);
        dd = Number(dd);

        var date = new Date();

        var DAY = 24 * 60 * 60 * 1000;

        if ( yy != 0 ){
            date.setTime(today.getTime() + DAY * 365 * yy);
        }

        if ( mm != 0 ){
            date.setTime(today.getTime() + DAY * 30 * mm);
        }

        if ( dd != 0 ){
            date.setTime(today.getTime() + DAY * dd);
        }

        return lpad(new String(date.getYear()),4,'0') + lpad(new String(date.getMonth() + 1),2,'0') + lpad(new String(date.getDate()),2,'0');
    }



    function getBoundDate(yy, mm, dd) {
        yy = Number(yy);
        mm = Number(mm);
        dd = Number(dd);

        var date = new Date();

        var DAY = 24 * 60 * 60 * 1000;


        if ( yy != 0 ){
            date.setTime(datToday.getTime() + DAY * 365 * yy);
        }

        if ( mm != 0 ){
            date.setTime(datToday.getTime() + DAY * 30 * mm);
        }

        if ( dd != 0 ){
            date.setTime(datToday.getTime() + DAY * dd);
        }

        return lpad(new String(date.getYear()),4,'0') + lpad(new String(date.getMonth() + 1),2,'0') + lpad(new String(date.getDate()),2,'0');
    }


//---------------------------------------
//검색날짜 체크....
//---------------------------------------

    function isVaildTerm(obj,yy,mm,dd)
    {
        var datestr = obj.value;


        //널인지?
        if(isEmpty(datestr)){
            return null;
        }

        // 날짜 포맷제거
        obj_removeformat(obj);

        //8자리인지?
        if (getByteLength(datestr) != 8) {
            alert("날짜는 '-'를 제외한 8자리 숫자로 입력하십시오.");
            return false;

        }



        // yy,mm,dd,fromto가 없을 경우
        if (yy == null) yy = 0;
        if (mm == null) mm = 0;
        if (dd == null) dd = 0;

        // 검색날짜 유효기간 가져오기
        var boundDate = getBoundDate(yy,mm,dd);

        if (yy < 0  || mm < 0  || dd < 0) {
            if ( boundDate > datestr) {
                alert("유효하지 않은 검색날짜입니다.\n유효한 날짜는" + boundDate.substring(0,4) + "년 " + boundDate.substring(4,6) + "월 " + boundDate.substring(6) + "일부터 입니다.");
                obj.select();
                return false;
            }
        } else {
            if ( boundDate < datestr) {
                alert("유효하지 않은 검색날짜입니다.\n유효한 날짜는" + boundDate.substring(0,4) + "년 " + boundDate.substring(4,6) + "월 " + boundDate.substring(6) + "일까지 입니다.");
                obj.select();
                return false;
            }
        }


        return true;

    }

//---------------------------------------
//오늘날짜 체크.... 
//---------------------------------------

    function getToDay()
    {

        /*
        var date = datToday;
           
        var year  = date.getFullYear();
        var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
        var day   = date.getDate();

        if (("" + month).length == 1) { month = "0" + month; }
        if (("" + day).length   == 1) { day   = "0" + day;   }

        return ("" + year + month + day)
*/      
        
        var _date = new Date();
        var _year = _date.getYear();
        var _month = "" + (_date.getMonth()+1);
        var _day = "" + _date.getDate();
        
        if(_month.length == 1)  _month = "0" + _month;
        if(_day.length == 1)    _day = "0" + _day;
        
        var tmp = "" + _year + _month + _day;
        return tmp;
        
    }

//---------------------------------------
//콥보 체크.... 
//---------------------------------------

    function selectComboBox(targt, optValue)
    {
        last = targt.length;
        for(var i=0; i<last; i++){
            if(targt.options[i].value == optValue){
                targt.selectedIndex = i;
                targt.options[i].selected;
            }
        }
    }

//---------------------------------------
//콤보에 값이 있는지 체크.... 
//---------------------------------------

    function isExistsComboBoxValue(targt, optValue)
    {
        last = targt.length;
        for(var i=0; i<last; i++){
            if(targt.options[i].value == optValue){
                return true;
            }
        }
        return false;
    }
    
//---------------------------------------
//숫자입력란에 콤마 추가.... 
//---------------------------------------

function CommaIn(input) {
    eval(input).value = putComma(eval(input).value);
}

//---------------------------------------
//숫자입력란에 콤마 제거.... 
//---------------------------------------

function CommaOut(input) {
    eval(input).value = eval(input).value.replace(/,/gi,"");
}

//---------------------------------------
//문자열에 있는 특정문자패턴을 다른 문자패턴으로 바꾸는 함수..... 
//---------------------------------------

function replace(targetStr, searchStr, replaceStr)
{
    var len, i, tmpstr;

    len = targetStr.length;
    tmpstr = "";

    for ( i = 0 ; i < len ; i++ ) {
        if ( targetStr.charAt(i) != searchStr ) {
            tmpstr = tmpstr + targetStr.charAt(i);
        }
        else {
            tmpstr = tmpstr + replaceStr;
        }
    }
    return tmpstr;
}

//---------------------------------------
//필수입력 체크 처리함수.....  사용예 : if ( essStr( frm.SiteCode , "사이트코드를 입력하십시오.!"    ))  return;
//---------------------------------------

function essStr(obj, msg)
{
    var str = obj.value;

    if ( str == "" )
    {
        alert(msg);
        obj.focus();
        return true;
    }
    
    return false;
}

//---------------------------------------
//날짜 타입 맞는지 체크 - 한선경 추가
//---------------------------------------
function isValue(obj)
{
    if( obj == null || obj.value == '' || obj.value.length <= 0 ) {
        return false;
    }
    return true;
}//isValue()

//년월 입력시 마지막 일자
function  getEndOfMonthDay( yy, mm )
{
    var max_days=0;

    if (mm == 1)        max_days = 31 ;
    else if (mm == 2) {

        if ((( yy % 4 == 0) && (yy % 100 != 0)) || (yy % 400 == 0))
                        max_days = 29;
        else
                        max_days = 28;
    }
    else if (mm == 3)   max_days = 31;
    else if (mm == 4)   max_days = 30;
    else if (mm == 5)   max_days = 31;
    else if (mm == 6)   max_days = 30;
    else if (mm == 7)   max_days = 31;
    else if (mm == 8)   max_days = 31;
    else if (mm == 9)   max_days = 30;
    else if (mm == 10)  max_days = 31;
    else if (mm == 11)  max_days = 30;
    else if (mm == 12)  max_days = 31;
    else {

        return '';
    }
        return max_days;
}

function isValidDate(obj, maxLength)
{

    if( obj.value.length != maxLength ) return false;

    var inputDate = obj.value;

    var yyyy = inputDate.substring(0, 4);
    var mm   = (maxLength == 10)?inputDate.substring(5,7):inputDate.substring(4,6);
    var dd   = (maxLength == 8)?inputDate.substring(6, 8):inputDate.substring(8,10);
    
    if (isNaN(yyyy) || parseInt(yyyy) < 1000) return false;
    if (isNaN(mm) || parseFloat(mm) > 12 || parseFloat(mm) < 1) return false;
    if (isNaN(dd) || parseFloat(dd) < 1 || (parseFloat(dd) > getEndOfMonthDay(parseFloat(yyyy.substring(2,4)), parseFloat(mm))) ) return false;

    
    return true;
}

// 날짜 유효성 검사
function isValidDateType(dateObj, date_length)
{
    if ( ! isValue(dateObj) )   return false;
    if ( ! isValidDate(dateObj, date_length) ) return false;
   
    return true;
}



//---------------------------------------
//Email 형식 체크 - 한선경 추가
//---------------------------------------
function isValidEmailType(obj)
{
   var data = obj.value;
    if (data.length != 0 ) {
        var sign = data.indexOf("@");
        var dot = data.indexOf(".");
        if( sign < 1 || dot < 2 || sign >= dot ) return false;
        return true;
    }
    return false;
}//isValidEmail()

// 처음 끝 공백제거 
function trim(str){
   return replace(str," ","");
}

//---------------------------------------
//핸드폰번호 형식 체크 - 한선경 추가
//---------------------------------------
function isValidCellType(obj,msg)
{
   var data = obj.value;
    if (data.length != 0 ) {
        var sign = data.substring(0,data.indexOf("-"));
        if(data.length <12 || sign.length > 3 || sign.length < 3){
             alert(msg);
             obj.value="";
             obj.focus();            
             return true;
        }else{              
        return false;
      }
    }else{
    return false;
  }
}//isValidEmail()

//---------------------------------------
//주민번호 체크 - 
//---------------------------------------
function check_juminno(memberNo1, memberNo2) {
    var jumin1 = memberNo1.value;
    var jumin2 = memberNo2.value;
    
    if(jumin1=="" || jumin1==null || jumin1.length!=6) {
        alert("주민등록번호 앞자리를 입력하세요!");
        memberNo1.focus();
        return false;
    }
    else if(jumin2=="" || jumin2==null || jumin2.length!=7) {
        alert("주민등록번호 뒷자리를 입력하세요!");
        memberNo2.focus();
        return false;
    }

    var yy     = jumin1.substr(0,2); // 년도
    var mm     = jumin1.substr(2,2); // 월
    var dd     = jumin1.substr(4,2); // 일
    var genda  = jumin2.substr(0,1); // 성별
    var msg, ss, cc;

    // 숫자가 아닌 것을 입력한 경우
    if (!isNumeric(jumin1)) {
        alert("주민등록번호 앞자리를 숫자로 입력하세요!");
        memberNo1.focus();
        return false;
    }
    // 길이가 6이 아닌 경우
    if (jumin1.length != 6) {
        alert("주민등록번호 앞자리를 다시 입력하세요!");
        memberNo1.focus();
        return false;
    }
    // 첫번째 자료에서 연월일(YYMMDD) 형식 중 기본 구성 검사
    if (yy < "00" || yy > "99" ||
        mm < "01" || mm > "12" ||
        dd < "01" || dd > "31") {
        alert("주민등록번호 앞자리를 다시 입력하세요!");
        memberNo1.focus();
        return false;
    }
    // 숫자가 아닌 것을 입력한 경우
    if (!isNumeric(jumin2)) {
        alert("주민등록번호 뒷자리를 숫자로 입력하세요!");
        memberNo2.focus();
        return false;
    }
    // 길이가 7이 아닌 경우
    if (jumin2.length != 7) {
        alert("주민등록번호 뒷자리를 다시 입력하세요!");
        memberNo2.focus();
        return false;
    }
    // 성별부분이 1 ~ 4 가 아닌 경우
    if (genda < "1" || genda > "4") {
        alert("주민등록번호 뒷자리를 다시 입력하세요!");
        memberNo2.focus();
        return false;
    }
    // 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
    cc = (genda == "1" || genda == "2") ? "19" : "20";
    // 첫번째 자료에서 연월일(YYMMDD) 형식 중 날짜 형식 검사
    if (isYYYYMMDD(parseInt(cc+yy), parseInt(mm), parseInt(dd)) == false) {
        alert("주민등록번호 앞자리를 다시 입력하세요!");
        memberNo1.focus();
        return false;
    }
    // Check Digit 검사
    if (!isSSN(jumin1, jumin2)) {
        alert("유효하지 않은 주민등록번호를 입력하셨습니다.\n다시 한 번 정확한 주민등록번호를 입력하시기 바랍니다.");
        memberNo1.focus();
        return false;
    }
    return true;
}


//------------------------------------------
//주민번호 체크 하나의 입력박스... 신주희추가
//------------------------------------------
function chk_juminno(member) {
    
    var temp = member.value;
    var jumin1;
    var jumin2;
        if(temp.length == 13)
        {
            jumin1 = temp.substr(0,6);
            jumin2 = temp.substr(6,7);
            
        }
        else if(temp.length == 14)
        {
            
            jumin1 = temp.substr(0,6);
            jumin2 = temp.substr(7,7);
            
        }
        else
        {
            alert("주민번호를 전부 입력하세요");
            return false;
        }
        if(jumin1=="" || jumin1==null || jumin1.length!=6) {
            alert("주민등록번호 앞자리를 입력하세요!");
            return false;
        }
        else if(jumin2=="" || jumin2==null || jumin2.length!=7) {
            alert("주민등록번호 뒷자리를 입력하세요!");
            return false;
        }
        
        var yy     = jumin1.substr(0,2); // 년도
        var mm     = jumin1.substr(2,2); // 월
        var dd     = jumin1.substr(4,2); // 일
        var genda  = jumin2.substr(0,1); // 성별
        var msg, ss, cc;
    
        // 숫자가 아닌 것을 입력한 경우
        if (!isNumeric(jumin1)) {
            alert("주민등록번호 앞자리를 숫자로 입력하세요!");
            return false;
        }
        // 길이가 6이 아닌 경우
        if (jumin1.length != 6) {
            alert("주민등록번호 앞자리를 다시 입력하세요!");
            return false;
        }
        // 첫번째 자료에서 연월일(YYMMDD) 형식 중 기본 구성 검사
        if (yy < "00" || yy > "99" ||
            mm < "01" || mm > "12" ||
            dd < "01" || dd > "31") {
            alert("주민등록번호 앞자리를 다시 입력하세요!");
            return false;
        }
        // 숫자가 아닌 것을 입력한 경우
        if (!isNumeric(jumin2)) {
            alert("주민등록번호 뒷자리를 숫자로 입력하세요!");
            return false;
        }
        // 길이가 7이 아닌 경우
        if (jumin2.length != 7) {
            alert("주민등록번호 뒷자리를 다시 입력하세요!");
            return false;
        }
        // 성별부분이 1 ~ 4 가 아닌 경우
        if (genda < "1" || genda > "4") {
            alert("주민등록번호 뒷자리를 다시 입력하세요!");
            return false;
        }
        // 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
        cc = (genda == "1" || genda == "2") ? "19" : "20";
        // 첫번째 자료에서 연월일(YYMMDD) 형식 중 날짜 형식 검사
        if (isYYYYMMDD(parseInt(cc+yy), parseInt(mm), parseInt(dd)) == false) {
            alert("주민등록번호 앞자리를 다시 입력하세요!");
            return false;
        }
        // Check Digit 검사
        if (!isSSN(jumin1, jumin2)) {
            alert("유효하지 않은 주민등록번호를 입력하셨습니다.\n다시 한 번 정확한 주민등록번호를 입력하시기 바랍니다.");
            return false;
        }
        return true;
    
}


function isNumeric(s) {
    for (i=0; i<s.length; i++) {
        c = s.substr(i, 1);
        if (c < "0" || c > "9") return false;
    }
    return true;
}

function isYYYYMMDD(y, m, d) {
    switch (m) {
    case 2: // 2월의 경우
        if (d > 29) return false;
        if (d == 29) {
            // 2월 29의 경우 당해가 윤년인지를 확인
            if ((y % 4 != 0) || (y % 100 == 0) && (y % 400 != 0))
                return false;
        }
        break;

    case 4: // 작은 달의 경우
    case 6:
    case 9:
    case 11:
        if (d == 31) return false;
    }

    // 큰 달의 경우
    return true;
}

function isSSN(s1, s2) {
    n = 2;
    sum = 0;

    for (i=0; i<s1.length; i++)
        sum += parseInt(s1.substr(i, 1)) * n++;
    for (i=0; i<s2.length-1; i++) {
        sum += parseInt(s2.substr(i, 1)) * n++;
        if (n == 10) n = 2;
    }

    c = 11 - sum % 11;
    if (c == 11) c = 1;
    if (c == 10) c = 0;
    if (c != parseInt(s2.substr(6, 1))) return false;
    else return true;
}

//---------------------------------------
//사업자등록번호 체크 - 
//---------------------------------------

function check_busino(vencod1,vencod2,vencod3) {
    var sum = 0;
    var vencodvalue = vencod1.value + vencod2.value + vencod3.value;
    var getlist =new Array(10);
    var chkvalue =new Array("1","3","7","1","3","7","1","3","5");
    for(var i=0; i<10; i++) { getlist[i] = vencodvalue.substring(i, i+1); }
    for(var i=0; i<9; i++) { sum += getlist[i]*chkvalue[i]; }
    sum = sum + parseInt((getlist[8]*5)/10);
    sidliy = sum % 10;
    sidchk = 0;
    if(sidliy != 0) { sidchk = 10 - sidliy; }
    else { sidchk = 0; }
    if(sidchk != getlist[9]) {
        alert("사업자등록번호를 검토한 후, 다시 입력하세요!");
        vencod1.value = '';
        vencod2.value = '';
        vencod3.value = '';
        vencod1.focus();
        return false; 
    }
    return true;
}
function check_busino_test(vencod1,vencod2,vencod3) {
    var sum = 0;
    var vencodvalue = vencod1.value + vencod2.value + vencod3.value;
    var getlist =new Array(10);
    var chkvalue =new Array("1","3","7","1","3","7","1","3","5");
 
    alert( "vencodvalue=="+ vencodvalue); 
    for(var i=0; i<10; i++) { 
        getlist[i] = vencodvalue.substring(i, i+1); 
        alert( "getlist=="+ getlist[i] ); 
    }
    
    for(var i=0; i<10; i++) { 
        sum += getlist[i]*chkvalue[i];
         alert( "sum=="+sum ); 
    }
    
    sum = sum + parseInt((getlist[8]*5)/10);
    sidliy = sum % 10;
    sidchk = 0;
    
    alert(sidliy +  ", " +sidchk ); 
    if(sidliy != 0) { sidchk = 10 - sidliy; }
    else { sidchk = 0; }
    
    if(sidchk != getlist[9]) {
        alert("사업자등록번호를 검토한 후, 다시 입력하세요!");
        vencod1.value = '';
        vencod2.value = '';
        vencod3.value = '';
        vencod1.focus();
        return false; 
    }
    return true;
}
//---------------------------------------
//사업자등록번호 체크 - 
//---------------------------------------

function check_busino_re(vencod1,vencod2,vencod3) {
    var sum = 0;
    var vencodvalue = vencod1.value + vencod2.value + vencod3.value;
    var getlist =new Array(10);
    var chkvalue =new Array("1","3","7","1","3","7","1","3","5");
    for(var i=0; i<10; i++) { getlist[i] = vencodvalue.substring(i, i+1); }
    for(var i=0; i<9; i++) { sum += getlist[i]*chkvalue[i]; }
    sum = sum + parseInt((getlist[8]*5)/10);
    sidliy = sum % 10;
    sidchk = 0;
    if(sidliy != 0) { sidchk = 10 - sidliy; }
    else { sidchk = 0; }
    if(sidchk != getlist[9]) {
        alert("사업자등록번호를 검토한 후, 다시 입력하세요!");
        vencod1.focus();
        return false; 
    }
    return true;
}
/*------------------------------------------*/
// '.' 와 숫자만 허용 (Float num) 심우상 추가
/*------------------------------------------*/
function validFloatCheck(object, num){      
    var value = object.value;
    //var sminus = value.indexOf("-");
    var spoint = value.indexOf(".");
    var lpoint = value.length;
    
    if(object.name != "HAEAN_DOSEO")
    {
        if ( ( (event.keyCode != 47) && (event.keyCode > 45) && (event.keyCode <= 57)) || event.keyCode == 8 ){
            lpoint -= 1;
            if( spoint >= 0){
                spoint += 1;
                value = value.substring(spoint);
                
                //"."의 두번입력 막음...
                if(value.length > num || (spoint >= 0 && event.keyCode == 46 ))
                    return false;
                else
                    return true;
                            
            }else{
                return true;
            }
        }else return false;
    }
    return true;        
}
/*------------------------------------------*/
// 콤마 제거 심우상 추가
/*------------------------------------------*/

function removeComma( str )
{
    if( str == "" ) return "";

    var num = str;

    var i = 0;
    var pos_com = 0;
    var rtn_num = "";

    while( i < num.length ) {
        pos_com = num.indexOf(',',i);

        if( pos_com == -1 ) {
            rtn_num += num.substring(i, num.length );
            break;
        }else {
            rtn_num += num.substring(i, pos_com );
            i = pos_com+1;
        }
    }
    return rtn_num;
}

/*------------------------------------------*/
// 콤마 삽입  심우상 추가
/*------------------------------------------*/
function setComma( str )
{
    
    num = removeComma(str);
    len = 0;

    if( num == "" ) return "";

    /* - 가 있으면 잘라냈다  반환시 붙여 준다 */
    minus = num.indexOf( '-' );
    /* - 의 위치를 못 찾았으면 */
    if( minus == -1 )
        num = num;
    else
        num = num.substring(minus+1, num.length );

    /* 소숫점의 위치를 찾는다. */
    point = num.indexOf( '.' );

    /* 소숫점의 위지를 못찾으면 계산 길이는 값의 길이가 되고 */
    if( point == -1 )
        len = num.length;
    /* 소숫점의 위치를 찾으면 계산 길이는 소수점 앞자리 까지가 된다 */
    else
        len = point;

    /* 값에서 계산할 부분만 잘래내고 */
    rtn_num = num.substring(0, len );

    /* 뒤에서부터 3자리씩 잘라서 저장할 배열을 만든다 */
    array_num = new Array( ((len - len % 3) / 3) + 1 );
    idx = 0;

    /* 뒤에서부터 3자리씩 잘라서 배열에 저장하고 */
    for( i = len ; i > 0 ; i -= 3 )
    {
        array_num[idx] = rtn_num.substring(i-3, i );
        idx++;
    }
    rtn_num = "";

    /* 배열의 뒷부분부터 , 와 함께 붙여 나간다 */
    for( i = idx-1; i >= 0 ;i-- )
    {
        if( i < (idx-1) ) rtn_num += ","; /* 맨 앞에 , 가 오지 않도록 한다 */
        rtn_num += array_num[i];
    }

    /* 소숫점이하 값이 있으면 마지막에 붙여 준다 */
    if( point > -1 ) rtn_num += num.substring( point, num.length );
    /* - 가 있으면 앞에 붙여준다 */
    if( minus > -1 ) rtn_num = '-' + rtn_num;
    /* , 를 삽입한 문자열을 return */
    return rtn_num;
}
/*------------------------------------------*/
// 숫자 입력시 마다 콤마 입력  심우상 추가
/*------------------------------------------*/
 function  keyInComma(Obj){
    var str = Obj.value
    str =  removeComma(str)
    var retVal
      if(isNumeric(str)){   
    str =  trim(str)
    Obj.value = setComma(str)
      }else{
        var errMsg ="숫자를 입력을 하세요!   "
        viewErrMsg(Obj,errMsg)
      }
    
 }  
/*------------------------------------------*/
// 에러 메세지 출력후 포커싱
/*------------------------------------------*/
function viewErrMsg(obj, errMsg)
{
    alert(errMsg);

    if( obj != null) {
        try {
            obj.focus();
            if(obj.tagName.toUpperCase() == 'INPUT' && obj.type.toUpperCase() == 'TEXT') obj.select();
        }catch(exception) {
        }
    }
     return false;
}

/*------------------------------------------*/
// 주소조회용
/*------------------------------------------*/
function funcFRAddress(obj1, obj2, obj3) {
    var url = "/Common/TMSFRAddressPop.jsp?FROMNAME="+ obj1 +"&ZIPCODE="+ obj2+"&ADDRESS1="+ obj3;
    window.open(url,"AddressAction", "width=511,height=425,scrollbars=no,top=100,left=500,directories=no,location=no,resizable=yes,menubar=no");
}

//---------------------------------------
//날짜의 이후일자 체크 - 한선경 추가
//---------------------------------------
function check_date_cmp(startDate1, endDate1, comment,comment2)
{
    var startDate = replace(startDate1, "-", "");
    startDate = replace(startDate, "/", "");

    var endDate = replace(endDate1, "-", "");
    endDate = replace(endDate, "/", "");


    if  (startDate > endDate)
    {
        alert(comment + "는 "+comment2+"보다 이후날짜여야 합니다.\n\n" );
        //alert("Check "+comment + "\'s value and   "+comment2+"\'s value.\n\n" );
        return false;
    }
    else
    {
        return true;
    }
}
//--------------------------------------------------
//  소수이하 반올림  funcRound('123.5678',2) -- 심우상 추가
//--------------------------------------------------

function funcRound(data,pntLen)
{
   if (isNaN(data) || isNaN(pntLen) )
   {
      alert('Input value '+data+'or decimal below'+pntLen+'is not numeral.');
      return ;
   }
   data   = parseFloat(data);
   pntLen = parseInt(pntLen,10)

   var round = Math.pow(10,pntLen);
   var newDoub = Math.floor(data * round +.5)/round;

   return newDoub;
}

//--------------------------------------------------
//  포탈 파일 다운로드 (게시판, 공지사항, 고객응대
//--------------------------------------------------
function cr_file_open( filename) {
    window.open("http://img.ktmall.com/uploadfile/mro/cr/"  + filename, "new_file", "width=920  height=530 scrollbars=yes resizable=yes menubar=yes ");
}


//--------------------------------------------------
//  신규상품, 견적 파일 다운로드
//--------------------------------------------------
function ps_file_open( filename) {
    window.open("http://img.ktmall.com/uploadfile/mro/ps/"  + filename, "new_file", "width=920  height=530 scrollbars=yes resizable=yes menubar=yes ");
 }
 
 

//--------------------------------------------------
//  역경매파일다운로드
//--------------------------------------------------
function file_open(type, filename) {
        window.open("http://img.ktmall.com/images/file/b2b/auction/multimedia/file/" + type + "/"  + filename, "new_file", "width=920  height=530 scrollbars=yes resizable=yes menubar=yes ");
 }
 
//--------------------------------------------------
//  입력값이 숫자인지 체크
//--------------------------------------------------
 function validate(field) {
 
    var valid = "0123456789"
    var ok = "yes";
    var temp;
    
    for (var i=0; i<field.value.length; i++) {
        temp = "" + field.value.substring(i, i+1);
        
        if (valid.indexOf(temp) == "-1") ok = "no";
    }
    if (ok == "no") {
        alert("숫자만 입력할 수 있습니다");
        field.focus();
        field.select();
    }
}

//--------------------------------------------------
//  입력값이 숫자인지 체크(by hcs)
//--------------------------------------------------

    function formatNumber1(num){
    
    if(isNaN(num)) { 
        alert("문자는 사용할 수 없습니다.");
        return "";
    }
    return num;
    }

    function formatNumber2(num){
        fl=""
        if(isNaN(num)) { alert("문자는 사용할 수 없습니다.");return ""}
        if(num==0) return num
        
        if(num<0){ 
                num=num*(-1)
                fl="-"
        }else{
                num=num*1 //처음 입력값이 0부터 시작할때 이것을 제거한다.
        }
        num = new String(num)
        temp=""
        co=3
        num_len=num.length
        while (num_len>0){
                num_len=num_len-co
                if(num_len<0){co=num_len+co;num_len=0}
                temp=","+num.substr(num_len,co)+temp
        }
        return fl+temp.substr(1)
    }

//--------------------------------------------------
//  입력값에 콤마 추가( by hcs)
//--------------------------------------------------

    function formatNumber3(num){
        num=new String(num)
        num=num.replace(/,/gi,"")
        return formatNumber2(num)
    }
    
//--------------------------------------------------
//  입력값에 콤마제거(by hcs)
//--------------------------------------------------    
function formatNumber4(str) {
    var str0 =""
    str0 = str.replace(/,/gi,"")
    return str0
}   

//--------------------------------------------------------------------------
//  날짜 유효성 검사(by hcs)   ex) 접수일자 날짜 유효성 검사
//                              if(!checkValidDate(frm.S_START_RECE_DATE))
//                                  return;
//--------------------------------------------------------------------------    
function checkValidDate(dateObj)
{
    if ( ! isValue(dateObj) )   return true;
    if ( ! isValidDate(dateObj, 10) ){
        alert('유효하지 않은 날짜 입니다');
        dateObj.focus();
        return false;
    }
    
    return true;
   
}
function checkValidDate2(dateObj)
{
    if ( ! isValue(dateObj) )   return true;
    if ( ! isValidDate(dateObj, 10) ){
        dateObj.focus();
        return false;
    }
    
    return true;
   
}

    /**
    * @desc     오른쪽 공백를 잘라준다
    * @param    str - String
    * @return   str - 오른쪽 공백을 제거한 String  value return
    */
    function RTrim(str)
    {          
       if(str.charAt(str.length-1) == " ") 
       {
          str=str.substring(0,str.length-1); 
          return RTrim(str);
       }
       else
          return str;
    }

    /**
    * @desc     왼쪽 공백를 잘라준다.
    * @param    str - String
    * @return   str - 왼쪽 공백을 제거한 String  value return
    */
    function LTrim(str)
    { 
       if(str.charAt(0) == " ")
       {
          str=str.substring(1,str.length); 
          return LTrim(str);
       }
       else
          return str;
    }

    /**
    * @desc     좌우 공백를 잘라준다
    * @param    str - String
    * @return   str - 좌우 공백을 제거한 String  value return
    */
    function LRTrim(str)
    {
       return RTrim(LTrim(str));  
    }  
    
    /**
    * @desc     영화부 출력물
    * @param    path - 출력물경로
    * @param    mvId - 영화아이디
    * @param    pMode - insert/update
    */
    function doPrintMv(path,mvId,pMode)
    {
        if(pMode == 'insert'){
            alert('신청서를 제출 후 출력 할 수 있습니다.');
            return;
        }
            
        //스크린의 크기
        cw=670;
        ch=500;
        
        sw=screen.availWidth;
        sh=screen.availHeight;

        //열 창의 포지션
        px=(sw-cw)/2;
        py=(sh-ch)/2;
        url = path+"?mvId="+mvId+"&pMode="+pMode;
        var zwin = window.open(url,'zip','width='+cw+',height='+ch+',scrollbars=yes,top='+py+',left='+px);
        zwin.focus();   
    }
    
    /**
    * @desc     영상부 출력물
    * @param    path - 출력물경로
    * @param    mvId - 영상아이디
    * @param    pMode - insert/update
    */
    function doPrintVd(path,vdId,pMode)
    {
        if(pMode == 'insert'){
            alert('신청서를 제출 후 출력 할 수 있습니다.');
            return;
        }
            
        //스크린의 크기
        cw=670;
        ch=500;
        
        sw=screen.availWidth;
        sh=screen.availHeight;

        //열 창의 포지션
        px=(sw-cw)/2;
        py=(sh-ch)/2;
        url = path+"?vdId="+vdId+"&pMode="+pMode;
        var zwin = window.open(url,'zip','width='+cw+',height='+ch+',scrollbars=yes,top='+py+',left='+px);
        zwin.focus();   
    }
    
    /**
    * @desc     공연부 출력물
    * @param    path - 출력물경로
    * @param    peId - 공연아이디
    * @param    peAlterRecoId - 공연변경추천아이디
    * @param    pMode - insert/update
    */
    function doPrintPe(path,peId,peAlterRecoId,pMode)
    {
        if(pMode == 'insert'){
            alert('신청서를 제출 후 출력 할 수 있습니다.');
            return;
        }
            
        //스크린의 크기
        cw=670;
        ch=500;
        
        sw=screen.availWidth;
        sh=screen.availHeight;

        //열 창의 포지션
        px=(sw-cw)/2;
        py=(sh-ch)/2;
        url = path+"?peId="+peId+"&peAlterRecoId="+peAlterRecoId+"&pMode="+pMode;
        var zwin = window.open(url,'zip','width='+cw+',height='+ch+',scrollbars=yes,top='+py+',left='+px);
        zwin.focus();   
    }
    
    // 신청사 회원관리번호 체크
    function checkPasswd(sUserPass1 , sUserPass2 , action )
    {
    
        nUserPass1 = sUserPass1.length;
        bsTempChar = '';
        intSame = 0;
        isInt = false;
        isString = false;
        
        
        if( ( sUserPass1 == '' || sUserPass1 == null ) && action=='write') {
            alert('신청사회원관리번호를 입력해주세요');
            return false;
        } else if(sUserPass1 != sUserPass2) {
            alert('회원관리번호가 서로 다릅니다');
            return false;
        } else if(nUserPass1 > 12 || nUserPass1 < 4) {
            alert('신청사회원관리번호는 4자리 이상 12자리 이내입니다. ');
            return false;               
        } else if(nUserPass1 < 10) { 
            for (var i =0 ; i < nUserPass1; i++)
            {
                sTempChar = sUserPass1.substr(i,1);
                nTempChar = sTempChar.charCodeAt(0);
                        
                if ( nTempChar < 48 ||  (nTempChar > 58 && nTempChar < 65) || (nTempChar > 90 && nTempChar < 97) || nTempChar > 122)
                {
                    alert("신청사회원관리번호는 영문이나 숫자만 사용하실수 있습니다");
                    return false;
                } 
    
                if(nTempChar > 47 && nTempChar < 58) {
                    isInt = true;
                } else if((nTempChar > 96 && nTempChar < 123) || (nTempChar > 64 && nTempChar < 91)) {
                    isString = true;
                }
                
                bsTempChar = sTempChar ;
                
            }
            
            //if(action == 'write' || !Js_isNull(sUserPass1)) {
            //  if(!isInt || !isString) {
            //      alert('패스워드는 영문과 숫자를 같이 사용하셔야 합니다');
            //      return false;   
            //  }
            //}
        } 
        
        return true;
    }   
    
    //사업자 등록 번호 중복체크 박평우 추가
     var eDouble;
    function funcEnterDS(inDouble,enReg1,enReg2,enReg3){
        
        var ENTER_REGNO1 = "";
        var ENTER_REGNO2 = "";
        var ENTER_REGNO3 = "";
        var ENTER_REG_NO  = "";
        
        ENTER_REGNO1 = enReg1.value;
        ENTER_REGNO2 = enReg2.value;
        ENTER_REGNO3 = enReg3.value;
        
        if (ENTER_REGNO1 == "" || ENTER_REGNO2 == "" || ENTER_REGNO3 == "")
        {
            alert("사업자등록번호를 확인하세요.");
            return false;
        }
            
        ENTER_REG_NO = ENTER_REGNO1 + ENTER_REGNO2 + ENTER_REGNO3;
        var url="/jsp/popup/KPoEnterNS.jsp?ENTER_REG_NO=" + ENTER_REG_NO;
        
        
        var ls_Data =window.open(url, "New_Win2", "toolbar=no,width=300,height=50, location=no,status=yes,scrollbars=yes,directories=no,menubar=no,resizable=yes");
                
        eDouble = inDouble;
      
        if (ls_Data != null)
        {
            setData( ls_Data );     
        }
        
    }
    
     // 데이터 중복체크 아이프레임 사용
     function funcEnterDS2(inDouble,enReg1,enReg2,enReg3){
        
        var ENTER_REGNO1 = "";
        var ENTER_REGNO2 = "";
        var ENTER_REGNO3 = "";
        var ENTER_REG_NO  = "";
        
        ENTER_REGNO1 = enReg1.value;
        ENTER_REGNO2 = enReg2.value;
        ENTER_REGNO3 = enReg3.value;
        
        if (ENTER_REGNO1 == "" || ENTER_REGNO2 == "" || ENTER_REGNO3 == "")
        {
            alert("사업자등록번호를 확인하세요.");
            return false;
        }
            
        ENTER_REG_NO = ENTER_REGNO1 + ENTER_REGNO2 + ENTER_REGNO3;
        var url="/jsp/popup/KPoEnterNS2.jsp?ENTER_REG_NO=" + ENTER_REG_NO;
        
        document.frames['zframe'].location.href = url;
        
        //var ls_Data =window.showModalDialog(url, "New_Win2", "toolbar=no,width=300,height=50, location=no,status=yes,scrollbars=yes,directories=no,menubar=no,resizable=yes");
        //      
        //eDouble = inDouble;
        //
        //if (ls_Data != null)
        //{
        //    setData( ls_Data );       
        //}
        
    } 
        
    function setData( dataString )  
    {   
        
        if ( dataString.length > 0 )
        {
       
            eval(eDouble).value = dataString;
        }
        
    }
    
 // 우편번호 검색 변민경 추가
    var offZip1 = '';
    var offZip2 = '';
    var offKor1 = '';
    var offKor2 = '';
    var offEng = '';
    var city_cd = '';
    var si_cd = '';
    var gu_cd = '';
    var kor_do_si = '';
    var kor_si_gu = '';
    var kor_eub_dong = '';
    var eng_do_si = '';
    var eng_si_gu = '';
    var eng_eub_dong = '';
    var ji_bun = '';
    var addr_etc = '';
    
    function zipSearch(OFF_ZIP_CD1,OFF_ZIP_CD2,OFF_KOR_ADDR1,OFF_KOR_ADDR2,OFF_ENG_ADDR1,CITY_CD,SI_CD,GU_CD,KOR_DO_SI,KOR_SI_GU,KOR_EUB_DONG,ENG_DO_SI,ENG_SI_GU,ENG_EUB_DONG,JI_BUN,ADDR_ETC)
    {
        //newZipSearch(OFF_ZIP_CD1, OFF_ZIP_CD2, OFF_KOR_ADDR1, "", "", "");
        
        var zipcode = window.showModalDialog("/jsp/popup/KPoZipCodeS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:550px; dialogHeight:400px; scroll:1; help:1; status:0");
        
        offZip1      = OFF_ZIP_CD1;
        offZip2      = OFF_ZIP_CD2;
        offKor1      = OFF_KOR_ADDR1;
        offKor2      = OFF_KOR_ADDR2;
        offEng       = OFF_ENG_ADDR1;
        city_cd      = CITY_CD;
        si_cd        = SI_CD;
        gu_cd        = GU_CD;
        kor_do_si    = KOR_DO_SI;
        kor_si_gu    = KOR_SI_GU;
        kor_eub_dong = KOR_EUB_DONG;
        eng_do_si      = ENG_DO_SI;
        eng_si_gu    = ENG_SI_GU;
        eng_eub_dong = ENG_EUB_DONG;
        ji_bun       = JI_BUN;
        addr_etc     = ADDR_ETC;

        if (zipcode != null)
        {
            setZipCode( zipcode );      
        }
    }
    
    
    var OFF_ADDR_ETC;
    var OFF_ENG_ADDR_ETC;
    
    function zipSearch2(OFF_ZIP_CD1,OFF_ZIP_CD2,OFF_KOR_ADDR_1,OFF_KOR_ADDR_2,OFF_ENG_ADDR_1,OFF_ENG_ADDR_2,CITY_CD,SI_CD,GU_CD,KOR_DO_SI,KOR_SI_GU,KOR_EUB_DONG,ENG_DO_SI,ENG_SI_GU,ENG_EUB_DONG,ZIP_CHK){
        var zipcode = window.showModalDialog("/jsp/popup/KPoZipCodeS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:550px; dialogHeight:400px; scroll:1; help:1; status:0");
        offZip1      = OFF_ZIP_CD1;
        offZip2      = OFF_ZIP_CD2;
        city_cd      = CITY_CD;
        si_cd        = SI_CD;
        gu_cd        = GU_CD;
        kor_do_si    = KOR_DO_SI;
        kor_si_gu    = KOR_SI_GU;
        kor_eub_dong = KOR_EUB_DONG;
        eng_do_si    = ENG_DO_SI;
        eng_si_gu    = ENG_SI_GU;
        eng_eub_dong = ENG_EUB_DONG;
        
        if (zipcode != null){
            setZipCode( zipcode );
            
            if(eval(ZIP_CHK).checked){
                eval(ZIP_CHK).value        = "1";
                eval(OFF_KOR_ADDR_1).value = eval(KOR_DO_SI).value + ' ' + eval(KOR_SI_GU).value;
                eval(OFF_KOR_ADDR_2).value = LRTrim(eval(KOR_EUB_DONG).value + ' ' + OFF_ADDR_ETC);
                eval(OFF_ENG_ADDR_1).value = OFF_ENG_ADDR_ETC + eval(ENG_EUB_DONG).value + ', ' + eval(ENG_SI_GU).value + ', ' + eval(ENG_DO_SI).value + " ( "+ eval(ENG_DO_SI).value + " "+ eval(OFF_ZIP_CD1).value+"-"+eval(OFF_ZIP_CD2).value+" Korea) Rep of " + "KOREA";
            }else{
                eval(OFF_KOR_ADDR_1).value = eval(KOR_DO_SI).value + ' ' + eval(KOR_SI_GU).value + ' ' + eval(KOR_EUB_DONG).value + ' ' + OFF_ADDR_ETC;
                eval(OFF_ENG_ADDR_1).value = OFF_ENG_ADDR_ETC + eval(ENG_EUB_DONG).value + ', ' + eval(ENG_SI_GU).value + ', ' + eval(ENG_DO_SI).value + " ( "+ eval(ENG_DO_SI).value + " "+ eval(OFF_ZIP_CD1).value+"-"+eval(OFF_ZIP_CD2).value+" Korea) Rep of " + "KOREA";
            }
        }
    }
    
function setZipCode( dataString )
{
    var zipArray;
    if(dataString.length != 0 )
    {
        allZipcode = dataString.split('Ω');
        
        for (i=0;i<allZipcode.length;i++)
        {
            zipArray=allZipcode[i];
        
        if(i == 0)
            {
                if(offZip1!="")
            eval(offZip1).value = zipArray;
            }else if(i == 1)
            { 
                if(offZip2!="")
                  eval(offZip2).value = zipArray;
            }else if(i == 2)
            {
                if(offKor1!="")
                  eval(offKor1).value = zipArray;
            }else if(i == 3)
            {
                if(offKor2!="")
                  eval(offKor2).value = zipArray;
            }else if(i == 4)
            {
                if(offEng!="")
                  eval(offEng).value = zipArray;
            }else if(i == 5)
            {
                if(city_cd!="")
                  eval(city_cd).value = zipArray;
            }else if(i == 6)
            {
                if(si_cd!="")
                  eval(si_cd).value = zipArray;
            }else if(i == 7)
            {
                if(gu_cd!="")
                  eval(gu_cd).value = zipArray;
            }else if(i == 8)
            {
                if(kor_do_si!="")
                  eval(kor_do_si).value = zipArray;
            }else if(i == 9)
            {
                if(kor_si_gu!="")
                  eval(kor_si_gu).value = zipArray;
            }else if(i == 10)
            {
                if(kor_eub_dong!="")
                  eval(kor_eub_dong).value = zipArray;
            }else if(i == 11)
            {
                if(eng_do_si!="")
                  eval(eng_do_si).value = zipArray;
            }else if(i == 12)
            {
                if(eng_si_gu!="")
                  eval(eng_si_gu).value = zipArray;
            }else if(i == 13)
            {
                if(eng_eub_dong!="")
                  eval(eng_eub_dong).value = zipArray;
            }else if(i == 14)
            {
                if(ji_bun!="")
                  eval(ji_bun).value = zipArray;
            }else if(i == 15)
            {
                OFF_ADDR_ETC = zipArray;
                if(addr_etc!="")
                  eval(addr_etc).value = zipArray;
                
            }else if(i == 16)
            {
                OFF_ENG_ADDR_ETC = zipArray;
                if(OFF_ENG_ADDR_ETC != ""){
                    OFF_ENG_ADDR_ETC = OFF_ENG_ADDR_ETC + ", ";
                }
            }
        }
    }
}       
 /*
    // 국가 검색 변민경 추가 (국가명으로 나오게)
    function search(CTR_NM , CTR_CD)
    {
        //var ctrcode = window.showModalDialog("/jsp/popup/KPoTraComEtcS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:px; dialogHeight:350px; scroll:1; help:1; status:0");
        var ctrcode = window.showModalDialog("/icip/cstmrinfomanage/cstmrinqire/corpNationSearchPopup.do?form_name=frm&search=no", "newWin", "dialogWidth:px; dialogHeight:350px; scroll:1; help:1; status:0");
        
  
        var ctrnm   = CTR_NM;
        var ctr     = CTR_CD;
    
        if(ctrcode == undefined) ctrcode = window.returnValue;
        
        if (ctrcode != null)
        {
            allcode = ctrcode.split('_/');
            eval(ctrnm).value = allcode[1];
            eval(ctr).value = allcode[0];
        }
    }
    */
    // 국가 검색 정성훈 변경 (국가코드로 나오게)
    function search1(CTR_CD)
    {
        var ctrcode = window.showModalDialog("/jsp/popup/KPoTraComEtcS.jsp?form_name=frm&search=no", "newWin", "top=10,left=10,width=450,height=300,scrollbars=yes,resizable=no");
 
        var ctr  = CTR_CD;
    
        if (ctrcode != null)
        {
            allcode = ctrcode.split('_/');
            eval(ctr).value = allcode[0];
        }
    }
    
  //이용자명, 이용자주민번호체크 중복체크 박평우 추가
    var uDouble;
    function funcUserDS(UserDouble, UserName, UserJuminNo1, UserJuminNo2)
    {
        var USER_JUMIN_NO1 = "";
        var USER_JUMIN_NO2 = "";
        var USER_NAME = "";
        USER_NAME      = UserName.value;
        USER_JUMIN_NO1 = UserJuminNo1.value;
        USER_JUMIN_NO2 = UserJuminNo2.value;
        
        frm.USER_USE_YN.checked = false;
        
        if (USER_NAME == "")
        {
            alert("이용자명을 확인하세요.");
            return;
        }
        
        if (USER_JUMIN_NO1 == "" || USER_JUMIN_NO2== "")
        {
            alert("주민번호를 확인하세요.");
            return;
        }
        
        var url="/jsp/popup/KPoUserS.jsp?USER_NAME=" + USER_NAME + "&USER_JUMIN_NO1="+ USER_JUMIN_NO1+"&USER_JUMIN_NO2="+ USER_JUMIN_NO2+"&KMDS_USER_GUBUN=frm.KMDS_USER_GUBUN";
        //var url="/jsp/popup/KPoUserS.jsp?USER_NAME=" + USER_NAME + "&USER_JUMIN_NO1="+ USER_JUMIN_NO1+"&USER_JUMIN_NO2="+ USER_JUMIN_NO2;
        document.frames['xframe'].location.href = url;        
               
     // var url="/jsp/popup/KPoUserS.jsp?USER_NAME=" + USER_NAME + "&USER_JUMIN_NO1="+ USER_JUMIN_NO1+"&USER_JUMIN_NO2="+ USER_JUMIN_NO2;
     // var ls_Data =window.showModalDialog(url, "New_Win2", "toolbar=no,width=500,height=200, location=no,status=yes,scrollbars=yes,directories=no,menubar=no,resizable=yes");
                
    //  uDouble = inDouble;
      
    //    if (ls_Data != null)
    //    {
    //      setUserData( ls_Data );     
    //  }
        
    }   
        
    
    function setUserData( dataString )  
    {   
        if ( dataString.length > 0 )
        {
       
            eval(uDouble).value = dataString;
        }
    }
        
     //ID 중복체크 박평우 추가
     var idVarDouble;
    function funcIDS(IDDouble,UserID)
    {
        var ID_DOUBLE = "";
        var USER_ID   = "";
                
        USER_ID       = UserID.value;
               
        if (USER_ID == "")
        {
            alert("ID를 확인하세요.");
            return;
        }
        
         var url="/jsp/popup/KPoIDS.jsp?USER_ID=" + USER_ID;
        
        document.frames['idframe'].location.href = url;        
           
        //var url="/jsp/popup/KPoIDS.jsp?USER_ID=" + USER_ID;
        //var ls_Data =window.showModalDialog(url, "New_Win2", "toolbar=no,width=500,height=200, location=no,status=yes,scrollbars=yes,directories=no,menubar=no,resizable=yes");
                
        //idVarDouble = IDDouble;
      
        //if (ls_Data != null)
        //{
        //    setIDData( ls_Data );     
        //}
        
    }   
    
    function setIDData(dataString )     
    {   
        if ( dataString.length > 0 )
        {
            eval(idVarDouble).value = dataString;
        }
    }
    
    var chgcd;  
    var chgcontent;
    var chgDt;
    var chgGbn;
    function CancelCdSel(member_g){
        var cancelcd = window.showModalDialog("/jsp/popup/KTraCancelS.jsp?form_name=frm&member_gbn="+member_g, "newWin","dialogWidth:px; dialogHeight:270px; scroll:1; help:1; status:0");

        chgcd = frm.CANCEL_CODE;
        chgcontent = frm.CANCEL_CODE_CONTENT;
        chgDt = frm.CANCEL_CLOSURE_DATE;
        chgGbn = frm.CANCEL_CLOSURE_GUBUN;
        
        if (cancelcd != null) setCancelCode( cancelcd );
    }        
    
    
    function setCancelCode( cancelcd ){
        var addCancel;
        
        if(cancelcd.length != 0 ){
            allCancelcode = cancelcd.split('||');
              
            for (i=0;i<allCancelcode.length;i++){
                addCancel=allCancelcode[i];
                
                if(i == 0){
                    
                    if(chgcd!="")
                        eval(chgcd).value = addCancel;
                
                }else if(i == 1){ 
                    if(chgcontent!="")
                        eval(chgcontent).value = addCancel;
                    
                }else if(i == 2){ 
                    if(chgDt!="")
                        eval(chgDt).value = addCancel;
                }else if(i == 3){ 
                    if(chgGbn!="")
                        eval(chgGbn).value = addCancel;
                }
            }
        }
    }
    
    
    // 롤오버메뉴 스크립트 시작 정희정 추가
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
    // 롤오버메뉴 스크립트 끝
    
// 법인번호데 중복체크 아이프레임 사용
     function funcEnterCorpNo(corpNo,corp1,corp2){
        
        var CORPO_NO1 = "";
        var CORPO_NO2 = "";
        var CORPO_NO  = "";
        
        CORPO_NO1 = corp1.value;
        CORPO_NO2 = corp2.value;
        
        if (CORPO_NO1 == "" || CORPO_NO2 == "")
        {
            alert("법인번호를 확인하세요.");
            return false;
        }
            
        CORPO_NO = CORPO_NO1 + CORPO_NO2;
        var url="/jsp/popup/KPoEnterCP.jsp?CORPO_NO=" + CORPO_NO;
        
        document.frames['cframe'].location.href = url;
        
        //var ls_Data =window.showModalDialog(url, "New_Win2", "toolbar=no,width=300,height=50, location=no,status=yes,scrollbars=yes,directories=no,menubar=no,resizable=yes");
        //      
        //eDouble = inDouble;
        //
        //if (ls_Data != null)
        //{
        //    setData( ls_Data );       
        //}
        
    }
    // 특수문자 입력제한 : 서영환 추가
    function nonkr(obj) {
          var tvalue = obj.value;
          var onvalue = obj.value;
          
          count=0
        
          for (i=0;i<onvalue.length;i++){
           ls_one_char = onvalue.charAt(i);
           
           if(ls_one_char.search(/[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|.|-|@|/| ]/) == -1) {
            count++
           }
          }
          
          if(count!=0) {
           alert("특수문자는 입력하실 수 없습니다.") 
           obj.value = "";
           obj.focus();
           return false;
          } else {
           return false;
          }
     }
     function nonkr2(obj) {
          var tvalue = obj.value;
          var onvalue = obj.value;
          
          count=0
        
          for (i=0;i<onvalue.length;i++){
           ls_one_char = onvalue.charAt(i);
           
           if(ls_one_char.search(/[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|._-|-|@|/| ]/) == -1) {
            count++
           }
          }
          
          if(count!=0) {
           alert("특수문자는 입력하실 수 없습니다.") 
           obj.value = "";
           obj.focus();
           return false;
          } else {
           return false;
          }
     }
// 비공개 여부 값 변경
function checkYN(checkyn)
{
   if(checkyn.checked == true)
   {
        checkyn.value = "Y";
   }else{
        checkyn.value = "N";
   }
}
//소문자만 입력
function chkEng(obj) {

    str=obj.value
    
    chk_r=true // 원하지 않는 문자가 없음
    
    for (k=0 ; k<str.length ; k++)
    {
        if (str.charCodeAt(k)<97 || str.charCodeAt(k)>122)
        {
        chk_r=false
        }
    }
    
    if (chk_r==false) // 결과를 보고 액션을 취함
    {
        alert("소문자로 입력해주세요!")
        return;
    }   
}
     
/* 
function isAlphaEngChk(obj) {
     var chk;
     str = obj.value;
     res1 = (/[a-z]/i).test(str); //영문이 있는지
     res2 = (/[0-9]/).test(str); //숫자가 있는지
     res3 = (/^[0-9a-z_]*$/i).test(str); //영문, 숫자, _ 이외엔 없는지
     res4 = (/[A-Z]/i).test(str); //영문이 있는지
     chk = res1 && res2 && res3;
     if ( str.length > 0 && chk == false ) {
        alert("영어와 숫자를 조합하여 입력하셔야 합니다.");
        obj.focus();
        return false;
     }else if(str.length > 0 && res4 == true) {
        alert("영어 대문자는 사용하실수 없습니다.");
        obj.focus();        
        return false;
     }else {
        return true;    
     }
}    
*/
    
 function isAlphaEngChk(field) {
  
    var valid = "0123456789abcdefghijklmnopqrstuvwxyz";
    var ok = "yes";
    var temp;
    
    for (var i=0; i<field.value.length; i++) {
        temp = "" + field.value.substring(i, i+1);
        
        if (valid.indexOf(temp) == "-1") ok = "no";
    }
    if (ok == "no") {
        alert("영소문자 혹은 숫자만 입력할 수 있습니다");
        field.value="";
        field.focus();
        return true;
    }
}
function isAlphaUpperEngChk(obj) {
     var chk;
     str = obj.value;
     for(i=0; i<str.length; i++) {
       if(str.charCodeAt(i) >= 97 && str.charCodeAt(i) <= 122) {
           alert("소문자는 사용하실수 없습니다.");
           obj.value = "";
           return false;
       }
     }
}
         
// 주소록 검색 변민경 추가
  var connm;       
    var conjik;      
    var condept;     
  var conzipcd1;    
  var conzipcd2;
    var conaddr1;    
    var conaddr2;    
    var conemail1;    
    var conemail2;
    var conemailyn;  
    var concell1;     
    var concell2;
    var concell3;
    var concellyn;   
    var contel;      
    var contelyn;     
    var confax;      
    var confaxyn;  
    var ref;  

    function AddressSearch(memid,memgbn,CONTACT_NM,CONTACT_JIK,CONTACT_DEPT,CONTACT_ZIP_CD1,CONTACT_ZIP_CD2,CONTACT_ADDR1,CONTACT_ADDR2,CONTACT_EMAIL1,CONTACT_EMAIL2,CONTACT_EMAIL_YN,CONTACT_CELL1,CONTACT_CELL2,CONTACT_CELL3,CONTACT_CELL_YN,CONTACT_TEL,CONTACT_TEL_YN,CONTACT_FAX,CONTACT_FAX_YN)
    {
// var addresscode = window.open("/jsp/popup/KPoAddressInfo.jsp?MEMBER_ID="+memid+"&form_name=frm&search=no","AddressAction", "width=511,height=425,scrollbars=no,top=100,left=500,directories=no,location=no,resizable=yes,menubar=no");
        var addresscode = window.showModalDialog("/jsp/popup/KPoAddressInfo.jsp?MEMBER_ID="+memid+"&MEMBER_GBN="+memgbn+"&form_name=frm&search=no", window, "dialogWidth:700px; dialogHeight:400px; scroll:1; help:1; status:0");

        connm       = CONTACT_NM;         
        conjik      = CONTACT_JIK;        
        condept     = CONTACT_DEPT;       
        conzipcd1   = CONTACT_ZIP_CD1;     
        conzipcd2   = CONTACT_ZIP_CD2;
        conaddr1    = CONTACT_ADDR1;      
        conaddr2    = CONTACT_ADDR2;      
        conemail1   = CONTACT_EMAIL1;      
        conemail2   = CONTACT_EMAIL2;      
        conemailyn  = CONTACT_EMAIL_YN;   
        concell1    = CONTACT_CELL1;       
        concell2    = CONTACT_CELL2;       
        concell3    = CONTACT_CELL3;       
    concellyn   = CONTACT_CELL_YN;    
        contel      = CONTACT_TEL;        
        contelyn      = CONTACT_TEL_YN;     
      confax      = CONTACT_FAX;        
    confaxyn    = CONTACT_FAX_YN;     


        if (addresscode != null)
        {
            setAddressCode( addresscode );      
        }
    }
function setAddressCode( dataString )
{
    var addArray;
    if(dataString.length != 0 )
    {
        allZipcode = dataString.split('Ω');
      
        for (i=0;i<allZipcode.length;i++)
        {
            addArray=allZipcode[i];
        if(allZipcode.length == 4){
            if(i == 0)
            {
                if(conzipcd1!="")
            eval(conzipcd1).value = addArray;
            }else if(i == 1)
            { 
                if(conzipcd2!="")
                  eval(conzipcd2).value = addArray;
            }else if(i == 2)
            {
                if(conaddr1!="")
                  eval(conaddr1).value = addArray;
            }else if(i == 3)
            {
                if(conaddr2!="")
                  eval(conaddr2).value = addArray;
            }
        }else{
                
        if(i == 0)
            {
                if(connm!="")
            eval(connm).value = addArray;
            }else if(i == 1)
            { 
                if(conjik!="")
                  eval(conjik).value = addArray;
            }else if(i == 2)
            {
                if(condept!="")
                  eval(condept).value = addArray;
            }else if(i == 3)
            {
                if(conzipcd1!="")
                  eval(conzipcd1).value = addArray;
            }else if(i == 4)
            {
                if(conzipcd2!="")
                  eval(conzipcd2).value = addArray;
            }else if(i == 5)
            {
                if(conaddr1!="")
                  eval(conaddr1).value = addArray;
            }else if(i == 6)
            {
                if(conaddr2!="")
                  eval(conaddr2).value = addArray
            }else if(i == 7)
            {
                if(conemail1!="")
                  eval(conemail1).value = addArray;
            }else if(i == 8)
            {
                if(conemail2!="")
                  eval(conemail2).value = addArray;
            }else if(i == 9)
            {       
                if(conemailyn!=""){
                  if(addArray == "Y"){
                  eval(conemailyn).checked = true;
                }else{
                    eval(conemailyn).checked = false;
                }
              }
            }else if(i == 10)
            {
                if(concell1!="")
                  eval(concell1).value = addArray;
            }else if(i == 11)
            {
                if(concell2!="")
                  eval(concell2).value = addArray;
            }else if(i == 12)
            {
                if(concell3!="")
                  eval(concell3).value = addArray;
            }else if(i == 13)
            {               
                if(concellyn!=""){              
                if(addArray == "Y"){
                  eval(concellyn).checked = true;
                }else{
                    eval(concellyn).checked = false;
                }
              }
            }else if(i == 14)
            {
                if(contel!="")
                  eval(contel).value = addArray;
            }else if(i == 15)
            {
                if(contelyn!=""){
                if(addArray == "Y"){
                  eval(contelyn).checked = true;
                }else{
                    eval(contelyn).checked = false;
                }
              }
            }else if(i == 16)
            {
                if(confax!="")
                  eval(confax).value = addArray;
            }else if(i == 17)
            {
                if(confaxyn!=""){
                if(addArray == "Y"){
                  eval(confaxyn).checked = true;
                }else{
                    eval(confaxyn).checked = false;
                }
              }
            }
        }
      }
    }
    
}

//--------------------------------------------------
//  입력값이 숫자인지 체크2
//--------------------------------------------------
 function validate2(field,msg) {
 
    var valid = "0123456789"
    var ok = "yes";
    var temp;
    
    for (var i=0; i<field.value.length; i++) {
        temp = "" + field.value.substring(i, i+1);
        
        if (valid.indexOf(temp) == "-1") ok = "no";
    }
    if (ok == "no") {
        alert(msg + "는 숫자만 입력할 수 있습니다");
        field.focus();
        field.select();
        return true;
    } else {
        return false;
    }
}


//--------------------------------------------------
//  전화번호와 팩스번호에 숫자와 '-'만 입력되게.
//--------------------------------------------------
function chkTelFaxNumber(field,msg) {

   var valid = "-0123456789"
   var ok = "yes";
   var ch;
   var value =  field.value;
   var temp = value;

    while (1)
    {
         if(temp.indexOf("--") == -1)
         {
             if(temp.length > 1) if (temp.substr(0,1) == "-")             temp = temp.substring(1,temp.length-1);
             break;
         }
         else
         {
             temp = temp.replace("--","-");
         }
    }

    value = temp;

   for (var i=0; i<value.length; i++) {
       ch = "" + value.substring(i, i+1);

       if (valid.indexOf(ch) == -1)
       {
           temp = temp.replace(ch,"");
           ok = "no";
       }

   }
   if (ok == "no") {
       alert(msg + "는 숫자와 '-'만 입력할 수 있습니다");
       field.focus();
   }
    while (1)
    {
         if(temp.indexOf("--") == -1)
         {
             if(temp.length > 1) if (temp.substr(0,1) == "-")             temp = temp.substring(1,temp.length-1);
             break;
         }
         else
         {
             temp = temp.replace("--","-");
         }
    }

    if(temp == "-") temp = "";
    field.value = temp;
}


//---------------------------------------
//두 문자 같은지 확인
//---------------------------------------
function twinStr(obj1, obj2, msg)
{
    if ( obj1.value == obj2.value ) {
        return false;
    } else {
        alert(msg);
        obj1.focus();
        obj1.select();
        return true;
    }
}

//---------------------------------------
//지정된 LENGTH 가 되면 자동으로 다음 텍스트박스로 이동
//---------------------------------------
function MoveFocus(obj1,obj2,vlen)
{
     if ( obj1.value.length == vlen )
     {
          obj2.focus();
          obj2.select();
          return;
    }
}


/** 
* 입력값이 특정 문자(chars)만으로 되어있는지 체크 
* 특정 문자만 허용하려 할 때 사용 
* ex) if (!containsCharsOnly(form.blood,"ABO")) { 
* alert!("혈액형 필드에는 A,B,O 문자만 사용할 수 있습니다."); 
* } 
* 추가 : 서영환
*/ 
function containsCharsOnly(input,chars) { 
    for (var inx = 0; inx < input.value.length; inx++) { 
        if (chars.indexOf(input.value.charAt(inx)) == -1) 
        return false; 
    } 
    return true; 
} 

/** 
* 입력값에 숫자만 있는지 체크 
*/ 
function isAlphabet(input) { 
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; 
    return containsCharsOnly(input,chars); 
} 

/** 
* 입력값에 숫자만 있는지 체크 
*/ 
function isNumber(input) { 
    var chars = "0123456789"; 
    return containsCharsOnly(input,chars); 
}

/** 
* 패스워드 영숫자 혼합체크
* 영문과 숫자+로만 이루어져 있으면 true 아니면 false
* 추가 서영환
*/ 

function isAlphaNumChk(obj) {
     var chk;
     str = obj.value;
     res1 = (/[a-z]/i).test(str); //영문이 있는지
     res2 = (/[0-9]/).test(str); //숫자가 있는지
     res3 = (/^[0-9a-z_]*$/i).test(str); //영문, 숫자, _ 이외엔 없는지
     
     chk = res1 && res2 && res3;
     if ( str.length > 0 && chk == false ) {
        alert("패스워드는 영어와 숫자를 조합하여 생성하셔야 합니다.");
        obj.value = "";
        obj.focus();
        return false;
     } else {
        return true;    
     }
}


/** 
* 날짜형태로 입력되는지 실시간 체크해서 날짜포멧 검사
* 추가 서영환
*/ 

function formatDate(obj) {
     var vdate = obj.value;
     var chk;
     
     if(event.keyCode != 8){
         if ( vdate.length == 4 ) obj.value = vdate + "-";
         if ( vdate.length == 7 ) obj.value = vdate + "-";
     }   
}

function dateChk(obj, meg){
    var iDate = obj.value;
    
    iDate = iDate.split("-").join("");
    
    if(iDate.length != 8 && iDate.length != 0){
        alert(meg + " 입력이 잘못되었습니다.");
        obj.value = "";
        obj.focus();
        return;
    }
    if(iDate.length != 0){
        oDate = new Date();
        oDate.setFullYear(iDate.substring(0,4));
        oDate.setMonth(iDate.substring(4,6) - 1);
        oDate.setDate(iDate.substring(6));
        
        if(oDate.getFullYear() != iDate.substring(0,4)
        || oDate.getMonth()+1 != iDate.substring(4,6)
        || oDate.getDate() != iDate.substring(6)){
            alert(meg + " 입력이 잘못되었습니다.");
            obj.value = "";
            obj.focus();
            return;
        }
        
        obj.value = iDate.substring(0,4) + "-" + iDate.substring(4,6) + "-" + iDate.substring(6);
    }
}

function MonformatDate(obj) {
     
     var vdate = obj.value;
     var chk;
     
     if ( vdate.length == 4 ) obj.value = vdate + "-";
     if ( vdate.length == 7 ) {
        
        var yyyy = vdate.substring(0, 4);
        var mm   = vdate.substring(5, 7);
        
        if (isNaN(yyyy) || parseInt(yyyy) < 1000) {
            alert('유효하지 않은 날짜 입니다');
            chk = false;
        } else if (isNaN(mm) || parseFloat(mm) > 12 || parseFloat(mm) < 1) {
            alert('유효하지 않은 날짜 입니다');
            chk = false;
        }
        if ( chk == false ) obj.value = "";
     }
     
}
 
function getPageSize() {
    var xScroll, yScroll;
    var windowWidth, windowHeight;

    if (window.innerHeight && window.scrollMaxY) {
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight) {
        xScroll = document.body.scrollWidth;
        yScroll = document.body.scrollHeight;
    } else {
        xScroll = document.body.offsetWidth;
        yScroll = document.body.offsetHeight;
    }

    if (self.innerHeight) {
        if (document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            windowWidth = self.innerWidth;
        }

        windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) {
        windowWidth = document.documentElement.clientWidth;
        windowHeight = document.documentElement.clientHeight;
    } else if (document.body) {
        windowWidth = document.body.clientWidth;
        windowHeight = document.body.clientHeight;
    }

    if (yScroll < windowHeight) {
        pageHeight = yScroll;
    } else {
        pageHeight = windowHeight;
    }

    if (xScroll < windowWidth) {
        pageWidth = xScroll;
    } else {
        pageWidth = windowWidth;
    }

    return [pageWidth, pageHeight];
}

/**
 * loading layer를 보이고 감춘다.
 */
function loading(flag, loadingText){
    if(flag) {
        $("#loading").center().show();
    } else {
        $("#loading").hide();
    }
}

function pup_loading(bul){
    if (bul){

        var arrayPageSize = getPageSize();

        document.getElementById('loading').style.left = document.body.clientWidth / 2 - 90;
        document.getElementById('loading').style.top = document.body.clientHeight / 2 - 60;
        document.getElementById('loading').style.display='block';
    }else{
        document.getElementById('loading').style.display='none';
    }
}

function ifrm_loading(bul){
    if (bul){
        var arrayPageSize = getPageSize();

        document.getElementById('loading').style.left = document.body.clientWidth / 2 - 238;
        document.getElementById('loading').style.top = document.body.clientHeight / 2 - 60;
        document.getElementById('loading').style.display='block';
    }else{
        document.getElementById('loading').style.display='none';
    }
}

function cursor(obj){
    obj.select(0, obj.length);
}

/**
 * 금액에 콤마를 추가하여 표시한다. 
 */
function getFormatCurrency(money) {
    
    var tempMoney = money.toString().replace(/\$|\,/g, "");
    if(tempMoney == "" || isNaN(tempMoney)) {
        return money;
    }

    var reg = /(^[+-]?\d+)(\d{3})/;
    while(reg.test(tempMoney)) {
        tempMoney = tempMoney.replace(reg, '$1' + ',' + '$2');
    }
    
    return tempMoney;
}




//new jsp 달력..(기존값셋팅)
function openCalendarJsp(ss){
    
    var intWidth = 226;
    var intHeight = 255;

    var url = "/Common/calendar.jsp?sVal="+eval(ss).value;
    var winPosLeft = (screen.width - intWidth) / 2;
    var winPosTop  = (screen.height - intHeight) / 2;

    var ls_Date = window.showModalDialog(url, "달력", "dialogTop:"+winPosTop+"; dialogLeft:"+winPosLeft+"; dialogWidth:226px; dialogHeight:255px; Raised; resizable: no; help: no; scroll: no; status: yes;");

    if(ls_Date == undefined) ls_Date = window.returnValue;

    inpst = ss;
    if(ls_Date != null){
        setDate( ls_Date );
    }
}

function setDate(dateString){

    if(dateString.length > 0){
        eval(inpst).value = dateString.substring(0, 4) + '-' + dateString.substring(5, 7) + '-' + dateString.substring(8);
    }else{
        eval(inpst).value = dateString;
    }
}


    //주민번호 체크 함수 
     function jumin_check() 
    {

         if ( frm.JUMIN_NO1.value.length + frm.JUMIN_NO2.value.length  != 13)   {   // 번호가 13개 아니라면
            alert("대표자주민등록번호의 자리수를 확인 하시기 바랍니다."); 
            document.getElementById("JUMIN_NO1").focus();  //커서를 주민번호 앞자리에 놓는다.
          return false;
         } 
         else 
         {   
            var strjumin = document.getElementById("JUMIN_NO1").value + document.getElementById("JUMIN_NO2").value;   //변수에 주민번호 앞자리 담음
            
         }
            var a1=strjumin.substring(0,1)   //주민번호 계산법
            var a2=strjumin.substring(1,2)          
            var a3=strjumin.substring(2,3)
            var a4=strjumin.substring(3,4)
            var a5=strjumin.substring(4,5)
            var a6=strjumin.substring(5,6)
            var checkdigit=a1*2+a2*3+a3*4+a4*5+a5*6+a6*7
            var b1=strjumin.substring(6,7)
            var b2=strjumin.substring(7,8)
            var b3=strjumin.substring(8,9)
            var b4=strjumin.substring(9,10)
            var b5=strjumin.substring(10,11)
            var b6=strjumin.substring(11,12)
            var b7=strjumin.substring(12,13)
            var checkdigit=checkdigit+b1*8+b2*9+b3*2+b4*3+b5*4+b6*5 
            checkdigit = checkdigit%11
            checkdigit = 11 - checkdigit
            checkdigit = checkdigit%10
            
            if (checkdigit != b7) 
            {   
               alert('올바르지 않은 주민등록번호입니다.\n다시 확인하고 입력해 주세요.'); 
              // document.getElementById("USER_JUMIN_NO1").value="";
               document.getElementById("JUMIN_NO1").focus();   
               frm.JUMIN_NO_CHK.value = "2" ;  
               return false;
            } else {
                 alert('올바른 주민등록번호입니다.'); 
                 frm.JUMIN_NO.value = strjumin ;  
                 frm.JUMIN_NO_CHK.value = "1" ;  
            }

          return true;
    }
    
         window.returnValue = undefined;
/*     
    //2012/10/10 happy81
     //function newZipSearch(zip_cd_1, zip_cd_2, address, new_address, eng_address, new_eng_address, city_cd, si_cd, gu_cd)
     function newZipSearch(zip_cd_1, zip_cd_2, address, new_address, eng_address, new_eng_address, city_cd, si_cd, gu_cd)
     {
        //var resultValue = window.showModalDialog("/jsp/popup/NewKPoZipCodeS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:700px; dialogHeight:450px; scroll:1; help:1; status:0; resizable:1");
        var resultValue = window.showModalDialog("/icip/cstmrinfomanage/cstmrregist/zipSearch.do?form_name=frm&search=no", "newWin", "dialogWidth:700px; dialogHeight:450px; scroll:1; help:1; status:0; resizable:1");
        if(resultValue == undefined) resultValue = window.returnValue;
        
         if(resultValue != undefined){
            //$("input[name=" + zip_cd_1 + "]").val(resultValue.zipCd.substr(0,3));
            //$("input[name=" + zip_cd_2 + "]").val(resultValue.zipCd.substr(3,2));
            $("input[name=" + zip_cd_1 + "]").val(resultValue.zipCd);
            $("input[name=" + zip_cd_2 + "]").val(resultValue.oldzipCd);
            $("input[name=" + address + "]").val(resultValue.addr);
            $("input[name=" + new_address + "]").val(resultValue.newAddr);
            $("input[name=" + eng_address + "]").val(resultValue.engAddr);
            $("input[name=" + new_eng_address + "]").val(resultValue.newEngAddr);
            
            $("input[name=" + city_cd + "]").val(resultValue.cityCd);
            $("input[name=" + si_cd + "]").val(resultValue.siCd);
            $("input[name=" + gu_cd + "]").val(resultValue.guCd);
         }
        
     } 
     */
         
     function newZipSearch2(zip_cd_1, zip_cd_2, address, new_address, eng_address, new_eng_address)
     {
         var resultValue = window.showModalDialog("/jsp/popup/NewKPoZipCodeS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:700px; dialogHeight:450px; scroll:1; help:1; status:0");
         if(resultValue == undefined) {
             resultValue = window.returnValue;
         }
         if(resultValue != undefined){
             //$("input[name=" + zip_cd_1 + "]").val(resultValue.zipCd.substr(0,3));
             //$("input[name=" + zip_cd_2 + "]").val(resultValue.zipCd.substr(3,2));
             $("input[name=" + zip_cd_1 + "]").val(resultValue.zipCd);
             $("input[name=" + zip_cd_2 + "]").val(resultValue.oldzipCd);
             $("input[name=" + address + "]").val(resultValue.addr);
             $("input[name=" + new_address + "]").val(resultValue.newAddr);
             $("input[name=" + eng_address + "]").val(resultValue.engAddr);
             $("input[name=" + new_eng_address + "]").val(resultValue.newEngAddr);
         }else{
             self.close();
         }
     } 
     
     function initTableSorter(){
            // 테이블 정렬 처리
         var oColumn = ($("input[name='column']").length == 0) ? $("input[name='columNm']") : $("input[name='column']");
         var oSortDir = ($("input[name='sortType']").length == 0) ? $("input[name='sort']") : $("input[name='sortType']");
            var sortColumn = oColumn.val();
            var sortDir = oSortDir.val();
            $("table tr._tablesorter > th[data-column-name='" + sortColumn + "']").addClass(function(){
                if(sortDir == "ASC" || sortDir == "asc") $(this).addClass("headerSortUp");
                else if(sortDir == "DESC" || sortDir == "desc") $(this).addClass("headerSortDown");
            });
            $("table tr._tablesorter > th").each(function(){
                $(this).css("cursor", "pointer").click(function(){
                    if($(this).hasClass("headerSortUp")){
                        eval($(this).attr("data-sort-desc-func"));
                    }
                    else{
                        eval($(this).attr("data-sort-asc-func"));
                    }
                });
            });
            
        }

        try{
        jQuery.fn.center = function (absolute) {
            return this.each(function () {
                var t = jQuery(this);

                t.css({
                    position:   absolute ? 'absolute' : 'fixed', 
                    left:       '50%', 
                    top:        '50%', 
                    zIndex:     '9999'
                }).css({
                    marginLeft: '-' + (t.outerWidth() / 2) + 'px', 
                    marginTop:  '-' + (t.outerHeight() / 2) + 'px'
                });

                if (absolute) {
                    t.css({
                        marginTop:  parseInt(t.css('marginTop'), 10) + jQuery(window).scrollTop(), 
                        marginLeft: parseInt(t.css('marginLeft'), 10) + jQuery(window).scrollLeft()
                    });
                }
            });
        };
        
        
        $(document).ready(function(){
            $("input[maxlength]").keyup(function(){
                if($(this).attr("maxlength") == $(this).val().length) $(this).next().focus();
            });
            
            //document.domain = 'icip.net';
        });
        
        }catch(e){
            ;
        }