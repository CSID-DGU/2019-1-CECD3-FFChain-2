$(document).ready(function(){
	if($("#snb").get(0) == undefined){
		$("#contents").css({marginLeft: "10px", float: "left"});
	}
	setTimeout("initLeftMenu()", 300);
	//initLeftMenu();
});

function initLeftMenu(){
	layoutFix();
	$(window).resize(function() {
		layoutFix();
		$('#snb').css('height' ,  $('#contents').height() );
		$('#snb_wrap').css('height' ,  $('#contents').height() );
	});
	$('#snb .arrbtn').toggle(first,second);

	/*
	if ( $('#contents').height() < $(window).height() - 157 )
	{
		$('#snb').css('height' ,  $(window).height() - 157 );
		$('#snb_wrap').css('height' ,  $(window).height() - 157 );

	}else{
		$('#snb').css('height' ,  $('#contents').height() );
		$('#snb_wrap').css('height' ,  $('#contents').height() );
	}
	*/
	$('#snb').css('height' ,  $('#contents').height() );
	$('#snb_wrap').css('height' ,  $('#contents').height() );

}

function getOuterWidth(){
	var limit = 1200;
	var outWd = $(window).width();
	var snbWd = $('#contents').hasClass("expand") ? 30 : 270;    // 이동 후 위치 : 이동 전 위치;
	if(limit < outWd){
		var outWd = $(window).width();
	}else{
		var outWd = limit;
	}
	$('#wrap, #header').css('width' ,  outWd+"px" );
	$('#main_wrap').css('width' ,  outWd-20+"px" );
	return outWd-snbWd;
}

function layoutFix(){
	$('#contents').css('margin-left' ,  "10px");
	if($("#snb").get(0) != undefined){
		var outWd = getOuterWidth();
		$('#contents').css('width' ,  outWd+"px" );
	}
}

function toggleSplitBar(){$(".snb_area").toggle();
	if($(".snb_area").css("display")=="block")
		{
		$(".slide_bar").removeClass("closed");
		}else{$(".slide_bar").addClass("closed");
		}
};

function first(){
	$('#contents').addClass("expand");
	var outWd = getOuterWidth();
	$('#snb').animate({left:'-=235px'},200);
	$('#contents').animate({width:outWd},200);
	$('.arrbtn img').attr('src','/imgNew/btn_right.gif');
}

function second(){
	$('#contents').removeClass("expand");
	var outWd = getOuterWidth();
	$('#snb').animate({left:'+=235px'},200);
	$('#contents').animate({width:outWd},200);
	$('.arrbtn img').attr('src','/imgNew/btn_left.gif');
}

