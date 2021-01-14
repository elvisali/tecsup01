/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.modelo;

import java.util.Date;

/**
 *
 * @author Elvis
 */
public class Matriculas {
    int codigo ;
Date fecha ;
String nro_doc ;
int codigo_alumno ;
float total ;
String estado ;

    public Matriculas(int codigo, Date fecha, String nro_doc, int codigo_alumno, float total, String estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.nro_doc = nro_doc;
        this.codigo_alumno = codigo_alumno;
        this.total = total;
        this.estado = estado;
    }

public Matriculas(){}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public int getCodigo_alumno() {
        return codigo_alumno;
    }

    public void setCodigo_alumno(int codigo_alumno) {
        this.codigo_alumno = codigo_alumno;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



}
