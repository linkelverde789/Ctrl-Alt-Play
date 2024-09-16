<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <title>Actualizar Videojuego</title>
    <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/formularioVideojuego.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%List<Integer> ListID = (List<Integer>) request.getAttribute("ID");%>
    <% HashMap<Integer, String> studios = (HashMap<Integer, String>) request.getAttribute("studios"); %>
    <% HashMap<Integer, String> publisher = (HashMap<Integer, String>) request.getAttribute("publisher"); %>
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <a href="admin_index.jsp">Administrar</a>
    </nav>
    <h1>Actualizar Juego</h1>
    <form action="actualizarJuego_admin" method="post">
        <div>
            <label for="id">Elige el ID del juego a modificar:</label>
            <select name="id" id="id" required>
                <%
                    for (Integer id : ListID) {
                %>
                <option value="<%=id%>"><%=id%>
                </option>

                <%
                    }
                %>
            </select>
        </div>
        <div>
            <label for="titulo">Introduce el nombre del juego:</label>
            <input type="text" maxlength="100" id="titulo" name="titulo">
        </div>

        <div>
            <label for="descripcion">Introduce una descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="3"></textarea>
        </div>
        <div>
            <label for="estudio">Introduce el estudio:</label>
            <select id="estudio" name="estudio">
                <% for (Map.Entry<Integer, String> entry : studios.entrySet()) {
                %>
                <option value="<%= entry.getKey() %>">
                    <%= entry.getValue() %>
                </option>
                <% } %>
            </select>
        </div>

        <div>
            <label for="distribuidor">Introduce el publicador:</label>
            <select id="distribuidor" name="distribuidor">
                <% for (Map.Entry<Integer, String> entry : publisher.entrySet()) {
                %>
                <option value="<%= entry.getKey() %>">
                    <%= entry.getValue() %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="boton">
            <button type="submit" class="btn btn-primary">Actualizar Juego</button>
        </div>
    </form>
</div>
</body>
</html>