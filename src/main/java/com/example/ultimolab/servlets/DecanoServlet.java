package com.example.ultimolab.servlets;

import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.Usuario;
import com.example.ultimolab.daos.DaoCurso;
import com.example.ultimolab.daos.DaoCursoHasDocente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DecanoServlet", value = "/decano")
public class DecanoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "default" : request.getParameter("action");

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        switch (action){
            case "default":
                if(session.getAttribute("usuario") != null){
                    //una navbar de cursos que tiene
                    request.getRequestDispatcher("/VistaDecano/listaCursos.jsp").forward(request,response);
                }else{

                }
                break;
            case "editar":
                break;

            case "borrar":
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
