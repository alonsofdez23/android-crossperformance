package com.unir.cross_performance.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Clase {
    @SerializedName("id")
    private int id;

    @SerializedName("monitor_id")
    private int monitorId;

    @SerializedName("entreno_id")
    private Integer entrenoId; // Puede ser nulo

    @SerializedName("fecha_hora")
    private Date fechaHora;

    @SerializedName("vacantes")
    private int vacantes;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("atletas")
    private List<Atleta> atletas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(int monitorId) {
        this.monitorId = monitorId;
    }

    public Integer getEntrenoId() {
        return entrenoId;
    }

    public void setEntrenoId(Integer entrenoId) {
        this.entrenoId = entrenoId;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", monitorId=" + monitorId +
                ", entrenoId=" + entrenoId +
                ", fechaHora=" + fechaHora +
                ", vacantes=" + vacantes +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", atletas=" + atletas +
                '}';
    }
}
