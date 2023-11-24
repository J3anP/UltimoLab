<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: sans-serif;
            background: linear-gradient(#141e30, #243b55);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-box {
            width: 90%;
            max-width: 400px;
            padding: 40px;
            background: rgba(0,0,0,.5);
            border-radius: 10px;
            color: #fff;
        }

        .login-box h2 {
            margin-bottom: 30px;
            text-align: center;
        }

        .login-box .user-box {
            position: relative;
            margin-bottom: 30px;
        }

        .login-box .user-box input {
            width: 100%;
            padding: 10px 0;
            font-size: 16px;
            color: #fff;
            border: none;
            border-bottom: 1px solid #fff;
            outline: none;
            background: transparent;
        }

        .login-box .user-box label {
            position: absolute;
            top:0;
            left: 0;
            padding: 10px 0;
            font-size: 16px;
            color: #fff;
            pointer-events: none;
            transition: .5s;
        }

        .login-box .user-box input:focus ~ label,
        .login-box .user-box input:valid ~ label {
            top: -20px;
            left: 0;
            color: #03e9f4;
            font-size: 12px;
        }

        .login-box form a {
            display: block;
            width: 100%;
            padding: 10px 20px;
            margin-top: 40px;
            color: #03e9f4;
            font-size: 16px;
            text-decoration: none;
            text-transform: uppercase;
            text-align: center;
            border: 1px solid #03e9f4;
            border-radius: 5px;
            transition: all 0.3s ease;
        }

        .login-box form a:hover {
            background-color: #03e9f4;
            color: #fff;
            border-color: #03e9f4;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h2>Bienvenido</h2>
    <form>
        <div class="user-box">
            <input type="text" name="" required="">
            <label>Nombre de usuario</label>
        </div>
        <div class="user-box">
            <input type="password" name="" required="">
            <label>Contraseña</label>
        </div>
        <a href="#">Ingresar</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


