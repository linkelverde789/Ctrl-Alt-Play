<%@ page import="java.util.List" %>
<%@ page import="proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar juego</title>
    <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/formularioVideojuego.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%List<VideogameEntity> list = (List<VideogameEntity>) request.getAttribute("list");%>
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
    <form action="eliminarJuego_admin" method="post">
        <div>
            <label for="id">Elige el juego a eliminar:</label>
            <select name="id" id="id" required>
                <%
                    for (VideogameEntity item : list) {
                %>
                <option value="<%=item.getID()%>"><%=item.getTitle()%>
                </option>

                <%
                    }
                %>
            </select>
        </div>
        <div class="boton">
            <button type="submit" class="btn btn-primary">Actualizar Juego</button>
        </div>
    </form>
</div>
</body>
</html>