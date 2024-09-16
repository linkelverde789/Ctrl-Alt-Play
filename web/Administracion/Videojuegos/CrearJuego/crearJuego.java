package proyecto.sergio.demo.web.Administracion.Videojuegos.CrearJuego;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames.RepositoryInsertGames;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames.ServiceInsertGame;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.CreateGames.VideogameEntity;
import proyecto.sergio.demo.poolConexiones.Conexion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(name="crearJuego_admin", value="/crearJuego_admin")
@MultipartConfig
public class crearJuego extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "/usr/local/tomcat/webapps/demo-1.0-SNAPSHOT/Miscelanea/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            request.setCharacterEncoding("UTF-8");

            // Obtener los parámetros del formulario
            String nombreJuego = request.getParameter("nombreJuego");
            String descripcion = request.getParameter("descripcion");
            String estudio = request.getParameter("estudio");
            String[] generos = request.getParameterValues("genero");
            String[] consolas = request.getParameterValues("consola");
            String publicador = request.getParameter("publicador");
            List<Part> imagenes = request.getParts().stream().filter(part -> "imagenes".equals(part.getName())).collect(Collectors.toList());
            List<Part> manuales = request.getParts().stream().filter(part -> "manuales".equals(part.getName())).collect(Collectors.toList());
            List<Part> guias = request.getParts().stream().filter(part -> "guias".equals(part.getName())).collect(Collectors.toList());

            // Crear la entidad del videojuego
            VideogameEntity videojuego = new VideogameEntity(0, nombreJuego, descripcion, Integer.parseInt(publicador), Integer.parseInt(estudio));

            // Añadir géneros
            if (generos != null) {
                for (String genero : generos) {
                    videojuego.addGenreID(Integer.parseInt(genero));
                }
            }else{
                System.out.println("sin generos");
            }

            // Añadir consolas
            if (consolas != null) {
                for (String consola : consolas) {
                    videojuego.addConsoleID(Integer.parseInt(consola));
                }
            }else{
                System.out.println("sin consolas");
            }

            if (imagenes != null && !imagenes.isEmpty()) {
                for (Part imagenItem : imagenes) {
                    if (imagenItem != null && imagenItem.getSize() > 0) {
                        String fileName = saveFile(imagenItem, "fotos");
                        videojuego.addFoto(fileName);
                    }
                }
            } else {
                System.out.println("sin imagenes");
            }

            if (guias != null && !guias.isEmpty()) {
                for (Part guiaItem : guias) {
                    if (guiaItem != null && guiaItem.getSize() > 0) {
                        String fileName = saveFile(guiaItem, "guias");
                        videojuego.addGuia(fileName);
                    }
                }
            } else {
                System.out.println("sin guias");
            }

            if (manuales != null && !manuales.isEmpty()) {
                for (Part manualItem : manuales) {
                    if (manualItem != null && manualItem.getSize() > 0) {
                        String fileName = saveFile(manualItem, "manuales");
                        videojuego.addManual(fileName);
                    }
                }
            } else {
                System.out.println("sin manuales");
            }



            int id=new ServiceInsertGame(new RepositoryInsertGames(connection)).insertGame(videojuego);

            response.sendRedirect("juegoCompleto_user?id="+id);
        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            response.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    private String saveFile(Part part, String subDirectory) throws IOException {
        String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String fileExtension = "";

        // Obtener la extensión del archivo
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        File directory = new File(UPLOAD_DIRECTORY + subDirectory);

        // Crear el directorio si no existe
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, uniqueFileName);

        // Si el archivo ya existe como directorio, genera un nuevo nombre de archivo
        while (file.exists() && file.isDirectory()) {
            uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            file = new File(directory, uniqueFileName);
        }

        // Guardar el archivo
        try (InputStream input = part.getInputStream();
             FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        return uniqueFileName;
    }

}
