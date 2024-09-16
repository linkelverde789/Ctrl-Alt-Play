<%@ page import="proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <title>Insertar Rom</title>
    <link rel="stylesheet" href="estilos/normalize.css">
    <link rel="stylesheet" href="estilos/formularioVideojuego.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%
        HashMap<Integer, String> consola = (HashMap<Integer, String>) request.getAttribute("consolas");
        List<VideogameEntity> juego = (List<VideogameEntity>) request.getAttribute("juegos");
    %>
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <a href="admin_index.jsp">Administrar</a>
    </nav>
    <h1>Insertar Rom</h1>
    <form action="insertarRom_admin" method="post" enctype="multipart/form-data">
        <div>
            <label for="juego">Elige el juego:</label>
            <select id="juego" name="juego" required>
                <% if (juego != null) {
                    for (VideogameEntity item : juego) { %>
                <option value="<%= item.getID() %>"><%= item.getTitle()%>
                </option>
                <% }
                } %>
            </select>
        </div>
        <div>
            <label for="consola">Introduce la consola:</label>
            <select id="consola" name="consola" required>
                <% if (consola != null) {
                    for (Map.Entry<Integer, String> entry : consola.entrySet()) { %>
                <option value="<%= entry.getKey() %>"><%= entry.getValue() %>
                </option>
                <% }
                } %>
            </select>
        </div>
        <div class="form-group">
            <label for="rom">Introduce la rom a subir:</label>
            <input type="file" class="form-control" id="rom" name="rom" required>
        </div>
        <div class="boton">
            <button type="submit" class="btn btn-primary">Añadir rom</button>
        </div>
    </form>
</div>
</body>
</html>
