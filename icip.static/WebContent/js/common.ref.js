/**
 * @author
 *
 */


// 레이어 팝업 오픈
var cnt=0;
function showPopup(popId){
var pop = document.getElementById(popId);
	if(cnt==0){
		cnt=1;
		pop.style.display="block";
		//pop.style.top="20px";
		//pop.style.left="100px";
	}else{
		cnt=0;
		pop.style.display="none";
	}
}



$.postJSON = function(url, data, callback) {alert(callback);
	$.ajax({
		'url': url,
		'type': 'post',
		'processData': false,
		'data': JSON.stringify(data),
		contentType: 'application/json',
		success: callback
	});
};

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

/**
 * 메시지 팝업창을 연다.
 *
 * @param messageTitle
 * @param messageCode
 * @param bindArgs '|' 로 구분되는 메시지에 바인드될 argument.
 * @returns {Boolean}
 */
function open_message(messageTitle, messageCode, bindArgs) {
	if (messageTitle == null) {
		alert("ERROR : message title is null.");
		return false;
	}
	if (messageCode == null) {
		alert("ERROR : message code is null.");
		return false;
	}
	//alert("messageCode=="+messageCode);
	var uri = '/common/common.messagePopup?messageTitle=' + messageTitle + '&messageCode=' + messageCode;
	if (bindArgs !== null) {
		uri = uri + "&bindArgs=" + bindArgs;
	}

	Shadowbox.open({
		content: uri,
		player: "iframe",
		width: 315,
		height: 200
	});

	return false;
}



/**
 * 메시지 팝업을 보여준다. 창을 닫을 때 입력받은 callback 함수를 수행한다.
 *
 * @param messageTitle
 * @param messageCode
 * @param callback
 * @returns {Boolean}
 */
function open_messageWithCallback(messageTitle, messageCode, callback) {
	if (messageTitle == null) {
		alert("ERROR : message title is null.");
		return false;
	}
	if (messageCode == null) {
		alert("ERROR : message code is null.");
		return false;
	}

	var uri = $contextRoot + '/common/common.messagePopup?messageTitle=' + messageTitle + '&messageCode=' + messageCode;
	if (callback !== null) {
		uri = uri + '&callback=' + callback;
	}

	Shadowbox.open({
		content: uri,
		player: "iframe",
		width: 302,
		height: 185
	});

	return false;
}

/**
 * 에러 메시지를 보여주기 위한 메소드. 공통팀에서 사용됨.
 *
 * @param messageTitle
 * @param callback
 * @returns {Boolean}
 */
function errorMessageWithCallback(messageTitle, callback) {
	if (messageTitle == null) {
		messageTitle = "ErrorMessage";
	}

	var uri = '/common/common.errMessagePopup?messageTitle=' + messageTitle + '&callback=' + callback;

	Shadowbox.open({
		content: uri,
		player: "iframe",
		width: 302,
		height: 185
	});

	return false;
}

function open_confirm(messageTitle, messageCode, callback) {
	if (messageTitle == null) {
		alert("ERROR : message title is null.");
		return false;
	}
	if (messageCode == null) {
		alert("ERROR : message code is null.");
		return false;
	}

	var uri = '/common/common.confirmPopup?messageTitle=' + messageTitle + '&messageCode=' + messageCode;
	if (callback !== null) {
		uri = uri + '&callback=' + callback;
	}

	Shadowbox.open({
		content: uri,
		player: "iframe",
		width: 302,
		height: 185
	});

	return false;
}
// 확인/취소 버튼 클릭 후 YES/NO 반환하는 confirm widnow
function open_common_confirm(messageTitle, messageCode, callback) {
	if (messageTitle == null) {
		alert("ERROR : message title is null.");
		return false;
	}
	if (messageCode == null) {
		alert("ERROR : message code is null.");
		return false;
	}

	var uri = '/common/common.confirmPopup?messageTitle=' + messageTitle + '&messageCode=' + messageCode;
	if (callback !== null) {
		uri = uri + '&callback=' + callback;
	}

	Shadowbox.open({
		content: uri,
		player: "iframe",
		width: 304,
		height: 150
	});

	return false;
}

//확인/취소 버튼 클릭 후 YES/NO 반환하는 confirm widnow(Warning Title노출안함)
function open_common_confirm_noSubTitle(messageTitle, messageCode, callback) {
	if (messageTitle == null) {
		alert("ERROR : message title is null.");
		return false;
	}
	if (messageCode == null) {
		alert("ERROR : message code is null.");
		return false;
	}

	var uri = '/common/common.confirmPopup?subTitleYN=false&messageTitle=' + messageTitle + '&messageCode=' + messageCode;
	if (callback !== null) {
		uri = uri + '&callback=' + callback;
	}

	Shadowbox.open({
		content: uri,
		player: "iframe",
		width: 304,
		height: 150
	});

	return false;
}

/**
 * JavaScript 전용 message popup 입니다.
 * @param ti_tle
 * @param con_tent
 * @returns {Boolean}
 */
function open_alert(ti_tle, con_tent, obj_id, callback) {
	$("#alert_popup_title").text(ti_tle);
	$("#alert_popup_content").text(con_tent);

	if (obj_id) {
		$("#alert_obj_id").val(obj_id);
	}
	if(obj_id==''){
		$("#alert_obj_id").val("");
	}
	if (callback) {
		$("#alert_callback_id").val(callback);
	}
	resize_popup_height("alert_popup");

	//동일한 화면에서 다른 layer popup 띄운 후  open_alert 띄울때 중앙정렬 안되는 문제 수정 (2013-02-22)
	var posLeft = (  ($(window).width() - $("#alert_popup .layerInner").width()) / 2 );
	var posTop = ( ($(window).height() - $("#alert_popup .layerInner").height()) / 2 );
	$("#alert_popup .layerInner").css("left",parseInt(posLeft));
	$("#alert_popup .layerInner").css("top",parseInt(posTop));
	//

	Shadowbox.open({
		content: $("#alert_popup").html(),
		player: "html",
		width: 304,
		height: 150,
		modal: true
	});
	return false;
}

function open_alert_close() {
	//alert( $("#alert_obj_id").val() );
	Shadowbox.close();
	$(this).focus(); // added. 2011.07.06. IE7,8 에서 focus out 되는것을 수정.

	if($("#alert_callback_id").val()){
		var id = $("#alert_callback_id").val();
		var alert_obj_id = $("#alert_obj_id").val();
		var obj = $("#" + id );
		obj.find("[id='"  + alert_obj_id  + "']" ).focus();
		obj.find("[name='"+ alert_obj_id  + "']" ).focus();
	} else if ($("#alert_obj_id").val()) {
		//alert( $("#alert_obj_id").val());
		$("[id='" + $("#alert_obj_id").val() + "']" ).focus();
		$("[name='"+ $("#alert_obj_id").val() + "']").focus();
	} else {

	}
	if($("#alert_callback_id").val() == 'callBack'){
		callBack();
	}
}

function set_menu_on(obj_id) {
	$("#"+obj_id).addClass("on");
}

function set_menu_reon(obj_id) {
	$("[id^=sub_]:.on" ).removeClass("on");
	$("#"+obj_id).addClass("on");
}

function resize_popup_height(div_id) {
	var height = ($("#" + div_id).height() + 23 + 4) > 100 ? ($("#" + div_id).height() + 23 + 4) : 100;
	parent.$("#sb-wrapper-inner").height(height);
	parent.$("#sb-wrapper").width( $("#" + div_id).width() + 4); // 2011.07.15 팝업내용 드래그시에 움직는 것 조정.
	parent.$("#sb-wrapper").height(height);
}

function on_date_blur(obj) {
    if (obj.value == "") {
    	return true;
    }

    // yyyymmdd → yyyy-mm-dd
    if (obj.value.length == 8) {
    	var y = obj.value.substring(0, 4);
    	var m = obj.value.substring(4, 6);
    	var d = obj.value.substring(6, 8);
    	obj.value = y + "-" + m + "-" + d;
    }

	if ( !check_date(obj) ) {
        // focus if validation failed
		obj.value = "";
        obj.focus();
    }
}

function check_date(obj) {
	var mo, day, yr;
    var entry = obj.value;
    var re = /\b\d{4}[\/-]\d{1,2}[\/-]\d{1,2}\b/;

    if (re.test(entry) ) {
        var delimChar = (entry.indexOf("/") != -1) ? "/" : "-";
        var delim1 = entry.indexOf(delimChar);
        var delim2 = entry.lastIndexOf(delimChar);
        yr = parseInt(entry.substring(0, delim1), 10);
        mo = parseInt(entry.substring(delim1+1, delim2), 10);
        day = parseInt(entry.substring(delim2+1), 10);
        var testDate = new Date(yr, mo-1, day);
        //alert(testDate);

        if (testDate.getDate() == day) {
            if (testDate.getMonth() + 1 == mo) {
                if (testDate.getFullYear() == yr) {
                    return true;
                } else {
                    open_alert("Alert", "There is a problem with the year entry.");
                }
            } else {
                open_alert("Alert", "There is a problem with the month entry.");
            }
        } else {
            open_alert("Alert", "There is a problem with the date entry.");
        }
    } else {
        open_alert("Alert", "Incorrect date format. Enter as yyyyMMdd.");
    }

    return false;
}

function check_xss(obj) {
	var val = $(obj).val();
	var re = /\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|alert+|script+|javascript+|document\.+|\.cookie+|xss\:+|\:expression|style\=+|background\:+/g;
	if (val.match(re) ) {
	    return false;
	} else {
		return true;
	}
}

function showModal(divID) {
    window.onscroll = function () { document.getElementById(divID).style.top = document.body.scrollTop; };
    document.getElementById(divID).style.display = "block";
    document.getElementById(divID).style.top = document.body.scrollTop;
}

function hideModal(divID) {
    document.getElementById(divID).style.display = "none";
}

/*
 * 선택된 행을 히든 처리하고 플래그를 삭제처리한다.
 *
 *            srcFormId
 *            radio Id
 */
var deleteRow = function(srcFormId, radioId){
	  var colCnt = 0;
	  var radioSelector = '#'+ srcFormId + ' :input[id='+radioId+']:checked';

	  if(recordStatus != 'undefined'){
		  // 기존 행은 업데이트 플래그 처리한다.
		  $(radioSelector).parents("tr").attr("deleteYn","1");
		  $(radioSelector).parents("tr").attr("recordStatus","U");
		  $(radioSelector).parents("tr").hide(); // 숨김처리한다.
	  } else {
		  // 신규 추가된 행은 뷰테이블에서 실제 remove 처리한다.
		  $(radioSelector).parents("tr").remove();
	  }
	  // 이 시점에서 renumbering을 처리한다.
	  //sortOrderedNumber();

      return ;
};
// 행삭제에 따른 정렬순서 재처리
var sortOrderedNumber = function(tableId, boolAscDesc){
	return ;
};

/*
 * 선택된 행의 상태값을 hide(02)/exporsure(01) 처리한다.
 *
 *            srcFormId üũ¹ڽº°¡ ǷȔµƠForm id ¹®Z¿­
 *            checkboxId üũ¹ڽºG id ¹®Z¿­
 */
var setHideExposure = function(srcFormId, radioId, boolTF){
	  var radioSelector = '#'+ srcFormId + ' :input[id='+radioId+']:checked';

	  if(true){  // 노출
		  $(radioSelector).parents("tr").attr("#statusCode",'01');
		  $(radioSelector).parents("tr").find("#statusCodeName").text("Exposure");
	  } else { // 숨김
		  $(radioSelector).parents("tr").attr("#statusCode",'02');
		  $(radioSelector).parents("tr").find("#statusCodeName").text("Hide");
	  }

      return ;
};


/************************************************************************/
/**     checkbox, radio 컨트롤 관련 공통 함수                           */
/************************************************************************/
/**
 *  Checkbox를 Enable/Disable 처리하는 공통함수
 *  idOrName : "id"/"name" 중 하나를 지정할 것[반드시 소문자로 기재할 것].
 *  checkboxNameId : checkbox의 id/name 속성명
 *  boolEnable     : true(Enable)/false(Disable)
 *  boolKeepData   : 기존 체크된 데이터를 보존할 것인지 여부 true(보존) / false(체크해제처리)
 *  Usage 1 : enableCheckbox("id", "realChannel", false, true);  // Disable처리 예제(데이터 보존)
 *  Usage 2 : enableCheckbox("id", "realChannel", true, true);  // enable처리 예제(데이터 보존)
 */
function manageCheckedControll(idOrName, checkboxNameId, boolEnable, boolKeepData){

	var selector = "input["+idOrName + "=" + checkboxNameId +"]"; // selector 구성

	if(!boolKeepData)
		$(selector).removeAttr('checked'); // 데이터 보존하지 않음

    if(boolEnable) {
		$(selector).removeAttr('disabled');     // 기존 disabled 삭제
	   	   $(selector).attr('enabled', 'enabled'); // Enable
	} else {
		$(selector).removeAttr('enabled');        // 기존 enabled 삭제
	   	   $(selector).attr('disabled', 'disabled'); // Disable 처리
	}
}
// 해당 id/name에 속한 checkbox/radio의 개수 또는 체크된 개수 반환
function getCountChecked(idOrName, checkboxNameId, boolChecked){
	var selector = "input["+ idOrName + "=" + checkboxNameId + "]";
	if(boolChecked)
		selector = selector + ":checked";
    return $(selector).length;
}
// checkbox/radio를 체크 선택/해제 시키는 wrapper 함수
function controllChecked(idOrName, checkboxNameId, boolChecked){
	var selector = "input["+ idOrName + "=" + checkboxNameId + "]";
	if(boolChecked){
	    $(selector).attr('checked','checked');
	} else {
		$(selector).removeAttr('checked');
	}
}
/**
 *  Checkbox를 제외한 일반 form element들을 Enable/Disable 처리하는 공통함수
 *  formId : form element가 속해있는 form의 id(페이지내에서 유일하다면 "" 문자열도 가능함)
 *  controllId : form element의 id명
 *  boolEnabled  : true(Enable)/false(Disable)
 *  boolKeepData   : 기존 체크된 데이터를 보존할 것인지 여부 true(보존) / false(비보존)
 */
function controlEnabled(formId, controllId, boolEnabled, boolKeepData){
	var selector = '';
	if(formId != 'undefined' && formId != null && formId != ""){
	    selector = "#"+ formId + " #" +  controllId;
	} else {
		selector = "#"+ controllId;
	}

    if(!boolKeepData){
		if($(selector).is("select")){
			var selectedSelector = selector + " option:selected";  // 현재 선택 option의 selector
			$(selectedSelector).removeAttr('selected'); // 데이터 보존하지 않음
		} else {
			$(selector).val("");
		}
	}

	if(boolEnabled){

		$(selector).removeAttr('disabled');
		$(selector).attr('enabled', 'enabled');
	} else {
		$(selector).removeAttr('enabled');
		$(selector).attr('disabled', 'disabled');
	}
}

/**
 * channel All 선택
 */
function checkBoxSelectAll(obj, chkListName) {

	var chkCannel = $('input[name=' + chkListName+']');

	if(obj.checked){
		for(var i = 0; i < chkCannel.length; i++ ){
			//$(chkCannel)[i].checked = true;
			chkCannel[i].checked = true;
		}
	}
	else { // all unchecked
		for (var i = 0; i < chkCannel.length; i++) {
			chkCannel[i].checked = false;
			//$(chkCannel)[i].checked = false;
		}
	}
}

/**
 * channel 개별 선택.
 */
function checkBoxSelect(checkAllId, checkListName){
	var channels = document.getElementsByName(checkListName);
	var isCheckAll = true;
	for (var i = 0; i < channels.length; i++) {
		if (channels[i].checked === false) {
			isCheckAll = false;
			break;
		}
	}
	if (isCheckAll) {
		document.getElementById(checkAllId).checked = true;
	}
	else {
		document.getElementById(checkAllId).checked = false;
	}
}

/**
 * Test Channel 선택/해제
 */
function chkTestChannel(chkObj, channelAllId, channelListName){
	var isChecked = chkObj.checked;
	var channels = document.getElementsByName(channelListName);
	if (isChecked) {
		// Channel 선택된 것을 해제.
		if(channels.length > 1){
			document.getElementById(channelAllId).checked = false;
		}
		$("input[name="+channelListName+"]:checkbox:checked").each(function() {
			this.checked = false;
		});
	}
}
/**
 * Channel 의 선택/해제
 */
function chkChannel(chkObj, testChannel) {
	var isChecked = chkObj.checked;
	if (isChecked) {
		// Test Channel 선택된 것을 해제.
		document.getElementById(testChannel).checked = false;
	}
}

/*****************checkbox, radio 컨트롤 관련 공통 함수 끝 **********************/


/***************** 시작종료일 체크 함수 시작**********************/

// 모든 공백제거(양끝, 중간)
function eraseSpace(val) {
	var space = /\s+/g;
	val = val.replace(space,"");
	return val;
}

// 공백여부
function isEmpty(val) {
	if (val == null || eraseSpace(val) == "") {
		return true;
	}
	return false;
}

// 좌측공백제거
function lTrim(val) {
	var space = /^\s*/;
	val = val.replace(space,"");
	return val;
}

// 우측공백제거
function rTrim(val) {
	var space = /\s*$/;
	val = val.replace(space,"");
	return val;
}

// 양쪽 끝 공백제거
function trim(val) {
	val = rTrim(lTrim(val));
	return val;
}

// 영문 숫자 조합
function isAlphanumberic(val) {
   var isStr = /^([a-zA-Z0-9]+)$/;   //영문 숫자 조합
    if( ! isStr.test(val) ){
       return false;
    }
    return true;
}



// 윤년여부
function isLeapYear(iYear) {
	if (((iYear % 4) == 0) && ((iYear % 100) != 0) || ((iYear % 400) == 0)) {
		return false;
	} else {
		return true;
	}
}

// 년월별 마지막 날
function getDaysInMonth(iYear, iMonth) {
	var tmpByte=0;

	if (iMonth == 1 || iMonth == 3 || iMonth == 5 || iMonth == 7 || iMonth == 8 || iMonth == 10 || iMonth == 12) {
		tmpByte = 31;
	} else if (iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11) {
		tmpByte = 30;
	} else if (iMonth == 2) {
		if (!isLeapYear(iYear)) {
			tmpByte = 29;
		} else {
			tmpByte = 28;
		}
	}

	return tmpByte;
}

// "0" 추가
function addZero(n) {
	  return n < 10 ? "0" + n : n;
}

// 날짜 정규식
var regDate = /^([1|2]\d{3})[\-\/\.]?(0[1-9]|1[012])[\-\/\.]?(0[1-9]|[12][0-9]|3[0-1])$/;

// 시작종료일 관련 함수
function checkStaEndDt(staObj, endObj) {

	var d = new Date();
	var staDt;
	var endDt;

	var toDay =
	    d.getFullYear() + '-' +
	    addZero(d.getMonth() + 1, 2) + '-' +
	    addZero(d.getDate(), 2);

	if (!isEmpty(eraseSpace(staObj.val()))) {
		if (staObj.val().match(regDate) == null) {
			open_message('Alter', 'admin.common.date.invalidFormat', "");
			return false;
		} else {
			if (parseInt(RegExp.$3, 10) < 0 || parseInt(RegExp.$3, 10) > getDaysInMonth(parseInt(RegExp.$1, 10), parseInt(RegExp.$2, 10))) {
				open_message('Alter', 'admin.common.date.invalidDate', "");
				return false;
			} else {
				staDt = RegExp.$1 + "-" + RegExp.$2 + "-" + RegExp.$3;

				if (staDt > toDay) {
					open_message('Alter',  'admin.common.date.todayEarly', "");
					return false;
				}
			}
		}
	}

	if (!isEmpty(eraseSpace(endObj.val()))) {
		if (endObj.val().match(regDate) == null) {
			open_message('Alter', 'admin.common.date.invalidFormat', "");
			return false;
		} else {
			if (parseInt(RegExp.$3, 10) < 0 || parseInt(RegExp.$3, 10) > getDaysInMonth(parseInt(RegExp.$1, 10), parseInt(RegExp.$2, 10))) {
				open_message('Alter', 'admin.common.date.invalidDate', "");
				return false;
			} else {
				endDt = RegExp.$1 + "-" + RegExp.$2 + "-" + RegExp.$3;
			}
		}
	}

	if (!isEmpty(eraseSpace(staObj.val())) && !isEmpty(eraseSpace(endObj.val()))) {
		if (staDt > endDt) {
			open_message('Alter', 'admin.common.date.todayEarly', "");
			return false;
		}
	}

	staObj.val(staDt);
	endObj.val(endDt);
	return true;

}
/***************** 시작종료일 체크 함수 끝**********************/


function post_to_url(path, params, method) {
    method = method || "post"; // Set method to post by default, if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);

        form.appendChild(hiddenField);
    }

    document.body.appendChild(form);    // Not entirely sure if this is necessary
    form.submit();
}

/**
 * 인풋 박스를 동적으로 생성한다
 * @param name : input box이름
 * @param type : input box타입
 * @param value : input box 값
 * @param parentId : input box삽입할 div id
 * @return
 * @author charles
 */
createInput = function(name, type, value, parentId){
	var input = document.createElement("input");
	input.id = name;
	input.name = name;
	input.type = type;
	input.value = value;
	$('#' + parentId).append(input);
};

/**
 * <pre>
 * 숫자인지 아닌지  검사한다.
 * 검사할 값이 "" 일 경우 true를 리턴하고 싶으면, space인수에 true를 넣으면 된다.
 * </pre>
 * @param digitChar 검사할 string
 * @param space ""일 때 허용여부(true||false)
 * @return boolean
 */
function checkDigitOnly(digitChar, space) {
	if(!space){
		if ( digitChar == null || digitChar=='' ){
    		return false ;
    	}
	}

	for(var i=0;i<digitChar.length;i++){
    	var c=digitChar.charCodeAt(i);
       	if( !(  0x30 <= c && c <= 0x39 ) ) {
       		return false ;
       	}
     }

    return true ;
};
/**
 * LPad 함수 구현(날짜앞에 0 같은거 채우기)
 * //digit : 원본문자
//size : 새로운 문자의 길이
//attatch : 왼쪽에 채워넣을 문자
 */
function LPad(digit, size, attatch) {
    var add = "";
    digit = digit.toString();

    if (digit.length < size) {
        var len = size - digit.length;
        for (i = 0; i < len; i++) {
            add += attatch;
        }
    }
    return add + digit;
};

/**
 * rowSpan기능구현 :   cellMerge('#mainTable',3); 와 같은 용법으로 jsp로드시 호출함.
 * tObj : 테이블 Id, n 맨앞부터 순서대로 rowSpan 하고싶은 갯수
 */
function cellMergeTest(tObj,n) {
	for (var i= n-1 ;i >= 0 ;i-- ) {
		var row = $("tr:last", tObj); //제일 마지막 row
		var cursorObj = $(row.children(':eq(' + i + ')')); //tr의  td
		var value = $(cursorObj).html(); //마지막 row의  td의 html
		var groupCount = 1;

		do {
			if( value == row.prev().children(':eq(' + i + ')').html() ) { //마지막 row 바로 위의 row의  td html
				cursorObj.remove(); //마지막 row의  td 제거
				groupCount++;

			} else {
				value = row.prev().children(':eq(' + i + ')').html();
				cursorObj.attr("rowspan", groupCount);
				groupCount = 1;
			}
			row = row.prev();
			cursorObj = row.children(':eq(' + i + ')');
		} while(row.length != 0);
	}
}

/**
 * rowSpan기능구현 :   cellMergeOne('#mainTable',3); 와 같은 용법으로 jsp로드시 호출함.
 * tObj : 테이블 Id, n 맨앞부터 순서대로 0부터 시작하여 rowSpan 하고싶은 위치
 * 여러개를 쓰고 싶은 경우 뒤에서부터 순서대로 기술하여야 제대로 작동함.
 *      cellMergeOne('#mainTable',7);
		cellMergeOne('#mainTable',6);
		cellMergeOne('#mainTable',4);
		cellMerge('#mainTable',3);
 */
function cellMerge(tObj,n) {
	if(tObj == undefined || n == undefined){
		return false;
	}
	if(n > 4){
		return false;
	} else if(n == 4){
		cellMerge4(tObj);
	} else if(n == 3){
		cellMerge3(tObj);
	} else if(n == 2){
		cellMerge2(tObj);
	} else if (n == 1){
		cellMergeOne(tObj,0);
	} else {
		return false;
	}
}

/**
 * rowSpan기능구현 :   cellMergeFromOne('#mainTable',6,2); 와 같은 용법으로 jsp로드시 호출함.
 * tObj : 테이블 Id, n : 맨앞부터 순서대로 0부터 시작하여 rowSpan 하고싶은 위치, fromPosition : rowSpan하기위해 참고해야 할 위치
 * 여러개를 쓰고 싶은 경우 뒤에서부터 순서대로 기술하여야 제대로 작동함.
 *      cellMergeFrom('#mainTable',7,2);
		cellMergeOne('#mainTable',6);
		cellMergeOne('#mainTable',4);
		cellMerge('#mainTable',3);
 */
function cellMergeFrom(tObj,n, fromPosition) {
	var row = $("tr:last", tObj); //제일 마지막 row
	var cursorObj = $(row.children(':eq(' + fromPosition + ')')); //tr의 비교 td
	var delObj = $(row.children(':eq(' + n + ')')); //tr의 지울대상 td
	var value = $(cursorObj).html(); //마지막 row의 td의 html
	var groupCount = 1;

	do {
		if( value == row.prev().children(':eq(' + fromPosition + ')').html() ) { //마지막 row 바로 위의 row의 td html
			delObj.remove(); //마지막 row의 td 제거
			groupCount++;

		} else {
			value = row.prev().children(':eq(' + fromPosition + ')').html();
			delObj.attr("rowspan", groupCount);
			groupCount = 1;
		}
		row = row.prev();
		cursorObj = row.children(':eq(' + fromPosition + ')');
		delObj = row.children(':eq(' + n + ')');
	} while(row.length != 0);
}

function cellMergeFrom3(tObj,n) {
	var row = $("tr:last", tObj); //제일 마지막 row
	var delObj = $(row.children(':eq(' + n + ')')); //tr의 지울대상 td
	var groupCount = 1;
	var firstObj = $(row.children(':eq(' + 0 + ')')); //tr의  td
	var secondObj = $(row.children(':eq(' + 1 + ')')); //tr의  td
	var thirdObj = $(row.children(':eq(' + 2 + ')')); //tr의 td
	var firstValue = $(firstObj).html(); //첫번째   td의 html
	var secondValue = $(secondObj).html(); //두번째   td의 html
	var thirdValue = $(thirdObj).html(); //세번째   td의 html


	do {
		if( firstValue == row.prev().children(':eq(' + 0 + ')').html()
				&& secondValue == row.prev().children(':eq(' + 1 + ')').html()
				&& thirdValue == row.prev().children(':eq(' + 2 + ')').html() ) { //마지막 row 바로 위의 row의  td html
			delObj.remove(); //마지막 row의 td 제거
			groupCount++;

		} else {
			secondValue = row.prev().children(':eq(' + 1 + ')').html();
			firstValue = row.prev().children(':eq(' + 0 + ')').html();
			thirdValue = row.prev().children(':eq(' + 2 + ')').html();
			delObj.attr("rowspan", groupCount);
			groupCount = 1;
		}
		row = row.prev();
		firstObj = row.children(':eq(' + 0 + ')');
		secondObj = row.children(':eq(' + 1 + ')');
		thirdObj = row.children(':eq(' + 2 + ')');
		delObj = row.children(':eq(' + n + ')');
	} while(row.length != 0);
}

function cellMergeOne(tObj,n) {
	var row = $("tr:last", tObj); //제일 마지막 row
	var cursorObj = $(row.children(':eq(' + n + ')')); //tr의  td
	var value = $(cursorObj).html(); //마지막 row의  td의 html
	var groupCount = 1;

	do {
		if( value == row.prev().children(':eq(' + n + ')').html() ) { //마지막 row 바로 위의 row의  td html
			cursorObj.remove(); //마지막 row의  td 제거
			groupCount++;

		} else {
			value = row.prev().children(':eq(' + n + ')').html();
			cursorObj.attr("rowspan", groupCount);
			groupCount = 1;
		}
		row = row.prev();
		cursorObj = row.children(':eq(' + n + ')');
	} while(row.length != 0);
}

function cellMerge3(tObj) {

	var row = $("tr:last", tObj); //제일 마지막 row
	var firstObj = $(row.children(':eq(' + 0 + ')')); //tr의  td
	var secondObj = $(row.children(':eq(' + 1 + ')')); //tr의 지울대상 td
	var thirdObj = $(row.children(':eq(' + 2 + ')')); //tr의 지울대상 td
	var firstValue = $(firstObj).html(); //첫번째   td의 html
	var secondValue = $(secondObj).html(); //두번째   td의 html
	var thirdValue = $(thirdObj).html(); //세번째  td의 html
	var groupCount = 1;

	do {
		if( firstValue == row.prev().children(':eq(' + 0 + ')').html()
				&& secondValue == row.prev().children(':eq(' + 1 + ')').html()
				&& thirdValue == row.prev().children(':eq(' + 2 + ')').html() ) { //마지막 row 바로 위의 row의  td html
			thirdObj.remove(); //마지막 row의  td 제거
			groupCount++;

		} else {
			secondValue = row.prev().children(':eq(' + 1 + ')').html();
			firstValue = row.prev().children(':eq(' + 0 + ')').html();
			thirdValue = row.prev().children(':eq(' + 2 + ')').html();
			thirdObj.attr("rowspan", groupCount);
			groupCount = 1;
		}
		row = row.prev();
		firstObj = row.children(':eq(' + 0 + ')');
		secondObj = row.children(':eq(' + 1 + ')');
		thirdObj = row.children(':eq(' + 2 + ')');
	} while(row.length != 0);


	cellMerge2(tObj);
}

function cellMerge2(tObj) {
		//두번째 Rowspan
		var row = $("tr:last", tObj); //제일 마지막 row
		var firstObj = $(row.children(':eq(' + 0 + ')')); //tr의  td
		var secondObj = $(row.children(':eq(' + 1 + ')')); //tr의 지울대상 td
		var firstValue = $(firstObj).html(); //첫번째  td의 html
		var secondValue = $(secondObj).html(); //두번째  td의 html
		var groupCount = 1;

		do {
			if( firstValue == row.prev().children(':eq(' + 0 + ')').html() && secondValue == row.prev().children(':eq(' + 1 + ')').html() ) { //마지막 row 바로 위의 row의  td html
				secondObj.remove(); //마지막 row의  td 제거
				groupCount++;

			} else {
				secondValue = row.prev().children(':eq(' + 1 + ')').html();
				firstValue = row.prev().children(':eq(' + 0 + ')').html();
				secondObj.attr("rowspan", groupCount);
				groupCount = 1;
			}
			row = row.prev();
			firstObj = row.children(':eq(' + 0 + ')');
			secondObj = row.children(':eq(' + 1 + ')');
		} while(row.length != 0);

		//첫번째 Rowspan
		cellMergeOne(tObj,0);
}

function cellMerge4(tObj) {
	var row = $("tr:last", tObj); //제일 마지막 row
	var firstObj = $(row.children(':eq(' + 0 + ')')); //tr의  td
	var secondObj = $(row.children(':eq(' + 1 + ')')); //tr의 td
	var thirdObj = $(row.children(':eq(' + 2 + ')')); //tr의 td
	var fourthObj = $(row.children(':eq(' + 3 + ')')); //tr의 td
	var firstValue = $(firstObj).html(); //첫번째   td의 html
	var secondValue = $(secondObj).html(); //두번째  td의 html
	var thirdValue = $(thirdObj).html(); //세번째  td의 html
	var fourthValue = $(thirdObj).html(); //네번째  td의 html
	var groupCount = 1;

	do {
		if( firstValue == row.prev().children(':eq(' + 0 + ')').html()
				&& secondValue == row.prev().children(':eq(' + 1 + ')').html()
				&& thirdValue == row.prev().children(':eq(' + 2 + ')').html()
				&& fourthValue == row.prev().children(':eq(' + 3 + ')').html() ) { //마지막 row 바로 위의 row의  td html
			fourthObj.remove(); //마지막 row의  td 제거
			groupCount++;

		} else {
			secondValue = row.prev().children(':eq(' + 1 + ')').html();
			firstValue = row.prev().children(':eq(' + 0 + ')').html();
			thirdValue = row.prev().children(':eq(' + 2 + ')').html();
			fourthValue = row.prev().children(':eq(' + 3 + ')').html();
			fourthObj.attr("rowspan", groupCount);
			groupCount = 1;
		}
		row = row.prev();
		firstObj = row.children(':eq(' + 0 + ')');
		secondObj = row.children(':eq(' + 1 + ')');
		thirdObj = row.children(':eq(' + 2 + ')');
		fourthObj = row.children(':eq(' + 3 + ')');
	} while(row.length != 0);

	cellMerge3(tObj);
}

/**
 * 현재일 반환 (yyyy-mm-dd)
 *
 * @param offset
 * @returns today
 */
function getCurrDate() {
	var d = new Date();
	return d.getFullYear() + '-' + LPad(d.getMonth() + 1, 2, '0') + '-' + LPad(d.getDate(), 2, '0');
}

/**
 * 현재일에서 계산된 일자를 반환 (yyyy-mm-dd)
 *
 * @param offset
 * @returns today + offset
 */
function getOffsetDate(offset) {
	var d = new Date();
	var addDate = new Date(d.getTime() + (offset * 1000 * 60 * 60 * 24));
	return addDate.getFullYear() + '-' + LPad(addDate.getMonth() + 1, 2, '0') + '-' + LPad(addDate.getDate(), 2, '0');
}

/**
 * 말줄임 (20 bytes + '..')
 *
 * @param strValue
 * @param len (생략시 20자로 고정)
 * @returns string
 */
function cutString(strValue, len) {
	if (strValue == null || strValue == undefined || typeof strValue != 'string') {
		strValue = "";
	} else {
		if (len == null || len == undefined) {
			len = 20;
		}
		if (strValue.length > len) {
			strValue = strValue.substring(0, len) + "..";
		}
	}
	return strValue;
}


/**
 * 숫자를 한글로
 *
 * @param num
 * @returns string
 */
function viewKorean(num) {

    num = ("" + num).replace(/,/g, "");

    if (num == "0") return "영";

    var hanA = new Array("", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구", "십");
    var danA = new Array("", "십", "백", "천", "", "십", "백", "천", "", "십", "백", "천", "", "십", "백", "천");
    var result = "";
    for (var i = 0; i < num.length; i++) {
        str = "";
        han = hanA[num.charAt(num.length - (i + 1))];
        if (han != "") str += han + danA[i];
        if (i == 4) str += "만";
        if (i == 8) str += "억";
        if (i == 12) str += "조";

        result = str + result;
    }
    if(result.indexOf('억만') > -1) {

        result = result.replace('만','');
    }
    if(result.indexOf('조억') > -1) {

        result = result.replace('억','');
    }

    return result;
}


