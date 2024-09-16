package proyecto.sergio.demo.web.Administracion.Videojuegos.InsertarRom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import proyecto.sergio.demo.Repositorios.ROM.RepositoryInsertRom;
import proyecto.sergio.demo.Repositorios.ROM.RomEntity;
import proyecto.sergio.demo.Repositorios.ROM.ServiceInsertRom;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.UUID;
@MultipartConfig(maxFileSize = 1024 * 1024 * 1024, maxRequestSize = 1024 * 1024 * 1024) //nota: máximo 1GB de archivo.
@WebServlet(name = "insertarRom_admin", value = "/insertarRom_admin")
public class insertarRom extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            request.setCharacterEncoding("UTF-8");

            System.out.println("antes de juego");
            String juegoParam = request.getParameter("juego");
            if (juegoParam == null || juegoParam.isEmpty()) {
                throw new IllegalArgumentException("Parámetro 'juego' es nulo o vacío");
            }
            int id_juego = Integer.parseInt(juegoParam);
            System.out.println("id_juego: " + id_juego);

            System.out.println("antes de consola");
            String consolaParam = request.getParameter("consola");
            if (consolaParam == null || consolaParam.isEmpty()) {
                throw new IllegalArgumentException("Parámetro 'consola' es nulo o vacío");
            }
            int id_consola = Integer.parseInt(consolaParam);
            System.out.println("id_consola: " + id_consola);

            System.out.println("antes de rom");
            Part rom = request.getPart("rom");
            if (rom == null) {
                throw new IllegalArgumentException("Parte 'rom' es nula");
            }
            System.out.println("rom: " + rom.getSubmittedFileName());

            RomEntity romEntity = new RomEntity(saveFile(rom), 0, id_consola, id_juego);
            ServiceInsertRom serviceInsertRom = new ServiceInsertRom(new RepositoryInsertRom(connection));

            if (serviceInsertRom.insertRom(romEntity) > 0) {
                response.sendRedirect("admin_index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            response.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    private String saveFile(Part part) throws IOException {
        String UPLOAD_DIRECTORY = "/usr/local/tomcat/webapps/demo-1.0-SNAPSHOT/Miscelanea/roms";
        String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File directory = new File(UPLOAD_DIRECTORY);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, originalFileName);
        while (file.exists()) {
            originalFileName = UUID.randomUUID() + "_" + originalFileName;
            file = new File(directory, originalFileName);
        }

        try (InputStream input = part.getInputStream(); FileOutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

        return originalFileName;
    }
}