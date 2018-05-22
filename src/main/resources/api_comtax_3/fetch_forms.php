<?php

$form_id = $_REQUEST['form_id'];

if($form_id=="form_1"){
?>

<div class="col-md-12">	
<form id="form1" name="form1" class="form-horizontal" >
<fieldset>

<legend>APPLICATION</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Text Input-1</label>  
  <div class="col-md-4">
  <input id="textinput1" name="textinput1" type="text" placeholder="placeholder-1" class="form-control input-md">
  <span class="help-block">help</span>  
  </div>
</div>

<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Text Input-2</label>  
  <div class="col-md-4">
  <input id="textinput2" name="textinput2" type="text" placeholder="placeholder-2" class="form-control input-md">
  <span class="help-block">help</span>  
  </div>
</div>

<!-- Dropdown -->
<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">Select Basic</label>
  <div class="col-md-4">
    <select id="selectbasic" name="selectbasic" class="form-control">
      <option value="1">Option one</option>
      <option value="2">Option two</option>
      <option value="3">Option three</option>
      <option value="4">Option four</option>	  
    </select>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <div class="col-md-4"></div>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary submitForm">Submit</button>
  </div>
</div>


</fieldset>	
</form>
</div>	

<?php
}
?>	

<?php

if($form_id=="form_2"){
?>

<div class="col-md-12">	

<form id="form2" name="form2" class="form-horizontal" >
<fieldset>

<legend>ALERT</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Text Input-1</label>  
  <div class="col-md-4">
  <input id="textinput1" name="textinput1" type="text" placeholder="placeholder-1" class="form-control input-md">
  <span class="help-block">help</span>  
  </div>
</div>

<!-- Dropdown -->
<div class="form-group">
  <label class="col-md-4 control-label" for="selectbasic">Select Option</label>
  <div class="col-md-4">
    <select id="selectbasic" name="selectbasic" class="form-control">
      <option value="1">Option one</option>
      <option value="2">Option two</option>
      <option value="3">Option three</option>
      <option value="4">Option four</option>	  
    </select>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <div class="col-md-4"></div>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Submit</button>
  </div>
</div>


</fieldset>	
</form>
	
</div>

<?php
}
?>	


<?php

if($form_id=="form_3"){
?>

<div class="col-md-12">	

<form id="form2" name="form2" class="form-horizontal" >
<fieldset>

<legend>DOCUMENT</legend>

<p>Form 'DOCUMENT' content goes here...</p>


</fieldset>	
</form>
	
</div>

<?php
}
?>	



<?php
//---------------------------------- just to test -----------------------------
if($form_id==9999){
?>
<div class="col-md-12">	

 <table class="table table-striped">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>mary@example.com</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>july@example.com</td>
      </tr>
    </tbody>
  </table>

</div>
<?php
}	
?>


