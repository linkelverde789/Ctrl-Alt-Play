<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="es">
<head>
    <%List<Integer> ListID = (List<Integer>) request.getAttribute("listaID");
        Collections.sort(ListID);%>

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
        <a href="admin_index.jsp">Administrar</a>
    </nav>

    <h1>Actualizar datos</h1>
    <div>
        <form action="actualizarCuenta_admin" method="post">
            <div class="campo">
                <label for="ID">Elige el ID del usuario a modificar</label>
                <select name="ID" id="ID" required>
                    <%
                    if (ListID != null) {
                        for (Integer id : ListID) {
                    %>
                    <option value="<%=id%>"><%=id%></option>
                    <%
                        }
                    }
                    %>
                </select>
            </div>
            <div class="campo">
                <label for="rolID">Rol:</label>
                <select id="rolID" name="rolID" required>
                    <option value="1">Estandar</option>
                    <option value="2">Administrador</option>
                    <option value="3">En espera</option>
                    <option value="4">Baneado</option>
                </select>
            </div>
            <div class="campo">
                <label for="nombre">Nombre completo:</label>
                <input type="text" id="nombre" name="nombre" maxlength="100">
            </div>
            <div class="campo">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" maxlength="50">
            </div>
            <div class="campo">
                <label for="correo">Correo electrónico:</label>
                <input type="email" id="correo" name="correo" maxlength="100">
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
