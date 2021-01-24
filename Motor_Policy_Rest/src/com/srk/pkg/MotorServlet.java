package com.srk.pkg;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import oracle.jdbc.driver.OracleDriver;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
 
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

//import com.oracle.webservices.impl.internalapi.session.manager.Session;

import java.io.*;


/**
 * Servlet implementation class MotorServlet
 */
@WebServlet("/MotorServlet")
public class MotorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MotorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    
    
    protected synchronized void  doGet( HttpServletRequest request, HttpServletResponse response) 
			throws   ServletException, IOException,NullPointerException
 {
		 
		// list of type model object
		 ArrayList<Result> list1 = new ArrayList<>();
		 
		 int ccode =0000;
	//  String method = request.getParameter("method").toString();
		 //obviously if you try to change parameter to string upon page load the value will be null and it will throw exception
	
	   
	 Connection connection = null;
            ArrayList<String> list2 = new ArrayList<String>();
            ResultSet rs=null;
             Statement statement=null;
         String Query=""; String Query2="";
         
     	  String clnm=""; String rsdt=""; String rset=""; String days=""; String polist="";String cert="";String netrt="";String efurt="";String trackerrt="";
     	 String coinsbrid="";
     	 String coinshare="";
     	 String   zoneid="" ;
     	 String  provid ="";
     	 String    docid ="";
     	 String    COMP_ID="" ;  String    veh_type="" ;
       String method="";
      method= request.getParameter("method");
	
       try{    
    	   Class.forName("oracle.jdbc.driver.OracleDriver");
         connection =
        		 //DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis"); 
         DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test", "efu_gis", "test");
       }
       catch (Exception exp) {
      	 Result category3 = new Result( 001  , exp.getMessage());
      	 list1.add(category3);
           }
       
       
         if (method.equals("verify") )
         {
        	  ccode =  Integer.parseInt(request.getParameter("ccode").toString());
        	
        	Query =  "  select  (uw.BRANCH_ID || uw.CLASS_ID|| TRIM(to_char(uw.POLICY_NO,'000000')) ";
        	Query =Query +" || '/' || to_char(uw.ISSUE_DATE,'mm')||'/' ||uw.ISSUE_YR  ) AS Policy_No  ";
        	Query =Query + " ,  uw.POLICY_NO as polno  ";
        	 Query =Query + " from uw_main  uw ";
        	Query =Query + " left join  motor_main mm on uw.uw_doc_id=mm.uw_doc_id    "; 
            Query =Query + " left join  EFU_mast.CLIENTS cl on uw.CLIENT_ID=cl.CLIENT_ID    ";
             Query =Query + "  where  ";
         //   Query =Query + " uw.sub_class_type_id in (1012,1032)  and";
            Query =Query + "    uw.policy_type = 'P' and uw.declaration>1  and class_id=4";
           // Query =Query + "    and (uw.risk_end_date - (uw.risk_days - 1)) = uw.risk_start_date ";
            Query =Query + " and mm.OPEN_NONOPEN='O' and uw.risk_end_date > trunc(sysdate)  ";
            Query =Query + " and TRIM(uw.CLIENT_ID)=TRIM("+ccode+ ")  ";
             //   Query =Query + "         order by uw.branch_id,uw.client_id,uw.policy_no  ";
             
         // DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test", "efu_gis", "test");
      try {  statement = connection.createStatement();
        
                    rs = statement.executeQuery(Query);
          
         while (rs.next()) {
        	 
        	 list2.add(rs.getString(1));
         	
         } 
      
         }
         catch(Exception ex)
         {
         	
         	list2.add("this is error for list "+ ex.getMessage());
         }
         
         request.setAttribute("ResultList", list1);
         PrintWriter pwout= response.getWriter();
         pwout.print(list2);
        }
      
         
         if  (method.equals("getcert")   )
         {
        	polist= request.getParameter("polist").toString();
        	 ccode =  Integer.parseInt(request.getParameter("ccode").toString());
                         
        	  Query2 =Query2  + "	 select  "; 
        	Query2 =Query2  + "	  nvl(to_char( uw.RISK_START_DATE,'dd/mm/yyyy' ) ,'not defined') rsdt";
        	Query2 =Query2  + " , nvl(TRIM(cl.client_nm),'-') as client_nm 	 ";
        	Query2 =Query2  + "	, nvl(to_char(uw.RISK_END_DATE,'dd/mm/yyyy' ) ,'not defined')  as rset ";
        	Query2 =Query2  + "	, (uw.risk_end_date-risk_start_date+1) as  no_of_days "; 
        	Query2 =Query2  + " , nvl(mm.TRACKER_RATE,0) TRACKER_RATE, nvl(mm.EFU_RATE,0) EFU_RATE, nvl(mm.NET_RATE,0) NET_RATE     ";
        	
        	Query2 =Query2  +   " , nvl(p.COINS_BRANCH_ID,0) coinsbrid , nvl(p.COINS_SHARE,0) coinshare  "   ;    
        	Query2 =Query2  +   " , DECODE(cib.ZONE_ID,null,br.ZONE_ID) zoneid   "   ;
        	Query2 =Query2  +    " , DECODE(cib.PROVINCE_STATE_ID , null, br.PROVINCE_STATE_ID) provid  ";
        	Query2 =Query2  +   " , uw.uw_doc_id , uw.COMP_ID  ";
        	Query2 =Query2  +   "  , nvl(case when md.vehicle_type = 1 then 'Car'   ";
        	Query2 =Query2  +   "    when md.vehicle_type = 2 then 'Motor Cycle'   ";
        	Query2 =Query2  +   "   when md.vehicle_type = 3 then 'Others'  end,'-'  ) as veh_type";
        	Query2 =Query2 	+ " from uw_main uw join EFU_MAST.CLIENTS cl  on uw.CLIENT_ID=cl.CLIENT_ID";
        	Query2 =Query2  +  " left join  motor_main mm on uw.uw_doc_id=mm.uw_doc_id    "; 
        	Query2 =Query2  +  " left join PREM_SHARES p on (  mm.UW_DOC_ID = p.UW_DOC_ID   "   ;
        	Query2 =Query2  +   "and p.coins_branch_id = 110000   ) ";
        	Query2 =Query2  +   " left  join EFU_MAST.BRANCHES br on (uw.BRANCH_ID = br.BRANCH_ID and uw.comp_id = br.comp_id)   "   ;
        	Query2 =Query2  +  "  left join EFU_MAST.COINS_BRANCHES cib on (br.province_state_id   "   ;
        	Query2 =Query2  +  "= cib.province_state_id and p.COINS_BRANCH_ID = cib.COINS_BRANCH_ID) ";       
        	
        	  Query2 =Query2 + " left join  motor_main mm on uw.uw_doc_id=mm.uw_doc_id    ";
        	  Query2 =Query2 + "  full join efu_mast.vehicle vh on  mm.vehicle_id = vh.VEHICLE_ID "; 
        			  Query2 =Query2 + "     full join   efu_mast.veh_model md on vh.model_id = md.model_id ";
        	  
        	Query2 =Query2  +  " where uw.POLICY_TYPE='P' and TRIM(uw.POLICY_NO)= TRIM(' "+polist+"') and TRIM(cl.CLIENT_ID)=TRIM('"+ccode+"') "; 
        	Query2 =Query2  +  " and mm.OPEN_NONOPEN='O' and rownum=1 ";
        	
        	/*HttpSession oldsession = request.getSession(false);
        	if (oldsession != null)
        	{        		
        		oldsession.invalidate();        		        		
        	}*/
        	
        	HttpSession newSession = request.getSession(true);
        	newSession.setMaxInactiveInterval(5*60);
        	
        	
        try {	
        	  statement = connection.createStatement();
              rs = statement.executeQuery(Query2);
              list2.clear();
              
              while (rs.next()) {
            
         clnm=rs.getString("client_nm");
         rsdt=rs.getString("rsdt");
      	 rset= rs.getString("rset");
      	 days= rs.getString("no_of_days");
          netrt=rs.getString("NET_RATE");
          trackerrt=rs.getString("TRACKER_RATE");
          efurt=rs.getString("EFU_RATE");
            coinsbrid=  rs.getString("coinsbrid") ;
      	   coinshare=  rs.getString("coinshare");
      	     zoneid=  rs.getString("zoneid");
      	    provid =  rs.getString("provid");
      	      docid = rs.getString("uw_doc_id");
      	      COMP_ID= rs.getString("COMP_ID") ; 
      	    veh_type= rs.getString("veh_type") ;    
            }
              } /*end of try block*/
        catch(Exception ex)
        {
              	
              	list2.add("this is error for pol info "+ ex.getMessage());
              }
        
                         list2.add(clnm);  
                         list2.add(rsdt); 
                         list2.add(rset); 
                         list2.add(days);  
                         list2.add(netrt); 
                         list2.add(veh_type); 
                         newSession.setAttribute("netrt", netrt);
                         newSession.setAttribute("trackerrt", trackerrt);
                         newSession.setAttribute("efurt", efurt);
                         newSession.setAttribute("coinsbrid", coinsbrid);
                         newSession.setAttribute("coinshare", coinshare);
                         newSession.setAttribute("zoneid", zoneid);
                         newSession.setAttribute("provid", provid );
                         newSession.setAttribute("docid", docid );
                         newSession.setAttribute("compid", COMP_ID );
                          
			       Query2 =  "  select  ";
			       Query2=Query2+ " (uw.BRANCH_ID || uw.CLASS_ID|| TRIM(to_char(uw.CERTIFICATE_NO,'000000')) || '/' || to_char(uw.ISSUE_DATE,'mm')||'/' ||uw.ISSUE_YR  )";
			       Query2 =Query2	+ " AS  certfull ";
			       Query2 =Query2	+ "   from uw_main uw  ";
			       Query2 =Query2	+ "   join EFU_MAST.CLIENTS cl on uw.CLIENT_ID=cl.CLIENT_ID ";
                   Query2 =Query2	+ "      full join motor_main mm on uw.UW_DOC_ID = mm.uw_doc_id  ";
                   Query2 =Query2  + " where   uw.POLICY_TYPE='M' and TRIM(uw.POLICY_NO)= TRIM(' "+ polist +"')   ";
                   Query2 =Query2  + "  and TRIM(cl.CLIENT_ID)=TRIM('"+ccode+"' )  ";
                   Query2 =Query2	+ "  and mm.CHASIS_NO is not null  ";
                   Query2 =Query2	+ "  and uw.BRANCH_ID is not null and  uw.CLASS_ID is not null ";
                    	
                    	
                    try { 
                    	statement = connection.createStatement();
                                     rs = statement.executeQuery(Query2);
                                     
                                     while (rs.next()) {
                                    	// Result certlist = new Result( rs.getInt(1)  , rs.getString(3));
                                   	 // list4.add(certlist);
                     
                                list2.add(rs.getString(1));
                                     }                           
                    }
                    catch(Exception ex)
                    {
                    	
                    	list2.add("this is error for list "+ ex.getMessage());
                    }
                       
        
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
         
         }
         
          if  (method.equals("selcert")   )
         {
        	cert= request.getParameter("cert").toString();
        	polist= request.getParameter("polist").toString();
        	 ccode =  Integer.parseInt(request.getParameter("ccode").toString());
                         
        	   
        	    Query2 = " select TRIM(vh.sitting_capacity )  sitting_capacity ";
        	    Query2 =Query2  + " , TRIM(vh.cubic_capicity_p) cubic_capicity ";
        	 //   Query2 =Query2  + "  , TRIM(mk.DESCRIPTION ) v_make ";
        	    Query2 =Query2  + "  , vh.MAKE_ID v_make"; 
        	    Query2 =Query2  + "  , TRIM(md.MODEL_ID ) v_model ";
        	 //   Query2 =Query2  + "  , TRIM(md.DESCRIPTION ) v_model_desc ";  
        		Query2 =Query2  + "   ,TRIM( mm.MODEL_YEAR) MODEL_YEAR ";
        		Query2 =Query2  + "   ,TRIM(mm.REG_NO) REG_NO ";
        		Query2 =Query2  + "   ,TRIM(mm.CHASIS_NO) CHASIS_NO "; 
        		Query2 =Query2  + "    ,TRIM(mm.ENGINE_NO) ENGINE_NO ";
        		Query2 =Query2  + " ,TRIM(cr.COLOR_DETAIL) color ";
        	    Query2 =Query2  + " , TRIM(bd.description) body_type ";
        	    Query2 =Query2  + " , TRIM(uw.SUMINSURED) SUMINSURED ";
        		Query2 =Query2  + " , case when md.vehicle_type = 1 then 'Car'   ";
            	Query2 =Query2  + "         when md.vehicle_type = 2 then 'Motor Cycle'   ";
            	Query2 =Query2  + "     when md.vehicle_type = 3 then 'Others' ";
            	Query2 =Query2  + "   end as vehicle_type ";
            	Query2 =Query2  + "  , TRIM(vh.metric_tons) metric_tons";
            	
            	Query2 =Query2  + " , TRIM(uw.INSURED_NM) INSURED_NM, uw.INSURED_CNIC, uw.HOLDER_CELL, uw.HOLDER_EMAIL   ";
            	Query2 =Query2  + " ,  uw.AREA_CD , uw.PROVINCE_CD, uw.CITY_CD   ";
            	Query2 =Query2  + " , REPLACE(( TRIM(uw.ADDRESS_1) || TRIM(uw.ADDRESS_2)|| TRIM(uw.ADDRESS_3)),',',' ') as cl_add  ";
            	
            	Query2 =Query2  + " , mm.TRACKER_INSTALLED, mm.TRACKER_INSTALLED_BY, nvl(tc.COMPANY_NM,'N' ) COMPANY_NM";
            	Query2 =Query2  + " , vh.vehicle_id ";
            	Query2 =Query2  + "  from efu_mast.veh_make mk, efu_mast.veh_model md, efu_mast.vehicle vh, ";
            	Query2 =Query2 	+ " efu_mast.veh_body_type bd, motor_main mm, uw_main uw, EFU_MAST.CLIENTS cl,  EFU_MAST.COLOR cr ,EFU_MAST.TRACKER_COMPANY tc ";
            	Query2 =Query2  + "  where ";
            	Query2 =Query2  + "  vh.vehicle_id = mm.VEHICLE_ID ";
            	Query2 =Query2  + "  and uw.POLICY_TYPE='M' and mm.UW_DOC_ID=uw.UW_DOC_ID ";
            	Query2 =Query2  + "and uw.CLIENT_ID=cl.CLIENT_ID   and mm.COLOR_ID =cr.COLOR_ID and vh.make_id = mk.make_id ";
            	//Query2 =Query2  + "  and vh.make_id = md.make_id ";
            	
            	Query2 =Query2  + "   and mm.TRACKER_COMP_ID = tc.TRACKER_COMP_ID  (+) ";
            	Query2 =Query2  + "  and vh.body_type_id = bd.body_type_id ";
            	Query2 =Query2  + "  and md.model_id = vh.model_id ";
                Query2 =Query2  + " and TRIM (uw.CERTIFICATE_NO) = TRIM ('"+cert+"' )  ";
               // Query2 =Query2  + "  and TRIM (cl.CLIENT_ID) = TRIM ('"+ccode+"' )";
                Query2 =Query2  + "    and TRIM (uw.POLICY_NO) = TRIM ('"+polist+"')";
             
            try {
            	statement = connection.createStatement();
              rs = statement.executeQuery(Query2);
              list2.clear();
              
              while (rs.next()) {
            
        
            	  		list2.add(rs.getString("cubic_capicity")); 
            	  		 list2.add(rs.getString("sitting_capacity"));  
            	  		list2.add(rs.getString("body_type"));  
            	  		list2.add(rs.getString("vehicle_type")); 
            	  		
            	  		list2.add(rs.getString("v_make"));  
            	  		list2.add(rs.getString("v_model"));  
            	  		list2.add(rs.getString("MODEL_YEAR"));  
            	  		list2.add(rs.getString("REG_NO"));  
            	  		list2.add(rs.getString("CHASIS_NO"));  
            	  		list2.add(rs.getString("ENGINE_NO")); 
            	  		list2.add(rs.getString("color"));
            	  		list2.add(rs.getString("SUMINSURED"));
            	  		
            	  		list2.add(rs.getString("INSURED_NM"));
            	  		list2.add(rs.getString("INSURED_CNIC"));
            	  		list2.add(rs.getString("HOLDER_CELL"));
            	  		list2.add(rs.getString("HOLDER_EMAIL"));
            	  		list2.add(rs.getString("cl_add"));
            	  		
            	  		list2.add(rs.getString("TRACKER_INSTALLED"));
            	  		list2.add(rs.getString("TRACKER_INSTALLED_BY"));
            	  		list2.add(rs.getString("COMPANY_NM"));
            	  		list2.add(rs.getString("vehicle_id"));
            	  		
            	  		list2.add(rs.getString("PROVINCE_CD"));
            	  		list2.add(rs.getString("CITY_CD"));
            	  		list2.add(rs.getString("AREA_CD"));
            	  		
            	  		
            	  		//list2.add("metric_tons"); 
            	  		//list2.add("sitting_capacity");   
                        
              }
              }
          catch(Exception ex)
          {
          	
          	list2.add("this is error for list "+ ex.getMessage());
          }
                       
                     
                  
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
         
         } 
         
        
          if (method.equals("provid") ) {
        	 
              
        	  list2.clear();   
          	
          	String Querytrack =  " select ps.PROVINCE_NM,ps.PROVINCE_STATE_ID,ps.PROVINCE_STATE_CD from EFU_MAST.PROVINCE_STATE ps order by ps.PROVINCE_NM asc ";
             
          	 try { Statement  statement2 = connection.createStatement();
          
                ResultSet  res = statement2.executeQuery(Querytrack);
            
           while (res.next()) {
        	  
           	    list2.add(res.getString(2)+"#"+res.getString(1));
         } }
             catch(Exception ex)
             {
             	
             	list2.add("this is error for list "+ ex.getMessage());
             }
            
           PrintWriter pwout= response.getWriter();
           pwout.print(list2);
          }
          
         
          if (method.equals("cityid") ) {
        	 String provcd="";
        	 provcd= request.getParameter("provid").toString();
              
        	  list2.clear();   
          	
          	String Querytrack =  " select ct.CITY_NM,ct.CITY_ID from EFU_MAST.CITY ct where ct.PROVINCE_STATE_ID='"+provcd+"' order by ct.CITY_NM  asc ";
             
          	 try { Statement  statement2 = connection.createStatement();
          
                ResultSet  res = statement2.executeQuery(Querytrack);
            
           while (res.next()) {
        	  
           	    list2.add(res.getString(2)+"#"+res.getString(1));
         } }
             catch(Exception ex)
             {
             	
             	list2.add("this is error for list "+ ex.getMessage());
             }
            
           PrintWriter pwout= response.getWriter();
           pwout.print(list2);
          }
          
          
          if (method.equals("areaid") ) {
        	  String citycd="";
        	  citycd=  request.getParameter("cityid").toString();
              
        	  list2.clear();   
        	  
          	
          	String Querytrack =  " select ar.AREA_NM,ar.AREA_ID from EFU_MAST.AREA ar where ar.CITY_ID=' "+citycd+ "' order by ar.AREA_NM asc  ";
             
          	 try { Statement  statement2 = connection.createStatement();
          
                ResultSet  res = statement2.executeQuery(Querytrack);
            
           while (res.next()) {
        	  
           	    list2.add(res.getString(2)+"#"+res.getString(1));
         } }
             catch(Exception ex)
             {
             	
             	list2.add("this is error for list "+ ex.getMessage());
             }
            
           PrintWriter pwout= response.getWriter();
           pwout.print(list2);
          }
         
         if (method.equals("tracker") ) {
          
        	  list2.clear();   
          	
          	String Querytrack =  "select Distinct TRIM(tc.COMPANY_NM),TRIM(tc.COMP_ID) from EFU_MAST.TRACKER_COMPANY tc order by TRIM(tc.COMPANY_NM) asc ";
             
          	 try { Statement  statement2 = connection.createStatement();
          
                ResultSet  res = statement2.executeQuery(Querytrack);
            
           while (res.next()) {
        	  
           	    list2.add(res.getString(2)+"#"+res.getString(1));
         } }
             catch(Exception ex)
             {
             	
             	list2.add("this is error for list "+ ex.getMessage());
             }
            
           PrintWriter pwout= response.getWriter();
           pwout.print(list2);
          }
         
         
         if  (method.equals("getvehmake")   )
         {
        	
        	 list2.clear();     
                 
                   Query2 =  "  select Trim(vh.DESCRIPTION) DESCRIPTION,vh.MAKE_ID from EFU_MAST.VEH_MAKE vh order by  vh.DESCRIPTION ASC ";
                    	
                    try {     statement = connection.createStatement();
                                     rs = statement.executeQuery(Query2);
                                     
                                     while (rs.next()) {
                                   
                                list2.add(rs.getString(2)+"#"+rs.getString(1));
                                     }                           
                    }
                    catch(Exception ex)
                    {
                    	
                    	list2.add("this is error for list "+ ex.getMessage());
                    }
         
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
        
         }
         
         if  (method.equals("getvehmod")   )
         {
        	 String vehid =   request.getParameter("vehid").toString() ;
              
        	 list2.clear(); 
        	 		 
        	 Query2 =  " select TRIM(vm.DESCRIPTION) ,TRIM( vm.MODEL_ID) from efu_mast.VEH_MODEL vm where ";
        	 Query2 = Query2+" TRIM(vm.MAKE_ID)=TRIM('"+vehid+"')  order by  vm.DESCRIPTION ASC  ";
                   
                    	
                    try {     statement = connection.createStatement();
                                     rs = statement.executeQuery(Query2);
                                     
                                     while (rs.next()) {
                                    	// Result certlist = new Result( rs.getInt(1)  , rs.getString(3));
                                    //	 list4.add(certlist);
                     
                                list2.add(rs.getString(2)+"#"+rs.getString(1));
                                     }                           
                    }
                    catch(Exception ex)
                    {
                    	
                    	list2.add("this is error for list "+ ex.getMessage());
                    }
                       
       //  request.setAttribute("certlist", list4);
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
        // RequestDispatcher dispatcher2 = request.getRequestDispatcher("index.jsp");
        //  dispatcher2.forward(request, response);
      	  
         }
         if  (method.equals("getcap")   )
         {
        	 String vehid =   request.getParameter("vehid").toString() ;
              
        	 list2.clear(); 
        	 		 
        	 Query2 = "select vh.VEHICLE_ID , TRIM(vh.CUBIC_CAPICITY_P) cubcap , TRIM(vh.SITTING_CAPACITY) sitcap from efu_mast.vehicle vh where vh.VEHICLE_ID= ";
        	 Query2 = Query2+" TRIM('"+vehid+"') ";
                   
                    	
                    try {     statement = connection.createStatement();
                                     rs = statement.executeQuery(Query2);
                                     
                                     while (rs.next()) {
                                    	// Result certlist = new Result( rs.getInt(1)  , rs.getString(3));
                                    //	 list4.add(certlist);
                               list2.add(rs.getString(2) ); //cubic
                                list2.add(rs.getString(3) ); //sitting
                                     }                           
                    }
                    catch(Exception ex)
                    {
                    	
                    	list2.add("this is error for list "+ ex.getMessage());
                    }
                       
       //  request.setAttribute("certlist", list4);
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
        // RequestDispatcher dispatcher2 = request.getRequestDispatcher("index.jsp");
        //  dispatcher2.forward(request, response);
      	  
         }
         
         
        
         if  (method.equals("getmodvar")   )
         {
        	 String vehmd =   request.getParameter("vehmd").toString() ;
        	 String vehmk =   request.getParameter("vehmk").toString() ;
        	 list2.clear();  
        	 Query2 =  "  SELECT VEHICLE.VEHICLE_ID, VEHICLE.DESCRIPTION,VEHICLE.tracker ";
        	Query2 = Query2+ "     FROM efu_mast.VEHICLE ";
        	Query2 = Query2+ "     where VEHICLE.make_id = "+vehmk+" ";
        	Query2 = Query2+ "   and VEHICLE.model_id =   "+vehmd+ " ";
        	Query2 = Query2+ "   and status <> 'D' ";
        	Query2 = Query2+ "   order by VEHICLE.DESCRIPTION ";
        											 
        	// Query2 =  " vb.BODY_TYPE_ID,TRIM(vb.DESCRIPTION) body_desc from EFU_MAST.VEH_BODY_TYPE vb join EFU_MAST.VEHICLE";
        	// Query2 = Query2+ "  vh on vb.BODY_TYPE_ID=vh.BODY_TYPE_ID where  ";
        	// Query2 = Query2+ "  TRIM(vh.VEHICLE_ID)=TRIM('"+vehmd+"')  order by  vh.DESCRIPTION ASC ";
                    	
                    try {     statement = connection.createStatement();
                                     rs = statement.executeQuery(Query2);
                                     
                                     while (rs.next()) {
                                    	// Result certlist = new Result( rs.getInt(1)  , rs.getString(3));
                                    //	 list4.add(certlist);
                     
                                list2.add(rs.getString(1)+"#"+rs.getString(2));
                                     }                           
                    }
                    catch(Exception ex)
                    {
                    	
                    	list2.add("this is error for list "+ ex.getMessage());
                    }
                       
       //  request.setAttribute("certlist", list4);
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
        // RequestDispatcher dispatcher2 = request.getRequestDispatcher("index.jsp");
        //  dispatcher2.forward(request, response);
      	  
         }
         
         if  (method.equals("getvehbod")   )
         {
        	 String vehid =   request.getParameter("vehid").toString() ;
              
        	 list2.clear();  
        	  
        	 Query2 =  " vb.BODY_TYPE_ID,TRIM(vb.DESCRIPTION) body_desc from EFU_MAST.VEH_BODY_TYPE vb join EFU_MAST.VEHICLE";
        	 Query2 = Query2+ "  vh on vb.BODY_TYPE_ID=vh.BODY_TYPE_ID where  ";
        	 Query2 = Query2+ "  TRIM(vh.VEHICLE_ID)=TRIM('"+vehid+"')  order by  vh.DESCRIPTION ASC ";
                    	
                    try {     statement = connection.createStatement();
                                     rs = statement.executeQuery(Query2);
                                     
                                     while (rs.next()) {
                                    	// Result certlist = new Result( rs.getInt(1)  , rs.getString(3));
                                    //	 list4.add(certlist);
                     
                                list2.add(rs.getString(1)+"#"+rs.getString(2));
                                     }                           
                    }
                    catch(Exception ex)
                    {
                    	
                    	list2.add("this is error for list "+ ex.getMessage());
                    }
                       
       //  request.setAttribute("certlist", list4);
      	 PrintWriter pwout= response.getWriter();
         pwout.print(list2); 
        // RequestDispatcher dispatcher2 = request.getRequestDispatcher("index.jsp");
        //  dispatcher2.forward(request, response);
      	  
         }

        
	
	//Result   category = new Result(ccode, "Some Client");
	//  list1.add(category);
	 	  
	} 
	  
	
    /**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
   /** protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
    	 response.setContentType("application/pdf");
    	 String imageDataString="";
    	ArrayList<String> postresult = new ArrayList<String>();
    	   try {           
               
               URL url = new URL("http://apps2.efuinsurance.com/reports/rwservlet?cmdkey=etuwprod&3990016344");
                InputStream is = url.openStream();
             // FileInputStream imageInFile = new FileInputStream(is.toString());
            //   String path = "/WebContent/resources/files/test.pdf";
            //  String realPath = request.getServletContext().getRealPath(path);
              
               
            //  InputStream  imageInFile = new FileInputStream(realPath);
             
               byte imageData[] = new byte[16384];
              // imageInFile.read(imageData);
               is.read(imageData);
       
               // Converting Image byte array into Base64 String
              //   imageDataString =Base64.encodeBase64(imageData);  
               imageDataString =new sun.misc.BASE64Encoder().encode(imageData);      
             //  System.out.println("imageDataString : " + imageDataString);
            postresult.add(imageDataString);
            }
    	  // catch (FileNotFoundException e) {
    	//	   postresult.add(e.getMessage());
          // } 
    	//   catch (Exception ioe) {
    	//	   postresult.add(ioe.getMessage());
        //   }
    	  catch(UnsupportedEncodingException e) {
    		    postresult.add(e.getMessage());
            }
    	
    	int ccode =  Integer.parseInt(request.getParameter("ccode").toString());
    	int  polist= Integer.parseInt(request.getParameter("pol").toString());
    	int cert= Integer.parseInt(request.getParameter("selcert").toString());
    	 
      /*     try{ 
        	   
        	 
		     Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection	 connection =
			    		 DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test", "efu_gis", "test"); 
			 //DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis"); 
			

            String  Query =  "  update uw_main uw set uw.policy_type='C' where ";
             Query = Query+ "   TRIM(uw.CERTIFICATE_NO)=TRIM('"+cert+"') and TRIM(uw.POLICY_NO)=trim('"+polist+"')";
             
             Query = Query+ "  and trim (uw.CLIENT_ID) = TRIM('"+ccode+"')";
		     
        Statement     statement = connection.createStatement();
                   
             statement.executeUpdate(Query);            
              postresult.add( "Success" ) ; 
		     	 }
			   
	        catch(Exception ex)
	        {
	        	
	        	postresult.add("this is error for post request "+ ex.getMessage());
	        }
       
      PrintWriter pwout= response.getWriter();
     // pwout.print(postresult);
      pwout.print(imageDataString);  
	
	}*/
     
    /**
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	//String t =   request.getParameter("t").toString() ;
		
     

    	
    	
    	
    	
    	ArrayList<String> postresult = new ArrayList<String>();
     	// if (t.equals("a"))
       //  {
        	 
       //  }
       //  else{
        	 try { 
     		 //  String postparam = request.getParameter("ccode"); 
     		 
     		//   postresult.add(postparam);
     		  
     		   postresult.addAll( postrequest( request,response)) ;
     		 
     	 }
        catch(Exception ex)
        {
        	
        	postresult.add("this is error for post request "+ ex.getMessage());
        } 
        	 //}
       
      PrintWriter pwout= response.getWriter();
      pwout.print(postresult);
	
	
	}

    
    public ArrayList<String> postrequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ClassNotFoundException,SQLException, NullPointerException,ServletException, IOException
    // public JSONObject getvalidcar(String reg, String eng, String chas) throws ClassNotFoundException,SQLException

    {
       
        
               ArrayList<String> list1 = new ArrayList<String>();
               ResultSet res2=null;
               Statement statement=null;
     		   Connection proccon =null;
     	       HttpSession currentsession = request.getSession(true);
               double newFIF=0;
               double newFED=0;
               int tracker=0; 
               int grossefu=0;
               int   stefu=0;
               int ASC_Rate=0;
               int ASC=0;
               int netefu=0;
               double netp=0; 
               int nfrtx=0;
     	       int decimal_param=0;  
     	      int coinshare=0; 
     	      String NEWSUMINSURED="";
     	      String brid="";
     	     String vmodid="";
     	     String vbodid="";
     	     String vmakeid="";
     	     String vtypeid="";  
     	     String mtypeid="";
     	     String ins_nm="";
     	     String vehid="";
     	    String cell="";
     	   String email="";
     	          double efurt = 0; 
     	          double trackrt = 0; 
     	          double netrt =0;
     	          String docid= ""; 
     		      String compid= ""; 
     		      String coinsbrid= ""; 
     		      String zoneid=  ""; 
     		      String provid=  "";
     		     String cityid=  "";
     		     String areaid ="";
     		     String  Query2 ="";
     		     String insured_nm="";
     		 int  tot_levies=0;
            int FIF_Rate = 0;     
            int  FED_Rate=0;
            int  st_rt=0;
            int st =0;
            int nf_rate =0; 
            int newcert=0;
            String reg_no ="";
            String chass="";
            String eng="";
            String cnic ="";  
            String new_docid="";
            String newpolstring="";
            String msg=""; String MODEL_YEAR="";
     		
     		DecimalFormat df = new DecimalFormat("###.0000");
     		int new_sumint =0;
     		int coinshrint = 	0; 
     		 Double efushr = 0.0;
     		String add=""; String add1=""; String add2="";String add3=""; String add4=""; 
     		    Enumeration<String> parameterNames = request.getParameterNames();
     		   Map<String,String[]> parameterValues = request.getParameterMap();

     		      java.util.Date today = new java.util.Date();
                   Date sqldate= new java.sql.Date(today.getTime());
                    
     	       try {
     	    	  add = request.getParameter("add").toString().trim(); 
     	    	 insured_nm= request.getParameter("ins_nm").toString().trim(); 
     	    	 email= request.getParameter("email").toString().trim(); 
     	    	 cell= request.getParameter("cell").toString().trim(); 
     	    	MODEL_YEAR= request.getParameter("modyr").toString().trim(); 
     	    	 
     	    	 NEWSUMINSURED = request.getParameter("sumins").toString().trim() ;    
     	    	brid =  request.getParameter("brid").toString().trim() ;
     	    	// vbodid =  request.getParameter("vbod").toString().trim() ;
     	    	vtypeid =  request.getParameter("vtype").toString().trim() ;
     	    	vehid =  request.getParameter("mtype").toString().trim() ;
     	    	//ins_nm =  request.getParameter("ins_nm").toString().trim() ;
     	    	
     	    	vmodid =  request.getParameter("vmod").toString().trim() ;
     	    	vmakeid =  request.getParameter("vmac").toString().trim() ;
     	    	cnic =  request.getParameter("cnic").toString().trim() ;
     	    	reg_no =  request.getParameter("reg").toString().trim() ;
     	    	chass =  request.getParameter("chass").toString().trim() ;
     	    	eng =  request.getParameter("eng").toString().trim() ;
     	    	//vehid =  request.getParameter("vehid").toString().trim() ;
     	    	
     	     	docid   =currentsession.getAttribute("docid").toString().trim();
     	    	    
     	        compid= currentsession.getAttribute("compid").toString(); 
     	        coinsbrid=  currentsession.getAttribute("coinsbrid").toString();
     	        zoneid=   currentsession.getAttribute("zoneid").toString();
     	        provid=  currentsession.getAttribute("provid").toString();
     	      
     	       // if change from input screen
     	        provid= request.getParameter("provlist").toString().trim() ;
     	       cityid= request.getParameter("citylist").toString().trim() ;
     	      areaid= request.getParameter("arealist").toString().trim() ;
     	        
     	       coinshare=  Integer.parseInt(currentsession.getAttribute("coinshare").toString());    

     	        new_sumint = Integer.parseInt(NEWSUMINSURED);
                 
            float      tempfloat = (float)((float) new_sumint * coinshare /100.0);
           efushr =  Double.parseDouble(df.format( tempfloat ));
     	       
     	       
     	      efurt=   Double.parseDouble(currentsession.getAttribute("efurt").toString()) ;  
     	     trackrt= Double.parseDouble(currentsession.getAttribute("trackerrt").toString()) ;  
     	    netrt=  Double.parseDouble(currentsession.getAttribute("netrt").toString()) ;  
     	      
     	         
     	       }
     	     catch (Exception exp) {
             	 
     	    	   list1.add( "error for session and request object access : " +exp.getMessage() );
                  }
     	     
     	      
     	     
     	     int lenth=add.length();
     	     int len3=(add.length())/3;
     	  
     	   add1=add.substring(0,len3);
     	 
     	  add2=add.substring(len3, 2*len3);
     	 
     	 add3=add.substring(2*len3);
     	   
     	            list1.add("Post request test from submit button :  ");
     	          
     	            
     	             Class.forName("oracle.jdbc.driver.OracleDriver");
                   proccon =
                 //  DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test", "efu_gis", "test");
     	          
     	            
     	            //decimal
     	             try{
     	        	   
     	                Query2 = " select currency_cd,decimals from efu_mast.company_name where comp_id =";
     	           
     	               Query2 =Query2 + " "+compid+" ";
     	              statement = proccon.createStatement();
     	           res2 = statement.executeQuery(Query2);
     	           while (res2.next())
     	           {
     	               decimal_param=res2.getInt(2); 
     	               
     	               }
     	           } catch (Exception exp) {
     	                    list1.add( "decimal from company_name exception : " +exp.getMessage() );
     	                } 
     	     
     	 
             try{
                  
                   CallableStatement cStmt2 = proccon.prepareCall("{call LeviesRates(?,?,?,?,?,?,?,    ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                 
                                        cStmt2.setString("forendt",docid );
                                        cStmt2.setString("refcan", null);
                                
                                        cStmt2.setDate("efctdt",  sqldate ); 
                                        cStmt2.setInt("compid", Integer.parseInt(compid));
                                        cStmt2.setInt("coins_br", Integer.parseInt(coinsbrid ));
                                        cStmt2.setInt("coins_zone", Integer.parseInt(zoneid ));
                                        cStmt2.setInt("coins_prvnc", Integer.parseInt(provid ) );
                                          
                                        cStmt2.registerOutParameter("ascrt", Types.DOUBLE);
                                        cStmt2.registerOutParameter("ascamt", Types.DOUBLE);
                                         cStmt2.registerOutParameter("ascminamt",  Types.DOUBLE);
                                         cStmt2.registerOutParameter("ascmaxamt",  Types.DOUBLE);
                                        cStmt2.registerOutParameter("fifrt",  Types.DOUBLE);
                                         cStmt2.registerOutParameter("fifminamt",  Types.DOUBLE);
                                        cStmt2.registerOutParameter("fifmaxamt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("fedrt",  Types.DOUBLE );
                                         cStmt2.registerOutParameter("fedminamt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("fedmaxamt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("strt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("stminrt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("stmaxrt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("stminamt",  Types.DOUBLE );  
                                       cStmt2.registerOutParameter("stcmaxamt",  Types.DOUBLE);
                                        cStmt2.registerOutParameter("nflrtx_rt",  Types.DOUBLE );
                                        cStmt2.registerOutParameter("nflrtx_minamt",  Types.DOUBLE );
                                         cStmt2.registerOutParameter("permyn", java.sql.Types.VARCHAR);
                                        cStmt2.registerOutParameter("msg", java.sql.Types.VARCHAR);
                                        cStmt2.execute();
         
                                           ASC_Rate =cStmt2.getInt("ascrt");
                                            ASC  =cStmt2.getInt("ascamt");
                                        tot_levies=ASC+cStmt2.getInt("fifmaxamt")+cStmt2.getInt("fedmaxamt");
                                        FIF_Rate = cStmt2.getInt("fifrt");     
                                        FED_Rate=cStmt2.getInt("fedrt");
                                            st_rt=cStmt2.getInt("strt");
                                        st =cStmt2.getInt("stminamt");
                                        nf_rate =cStmt2.getInt("nflrtx_rt"); 
                                        msg=cStmt2.getString("msg");
//                                        list1.add(Integer.toString(ASC_Rate));
//                                        list1.add(Integer.toString(FIF_Rate));
//                                                           list1.add(Integer.toString(FED_Rate));
//                                                           list1.add(Integer.toString(st));
//                                                           list1.add(Integer.toString(nf_rate));
               list1.add(" ASC_Rate "+ASC_Rate+" FIF_Rate "+FIF_Rate+" FED_Rate " +FED_Rate+" st_rt " +st_rt+" st " +st+" nf_rate " +nf_rate+" msg "+msg  );             
               } catch (Exception exp) {
                  
                
                   list1.add( "Error levies rates procedure call error" +sqldate+" "+docid+" "+compid+" "+coinsbrid+" "+zoneid+" "+provid+" "+exp.getMessage());
               }
               
            
               
           //    PrintWriter pwout= response.getWriter();
          //     pwout.print(list1); 
                           
                 try{     
                	  CallableStatement cStmt2 = proccon.prepareCall("{call CalcRevPrem_NU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                                                   
                                       cStmt2.setInt(1, Integer.parseInt(NEWSUMINSURED) );
                                          cStmt2.setInt(2, coinshare );
                                            cStmt2.setInt(3, 0); 
                                          cStmt2.setDouble(4, netrt );
                                             cStmt2.setDouble(5, efurt );
                                          cStmt2.setDouble(6, trackrt );
                                          cStmt2.setInt(7, ASC_Rate );
                                          cStmt2.setInt(8, FIF_Rate );
                                             cStmt2.setInt(9, FED_Rate );
                                         cStmt2.setInt(10, st );
                                         cStmt2.setInt(11, nf_rate );
                                           cStmt2.setString(12, "N");
                                                             cStmt2.setDate(13,   new java.sql.Date(today.getTime()));
                                                               cStmt2.setInt(14, decimal_param);
                    
                                                          
 
                                         cStmt2.registerOutParameter(15, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(16, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(17, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(18, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(19, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(20, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(21, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(22, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(23, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(24, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(25, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(26, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(27, java.sql.Types.INTEGER);  
                                         cStmt2.registerOutParameter(28, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(29, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(30, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(31, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(32, java.sql.Types.INTEGER);
                                         cStmt2.registerOutParameter(33, java.sql.Types.INTEGER);
                                           cStmt2.execute(); 
						                                   //TST PROC
//                                                             cStmt2.setString("leas_yn", "N");
//                                                             cStmt2.setDate("leas_dt",   new java.sql.Date(today.getTime()));
//                                        
//                                        
//                                                             cStmt2.registerOutParameter("shrsumins ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("Gross100 ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("ASC100 ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("FIF100 ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("FED100 ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("st100 ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("Net100 ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("nflr_tx ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("GrossEFU ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("ASCEFU ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("GrossASCEFU ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("FIFEFU ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("FEDEFU ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("stEFU ", java.sql.Types.INTEGER);  
//                                                             cStmt2.registerOutParameter("NetEFU ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("nflr_tx_efu ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("Grosstrkr ", java.sql.Types.INTEGER);
//                                                             cStmt2.registerOutParameter("ASCTrkr  ", Types.INTEGER);
//                                                             cStmt2.registerOutParameter("GrossASCTrkr ", Types.INTEGER);
                                                             
                                                                    
                                                             grossefu=cStmt2.getInt(23);
                                                            ASC=cStmt2.getInt(24);  
                                                            stefu=  cStmt2.getInt(28);
                                                            netefu=  cStmt2.getInt(29);   
                                                            nfrtx =cStmt2.getInt(30);
                                                            tracker=cStmt2.getInt(33); 
                                                            if  ( trackrt>0)
                                                            {   
                                                            int grossadd=grossefu+ASC+tracker; 
                                                            newFIF=     (grossadd)*((double)FIF_Rate/100) ;
                                                            newFIF= Math.round(newFIF);
                                                            newFED=  (grossadd)*((double)FED_Rate/100) ;
                                                            newFED= Math.round(newFED);
                                                            netp= Math.round   ( (grossadd)+ (newFIF) +  (newFED) +stefu);   
                                                                    }
                                                                     else
                                                            { netp=netefu;
                                                            newFIF=cStmt2.getInt(26);
                                                            newFED=cStmt2.getInt(27);
                                                                  } 
                 } catch (Exception exp) {
                     
                     
                     list1.add( "Error premium calc procedure call: " +exp.getMessage());
                 }  
                  
                      
                     
                   /*   insert procedures */
                                                                      
                            try{ 
                        	   CallableStatement cStmt3 = proccon.prepareCall("{call WEB_BANK_INSERT_UW_MAIN(?,?,?, ?,?,?,?,?,? ,?,?,?  ,?,?)}");
                                
                        cStmt3.setString (1,  docid );
                        cStmt3.setString(2, NEWSUMINSURED  );
                        cStmt3.setString(3,  cnic );
                        
                        cStmt3.setString(4,  add1 );
                        cStmt3.setString(5,  add2 );
                        cStmt3.setString(6,  add3 );
                        cStmt3.setString(7,  insured_nm );
                        cStmt3.setString(8,  email );
                        cStmt3.setString(9,  cell );
                       
                        cStmt3.setString(10,  provid );
                        cStmt3.setString(11,  areaid );
                        cStmt3.setString(12,  cityid );
                       // cStmt3.setString(4,  netrt );
                       // cStmt3.setString(5,  efurt );
                         
                        cStmt3.registerOutParameter(13, java.sql.Types.VARCHAR);
                        cStmt3.registerOutParameter(14, java.sql.Types.VARCHAR);
                                cStmt3.execute();
                                
                   newcert      =   Integer.parseInt( cStmt3.getString(13));
                   new_docid    =   cStmt3.getString(14)   ;
                        
                   // list1.add("new_docid :  "+new_docid+" .");
                  
                    }
                                                 
                                                    catch (Exception exp) { 
 list1.add( "Error WEB_BANK_INSERT_UW_MAIN : " +exp.getLocalizedMessage()+",Data sent:  p_docid "+docid+" new_sum "+NEWSUMINSURED+" cnic "+cnic  );
                                                }
                               
                       newpolstring = Integer.toString(newcert);
                        while (newpolstring.length() < 6)
                        {newpolstring = "0" + newpolstring;}
                   newpolstring= brid+"4"+newpolstring+"/";
                 //  list1.add( " newpolstring "+newpolstring);
                       
                   
                   list1.add("newcert :  "+newpolstring+" .");  
                                
               try{
                   
                   CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_BANK_INSERT_MOTOR( ?,?,?,?, ?,?,?,?,? ,? )}");
                          
                       cStmt4.setString(1,  docid ); 
                       cStmt4.setString(2, new_docid  );
                       cStmt4.setString (3, Double.toString( netrt) ); 
                       cStmt4.setString(4, Double.toString(trackrt) );
                       cStmt4.setString(5, Double.toString(efurt ));
                       cStmt4.setString(6, reg_no  );
                       cStmt4.setString(7, chass  );
                       cStmt4.setString(8, eng  );
                       cStmt4.setString(9, MODEL_YEAR  );
                        
                       cStmt4.setString(10, vehid  );
                       cStmt4.execute();
                       
                   } catch (Exception exp) {
                     
                     // invalid column index exception
                       list1.add( "Error INSERT_Motor : " +exp.getLocalizedMessage());
                   }
                                               
                                               
                          try{
                           
                       CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_RENEWAL_INSERT_MULTI(?,?)}");
                               
                            cStmt4.setString(1, docid ); 
                            cStmt4.setString(2, new_docid  );
                            cStmt4.execute();
                            
                        } catch (Exception exp) {
                          
                          // invalid column index exception
                            list1.add( "Error INSERT_MULTI error" +exp.getLocalizedMessage()+" Error Class: "+ exp.getClass());
                        }
                                    
                        try{
                           
                        CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_RENEWAL_INSERT_MULTI_DOS (?,?)}");
                               
                            cStmt4.setString(1, docid ); 
                            cStmt4.setString(2, new_docid  );
                            cStmt4.execute();
                            
                        } catch (Exception exp) {
                          
                          // invalid column index exception
                           list1.add( "Error INSERT_MULTI_DOS error" +exp.getMessage()+" p_docid: "+ docid+" new_docid "+new_docid);
                        }
                                
                       try{
                          
                       CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_RENEWAL_INSERT_CLAUSE (?,?)}");
                              
                           cStmt4.setString(1,new_docid   ); 
                           cStmt4.setString(2, docid  );
                           cStmt4.execute();
                           
                       } catch (Exception exp) {
                         
                         // invalid column index exception
                          list1.add( "Error WEB_RENEWAL_INSERT_CLAUSE error" +exp.getMessage()+" p_docid: "+ docid+" new_docid "+new_docid);
                       }
                                
                                             
                        try{
                           
                        CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_RENEWAL_INSERT_risk(?,? ,?,?,?)}");
                               
                            cStmt4.setString(1, docid ); 
                            cStmt4.setString(2, new_docid  );
                            cStmt4.setString(3, Integer.toString(grossefu)  );
                            cStmt4.setString(4,NEWSUMINSURED  );
                          cStmt4.setString(5, Integer.toString(coinshare)  );
                            cStmt4.execute();
                            
                        } catch (Exception exp) {
                          
                           //invalid column index exception
                            list1.add( "Error INSERT_risk: " +exp.getMessage()+" p_docid: "+ docid+" new_docid "+new_docid+" grossefu: "+ grossefu);
                        }
                  
                           
                        try{
                           
                        CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_RENEWAL_INSERT_PREM_SHARES(?,?,?,?,? , ?,?,?,?,? , ?,?,?,?,? , ?)}");
                               
                            cStmt4.setString(1, docid ); 
                            cStmt4.setString(2, new_docid  );
                            cStmt4.setString(3,  Double.toString(efushr)); 
                            cStmt4.setString(4, Integer.toString(coinshare)  );
                            cStmt4.setString(5, Integer.toString(grossefu) );
                            cStmt4.setString(6,  Integer.toString(ASC_Rate ) ); 
                            cStmt4.setString(7, Integer.toString(ASC ) );
                            cStmt4.setString(8,  Integer.toString(FIF_Rate)); 
                            cStmt4.setString(9, Double.toString(newFIF)  );
                            cStmt4.setString(10, Integer.toString(FED_Rate)  );
                            cStmt4.setString(11, Double.toString(newFED ) );
                            cStmt4.setString(12, Integer.toString(st_rt)   );
                            cStmt4.setString(13, Integer.toString(stefu)  );
                            cStmt4.setString(14, Double.toString(netp ) );
                            cStmt4.setString(15, Integer.toString(nf_rate)  );
                            cStmt4.setString(16, Integer.toString(nfrtx ) );
                            cStmt4.execute();
                                  
                        } catch (Exception exp) {
                          
                          // invalid column index exception
                            list1.add( "Error INSERT_PREM_SHARES new docid "+new_docid+": "  +exp.getLocalizedMessage());
                        }
                         
                        try{
                           
                        CallableStatement     cStmt4 = proccon.prepareCall("{call WEB_RENEWAL_INSERT_PREM_INSTAL(?,?,?,?,? ,?,?,?)}");
                               
                            cStmt4.setString(1, docid ); 
                            cStmt4.setString(2, new_docid  );
                            cStmt4.setString(3, Integer.toString(grossefu)); 
                            cStmt4.setString(4, Integer.toString(tot_levies ) );
                            cStmt4.setInt(5, stefu     );
                            cStmt4.setString(6, Integer.toString(grossefu) ); 
                            cStmt4.setString(7, Integer.toString(tot_levies) );
                            cStmt4.setInt(8,  stefu    );
                            cStmt4.execute();
                            
                        } catch (Exception exp) {
                        
                          // invalid column index exception
                        list1.add( "Error WEB_RENEWAL_INSERT_Instal : " +exp.getMessage());
                        }  
                                             
                                                    
                                                                        
                                                                        
                                                                        /*end of inssert procedures*/
                                                                        
                                                                        
                                                                        
                   
     	
          
       
           return list1;
       }
	
	
	

	
	
	
	
	
     }
