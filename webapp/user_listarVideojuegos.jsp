<%@ page import="java.util.List" %>
<%@ page import="proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.VideogameEntity" %>
<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <%
        UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario");
    %>
    <link rel="stylesheet" type="text/css" href="estilos/normalize.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="estilos/listarVideojuego.css">

    <title>Mostrar Juegos</title>
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp" style="text-decoration: none">Menú</a>
        <a href="listarJuegos_user"  style="text-decoration: none">Videojuegos</a>
        <a href="user_userDetails.jsp"  style="text-decoration: none">Mis datos</a>
        <%
            if (usuarioSesion != null && usuarioSesion.getID_Rol() == 2) {
        %>
        <a href="admin_index.jsp"  style="text-decoration: none">Administrar</a>
        <%
            }
        %>
    </nav>

    <div class="row justify-content-center">
        <form class="d-flex buscador" action="listarJuegos_user" method="post">
            <div class="col-lg-12">
                <input type="text" class="form-control" name="busqueda" id="busqueda" placeholder="Escribe aquí...">
            </div>
            <div class="input-group-append">
                <button type="submit" class="btn btn-dark">Buscar</button>
            </div>
        </form>
    </div>

    <div class="container">
        <h1 class="videojuego-titulo">Resultados</h1>
        <div class="row">
            <%
                List<VideogameEntity> lista = (List<VideogameEntity>) request.getAttribute("resultado");
                if (lista != null && !lista.isEmpty()) {
                    for (VideogameEntity item : lista) {
            %>
            <div class="col-md-6">
                <div class="videojuego">
                    <p class="videojuego-info">
                        <strong>ID:</strong> <%= item.getID() %><br>
                        <strong>Título:</strong> <%= item.getTitulo() %><br>
                        <strong>Consolas:</strong> <%= item.getConsolas()==null||item.getConsolas().isEmpty() ? "Sin consolas" : item.getConsolas() %>
                        <br>
                        <strong>Géneros:</strong> <%= item.getGeneros()==null||item.getGeneros().isEmpty() ? "Sin géneros" : item.getGeneros() %>
                        <br>
                        <strong>Estudio:</strong> <%= item.getEstudio() %><br>
                        <strong>Distribuidor:</strong> <%= item.getDistribuidor() %><br>
                    </p>
                    <form action="juegoCompleto_user" method="get">
                        <input type="hidden" name="id" value="<%= item.getID() %>">
                        <button class="btn btn-secondary" type="submit">Ver toda la información</button>
                    </form>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <div>
                <p>No se encontraron resultados.</p>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
