package servercode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostClass {


	public  ArrayList<String>  postrequestinterface(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
	 
	
		 ArrayList<String> postresult = new ArrayList<String>();
     	 try { 
     		 //  String postparam = request.getParameter("ccode"); 
     		 
     		//   postresult.add(postparam);
     		  
     		   postresult.addAll( postrequest( request,response)) ;
     		 
     	 }
        catch(Exception ex)
        {
        	
        	postresult.add("this is error for list "+ ex.getMessage());
        }
      
     	 /*if need to use by servlet
    //  PrintWriter pwout= response.getWriter();
     // pwout.print(postresult);
	*/
     	return  postresult;
	
	}

    
    public ArrayList<String> postrequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ClassNotFoundException,SQLException, NullPointerException,ServletException, IOException
    // public JSONObject getvalidcar(String reg, String eng, String chas) throws ClassNotFoundException,SQLException

    {
       
        
               ArrayList<String> list1 = new ArrayList<String>();
             
     		
     		   Connection proccon =null;
     	       HttpSession currentsession = request.getSession(true);
     	      String postparam = request.getParameter("ccode"); 
     	       String docid= ""; 
     		      String compid= ""; 
     		      String coinsbrid= ""; 
     		      String zoneid=  ""; 
     		      String provid=  ""; 
     		      java.util.Date today = new java.util.Date();
                   Date sqldate= new java.sql.Date(today.getTime());
                    
     	       try { 
     		     docid= currentsession.getAttribute("docid").toString();
     	        compid= currentsession.getAttribute("compid").toString(); 
     	        coinsbrid=  currentsession.getAttribute("coinsbrid").toString();
     	        zoneid=   currentsession.getAttribute("zoneid").toString();
     	        provid=  currentsession.getAttribute("provid").toString();
     	        
     	    
     	            list1.add("Post request test from submit button :  "+postparam);
     	  
     	     
     	     }
     	     catch (Exception exp) {
             	 
             	 list1.add(exp.getMessage());
                  }
     	     
     	   int ASC_Rate =0;
            int  ASC  =0;
            int  tot_levies=0;
            int FIF_Rate = 0;     
            int  FED_Rate=0;
            int  st_rt=0;
            int st =0;
            int nf_rate =0; 
         String msg="";
         
     	 
             try{
                   Class.forName("oracle.jdbc.driver.OracleDriver");
                   proccon =
                 //  DriverManager.getConnection("jdbc:oracle:thin:@oda01-scan.efuinsurance.com:1521/efuprd.efuinsurance.com", "efu_gis", "PRODgis");
                  DriverManager.getConnection("jdbc:oracle:thin:@test.efuinsurance.com:1521:test", "efu_gis", "test");
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
             /*               CallableStatement cStmt2 = connection.prepareCall("{call CalcRevPrem_NU(?,?,?,?,?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                boolean adjust=false;
                 try{                                          
                                                           cStmt2.setInt(1, NEWSUMINSURED );
                                                              cStmt2.setInt(2, COINS_SHARE );
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
                    
                                                          
//                       cStmt2.registerOutParameter(14, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(15, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(16, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(17, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(18, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(19, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(20, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(21, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(22, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(23, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(24, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(25, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(26, java.sql.Types.INTEGER);  
//                       cStmt2.registerOutParameter(27, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(28, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(29, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(30, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(31, java.sql.Types.INTEGER);
//                       cStmt2.registerOutParameter(32, java.sql.Types.INTEGER);
                                                          //TEST PROC  
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
                                                             
                                                                        cStmt2.execute();
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
                     
                     
                     //  session.setAttribute("new_docid ", new_docid);
                     //  session.setAttribute("P_docid ", docid);
                                                                //    pd.setNew_docid(new_docid);
                                                                     pd.setP_docid(docid);
                     //  session.setAttribute("p_doc", docid);
                    //   session.setAttribute("new_doc", new_docid);
                                   //          session.setAttribute("Gross_amt", Integer.toString(grossefu));
                    //               session.setAttribute("P_docid", docid);
                    //   session.setAttribute("grossefu", grossefu);
//                       session.setAttribute("tot_levies", tot_levies);
//                       session.setAttribute("grossefu", grossefu);
//                       session.setAttribute("Inst_efu_levies", tot_levies);
//                       session.setAttribute("Inst_efu_st_amt", stefu);
//                       session.setAttribute("newFED", newFED);
//                       session.setAttribute("FED_Rate", FED_Rate);
//                       session.setAttribute("newFIF", newFIF);
//                       session.setAttribute("FIF_Rate", FIF_Rate);
//                       session.setAttribute("ASC_Rate", ASC_Rate);
//                       session.setAttribute("Asc_amt", ASC+tracker);
//                       session.setAttribute("St_amt", stefu);
//                       session.setAttribute("St_rate", st_rt);
//                       session.setAttribute("Nflrt", nf_rate);
//                       session.setAttribute("Flr_tx", nfrtx);
//                       session.setAttribute("Net_amt", netp);
//                       session.setAttribute("New_sum", NEWSUMINSURED);
//                       session.setAttribute("Efushr", COINS_SHARE);
//                       session.setAttribute("netrt", netrt);
//                       session.setAttribute("efurt", efurt);
//                       session.setAttribute("Trackrt", trackrt);       
                                                                           
                                                                             pd.setGross_amt( Integer.toString(grossefu)    ); 
                                                                                 pd.setInst_tot_levies(Integer.toString(tot_levies));
                                                                           pd.setInst_st_amt(Integer.toString(stefu));
                                                                             pd.setEfu_gross_amt(Integer.toString(grossefu));
                                                                   pd.setInst_efu_levies(Integer.toString(tot_levies));
                                                                      pd.setInst_efu_st_amt (Integer.toString(stefu));
                                                                                
                                                                     
                                                                              pd.setFed_amt(Double.toString(newFED));
                                                                              pd.setFed_rate(Integer.toString(FED_Rate));
                                                                            pd.setFif_amt (Double.toString(newFIF));
                                                                             pd.setFif_rate (Integer.toString(FIF_Rate));
                                                                            pd.setAsc_rate(Integer.toString(ASC_Rate));
                                                                           pd.setAsc_amt( Integer.toString(ASC+tracker));
                                                                             pd.setSt_amt(Integer.toString(stefu));
                                                                            pd.setSt_rate(Integer.toString(st_rt));
                                                                                pd.setNflrt(Integer.toString(nf_rate));
                                                                      pd.setFlr_tx (Integer.toString(nfrtx));
                                                                                  pd.setNet_amt(Double.toString(netp)); 
                                                                                 pd.setNew_sum(Integer.toString(NEWSUMINSURED));
                                                                      pd.setEfushr(Integer.toString(COINS_SHARE));
                                                                            pd.setNetrt(Double.toString(netrt));
                                                                           pd.setEfurt(Double.toString(efurt));
                                                                           pd.setTrackrt(Double.toString(trackrt));
                                                                               // } else { netp=netefu;}
                                                                       //     cprem=  ( NEWSUMINSURED*(netrt/100));
                                                           //               if( netp!=cprem) 
                                                           //               {  
                                                           //                    diff  =(int) (netp-cprem)  ;
                                                           //              grossefu=grossefu+    diff;
                                                            //                  netp=netp+    diff;
                                                              //                }
                                                                    //   list1.add(Integer.toString(grossefu ));
                                                                   //        list1.add(Integer.toString(netefu));
                                                                   //       list1.add(Integer.toString(nfrt));
                                                                        //netefu=netefu+nfrt;
                                                                        list1.add(Double.toString(netp));
                                                                        list1.add(pol_theft);        
                                                                        list1.add(convert);   
                                                                        list1.add((claimcount ));   
                                                                        list1.add((cancel ));  
                     
                     
                       list1.add(pd.getBr_nm());
                       
                        list1.add(" old_prem "+Double.toString(old_prem)+" , NEW NET EFU FROM PROCEDURE: "+netefu);
                       list1.add(" NEWSUMINSURED "+Double.toString(NEWSUMINSURED));
                       list1.add(" COINS_SHARE "+Double.toString(  COINS_SHARE));        
                       list1.add(" trackrt "+Double.toString(trackrt));
                       list1.add(" netrt "+Double.toString( netrt)+" temp"+temp);   
                       list1.add(" efurt "+Double.toString( efurt));   
                       list1.add(" ASC_Rate "+Double.toString( ASC_Rate));   
                       list1.add(" FIF_Rate "+Double.toString(FIF_Rate));
                       list1.add(" FED_Rate "+Double.toString(FED_Rate));        
                       list1.add(" st "+Double.toString(st));   
                       list1.add(" nf_rate "+Double.toString(nf_rate));
                       list1.add(" decimal_param "+Double.toString(decimal_param));    

                       //             list1.add(pd.getBr_nm()+NEWSUMINSURED+" coins share " + COINS_SHARE+" efu coinsshare from class "+pd.getEfushr()+" tracker rate "+ trackrt+" efu rt "+ efurt+" net rt " +netrt +"ASC  "+ASC_Rate +"  "+FIF_Rate +"  "+FED_Rate+"  "+st+"  "+ nf_rate+"  " +new java.sql.Date(today.getTime())+decimal_param);
////                       list1.add(pd.getBr_nm()+"  "+pd.getFed_amt()+"  "+pd.getFif_amt()+" "+pd.getGross_amt()+" "+pd.getAsc_amt()+" "+pd.getTrackrt()); 
////                                                        //    list1.add(Integer.toString(grossefu));
////                     
////                       
////                                                          //                      list1.add(Integer.toString( ASC+tracker)); 
////                                                          //                      list1.add(cStmt2.getString("GrossASCEFU"));
                   } catch (Exception exp) {
                       
                      
                       list1.add( "Error premium calc procedure call: " +exp.getMessage());
                   }*/
     	
          
       
           return list1;
       }


}
