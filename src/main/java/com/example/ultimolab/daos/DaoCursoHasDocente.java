package com.example.ultimolab.daos;
import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.Evaluaciones;
import com.example.ultimolab.beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCursoHasDocente extends DaoBase{

    public ArrayList<Curso> obtenerCursosPorDocente(int idDocente){
        ArrayList<Curso> cursos = new ArrayList<>();
        String sql = "select idcurso from curso_has_docente where iddocente = ?";
        try(Connection conn=this.getConnection(); PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setInt(1,idDocente);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Curso curso = new Curso();
                    curso.setIdCurso(rs.getInt(1));
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cursos;
    }

}
