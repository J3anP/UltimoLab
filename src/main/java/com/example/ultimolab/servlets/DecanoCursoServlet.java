package com.example.ultimolab.servlets;

import com.example.ultimolab.beans.CursoHasDocente;
import com.example.ultimolab.beans.Usuario;
import com.example.ultimolab.beans.Curso;
import com.example.ultimolab.daos.DaoCurso;
import com.example.ultimolab.daos.DaoCursoHasDocente;
import com.example.ultimolab.daos.DaoSemestre;
import com.example.ultimolab.daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DecanoServlet", value = "/decanoCurso")
public class DecanoCursoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");


        ArrayList<Curso> listaCursos = new ArrayList<>();
        DaoCurso daoCurso = new DaoCurso();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoSemestre daoSemestre = new DaoSemestre();
        String idCurso;
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        switch (action){
            case "lista":
                if(session.getAttribute("usuario") != null){
                    //una navbar de cursos que tiene
                    listaCursos = daoCurso.listaCursos(usuario.getIdUsuario());
                    request.setAttribute("listaCursos",listaCursos);
                    request.getRequestDispatcher("/VistaDecano/listaCursos.jsp").forward(request,response);
                }else{
                    session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                    request.getRequestDispatcher("VistaDocente/noSession.jsp").forward(request, response);
                    //La vista noSession es comun para todos
                }
                break;
            case "formCrear":
                request.setAttribute("docentesSinCurso",daoUsuario.docentesSinCurso());
                request.getRequestDispatcher("VistaDecano/formRegistrarCursos.jsp").forward(request, response);
                break;
            case "editar":
                idCurso = request.getParameter("idcurso") == null? "lista" : request.getParameter("idcurso");
                Curso curso =  daoCurso.obtenerCurso(Integer.parseInt(idCurso));
                request.setAttribute("curso",curso);
                request.getRequestDispatcher("VistaDecano/formEditCursos.jsp").forward(request, response);
                break;
            case "borrar":
                //falta poner que en el caso de curso_has_docente se borre
                idCurso = request.getParameter("idcurso") == null? "lista" : request.getParameter("idcurso");
                String semestre = request.getParameter("semestre");
                int idSemestre = daoSemestre.obtenerIdSemestrePorNombre(semestre);
                daoCurso.eliminarCurso(Integer.parseInt(idCurso),idSemestre);
                ArrayList<Curso> listaCursosN = daoCurso.listaCursos(usuario.getIdUsuario());//nueva lista de cursos en base al iddecano
                request.setAttribute("listaCursos", listaCursosN);
                request.getRequestDispatcher("VistaDecano/listaCursos.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");


        ArrayList<CursoHasDocente> listaCursos = new ArrayList<>();
        DaoCurso daoCurso = new DaoCurso();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoSemestre daoSemestre = new DaoSemestre();
        String idCurso;
        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        switch (action){
            case "lista":
                response.sendRedirect("login?action=default");
                break;
            case "formCrear":
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                int idDocente = Integer.parseInt(request.getParameter("iddocente"));

                daoCurso.registrarCurso(codigo,nombre,idDocente,usuario.getIdUsuario());
                session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                response.sendRedirect("decanoCurso?action=lista");
                break;
            case "editar":
                //Me quede aquí
                idCurso = request.getParameter("idcurso") == null? "lista" : request.getParameter("idcurso");
                int cursoId = Integer.parseInt(idCurso);
                String nuevoNombre = request.getParameter("nombre");
                daoCurso.editarCurso(cursoId,nuevoNombre);

                session.setAttribute("usuario",daoUsuario.obtenerUsuario(usuario.getIdUsuario()));
                response.sendRedirect("decanoCurso?action=lista");
                break;
        }
    }
}
