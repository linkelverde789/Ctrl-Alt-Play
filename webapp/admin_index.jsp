<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <% UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario"); %>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilos/normalize.css">
    <link rel="stylesheet" href="estilos/admin.css">
    <title>Administración</title>
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <a href="admin_index.jsp">Administrar</a>
    </nav>
    <h1>Administración</h1>
    <div class="contenedor">
        <div class="columna izquierda">
            <h2>Videojuegos</h2>
            <div class="botones">

                <h3>Crear juego</h3>
                <form action="gestorCrearJuego_admin" method="post">
                    <button class="btn" type="submit">Crear juego</button>
                </form>

                <h3>Actualizar juego</h3>
                <form action="gestorActualizarJuego_admin" method="post">
                    <button class="btn" type="submit">Actualizar juego</button>
                </form>

                <h3>Eliminar juego</h3>
                <form action="gestorEliminarJuego_admin" method="post">
                    <button class="btn" type="submit">Eliminar juego</button>
                </form>

                <h3>Añadir Rom</h3>
                <form action="gestorInsertarRom_admin" method="post">
                    <button class="btn" type="submit">Añadir Rom</button>
                </form>

            </div>
        </div>
        <div class="columna derecha">
            <h2>Usuarios</h2>
            <div class="botones">

                <h3>Insertar Usuario</h3>
                <form action="admin_crearUsuario.jsp" method="post">
                    <button class="btn" type="submit">Insertar Usuario</button>
                </form>

                <h3>Actualizar Usuario</h3>
                <form action="gestionarFormularioActualizar_admin" method="post">
                    <button class="btn" type="submit">Actualizar Usuario</button>
                </form>

                <h3>Eliminar Usuario</h3>
                <form action="gestionarFormularioEliminar_admin" method="post">
                    <button class="btn" type="submit">Eliminar Usuario</button>
                </form>

                <h3>Listar Usuarios</h3>
                <form action="listarUsuarios_admin" method="post">
                    <button class="btn" type="submit">Listar Usuarios</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
