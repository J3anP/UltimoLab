package com.example.ultimolab.beans;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
public class Curso {
    private int idCurso;

    private String codigo;
    private String nombre;
    private Facultad facultad;
    private Date fechaRegistro;
    private Time horaRegistro;

    private Date fechaEdicion;
    private Time horaEdicion;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Time getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(Time horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Date fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public Time getHoraEdicion() {
        return horaEdicion;
    }

    public void setHoraEdicion(Time horaEdicion) {
        this.horaEdicion = horaEdicion;
    }
}
