package proyecto.sergio.demo.filtros;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/*")
public class AccesoFiltroAdmin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURL().toString();

        Object rolObj = httpRequest.getSession().getAttribute("rol");

        // Manejo de URLs que contienen "admin"
        if (url.contains("admin")) {
            int rol = (rolObj != null) ? (int) rolObj : -1;
            if (rol == 2) {
                chain.doFilter(request, response);
                return;
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
                return;
            }
        }

        // Manejo de URLs que contienen "user"
        if (url.contains("user")) {
            int rol = (rolObj != null) ? (int) rolObj : -1;
            if (rol == -1) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
                return;
            }
        }

        // Continuar con el siguiente filtro en la cadena
        chain.doFilter(request, response);
    }
}
