package proyecto.sergio.demo.web.Administracion.Usuario.Eliminar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.remove.RepositoryRemoveUser;
import proyecto.sergio.demo.Repositorios.User.remove.ServiceRemoveUser;
import proyecto.sergio.demo.poolConexiones.Conexion;


import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "eliminarUsuario_admin", value = "/eliminarUsuario_admin")
public class EliminarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            int ID = Integer.parseInt(req.getParameter("ID"));
            ServiceRemoveUser serviceRemoveUser = new ServiceRemoveUser(new RepositoryRemoveUser(connection));
            if (serviceRemoveUser.deleteUser(ID)) {
                resp.sendRedirect("admin_index.jsp");
            } else {
                resp.sendRedirect("error.jsp?error=" + URLEncoder.encode("No se ha podido actualizar la cuenta y no sabemos el porqué.", "UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
