package com.example.ultimolab.daos;

import com.example.ultimolab.beans.Facultad;
import com.example.ultimolab.beans.FacultadHasDecano;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoFacultadHasDecano extends DaoBase{
    public Facultad obtenerFacultadPorDecano(int idDecano){
        Facultad facultad = new Facultad();
        DaoFacultad daoFacultad = new DaoFacultad();
        String sql = "select * from facultad_has_decano where iddecano = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idDecano);
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    facultad = daoFacultad.obtenerFacultad(rs.getInt("idfacultad"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facultad;
    }
}
