package proyecto.sergio.demo.web.Administracion.Videojuegos.ActualizarJuego;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.Other.getID.RepositoryGetID;
import proyecto.sergio.demo.Repositorios.Videogames.Other.getID.ServiceGetID;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Publisher.RepositoryGetPublisher;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Publisher.ServiceGetPublisher;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Studios.RepositoryGetStudios;
import proyecto.sergio.demo.Repositorios.Videogames.WebGames.Studios.ServiceGetStudios;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "gestorActualizarJuego_admin", value = "/gestorActualizarJuego_admin")
public class gestorActualizarJuego extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {

            ServiceGetPublisher serviceGetPublisher = new ServiceGetPublisher(new RepositoryGetPublisher(connection));
            ServiceGetStudios serviceGetStudios = new ServiceGetStudios(new RepositoryGetStudios(connection));
            ServiceGetID serviceGetID = new ServiceGetID(new RepositoryGetID(connection));

            HashMap<Integer, String> studios = serviceGetStudios.getStudios();
            HashMap<Integer, String> publisher = serviceGetPublisher.getPublisher();
            List<Integer> ID = serviceGetID.getID();
            Collections.sort(ID);
            req.setAttribute("studios", studios);
            req.setAttribute("publisher", publisher);
            req.setAttribute("ID", ID);
            req.getRequestDispatcher("admin_actualizarVideojuego.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
