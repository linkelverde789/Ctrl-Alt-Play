package proyecto.sergio.demo.web.Administracion.Usuario.Actualizar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.update.RepositoryUpdateUser;
import proyecto.sergio.demo.poolConexiones.Conexion;
import proyecto.sergio.demo.Repositorios.User.update.ServicioUpdateUser;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "actualizarCuenta_admin", value = "/actualizarCuenta_admin")
public class ActualizarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {

            String nombre = req.getParameter("nombre");
            String usuario = req.getParameter("usuario");
            String correo = req.getParameter("correo");
            String contrasena = req.getParameter("contraseña");
            int ID = Integer.parseInt(req.getParameter("ID"));
            int rolID = Integer.parseInt(req.getParameter("rolID"));


            ServicioUpdateUser serviceUpdateUser = new ServicioUpdateUser(new RepositoryUpdateUser(connection));
            UserEntity updateUser = new UserEntity(ID, rolID, nombre, usuario, contrasena, correo);
            if (serviceUpdateUser.updateUser(updateUser)) {
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
