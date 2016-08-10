/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint;

/**
 *
 * @author siddhartha.dhanetwal
 */
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import org.json.JSONObject;

@Path("/UserService")
public class UserService {
    
   UserDao userDao = new UserDao();

   @GET
   @Path("/search")
   @Produces(MediaType.APPLICATION_JSON)
   public String getSearchResult(){
      return "[{\"symbol\":\"AA\",\"full_name\":\"AA\",\"description\":\"Alcoa Inc.\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"FRE.AX\",\"full_name\":\"FRE.AX\",\"description\":\"Freshtel Holdings Limited\",\"exchange\":\"ASX\",\"type\":\"stock\"},{\"symbol\":\"FCEL\",\"full_name\":\"FCEL\",\"description\":\"FuelCell Energy Inc.\",\"exchange\":\"NGM\",\"type\":\"stock\"},{\"symbol\":\"F\",\"full_name\":\"F\",\"description\":\"Ford Motor Co.\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"WFC\",\"full_name\":\"WFC\",\"description\":\"Wells Fargo & Company\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"ETFC\",\"full_name\":\"ETFC\",\"description\":\"E*TRADE Financial Corporation\",\"exchange\":\"NasdaqNM\",\"type\":\"stock\"},{\"symbol\":\"ANF\",\"full_name\":\"ANF\",\"description\":\"Abercrombie & Fitch Co.\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"MSFT\",\"full_name\":\"MSFT\",\"description\":\"Microsoft Corporation\",\"exchange\":\"NasdaqNM\",\"type\":\"stock\"},{\"symbol\":\"BMO\",\"full_name\":\"BMO\",\"description\":\"Bank of Montreal\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"BAC\",\"full_name\":\"BAC\",\"description\":\"Bank of America Corporation\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"^NSEI\",\"full_name\":\"^NSEI\",\"description\":\"CNX NIFTY\",\"exchange\":\"NSE\",\"type\":\"index\"},{\"symbol\":\"DO\",\"full_name\":\"DO\",\"description\":\"Diamond Offshore Drilling, Inc.\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"BK\",\"full_name\":\"BK\",\"description\":\"The Bank of New York Mellon Corporation\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"ETP\",\"full_name\":\"ETP\",\"description\":\"Energy Transfer Partners, L.P.\",\"exchange\":\"NYSE\",\"type\":\"stock\"},{\"symbol\":\"AEO\",\"full_name\":\"AEO\",\"description\":\"American Eagle Outfitters, Inc.\",\"exchange\":\"NYSE\",\"type\":\"stock\"}]";
   }	
   @GET
   @Path("/config")
   @Produces(MediaType.APPLICATION_JSON)
   public String getConfig(){
      return "{\"supports_search\":true,\"supports_group_request\":false,\"supports_marks\":true,\"supports_timescale_marks\":true,\"supports_time\":true,\"exchanges\":[{\"value\":\"\",\"name\":\"All Exchanges\",\"desc\":\"\"},{\"value\":\"XETRA\",\"name\":\"XETRA\",\"desc\":\"XETRA\"},{\"value\":\"NSE\",\"name\":\"NSE\",\"desc\":\"NSE\"},{\"value\":\"NasdaqNM\",\"name\":\"NasdaqNM\",\"desc\":\"NasdaqNM\"},{\"value\":\"NYSE\",\"name\":\"NYSE\",\"desc\":\"NYSE\"},{\"value\":\"CDNX\",\"name\":\"CDNX\",\"desc\":\"CDNX\"},{\"value\":\"Stuttgart\",\"name\":\"Stuttgart\",\"desc\":\"Stuttgart\"}],\"symbolsTypes\":[{\"name\":\"All types\",\"value\":\"\"},{\"name\":\"Stock\",\"value\":\"stock\"},{\"name\":\"Index\",\"value\":\"index\"}],\"supportedResolutions\":[\"D\",\"2D\",\"3D\",\"W\",\"3W\",\"M\",\"6M\"]}";
   }
   
   @GET
   @Path("/symbols")
   @Produces(MediaType.APPLICATION_JSON)
   public String getSymbols(){
       return "{\"name\":\"AA\",\"exchange-traded\":\"NYSE\",\"exchange-listed\":\"NYSE\",\"timezone\":\"America/New_York\",\"minmov\":1,\"minmov2\":0,\"pricescale\":10,\"pointvalue\":1,\"session\":\"0930-1630\",\"has_intraday\":false,\"has_no_volume\":false,\"ticker\":\"AA\",\"description\":\"Alcoa Inc.\",\"type\":\"stock\",\"supported_resolutions\":[\"D\",\"2D\",\"3D\",\"W\",\"3W\",\"M\",\"6M\"]}";
   }
   
   @GET
   @Path("/history")
   @Produces(MediaType.APPLICATION_JSON)
   public String getHistory(){
       return "{\"t\":[1466985600,1467072000\n" +
",1467158400],\"c\":[9.1,9.33,9.1],\"o\":[9.28,9.35,9.51],\"h\":[9.3,9.38,9.52],\"l\":[8.95,9.19,9.01],\"v\":[21866300\n" +
",18110900,37131600],\"s\":\"ok\"}";//from where does it update
   }
   
   @GET
   @Path("/time")
   @Produces(MediaType.TEXT_PLAIN)
    public String getServerTime(){
       long gmt=19800000;
       long time=new Date().getTime()-gmt;
       String s=String.valueOf(time);
       return s;
   }
   
   @GET
   @Path("/timescal_marks")
   @Produces(MediaType.APPLICATION_JSON)
    public String getTimescaleMarks(){
       
       return "[{\"id\":\"tsm1\",\"time\":1467244800,\"color\":\"red\",\"label\":\"A\",\"tooltip\":\"\"},{\"id\":\"tsm2\",\"time\":1466899200\n" +
",\"color\":\"blue\",\"label\":\"D\",\"tooltip\":[\"Dividends: $0.56\",\"Date: Sun Jun 26 2016\"]},{\"id\":\"tsm3\",\"time\"\n" +
":1466640000,\"color\":\"green\",\"label\":\"D\",\"tooltip\":[\"Dividends: $3.46\",\"Date: Thu Jun 23 2016\"]},{\"id\"\n" +
":\"tsm4\",\"time\":1465948800,\"color\":\"#999999\",\"label\":\"E\",\"tooltip\":[\"Earnings: $3.44\",\"Estimate: $3.60\"\n" +
"]},{\"id\":\"tsm7\",\"time\":1464652800,\"color\":\"red\",\"label\":\"E\",\"tooltip\":[\"Earnings: $5.40\",\"Estimate: $5\n" +
".00\"]}]";
   }
   @GET
   @Path("/marks")
   @Produces(MediaType.APPLICATION_JSON)
    public String getMarks(){
        return "{\"id\":[0,1,2,3,4,5],\"time\":[1467244800,1466899200,1466640000,1466640000,1465948800,1464652800],\"color\"\n" +
":[\"red\",\"blue\",\"green\",\"red\",\"blue\",\"green\"],\"text\":[\"Today\",\"4 days back\",\"7 days back + Lorem ipsum\n" +
" dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna\n" +
" aliqua.\",\"7 days back once again\",\"15 days back\",\"30 days back\"],\"label\":[\"A\",\"B\",\"CORE\",\"D\",\"EURO\"\n" +
",\"F\"],\"labelFontColor\":[\"white\",\"white\",\"red\",\"#FFFFFF\",\"white\",\"#000\"],\"minSize\":[14,28,7,40,7,14]}";
    }
    
   @POST
   @Path("/save")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public void saveData(String data){
       //System.err.println("dsdfsfsdf"+req.getParameter(""));
     String a=data;
System.out.println(a);
 /* try {
      
    JSONObject jsonObject = JSONObject.fromObject(jb.toString());
  } catch (ParseException e) {
    // crash and burn
    throw new IOException("Error parsing JSON request string");
  }*/
//  System.out.println(msg);
try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","POC","15021992Sd");

            String sql = "insert into CHARTS(key,value) values(?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "stx-views");
            pstmt.setString(2, a);
            pstmt.executeUpdate();
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
      //return "{\"name\":\"unknown\", \"age\":-1}";
   }
   
   @POST
   @Path("/load")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public String loadData(){
     String a=null;
try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","POC","15021992Sd");

            String sql = "select value from CHARTS where key='stx-views'";
 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
            a=rs.getString("value");
            }
            con.close();
            return a;
        }
        catch(Exception e){
            System.out.println(e);
        }
      return a;
   }
}
