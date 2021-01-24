/**
 * 
 */
          
           
           
           function verifyclient(evt)
           {
              // getLocation();
        // if (val.length>4){

         
           var val= document.getElementById("ccode").value;
            
          if (val !== null && val !== '') 
          { 
        	  var elemid= evt.target.id;
            var url=""; var jelemid=""; var cc=false; var pl=false;
          
             url= 'MotorServlet?method=verify&ccode='+val ;
             jelemid ="#polist";
              cc=true;
                         
           
//           if(window.XMLHttpRequest){  
//           request=new XMLHttpRequest();  
//           }  
//           else if(window.ActiveXObject){  
//           request=new ActiveXObject("Microsoft.XMLHTTP");  
//           }  
//             var url="textmatch.jsp?t=ver_code&ccode="+val;  
//           try  
//           { 
       //   // document.getElementById("msgdisp").style.color="Green";
       //   // document.getElementById("prevmsgdisp").style.color="Green";
       //   // document.getElementById("prevmsgdisp").innerHTML="Loading . . . ";
//           document.getElementById("msgdisp").innerHTML="Loading . . . ";
       //    
//           request.onreadystatechange=getInfo4();  
//           request.open("GET",url,true);  
//           request.send(); 
//           }  
//           catch(e)  
//           {  
 
//           }  

         $(jelemid).find('option').remove();
        
           document.getElementById("msgdisp").style.display="Block";
           document.getElementById("msgdisp").innerHTML= "Loading . . .";
           document.getElementById("spinner").style.display="Block";
           $.ajax({
           
             type: 'GET',
             async:true,
             url: url , 
             // data: ({  ccode : val, }),
             cache:false,
             
            // data: {ccode:val,t:"ver_code"},
             
             success: function(code) {
           	  var lim=0;
       
           	
               document.getElementById("msgdisp").innerHTML="";
               document.getElementById("spinner").style.display="none";
              // document.getElementById("spinner").style.display="none";
              
               	$(jelemid).css("display","block");
               	$("#plbl").css("display","block");
              var ans=code.split(',');
       
              lim=ans.length;  
           
              
       $(jelemid)
       .append($("<option></option>")
                  .attr("value",""  )
                  .text( "Select" ) ); 
                  
      var polval = " ";  var brid = " "; var firstval =  "";
      
       for(var z=0; z<lim; z++)
           {
         
     // polval = Number(ans[z].substring(5, 11)).toString();
     
      
				    	   if (lim==1)
				           {
				
								firstval = ans[z].substring(1, ans[0].length);
								firstval = firstval.slice(0, -1);
								polval = Number(firstval.substring(4, 10)).toString();
								brid=Number(firstval.substring(0, 3)).toString();
							 
								$(jelemid)
				            .append($("<option></option>")
				                       .attr("value",polval )
				                       .text( firstval) );
				           }
    	   
                          else if (z==0)
			               {
    	 
    	  					firstval = ans[z].substring(1, ans[0].length);
    	  					polval = Number(firstval.substring(4, 10)).toString();
							brid=Number(firstval.substring(0, 3)).toString();
    	  				 
    	  					$(jelemid)
			                .append($("<option></option>")
			                           .attr("value",polval )
			                           .text( firstval) );
			               }
                           
				            else if (z==lim-1)
				           { 
				            	var lastelem=ans[z].slice(0, -1);
				            	var temp  = lastelem.trim().substring(4, 10);
								brid=Number(firstval.substring(0, 3)).toString();
                           polval = Number(temp).toString();
				             
				            	 $(jelemid)
                         .append($("<option></option>")
                           .attr("value",polval )
                           .text( lastelem ) );  
                       
                           }
                           else
                           {
                        polval = Number(ans[z].substring(4, 10)).toString();
						brid=Number(firstval.substring(0, 3)).toString();
      	  					 
                           $(jelemid)
                .append($("<option></option>")
                           .attr("value",polval)
                           .text( ans[z] ) );
                           
                           }
            }
                 document.getElementById("brid").value=brid;
             
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
        
           }
        
           function verifypol(evt)
           {
        	   provlist();
           	 var val= document.getElementById("ccode").value;
                
                var elemid= evt.target.id;
                var url=""; var jelemid=""; var cc=false; var pl=false;
               	
                  var pol= document.getElementById("polist").value;
                
                  if (pol !== null && pol !== '' && pol !== 'Select')
                 {
                  url=  'MotorServlet?method=getcert&ccode='+val+'&polist='+pol ;
                  pl=true;
               
                     
                  $("#certlist").find('option').remove();
              
              document.getElementById("msgdisp").style.display="Block";
              document.getElementById("msgdisp").innerHTML= "Loading . . .";
              document.getElementById("spinner").style.display="Block";
              $.ajax({
              
                type: 'GET',
                async:true,
                url: url , 
                
                cache:false,
               
                success: function(code) {
               
                	
                	var lim=0;
              	  document.getElementById("infodisplay").style.display="block";
                  document.getElementById("msgdisp").innerHTML= "";
                  document.getElementById("spinner").style.display="none";
                 if (pl)
                 { 
               	//  document.getElementById("msgdisp").innerHTML= code;
               	  }
                 
                 // document.getElementById("spinner").style.display="none";
                 
                 $("#certlist").css("display","block");
                 $("#crlbl").css("display","block");
                 var ans=code.split(',');
              
          		  lim=ans.length-1;   
               
              document.getElementById('cl_nm').value=ans[0].substring(1, ans[0].length) ;
              document.getElementById('rstdt').value=ans[1] ;
              document.getElementById('risk').value=ans[2] ;
              document.getElementById('nodays').value=ans[3]  ;
              document.getElementById('netrt').value=ans[4]  ;
              document.getElementById('vtype').value=ans[5]  ;
              if( ans.length==5)
              { document.getElementById('nodays').value=ans[4].slice(0, -1) ;}
             
          $("#certlist")
          .append($("<option></option>")
                     .attr("value",""  )
                     .text( "Select" ) ); 
                     
         var polval = " ";  var firstval =  "";
         
          for(var z=5; z<=lim; z++)
              {
            
         polval = Number(ans[z].substring(5, 11)).toString();
              
         if (z==0)
                  {
                  firstval = ans[z].substring(1, ans[0].length);
                  $("#certlist")
                   .append($("<option></option>")
                              .attr("value",polval )
                              .text( firstval) );
                  }
                              
               else if (z==ans.length)
              { 
            	   $("#certlist")
                   .append($("<option></option>")
                              .attr("value",polval)
                              .text( ans[z].slice(0, -1) ) );
                              }
                              else
                              {  
              $("#certlist")
                   .append($("<option></option>")
                              .attr("value",polval)
                              .text( ans[z] ) );
                              
                              }
              }
                
                },
                error: function(xhr,ThrownError) {
               
                      document.getElementById('msgdisp').style.display = 'block';

                 document.getElementById('msgdisp').innerHTML= "closing error."+xhr +' xhr.status: '+xhr.status;

            setTimeout(function () {
                          $("#msgdisp").hide(); $("#msgdisp").html('');
                      }, 5 * 1000); 
                }
             }); 
           } //if not empty
           }
           
           
           function   getcertdet(val)
           {
        	   document.getElementById("combtn").disabled=false;
        	   document.getElementById("subbtn").disabled=true;
           	 var ccode= document.getElementById("ccode").value;
           	//  var cert= document.getElementById("certlist").value;
           	  var pol= document.getElementById("polist").value;
           	   
                var url="";  
                if (  pol !== 'Select' && val !== 'Select')
                {
                url=  'MotorServlet?method=selcert&ccode='+ccode+'&cert='+val+'&polist='+pol ;
          
                $('#vmod').css("display","block");
              document.getElementById("msgdisp").style.display="Block";
              document.getElementById("msgdisp").innerHTML= "Loading . . .";
            var elemx=  document.getElementById("cubic");
         var vbod=   document.getElementById("vbod") ;
         var vmod=     document.getElementById("vmod") ;
         var vmac=   document.getElementById("vmac") ;
            $.ajax({
              
                type: 'GET',
                async:true,
                url: url , 
                
                cache:false,
               
                success: function(code) {
            
                 
                document.getElementById("msgdisp").innerHTML= " ";
                 var ans=code.split(',');
                  
                 var firstval=ans[0].substring(1, ans[0].length);
               
              document.getElementById("cubic").value=firstval;
              document.getElementById('scap').value=ans[1] ;
             
             //  document.getElementById('vbod').value=ans[2] ;
              document.getElementById('vtype').value=ans[3]  ;  
              
              
           
               onvehsel(ans[4]);
              $("select[id='vmac']").val(ans[4]); 
               
               var temp=ans[5];  
             // onmodsel(temp);
                   getmodvar(temp); 
                  
                  
                    
                    //  $("select[id='vbod']").val(ans[2]); 
                   
              
                    //vanilla javascript for selecting dropdown based on value    
                /*  var vbodopts = vbod.options;
                   for (var vbodopt, j = 0; vbodopt = vbodopts[j]; j++) {
                     if (vbodopt.value == ans[2]) {
                    
                    	 vbod.selectedIndex = j;
                       break;
                     }
                   }
                  
                   var vmacopts = vmac.options;
                   for (var vmacopt, j = 0; vmacopt = vmacopts[j]; j++) {
                     if (vmacopt.value == ans[4]) {
                  
                    	 vmac.selectedIndex = j;
                       break;
                     }
                   }
                   
                   var vmodopts = vmod.options;
                   for (var vmodopt, j = 0; vmodopt = vmodopts[j]; j++) {
                     if (vmodopt.value == ans[5]) {
                
                    	 vmod.selectedIndex = j;
                       break;
                     }
                   }*/
                    
               
              document.getElementById('modyr').value=ans[6]  ;
              document.getElementById('reg').value=ans[7] ;
              document.getElementById('chass').value=ans[8] ;
              document.getElementById('eng').value=ans[9]  ; 
              document.getElementById('color').value=ans[10]  ;
             
              var sum=ans[11];
             // alert(sum);
              var length=(sum.length)-1;
	          var max = Math.pow(10, length);
	          document.getElementById('slither').min="100000" ; 
	          document.getElementById('slither').max=max ; 
	          document.getElementById('slither').value=sum ; 
	             // document.getElementById('sumins').value=sum ;  
	          updateInput(sum.trim());
	              
              document.getElementById('ins_nm').value=ans[12]  ;
              document.getElementById('cnic').value=ans[13]  ;
              document.getElementById('cell').value=ans[14]  ;
              document.getElementById('email').value=ans[15]  ;
              document.getElementById('add').value=ans[16]   ;
              document.getElementById('vehid').value=ans[20]   ;
              modlist(temp);// for vmod
              vehtypelist(ans[20]  );
              
              
                          var trackyn=ans[17]  ;
                 var trackby=ans[18]   ;
                 var trackc=  ans[19]   ;
                 
                
                 
                 $("select[id='provlist']").val( ans[21] ); 
                 getcitylist(ans[21]);
                 $("select[id='citylist']").val( ans[22] ); 
                 getarealist(ans[22]);
                 $("select[id='arealist']").val( ans[23].slice(0,-1) ); 
               //  document.getElementById('provlist').value=ans[21]   ;
               //  document.getElementById('arealist').value=ans[22]   ;
               //  document.getElementById('citylist').value=ans[23].slice(0, -1)   ;
                
                 if (!(trackc.trim()=="N"))
                	 {
              
                	 //document.getElementById("trackc").selectedIndex = trackc;
                	 }
             
               
                  if (trackyn.trim() =="Y")
                 { 
               	 document.getElementById('trackyes').checked=true;
                	 document.getElementById('trackno').checked=false;    
                	 document.getElementById('trackClient').disabled = false; 
                     document.getElementById('trackEFU').disabled = false; 
                 }
                 
                  if (trackyn.trim() =="N")
                 {
                 
                	 document.getElementById('trackno').checked=true;                 	 
                	 document.getElementById('trackyes').checked=false;
                	 document.getElementById('trackClient').checked=false;
                	 document.getElementById('trackEFU').checked=false;
                	 document.getElementById('trackClient').disabled = true; 
                     document.getElementById('trackEFU').disabled = true; 
                 }
                 
                 if (trackby.trim() =="C"||trackby.trim()  =="L")
                 {
                  
                 document.getElementById('trackClient').checked=true;
                  document.getElementById('trackEFU').checked=false;
                 }
                   if (trackby.trim()  =="E" )
                 { 
                  document.getElementById('trackEFU').checked=true;
                 document.getElementById('trackClient').checked=false;
                 } 
               
	            
          
                }
             ,
                error: function(xhr,ThrownError) {
               
                      document.getElementById('msgdisp').style.display = 'block';

                 document.getElementById('msgdisp').innerHTML= "closing error."+xhr +' xhr.status: '+xhr.status;

            setTimeout(function () {
                          $("#msgdisp").hide(); $("#msgdisp").html('');
                      }, 5 * 1000); 
                }
             }); 
                }//if not empty selection
           }
          function onvbodsel(value)
          {
        	 
        	  
          }
           
           function vehiclemake()
           {
           	
           	$('#vmac').css("display","block");
              $('#vmac').find('option').remove();
              
              document.getElementById("msgdisp").innerHTML= "  ";
            
              $.ajax({
              
                type: 'GET',
                async:true,
                url: 'MotorServlet?method=getvehmake'  , 
                
                cache:false,
               
                success: function(code) {
              	  var lim=0; var vcode="";
              	  
               	 var ans=code.split(',');
              	 lim=ans.length-1;
                  document.getElementById("msgdisp").innerHTML= " ";
              
               
               //   document.getElementById("spinner").style.display="none";
                 
             
         $('#vmac').append($("<option></option>")
                     .attr("value",""  )
                     .text( "Select" ) ); 
                     
         var firstval =  "";
         
          for(var z=0; z<=lim; z++)
              {
       	   vcode=ans[z].split('#');
       	    // document.getElementById("msgdisp").innerHTML= vcode;
              
         if (z==0)
                  {
                   firstval = vcode[0].substring(1, ans[0].length);
                  $('#vmac').append($("<option></option>")
                              .attr("value",firstval )
                              .text( vcode[1]) );
                  }
                              
               else if (z==lim&&!z==0)
              { 
               	  $('#vmac')
                     .append($("<option></option>")
                                .attr("value",vcode[0] )
                                .text( vcode[1].slice(0, -1)) );
                              }
                              else
                              {  
                           	   $('#vmac')
                   .append($("<option></option>")
                              .attr("value",vcode[0])
                              .text( vcode[1] ) );
                              
                              }
              } 
                
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
          function  onloadfunc()
           {
           	// document.getElementById("msgdisp").innerHTML= "On Load event ";	  
           	 vehiclemake();
           	 tracking();
           }
          
          
         function vmodajax(val)
         {
        	 
        	
        	 getmodvar (val);
        	 
         }
         
         
         function getmodvar (val)
         {
        	 
        	 $('#mtype').css("display","block");
             $('#mtype').find('option').remove();
      	 var vehmk =document.getElementById("vmac").value;
             
      	       $.ajax({
                  
                  type: 'GET',
                  async:true,
                  url: 'MotorServlet?method=getmodvar&vehmd='+val +'&vehmk='+vehmk , 
                  
                  cache:false,
                 
                  success: function(code) {
               	  
                	  var lim=0; var vcode="";
                	  
                 	 var ans=code.split(',');
                	 lim=ans.length-1;
                    document.getElementById("msgdisp").innerHTML= " ";
                   //   document.getElementById("spinner").style.display="none";
                 
           $('#mtype')
            .append($("<option></option>")
                       .attr("value",""  )
                       .text( "Select" ) ); 
                       
           var firstval =  "";
           
            for(var z=0; z<=lim; z++)
                {
         	   vcode=ans[z].split('#');
         	    // document.getElementById("msgdisp").innerHTML= vcode;
                
           if (z==0)
                    {
                     firstval = vcode[0].substring(1, ans[0].length);
                    $('#mtype')
                     .append($("<option></option>")
                                .attr("value",firstval )
                                .text( vcode[1]) );
                    }
                                
                 else if (z==lim&&!z==0)
                {  
                 	  $('#mtype')
                       .append($("<option></option>")
                                  .attr("value",vcode[0] )
                                  .text( vcode[1].slice(0, -1)) );
                                }
                                else
                                {  
                 	   $('#mtype')
                     .append($("<option></option>")
                                .attr("value",vcode[0])
                                .text( vcode[1] ) );
                                
                                }
                } 
                  
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
         
          
         
         function  onmodsel(val)
         {
        	 
      	   $('#vbod').css("display","block");
             $('#vbod').find('option').remove();
      	  
      	    $.ajax({
                  
                  type: 'GET',
                  async:true,
                  url: 'MotorServlet?method=getvehbod&vehid='+val  , 
                  
                  cache:false,
                 
                  success: function(code) {
               	    
                	  var lim=0; var vcode="";
                	  
                 	 var ans=code.split(',');
                	 lim=ans.length-1;
                    document.getElementById("msgdisp").innerHTML= " ";
                
                 
                 //   document.getElementById("spinner").style.display="none";
                   
               
           $('#vbod')
            .append($("<option></option>")
                       .attr("value",""  )
                       .text( "Select" ) ); 
                       
           var firstval =  "";
           
            for(var z=0; z<=lim; z++)
                {
         	   vcode=ans[z].split('#');
         	    // document.getElementById("msgdisp").innerHTML= vcode;
                
           if (z==0)
                    {
                     firstval = vcode[0].substring(1, ans[0].length);
                    $('#vbod')
                     .append($("<option></option>")
                                .attr("value",firstval )
                                .text( vcode[1]) );
                    }
                                
                 else if (z==lim&&!z==0)
                {  
                 	  $('#vbod')
                       .append($("<option></option>")
                                  .attr("value",vcode[0] )
                                  .text( vcode[1].slice(0, -1)) );
                                }
                                else
                                {  
                 	   $('#vbod')
                     .append($("<option></option>")
                                .attr("value",vcode[0])
                                .text( vcode[1] ) );
                                
                                }
                } 
                  
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
         
         
         
         
         
         function  onvehsel(val)
          {
       	   $('#vmod').css("display","block");
              $('#vmod').find('option').remove();
       	  
       	    $.ajax({
                   
                   type: 'GET',
                   async:true,
                   url: 'MotorServlet?method=getvehmod&vehid='+val  , 
                   
                   cache:false,
                  
                   success: function(code) {
                	   
                 	  var lim=0; var vcode="";
                 	  
                  	 var ans=code.split(',');
                 	 lim=ans.length-1;
                     document.getElementById("msgdisp").innerHTML= " ";
                 
                  
                  //   document.getElementById("spinner").style.display="none";
                    
                
            $('#vmod')
             .append($("<option></option>")
                        .attr("value",""  )
                        .text( "Select" ) ); 
                        
            var firstval =  "";
            
             for(var z=0; z<=lim; z++)
                 {
          	   vcode=ans[z].split('#');
          	    // document.getElementById("msgdisp").innerHTML= vcode;
                 
            if (z==0)
                     {
                      firstval = vcode[0].substring(1, ans[0].length);
                     $('#vmod')
                      .append($("<option></option>")
                                 .attr("value",firstval )
                                 .text( vcode[1]) );
                     }
                                 
                  else if (z==lim&&!z==0)
                 { 
                  	  $('#vmod')
                        .append($("<option></option>")
                                   .attr("value",vcode[0] )
                                   .text( vcode[1].slice(0, -1)) );
                                 }
                                 else
                                 {  
                              	   $('#vmod')
                      .append($("<option></option>")
                                 .attr("value",vcode[0])
                                 .text( vcode[1] ) );
                                 
                                 }
                 } 
                   
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
         
         



         
         function  provlist()
         {
      	   $('#provlist').css("display","block");
             $('#provlist').find('option').remove();
      	  
      	    $.ajax({
                  
                  type: 'GET',
                  
                  async:true,
                  
                  url: 'MotorServlet?method=provid'   , 
                  
                  cache:false,
                 
                  success: function(code) {
                	  var lim=0; var vcode="";
                	 
                 	 var ans=code.split(',');
                	 lim=ans.length-1;
                    document.getElementById("msgdisp").innerHTML= " ";
                   
                 
                 //   document.getElementById("spinner").style.display="none";
                   
               
           $('#provlist')
            .append($("<option></option>")
                       .attr("value",""  )
                       .text( "Select" ) ); 
                       
           var firstval =  "";
           
            for(var z=0; z<=lim; z++)
                {
         	   vcode=ans[z].split('#');
         	    // document.getElementById("msgdisp").innerHTML= vcode;
                
           if (z==0)
                    {
                     firstval = vcode[0].substring(1, ans[0].length);
                    $('#provlist')
                     .append($("<option></option>")
                                .attr("value",firstval )
                                .text( vcode[1]) );
                    }
                                
                 else if (z==lim&&!z==0)
                { 
                 	  $('#provlist')
                       .append($("<option></option>")
                                  .attr("value",vcode[0] )
                                  .text( vcode[1].slice(0, -1)) );
                                }
                                else
                                {  
                     $('#provlist')
                     .append($("<option></option>")
                                .attr("value",vcode[0])
                                .text( vcode[1] ) );
                                
                                }
                } 
                  
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
         
         function  getcitylist(val)
         {
      	   $('#citylist').css("display","block");
           $('#citylist').find('option').remove();
           
      	  var provid=val;
      	   
      	        
      	         $.ajax({
                  
                  type: 'GET',
                  
                  async:true,
                  
                  url: 'MotorServlet?method=cityid&provid=' + provid , 
                  
                  cache:false,
                 
                  success: function(code) {
                	  var lim=0; var vcode="";
                	 
                 	 var ans=code.split(',');
                	 lim=ans.length-1;
                    document.getElementById("msgdisp").innerHTML= " ";
                   
                 
                 //   document.getElementById("spinner").style.display="none";
                   
               
           $('#citylist')
            .append($("<option></option>")
                       .attr("value",""  )
                       .text( "Select" ) ); 
                       
           var firstval =  "";
           
            for(var z=0; z<=lim; z++)
                {
         	   vcode=ans[z].split('#');
         	    // document.getElementById("msgdisp").innerHTML= vcode;
                
           if (z==0)
                    {
                     firstval = vcode[0].substring(1, ans[0].length);
                    $('#citylist')
                     .append($("<option></option>")
                                .attr("value",firstval )
                                .text( vcode[1]) );
                    }
                                
                 else if (z==lim&&!z==0)
                { 
                 	  $('#citylist')
                       .append($("<option></option>")
                                  .attr("value",vcode[0] )
                                  .text( vcode[1].slice(0, -1)) );
                                }
                                else
                                {  
                     $('#citylist')
                     .append($("<option></option>")
                                .attr("value",vcode[0])
                                .text( vcode[1] ) );
                                
                                }
                } 
                  
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
         
         
         function  getarealist(val)
         {
      	   $('#arealist').css("display","block");
             $('#arealist').find('option').remove();
             var cityid=val;
             
             
      	     $.ajax({
                  
                  type: 'GET',
                  
                  async:true,
                  
                  url: 'MotorServlet?method=areaid&cityid=' + cityid    , 
                  
                  cache:false,
                 
                  success: function(code) {
                	  var lim=0; var vcode="";
                	 
                 	 var ans=code.split(',');
                	 lim=ans.length-1;
                    document.getElementById("msgdisp").innerHTML= " ";
                   
                 
                 //   document.getElementById("spinner").style.display="none";
                   
               
           $('#arealist')
            .append($("<option></option>")
                       .attr("value",""  )
                       .text( "Select" ) ); 
                       
           var firstval =  "";
           
            for(var z=0; z<=lim; z++)
                {
         	   vcode=ans[z].split('#');
         	    // document.getElementById("msgdisp").innerHTML= vcode;
                
           if (z==0)
                    {
                     firstval = vcode[0].substring(1, ans[0].length);
                    $('#arealist')
                     .append($("<option></option>")
                                .attr("value",firstval )
                                .text( vcode[1]) );
                    }
                                
                 else if (z==lim&&!z==0)
                { 
                 	  $('#arealist')
                       .append($("<option></option>")
                                  .attr("value",vcode[0] )
                                  .text( vcode[1].slice(0, -1)) );
                                }
                                else
                                {  
                     $('#arealist')
                     .append($("<option></option>")
                                .attr("value",vcode[0])
                                .text( vcode[1] ) );
                                
                                }
                } 
                  
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
         //end of location lists 
         
         
          
          function  tracking()
          {
       	   $('#trackc').css("display","block");
              $('#trackc').find('option').remove();
       	  
       	    $.ajax({
                   
                   type: 'GET',
                   
                   async:true,
                   
                   url: 'MotorServlet?method=tracker'   , 
                   
                   cache:false,
                  
                   success: function(code) {
                 	  var lim=0; var vcode="";
                 	 
                  	 var ans=code.split(',');
                 	 lim=ans.length-1;
                     document.getElementById("msgdisp").innerHTML= " ";
                    
                  
                  //   document.getElementById("spinner").style.display="none";
                    
                
            $('#trackc')
             .append($("<option></option>")
                        .attr("value",""  )
                        .text( "Select" ) ); 
                        
            var firstval =  "";
            
             for(var z=0; z<=lim; z++)
                 {
          	   vcode=ans[z].split('#');
          	    // document.getElementById("msgdisp").innerHTML= vcode;
                 
            if (z==0)
                     {
                      firstval = vcode[0].substring(1, ans[0].length);
                     $('#trackc')
                      .append($("<option></option>")
                                 .attr("value",firstval )
                                 .text( vcode[1]) );
                     }
                                 
                  else if (z==lim&&!z==0)
                 { 
                  	  $('#trackc')
                        .append($("<option></option>")
                                   .attr("value",vcode[0] )
                                   .text( vcode[1].slice(0, -1)) );
                                 }
                                 else
                                 {  
                      $('#trackc')
                      .append($("<option></option>")
                                 .attr("value",vcode[0])
                                 .text( vcode[1] ) );
                                 
                                 }
                 } 
                   
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
          
            
        function  postrequest ()
        
        {
        	var ready=true;
        	var ifsuccess=false;
        	$('.required').each(function(){
                if( $(this).val() == "" ){
                  alert('Please fill all the fields');
                  ready=false;
                
                }
                else {ready=true;}    
        	});
               
        	var frm=$("#motorform");
        	 var me = $(this);
        	    
        	 document.getElementById('method').value="insert";
        	    if ( me.data('requestRunning') ) {
        	        return;
        	    }

        	    me.data('requestRunning', true);
        	if (ready)
        	    {  $.ajax({
                
            	 type: 'POST',
            	   async: true,
            	 //  data:frm.serialize()+ "&t=a",
            	//  url: 'textmatch.jsp', 
            	     data:frm.serialize(),
                    url: 'MotorServlet', 
               
                 cache:false,
                 success: function(code) {
                //	 document.getElementById('msgdisp').innerHTML= code;	 
               	// var lim=0; var vcode="";
               	 
               // 	 var ans=code.split(',');
              // 	 lim=ans.length-1;
                 
            //   	return false;
                	 ifsuccess=true;
               alert(code);
                 }
                , complete: function() {
                     me.data('requestRunning', false);
                 }
                 
                , error: function(xhr,ThrownError) {
                	 ifsuccess=false;
                       document.getElementById('msgdisp').style.display = 'block';

                  document.getElementById('msgdisp').innerHTML= "closing error."+xhr +' xhr.status: '+xhr.status;

               setTimeout(function () {
                           $("#msgdisp").hide(); $("#msgdisp").html('');
                       }, 5 * 1000); 
                 }  
              }); 
        	    
        	    location.reload();
        	 /* if (ifsuccess){   alert("Check certificate List");
        	    }
        	  else {
        		  alert("Apparently Something went wrong with submission. Check Certificate List");
        		  
        	  }*/
        	    
        	    }
        	
        	/*document.getElementById("method").value="postreq";
        	 var http = new XMLHttpRequest();
        	    http.open("POST", "MotorServlet", true);
        	    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        	   // var params = "search=" + <<get search value>>; // probably use document.getElementById(...).value
        	    http.send(frm.serialize());
        	    http.onload = function() {
        	    	 document.getElementById('msgdisp').innerHTML= http.responseText;
        	    	  
                         }
        	  return false;*/
        	  
        }
        
         function  putrequest ()
        
        {
               
        	var frm=$("#motorform");
        	 var me = $(this);
        	 var  certext= $("#certlist option:selected").text();
        	var selcert= $("#certlist option:selected").val();
        	 
        	var pol= document.getElementById('polist').value;
        	var ccode= document.getElementById('ccode').value;
        	 document.getElementById('method').value="update";
        	 
        	    if ( me.data('requestRunning') ) {
        	        return;
        	    }

        	    me.data('requestRunning', true);
        	 
        	    $.ajax({
                
            	 type: "PUT",
            	   async: true,
              	 //  data:frm.serialize(),
            	   url: 'MotorServlet?selcert='+selcert +'&pol='+pol+'&ccode='+ccode , 
            	//   url: 'MotorServlet',
            	   // cache:false,
                   success: function(code) {
                 	// document.getElementById('msgdisp').innerHTML= "Successful Final Commit with following certificate # :  "+selcert;	 
                     
                	   window.open(code); 
                    	
                    	
                    	setTimeout(function () {
                           $("#msgdisp").hide(); $("#msgdisp").html('');
                       }, 10 * 1000); 	 
                
                 	 
                   }
                  , complete: function() {
                       me.data('requestRunning', false);
                   }
                   
                  , error: function(xhr,ThrownError) {
                  
                         document.getElementById('msgdisp').style.display = 'block';

                    document.getElementById('msgdisp').innerHTML= "closing error."+xhr +' xhr.status: '+xhr.status;

                 setTimeout(function () {
                             $("#msgdisp").hide(); $("#msgdisp").html('');
                         }, 5 * 1000); 
                   }  
                });  
        	
        	 
        }
        
        
                