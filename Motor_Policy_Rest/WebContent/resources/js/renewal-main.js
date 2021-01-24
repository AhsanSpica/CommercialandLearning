 
// $(document).ready(function(){


//});

  $('#dd').calendar({

  trigger: '#trans_dt'
});
   
  
  
//   $('#container').calendar({
//  data: [
//    {
//      date: '2015/12/24',
//      value: 'Christmas Eve'
//    },
//    {
//      date: '2015/12/25',
//      value: 'Merry Christmas'
//    },
//    {
//      date: '2016/01/01',
//      value: 'Happy New Year'    }
//  ]
//});


    function update (evt)
    {
    var name =evt.name;
    
    document.getElementsByName(name)[1].value=evt.value;
    }
    
    
     function bodyonload()

   {
   var element=document.getElementById("checky");
    element.checked=false;
   //document.getElementById("verbtn").disabled=true;  
    document.getElementById('subbtn').disabled = false;
   
   }
   
function checky() 

{ 
 //   document.getElementById('verbtn').disabled = !this.checked;
  //  document.getElementById('sub_btn').disabled = !this.checked;
  //  alert("   ");
   var element=document.getElementById("checky");
   var checked=element.checked;
 
    document.getElementById('sub_btn').disabled = !checked;
  
}


     function showdialog(){    $( "#dialog" ).dialog({
         position: 'center',
        maxWidth:1000,
                    maxHeight: 1000,
                    width: 650,
                    height: 800,
        overflow:'scroll',
        show: {
effect: "fade",
duration: 360
},
hide: {
effect: "fade",
duration: 400
},
    buttons: [
       
        {
            text: "Close",
            click: function() {
                $( this ).dialog( "close" );
                 $( "#dialog" ).hide();
            }
        }
    ]
});

}
      
  function usersumcheck(evt)
   {
   var val=evt.value;
   var oldsum= document.getElementById("sumins").value;
  var realval= oldsum-((oldsum)/10);
 var twenty=(realval*2)/10;
  var upper= ((realval*1)+((realval*2)/10) );
 var lower=  (realval-twenty);
    
     //alert("dep old sum "+realval+" val "+val+" twenty "+twenty+"upper " +upper+ " lower "+lower);
    if (isNaN(evt.value) ) {
        evt.value=realval;
     return false; 
     }
     else if ( val>upper) {    
        evt.value=realval;
     return false;
     }
     
  else  if(  val<lower )
    {
      evt.value=realval;
    return false;
    }
   
 // else {  sumonchange();}
   return;
   }
   
  function checkmob(el) {
  if ((el.value.length < 11)||isNaN(el.value)||(el.value.length>11)||!el.value.startsWith('03')) {
    el.value='';
    return false; 
  }
  return true;
}


    function regcheck(el)
{
   var oldreg=document.getElementById("reg_temp").value;
 var patt = new RegExp("([A-Za-z]{1,3}[-][0-9]{3,9})|([A-Za-z]{2,3}[-][0-9]{2,3}[-][0-9]{2,9})");
 var res =patt.test(el.value);
 // alert(res +" truth value "+(oldreg!="APPLIED"||oldreg!="applied" ||oldreg!="Applied"));
   
 if (res==false){
   if(el.id=="regin")
   {  el.value = '';
   }
   else { 
   el.value = oldreg;
   }
     return false;
     }
     
     if (oldreg!="APPLIED"&&oldreg!="applied" &&oldreg!="Applied")
     {
    
//  if(el.id=="regin")
//   {  el.value = '';}
//   else { 
   el.value = oldreg;
 //  }
     }
    
}

function emailcheck(el)
     {  var patt = new RegExp("[^@]+@[^@]+\.[a-zA-Z]{2,6}");
    var res = patt.test(el.value);
     if (res===false){
     document.getElementById("email").value = '';
     return false;}
     return true;
     }
function z(evt)
{

var z=evt.value;
//alert(z);
var x=z.replace(/\b./g, function(m){ return m.toUpperCase(); });
//alert(x);
evt.value=x;
}
   function addcheck(evt)
{
var charCode = (evt.which) ? evt.which : event.keyCode

   if(charCode != 58&&charCode != 59&&charCode != 32
   &&charCode!=40&&charCode!=41&&charCode!=43&&charCode!=45&&charCode!=46&&charCode!=47)
    {if (charCode > 31 && 
    ((charCode < 48 || charCode > 57)&&(charCode < 65 || charCode > 90)&&(charCode < 97 || charCode > 122)))
       { return false;}}
       
return true;
}
       function range(evt)
 {
 var dsum=  document.getElementById('nsum_ins').innerHTML;
 var  sum=  document.getElementById('sumins').innerHTML;
 
alert("dep. sum "+dsum+"sum  "+sum+"sum  "+sum);
// alert(evt.value>=dsum  &&evt.value<=sum);
 
   if (evt.value>=dsum  &&evt.value<=sum)
   { 
   return true;}
   else{ 
   document.getElementById('usersum').value=dsum;
   return false};
    getvalid();
 }       
   //    $('#regin,#engin,#chassin').change(function(){  
     
 
     
     
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
//                   if( el.value<upperlimit&&el.value>lowlimit)
//                   {  
//                   return ;
//                   }
//                   else {
//                   el.value='';
//                   return false;
//                   }
                   
        //           } 
  
  
}
     function nickeypress(el)
{
 var length = el.value.length; 
 if(length == 5 || length == 13)
   { el.value=el.value+'-';
  
   }
}
  function checkLength(el) {
  if (el.value.length != 15) {
    el.value='';
    return false; 
  }
  return true;
}   
  