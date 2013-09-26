package dao;

import java.util.ArrayList;
import model.Usuario;

public interface UsuarioDaoInterface {
    
    public Integer criar(Usuario user);
    
    public Usuario buscaPorCodigo(Integer id);
    
    public Usuario buscaPorMatricula(String matricula);
    
    public void remover(Usuario user);
    
    public void atualizar(Usuario user);
    
    public Usuario getLoginValido(String user, String password);
    
    public ArrayList<Usuario> obtemTodos();
    
}
