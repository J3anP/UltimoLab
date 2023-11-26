<%--
  Created by IntelliJ IDEA.
  User: jeanp
  Date: 23/11/2023
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.ultimolab.beans.Usuario" %>
<%@ page import="com.example.ultimolab.beans.Curso" %>
<%@ page import="com.example.ultimolab.beans.CursoHasDocente" %>
<%@ page import="java.util.ArrayList" %>

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

<% Curso cursoEdit = (Curso) request.getAttribute("curso"); %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Curso</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #141e30;
            color: white;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }
        .form-container {
            background-color: #17202A;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(255, 255, 255, 0.3);
            max-width: 400px;
            width: 100%;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-control {
            background-color: transparent;
            border: 1px solid white;
            color: white;
        }
        .form-control:focus {
            background-color: transparent;
            color: white;
            border-color: #03e9f4;
            box-shadow: none;
        }
        .btn-primary {
            background-color: #03e9f4;
            border-color: #03e9f4;
        }
        .btn-primary:hover {
            background-color: #03a8f4;
            border-color: #03a8f4;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="form-container">
                <h2 class="text-center mb-4">Editar Curso</h2>
                <form action="decanoCurso?action=editar&idcurso=<%=cursoEdit.getIdCurso()%>" method="POST">
                    <div class="form-group">
                        <label for="nombre">Nombre Curso</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="<%=cursoEdit.getNombre()%>">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mt-4">Editar</button>
                    <a href="decanoCurso?action=lista" class="btn btn-secondary btn-block mt-3">Cancelar</a>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
<% } %>