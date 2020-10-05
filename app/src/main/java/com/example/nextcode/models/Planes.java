package com.example.nextcode.models;

import com.google.gson.annotations.Expose;

public class Planes {

    @Expose
    private int id;
    @Expose
    private String nombre;
    @Expose
    private String subtotal;
    @Expose
    private String iva;
    @Expose
    private String total;
    @Expose
    private String tipo;
    @Expose
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Planes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", iva='" + iva + '\'' +
                ", total='" + total + '\'' +
                ", tipo='" + tipo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
