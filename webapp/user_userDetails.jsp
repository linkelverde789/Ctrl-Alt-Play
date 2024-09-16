<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <% UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario"); %>
    <title>Mis Datos</title>
        <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/usuarios.css">
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp" style="text-decoration: none">Menú</a>
        <a href="listarJuegos_user"  style="text-decoration: none">usuarios</a>
        <a href="user_userDetails.jsp"  style="text-decoration: none">Mis datos</a>
        <%
            if(usuarioSesion.getID_Rol()==2){%>
        <a href="admin_index.jsp"  style="text-decoration: none">Administrar</a>
        <%}%>
    </nav>
    <h1>Mis Datos</h1>
    <div>
        <p><strong>Nombre:</strong>
            <%= usuarioSesion.getNombre() %>
        </p>
        <p><strong>Email:</strong>
            <%= usuarioSesion.getCorreo() %>
        </p>
        <p><strong>Usuario:</strong>
            <%= usuarioSesion.getUsuario() %>
        </p>
        <div class="botones">
            <form action="user_actualizarUsuario.jsp" method="post">
                <button class="btn" type="submit">Actualizar Datos</button>
            </form>
            <form action="eliminarCuenta_user" method="post">
                <button class="btn" type="submit">Borrar Cuenta</button>
            </form>
            <form action="logout" method="post">
                <button class="btn" type="submit">Cerrar sesión</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>