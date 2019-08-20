/**
 * 팝업페이지 (POST 방식)
 * requestURL 의 파라미터값으로 Submit 까지 완료 (팝업)
 * requestUrl : http://test.com/sample/send.do?param1=1&param2=2
 */
 
     function dirname(path) {
         if (path.lastIndexOf("/") == -1)
             return "./";
         return path.replace(/\\/g, '/').replace(/\/[^\/]*\/?$/, '') + "/";
     }
      
     function getActiveScript() {
         var d = document.getElementsByTagName("script");
         var path = dirname(d[d.length - 1].src);
         delete d;
      
         var offset=path.indexOf(location.host)+location.host.length;
         return path.substring(offset);
     } 
      
      
     function getContextPath(){
         var offset=location.href.indexOf(location.host)+location.host.length;
         var ctxPath=location.href.substring(offset, location.href.indexOf('/',offset+1));
      
         if ((/^\/js/).test(getActiveScript())) {
             return "";
         }
      
         return ctxPath;
     }
      
     function loadScript(src, f) {
       var head = document.getElementsByTagName("head")[0];
       var script = document.createElement("script");
       script.src = src;
       var done = false;
       script.onload = script.onreadystatechange = function() { 
         // attach to both events for cross browser finish detection:
         if ( !done && (!this.readyState ||
           this.readyState == "loaded" || this.readyState == "complete") ) {
           done = true;
           if (typeof f == 'function') f();
           // cleans up a little memory:
           script.onload = script.onreadystatechange = null;
           head.removeChild(script);
         }
       };
       head.appendChild(script);
     }
      
     loadScript(getContextPath() + '/js/egovframework/com/cmm/showModalDialog.js');
     
     var var1, var2, var3, var4, var5, var6, var7, var8, var9;
    //2012/10/10 happy81
     //function newZipSearch(zip_cd_1, zip_cd_2, address, new_address, eng_address, new_eng_address, city_cd, si_cd, gu_cd)
     function newZipSearch(zip_cd_1, zip_cd_2, address, new_address, eng_address, new_eng_address, city_cd, si_cd, gu_cd)
     {
        //var resultValue = window.showModalDialog("/jsp/popup/NewKPoZipCodeS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:700px; dialogHeight:450px; scroll:1; help:1; status:0; resizable:1");
        var resultValue = window.showModalDialog("/icip/cstmrinfomanage/cstmrregist/zipSearch.do?form_name=frm&search=no", "newWin", "dialogWidth:700px; dialogHeight:450px; scroll:1; help:1; status:0; resizable:1", "showModalDialogCallbackZip");
        var1=zip_cd_1; var2=zip_cd_2; var3=address; var4=new_address; var5=eng_address; var6=new_eng_address; var7=city_cd; var8=si_cd; var9=gu_cd;
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
     
     function showModalDialogCallbackZip(retVal) {
         if (retVal) {
             $("input[name=" + var1 + "]").val(retVal.zipCd);
             $("input[name=" + var2 + "]").val(retVal.oldzipCd);
             $("input[name=" + var3 + "]").val(retVal.addr);
             $("input[name=" + var4 + "]").val(retVal.newAddr);
             $("input[name=" + var5 + "]").val(retVal.engAddr);
             $("input[name=" + var6 + "]").val(retVal.newEngAddr);
             
             $("input[name=" + var7 + "]").val(retVal.cityCd);
             $("input[name=" + var8 + "]").val(retVal.siCd);
             $("input[name=" + var9 + "]").val(retVal.guCd);
         }
     }
     var ctrnm = "";
     var ctr = "";
     function search(CTR_NM , CTR_CD)
     {
         //var ctrcode = window.showModalDialog("/jsp/popup/KPoTraComEtcS.jsp?form_name=frm&search=no", "newWin", "dialogWidth:px; dialogHeight:350px; scroll:1; help:1; status:0");
         var ctrcode = window.showModalDialog("/icip/cstmrinfomanage/cstmrinqire/corpNationSearchPopup.do?form_name=frm&search=no", "newWin", "dialogWidth:px; dialogHeight:350px; scroll:1; help:1; status:0", "showModalDialogCallbackNation");
         
   
         ctrnm   = CTR_NM;
         ctr     = CTR_CD;
     
         if(ctrcode == undefined) ctrcode = window.returnValue;
         
         if (ctrcode != null)
         {
             allcode = ctrcode.split('_/');
             eval(ctrnm).value = allcode[1];
             eval(ctr).value = allcode[0];
         }
     }
     function showModalDialogCallbackNation(retVal) {
         if (retVal) {
             allcode = retVal.split('_/');
             eval(ctrnm).value = allcode[1];
             eval(ctr).value = allcode[0];
         }
     }
     /*
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
     */