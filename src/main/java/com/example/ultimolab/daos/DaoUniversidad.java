package com.example.ultimolab.daos;

import com.example.ultimolab.beans.Universidad;

import java.sql.*;

public class DaoUniversidad extends DaoBase{

    public Universidad obtenerUniversidad(int idUniversidad){
        DaoUsuario daoUsuario = new DaoUsuario();
        Universidad universidad = new Universidad();
        String sql = "select * from universidad where iduniversidad = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idUniversidad);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    universidad.setIdUniversidad(rs.getInt("iduniversidad"));
                    universidad.setNombre(rs.getString("nombre"));
                    universidad.setLogoUrl(rs.getString("logo_url"));
                    universidad.setAdministrador(daoUsuario.obtenerAdministrador(rs.getInt("idadministrador")));
                    universidad.setFechaRegistro(rs.getDate("fecha_registro"));
                    universidad.setFechaEdicion(rs.getDate("fecha_edicion"));
                }
                else {
                    universidad = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return universidad;
    }
}
