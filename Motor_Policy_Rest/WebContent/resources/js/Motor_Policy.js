


function noenter() {
  return !(window.event && window.event.keyCode == 13); }
/*$( "#ccode" ).keypress(function(e){
 alert("keypresed ");
	//  if ( e.which == 13 ) return false;
    //or...
    if ( e.which == 13 ){ 
    	alert("enter pressed ");
    	e.preventDefault()};
});

$( "form input:text" ).keypress(function(e){
    if ( e.which == 13 ) return false;
    //or...
    if ( e.which == 13 ) e.preventDefault();
});*/
							    var timer;

							    function regcheck(el)
							    {
							     //  var oldreg=document.getElementById("reg_temp").value;
							  
							   //  var patt = new RegExp("([A-Za-z]{1,3}[-][0-9]{3,9})|([A-Za-z]{2,3}[-][0-9]{2,3}[-][0-9]{2,9})");
							  
							     var patt = new RegExp("([A-Za-z]{1,3}[-][0-9]{3,9})|([A-Za-z]{2,3}[-][0-9]{2,3}[-][0-9]{2,9})");
							     var res =patt.test(el.value);  
							     clearTimeout(timer);
							     timer=setTimeout(function validate(){
							     if (res){
							    	return true; 
							      }
							     else{el.value = ''; return false;}
							       },4000);
							        
							    }
							    
							    
							    
							    function emailcheck(val)
								{
								var patt = new RegExp("[^@]+@[^@]+\.[a-zA-Z]{2,6}");
								var res = patt.test(val);	
									
									  clearTimeout(timer);
									   timer=setTimeout(function validate(){

									   if (res===false){
									//alert(val);
								document.getElementById("email").value = '';
								return false;}
								return true;
									   },6000);
									 
								}
								
								
						
								function modlist(val)
								{
									
									//  document.getElementById('tempdisp').style.display = 'none';
									 // document.getElementById('tempdisp').innerHTML= val;
									
									//    $("select[id='vmod']").val(val); 
									  var options= document.getElementById('vmod').options;
									  var  n= options.length;
									  for (var i= 0;  i<=n; i++) {
									      if (options[i].value===val) {
									       alert(options[i].value);
									    	  options[i].selected= true;
									          break;
									      }
									  }
									 // $("#vmod").val(val); 
									 // var vmodopts = vmod.options;
									 // alert("vmod   "+vmodopts.length);
					                  /* for (var vmodopt, j = 0; vmodopt = vmodopts[j]; j++) {
					                     if (vmodopt.value == ans[5]) {
					                   
					                    	 vmod.selectedIndex = j;
					                       break;
					                     }
					                   }*/
									
								}
								

								function vehtypelist(val)
								{
									//  alert( val);
								 	
								 	
								//  $("select[id='mtype']").val( val );	
								 	 var options= document.getElementById('vmod').options;
									  var  n= options.length;
									  for (var i= 0;  i<=n; i++) {
									      if (options[i].value===val) {
									    	  alert(options[i].value);
									    	  options[i].selected= true;
									          break;
									      }
									  }
								 	// $("#mtype").val(val);   
									//  var mtypeopts = mtype.options;
									//   alert("mtype   "+mtypeopts.length+"  " +mtypeopts[1].value);
									  
				/*	                    for (var mtypeopt, j = 0; mtypeopt <= mtypeopts.length; j++) {
					                    	 
					                	   if (mtypeopts[j].value == val) {
					                      
					                    	 mtype.selectedIndex = j ;
					                       break;
					                     }
					                   } */ 
									
								}
							
								 


								function  onmodselajax(val)
									{
						  
							  $.ajax({
						         
						         type: 'GET',
						         async:true,
						         url: 'MotorServlet?method=getcap&vehid='+val  , 
						         
						         cache:false,
						        
						         success: function(code) {
						      	    
						        //	 alert(code);
						       	  var lim=0;  
						       	  
						        	 var ans=code.split(',');
						        var cubic=	ans[0].substring(1, ans[0].length);
						        var sitting =	ans[1].slice(0, -1);
						        document.getElementById("scap").value= sitting;
						        document.getElementById("cubic").value= cubic;
						        
						        
						        },
						         error: function(xhr,ThrownError) {
						        
						               document.getElementById('msgdisp').style.display = 'block';
						
						          document.getElementById('msgdisp').innerHTML= "closing error."+xhr +' xhr.status: '+xhr.status;
						
						       setTimeout(function () {
						                   $("#msgdisp").hide(); $("#msgdisp").html('');
						               }, 5 * 1000); 
						         }  
						      });   
						}


                function isNumber(evt) {
    evt = (evt) ? evt : window.event;
   var valelem= document.getElementById("remarks").value;
    var maxlim=  document.getElementById("car_lim").value  ;
  
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57) ) {
    
        return false;
    }
  
    return true;
    }
                  

                function comment(event)
                
                {
              //  alert(event.keyCode);
//                var clear=document.getElementById("itemsent") ;
             
                    if (event.keyCode>32&&event.keyCode<48 )
                      { 
                     // alert(event.keyCode);
                    document.getElementById("itemsent").value=" ";
                  //  return false;
                    }
                 
                }
                 
                function nickeyup(el)
                {
                     var oldnic= document.getElementById("nickytemp").value;
                  if (el.keyCode >= 48 &&el.keyCode<=57)
                                      { 
                                  return;
                                      }
                                      else {el.value=oldnic;}
                  if(el.value.length<12||el.value.length>14)
                  {  el.value=oldnic;
                  return false;
                  } 
                    //    var charCode = (el.which) ? el.which : el.keyCode
                    //     if (iKeyCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
                    //       { el.value='';
                    //         return false;
                    //       }
                    //       else {return;}
                    //  if (el.target.id=="usersum")
                    //               {  
                    //               alert("usersum element");
//                                   if( el.value<upperlimit&&el.value>lowlimit)
//                                   {  
//                                   return ;
//                                   }
//                                   else {
//                                   el.value='';
//                                   return false;
//                                   }
                                   
                        //           } 
                  
                  
                } 
                
                
                function updateInput(val) 
                {
                	   
                	//  alert(val);
                	updateprem(val);
                	  
                	       var words=  inWords (val);
                	      document.getElementById('sumtext').value= words;
                	      document.getElementById('sumins').value= val;
                	  
                }
                
                   
                function updateprem(value)
                {
             	   
             	   var netrt=document.getElementById("netrt").value;
             	   
             	   value=(netrt*value)/100;
             	   
             	   value= Math.round(value);
             	   document.getElementById("premval").value=value;
             	   document.getElementById("premdiv").innerHTML="";
             	   document.getElementById("premdiv").innerHTML="Calculated premium: "+value+"/-"; 
                }
                
                
                   
                   function inWords (num) {
                	 
                	   var a = ['','one ','two ','three ','four ', 'five ','six ','seven ','eight ','nine ','ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
                       var b = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];

                    if ((num = num.toString()).length > 9) return 'overflow';
                    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
                    if (!n) return; var str = '';
                    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'crore ' : '';
                    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'lakh ' : '';
                    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'thousand ' : '';
                    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'hundred ' : '';
                    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only ' : '';
                    return str;
                }
                
                
                function addCommas(val) {
           // nStr += '';
            var rgx = /(\d+)(\d{3})/;
            while (rgx.test(val)) {
                    val = val.replace(rgx, '$1' + ',' + '$2');
            }
            return val;
        }