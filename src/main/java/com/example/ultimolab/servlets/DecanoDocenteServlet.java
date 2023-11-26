package com.example.ultimolab.servlets;

import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.beans.Usuario;
import com.example.ultimolab.daos.DaoCursoHasDocente;
import com.example.ultimolab.daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

@WebServlet(name = "DecanoDocenteServlet", value = "/decanoDocente")
public class DecanoDocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        ArrayList<Usuario> listaDocentes = new ArrayList<>();
        DaoUsuario daoUsuario = new DaoUsuario();
        String idDocente;
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        switch (action){
            case "lista":
                if(session.getAttribute("usuario") != null){
                    //una navbar de cursos que tiene
                    listaDocentes = daoUsuario.listaDocenteDeDecano(usuario.getIdUsuario());//lista Docentes en base al id del decano
                    request.setAttribute("listaDocentes",listaDocentes);
                    request.getRequestDispatcher("/VistaDecano/listaDocentes.jsp").forward(request,response);
                }else{
                    session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                    request.getRequestDispatcher("VistaDocente/noSession.jsp").forward(request, response);

                }
                break;
            case "formCrear":
                request.getRequestDispatcher("VistaDecano/formRegistrarDocente.jsp").forward(request, response);
                break;
            case "editar":
                idDocente = request.getParameter("iddocente") == null? "lista" : request.getParameter("iddocente");
                Usuario docente = daoUsuario.obtenerUsuario(Integer.parseInt(idDocente));
                request.setAttribute("docente",docente);
                request.getRequestDispatcher("VistaDecano/formEditDocente.jsp").forward(request, response);
                break;
            case "borrar":
                idDocente = request.getParameter("iddocente") == null? "lista" : request.getParameter("iddocente");
                daoUsuario.decanoEliminaDocente(Integer.parseInt(idDocente));
                ArrayList<Usuario> listaDocentesN = daoUsuario.listaDocenteDeDecano(usuario.getIdUsuario());
                request.setAttribute("listaDocentes", listaDocentesN);
                request.getRequestDispatcher("VistaDecano/listaDocentes.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        ArrayList<CursoHasDocente> listaDocentes = new ArrayList<>();
        DaoUsuario daoUsuario = new DaoUsuario();
        String idDocente;
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        switch (action){
            case "lista":
                response.sendRedirect("login?action=default");
                break;
            case "formCrear":
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");
                daoUsuario.decanoRegistraDocente(nombre,correo,contrasena);
                session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                response.sendRedirect("decanoDocente?action=lista");
                break;
            case "editar":
                //Me quede aquí
                idDocente = request.getParameter("iddocente") == null? "lista" : request.getParameter("iddocente");
                int docenteId = Integer.parseInt(idDocente);
                String nuevoNombre = request.getParameter("nombre");

                daoUsuario.decanoEditaDocente(nuevoNombre,docenteId);
                session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                response.sendRedirect("decanoDocente?action=lista");
                break;
        }
    }
}
