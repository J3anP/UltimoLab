package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Semestre;
import com.example.ultimolab.beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DaoSemestre extends DaoBase{

    public Semestre obtenerSemestre(int idSemestre){
        String sql = "select * from semestre where idsemestre = ?";
        Semestre semestre = new Semestre();
        DaoUsuario daoUsuario = new DaoUsuario();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idSemestre);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    semestre.setIdSemestre(rs.getInt("idsemestre"));
                    semestre.setNombre(rs.getString("nombre"));
                    semestre.setAdministrador(daoUsuario.obtenerAdministrador(rs.getInt("idadministrador")));
                    semestre.setHabilitado(rs.getBoolean("habilitado"));
                    semestre.setFechaRegistro(rs.getDate("fecha_registro"));
                    semestre.setFechaEdicion(rs.getDate("fecha_edicion"));

                }
                else {
                    semestre = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return semestre;
    }
    public Integer obtenerIdSemestrePorNombre(String nombre){
        Integer idSemestre = 0;
        String sql = "select idsemestre from semestre where nombre = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nombre);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    idSemestre = rs.getInt("idsemestre");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idSemestre;
    }
    public ArrayList<Semestre> listaSemestres( ){
        ArrayList<Semestre> listaSemestres = new ArrayList<>();
        String sql = "select * semestre";
        try(Connection conn=this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Semestre semestre = new Semestre();
                    semestre.setIdSemestre(rs.getInt("idsemestres"));
                    semestre.setNombre(rs.getString("nombre"));
                    semestre.setHabilitado(rs.getBoolean("habilitado"));
                    listaSemestres.add(semestre);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaSemestres;
    }
    public boolean haySemestre(int idSemestre){
        String sql = "select idsemestre from semestre where idsemestre=?";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idSemestre);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
