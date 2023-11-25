package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.daos.DaoCursoHasDocente;
import com.example.ultimolab.beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DaoCurso extends DaoBase{

    public Curso obtenerCurso(int idCurso){
        Curso curso = new Curso();
        DaoFacultad daoFacultad = new DaoFacultad();
        String sql = "select * from curso where idcurso = ?";
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
    public ArrayList<Curso> listarCursosDocente(int idDocente){//Editar esto porque va en la vista de Decano
        //Además falta validar que solo esten los cursos que pertenecen a la facultad
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

    public void editarCurso(int idCurso, String nombre){
        String sql = "update curso set nombre = ?,fecha_edicion=now() where idcurso = ?";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setInt(2,idCurso);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarCurso(int idCurso, int idSemestre){
        String sql = "";

        DaoSemestre daoSemestre = new DaoSemestre();

        if(daoSemestre.obtenerSemestre(idSemestre).getHabilitado()){
            sql = "update curso_has_docente set iddocente = 0 where idcurso = ?";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1,idCurso);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sql = "delete from curso where idcurso = ?";
            try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
                pstmt.setInt(1,idCurso);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ArrayList<CursoHasDocente> listaCursos(int idFacultad){
        ArrayList<CursoHasDocente> listaCursos = new ArrayList<>();
        String sql = "select * from curso c left join curso_has_docente chd on chd.idcurso = c.idcurso where c.id_facultad = ?";
        try(Connection conn=this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CursoHasDocente curso = new CursoHasDocente();

                    curso.getCurso().setIdCurso(rs.getInt("idcurso"));
                    curso.getCurso().setCodigo(rs.getString("codigo"));
                    curso.getCurso().setNombre(rs.getString("nombre"));
                    curso.getCurso().setFechaRegistro(rs.getDate("fecha_registro"));
                    curso.getCurso().setFechaEdicion(rs.getDate("fecha_edicion"));
                    curso.getDocente().setIdUsuario(rs.getInt("iddocente"));
                    listaCursos.add(curso);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCursos;
    }

    public Integer obtenerIdCursoPorNombre(String nombre){
        Integer idCurso = 0;
        String sql = "select idcurso from curso where nombre = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nombre);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    idCurso = rs.getInt("idcurso");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idCurso;
    }


}
