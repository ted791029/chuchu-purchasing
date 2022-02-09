$(function() {
    $( ".datepicker" ).datepicker({
    	 dateFormat: 'yy-mm-dd'
    });
    $.datepicker.setDefaults( $.datepicker.regional[ "zh-TW" ] );
});
/**
*全選/全取消
*/
function checkAll(all){
	if($(all).prop("checked")) {
		$('.checkbox').each(function() {
			$(this).prop('checked', true);
		});
	}else{
		$('.checkbox').each(function() {
			$(this).prop('checked', false);
		});
	}
}
/**
*刪除選中的項目
*/
function deleteChecked(form){
	if(!isChecked()){
		alert("請選擇刪除項目");
		return;
	}
	var yes = confirm('確定要刪除嗎？');
	if (yes) {
		form.submit();
	}
}
/**
*是否有選中
*/
function isChecked(){
	let count = 0;
	$('.checkbox').each(function() {
		if($(this).prop("checked")) {
			count++;
		}
	});
	return count > 0 ? true : false;
}
