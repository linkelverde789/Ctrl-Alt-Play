package proyecto.sergio.demo.web.General.ActualizaUsuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.update.RepositoryUpdateUser;
import proyecto.sergio.demo.poolConexiones.Conexion;
import proyecto.sergio.demo.Repositorios.User.update.ServicioUpdateUser;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "actualizarCuenta_user", value = "/actualizarCuenta_user")
public class ActualizarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {

            HttpSession session = req.getSession();
            UserEntity sessionUser = (UserEntity) session.getAttribute("usuario");
            String nombre;
            String usuario;
            String correo;
            String contraseña;

            if (req.getParameter("nombre") == null) {
                nombre = sessionUser.getNombre();
            } else {
                nombre = req.getParameter("nombre");
            }

            if (req.getParameter("correo") == null) {
                correo = sessionUser.getCorreo();
            } else {
                correo = req.getParameter("correo");
            }


            if (req.getParameter("usuario") == null) {
                usuario = sessionUser.getUsuario();
            } else {
                usuario = req.getParameter("usuario");
            }

            if (req.getParameter("contraseña") == null) {
                contraseña = sessionUser.getPassword();
            } else {
                contraseña = req.getParameter("contraseña");
            }
            ServicioUpdateUser serviceUpdateUser = new ServicioUpdateUser(new RepositoryUpdateUser(connection));
            UserEntity updateUser = new UserEntity(sessionUser.getID(), sessionUser.getID_Rol(), nombre, usuario, contraseña, correo);
            if (serviceUpdateUser.updateUser(updateUser)) {
                session.setAttribute("usuario", updateUser);
                resp.sendRedirect("user_userDetails.jsp");
            } else {
                resp.sendRedirect("error.jsp?error=" + URLEncoder.encode("No se ha podido actualizar tu cuenta y no sabemos el porqué.", "UTF-8"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
