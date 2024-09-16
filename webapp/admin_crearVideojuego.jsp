<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="proyecto.sergio.demo.Repositorios.User.UserEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" href="estilos/normalize.css">

    <link rel="stylesheet" href="estilos/formularioVideojuego.css">
    <% UserEntity usuarioSesion = (UserEntity) request.getSession().getAttribute("usuario"); %>
    <% HashMap<Integer, String> genres = (HashMap<Integer, String>) request.getAttribute("generos"); %>
    <% HashMap<Integer, String> consoles = (HashMap<Integer, String>) request.getAttribute("consolas"); %>
    <% HashMap<Integer, String> studios = (HashMap<Integer, String>) request.getAttribute("estudios"); %>
    <% HashMap<Integer, String> publisher = (HashMap<Integer, String>) request.getAttribute("distribuidores"); %>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertar Juego</title>
</head>
<body>
<div class="general">
    <nav>
        <a href="user_menu.jsp">Menú</a>
        <a href="listarJuegos_user">Videojuegos</a>
        <a href="user_userDetails.jsp">Mis datos</a>
        <a href="admin_index.jsp">Administrar</a>
    </nav>

    <h1>Insertar Juego</h1>
    <form action="crearJuego_admin" method="post" enctype="multipart/form-data">
        <div>
            <label for="nombreJuego">Introduce el nombre del juego:</label>
            <input type="text" maxlength="100" id="nombreJuego" name="nombreJuego" required>
        </div>

        <div>
            <label for="descripcion">Introduce una descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="3" oninput="this.style.height = ''; this.style.height = this.scrollHeight + 'px'"></textarea>
        </div>

        <div>
            <label for="estudio">Introduce el estudio:</label>
            <select id="estudio" name="estudio" required>
                <%
                    for (Map.Entry<Integer, String> entry : studios.entrySet()) {
                %>
                <option value="<%= entry.getKey() %>"><%= entry.getValue() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>

        <div>
            <label for="publicador">Introduce el publicador:</label>
            <select id="publicador" name="publicador" required>
                <%
                    for (Map.Entry<Integer, String> entry : publisher.entrySet()) {
                %>
                <option value="<%= entry.getKey() %>"><%= entry.getValue() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>

        <div>
            <label for="genero">Introduce el género:</label>
            <select id="genero" name="genero" multiple>
                <%
                    for (Map.Entry<Integer, String> entry : genres.entrySet()) {
                %>
                <option value="<%= entry.getKey() %>"><%= entry.getValue() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>

        <div>
            <label for="consola">Introduce la consola:</label>
            <select id="consola" name="consola" multiple>
                <%
                    for (Map.Entry<Integer, String> entry : consoles.entrySet()) {
                %>
                <option value="<%= entry.getKey() %>"><%= entry.getValue() %>
                </option>
                <%
                    }
                %>
            </select>
        </div>


        <div class="form-group">
            <label for="imagenes">Subir imágenes del juego:</label>
            <input type="file" class="form-control" id="imagenes" name="imagenes" multiple="">
            <div id="previewImagenes" class="mt-3"></div>
        </div>

        <div class="form-group">
            <label for="guias">Subir guías del juego:</label>
            <input type="file" class="form-control" id="guias" name="guias" multiple="">
            <div id="previewGuias" class="mt-3"></div>
        </div>

        <div class="form-group">
            <label for="manuales">Subir manuales del juego:</label>
            <input type="file" class="form-control" id="manuales" name="manuales" multiple="">
            <div id="previewManuales" class="mt-3"></div>
        </div>

        <div class="boton">
            <button type="submit" class="btn btn-primary">Insertar Juego</button>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.11.338/pdf.min.js"></script>

<script>
    function previewFiles(inputId, previewId) {
        document.getElementById(inputId).addEventListener('change', function (event) {
            const preview = document.getElementById(previewId);
            preview.innerHTML = '';
            const files = event.target.files;
            if (files) {
                for (const file of files) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        if (file.type === 'application/pdf') {
                            const pdfUrl = e.target.result;
                            generatePdfThumbnail(pdfUrl, function (thumbnailUrl) {
                                const fileElement = document.createElement('div');
                                fileElement.classList.add('mr-2', 'mb-2');
                                const img = document.createElement('img');
                                img.src = thumbnailUrl;
                                img.classList.add('img-thumbnail');
                                img.style.maxWidth = '150px';
                                fileElement.appendChild(img);
                                preview.appendChild(fileElement);
                            });
                        } else {
                            const fileElement = document.createElement('div');
                            fileElement.classList.add('mr-2', 'mb-2');
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.classList.add('img-thumbnail');
                            img.style.maxWidth = '150px';
                            fileElement.appendChild(img);
                            preview.appendChild(fileElement);
                        }
                    };
                    reader.readAsDataURL(file);
                }
            }
        });
    }

    function generatePdfThumbnail(pdfUrl, callback) {

        pdfjsLib.getDocument({url: pdfUrl}).promise.then(function (pdf) {

            pdf.getPage(1).then(function (page) {
                const scale = 0.5;
                const viewport = page.getViewport({scale: scale});
                const canvas = document.createElement('canvas');
                const context = canvas.getContext('2d');
                canvas.height = viewport.height;
                canvas.width = viewport.width;
                const renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };

                page.render(renderContext).promise.then(function () {
                    const thumbnailUrl = canvas.toDataURL('image/jpeg');
                    callback(thumbnailUrl);
                });
            });
        });
    }

    previewFiles('imagenes', 'previewImagenes');
    previewFiles('guias', 'previewGuias');
    previewFiles('manuales', 'previewManuales');
</script>
</body>
</html>