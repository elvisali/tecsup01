/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;


import com.empresa.modelo.Alumnos;
import com.empresa.modelo.Cursos;
import java.util.List;

/**
 *
 * @author Banks
 */
public interface IMatriculasDAO {
    public List<Alumnos> buscarAlumnos(String nombre);
    public List<Cursos> buscarCursos();
    public boolean grabarMatricula(String[] datosMatricula,
            String[] codigoCursos, String[] montos);
    public List buscarContrato(String pnrodoc );
    public List buscarMatricula(String pnrodoc);
    public List buscarDetallesMatricula(String pnrodoc );
    public boolean anularMatricula(String pnrodoc );
    public boolean grabarMatriculaDetalle(String codigoMatricula , String codigoCurso);
    public boolean borrarMatriculaDetalle(String codigoMatricula , String codigoCurso);
    
     
}
