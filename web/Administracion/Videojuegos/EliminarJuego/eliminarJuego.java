package proyecto.sergio.demo.web.Administracion.Videojuegos.EliminarJuego;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.Other.delete.RepositoryDeleteGame;
import proyecto.sergio.demo.Repositorios.Videogames.Other.delete.ServiceDeleteGame;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "eliminarJuego_admin", value = "/eliminarJuego_admin")
public class eliminarJuego extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {

            int id = Integer.parseInt(req.getParameter("id"));
            ServiceDeleteGame serviceDeleteGame = new ServiceDeleteGame(new RepositoryDeleteGame(connection));

            if (serviceDeleteGame.removeGame(id)) {
                resp.sendRedirect("admin_index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}