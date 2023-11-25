package com.example.ultimolab.servlets;

import com.example.ultimolab.beans.Usuario;
import com.example.ultimolab.daos.DaoUsuario;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.daos.DaoCursoHasDocente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null? "default" : request.getParameter("action");
        switch(action){
            case "login":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null? "default" : request.getParameter("action");

        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        DaoUsuario daoUsuario = new DaoUsuario();
        switch (action){
            case "default":
                break;
            case "login":
                String correoInput = request.getParameter("correo");
                String contrasenaInput = request.getParameter("contrasena");

                if (daoUsuario.login(correoInput, contrasenaInput)){

                    Usuario usuario = daoUsuario.obtenerUsuarioCorreo(correoInput);
                    HttpSession session = request.getSession();

                    if(usuario.getRol().getNombre().equals("docente")){
                        //ArrayList<Curso> cursos = daoCursoHasDocente.obtenerCursosPorDocente(usuario.getIdUsuario());
                        session.setAttribute("usuario", usuario);
                        session.setMaxInactiveInterval(1800);
                        response.sendRedirect("/UltimoLab/docente");
                        daoUsuario.actualizarIngreso(usuario.getIdUsuario());
                    }else if(usuario.getRol().getNombre().equals("decano")){
                        session.setAttribute("usuario", usuario);
                        session.setMaxInactiveInterval(1800);
                        response.sendRedirect("/UltimoLab/decano");
                        daoUsuario.actualizarIngreso(usuario.getIdUsuario());
                    }else{
                        //Dado que solo piden el flujo para Docente y Decano por motivos de prueba los demás los considero no presentes
                        response.sendRedirect("login?action=login");
                    }
                }else{
                    response.sendRedirect("login?action=login");
                }
                break;


        }
    }
}
