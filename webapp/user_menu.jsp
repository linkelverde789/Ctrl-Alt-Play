<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <%UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario");%>
    <meta charset="UTF-8">
        <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/menu.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido</title>
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <%if (usuarioSesion.getID_Rol() == 2) {%>
        <a href="admin_index.jsp">Administrar</a>
        <%}%>
    </nav>
    <header>
        <h1>Bienvenido, <%=usuarioSesion.getUsuario()%>
        </h1>
    </header>
    <main>
        <section>
            <h2>Mis Datos</h2>
            <p>En esta sección puedes ver y modificar tu información personal, como nombre, dirección, y más.</p>
        </section>
        <section>
            <h2>Menú</h2>
            <p>Explora las diferentes opciones disponibles en la página, como acceder a tus datos, ver la lista de
                videojuegos, entre otros.</p>
        </section>
        <section>
            <h2>Videojuegos</h2>
            <p>Descubre una amplia variedad de videojuegos disponibles. Explora la lista para encontrar tu próximo
                juego favorito.</p>
        </section>
        <%if (usuarioSesion.getID_Rol() == 2) {%>
        <section>
            <h2>Administrador</h2>
            <p>Accede a funciones avanzadas y privilegios exclusivos reservados para administradores, como gestionar
                usuarios, configurar la página, entre otros.</p>
        </section>
        <%}%>

    </main>
    <footer>
        <img src="fotos/website%20logo.png" alt="Logo del sitio web">
    </footer>
</div>
</body>
</html>
