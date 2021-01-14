/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.modelo.Conexion;
import com.empresa.modelo.Cursos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Banks
 */
public class CursoDAOImpl implements ICursosDAO{
    
       public boolean registrar(Cursos cursos){
	Conexion co = new Conexion();
        String xcod = co.generarCodigo("cursos", "codigo");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO cursos values ('" + xcod + "', '"
                + cursos.getNombre() + "','"
                + cursos.getCosto()+ "','"
                + cursos.getFec_ini()+ "','"
                + cursos.getFec_fin()+ "','"
                + cursos.getDuracion()+ "','"
                + cursos.getSesiones()+ "','"
                + cursos.getCapacidad()+ "','"
                + cursos.getInscritos()+ "','"
                + cursos.getEstado()+ "' )";
        System.out.println(" INSERTAR :" + sql);
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();

        } catch (Exception e) {
        }
        return registrar;
}
public List<Cursos> obtener(){
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
                Cursos cursos = new Cursos(); 
                cursos.setCodigo(rs.getInt(1));
                cursos.setNombre(rs.getString(2));
                cursos.setCosto(rs.getFloat(3));
                cursos.setFec_ini(rs.getDate(4));
                cursos.setFec_fin(rs.getDate(5));
                cursos.setDuracion(rs.getInt(6));
                cursos.setSesiones(rs.getInt(7));
                cursos.setCapacidad(rs.getInt(8));
                cursos.setInscritos(rs.getInt(9));
                cursos.setEstado(rs.getString(10));
                listaCursos.add(cursos);
            }
            stm.close();
            rs.close();
            co.close();

        } catch (Exception e) {

        }
        return listaCursos;
}
public boolean actualizar(Cursos cursos){
	Conexion co = new Conexion(); 
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE  cursos SET" 
                + " nombre = '" + cursos.getNombre()+ "',"
                + " costo = '" + cursos.getCosto()+ "',"
                + " fec_ini = '" + cursos.getFec_ini()+ "',"
                + " fec_fin = '" + cursos.getFec_fin()+ "',"
                + " duracion = '" + cursos.getDuracion()+ "',"
                + " sesiones = '" + cursos.getSesiones()+ "',"
                + " capacidad = '" + cursos.getCapacidad()+ "',"
                + " inscritos = '" + cursos.getInscritos()+ "',"
                + " estado = '" + cursos.getEstado()+ "',"
                + " WHERE codigo = " + cursos.getCodigo();
        System.out.println(sql);
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (Exception e) {
        }
        return actualizar;
}
public boolean eliminar(Cursos cursos){
	return true;
}
public Cursos  buscar(int codigo){
	Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos where codigo=" + codigo;
        Cursos cursos = new Cursos();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

               cursos.setCodigo(rs.getInt(1));
                cursos.setNombre(rs.getString(2));
                cursos.setCosto(rs.getFloat(3));
                cursos.setFec_ini(rs.getDate(4));
                cursos.setFec_fin(rs.getDate(5));
                cursos.setDuracion(rs.getInt(6));
                cursos.setSesiones(rs.getInt(7));
                cursos.setCapacidad(rs.getInt(8));
                cursos.setInscritos(rs.getInt(9));
                cursos.setEstado(rs.getString(10));

            }
            stm.close();
            rs.close();
            co.close();
        } catch (Exception e) {
        }
        return cursos;
}
    
}
