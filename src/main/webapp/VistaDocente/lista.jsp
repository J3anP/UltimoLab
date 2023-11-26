<%--
  Created by IntelliJ IDEA.
  User: jeanp
  Date: 23/11/2023
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.ultimolab.beans.CursoHasDocente" %>
<%@ page import="com.example.ultimolab.beans.Evaluaciones" %>
<%@ page import="com.example.ultimolab.beans.Usuario" %>
<%@ page import="com.example.ultimolab.beans.Curso" %>
<%@ page import="com.example.ultimolab.daos.DaoSemestre" %>
<%@ page import="com.example.ultimolab.daos.DaoEvaluaciones" %>
<%@ page import="com.example.ultimolab.beans.Semestre" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %>

<!jsp:useBean type="java.util.ArrayList<!com.example.ultimolab.beans.CursoHasDocente>" scope="request" id="lista"/>

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
<!% Evaluaciones evaluacion = (Evaluaciones) session.getAttribute("evaluacion"); %>
<% ArrayList<Evaluaciones> listaEvaluaciones = (ArrayList<Evaluaciones>) request.getAttribute("listaEvaluaciones"); %>
<%int semestreSelectId= request.getAttribute("semestreFil")==null?0:(int) request.getAttribute("semestreFil");%>
<%ArrayList<Semestre> listaSemestres = new ArrayList<>();
    HashSet<Integer> idsSemestres = new HashSet<>();%>
<%for(Evaluaciones eval : listaEvaluaciones){
    idsSemestres.add(eval.getSemestre().getIdSemestre());
}
    for(int id : idsSemestres){
        listaSemestres.add(new DaoSemestre().obtenerSemestre(id));
    }%>

<html lang="es">
<head>
    <title>Evaluaciones</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #141e30;
        }
        .table {
            background-color: #17202A;
            color: white;
        }
        select.custom-select {
            background-color: #17202A;
            color: white;
            border-color: #17202A;
        }
        select.custom-select option {
            background-color: #17202A;
            color: white;
        }
    </style>
</head>
<body>

<div class="container mt-4">
    <div class="row">
        <div class="col">
            <h1 class="text-center text-white display-4 mb-4 font-weight-bold">PUCP</h1>
            <div class="d-flex justify-content-between mb-3">
                <form class="mr-3" method="get" action="<%=request.getContextPath()%>/docente">
                    <div class="form-group">
                        <input type="hidden" name="action" value="filtroSemestre">
                        <label for="idsemestre" class = "mb-0">Semestres:</label>
                        <select class="custom-select" id="idsemestre" name ="idsemestre" required>
                            <option>All</option>
                            <%for(Semestre sem : listaSemestres){%>
                            <option value="<%=sem.getIdSemestre()%>" <%if(semestreSelectId==sem.getIdSemestre()){%>selected<%}%>><%=sem.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Filtrar</button>
                </form>
                <div class ="mr-3">
                    <button type="button" style="padding-top: 2px" class="btn btn-lg war-btn"><a href="docente?action=formCrear" class="text-white">Registrar Evaluación</a></button>
                </div>
                <div class="mr-3">
                    <a href="logout" class="btn btn-outline-light">Logout</a>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th scope = "col">Estudiantes</th>
                    <th scope = "col">Codigos</th>
                    <th scope = "col">Correos</th>
                    <th scope = "col">Notas</th>
                    <th scope = "col">Curso</th>
                    <th scope = "col">Semestre</th>
                    <th scope = "col">Fecha Registro</th>
                    <th scope = "col">Fecha Edicion</th>
                    <th scope = "col">Editar</th>
                    <th scope = "col">Eliminar</th>

                </tr>
                </thead>
                <tbody>

                <%if(!(listaEvaluaciones.isEmpty())){%>
                <%int i=1; %>
                <% for (Evaluaciones eval : listaEvaluaciones) {%>
                <tr>
                    <td><%=i%></td>
                    <td><%=eval.getNombreEstudiante()%></td>
                    <td><%=eval.getCodigoEstudiante()%></td>
                    <td><%=eval.getCorreoEstudiante()%></td>
                    <td><%=eval.getNota()%></td>
                    <td><%=eval.getCurso().getNombre()%></td>
                    <td><%=eval.getSemestre().getNombre()%></td>
                    <td><%=eval.getFechaRegistro()%></td>
                    <td><%=eval.getFechaEdicion()%></td>
                    <td>
                        <div>
                            <button type="button" style="padding-top: 2px" class="btn war-btn">
                                <a href="docente?action=editar&idevaluacion=<%=eval.getIdEvaluaciones()%>" class="text-white">
                                    Editar
                                </a>
                            </button>
                        </div>
                    </td>
                    <td>
                        <div>
                            <button type="button" style="padding-top: 2px" class="btn war-btn">
                                <a href="docente?action=borrar&idevaluacion=<%=eval.getIdEvaluaciones()%>" class="text-white">
                                    Borrar
                                </a>
                            </button>
                        </div>
                    </td>

                </tr>
                <%i++;%>
                <%}%>

                <%} else {%>
                <tr>
                    <td colspan="11">
                        No hay evaluaciones registradas
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
<% } %>