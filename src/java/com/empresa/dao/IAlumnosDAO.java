/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;
import com.empresa.modelo.Alumnos;
import java.util.List;

/**
 *
 * @author Banks
 */
public interface IAlumnosDAO {
    public boolean registrar(Alumnos alumno);
    public List<Alumnos> obtener();
    public boolean actualizar(Alumnos alumno);
    public boolean eliminar(Alumnos alumno);
    public Alumnos  buscar(int codigo);
}
