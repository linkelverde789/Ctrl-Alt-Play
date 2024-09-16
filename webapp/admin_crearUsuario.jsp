<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creación de usuario</title>
    <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/formularioUsuarios.css">
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <a href="admin_index.jsp">Administrar</a>
    </nav>

    <h1>Crear usuario</h1>
    <div>
        <form action="crearUsuario_admin" method="post">
            <div class="campo">
                <label for="nombre">Nombre completo:</label>
                <input type="text" id="nombre" name="nombre" minlength="3" maxlength="100" required>
            </div>
            <div class="campo">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" minlength="3" maxlength="50" required>
            </div>
            <div class="campo">
                <label for="correo">Correo electrónico:</label>
                <input type="email" id="correo" name="correo" minlength="5" maxlength="100" required>
            </div>
            <div class="campo">
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" minlength="8" required>
            </div>
            <div class="campo">
                <label for="rol">Rol:</label>
                <select id="rol" name="rol" required>
                    <option value="1">Estandar</option>
                    <option value="2">Administrador</option>
                    <option value="3">En espera</option>
                    <option value="4">Baneado</option>
                </select>
            </div>
            <div class="boton">
                <button type="submit">Crear usuario</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
