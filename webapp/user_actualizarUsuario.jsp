<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <% UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario"); %>
    <title>Actualizar datos</title>
        <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/formularioUsuarios.css">
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <%
            if(usuarioSesion.getID_Rol()==2){%>
        <a href="admin_index.jsp">Administrar</a>
        <%}%>
    </nav>

    <h1>Actualizar mis datos</h1>
    <div>
        <form action="actualizarCuenta_user" method="post">
            <div class="campo">
                <label for="nombre">Nombre completo:</label>
                <input type="text" id="nombre" name="nombre" maxlength="100" placeholder="<%=usuarioSesion.getNombre()%>">
            </div>
            <div class="campo">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" maxlength="50" placeholder="<%=usuarioSesion.getUsuario()%>">
            </div>
            <div class="campo">
                <label for="correo">Correo electrónico:</label>
                <input type="email" id="correo" name="correo" maxlength="100" placeholder="<%=usuarioSesion.getCorreo()%>">
            </div>
            <div class="campo">
                <label for="contraseña">Contraseña:</label>
                <input type="password" id="contraseña" minlength="8" name="contraseña">
            </div>
            <div class="boton">
                <button type="submit">Actualizar datos</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
