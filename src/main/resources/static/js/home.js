/**
 * 
 */
$(document).ready(function(){
	$(function(){
		  var all_forms = [
		    { value: "F1 : Application", data: 'form_1'},
		    { value: "F2 : Alert", data: 'form_2'},
		    { value: "F3 : Document", data: 'form_3'}
		  ];
		  
		  // setup autocomplete function pulling from currencies[] array
		  $('#autocomplete').focus();	
		  $('#result_data').attr('hidden', true);
		  
		  $('#autocomplete').autocomplete({
		    lookup: all_forms,
		    onSelect: function (suggestion) {
				
				if(suggestion.data=='form_1')		
				{
					$('#display').load('/return-filecount');
					$('#result_data').attr('hidden', false);
				}	
				
				if(suggestion.data=='form_2')		
				{
					$('#display').load('form_alert.html');
					$('#result_data').attr('hidden', false);
				}			
				
				if(suggestion.data=='form_3')		
				{
					$('#display').load('form_document.html');
					$('#result_data').attr('hidden', false);
				}	
		    }
		  });
		});
	
	
	
});

//$(document).on('click', '.submitForm', function(e){
//    e.preventDefault();
//	//alert('Load data using AJAX...');
//    console.log("Loading ajax...");
//
//	var dataString = 'form_id='+ 9999;	//any constant for testing
//	
//	$.ajax
//		({
//			url: '/return-filecount',
//			data: dataString,
//			cache: false,
//			success: function(r)
//			{
//				$("#showResult").html(r);
//
//			} 
//		});
//	
//});


$(document).on('focus','#date',function(){
	
	$("input[name='date']").datepicker();
	
});