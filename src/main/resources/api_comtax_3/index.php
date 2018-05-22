<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Loading MySQL Records on Drop Down Selection using PHP jQuery</title>

<!-- --- Style Sheets ------------------------------------------------------------- -->

<link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/headerstyle.css">		
<link rel="stylesheet" type="text/css" href="assets/css/mainstyle.css">		

<!-- --- JS Files ----------------------------------------------------------------- -->
<script src="assets/js/jquery-1.11.2.min.js"></script>
<script src="assets/js/jquery.autocomplete.min.js"></script>

<script type="text/javascript">
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
			$('#display').load('form_application.html');
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

$(document).on('click', '.submitForm', function(e){
    e.preventDefault();
	alert('Load data using AJAX...');

	var dataString = 'form_id='+ 9999;	//any constant for testing
	
	$.ajax
		({
			url: 'fetch_forms.php',
			data: dataString,
			cache: false,
			success: function(r)
			{
				$("#showResult").html(r);

			} 
		});
	
});


</script>
</head>
<body style="background:#dcdcdc;">
<div class="header" style="padding:15px; height:80px;">
<div class="logo"><img src="assets/images/tcs_logo.png" style="height:60px;"></div>
<div class="header-content">GST G2G UAT TESTING</div>
</div>	
<nav class="navbar navbar-inverse navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">HOME</a>
        </div>
    </nav>

<div class="container" id="containerMain" style="background:#fff; margin-bottom:20px;">
    
      
      <div id="searchfield">
        <form><input type="text" name="currency" class="biginput" id="autocomplete" placeholder="Press 'F' to show all options"></form>
      </div><!-- @end #searchfield -->
   


    
    <div id="display">
       <!-- Form will be displayed here -->
    </div>
    

    <div class="panel panel-default" id="result_data">
      <div class="panel-heading">Panel to display result</div>
      <div class="panel-body" id="showResult">Content in table...</div>
    </div>	


</div>
<hr style="padding-top:50px; border-color:#dcdcdc;"/>
<footer class="footer">
      <div class="container">
        <span class="text-muted" style="text-align:center;">Copyright &copy; 2008 | <strong>TATA CONSULTANCY SERVICES</strong></span>
      </div>
</footer>
 

</body>
</html>