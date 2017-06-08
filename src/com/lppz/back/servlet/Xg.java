package com.lppz.back.servlet;
import com.lppz.back.db.Dbutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
@WebServlet("/Xg.action") @MultipartConfig
public class Xg extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("utf-8");
         response.setCharacterEncoding("utf-8");
         response.setContentType("text;html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
           Object o=session.getAttribute("my");
     String pname=request.getParameter("pname");
      String cc=request.getParameter("cc");
      String pric=request.getParameter("pric");
        String num= request.getParameter("num");
        String statu = request.getParameter("st");
        /*Part p=request.getPart("file");
        String s="";
        if(!p.getSubmittedFileName().equals("")){
            p.write(getServletContext().getRealPath("upload/")+p.getSubmittedFileName());
            //out.print(getServletContext().getRealPath("upload/")+p.getSubmittedFileName());
            s=p.getSubmittedFileName();
            Mysl my=new Mysl();
            my.getPhoto(getServletContext().getRealPath("upload/")+p.getSubmittedFileName(),50,getServletContext().getRealPath("smallup/")+p.getSubmittedFileName());
            session.setAttribute("os","http://localhost:8080/smallup/");
        }*/
        Dbutil db=new Dbutil();
       String sql=String.format("update goods set gname='"+pname+"',c_id='"+cc+"',gprice='"+pric+"',gnum='"+num+"',statu='"+statu+"' where id='"+o+"'");
       try {
           PreparedStatement pre=db.getConn().prepareStatement(sql);
           int i=pre.executeUpdate();
           if(i>0){
               response.sendRedirect("back/shangpin.jsp");
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
