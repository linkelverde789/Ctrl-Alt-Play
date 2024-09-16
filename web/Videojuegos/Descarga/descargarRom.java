package proyecto.sergio.demo.web.Videojuegos.Descarga;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.Videogames.Other.registerDownload.RepositoryRegisterDownload;
import proyecto.sergio.demo.Repositorios.Videogames.Other.registerDownload.ServiceRegisterDownload;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(name = "descargarRom_user", value = "/descargarRom_user")
public class descargarRom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            Object rolObj = req.getSession().getAttribute("rol");
            int rol = (rolObj != null) ? (int) rolObj : -1;
            if (rol < 1 || rol > 3) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            UserEntity userEntity= (UserEntity) req.getSession().getAttribute("usuario");
            int romId = Integer.parseInt(req.getParameter("id_rom"));
            ServiceRegisterDownload service = new ServiceRegisterDownload(new RepositoryRegisterDownload(connection));
            if(service.downloadGame(userEntity.getID(), romId) != 0){
                resp.sendRedirect("juegoCompleto_user?id="+Integer.parseInt(req.getParameter("id_juego")));
            }
        } catch (Exception e) {
            // Redirigir el mensaje de excepción como parámetro
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
