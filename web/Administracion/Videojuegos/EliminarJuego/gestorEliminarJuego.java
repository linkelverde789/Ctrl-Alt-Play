package proyecto.sergio.demo.web.Administracion.Videojuegos.EliminarJuego;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.Videogames.Other.getID.RepositoryGetID;
import proyecto.sergio.demo.Repositorios.Videogames.Other.getID.ServiceGetID;
import proyecto.sergio.demo.Repositorios.Videogames.Other.read.RepositoryReadGames;
import proyecto.sergio.demo.Repositorios.Videogames.Other.read.ServiceReadGames;
import proyecto.sergio.demo.Repositorios.Videogames.VideogameEntity;
import proyecto.sergio.demo.poolConexiones.Conexion;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "gestorEliminarJuego_admin", value = "/gestorEliminarJuego_admin")
public class gestorEliminarJuego extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            ServiceReadGames serviceReadGames=new ServiceReadGames(new RepositoryReadGames(connection));
            List<VideogameEntity> userList = serviceReadGames.readGames();
            req.setAttribute("list", userList);
            req.getRequestDispatcher("admin_eliminarVideojuego.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
