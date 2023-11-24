package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.daos.DaoCursoHasDocente;
import com.example.ultimolab.beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

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
    public ArrayList<Curso> listarCursosDocente(int idDocente){
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        ArrayList<Curso> cursos = daoCursoHasDocente.obtenerCursosPorDocente(idDocente);

        ArrayList<Curso> listaCursos = new ArrayList<>();
        String sql = "";
        for(Curso c: cursos){
            sql = "select idcurso,codigo,nombre,fecha_registro,fecha_edicion from curso where idcurso = ?";
            try(Connection conn=this.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1,c.getIdCurso());
                try(ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Curso curso = new Curso();
                        curso.setIdCurso(rs.getInt(1));
                        curso.setCodigo(rs.getString(2));
                        curso.setNombre(rs.getString(3));
                        curso.setFechaRegistro(rs.getDate(4));
                        curso.setFechaEdicion(rs.getDate(5));
                        listaCursos.add(curso);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listaCursos;

    }
}
