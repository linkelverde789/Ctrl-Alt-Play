package proyecto.sergio.demo.web.Videojuegos.ListarJuegos;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Normal.RepositoryListWebGame;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Normal.ServiceWebListGame;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Texto.RepositoryListTextWebGame;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.ListGames.Texto.ServiceWebListTextGame;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "listarJuegos_user",value = "/listarJuegos_user")
public class ListarJuegos extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ListarJuegos.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            ServiceWebListTextGame serviceWebListGame = new ServiceWebListTextGame(new RepositoryListTextWebGame(connection));
            String texto = req.getParameter("busqueda");
            req.setAttribute("resultado", serviceWebListGame.videogamesList(texto));
            req.getRequestDispatcher("user_listarVideojuegos.jsp").forward(req, resp);
        } catch (Exception ex) {
            String errorMessage = URLEncoder.encode(ex.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            ServiceWebListGame service = new ServiceWebListGame(new RepositoryListWebGame(connection));
            req.setAttribute("resultado", service.videogamesList());
            req.getRequestDispatcher("user_listarVideojuegos.jsp").forward(req, resp);
        } catch (Exception ex) {
            String errorMessage = URLEncoder.encode(ex.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
