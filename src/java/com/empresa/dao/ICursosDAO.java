/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.modelo.Cursos;
import java.util.List;

/**
 *
 * @author Banks
 */
public interface ICursosDAO {
    public boolean registrar(Cursos cursos);
    public List<Cursos> obtener();
    public boolean actualizar(Cursos cursos);
    public boolean eliminar(Cursos cursos);
    public Cursos  buscar(int codigo);
}
