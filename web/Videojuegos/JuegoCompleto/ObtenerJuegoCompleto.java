package proyecto.sergio.demo.web.Videojuegos.JuegoCompleto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto.RepositoryJuegoCompleto;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto.ServiceJuegoCompleto;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.JuegoCompleto.VideogameEntity;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "juegoCompleto_user",value ="/juegoCompleto_user" )
public class ObtenerJuegoCompleto extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conexion = Conexion.obtenerConexion().getConnection()) {
            int id = Integer.parseInt(req.getParameter("id"));
            VideogameEntity video = new ServiceJuegoCompleto(new RepositoryJuegoCompleto(conexion)).fullGame(id);
            req.setAttribute("juegoCompleto", video);
            // Usar forward en lugar de sendRedirect para enviar la solicitud a la JSP
            req.getRequestDispatcher("user_JuegoCompleto.jsp").forward(req, resp);
        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // No llamar a super.doGet
        try (Connection conexion = Conexion.obtenerConexion().getConnection()) {
            int id = Integer.parseInt(req.getParameter("id"));
            VideogameEntity video = new ServiceJuegoCompleto(new RepositoryJuegoCompleto(conexion)).fullGame(id);
            req.setAttribute("juegoCompleto", video);
            // Usar forward en lugar de sendRedirect para enviar la solicitud a la JSP
            req.getRequestDispatcher("user_JuegoCompleto.jsp").forward(req, resp);
        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
