package proyecto.sergio.demo.web.Administracion.Videojuegos.ActualizarJuego;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.create.RepositoryCreateUser;
import proyecto.sergio.demo.Repositorios.User.create.ServiceCreateUser;
import proyecto.sergio.demo.Repositorios.Videogames.Other.update.RepositoryUpdateGame;
import proyecto.sergio.demo.Repositorios.Videogames.Other.update.ServiceUpdateGame;
import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "actualizarJuego_admin", value = "/actualizarJuego_admin")
public class actualizarJuego extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            String title = req.getParameter("titulo");
            String description = req.getParameter("descripcion");
            int studioID = Integer.parseInt(req.getParameter("estudio"));
            int publisherID = Integer.parseInt(req.getParameter("distribuidor"));
            int id = Integer.parseInt(req.getParameter("id"));
            VideogameEntity videogameEntity = new VideogameEntity(id, studioID, publisherID, title, description);
            ServiceUpdateGame serviceUpdateGame = new ServiceUpdateGame(new RepositoryUpdateGame(connection));
            if (serviceUpdateGame.actualizarVideojuegos(videogameEntity)) {
                resp.sendRedirect("admin_index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
