package com.example.ultimolab.beans;
import java.sql.Date;
import java.sql.Time;
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;
    private Date fechaUltimo;
    private Time horaUltimo;
    private int cantIngresos;
    private Date fechaRegistro;
    private Time horaRegistro;
    private Date fechaEdicion;
    private Time horaEdicion;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getFechaUltimo() {
        return fechaUltimo;
    }

    public void setFechaUltimo(Date fechaUltimo) {
        this.fechaUltimo = fechaUltimo;
    }

    public Time getHoraUltimo() {
        return horaUltimo;
    }

    public void setHoraUltimo(Time horaUltimo) {
        this.horaUltimo = horaUltimo;
    }

    public int getCantIngresos() {
        return cantIngresos;
    }

    public void setCantIngresos(int cantIngresos) {
        this.cantIngresos = cantIngresos;
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
