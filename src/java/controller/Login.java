package controller;

import dao.UsuarioDaoJdbc;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    private UsuarioDaoJdbc userDao;
    
    public Login() {
        
        userDao = new UsuarioDaoJdbc();
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtém usuário com dados do login
        String reqUsuario = request.getParameter("usuario");
        String reqSenha = request.getParameter("senha");   
        Usuario user = userDao.getLoginValido(reqUsuario, reqSenha);

        // Se não retornou usuário, redireciona pra login com mensagem
        if (user == null) {
            response.sendRedirect("index.jsp?error=1");
            return;
        }
        
        // Se usuário retornado, login OK então configuro sessão e redireciono pra tela inicial
        HttpSession sessao = request.getSession(true);
        sessao.setAttribute("usuario", user);
        response.sendRedirect("/BibSis/secured/main");
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
