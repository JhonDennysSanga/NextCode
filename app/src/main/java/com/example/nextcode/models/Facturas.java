package com.example.nextcode.models;

import com.google.gson.annotations.Expose;

public class Facturas {

    @Expose
    private int id;
    @Expose
    private String serie;
    @Expose
    private int secuencial;
    @Expose
    private String fecha_emision;
    @Expose
    private String subtotal;
    @Expose
    private String iva;
    @Expose
    private String total;
    @Expose
    private String estado_pago;
    @Expose
    private int usuario_id;
    @Expose
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(int secuencial) {
        this.secuencial = secuencial;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
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

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Facturas{" +
                "id=" + id +
                ", serie='" + serie + '\'' +
                ", secuencial=" + secuencial +
                ", fecha_emision='" + fecha_emision + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", iva='" + iva + '\'' +
                ", total='" + total + '\'' +
                ", estado_pago='" + estado_pago + '\'' +
                ", usuario_id=" + usuario_id +
                ", status='" + status + '\'' +
                '}';
    }
}
