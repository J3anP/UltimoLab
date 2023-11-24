package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Curso;
import java.sql.*;
public class DaoCurso extends DaoBase{

    public Curso obtenerCurso(int idCurso){
        Curso curso = new Curso();
        DaoFacultad daoFacultad = new DaoFacultad();
        String sql = "select * from where idcurso = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,idCurso);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    curso.setIdCurso(rs.getInt("idcurso"));
                    curso.setCodigo(rs.getString("codigo"));
                    curso.setNombre(rs.getString("nombre"));
                    curso.setFacultad(daoFacultad.obtenerFacultad(rs.getInt("idfacultad")));
                    curso.setFechaRegistro(rs.getDate("fecha_registro"));
                    curso.setFechaEdicion(rs.getDate("fecha_edicion"));

                }
                else {
                    curso = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return curso;
    }
}
