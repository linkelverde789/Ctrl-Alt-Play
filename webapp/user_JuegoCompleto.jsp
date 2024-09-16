<%@ page import="proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto.VideogameEntity" %>
<%@ page import="java.util.Map" %>
<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <%
        VideogameEntity videogame = (VideogameEntity) request.getAttribute("juegoCompleto");
    %>
    <meta charset="UTF-8">
    <% UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario"); %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="estilos/normalize.css">
    <link rel="stylesheet" type="text/css" href="estilos/juegoCompleto.css">
    <title><%= videogame.getTitulo() %>
    </title>
    <script>
        function handleRomClick(romId, romName, gameId) {
            window.location.href = 'descargarRom_user?id_rom=' + romId + '&id_juego=' + gameId;

            setTimeout(function () {
                var link = document.createElement('a');
                link.href = 'Miscelanea/roms/' + romName;
                link.download = romName;
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }, 1);
        }
    </script>


</head>
<body>
<div class="general">
<nav>
    <a href="user_menu.jsp">Menú</a>
    <a href="listarJuegos_user">Videojuegos</a>
    <a href="user_userDetails.jsp">Mis datos</a>
    <%
        if (usuarioSesion.getID_Rol() == 2) {%>
    <a href="admin_index.jsp">Administrar</a>
    <%}%>
</nav>

    <div>
        <h1><%= videogame.getTitulo() %>
        </h1>
        <p><strong>Descripci&oacute;n:</strong> <%= videogame.getDescripcion() %>
        </p>
    </div>

    <div>
        <h2>Detalles del Juego</h2>
        <p>
            <strong>Consolas:</strong> <%= videogame.getConsolas() == null || videogame.getConsolas().isEmpty() ? "Sin consolas" : videogame.getConsolas() %>
        </p>
        <p>
            <strong>Géneros:</strong> <%= videogame.getGeneros() == null || videogame.getGeneros().isEmpty() ? "Sin géneros" : videogame.getGeneros() %>
        </p>
        <p><strong>Distribuidor:</strong> <%= videogame.getPublisher() %>
        </p>
        <p><strong>Estudio:</strong> <%= videogame.getStudio() %>
        </p>
    </div>
    <div>
        <h2>Im&aacute;genes</h2>
        <article>
            <% for (String foto : videogame.getImagen()) { %>
            <img src="Miscelanea/fotos/<%= foto %>">
            <% } %>
        </article>
    </div>

    <div class="manolo">
        <div>
            <h2>Manuales</h2>
            <ul>
                <% for (String manual : videogame.getManuales()) { %>
                <li><a download="<%=manual%>" href="Miscelanea/manuales/<%= manual %>"><%= manual %>
                </a></li>
                <% } %>
            </ul>
        </div>
        <div>
            <h2>Gu&iacute;as</h2>
            <ul>
                <% for (String guia : videogame.getGuias()) { %>
                <li><a download="<%= guia %>" href="Miscelanea/guias/<%= guia %>"><%= guia %>
                </a></li>
                <% } %>
            </ul>
        </div>
    </div>

    <div>
        <h2>ROMs</h2>
        <ul>
            <% int gameId = videogame.getId(); %>
            <% for (Map.Entry<Integer, String> item : videogame.getRoms().entrySet()) {
                Integer key = item.getKey();
                String value = item.getValue();
            %>
            <li><a href="#" onclick="handleRomClick(<%= key %>, '<%= value %>', <%= gameId %>)"><%= value %>
            </a></li>
            <% } %>
        </ul>
    </div>


</div>
</body>
</html>
