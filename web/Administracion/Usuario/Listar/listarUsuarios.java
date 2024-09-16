package proyecto.sergio.demo.web.Administracion.Usuario.Listar;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.read.RepositoryReadUser;
import proyecto.sergio.demo.Repositorios.User.read.ServiceReadUser;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "listarUsuarios_admin",value = "/listarUsuarios_admin")
public class listarUsuarios extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(proyecto.sergio.demo.web.Videojuegos.ListarJuegos.ListarJuegos.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            req.setAttribute("listaUsuarios", new ServiceReadUser(new RepositoryReadUser(connection)).readUser());
            req.getRequestDispatcher("admin_listarUsuarios.jsp").forward(req, resp);
        } catch (Exception ex) {
            String errorMessage = URLEncoder.encode(ex.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            req.setAttribute("listaUsuarios", new ServiceReadUser(new RepositoryReadUser(connection)).readUser());
            req.getRequestDispatcher("admin_listarUsuarios.jsp").forward(req, resp);
        } catch (Exception ex) {
            String errorMessage = URLEncoder.encode(ex.getMessage(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}