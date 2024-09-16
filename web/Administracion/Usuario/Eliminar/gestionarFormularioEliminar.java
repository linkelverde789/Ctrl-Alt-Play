package proyecto.sergio.demo.web.Administracion.Usuario.Eliminar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.sergio.demo.Repositorios.User.ID.RepositoryGetID;
import proyecto.sergio.demo.Repositorios.User.ID.ServiceGetID;
import proyecto.sergio.demo.Repositorios.User.read.RepositoryReadUser;
import proyecto.sergio.demo.Repositorios.User.read.ServiceReadUser;
import proyecto.sergio.demo.poolConexiones.Conexion;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "gestionarFormularioEliminar_admin", value = "/gestionarFormularioEliminar_admin")
public class gestionarFormularioEliminar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Conexion.obtenerConexion().getConnection()) {


            req.setAttribute("lista",new ServiceReadUser(new RepositoryReadUser(connection)).readUser());
            req.getRequestDispatcher("admin_eliminarUsuario.jsp").forward(req, resp);


        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = URLEncoder.encode(e.toString(), "UTF-8");
            resp.sendRedirect("error.jsp?error=" + errorMessage);
        }
    }
}
