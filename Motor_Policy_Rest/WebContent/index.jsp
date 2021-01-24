<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 

<html  >
<head>
<meta charset="ISO-8859-1">
 <link type="text/css" rel="stylesheet" href="resources/css/obsoleteStyle.css"/>
 <link type="text/css" rel="stylesheet" href="resources/css/responsive_header.css"/>
   <link type="text/css" rel="stylesheet" href="resources/css/tcal.css"/>
    <link type="text/css" rel="stylesheet" href="resources/css/compulsory_css.css"/>
    <script type="text/javascript" src=" resources/slider.js "  > </script>
      <script type="text/javascript" src="resources/js/jquery-3.1.1.js "  > </script>
 
  <script type="text/javascript" src=" /Motor_Policy_Rest/WebContent/resources/js/ajax.js"  > </script>
    
  <script type="text/javascript" src=" /Motor_Policy_Rest/WebContent/resources/js/Motor_Policy.js"  > </script>
    
<title>EFU | Motor Corporate Insurance</title>

 

<script>

              
                
</script>

</head>
<body onload="onloadfunc()">

 
  
  <div class="section group "   style="margin-top:40px;margin-bottom:-10px;">
	<div class="col span_0_of_4">
	<img id="headimg" name="headimg" height="76" width="68" src="Images/logo_efu.png" style=" margin-left:10%;"/>
	</div>
	<div class="col span_3_of_4   ">
	 <h2 style=" border-bottom: 0.1px; color: #2e3133;margin-left: 1%;   font-weight: bold;  font-family: Helvetica; font-size:13.0pt; width:320px;">
      Motor Corporate Insurance</h2>
	 </div>
	
	<div class="col span_0_of_4"> <a href="http://efuinsurance.com/index.php">
                          <img border="0" src="Images/top_link_home.gif" width="57"
                               height="25"/></a>
	 <a href="http://efuinsurance.com/getintouch/contactus.php"> 
                          <img border="0" src="Images/top_link_contact.gif"
                               width="57" height="25"/></a>
	</div>
</div>
 <form id="motorform">
  <input type="hidden" id="countrycode"   />
  <input type="hidden" id="latt"   />
  <input type="hidden" id="longtd"   />
  <input type="hidden" id="cname"   />
  <input type="hidden" id="comloc"   />
  <input type="hidden" id="netrt"  name="netrt" />
  
  <input type="hidden" id="method" name="method"   />
  
  <input type="hidden" id="premval" name="premval"   />
  <input type="hidden" id="brid" name="brid"  />
  <input type="hidden" id="vehid" name="vehid"  />
<!-- input block -->

<div class="section group " >
	<div class="col span_0_of_4">
	 
	</div>
	<div class="col span_3_of_4   ">
        <div class="colorstrip"></div>
	  <table style="   min-width:12%; max-width:800px; margin-top:10px; "   >
	 
	  <tr>
   
    <td width="100">
    <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"   ><b>Client Code </b></label> </td>
    <td width="100">
    <label width="100" style="display:none; font-family: Times New Roman; font-size:9;" id="plbl"      ><b>Policy </b>  </label> </td>
     
     <td width="100">
    <label width="100" style=" display:none;font-family: Times New Roman; font-size:9.0pt;" id="crlbl"     ><b>Certificate </b>  </label> </td> 
      
    </tr> 
    
    <tr>
     
     <td width="150">
     
    <input  tabindex="1"  type="text" style="text-align: center;  width: 80%;min-width:5%;"
     name="ccode" id="ccode"    formmethod="get"  onchange="verifyclient(event)" onkeypress="return noenter()"  /> 
     
      </td>
      
    
    
  <td width="250">
   <select  tabindex="2"    style="   width: 100%;min-width:5%; display:none;"    name="polist" id="polist"  
       onchange="verifypol (event)"  >  <!-- formmethod="get"  onchange ="this.form.submit()" --> 
     <%-- <c:forEach items="${ResultList}" var="category">
        <option value="${category.policy}">${category.text}</option>
    </c:forEach> --%>  
     
     </select>
    <!--  onchange=" selectevent(this.value)"  -->  
   </td>
     <td width="200">
     <select  tabindex="3"    style="   width: 100%; min-width:5%; display:none;"    name="certlist" id="certlist"  
    autocomplete="off"       onchange="getcertdet (this.value)"   >
     
     
     </select>
     
     
      </td>
     <td  style="width:50px;" >
    <input   type= "button" id ="subbtn"  name="subbtn"   class="buttonextra restbl" title="add certificates" 
    onclick="postrequest()"  value="Submit" autocomplete="off"  />
    
      </td>
    
    <td  style="width:50px;  ">
  
     <input   type= "button" id ="combtn"  name="combtn"   class="buttonextra restbl" title="final commit" 
      onclick="putrequest()" value="Commit" autocomplete="off" disabled ="disabled"  /> 
      </td>
    
    </tr>
    
         </table>       
        
	</div>
	
	<div class="col span_0_of_4">
	  
	</div>
</div>   

<div    >
	<label style="margin-left:220px; color: red; font-size: 12.0pt;  margin-bottom: 5px; font-family: Verdana;" id="msgdisp"> </label>
  
 
	</div>
 <div class="loader" id ="spinner" style="display:none;" ></div>

<div  id="infodisplay" style="display:none;" >

<!-- Policy details -->


<div id="poldiv" class="section group" >
	<div class="col span_0_of_4">
	 
	</div>
	
	
	<div class="col span_3_of_4   ">
     
	 
	   <div class="colorstrip" style="margin-top:20px;  "> Policy Details: </div>   
	 
	  <div style= "margin-left:20px; color: red; font-size: 12.0pt;  margin-bottom: 5px; font-family: Verdana; display:none;" id="tempdisp"> </div>
	 
	  <table style="   min-width:12%; max-width:800px; margin-top:10px; "   >
	
    
    <tr>
    <td width="180"> <label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Sum Insured (PKR Rs.) </b></label></td>
     <td width="150">
    <input  tabindex="4"  type="text" style="text-align:center;  width: 95%;  min-width:5%;" name="sumins"   id="sumins" required  class="required"   
     onkeydown="return isNumber(event)"   onchange="updateInput(this.value)"  
       autocomplete="off"  /> 
       </td>
   
    <td width="100">
       <input type="range"  class="slider"   id="slither"   min="1" 
       max="10000000" onchange=" updateInput (this.value)"    autocomplete="off"       />
         </td>
   
    <td width="200" colspan="1">
    <input  tabindex="5"  type="text" style="text-align: center;  width: 95%;  min-width:5%;"  
     id="sumtext"  autocomplete="off" readonly="readonly"   /> </td>
     </tr>
    
     <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Policy risk Start Date</b></label> </td>
    
     <td width="150">
    <input  tabindex="6"  type="text" style="text-align: center;  width: 95%;  min-width:5%;"    readonly= "readonly"  class="readonly" 
     id="rstdt"   name="rstdt"     onkeypress="return isNumber(event)"   
       autocomplete="off"  /> 
       </td>
    
    
    <td width="100"> <label width="100%" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Policy Risk End Date </b></label></td>
    <td width="200">  
      <input  tabindex="7"  type="text" style="text-align: center;  width: 95%;  min-width:5%;" 
        readonly= "readonly"   class="readonly"   name="risk" id="risk"    autocomplete="off"  /> 
       </td>
      
    </tr>
    <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>No. of Days </b></label> </td>
     <td width="150">
    <input  tabindex="8"  type="text" style="text-align: center;  width: 95%;  min-width:5%;" name="nodays"    id="nodays"  readonly= "readonly"  class="readonly" 
   
       autocomplete="off"  /> 
       </td>
    <td width="100">
    <!-- <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Insured Type </b></label> --> 
    </td>
    <td width="250">  
      <!-- <input  tabindex="2"  type="text" style="text-align: center;  width: 100%;min-width:5%;"    id="instype"    autocomplete="off"  /> --> 
       </td>
      
    </tr>
     
         </table>       
        
	</div>
	
	 
	<div id="premdiv" class=" col span_0_of_4 floatdiv">
	 
	</div>
	 
	
	
</div>  
	


<!--Client details --> 

<div class="section group" >
	<div class="col span_0_of_4">
	 
	</div>
	
	
	<div class="col span_3_of_4   ">
     
       <div class="colorstrip" style="margin-top:20px;  "> Client Details: </div>   
     
	  <table style="   min-width:12%; max-width:900px; margin-top:10px; "   >
	
    
    <tr>
    <td width="180"> <label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Client Name </b></label></td>
     <td width="250">
    <input  tabindex="9"  type="text" style="text-align: center;  width: 95%;min-width:5%;" 
    <%-- value="${CLIENT_NM}" var= "CLIENT_NM" --%>  name="cl_nm" id="cl_nm"  readonly= "readonly"  autocomplete="off"   />  </td>
   
    <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>C.N.I.C #</b></label> </td>
    <td width="250">  
      <input  tabindex="10"  type="text" style="text-align: center;  width: 95%; min-width:5%;"   name="cnic" required    class="required"   
       id="cnic"    autocomplete="off"   onchange="return nickeyup(this)"    /> 
       </td>
      
    </tr>
    
     <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Insured Name (Lessee) </b></label> </td>
     <td width="250">
    <input  tabindex="11"  type="text" style="text-align: center;    width: 95%; min-width:5%;"   name="ins_nm"  id="ins_nm"  required  class="required"     autocomplete="off"  />  </td>
   
    <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Cell# </b></label> </td>
    <td width="250">  
      <input  tabindex="12"  type="text" style="text-align: center;   width: 95%; min-width:5%;"  name="cell"    id="cell"  required  class="required"     autocomplete="off"  /> 
       </td>
      
    </tr>
    <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Insured Address </b></label> </td>
     <td width="250">
    <input  tabindex="13"  type="text" style="text-align: center;  width: 95%;min-width:5%;" name="add"   id="add"    autocomplete="off" required   class="required"   />  </td>
   
    <td width="100"> <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Email ID </b></label></td>
    <td width="250">  
      <input  tabindex="14"  type="text" style="text-align: center;   width: 95%;  min-width:5%;"  
      onkeyup="emailcheck( this.value )"
      
       name="email"  id="email"  required  class="required"     autocomplete="off"  /> 
       </td>
      
    </tr>
     <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Province </b></label> </td>
     <td width="250">
   <select  tabindex="13"  style="  text-align: center;  width: 90%; min-width:5%;  " name="provlist" id="provlist"   required  class="required"   
          onchange="getcitylist( this.value )"   > </select>
     </td>
   
    <td width="100"> <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>City </b></label></td>
    <td width="250">  
       <select  tabindex="14"  style="  text-align: center;  width: 90%; min-width:5%; display:none;" name="citylist" id="citylist"   required  class="required"   
        onchange="getarealist( this.value )"   > </select> </td>
      
    </tr>
    <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Area </b></label> </td>
     <td width="250">
    <select  tabindex="15"  style="  text-align: center;  width: 90%; min-width:5%; display:none;" name="arealist" id="arealist"    
      > </select>
     </td>
    </tr>
    
           </table>       
       
	</div>
	
	<div class="col span_0_of_4">
	
	</div>
</div>  




<!-- Vehicle Details : -->
<div id="vehdiv" class="section group " >
	<div class="col span_0_of_4">
	 
	</div>
	
	
	<div class="col span_3_of_4   ">
         <div class="colorstrip" style="margin-top:20px;  "> Vehicle Specifications: </div>   
	  <table id="veh_tbl"  style="   min-width:12%; max-width:800px; margin-top:10px; "   >
	
     <tr>
    <td width="180"> <label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Vehicle Manufacturer </b></label></td>
     <td width="250">
   <select  tabindex="15"    style="    width: 95%;  min-width:5%; display:none;"  name="vmac"  id="vmac"    autocomplete="off"  required  class="required"   
           onchange="onvehsel(this.value)"   >
      
     </select> </td>
  
   
     
    <td width="100"> <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Registration </b></label></td>
    <td width="250">  
      <input  tabindex="16"  type="text" style="text-align: center;   width: 95%; min-width:5%;"  name="reg"  id="reg"
       onkeyup="regcheck(this)" placeholder="XXX-0001"
      
        autocomplete="off" required  class="required"    /> 
       </td>
      
    </tr>
    <tr>
    <td width="180"> <label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Vehicle Model </b></label></td>
     <td width="250">
    
    <select  tabindex="17"    style="    width: 95%;  min-width:5%; display:none;"   name="vmod" id="vmod" class="vmod"   autocomplete="off" required   class="required"   
        onchange=" vmodajax(this.value) "      >
      
     </select>
    
    
    </td>
   
    <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Engine </b></label> </td>
    <td width="250">  
      <input  tabindex="18"  type="text" style="text-align: center;   width: 95%; min-width:5%;" name="eng"  id="eng"    autocomplete="off"  required  class="required"   /> 
       </td>
      
    </tr>
    
     <tr>
   
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Model Variants </b></label> </td>
   <td width="250">  
     <select tabindex="25" style=" width: 95%; min-width:5%; " id="mtype"  name="mtype" class="mtype"  onchange=" onmodselajax(this.value)"  required  class="required"   >
    
     </select>
     </td>
    
    <td width="100"> <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Chassis </b></label></td>
    <td width="250">  
      <input  tabindex="20"  type="text" style="text-align: center;    width: 95%; min-width:5%;"  name="chass"  id="chass" autocomplete="off" required  class="required"    /> 
       </td>
       
    </tr>
    
    <tr>
    <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Cubic Capacity </b></label> </td>
     <td width="250">
    <input  tabindex="21"  type="text" style="text-align: center;    width: 95%; min-width:5%;"    readonly= "readonly"  class="readonly"  name="cubic"  id="cubic"   autocomplete="off" required />  </td>
   
    <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Color </b></label> </td>
    <td width="250">  
      <input  tabindex="22"  type="text" style="text-align: center;    width: 95%; min-width:5%;"   name="color"  id="color"   autocomplete="off"   /> 
       </td>
    </tr>
    
    <tr>
   <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Vehicle Type </b></label> </td>
    <td width="250">  
     <input  tabindex="23"  type="text" style="text-align: center;  width: 95%; min-width:5%;"     readonly= "readonly"  class="readonly" 
     name="vtype"  id="vtype"   autocomplete="off"   /> 
     </td>
   
     <td width="180"> <label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Model Year </b></label></td>
     <td width="250">
    <input  tabindex="19"  type="text" style="text-align: center;  width: 95%;min-width:5%;"   name="modyr"  id="modyr"   autocomplete="off"  />  </td>
   
    <!-- <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Vehicle Body </b></label> </td>
    <td width="250">  
       <select  tabindex="24"    style="    width: 95%;  min-width:5%; display:none;"   id="vbod"  name="vbod"  onchange=" onvbodsel (this.value)"   autocomplete="off"   >
         </select>
       </td> -->
    </tr>
      <tr>
       <td width="180"><label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Sitting Capacity </b></label> </td>
     <td width="250">
    <input  tabindex="23"  type="text" style="text-align: center;  width: 95%; min-width:5%;"     readonly= "readonly"  class="readonly" 
     name="scap"  id="scap"   autocomplete="off"  />  </td>
  
    </tr>
         </table>       
       
	</div>
	
	<div class="col span_0_of_4">
	
	</div>
</div>  

<!-- Tracking Details : -->

<div class="section group " >
	<div class="col span_0_of_4">
	 
	</div>
	
	
	<div class="col span_3_of_4   ">
     
        <div class="colorstrip" style="margin-top:20px;  "> Tracking device  details: </div>   
     
	  <table style="   min-width:12%; max-width:800px; margin-top:10px; "   >
	
    
    <tr>
    <td width="180"> <label width="180" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Tracking Device Installed </b></label></td>
     <td width="250">
    <input  tabindex="26"  type="radio" style="text-align: center;  width: 15%;min-width:5%;" group="trackinst"     name="trackinst" id="trackyes"   autocomplete="off"  />
     <label width="40" style=" font-family: Times New Roman; font-size:10.0pt;"><b>Yes </b></label>
    <input  tabindex="27"  type="radio" style="text-align: center;  width: 15%;min-width:5%;"  group="trackinst"      name="trackinst" id="trackno"  autocomplete="off"  />
     <label width="40" style=" font-family: Times New Roman; font-size:10.0pt;"><b>No </b></label>
     </td>
   
    <td width="100"><label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Tracking Device Installed by </b></label> </td>
    <td width="250">  
      <input  tabindex="28"  type="radio" style="text-align: center;  width: 15%;min-width:5%;" group="trackby"    name="trackby" id="trackClient" value="L"  autocomplete="off"  />
     <label width="40" style=" font-family: Times New Roman; font-size:10.0pt;"><b>Client </b></label>
    <input  tabindex="2"  type="radio" style="text-align: center;  width: 15%;min-width:5%;"  group="trackby"     name="trackby" id="trackEFU"  value="E"   autocomplete="off"  />
     <label width="40" style=" font-family: Times New Roman; font-size:10.0pt;"><b>EFU </b></label>
     
      
      
       </td>
      
    </tr>
    
     <tr>
    <td width="100"> <label width="100" style=" font-family: Times New Roman; font-size:11.0pt;"><b>Tracking Company Name </b></label></td>
     <td width="250">
     
    <select  tabindex="3"    style="   width: 90%; min-width:5%; display:none;"   name="trackc" id="trackc"     
    autocomplete="off"        >
      
     </select>
      </td>
    
    </tr>
     
         </table>       
        
	</div>
	
	<div class="col span_0_of_4">
	
	</div>
</div> <!-- tracking det sec group -->
</div> <!-- complete info box div-->


<div class="section group " style="margin-top:100px;">
	<div class="col span_0_of_4">
	 
	</div>
	
	
	<div class="col span_3_of_4   ">
  <div class="colorstrip2"  > </div>
     <br>  
     <!-- HERE IS WHERE COPYRIGHT GOES -->
      ©Copyright EFU General Pakistan
      <br>     <br>   <br>  <br> <br> 
	</div>
	
	<div class="col span_0_of_4">
	
	</div>
</div>



</form> 
</body>
</html>