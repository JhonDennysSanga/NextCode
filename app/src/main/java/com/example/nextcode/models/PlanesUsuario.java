package com.example.nextcode.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class PlanesUsuario {

    @Expose
    private int id;
    @Expose
    private int usuario_id;
    @Expose
    private int plan_id;
    @Expose
    private String status;
    @Expose
    private Planes plan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Planes getPlan() {
        return plan;
    }

    public void setPlan(Planes plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "PlanesUsuario{" +
                "id=" + id +
                ", usuario_id=" + usuario_id +
                ", plan_id=" + plan_id +
                ", status='" + status + '\'' +
                '}';
    }
}
