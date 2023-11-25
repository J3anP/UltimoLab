package com.example.ultimolab.daos;

import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.Evaluaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoEvaluaciones extends DaoBase{
    public Evaluaciones obtenerEvaluaciones(int idEvaluaciones){
        Evaluaciones evaluaciones = new Evaluaciones();

        DaoCurso daoCurso = new DaoCurso();
        DaoSemestre daoSemestre = new DaoSemestre();
        String sql = "select * from evaluaciones where idevaluaciones = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idEvaluaciones);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    evaluaciones.setIdEvaluaciones(rs.getInt("idevaluaciones"));
                    evaluaciones.setNombreEstudiante(rs.getString("nombre_estudiante"));
                    evaluaciones.setCodigoEstudiante(rs.getString("codigo_estudiante"));
                    evaluaciones.setCorreoEstudiante(rs.getString("correo_estudiante"));
                    evaluaciones.setNota(rs.getInt("nota"));
                    evaluaciones.setCurso(daoCurso.obtenerCurso(rs.getInt("idcurso")));
                    evaluaciones.setSemestre(daoSemestre.obtenerSemestre(rs.getInt("idsemestre")));
                    evaluaciones.setFechaRegistro(rs.getDate("fecha_registro"));
                    evaluaciones.setFechaEdicion(rs.getDate("fecha_edicion"));


                }
                else {
                    evaluaciones = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return evaluaciones;
    }

    public ArrayList<Evaluaciones> listarEvaluaciones(int idDocente){
        ArrayList<Evaluaciones> listaEvaluaciones = new ArrayList<>();
        DaoCurso daoCurso = new DaoCurso();
        DaoSemestre daoSemestre  = new DaoSemestre();
        String sql = "select * from evaluaciones e left join curso_has_docente chd on chd.idcurso = e.idcurso where chd.iddocente = ?";
        try(Connection conn=this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idDocente);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Evaluaciones evaluaciones = new Evaluaciones();
                    evaluaciones.setIdEvaluaciones(rs.getInt("idevaluaciones"));
                    evaluaciones.setNombreEstudiante(rs.getString("nombre_estudiante"));
                    evaluaciones.setCodigoEstudiante(rs.getString("codigo_estudiante"));
                    evaluaciones.setCorreoEstudiante(rs.getString("correo_estudiante"));
                    evaluaciones.setNota(rs.getInt("nota"));
                    evaluaciones.setCurso(daoCurso.obtenerCurso(rs.getInt("idcurso")));
                    evaluaciones.setSemestre(daoSemestre.obtenerSemestre(rs.getInt("idsemestre")));
                    evaluaciones.setFechaRegistro(rs.getDate("fecha_registro"));
                    evaluaciones.setFechaEdicion(rs.getDate("fecha_edicion"));
                    listaEvaluaciones.add(evaluaciones);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEvaluaciones;
    }

    public void editarEvaluacion(int idEvaluaciones, String nombre, String codigo, String correo, int nota){
        String sql = "update evaluaciones set nombre_estudiante =?, codigo_estudiante = ?,correo_estudiante = ?, nota = ?, fecha_edicion = now() where idevaluaciones = ?";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setString(2,codigo);
            pstmt.setString(3,correo);
            pstmt.setInt(4,nota);
            pstmt.setInt(5,idEvaluaciones);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarEvaluacion(String nombre, String codigo, String correo, int nota, int idCurso,int idSemestre){
        String sql = "insert into evaluaciones(nombre_estudiante,codigo_estudiante,correo_estudiante,nota,idcurso,idsemestre,fecha_registro,fecha_edicion) values (?,?,?,?,?,?,now(),now())";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setString(2,codigo);
            pstmt.setString(3,correo);
            pstmt.setInt(4,nota);
            pstmt.setInt(5,idCurso);
            pstmt.setInt(6,idSemestre);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void eliminarEvaluacion(int idEvaluacion){

        boolean habilitado = obtenerEvaluaciones(idEvaluacion).getSemestre().getHabilitado();
        if(habilitado){
            String sql = "delete from evaluaciones where idevaluaciones = ?";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1,idEvaluacion);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
