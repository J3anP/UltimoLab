package com.example.ultimolab.daos;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.beans.Facultad;
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
            sql = "delete from curso_has_docente where idcurso=?";
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

    public ArrayList<Curso> listaCursos(int idDecano){
        ArrayList<Curso> listaCursos = new ArrayList<>();
        DaoFacultad daoFacultad =  new DaoFacultad();
        String sql = "select * from curso c inner join facultad f on c.idfacultad = f.idfacultad inner join facultad_has_decano fhd on fhd.idfacultad = f.idfacultad where fhd.iddecano = ?";
        try(Connection conn=this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idDecano);
            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    Facultad facultad = new Facultad();

                    curso.setIdCurso(rs.getInt("idcurso"));
                    curso.setCodigo(rs.getString("codigo"));
                    curso.setNombre(rs.getString("nombre"));
                    facultad.setIdFacultad(rs.getInt("idfacultad"));
                    facultad.setNombre(daoFacultad.obtenerFacultad(rs.getInt("idfacultad")).getNombre());
                    curso.setFacultad(facultad);
                    curso.setFechaRegistro(rs.getDate("fecha_registro"));
                    curso.setFechaEdicion(rs.getDate("fecha_edicion"));

                    listaCursos.add(curso);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCursos;
    }
    public void registrarCurso(String codigo, String nombre,int idDocente, int idDecano){
        String sql = "";
        DaoFacultadHasDecano daoFacultadHasDecano = new DaoFacultadHasDecano();
        sql = "insert into curso(codigo,nombre,idfacultad,fecha_registro,fecha_edicion) value(?,?,?,now(),now())";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,codigo);
            pstmt.setString(2,nombre);
            pstmt.setInt(3,daoFacultadHasDecano.obtenerFacultadPorDecano(idDecano).getIdFacultad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DaoCurso daoCurso = new DaoCurso();
        int idCursoNuevo = daoCurso.obtenerIdCursoPorNombre(nombre);//El nombre del curso es único

        sql = "insert into curso_has_docente(idcurso,iddocente) value(?,?)";
        try(Connection conn=getConnection(); PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setInt(1,idCursoNuevo);
            pstmt.setInt(2,idDocente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
    public String obtenerCursoDocente(int idDocente){
        String sql = "select * from curso c inner join curso_has_docente chd on c.idcurso = chd.idcurso where chd.iddocente= ?";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,idDocente);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getString("nombre");
                }else{
                    return "-----";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
