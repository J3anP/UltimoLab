package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Semestre;

import java.sql.*;
public class DaoSemestre extends DaoBase{

    public Semestre obtenerSemestre(int idSemestre){
        String sql = "select * from semestre where idsemestre = 1";
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
}
