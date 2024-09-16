package proyecto.sergio.demo.web.General.EliminarCuenta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.remove.RepositoryRemoveUser;
import proyecto.sergio.demo.Repositorios.User.remove.ServiceRemoveUser;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "eliminarCuenta_user", value = "/eliminarCuenta_user")
public class EliminarCuenta extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            ServiceRemoveUser serviceRemoveUser = new ServiceRemoveUser(new RepositoryRemoveUser(connection));
            HttpSession session = req.getSession();
            UserEntity userEntity = (UserEntity) session.getAttribute("usuario");

            if (serviceRemoveUser.deleteUser(userEntity.getID())) {
                session.invalidate();
                resp.sendRedirect("login.html");
            } else {
                resp.sendRedirect("error.jsp?error=" + URLEncoder.encode("No se ha podido eliminar tu cuenta y no sabemos el porqué.", "UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
