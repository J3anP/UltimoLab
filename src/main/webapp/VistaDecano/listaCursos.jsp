<%--
  Created by IntelliJ IDEA.
  User: jeanp
  Date: 23/11/2023
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.ultimolab.beans.Usuario" %>
<%@ page import="com.example.ultimolab.beans.Curso" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ultimolab.beans.CursoHasDocente" %>
<%@ page import="com.example.ultimolab.daos.DaoCursoHasDocente" %>


<% if (session.getAttribute("usuario") == null){ %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sesión Finalizada</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #17202A;
            color: white;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .message {
            text-align: center;
            font-size: 42px;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 2px;
            border: 3px solid #fff;
            padding: 20px;
            border-radius: 10px;
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 0px 0px 15px rgba(255, 255, 255, 0.3);
            margin-bottom: 20px;
        }
        .register {
            text-align: center;
        }
        .register a {
            display: inline-block;
            color: white;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        .register a:hover {
            color: lightgray;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="message">
                <p>Sesión Finalizada</p>
            </div>
            <p class="register">
                La sesión ha finalizado<br>
                <a href="login">Regresar</a>
            </p>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<% } else { %>
<% Usuario usuario = (Usuario) session.getAttribute("usuario"); %>
<% Curso curso = (Curso) session.getAttribute("curso"); %>
<% ArrayList<Curso> listaCursos = (ArrayList<Curso>) request.getAttribute("listaCursos"); %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista Cursos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #141e30;
        }
        .table {
            background-color: #17202A;
            color: white;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">PUCP</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="decanoCurso?action=lista">Cursos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="decanoDocente?action=lista">Docentes</a>
            </li>
        </ul>
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle mr-2" type="button" id="opcionesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Opciones
            </button>
            <div class="dropdown-menu" aria-labelledby="opcionesDropdown">
                <a class="dropdown-item" href="#">Opción 1</a>
                <a class="dropdown-item" href="#">Opción 2</a>
                <a class="dropdown-item" href="#">Opción 3</a>
            </div>
        </div>
        <div>
            <button type="button" style="padding-top: 2px" class="btn btn-lg war-btn"><a href="decanoCurso?action=formCrear" class="text-white">Registrar Curso</a></button>
        </div>
        <a href="#" class="btn btn-outline-light">Logout</a>
    </div>
</nav>

<div class="container mt-4">
    <div class="row">
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Codigo</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Docente</th>
                    <th scope="col">Fecha registro</th>
                    <th scope="col">Fecha edición</th>
                    <th scope="col">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <%if(!(listaCursos.isEmpty())){%>
                <%int i=1; %>
                <% for (Curso cur:listaCursos) {%>
                <tr>
                    <td><%=i%></td>
                    <td><%=cur.getCodigo()%></td>
                    <td><%=cur.getNombre()%></td>
                    <td><%=new DaoCursoHasDocente().getNombreDocentePorCurso(cur.getIdCurso())%></td>
                    <td><%=cur.getFechaRegistro()%></td>
                    <td><%=cur.getFechaEdicion()%></td>
                    <td>
                        <a href="decanoCurso?action=editar&idcurso=<%=cur.getIdCurso()%>" class="text-secondary mr-2">
                            Editar
                        </a>
                        <a href="decanoCurso?action=borrar&idcurso=<%=cur.getIdCurso()%>" class="text-danger">
                            Borrar
                        </a>
                    </td>
                </tr>
                <%i++;%>
                <%}%>

                <%} else {%>
                <tr>
                    <td colspan="11">
                        No hay cursos registrados
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%}%>