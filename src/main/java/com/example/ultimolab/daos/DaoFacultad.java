package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Facultad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoFacultad extends DaoBase {

    public Facultad obtenerFacultad(int idFacultad){

        Facultad facultad = new Facultad();
        DaoUniversidad daoUniversidad = new DaoUniversidad();

        String sql = "select * from facultad where idfacultad = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idFacultad);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    facultad.setIdFacultad(rs.getInt("idfacultad"));
                    facultad.setNombre(rs.getString("nombre"));
                    facultad.setUniversidad(daoUniversidad.obtenerUniversidad(rs.getInt("iduniversidad")));
                    facultad.setFechaRegistro(rs.getDate("fecha_registro"));
                    facultad.setFechaEdicion(rs.getDate("fecha_edicion"));

                }
                else {
                    facultad = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facultad;

    }
}
