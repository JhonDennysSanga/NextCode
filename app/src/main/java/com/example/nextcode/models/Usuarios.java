package com.example.nextcode.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Usuarios {

    @Expose
    private int id;
    @Expose
    private String cedula;
    @Expose
    private String contrasenia;
    @Expose
    private String nombres;
    @Expose
    private String apellidos;
    @Expose
    private String correo;
    @Expose
    private String status;
    @Expose
    private ArrayList<PlanesUsuario> planes;
    @Expose
    private ArrayList<Facturas> facturas;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PlanesUsuario> getPlanes() {
        return planes;
    }

    public ArrayList<Facturas> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Facturas> facturas) {
        this.facturas = facturas;
    }

    public void setPlanes(ArrayList<PlanesUsuario> planes) {
        this.planes = planes;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
