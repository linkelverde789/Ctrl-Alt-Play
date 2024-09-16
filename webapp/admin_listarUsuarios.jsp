<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page import="java.util.List" %>
<html lang="es">
<head>
    <title>Listar Usuarios</title>
    <meta charset="UTF-8">
    <%List<UserEntity> lista = (List<UserEntity>) request.getAttribute("listaUsuarios");%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="estilos/normalize.css">
    <link rel="stylesheet" type="text/css" href="estilos/listarUsuarios.css">
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp" style="text-decoration: none">Menú</a>
        <a href="listarJuegos_user"  style="text-decoration: none">Videojuegos</a>
        <a href="user_userDetails.jsp"  style="text-decoration: none">Mis datos</a>
        <a href="admin_index.jsp"  style="text-decoration: none">Administrar</a>
    </nav>

    <div class="container">
        <h1 class="usuario-titulo">Resultados</h1>
        <div class="row">
            <%
                for (UserEntity item : lista) {
            %>
            <div class="col-md-6">
                <div class="usuario">
                    <p class="usuario-info">
                        <strong>ID:</strong> <%= item.getID() %><br>
                        <strong>Rol ID:</strong> <%= item.getID_Rol() %><br>
                        <strong>Nombre:</strong> <%= item.getNombre() %><br>
                        <strong>Usuario:</strong> <%= item.getUsuario() %><br>
                        <strong>Correo:</strong> <%= item.getCorreo() %><br>
                    </p>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
