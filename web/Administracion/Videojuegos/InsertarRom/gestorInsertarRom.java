package proyecto.sergio.demo.web.Administracion.Videojuegos.InsertarRom;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.Other.read.RepositoryReadGames;
import proyecto.sergio.demo.Repositorios.Videogames.Other.read.ServiceReadGames;
import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Consoles.RepositoryGetConsoles;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Consoles.ServiceGetConsoles;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "gestorInsertarRom_admin", value = "/gestorInsertarRom_admin")
public class gestorInsertarRom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            HashMap<Integer, String> consolas = new ServiceGetConsoles(new RepositoryGetConsoles(connection)).getConsoles();
            req.setAttribute("consolas", consolas);

            List<VideogameEntity> juegos = new ServiceReadGames(new RepositoryReadGames(connection)).readGames();
            req.setAttribute("juegos", juegos);
            req.getRequestDispatcher("admin_insertarRom.jsp").forward(req, resp);

        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            req.setAttribute("consolas", new ServiceGetConsoles(new RepositoryGetConsoles(connection)).getConsoles());
            req.setAttribute("juegos", new ServiceReadGames(new RepositoryReadGames(connection)).readGames());
            req.getRequestDispatcher("admin_insertarRom.jsp").forward(req, resp);

        } catch (Exception e) {
            String errorMessage = URLEncoder.encode(e.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
