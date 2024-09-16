package proyecto.sergio.demo.web.Administracion.Videojuegos.CrearJuego;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Consoles.RepositoryGetConsoles;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Consoles.ServiceGetConsoles;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Genres.RepositoryGetGenres;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Genres.ServiceGetGenres;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Publisher.RepositoryGetPublisher;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Publisher.ServiceGetPublisher;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Studios.RepositoryGetStudios;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Studios.ServiceGetStudios;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "gestorCrearJuego_admin",value = "/gestorCrearJuego_admin")
public class gestorCrearJuego extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()){

            HashMap<Integer, String> genres = new ServiceGetGenres(new RepositoryGetGenres(connection)).getGenres();
            HashMap<Integer, String> studios = new ServiceGetStudios(new RepositoryGetStudios(connection)).getStudios();
            HashMap<Integer, String> publisher = new ServiceGetPublisher(new RepositoryGetPublisher(connection)).getPublisher();
            HashMap<Integer, String> consoles = new ServiceGetConsoles(new RepositoryGetConsoles(connection)).getConsoles();

            req.setAttribute("generos", genres);
            req.setAttribute("estudios", studios);
            req.setAttribute("distribuidores", publisher);
            req.setAttribute("consolas", consoles);

            RequestDispatcher rd = req.getRequestDispatcher("admin_crearVideojuego.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
