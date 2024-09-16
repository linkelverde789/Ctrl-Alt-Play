<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <%List<UserEntity> lista = (List<UserEntity>) request.getAttribute("lista");%>

    <title>Eliminar usuario</title>
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

    <h1>Eliminar usuario</h1>
    <div>
        <form action="eliminarUsuario_admin" method="post">
            <div class="campo">
                <label for="ID">Elige el usuario a eliminar:</label>
                <select name="ID" id="ID" required>
                    <%
                        for (UserEntity item : lista) {
                    %>
                    <option value="<%=item.getID()%>"><%=item.getUsuario()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>
            <div class="boton">
                <button type="submit">Actualizar datos</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
