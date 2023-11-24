package com.example.ultimolab.servlets;

import com.example.ultimolab.daos.DaoCurso;
import com.example.ultimolab.daos.DaoUsuario;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.daos.DaoCursoHasDocente;

import com.example.ultimolab.beans.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DocenteServlet", value = "/docente")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");
        DaoCurso daoCurso = new DaoCurso();
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        switch (action){
            case "default":
                if(session.getAttribute("usuario") != null){
                    //una navbar de cursos que tiene
                    ArrayList<Curso> listaCursos = daoCurso.listarCursosDocente(usuario.getIdUsuario());
                    request.setAttribute("listaCursos",listaCursos);


                    request.getRequestDispatcher("/VistaDocente/lista.jsp").forward(request,response);
                }else{

                }
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
