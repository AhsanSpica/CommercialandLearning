       
 
//  var request; 

 
 
 /*   function getInfo4(){  
    if(request.readyState==4){ 
  
    document.getElementById("msgdisp").innerHTML=" ";
    
     var val=request.responseText;  
    // var resp=val.split("*"); 
     document.getElementById("msgdisp").innerHTML=val;
     
    var error=val.search("Exception");
    if (error ==-1)
     {submit_data();}
    //document.getElementById('querydisp').innerHTML="Payment successful" ;  
    }  
    }*/



/*function getLocation() {
        var t=document.getElementById('msgdisp');
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        t.innerHTML = "Geolocation is not supported by this browser.";
    }
}

 


function showPosition(position) {
        var countrycode=""; var reg=""; var city=""; var country="";
    var t=document.getElementById('msgdisp');
    document.getElementById('latt').value=position.coords.longitude;
    document.getElementById('longtd').value=position.coords.latitude;
   // t.innerHTML = "Latitude: " + position.coords.latitude +  "<br>Longitude: " + position.coords.longitude; 
  //  setTimeout(function () {
    //            $("#msgdisp").hide(); $("#msgdisp").html('');
    //        }, 5 * 1000); 
            $.getJSON('http://www.geoplugin.net/json.gp?jsoncallback=?', function(response) {
           
      countrycode=response.geoplugin_countryCode;
     city=response.geoplugin_city;
        reg=response.geoplugin_region;
             document.getElementById('countrycode').value=countrycode;
             
             $.getJSON("resources/names.json", function (data) {
 for (x in data) {
if (x==countrycode)
    {
 
    document.getElementById('cname').value= data[x];
      document.getElementById('comloc').value=city+", "+reg+", "+data[x];
   //  alert("countrycode:  "+ countrycode+ " ,cname from web service: "+data[x]+  document.getElementById('cname').value +"long: "+position.coords.longitude +" , "+position.coords.latitude ) ;
    }
    }
    });
   // alert(city+"  "+reg);
    
});
//             $.get('https://ipinfo.io',function(response){
//                countrycode=response.country;
//             document.getElementById('countrycode').value=countrycode;
//             
//             $.getJSON("resources/names.json", function (data) {
// for (x in data) {
//if (x==countrycode)
//    {
//   
//    document.getElementById('cname').value=data[x];
//     alert("countrycode:  "+ countrycode+ " ,cname from web service: "+data[x]+  document.getElementById('cname').value   ) ;
//    }
//    }
//    });
//             
//   // document.getElementById('msgdisp').innerHTML = countrycode +" City: "+ response.city;
//    },'jsonp');
      
                      
}*/
   
  
    
    //GET CITIES GLOBALLY
    /*function getintcities()
    { 
     var cname=document.getElementById('cname').value;
     
     //alert("the  country name from web service : "+cname);
     
     document.getElementById("trans_to").style.display='block';
      document.getElementById("trans_fro").style.display='block';
      
    $.ajax({
      type: 'GET',
      url: 'textmatch.jsp', 
     cache:false,
      data: {cname:cname,t:"get_city"},
   
    success: function(code) {
       
      var ans=code.split('*');
      ans = ans[1].split(",") ;
 
    var firstval =  ans[0].substring(1, ans[0].length);
 //   alert(firstval);
 
     $('#trans_to')
         .append($("<option></option>")
                    .attr("value",""  )
                    .text( "Select" ) ); 
  $('#trans_to')
         .append($("<option></option>")
                    .attr("value",firstval  )
                    .text( firstval ) ); 
  for(var z=1; z<=ans.length; z++)
    {
    // alert(ans[z]);
     $('#trans_to')
         .append($("<option></option>")
                    .attr("value",ans[z] )
                    .text( ans[z] ) ); 
     }
     
     $('#trans_fro')
         .append($("<option></option>")
                    .attr("value",""  )
                    .text( "Select" ) ); 
     $('#trans_fro')
         .append($("<option></option>")
                    .attr("value",firstval  )
                    .text( firstval ) ); 
     
  
  for( z=1; z<=ans.length; z++)
    {
   // alert(ans[z]);
     $('#trans_fro')
         .append($("<option></option>")
                    .attr("value",ans[z] )
                    .text( ans[z] ) ); 
     }
      
      
      },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "closing error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });}*/
   
      //END OF GET INTERNATIONAL CITITES
    
    function selectevent(val)
    {
      getintcities();
   // document.getElementById("msgdisp").style.display="Block";
     document.getElementById("msgdisp").style.color="Green";
     $("#msgdisp").html('Loading . . .');
     // document.getElementById("body").classList.remove("loading");
   document.getElementById("querydisp").innerHTML=' ';
     
    var val2=document.getElementById("ccode").value;
    // alert("polno: "+val+" ccode: "+val2);
     document.getElementById("spinner").style.display="Block";
   $.ajax({
      type: 'GET',
      url: 'textmatch.jsp', 
     cache:false,
      data: {ccode: val2,polno:val,t:"get_data"},
   
      success: function(code) {
       document.getElementById("spinner").style.display="none";
      $("#msgdisp").html(' ');
   var cspan=code.split("*"); 
   //  alert(cspan);
     var res = cspan[1].split(",") ;
 //var res=JSON.parse(resp[1]);
   //alert(res);
    var firstval =  res[0].substring(1, res[0].length);
 

             document.getElementById("main").style.height = "710px"; 
             $( ".restbl" ).show(); 
          
      document.getElementById("cl_nm") .value=  firstval;
     document.getElementById("polno") .value=res[1]; 

       //document.getElementById("pissdt") .value=res[2];
        document.getElementById("rstdt").value=res[3]; 
       document.getElementById("risk").value=res[4];
  
           document.getElementById("sumins") .value=res[5]; 
            // document.getElementById("co_ins") .value=res[6]; 
     document.getElementById("docid") .value=res[7]; 
      var strmax=res[8];
    
    document.getElementById("car_lim").value= addCommas( res[8] );
    // document.getElementById("remark").max=parseInt(strmax);
    document.getElementById("slither").max=parseInt(strmax);
 // input.setAttribute("min",100);
//input.setAttribute("max",strmax);
   
    document.getElementById("usum") .value=res[9].slice(0, -1)+" %"; 
  },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= "closing error."+xhr+thrownError;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
      
   });
    }
   
    
  
    
  
    
    
    /*function update (val)
    {
    
    if (val=="1")
    
    {
  
    document.getElementById("lblmd").innerHTML="Truck Reg. No.";
    }
     if (val=="2")
    
    { 
    document.getElementById("lblmd").innerHTML="AWB No.";
     }
     if (val=="3")
    
    { 
    document.getElementById("lblmd").innerHTML="Rail Reciept No.";
    }
    }*/
    
    
/*    function minmax(value) 
{  var maxlim=  document.getElementById("slither").max  ;
//if(parseInt(value) < 0 || isNaN(parseInt(value))) 
     //   return 0; 
    // alert(maxlim);
      if(parseInt(value) > maxlim) 
      {  return maxlim; }
    else return value;
}*/
   // onkeyup="this.value = minmax(this.value)"    
  
    
    
    
    
    
    
    function post_dec()
    {
    var shipval=document.getElementById("remarks").value;
   shipval= parseFloat( shipval.replace(/,/g,''));
    var trans_dt=document.getElementById("trans_dt").value;
    var trans_to=document.getElementById("trans_to").value;
    var trans_fro=document.getElementById("trans_fro").value;
     var conloc= document.getElementById('comloc').value;
  var polno=document.getElementById("polno").value;
  var cl_nm=document.getElementById("cl_nm").value;
   
   var itemsent=document.getElementById("itemsent").value; 
    
    var reg_no=document.getElementById("reg_no").value;
   
    var docid=document.getElementById("docid").value;
     
    var modeSelect = document.getElementById("mode_ship");
    var MODE_SHIP = modeSelect.options[modeSelect.selectedIndex].text;
 
 
  
 
    $.ajax({
      type: 'GET',
      url: 'textmatch.jsp', 
     cache:false,
     // data: { docid:docid,email: email,reg_no: reg_no,commo: commo,trans_fro: trans_fro,trans_to: trans_to,trans_dt: trans_dt,shipval: shipval,cellno: cellno, t:"post_dec"},
   data: { docid:docid, reg_no: reg_no, trans_fro: trans_fro,trans_to: trans_to,trans_dt: trans_dt,shipval: shipval,itemsent:itemsent,MODE_SHIP:MODE_SHIP,polno:polno,cl_nm:cl_nm,conloc:conloc,  t:"post_dec"},
   
      success: function(code) {
  
   var cspan=code.split("*"); 
  cspan= cspan[1].split(",") ;
      
     document.getElementById('msgdisp').style.display = 'block';
    document.getElementById('msgdisp').innerHTML=cspan[0];
    setTimeout(function () {
                $("#msgdisp").html(''); location.reload(); 
            }, 4 * 1000); 
   
  },
      error: function(xhr, ajaxOptions, thrownError) {
     
            document.getElementById('msgdisp').style.display = 'block';

       document.getElementById('msgdisp').innerHTML= " "+thrownError+form_data;

  setTimeout(function () {
                $("#msgdisp").hide(); $("#msgdisp").html('');
            }, 5 * 1000); 
   
      }
//      
   });
    
    
    }
    
    

   /* function submit_data()
    {
     document.getElementById("main").method = "post"; 
     document.getElementById("main").action = "bill2.jsp"; 
      document.getElementById("main").submit(); 
    
    }*/

  

function sumonchange()
{
 // alert("method trigerred");
     var reg_no =$("#regin").val();
            var eng_no=$("#engin").val();
           var chas_no=$("#chassin").val();
           var usersum=1;
        var block=""; var thef=""; var con="";
      
         if( tbl_res.style.display=='block')
           {  
         //  if (valbtn==null)
        // {
         usersum=  document.getElementById("usersum").value;      
        // }
           }
          // document.getElementById("main").style.height = "710px"; 
          //  document.getElementById("tbl_res").style.display = "block"; 
           
         var url="textmatch.jsp?reg="+ reg_no+"&eng="+ eng_no+"&chas="+chas_no+"&t=validatepolicy"+"&usersum="+usersum  ;  
    //       var url="textmatch.jsp?reg="+ reg_no+"&eng="+ eng_no+"&chas="+chas_no+"&t=validatepolicy"  ;
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try  
{ document.getElementById("msgdisp").style.color="Green";
document.getElementById("msgdisp").innerHTML="Loading . . . ";
request.onreadystatechange=getInfo2;  
request.open("GET",url,true);  
request.send();  
}  
catch(e)  
{  
alert("Unable to connect to server");  
} 

}

   