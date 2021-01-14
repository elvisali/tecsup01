/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.controlador;

import com.empresa.dao.*;
import com.empresa.modelo.*;
import com.empresa.modelo.cBaseDatos;
import java.io.IOException;
import java.io.PrintWriter; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.runtime.arrays.ArrayData;

/**
 *
 * @author Banks
 */
@WebServlet(name = "controladorPrincipal", urlPatterns = {"/controladorPrincipal"})
public class controladorPrincipal extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        
    throws ServletException, IOException {
     System.out.println("----->>>    Pors 01");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("----->>>    Pors 02");
        try {
           
        cBaseDatos objDatos = new cBaseDatos();
        String accion = request.getParameter("accion");
        String boton = request.getParameter("boton");
        if (accion == null) {
            System.out.println("----->>>    Pors 03 IF");
            accion = "bienvenida";
        }
        System.out.println("----->>>    Pors 03 accion:"+accion);
        System.out.println("----->>>    Pors 03 boton:"+request.getParameter("boton") + "  Validate : " );
        
        if (accion.equals("bienvenida")) {
            request.getRequestDispatcher("/contenido.html").forward(request, response);
        } else if (accion.equals("listadoAreas")) {
            System.out.println("Llego aqui " + authenticationCheck(request));
            if (authenticationCheck(request)) {
                Vector arrAreas = (Vector) objDatos.getAreas();
                request.setAttribute("arrAreas", arrAreas);
                request.getRequestDispatcher("/mantenimientos/listadoAreas.jsp").forward(
                        request, response);
            } else {
                response.sendRedirect("errorAutenticacion.html");
            }

        } else if (accion.equals("NuevoEliminarArea")) {
            if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {
                Vector registro = new Vector();
                registro.add("");
                registro.add("");
                registro.add("");
                registro.add("");

                request.setAttribute("registro", registro);
                request.setAttribute("operacion", "INSERT");
                request.setAttribute("titulo", "Nueva Area");
                request.getRequestDispatcher("/mantenimientos/editarAreas.jsp").forward(
                        request, response);
            } else {
                String[] datos = request.getParameterValues("xcod");
                objDatos.eliminarAreas(datos);
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                        request, response);
            }
        } else if (accion.compareTo("GRABAR_AREA") == 0) {
            if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                String operacion = request.getParameter("operacion");
                if (operacion.equals("INSERT")) {
                    String[] datos = new String[3];
                    datos[0] = request.getParameter("xnom");
                    datos[1] = request.getParameter("xabr");
                    datos[2] = request.getParameter("xest");
                    objDatos.grabarNuevaArea(datos);
                } else {
                    String[] datos = new String[4];
                    datos[0] = request.getParameter("xcod");
                    datos[1] = request.getParameter("xnom");
                    datos[2] = request.getParameter("xabr");
                    datos[3] = request.getParameter("xest");
                    objDatos.modificarArea(datos);
                }

            }
            request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                    request, response);
        } else if (accion.compareTo("modificarArea") == 0) {
            String xcod = request.getParameter("xcod");
            Vector registro = objDatos.buscarArea(xcod);

            request.setAttribute("registro", registro);
            request.setAttribute("operacion", "UPDATE");
            request.setAttribute("titulo", "Modificar Area");
            request.getRequestDispatcher("/mantenimientos/editarAreas.jsp").forward(
                    request, response);
        } else if (accion.equals("listadoAlumnos")) {

            List<Alumnos> arrAlumno = new ArrayList<>();
            IAlumnosDAO dao = new AlumnoDAOImpl();
            arrAlumno = dao.obtener();
            request.setAttribute("arrAlumnos", arrAlumno);
            request.getRequestDispatcher("/mantenimientos/listadoAlumnos.jsp").forward(
                    request, response);
        } else if (accion.equals("NuevoEliminarAlumno")) {
            if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {

                Alumnos alumno = new Alumnos();
                request.setAttribute("alumno", alumno);
                request.setAttribute("operacion", "INSERT");
                request.setAttribute("titulo", "Nuevo ALumno");
                request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(
                        request, response);

            } else {
            }

        } else if (accion.compareTo("GRABAR_ALUMNO") == 0) {
            if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                String operacion = request.getParameter("operacion");
                String strDate = request.getParameter("xfec");
                Date xfe = Date.valueOf(strDate);
                Alumnos alumno = new Alumnos();

                alumno.setCodigo(Integer.parseInt(request.getParameter("xcod")));
                alumno.setNombre(request.getParameter("xnom"));
                alumno.setDireccion(request.getParameter("xdir"));
                alumno.setEmail(request.getParameter("xema"));
                alumno.setTelefono(request.getParameter("xtel"));
                alumno.setCelular(request.getParameter("xcel"));
                alumno.setSexo(request.getParameter("xsex"));
                alumno.setFec_nac(xfe);
                alumno.setEstado(request.getParameter("xest"));

                if (operacion.equals("INSERT")) {
                    IAlumnosDAO dao = new AlumnoDAOImpl();
                    dao.registrar(alumno);
                } else {
                    IAlumnosDAO dao = new AlumnoDAOImpl();
                    dao.actualizar(alumno);
                }
            }
            request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(
                    request, response);
        } else if (accion.compareTo("modificarAlumno") == 0) {
            String xcod = request.getParameter("xcod").trim();
            IAlumnosDAO dao = new AlumnoDAOImpl();
            Alumnos alumno = dao.buscar(Integer.parseInt(xcod));
            request.setAttribute("alumno", alumno);
            request.setAttribute("operacion", "UPDATE");
            request.setAttribute("titulo", "Modificar Alumno");
            request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(
                    request, response);
        } else if (accion.equals("listadoCursos")) {

            List<Cursos> arrCurso = new ArrayList<>();
            ICursosDAO dao = new CursoDAOImpl();
            arrCurso = dao.obtener();
            request.setAttribute("arrCursos", arrCurso);
            request.getRequestDispatcher("/mantenimientos/listarCursos.jsp").forward(
                    request, response);
        }  else if (accion.equals("NuevoEliminarCurso")) {
            if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {

                Cursos curso = new Cursos();
                request.setAttribute("curso", curso);
                request.setAttribute("operacion", "INSERT");
                request.setAttribute("titulo", "Nuevo Curso");
                request.getRequestDispatcher("/mantenimientos/editarCursos.jsp").forward(
                        request, response);

            } else {
            }

        }else if (accion.compareTo("GRABAR_CURSO") == 0) {
            if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                String operacion = request.getParameter("operacion");
                if (operacion.equals("INSERT")) {
                    String[] datos = new String[9];
                    datos[0] = request.getParameter("xnom");
                    datos[1] = request.getParameter("xcos");
                    datos[2] = request.getParameter("xini");
                    datos[3] = request.getParameter("xfin");
                    datos[4] = request.getParameter("xdur");
                    datos[5] = request.getParameter("xses");
                    datos[6] = request.getParameter("xcap");
                    datos[7] = request.getParameter("xins");
                    datos[8] = request.getParameter("xest");
                    objDatos.grabarNuevaCurso(datos);
                } else {
                     String[] datos = new String[10];
                        datos[0] = request.getParameter("xcod");
                        datos[1] = request.getParameter("xnom");
                        datos[2] = request.getParameter("xcos");
                        datos[3] = request.getParameter("xini");
                        datos[4] = request.getParameter("xfin");
                        datos[5] = request.getParameter("xdur");
                        datos[6] = request.getParameter("xses");
                        datos[7] = request.getParameter("xcap");
                        datos[8] = request.getParameter("xins");
                        datos[9] = request.getParameter("xest");
                     objDatos.modificarCurso(datos);
                }

            }
            request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(
                    request, response);
        }else if (accion.compareTo("modificarCurso") == 0) {
            String xcod = request.getParameter("xcod").trim();
            ICursosDAO dao = new CursoDAOImpl();
            Cursos curso = dao.buscar(Integer.parseInt(xcod)); 
            request.setAttribute("curso", curso);
            request.setAttribute("operacion", "UPDATE");
            request.setAttribute("titulo", "Modificar Alumno");
            request.getRequestDispatcher("/mantenimientos/editarCursos.jsp").forward(
                    request, response);
        } else if (accion.compareTo("matriculaMostrarAlumnos") == 0) {
            
             if (request.getParameter("modo").compareTo("Lista") == 0){
                 List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
                 String strAlumno = " ";
                 IMatriculasDAO dao = new MatriculaDAOImpl();
                 arrAlumnos = dao.buscarAlumnos((strAlumno));
                 request.setAttribute("arrAlumnos",arrAlumnos);
                 request.getRequestDispatcher("/mantenimientos/matriculaMostrarAlumnos.jsp").forward(
                    request, response);
                 
             }else if(request.getParameter("boton").compareTo("Buscar")==0){
                 List<Alumnos> arrAlumnos = new ArrayList<Alumnos>();
             String strAlumno = request.getParameter("xalu");
             IMatriculasDAO dao = new MatriculaDAOImpl();
                 arrAlumnos = dao.buscarAlumnos((strAlumno));
                 request.setAttribute("arrAlumnos",arrAlumnos);
                 request.setAttribute("nombre",strAlumno);
             request.getRequestDispatcher("/mantenimientos/matriculaMostrarAlumnos.jsp").forward(
                    request, response);
             
             }else{
                 int xcodAlumno = Integer.parseInt(request.getParameter("xcodAlumno"));
                 Alumnos alumno = new Alumnos();
                 IAlumnosDAO dao = new AlumnoDAOImpl();
                 alumno=dao.buscar(xcodAlumno);
                 
                 List<Cursos> arrCurso = new ArrayList<Cursos>();
                 IMatriculasDAO dao2 = new MatriculaDAOImpl();
                 arrCurso = dao2.buscarCursos();
                  request.setAttribute("arrCursos",arrCurso);
                   request.setAttribute("alumno",alumno);
                  request.getRequestDispatcher("/mantenimientos/matriculaMostrarCursos.jsp").forward(
                    request, response);
                         
             }
        }
        else if (accion.compareTo("matriculaMostrarSubtotal") == 0) {
           String xcodCursos[] = request.getParameterValues("xcodCurso");
           List<Cursos> arrCursos = new ArrayList<Cursos>();
           ICursosDAO dao  = new CursoDAOImpl();
           double total  = 0;
           for(int xc = 0 ;xc<xcodCursos.length;xc++){
               Cursos curso = new Cursos();
               curso =  dao.buscar(Integer.parseInt(xcodCursos[xc]));
               double costo = curso.getCosto();
               total  =  total + costo ;
               arrCursos.add(curso);
           }
           int xcodAlumno = Integer.parseInt((request.getParameter("xcodAlumno")));
           Alumnos alumno = new Alumnos();
           IAlumnosDAO dao2 = new AlumnoDAOImpl();
           alumno = dao2.buscar(xcodAlumno);
                request.setAttribute("arrCursos",arrCursos);
                request.setAttribute("alumno",alumno);
                request.setAttribute("total",total);
            request.getRequestDispatcher("/mantenimientos/matriculaMostrarSubtotal.jsp").forward(
                    request, response);
                   
        }
        else if (accion.compareTo("matriculaGrabar") == 0){
        if (request.getParameter("boton").compareTo("GRABAR") == 0){
            String xcodAlumno = request.getParameter("xcodAlumno");
            String xcodCursos[] = request.getParameterValues("xcodCurso");
            String xmontos[] = request.getParameterValues("xmonto");
            
            String[] datosMatricula = new String[3];
            datosMatricula[0] = request.getParameter("xndoc");
            datosMatricula[1] = xcodAlumno;
            datosMatricula[2] = request.getParameter("xtotal");
             
            IMatriculasDAO dao = new MatriculaDAOImpl();
            boolean rpta = dao.grabarMatricula(datosMatricula, xcodCursos, xmontos);
            if(rpta){
                out.println("<br> <h2>Registro grabado correctamente</h2>");
            }
            else{
                out.println("<br> <h2>Error al grabar Matricula </h2>");
            }
        }
        
        }
        
         else if (accion.compareTo("matriculaMostrarContrato") == 0) {
             
              
            if (request.getParameter("modo").compareTo("Lista") == 0) {
                List<Contrato> arrMatriculas = new ArrayList<Contrato>();
                String strMatricula = "X";
                IMatriculasDAO dao = new MatriculaDAOImpl();
                arrMatriculas = dao.buscarContrato((strMatricula));
                request.setAttribute("arrMatriculas", arrMatriculas);
                request.getRequestDispatcher("/mantenimientos/matriculaMostrarContratos.jsp").forward(
                        request, response);

            } else if (request.getParameter("boton").compareTo("Buscar") == 0) {

                List<Contrato> arrMatriculas = new ArrayList<Contrato>();
                String strMatricula = request.getParameter("xnro");
                IMatriculasDAO dao = new MatriculaDAOImpl();
                arrMatriculas = dao.buscarContrato((strMatricula));
                request.setAttribute("arrMatriculas", arrMatriculas);
                request.setAttribute("nombre", strMatricula);
                request.getRequestDispatcher("/mantenimientos/matriculaMostrarContratos.jsp").forward(
                        request, response);

            }  else if (request.getParameter("boton").compareTo("Editar") == 0) {
            String xnrodoc = request.getParameter("xndoc");
             IMatriculasDAO dao = new MatriculaDAOImpl();
             List arrMatr = dao.buscarMatricula(xnrodoc);
             List arrDetM    = dao.buscarDetallesMatricula(xnrodoc);
             request.setAttribute("xndoc", xnrodoc);
             request.setAttribute("arrMatr", arrMatr);
             request.setAttribute("arrDetM", arrDetM); 
                request.getRequestDispatcher("/mantenimientos/matriculaMostrarContratosDetalle.jsp").forward(
                        request, response);

            }
            
        }else if (accion.compareTo("matriculaMostrarContratoCursos") == 0) {
              boton =request.getParameter("boton") ;
            
             switch (boton) 
        {
            case "Lista":  
                 String xnrodoc = request.getParameter("xndoc");
                 List<Cursos> arrCurso = new ArrayList<Cursos>();
                 IMatriculasDAO dao2 = new MatriculaDAOImpl(); 
                 arrCurso = dao2.buscarCursos(); 
                  request.setAttribute("arrCursos",arrCurso);
                  request.setAttribute("xndoc",xnrodoc);
                  request.getRequestDispatcher("/mantenimientos/matriculaMostrarContratosCursos.jsp").forward(
                    request, response);
                     break;
                     
            case "GuardarCurso":  
                
                 String codCurso =request.getParameter("xcodCurso") ;     
                  xnrodoc = request.getParameter("xndoc");
            
             IMatriculasDAO dao = new MatriculaDAOImpl();
             List arrMatr = dao.buscarMatricula(xnrodoc);
             dao.buscarContrato(xnrodoc);
             Contrato contrato = new Contrato();
             contrato = (Contrato)dao.buscarContrato(xnrodoc).get(0);
             dao.grabarMatriculaDetalle(contrato.getCodigoM(), codCurso);
             List arrDetM    = dao.buscarDetallesMatricula(xnrodoc);
             
             request.setAttribute("xndoc", xnrodoc);
             request.setAttribute("arrMatr", arrMatr);
             request.setAttribute("arrDetM", arrDetM); 
                request.getRequestDispatcher("/mantenimientos/matriculaMostrarContratosDetalle.jsp").forward(
                        request, response);
                     break;
            
            case "EliminarCurso":  
                
                   codCurso =request.getParameter("xcodCurso") ;     
                  xnrodoc = request.getParameter("xndoc");
            
             IMatriculasDAO daoa = new MatriculaDAOImpl();
               arrMatr = daoa.buscarMatricula(xnrodoc);
             daoa.buscarContrato(xnrodoc);
               contrato = new Contrato();
             contrato = (Contrato)daoa.buscarContrato(xnrodoc).get(0);
             daoa.borrarMatriculaDetalle(contrato.getCodigoM(), codCurso);
               arrDetM    = daoa.buscarDetallesMatricula(xnrodoc);
             
             request.setAttribute("xndoc", xnrodoc);
             request.setAttribute("arrMatr", arrMatr);
             request.setAttribute("arrDetM", arrDetM); 
                request.getRequestDispatcher("/mantenimientos/matriculaMostrarContratosDetalle.jsp").forward(
                        request, response);
                
                     break;
           
        }
            
          
            
            
        } 
         else if (accion.compareTo("anulacionSeleccionarMatricula") == 0){
             request.getRequestDispatcher("/mantenimientos/anulacionSeleccionarMatricula.jsp").forward(
                        request, response);
         }
         else if (accion.compareTo("anulacionMostrarMatricula") == 0){
             String xnrodoc = request.getParameter("xndoc");
             IMatriculasDAO dao = new MatriculaDAOImpl();
             List arrMatr = dao.buscarMatricula(xnrodoc);
             List arrDetM    = dao.buscarDetallesMatricula(xnrodoc);
             request.setAttribute("xndoc", xnrodoc);
             request.setAttribute("arrMatr", arrMatr);
             request.setAttribute("arrDetM", arrDetM);
             request.getRequestDispatcher("/mantenimientos/anulacionMostrarMatricula.jsp").forward(
                        request, response);
         }
         else if (accion.compareTo("anularMatricula") == 0){
              if (request.getParameter("boton").compareTo("Anular Matricula") == 0){
                  String xndoc = request.getParameter("xndoc");
                  IMatriculasDAO dao = new MatriculaDAOImpl();
                  boolean anular = dao.anularMatricula(xndoc);
                  if(anular){
                      out.println("<h2>Registro anulado Correctamente</h2>");
                  }else{
                      out.println("<h2>Se produjo un error al anular la matricula</h2>");
                  }
             }
         
         }
        else {
            out.println("Accion: (" + accion + ") no reconocida...");
        }
    } catch (Exception ex) {
        System.out.println(ex.toString());
    } finally {
        out.close();
    }
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         System.out.println("ZXXXXXXXXXXXXXXXXXXXXXXXX    GET 01");
        processRequest(request, response);
        System.out.println("ZXXXXXXXXXXXXXXXXXXXXXXXX    GET 02");
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
        processRequest(request, response);
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

    public boolean authenticationCheck(HttpServletRequest request){
    HttpSession session=request.getSession(); 
    String atrib =(String) session.getAttribute("authentication");
    System.out.println("Atribute : "+session.getAttribute("authentication"));
    if(atrib.equals("true")){
       return true;
    }else{
    return false;
}
        
    }
}