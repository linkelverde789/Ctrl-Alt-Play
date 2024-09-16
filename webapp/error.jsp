<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Error</title>
        <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/error.css">
</head>
<body>
<div class="error">
    <h1>Ha ocurrido el siguiente error:</h1>
    <br/>
    <%
        String errorMessage = request.getParameter("error");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
    <h2>Error: <%= errorMessage %>
    </h2>
    <%
    } else {
    %>
    <h2>Vaya, no ha habido errores. No sé qué ha pasado, pero tú seguro que tienes la culpa.</h2>
    <%
        }
    %>
    <br/>
</div>
<div class="enlace">
    <a href="user_menu.jsp">Volver al inicio</a>
</div>
<div class="imagen">
    <img src="fotos/error.jpg" style="width: 300px;height: auto">
</div>

</body>
</html>
