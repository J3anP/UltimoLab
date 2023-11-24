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
}
