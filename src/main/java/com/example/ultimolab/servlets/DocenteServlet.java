package com.example.ultimolab.servlets;

import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.beans.Evaluaciones;
import com.example.ultimolab.daos.*;
import com.example.ultimolab.beans.Curso;

import com.example.ultimolab.beans.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DocenteServlet", value = "/docente")
public class DocenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoEvaluaciones daoEvaluaciones = new DaoEvaluaciones();
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        DaoUsuario daoUsuario = new DaoUsuario();

        ArrayList<Evaluaciones>  listaEvaluaciones = new ArrayList<>();
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");//Recordar

        Evaluaciones evaluacion;
        int evaluacionId = 0;
        RequestDispatcher view;
        switch (action) {
            case "formCrear":
                view = request.getRequestDispatcher("VistaDocente/formNew.jsp");
                view.forward(request, response);
                break;
            case "lista":
                if (session.getAttribute("usuario") != null){
                    listaEvaluaciones= daoEvaluaciones.listarEvaluaciones(usuario.getIdUsuario());
                    request.setAttribute("listaEvaluaciones",listaEvaluaciones);
                    request.getRequestDispatcher("VistaDocente/lista.jsp").forward(request,response);
                }else{
                    session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                    request.getRequestDispatcher("VistaDocente/noSession.jsp").forward(request, response);
                }
                break;
            case "editar":
                String id = request.getParameter("idevaluacion") == null? "lista" : request.getParameter("idevaluacion");
                evaluacionId = Integer.parseInt(id);
                evaluacion = daoEvaluaciones.obtenerEvaluaciones(evaluacionId);
                request.setAttribute("evaluacion",evaluacion);
                request.getRequestDispatcher("VistaDocente/formEdit.jsp").forward(request, response);
                break;
            case "borrar":
                evaluacionId = Integer.parseInt(request.getParameter("idevaluacion"));
                daoEvaluaciones.eliminarEvaluacion(evaluacionId);
                listaEvaluaciones = daoEvaluaciones.listarEvaluaciones(usuario.getIdUsuario());
                request.setAttribute("listaEvaluaciones",listaEvaluaciones);
                request.getRequestDispatcher("VistaDocente/lista.jsp").forward(request,response);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoEvaluaciones daoEvaluaciones = new DaoEvaluaciones();
        DaoCursoHasDocente daoCursoHasDocente = new DaoCursoHasDocente();
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");//Recordar
        DaoSemestre daoSemestre = new DaoSemestre();
        DaoCurso daoCurso = new DaoCurso();
        DaoUsuario daoUsuario = new DaoUsuario();
        Evaluaciones evaluacion;
        int evaluacionId = 0;
        RequestDispatcher view;
        switch (action) {
            case "lista":
                response.sendRedirect("login?action=default");
                break;
            case "formCrear":
                //evaluacionId = Integer.parseInt(request.getParameter("evaluacion"));
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String codigo = request.getParameter("codigo");
                int nota = Integer.parseInt(request.getParameter("nota"));
                int semestreId = daoSemestre.obtenerIdSemestrePorNombre(request.getParameter("semestre"));
                int cursoId = daoCurso.obtenerIdCursoPorNombre(request.getParameter("curso"));
                daoEvaluaciones.registrarEvaluacion(nombre,codigo,correo,nota,cursoId,semestreId);
                session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));

                response.sendRedirect("docente?action=lista");
                break;
            case "editar":

                String id = request.getParameter("idevaluacion") == null? "lista" : request.getParameter("idevaluacion");
                evaluacionId = Integer.parseInt(id);
                String nuevoNombre = request.getParameter("nombre");
                String nuevoCorreo = request.getParameter("correo");
                String nuevoCodigo = request.getParameter("codigo");
                int nuevaNota = Integer.parseInt(request.getParameter("nota"));

                daoEvaluaciones.editarEvaluacion(evaluacionId,nuevoNombre,nuevoCodigo,nuevoCorreo,nuevaNota);
                session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                response.sendRedirect("docente?action=lista");
                break;
        }

    }
}
