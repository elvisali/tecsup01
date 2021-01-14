/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.controlador;

import com.empresa.modelo.cBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Banks
 */
@WebServlet(name = "controladorLogin", urlPatterns = {"/controladorLogin"})
public class controladorLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
          PrintWriter out = response.getWriter();
        processRequest(request, response);
try  {
            cBaseDatos objDatos = new cBaseDatos(); 
            String accion = request.getParameter( "accion" );
            HttpSession session=request.getSession();
            switch (accion) {
        
        case "logout":
            System.out.println("LOGUT");
                session.removeAttribute("authentication");
                response.sendRedirect("index.html");
            break;
    }
            
            
            
        }catch(Exception ex) {
            System.out.println( ex.toString() );   
        } finally { 
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
        processRequest(request, response);
try  {
            cBaseDatos objDatos = new cBaseDatos(); 
            String accion = request.getParameter( "accion" );
            HttpSession session=request.getSession();  
            
            switch (accion) {
        case "autentication":
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (objDatos.validateUser(username, password)) { 
                session.setAttribute("authentication", "true");  
                response.sendRedirect("entorno.html");
            } else {
                response.sendRedirect("errorAutenticacion.html");
            }
            break;
        
        case "logout":
                
                 session.removeAttribute("authentication" );
                session.invalidate();
                response.sendRedirect("index.html");
            break;
    }
            
            
            
        }catch(Exception ex) {
            System.out.println( ex.toString() );   
        } finally { 
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
