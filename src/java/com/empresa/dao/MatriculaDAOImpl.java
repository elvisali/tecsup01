/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.modelo.Cursos;
import com.empresa.modelo.Alumnos;
import com.empresa.modelo.Conexion;
import com.empresa.modelo.Contrato;
import com.empresa.modelo.Matriculas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Banks
 */
public class MatriculaDAOImpl implements IMatriculasDAO {

    @Override
    public List<Alumnos> buscarAlumnos(String nombre) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM alumnos WHERE nombre LIKE'%" + nombre + "%'";
        List<Alumnos> listaAlumnos = new ArrayList<Alumnos>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Alumnos alumno = new Alumnos();
                alumno.setCodigo(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setDireccion(rs.getString(3));
                alumno.setEmail(rs.getString(4));
                alumno.setTelefono(rs.getString(5));
                alumno.setCelular(rs.getString(6));
                alumno.setSexo(rs.getString(7));
                alumno.setFec_nac(rs.getDate(8));
                alumno.setEstado(rs.getString(9));
                listaAlumnos.add(alumno);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método buscarAlumnos");
        }
        return listaAlumnos;
    }

    @Override
    public List<Cursos> buscarCursos() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos ORDER BY codigo";
        List<Cursos> listaCursos = new ArrayList<Cursos>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                curso.setCosto(rs.getInt(3));
                curso.setFec_ini(rs.getDate(4));
                curso.setFec_fin(rs.getDate(5));
                curso.setDuracion(rs.getInt(6));
                curso.setSesiones(rs.getInt(7));
                curso.setCapacidad(rs.getInt(8));
                curso.setInscritos(rs.getInt(9));
                curso.setEstado(rs.getString(10));
                listaCursos.add(curso);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método obtener");
        }
        return listaCursos;

    }

    public String getFecha() {
//        Calendar calendar = new GregorianCalendar();
//        Date date = (Date) calendar.getTime();
//        DateFormat formato = new SimpleDateFormat( "dd/MM/yyyy" );
//        return formato.format( date ) ;
        LocalDate fecha1 = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
        return fecha1.format(dtf);
    }

    @Override
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos) {
        Conexion co = new Conexion();
        String xcodm = co.generarCodigo("matriculas", "codigo");
        boolean registrar = true;
        Statement stm = null;
        Connection con = null;

        String sql = "insert into matriculas (codigo,fecha,nro_doc,"
                + "codigo_alumno,total,estado) values (?,?,?,?,?,'A') ";
        System.out.println("---------->    ");
        System.out.println("---------->  DATE : " + this.getFecha());
        System.out.println("---------->   ");
        String xfech = this.getFecha();

        String xndoc = datosMatricula[0];
        String xcoda = datosMatricula[1];
        String xtota = datosMatricula[2];

        try {
            con = co.Conectar();
            stm = con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, xcodm);
            ps.setString(2, xfech);
            ps.setString(3, xndoc);
            ps.setString(4, xcoda);
            ps.setString(5, xtota);
            ps.executeUpdate();

            for (int xc = 0; xc < codigoCursos.length; xc++) {
                String xcodCurso = codigoCursos[xc];
                this.grabarNuevoDetalle(con, xcodm, xcodCurso, montos[xc]);
            }

            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImpl, "
                    + "método grabarMatricula");
            e.printStackTrace();
            return false;
        }
        return registrar;
    }

    public void grabarNuevoDetalle(Connection xcon, String xcodm, String xcodc, String xmonto)
            throws SQLException {
        String sql = "insert into detalles (codigo_matricula,codigo_curso,"
                + "monto,asistencias,nota,estado) values (?,?,?,0,0,'A') ";

        PreparedStatement ps = xcon.prepareStatement(sql);
        ps.setString(1, xcodm);
        ps.setString(2, xcodc);
        ps.setString(3, xmonto);
        ps.executeUpdate();

        // actualizar nro de inscritos en curso
        sql = "update cursos set inscritos=inscritos+1 where codigo=?";
        PreparedStatement psc = xcon.prepareStatement(sql);
        psc.setString(1, xcodc);
        psc.executeUpdate();
    }
    
    public void borrarDetalle(Connection xcon, String xcodm, String xcodc )
            throws SQLException {
        String sql = " delete FROM detalles where codigo_matricula = ? and codigo_curso = ?   ";

        PreparedStatement ps = xcon.prepareStatement(sql);
        ps.setString(1, xcodm);
        ps.setString(2, xcodc); 
        ps.executeUpdate();

        // actualizar nro de inscritos en curso
        sql = "update cursos set inscritos=inscritos-1 where codigo=?";
        PreparedStatement psc = xcon.prepareStatement(sql);
        psc.setString(1, xcodc);
        psc.executeUpdate();
    }

    @Override
    public List buscarContrato(String pnrodoc) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "";

        if (pnrodoc.equals("X")) {
            sql = "select m.codigo ,a.nombre,m.fecha,m.total,m.estado,m.nro_doc from alumnos a, "
                    + "matriculas m where a.codigo=m.codigo_alumno   ";
        } else {
            sql = "select m.codigo ,a.nombre,m.fecha,m.total,m.estado,m.nro_doc from alumnos a, "
                    + "matriculas m where a.codigo=m.codigo_alumno AND nro_doc=? ";
        }

        List matricula = new ArrayList();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            PreparedStatement ps = co.prepareStatement(sql);
            if (!pnrodoc.equals("X")) {
                ps.setString(1, pnrodoc);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Contrato mat = new Contrato();

                mat.setCodigoM(rs.getString(1));
                mat.setNombre(rs.getString(2));
                mat.setFecha(rs.getString(3));
                mat.setTotal(rs.getString(4));
                mat.setEstado(rs.getString(5));
                mat.setNro(rs.getString(6));
                matricula.add(mat);
            }
            co.close();
            return matricula;

        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método buscarMatricula");
            e.printStackTrace();
        }
        return matricula;
    }

    @Override
    public List buscarMatricula(String pnrodoc) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select a.nombre,m.fecha,m.total,m.estado from alumnos a, "
                + "matriculas m where a.codigo=m.codigo_alumno AND nro_doc=? ";
        List matricula = new ArrayList();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, pnrodoc);
            rs = ps.executeQuery();

            while (rs.next()) {
                matricula.add(rs.getString(1));
                matricula.add(rs.getString(2));
                matricula.add(rs.getString(3));
                matricula.add(rs.getString(4));
            }
            co.close();
            return matricula;

        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método buscarMatricula");
            e.printStackTrace();
        }
        return matricula;
    }

    @Override
    public List buscarDetallesMatricula(String pnrodoc) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select d.codigo_curso,c.nombre,d.monto "
                + "from matriculas m, cursos c, detalles d "
                + "where d.codigo_curso=c.codigo AND m.codigo=d.codigo_matricula "
                + "AND m.nro_doc=? ";

        List listaDetalleMatricula = new ArrayList();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, pnrodoc);
            rs = ps.executeQuery();

            while (rs.next()) {
                List detallematricula = new ArrayList();
                detallematricula.add(rs.getString(1));
                detallematricula.add(rs.getString(2));
                detallematricula.add(rs.getString(3));
                listaDetalleMatricula.add(detallematricula);
            }
            co.close();
            return listaDetalleMatricula;

        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método buscarDetallesMatricula");
            e.printStackTrace();
        }
        return listaDetalleMatricula;
    }

    @Override
    public boolean anularMatricula(String pnrodoc) {
        Conexion co = new Conexion();
        boolean anular = true;
        Statement stm = null;
        Connection con = null;

        // 1. Colocar estado de la matricula en X
        String sql = "update matriculas set estado='X' where nro_doc=?";

        try {
            con = co.Conectar();
            stm = con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pnrodoc);
            ps.executeUpdate();

            // 2. Restarle 1 al inscritos de cada curso involucrado
            List cursos = this.buscarDetallesMatricula(pnrodoc);
            for (int xc = 0; xc < cursos.size(); xc++) {
                List fila = (List) cursos.get(xc);
                String xcodc = (String) fila.get(0);
                sql = "update cursos set inscritos=inscritos-1 where codigo=? ";
                PreparedStatement ps2 = con.prepareStatement(sql);
                ps2.setString(1, xcodc);
                ps2.executeUpdate();
                ps2.close();
            }
            ps.close();
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImpl, "
                    + "método anularMatricula");
            e.printStackTrace();
            return false;
        }
        return anular;
    }
    
    @Override
    public boolean grabarMatriculaDetalle(String codigoMatricula , String codigoCurso){
       Conexion co = new Conexion();
        String xcodm = codigoMatricula;
        boolean registrar = true;
        Statement stm = null;
        Connection con = null;
         try {
            con = co.Conectar();
            stm = con.createStatement();
        
                this.grabarNuevoDetalle(con, xcodm, codigoCurso, "0");
             
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImpl, "
                    + "método grabarMatriculaDetalle");
            e.printStackTrace();
            return false;
        }
        return registrar;
    }
    
    @Override
    public boolean borrarMatriculaDetalle(String codigoMatricula , String codigoCurso){
        Conexion co = new Conexion();
        String xcodm = codigoMatricula;
        boolean borrar = true;
        Statement stm = null;
        Connection con = null;
         try {
            con = co.Conectar();
            stm = con.createStatement();
        
                this.borrarDetalle(con, xcodm, codigoCurso );
             
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImpl, "
                    + "método grabarMatriculaDetalle");
            e.printStackTrace();
            return false;
        }
        return borrar;
    }
     
}
