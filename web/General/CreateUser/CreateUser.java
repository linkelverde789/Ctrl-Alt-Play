package proyecto.sergio.demo.web.General.CreateUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.create.RepositoryCreateUser;
import proyecto.sergio.demo.Repositorios.User.create.ServiceCreateUser;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "registroUsuario", value = "/registroUsuario")
public class CreateUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {
            String name = req.getParameter("nombre");
            String usuario = req.getParameter("usuario");
            String correo = req.getParameter("correo");
            String contrasena = req.getParameter("contrasena");
            int rol = Integer.parseInt(req.getParameter("rol"));
            UserEntity user = new UserEntity(0, rol, name, usuario, contrasena, correo);
            ServiceCreateUser serviceCreateUser = new ServiceCreateUser(new RepositoryCreateUser(connection));
            if (serviceCreateUser.createUser(user) > 0) {
                HttpSession session = req.getSession();
                session.setAttribute("usuario", user);
                session.setAttribute("rol", user.getID_Rol());
                resp.sendRedirect("user_menu.jsp");
            }else{
                resp.sendRedirect("login.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
