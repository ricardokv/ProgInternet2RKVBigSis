package controller;

import dao.UsuarioDaoJdbc;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Front", urlPatterns = {"/secured/main"})
public class Front extends HttpServlet {
    
    private UsuarioDaoJdbc usuarioDao;
    private String mensagem;
    private HttpSession sessao;
     
    public Front() {
        
        usuarioDao = new UsuarioDaoJdbc();
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        sessao = request.getSession(true);
        mensagem = "";
        
        // Obtém ação
        String action = (String) request.getParameter("action");
        if (action == null) {
            action = "conteudo";
        }
        
        // Processa ação
        if (action.equals("inserir") || action.equals("editar")) {
            String nome = (String) request.getParameter("nome");
            String usuario = (String) request.getParameter("usuario");
            String senha = (String) request.getParameter("senha");
            String senha2 = (String) request.getParameter("senha2");
            if (action.equals("inserir")) {
                if (inserirAcao(nome, usuario, senha, senha2)) action = "conteudo";
            }
            if (action.equals("editar")) {
                Integer id = Integer.parseInt((String) request.getParameter("id"));
                String posted = (String) request.getParameter("posted");
                if (posted == null) posted = "";
                if (editarAction(id, nome, usuario, senha, senha2, posted)) action = "conteudo";
            }
        }
        if (action.equals("excluir")) {
            Integer id = Integer.parseInt((String) request.getParameter("id"));
            removerAcao(id);
            action = "conteudo";
        }
        
        // Exibe Layout conforme ação
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./cabecalho.jsp").include(request, response);
        
        // Exibe mensagem
        if (!mensagem.equals("")) {
            PrintWriter out = response.getWriter();
            out.println(mensagem);
        }
        
        // Exibe conteúdo
        if (action.equals("inserir")) {
            request.getRequestDispatcher("./inserir.jsp").include(request, response);
        }
        
        if (action.equals("editar")) {
            request.getRequestDispatcher("./editar.jsp").include(request, response);
        }
        
        if (action.equals("conteudo")) {
            sessao.setAttribute("users", usuarioDao.obtemTodos());
            request.getRequestDispatcher("./conteudo.jsp").include(request, response);
        }
        
        request.getRequestDispatcher("./rodape.jsp").include(request, response);
        
    }
    
    private Boolean inserirAcao(String nome, String usuario, String senha, String senha2) {
        
        if (senha2 == null) return false;
        Boolean result = true;
        if (nome.length() <= 5) {
            mensagem = "Você deve digitar um nome com mais de 5 caracteres";
            result = false;
        }
        if (usuario.length() <= 5) {
            mensagem = "Você deve digitar um usuario com mais de 5 caracteres";
            result = false;
        }
        if (senha.length() <= 5) {
            mensagem = "Você deve digitar uma senha com mais de 5 caracteres";
            result = false;
        }
        if (!senha.equals(senha2)) {
            mensagem = "As senhas digitadas não conferem";
            result = false;
        }
        
        model.Usuario user = new model.Usuario();
        user.setNome(nome);
        user.setMatricula(usuario);
        user.setSenha(senha);
        sessao.setAttribute("user", user);
        if (!result) return result;
        
        Integer id = usuarioDao.criar(user);
        mensagem = "Inserido o usuário " + user.getNome();
        
        return true;
        
    }
    
    private Boolean editarAction(Integer id, String nome, String usuario, String senha, String senha2, String posted) {
        
        model.Usuario user = usuarioDao.buscaPorCodigo(id);
        if (!posted.equals("1")) {
            sessao.setAttribute("user", user);
            return false;
        }
        
        Boolean result = true;
        user.setNome(nome);
        user.setMatricula(usuario);
        if (senha.length() != 0) user.setSenha(senha);
        if (nome.length() <= 5) {
            mensagem = "Você deve digitar um nome com mais de 5 caracteres";
            result = false;
        }
        if (usuario.length() <= 5) {
            mensagem = "Você deve digitar um usuario com mais de 5 caracteres";
            result = false;
        }
        if (!senha.equals(senha2)) {
            mensagem = "As senhas digitadas não conferem";
            result = false;
        }
        
        if (!result) return result;
        
        usuarioDao.atualizar(user);
        sessao.setAttribute("user", user);
        mensagem = "Alterado o usuário " + nome;
        
        return true;
        
    }
    
    private void removerAcao(Integer id) {
        model.Usuario user = usuarioDao.buscaPorCodigo(id);
        mensagem = "Excluído o usuário " + user.getNome();
        usuarioDao.remover(user);
    }
    
    private void usuarioPendenciasAcao() {}
    
    private void usuarioRenovarAcao() {}
    
    private void livroConsultarAcao() {}
    
    private void livroEmprestimoAcao() {}
    
    private void livroDevolucaoAcao() {}

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
