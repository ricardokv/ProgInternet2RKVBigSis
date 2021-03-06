package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "Seguranca", urlPatterns = {"/secured/*"})
public class Seguranca implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        // Se não existe sessão, redirecionar para login
        HttpSession sessao = req.getSession(true);
        if (sessao.getAttribute("usuario") == null) {
            res.sendRedirect("/BibSis/index.jsp");
        }

        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {}
    

}
