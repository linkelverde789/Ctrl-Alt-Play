package proyecto.sergio.demo.web.General.login;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyecto.sergio.demo.Repositorios.User.UserEntity;
import proyecto.sergio.demo.Repositorios.User.login.RepositoryLogin;
import proyecto.sergio.demo.Repositorios.User.login.ServiceLogin;
import proyecto.sergio.demo.poolConexiones.Conexion;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection connection = Conexion.obtenerConexion().getConnection()) {

            UserEntity userLogged = new ServiceLogin(new RepositoryLogin(connection)).login(request.getParameter("usuario"), request.getParameter("password"));
            if (userLogged != null) {

                if (userLogged.getID_Rol() != 4) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", userLogged);
                    session.setAttribute("rol", userLogged.getID_Rol());
                    response.sendRedirect("user_menu.jsp");
                } else {
                    response.sendRedirect("baneado.html");
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            response.sendRedirect("error.jsp?error=" + errorMessage);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}