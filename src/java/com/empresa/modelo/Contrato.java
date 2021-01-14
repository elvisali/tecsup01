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
public class Contrato {
    String codigoM;
String nombre;
String fecha;
String total;
String estado;
String nro;



public Contrato(){}
    public String getCodigoM() {
        return codigoM;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public void setCodigoM(String codigoM) {
        this.codigoM = codigoM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Contrato(String codigoM, String nombre, String fecha, String total, String estado) {
        this.codigoM = codigoM;
        this.nombre = nombre;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }


}
