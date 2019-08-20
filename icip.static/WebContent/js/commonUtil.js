/************************************************
전체 업무에 적용되는 기능 정의
*************************************************/
var holidays= {};
var repeHolidays = {};
var selHoliDayPeriod = "ALL";   //휴일조회기준 ALL - 전체를 한번조회한다(Default) , YEAR - 년도변경시에만 조회되며 기조회된건은 조회요청안함
/*
 *  Xecure 인증서 로그인시 전용인증서를 보일지 전체 인증서를 보일지 선택하는 변수 로 전역 선언
 *  true 기본값은 모든 인증서 임. false 전용인증서 로드함 (업무에서 false 를 설정)
 * */
var isCertAll = true;
var selectedHolidayYear = new Array();

var progressCnt = 0;        //프로그레스바 컨트롤 Count
var placeholderIE = false;  //placeholderIE9여부
var placeholderUse = true;  //placeholder사용여부 : IE9에서만 적용된다.
var placeholderObj = [];    //placeholder적용된 Obj
var commonUtil= {
        progressLock : false,
        DIALOG_TYPE_RAW: 'WINDOW_ALERT',
        DIALOG_TYPE_UI:  'JQUERY_UI',
        DIALOG_TYPE_UI_ID: 'commonUtil_dialog',
        DIALOG_TYPE_UI_ALERT_TITLE: '알림',

        init: function() {
            $.extend(this, {
                alertType: this.DIALOG_TYPE_RAW,
                alertModal: false
            });

            _cmmutils_init_ajax();
            _cmmutils_init_validator();
            _cmmutils_init_datapicker();
        },

        /*************************************************************************************************
         * DatePicker를 모든 날짜를 선택 가능하도록 reset 한다.
         *
         **************************************************************************************************/
        resetToNoramlDatapicker: function() {
            _cmmutils_reset_to_normal_datapicker();
        },

        /*************************************************************************************************
        설명   : 공통 ajax Call (일반적인 ajax call 일때 이용)
        입력값 : url - 호출될 url (필수값)
                 data - 호출시 전송될 입력 파라메터  (필수값-공백 가능)
                 callback - 결과 전송시 호출될 callback (필수값)
                 methodType - post나 get등 전송 type (옵션값-기본은 post)
                 async - 동기화여부. true(비동기전송)/false(동기전송) (옵션값-기본은 true)
                 cache - cache 여부. true(cache 함)/false(cache 안함)  (옵션값-기본은 false)
        **************************************************************************************************/
        ajaxCall : function(url, data, callback,methodType,async,cache,corssDomain){
            if(methodType != 'post' && methodType != 'get' ){
                methodType = 'post';
            }
            if(async != true && async != false){
                async = true;
            }
            if(cache != true  && cache != false){
                cache = false;
            }
            var option = {
                    url: url,
                    data : (data?data:""),
                    type : methodType,
                    async : async,
                    cache : cache,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function(result) {
                        if(result.baseException) {
                            var exception = result.baseException;
                            commonUtil.alert(exception.message);
                        }
                        else {
                            if(callback) {
                                callback(result);
                            }
                        }
                        try{
                        	if(typeof getLastAccessedTime != "undefined"){
                        		getLastAccessedTime();
                        	}
                        }catch(e){
                        	//응답없음
                        	var noidea = 0;
                        }
                    },
                    error : function(req, status, error) {
                        var errorMsg = '시스템 에러 발생하였습니다.';
                        if(req.responseJSON && req.responseJSON.baseException) {
                            errorMsg = req.responseJSON.baseException.message;
                        }

                        commonUtil.alert(errorMsg);
                    }
                };

            if(corssDomain) option.crossDomain = true;
            $.ajax(option);
        },

        /*************************************************************************************************
        설명   : 공통 ajaxSubmit Call (첨부파일이 잇을시에 사용)
        입력값 : formId - form Id  (필수값)
                 url - 호출될 url (필수값)
                 callback - 결과 전송시 호출될 callback (필수값)
                 methodType - post나 get등 전송 type (옵션값-기본은 post)
        **************************************************************************************************/
        //ajaxSubmitCall : function(formId,url,callback,methodType,corssDomain,headers){
        ajaxSubmitCall : function(formId,url,callback,methodType,corssDomain){            
            if(methodType != 'post' && methodType != 'get' ){
                methodType = 'post';
            }
            var option = {
                    url: url,
                    type : methodType,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function(result) {
                        if(result.baseException) {
                            var exception = result.baseException;
                            commonUtil.alert(exception.message);
                        }
                        else {
                            if(callback) {
                                callback(result);
                            }
                        }
                        try{
                        	if(typeof getLastAccessedTime != "undefined"){
                        		getLastAccessedTime();
                        	}
                        }catch(e){
                        	//응답없음
                        	var noidea = 0;
                        }
                    },
                    error : function(req, status, error) {
                        var errorMsg = '일시적 장애가 발생하였습니다.';
                        if(req.responseJSON && req.responseJSON.baseException) {
                            errorMsg = req.responseJSON.baseException.message;
                        }

                        commonUtil.alert(errorMsg);
                    }
                };

            if( $("#"+formId).find("input[name='_ajax_heder']").length == 0) {
                $("#"+formId).prepend("<input type='hidden' name='_ajax_heder' value='true'/>");
            }
            if(corssDomain) option.crossDomain = true;
            //if(headers!="") option.headers = headers;
            $('#'+formId).ajaxSubmit(option);
        },

        /*************************************************************************************************
        설명   : 이미지 미리보기
        입력값 : fileObj - 이미지를 올릴 file object 값 (필수값)
                 width - 이미지 미리보기 width   (옵션값- 기본값 80)
                 height - 이미지 미리보기 height (옵션값- 기본값 80)
        **************************************************************************************************/
        imgPreview : function(thisObj, width,height) {

            imgObj = $('#'+thisObj.id+'Img').get(0);

            if(width == null || width == '' || width == undefined){
                width = 80;
            }
            if(height == null || height == '' || height == undefined){
                height = 80;
            }

            if (window.FileReader) {

                var reader = new FileReader();
                reader.onload = function(e){
                    $(imgObj).attr("src" , e.target.result);
                    $(imgObj).attr("width" , width);
                    $(imgObj).attr('height', height);

                };
                reader.readAsDataURL(thisObj.files[0]);
            }else {

                $(imgObj).attr("src" , "");

                thisObj.select();
                thisObj.blur();

                var imgSrc = document.selection.createRange().text;

                $(imgObj).attr("width" , width);
                $(imgObj).attr('height', height);

                if(imgObj.filters) {
                    imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";
                } else {
                    $(imgObj).attr("src", imgSrc);
                }
            }
        },

        /*************************************************************************************************
        설명   : 파일 객체 초기화 및 이미지 삭제
        입력값 : fileId - 초기화 할 file 객체 Id (필수값)
        **************************************************************************************************/
        imgRemove : function(fileId) {
             if ( window.FileReader ) {
                 $('#'+fileId+'Img').attr('src', '/images/sample/app_def_img05.gif');
             }else{
                 imgObj = $('#'+fileId+'Img').get(0);

                 $(imgObj).attr("src" , "/images/sample/app_def_img05.gif");
             }
             $('#'+fileId).replaceWith($('#'+fileId).clone(true));

        },

        /*************************************************************************************************
        설명   : 미리보기된 이미지 메인 이미지 화면으로 보기
        입력값 : imgObj - 크게 볼 이미지 객체 (필수값)
                 imgMainId - 메인 이미지 Id (옵션값- 기본값 imgMain)
        **************************************************************************************************/
        imgMainPreview : function(fileId,imgMainId) {
             if(imgMainId == null || imgMainId == '' || imgMainId == undefined){
                 imgMainId = 'imgMain';
             }
             var imgObj = $('#'+fileId+'Img');
             var imgMainObj = $('#'+imgMainId).get(0);
             var fileObj = $("#" + fileId);

             if ( window.FileReader ) {
                 $(imgMainObj).attr("src" , $(imgObj).attr("src"));
             }else {
                 fileObj.select();
                 fileObj.blur();
                 var imgSrc = document.selection.createRange().text;

                 if ( imgSrc == null || imgSrc == '' || imgSrc == undefined){
                     imgSrc = "/images/sample/app_def_img05.gif";
                     $(imgMainObj).attr("src" , imgSrc);
                 }else{
                     $(imgMainObj).attr("src" , "");

                     if(imgMainObj.filters) {
                         imgMainObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='file://"+ imgSrc + "')";
                     } else {
                         $(imgMainObj).attr("src" , imgSrc);
                     }
                 }
             }

        },

        /*************************************************************************************************
        설명   : 파일 사이즈를 리턴한다.
        입력값 : fileId - file 객체 Id (필수값)
        **************************************************************************************************/
        getFileSize : function(fileId) {
            var obj = document.getElementById(fileId);
            var size = 0;
            if(_gfn_isiE9()) {
                return size;
            }

            if(obj && obj.files.length > 0) {
                size = obj.files[0].size;
            }

            return size;
        },

        /*************************************************************************************************
        설명   : INPUT TYPE 가 TEXT 거나 TEXTAREA 인경우 validate rule을 기본으로 XSS로 해준다
        입력값 : formId - 체크할 form ID명
        **************************************************************************************************/
        setXss : function(formId){
            $('#' + formId + ' input[type="text"], #' + formId + ' textarea').each(function(){
                $(this).rules('add', {
                    xss: true
                });
            });
        },

        /*************************************************************************************************
        설명   : Progress를 표시한다.
        입력값 :
        **************************************************************************************************/
        showProgress : function(){
            var loadingHtml = '<div id="loading" class="loadingPlate"><img SRC="/images/loading/ajax_loding.gif" width="184px" height="108px"></div>';
            if($("#loading").length > 0) {
                $("#loading").show();
            } else{
                $("body").append(loadingHtml);
                $("#loading").show();
            }
        },

        /*************************************************************************************************
        설명   : Progress를 감춘다.
        입력값 :
        **************************************************************************************************/
        hideProgress : function(){
            $("#loading").remove();
        },

        /*************************************************************************************************
        설명   : Back Space키로 브라우저 Navigation이 뒤로 가는 것을 방지한다.
        입력값 :
        **************************************************************************************************/
        preventBackSpace : function() {
            if(!$(document).on) return;
            $(document).on('keydown', function (event) {
                var doPrevent = true;

                if(event.which === 8) {
                    var obj = event.target;
                    var tagName = obj.tagName.toUpperCase();
                    if( tagName === "INPUT") {
                        var type = obj.type.toUpperCase();
                        if( type ==="TEXT" || type === "PASSWORD" || type === "FILE") {
                            doPrevent = obj.readOnly || obj.disabled;
                        }
                    }
                    if( tagName === "TEXTAREA") {
                        doPrevent = obj.readOnly || obj.disabled;
                    }

                    if( doPrevent ) {
                        event.preventDefault();
                    }
                }
            });
        },

        /*************************************************************************************************
        설명   : 로그분석을 위해 쿠키에 값을 설정한다.
        입력값 : {USR_NO: 유저번호, CLTR_NO: 물건번호, PBCT_CDTN_NO: 공매조건번호}
                 ex) commonUtil.writeLogData({USR_NO: "1111", CLTR_NO: "2222", PBCT_CDTN_NO: "333"});
                 ex) commonUtil.writeLogData(); //쿠키를 삭제하는 경우
        **************************************************************************************************/
        writeLogData : function(args) {
            var expires = "";
            if(!args) {
                args = {USR_NO: "", CLTR_NO: "", PBCT_CDTN_NO: ""};
                var expire = new Date();
                expire.setDate(expire.getDate() - 1);
                expires = ";expires=" + expire.toGMTString();
            }
            if(!args["USR_NO"]) args["USR_NO"] = "";
            if(!args["CLTR_NO"]) args["CLTR_NO"] = "";
            if(!args["PBCT_CDTN_NO"]) args["PBCT_CDTN_NO"] = "";

            document.cookie = "USR_NO=" + args["USR_NO"] + ";path=/" + expires;
            document.cookie = "CLTR_NO=" + args["CLTR_NO"] + ";path=/" + expires;
            document.cookie = "PBCT_CDTN_NO=" + args["PBCT_CDTN_NO"] + ";path=/" + expires;
        },

        /*************************************************************************************************
        설명   : 브라우저 Alert 랩핑 함수
        입력값 : msg - 출력메시지, title - 타이틀, alertType - (WINDOW_ALERT:기본값, JQUERY_UI) , modal : modal여부
        **************************************************************************************************/
        alert: function (msg, title, alertType, modal) {
            if(typeof alertType === "undefined" || alertType === null ) {
                alertType = this.alertType;
            }

            if(alertType == this.DIALOG_TYPE_UI) {
                if(typeof title === "undefined" || title === null ) {
                    title = this.DIALOG_TYPE_UI_ALERT_TITLE;
                }

                if(typeof modal === "undefined") {
                    modal = this.alertModal;
                }

                var alertSel = "#" + this.DIALOG_TYPE_UI_ID;
                if($(alertSel).length == 0) {
                    $("body").append("<div id='" + this.DIALOG_TYPE_UI_ID + "'></div>");
                }

                $(alertSel).text(msg);
                $(alertSel).dialog({
                    modal: modal,
                    title: title
                });
            }
            else if(this.alertType == this.DIALOG_TYPE_RAW) {
                alert(msg);
            }
        },

        /*************************************************************************************************
        설명   : 브라우저 open 랩핑 함수
        입력값 : url - 호출경로, title - 타이틀, option - pop option , frm - form,  modal : modal여부
        **************************************************************************************************/
        open: function (url, title, option ,frm,site) {
            window.open(url,title, option);
        },
        /*************************************************************************************************
        설명   : 쿠키값을 취득한다.
        입력값 : cname - 쿠기명
        **************************************************************************************************/
        getCookie: function(cname) {
            var name = cname + "=";
            var decodedCookie = document.cookie;
            var ca = decodedCookie.split(';');
            for(var i = 0; i <ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') {
                    c = c.substring(1);
                }
                if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        },
        /*************************************************************************************************
        설명   : icipnet sso page를 호출하여 쿠키를 생성한다.
        출력값 : c_member_id - 쿠기값
        **************************************************************************************************/
        makeNetSsoCookie: function() {
            var _c_member_id = commonUtil.getCookie("c_member_id");
            var log = "\nbefore makeNetSsoCookie()._c_member_id : " +_c_member_id;

            _c_member_id = $.trim(_c_member_id);
            if(_c_member_id == "") {
                var _icipSsoicipnetUrl = $("#_icipSsoicipnetUrl");
                if(_icipSsoicipnetUrl.length == 0) {
                    var _icipSsoicipnetUrl = $('body').append('<iframe id="_icipSsoicipnetUrl" src="http://login.icip.net/SSO/index.jsp" style="width: 0px; height: 0px;"></ifrmae>');
                }
            }
            _c_member_id = commonUtil.getCookie("c_member_id");

            log = log + "\nafter makeNetSsoCookie()._c_member_id : " +_c_member_id;
            if(window.console) {
                //console.log(log);
            }

            return _c_member_id;
        }
};

commonUtil.init();

/*************************************************************************************************
설명   :브라우저명, 버전
**************************************************************************************************/
var browserDetect = {
    init: function () {
        this.browser = this.searchString(this.dataBrowser) || "Other";
        this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "Unknown";

        return this;
    },
    searchString: function (data) {
        for (var i = 0; i < data.length; i++) {
            var dataString = data[i].string;
            this.versionSearchString = data[i].subString;

            if (dataString.indexOf(data[i].subString) !== -1) {
                return data[i].identity;
            }
        }
    },
    searchVersion: function (dataString) {
        var index = dataString.indexOf(this.versionSearchString);
        if (index === -1) {
            return;
        }

        var rv = dataString.indexOf("rv:");
        if (this.versionSearchString === "Trident" && rv !== -1) {
            return parseFloat(dataString.substring(rv + 3));
        } else {
            return parseFloat(dataString.substring(index + this.versionSearchString.length + 1));
        }
    },

    dataBrowser: [
        {string: navigator.userAgent, subString: "OPR", identity: "Opera"},
        {string: navigator.userAgent, subString: "MSIE", identity: "Explorer"},
        {string: navigator.userAgent, subString: "Trident", identity: "Explorer"},
        {string: navigator.userAgent, subString: "Firefox", identity: "Firefox"},
        {string: navigator.userAgent, subString: "Chrome", identity: "Chrome"},
        {string: navigator.userAgent, subString: "Safari", identity: "Safari"}
    ]

};

commonUtil.browserDetect = browserDetect.init();

/**
 * Ajax 호출 초기화
 */
function _cmmutils_init_ajax() {

    //Ajax 호출시 Request Header, Progress 설정
    $.ajaxSetup({
        beforeSend: function(xhr) {
            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");
            // 헤더값이 존재할때만 셋팅
            if(header) {
                xhr.setRequestHeader(header,token);
            }

            xhr.setRequestHeader("AJAX", true);
            if(!commonUtil.progressLock) {
                commonUtil.showProgress();
            }
        },
        complete: function() {
            if(!commonUtil.progressLock) {
                commonUtil.hideProgress();
            }
        }
    });
}

/**
 * Validator 초기화
 */
function _cmmutils_init_validator() {
    jQuery.validator.setDefaults({
        onkeyup:false,
        onclick:false,
        onfocusout:false,
        onsubmit:false,
        showErrors:function(errorMap, errorList){
            //if(!$.isEmptyObject(errorList)){
            if(errorList && (errorList.length > 0)) {
                commonUtil.alert(errorList[0].message, "입력체크", commonUtil.DIALOG_TYPE_RAW, true);
            }
        },
        invalidHandler: function(form, validator) {
            var errors = validator.numberOfInvalids();
            if (errors) {
                validator.errorList[0].element.focus();
            }
        }
    });

    /* Validation 기본 형식 */
    jQuery.validatac.dgu = {
        alphanumericFormat: /^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]+$/,
        dateFormat: /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/,
        dateDelim: "-",
        //xssFormat: /\<|\>|\"|\%|\&|\+|alert+|(\<|\<\/)script+|javascript+|document\.+|\.cookie+|xss\:+|\:expression|style\=+|background(\:|\.)+|\.\/+|\.\.\/+/g
        xssFormat: /<script([^>]*)>|<\/script\s*>/g,
        xssEditorFormat: /<script([^>]*)>|<\/script\s*>|<form([^>]*)>|<\/form\s*>|<input([^>]*)>|<\/input\s*>|<textarea([^>]*)>|<\/textarea\s*>/g
    };

    jQuery.validator.addMethod("alphanumeric", function(value, element) {
        var alphanumericFormat = jQuery.validatac.dgu.alphanumericFormat;
        return this.optional(element) || alphanumericFormat.test(value);
    });

    jQuery.validator.addMethod("date", function(value, element) {
        if($.trim(value) == "") {
            return true;
        }

        var dateFormat = jQuery.validatac.dgu.dateFormat;
        var dateDelim = jQuery.validatac.dgu.dateDelim;
        var chkDate = dateFormat.test(value);
        if (chkDate == true) {
            var cDate = value.split(dateDelim).join("");
            var yr = parseInt(cDate.substring(0, 4), 10);
            var mo = parseInt(cDate.substring(4, 6), 10);
            var day = parseInt(cDate.substring(6, 8), 10);
            var testDate = new Date(yr, mo-1, day);
            if (testDate.getDate() != day || testDate.getMonth() + 1 != mo || testDate.getFullYear() != yr) {
                chkDate = false;
            }
        }
        return chkDate;
    });

    jQuery.validator.addMethod("xss", function(value, element) {
        var xssFormat = jQuery.validatac.dgu.xssFormat;

        if (value.match(xssFormat) ) {
            return false;
        } else {
            return true;
        }
    });

    jQuery.validator.addMethod("xssEditor", function(value, element) {
        var xssEditorFormat = jQuery.validatac.dgu.xssEditorFormat;

        if (value.match(xssEditorFormat) ) {
            return false;
        } else {
            return true;
        }
    });

    jQuery.validator.addMethod("minbytelength", function(value, element, param) {
        var len = _gfn_getByteLen(value);
        return len >= param;
    });

    jQuery.validator.addMethod("maxbytelength", function(value, element, param) {
        var len = _gfn_getByteLen(value);
        return len <= param;
    });

    jQuery.validator.addMethod("fixlength", function(value, element, param) {
        var length = $.isArray( value ) ? value.length : jQuery.validator.prototype.getLength( value, element );
        return jQuery.validator.prototype.optional( element ) || length == param;
    });

    jQuery.validator.addMethod("fixbytelength", function(value, element, param) {
        var len = _gfn_getByteLen(value);
        return len == param;
    });

    jQuery.validator.prototype.elements = function() {
        var validator = this,
        rulesCache = {};

        return $( this.currentForm )
        .find( "input, select, textarea" )
        .not( ":submit, :reset, :image, [disabled]" )
        .not( this.settings.ignore )
        .filter( function() {
            if ( !this.name && validator.settings.debug && window.console ) {
                console.error( "%o has no name assigned", this );
            }

            //IE9 placeholder 처리
            if (placeholderIE == true){
                if ($(this).attr("placeholder") && $(this).attr("placeholder") != "" && $(this).css("color") == "rgb(166, 166, 166)") {
                    $(this).val("");
                }
            }

            if ( this.name in rulesCache || !validator.objectLength( $( this ).rules() ) ) {
                return false;
            }

            rulesCache[ this.name ] = true;
            return true;
        });
    };

    jQuery.validator.prototype.defaultMessage= function( element, method ) {
        var str = "";
        if (element.title && element.title != "") str = element.title;
        else str = $('label[for='+element.id+']').text();
        return this.findDefined(this.customMessage(element.name, method), (str != "" ? str + '은(는) ' :  '') + $.validator.messages[ method ]);
    };
}

/**
 * datepicker 초기화
 */
function _cmmutils_init_datapicker() {

    if(!$.datepicker) {
        return;
    }

    date = new Date();
    var firstYn = true;

    $.datepicker.setDefaults({

        closeText: '닫기',
        prevText: '이전달',
        nextText: '다음달',
        currentText: '오늘',
        showOn: "both",
        changeMonth: true,
        changeYear: true,
        monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)','7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        weekHeader: 'Wk',
        dateFormat: 'yy-mm-dd',
        showMonthAfterYear: true ,
        buttonImageOnly: true,
        showOtherMonths:true, /* 이전/다음달의 여분 날짜 보이기 */
        buttonText: "달력",
        beforeShow: function() {
            if(holidays.initFlag == undefined && selHoliDayPeriod == 'ALL') {
                setHoliday_Datepicker();
            }
            return true;
        },
        beforeShowDay: function(day) {
            if ( firstYn && selHoliDayPeriod == 'YEAR') {
                selectedHolidaysByYear(day.getFullYear());
                firstYn = false;
            }

            var result;
            var holiday = holidays[$.datepicker.formatDate("yymmdd",day )]; //표시날이 휴일인지 체크

            //반복휴일 여부
            if (!holiday) {
                holiday = repeHolidays[$.datepicker.formatDate("mmdd",day )]; //표시날이 휴일인지 체크
            }

            if (holiday) {
                result =  [false, "date-holiday"+holiday.type, holiday.title];
            } else {
              switch (day.getDay()) {
                case 0: // 일요일?
                  result = [false, "date-sunday"];
                  break;
                case 6: // 토요일?
                  result = [false, "date-saturday"];
                  break;
                default:
                  result = [true, ""];
                  break;
              }
            }

            if ( selHoliDayPeriod == 'YEAR'){
                if ( day.getDate() == getLastDayOfYearAndMonth(day.getFullYear() , day.getMonth())){
                    firstYn = true;
                }
            }
            return result;
        }
    });

}

/**
 * DatePicker를 모든 날짜를 선택 가능하도록 reset
 */
function _cmmutils_reset_to_normal_datapicker() {

    if(!$.datepicker) {
        return;
    }

    $.datepicker.setDefaults({

        closeText: '닫기',
        prevText: '이전달',
        nextText: '다음달',
        currentText: '오늘',
        showOn: "both",
        changeMonth: true,
        changeYear: true,
        monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)','7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        weekHeader: 'Wk',
        dateFormat: 'yy-mm-dd',
        showMonthAfterYear: true ,
        buttonImageOnly: true,
        showOtherMonths:true, /* 이전/다음달의 여분 날짜 보이기 */
        buttonText: "달력",
        beforeShowDay: function(day) {

            switch (day.getDay()) {
                case 0: // 일요일?
                  result = [true, "date-sunday"];
                  break;
                case 6: // 토요일?
                  result = [true, "date-saturday"];
                  break;
                default:
                  result = [true, ""];
                  break;
            }

            return result;
        }
    });

}

//해당년월의 마지막날 구하기
function getLastDayOfYearAndMonth(year, month)
{
    return(new Date((new Date(year, month + 1, 1)) - 1)).getDate();
}

//휴일 조회
function setHoliday_Datepicker(searchYear){

    if ( searchYear == undefined) {
        if ( selHoliDayPeriod == 'YEAR') {
            searchYear = date.getFullYear();
        }else{
            searchYear = "";
        }
    }

    $.ajax({
        url : "/op/cmm/selectHoliDay.do",
        type : "post",
        cache : true,
        data : {
            "searchYear" : searchYear
        },
        async : false,
        dataType: "json",
        success: function(result) {
            var holiList = result.holidayList;
            holidays.initFlag = true;

            $.each(holiList , function(idx){
                if(holiList[idx].repeYn == "N") {
                    holidays[(holiList[idx].yr + holiList[idx].mon + holiList[idx].dy)] = {type:0, title:holiList[idx].rmk};
                } else {
                    repeHolidays[(holiList[idx].mon + holiList[idx].dy)] = {type:0, title:holiList[idx].rmk};
                }
            });

            if ( selHoliDayPeriod == 'YEAR'){
                selectedHolidayYear.push(searchYear);
            }
        }
    });
}

//기존 조회여부 체크 - 년도별
function selectedHolidaysByYear(searchYear){
    if ( selectedHolidayYear.indexOf(searchYear) >= 0 ){
        return false;
    }
    else{
        setHoliday_Datepicker(searchYear);
        return true;
    }
}

/**
 * 화면에서 입력한 문자열의 bytes 계산
 * @param str 문자열
 * @param isUtf8 true: multibyte문자를 3byte로 계산, false: multibyte문자를 2byte로 계산
 * @returns {Number}
 */
function _gfn_getByteLen(str, isUtf8) {
    var multiByteLen = 2;
    if(isUtf8) {
        multiByteLen = 3;
    }
    var _byte = 0;
    for(var i=0; i < str.length; i++) {
        var str2 = str.charAt(i);
        var code = str2.charCodeAt(0);
        if(code === 10 ) { //return code
            _byte += 2;
        } else {
            if(encodeURIComponent(str2).length > 4) {
                _byte += multiByteLen;
            } else {
                _byte++;
            }
        }
    }

    return _byte;
}

/**
 * 폼 데이터를 serialize 하고 값은 encodeURIComponent 로 처리한다.
 */
jQuery.fn.serializeAndEncode = function() {
    return $.map(this.serializeArray(), function(val) {
        return [val.name, encodeURIComponent(val.value)].join("=");
    }).join("&");
};

/*************************************************************************************************
설명 : IE9인지 체크.
**************************************************************************************************/
function _gfn_isiE9() {
    return browserDetect.browser == "Explorer" && browserDetect.version == "9";
}


function cmmAlert(msg) {
    if($("#lodingBarDiv").length > 0) {
        commonUtil.hideProgress();
    }
    alert(msg);
}

/*
 * json 값 null undefined number 체크
 */
function _cmmutils_json_null(pJsonStr) {
    var jsonStr = pJsonStr;
    // 문자열 null 일경우
    if (JSON.stringify(jsonStr) == "null") {
        return "";
    }
    // 인식할수없는 경우
    if (typeof(jsonStr) == 'undefined') {
        return "";
    }
    // 숫자일경우
    if (typeof(jsonStr) == 'number') {
        return jsonStr.toString();
    }
    return $.trim(jsonStr);
}
/*
 * HTML 태그 특수문자로 치환
 */
function htmlEncode(value){
    return $('<div/>').text(value).html();
}

/*
 * HTML 특수문자로 치환된것을 태그 형식으로 변환
 */
function htmlDecode(value){
    return $('<div/>').html(value).text();
}

//전화번호&핸드폰 정규식
function gfn_isTelNo(val){
    var reg = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;
    if(reg.test(val) == false) {
        return false;
    }else{
        return true;
    }
}

function gfn_phone_format(num){
    return num.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
}

/** 넘버만 입력받음 허용문자 = 1234567890 */
function onlyNumberEvent(){
    if(event.keyCode<48||event.keyCode>57)event.returnValue=false;
}
/** 넘버만 입력받음 허용문자 = 1234567890 */
function onlyNumberCheck(value){
    var regexp = /[^0-9]/g;
    if (value.match(regexp) != null) {
        return false;
    }
    return true;
}

/** 넘버만 입력받음 허용문자 = 1234567890,Del,Backspace.. */
function onlyNumber2(obj){
    var regexp = /[^0-9]/g;
    if ($(obj).val().match(regexp) != null) {
        $(obj).val($(obj).val().replace(regexp, ""));
    }
}

/**
 * 시작시간 완료시간 체크
 * @param startId
 * @param endId
 */
function checkStartToEndDate(startId,endId){
    if(startId==""||endId=="") return false;
    var SDate = $("#"+startId).val().replace(/-/g, "");
    var EDate = $("#"+endId).val().replace(/-/g, "");
    if( SDate > EDate ) {
        commonUtil.alert($("#"+startId).attr("title")+"는 "+$("#"+endId).attr("title")+"보다 이전 일시이어야 합니다.");
        return false;
    } else return true;
}
/**
 * 이메일 무결성 체크 정규식
 * @param email
 * @returns
 */
function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
    }

