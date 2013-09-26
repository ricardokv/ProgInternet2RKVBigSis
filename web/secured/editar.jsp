<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    model.Usuario user = (model.Usuario) session.getAttribute("user");
    %>
<form role="form" method="post">
    <input type="hidden" name="id" value="<%=user.getCod()%>">
    <input type="hidden" name="posted" value="1">
  <div class="form-group">
    <label for="idNome">Nome</label>
    <input type="text" class="form-control" id="idNome" name="nome" placeholder="Nome completo" value="<%=user.getNome()%>">
  </div>
  <div class="form-group">
    <label for="idUsuario">Usuário</label>
    <input type="text" class="form-control" id="idUsuario" name="usuario" placeholder="Usuário (login)" value="<%=user.getMatricula()%>">
  </div>
  <div class="form-group">
    <label for="idSenha">Senha</label>
    <input type="password" class="form-control" id="idSenha" name="senha" placeholder="Senha">
    <input type="password" class="form-control" id="idSenha2" name="senha2" placeholder="Redigite a senha">
  </div>
  <button type="submit" class="btn btn-default">Salvar</button>
</form>