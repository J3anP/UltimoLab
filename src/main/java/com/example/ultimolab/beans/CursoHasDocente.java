package com.example.ultimolab.beans;

public class CursoHasDocente {
    public Curso curso;
    public Usuario docente;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getDocente() {
        return docente;
    }

    public void setDocente(Usuario docente) {
        this.docente = docente;
    }
}
