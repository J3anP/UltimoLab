<%--
  Created by IntelliJ IDEA.
  User: jeanp
  Date: 23/11/2023
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cursos enseñando</title>
    <style>
        body {
            margin: 0;
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5;
            color: #fff;
            text-align: left;
            background-color: #141b2d;
            height:100vh;
        }
        a i {
            color: #9a9a9a;
        }
        a i:hover {
            color: #fff;
        }
        .badge-success {
            background-color: #00d68f;
        }
        .table {
            border-collapse: separate;
            border-spacing: 0 15px;
            color: rgba(255,255,255,0.7);
        }
        thead tr th {
            border-bottom:0 !important;
            background-color: #192038;
            font-weight: 500;
        }
        .table tr {
            box-shadow: 0 1px 20px 0 rgb(0 0 0 / 10%);
            background-color: #1f2940;
            border-radius:20px;
        }
        .table td,
        .table th {
            vertical-align: middle;
        }
        tr td:nth-child(n+5),tr th:nth-child(n+5) {
            border-radius: 0 .625rem .625rem 0;
        }
        tr td:nth-child(1),tr th:nth-child(1){border-radius: .625rem 0 0 .625rem;}
        .table td,
        .table th,
        .card {
            border: 0;
        }
        .w-48 {
            width: 3rem;
        }
        .-ml-5 {
            margin-left: -1.25rem;
        }
        .h-48 {
            height: 3rem;
        }
        .thump img {
            height: 100%;
            -o-object-fit: cover;
            object-fit: cover;
            width: 100%;
        }

    </style>
</head>
<body>
<link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
<div class="h-100 row align-items-center">
    <div class="container">
        <div class="col-lg-12 col-12">
            <div class="card bg-transparent">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Brand</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="w-48 h-48 thump">
                                        <img class="rounded-circle img-fluid" src="https://images.unsplash.com/photo-1613995369866-080828a12f60?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2134&q=80" alt="unsplash image">
                                    </div>
                                    <div class="ml-3">
                                        <div class="">Appple</div>
                                        <div class="text-muted">mail@rgmail.com</div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                Technology
                            </td>
                            <td class="">
                                200.00$
                            </td>
                            <td class="">
                                <span class="badge badge-pill badge-success font-weight-              ormal">Available</span>
                            </td>
                            <td>
                                <a href="#" class="text-secondary mr-2">
                                    <i class="material-icons-outlined">visibility</i>
                                </a>
                                <a href="#" class="text-secondary mx-2">
                                    <i class="material-icons-outlined">edit</i>
                                </a>
                                <a href="#" class="text-danger ml-2">
                                    <i class="material-icons-round">delete_outline</i>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="w-48 h-48 thump">
                                        <img class="rounded-circle img-fluid" src="https://images.unsplash.com/photo-1606293459209-958d5c67c84b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2089&q=80" alt="unsplash image">
                                    </div>
                                    <div class="ml-3">
                                        <div class="">Realme</div>
                                        <div class="text-muted">mail@rgmail.com</div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                Technology
                            </td>
                            <td class="">
                                200.00$
                            </td>
                            <td class="">
                                <span class="badge badge-pill badge-danger font-weight-              ormal">No Stock</span>
                            </td>
                            <td>
                                <a href="#" class="text-secondary mr-2">
                                    <i class="material-icons-outlined">visibility</i>
                                </a>
                                <a href="#" class="text-secondary mx-2">
                                    <i class="material-icons-outlined">edit</i>
                                </a>
                                <a href="#" class="text-danger ml-2">
                                    <i class="material-icons-round">delete_outline</i>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="w-48 h-48 thump">
                                        <img class="rounded-circle img-fluid" src="https://images.unsplash.com/photo-1562638863-ef1c7ffea8ad?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2134&q=80" alt="unsplash image">
                                    </div>
                                    <div class="ml-3">
                                        <div class="">Samsung</div>
                                        <div class="text-muted">mail@rgmail.com</div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                Technology
                            </td>
                            <td class="">
                                200.00$
                            </td>
                            <td class="">
                                <span class="badge badge-pill badge-warning">Start Sale</span>
                            </td>
                            <td>
                                <a href="#" class="text-secondary mr-2">
                                    <i class="material-icons-outlined">visibility</i>
                                </a>
                                <a href="#" class="text-secondary mx-2">
                                    <i class="material-icons-outlined">edit</i>
                                </a>
                                <a href="#" class="text-danger ml-2">
                                    <i class="material-icons-round">delete_outline</i>
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
