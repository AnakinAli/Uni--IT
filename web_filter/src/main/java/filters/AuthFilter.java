package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/protected/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();

        String user = (String) session.getAttribute("user");

        if (user != null && user.equals("admin")) {
            chain.doFilter(request, response);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            response.getWriter().print("Нямате достъп до този ресурс");
        }
    }
}