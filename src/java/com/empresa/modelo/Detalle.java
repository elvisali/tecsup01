/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.modelo;

/**
 *
 * @author Elvis
 */
public class Detalle {
    
    int codigo_matricula;
int codigo_curso;
float monto;
int asistencias;
int nota;
String estado;

    public Detalle(int codigo_matricula, int codigo_curso, float monto, int asistencias, int nota, String estado) {
        this.codigo_matricula = codigo_matricula;
        this.codigo_curso = codigo_curso;
        this.monto = monto;
        this.asistencias = asistencias;
        this.nota = nota;
        this.estado = estado;
    }

public Detalle(){}

    public int getCodigo_matricula() {
        return codigo_matricula;
    }

    public void setCodigo_matricula(int codigo_matricula) {
        this.codigo_matricula = codigo_matricula;
    }

    public int getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(int codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
