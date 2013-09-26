<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Usuario"%>
<div>
    <table class="table table-striped table-bordered table-hover">
        <tr>
            <th>Nome</th>
            <th>Usuário</th>
            <th colspan="2"></th>
        </tr>
        <%
            HttpSession sessao = request.getSession(true);
            ArrayList<Usuario> users = (ArrayList<Usuario>) sessao.getAttribute("users");
            
            for (Usuario user: users) {
                out.println("<tr>");
                out.println("<td>" + user.getNome() + "</td>");
                out.println("<td>" + user.getMatricula()+ "</td>");
                out.println("<td><a class='btn btn-primary' href='/BibSis/secured/main?action=editar&id=" + user.getCod() + "'>Editar</a></td>");
                out.println("<td><a class='btn btn-warning' href='/BibSis/secured/main?action=excluir&id=" + user.getCod() + "'>Deletar</a></td>");
                out.println("</tr>");
            }
            
            %>
    </table>
</div>