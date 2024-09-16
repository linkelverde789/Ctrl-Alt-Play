package proyecto.sergio.demo.web.Administracion.Usuario.Crear;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.create.RepositoryCreateUser;
import proyecto.sergio.demo.Repositorios.User.create.ServiceCreateUser;
import proyecto.sergio.demo.poolConexiones.Conexion;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "crearUsuario_admin", value = "/crearUsuario_admin")
public class CrearUsuario extends HttpServlet {
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
            if(serviceCreateUser.createUser(user)>0){
                resp.sendRedirect("admin_index.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }}