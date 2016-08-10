package com.oilspace.apps.charts;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.sql.*;
/**
 * Created by siddhartha.dhanetwal on 8/4/2016.
 */
public class ChartServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Request-Method", "GET");
        System.out.println("inside do get chart servlet");

        PrintWriter out = response.getWriter();
        out.println("sid");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Request-Method", "POST");
        response.setContentType("application/json");
        System.out.println("inside do post chart servlet");
        String message=request.getParameter("f");

        System.out.println(message);
        try{
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","POC","15021992Sd");

//step3 create the statement object
           // Statement stmt=con.createStatement();

//step4 execute query
            String sql = "insert into CHARTS(key,value) values(?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, "view");
            pstmt.setString(2, message);


            pstmt.executeUpdate();
        //    String sql = "INSERT INTO CHARTS " +
          //          "VALUES ('view',"+message+")";
          //  stmt.executeUpdate(sql);
//step5 close the connection object
            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }


    }
}
