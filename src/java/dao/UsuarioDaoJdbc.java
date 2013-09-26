package dao;

import conn.ConexaoJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import util.Parametro;

public class UsuarioDaoJdbc  extends ConexaoJdbc implements UsuarioDaoInterface {
    
    public UsuarioDaoJdbc() {
        super();
    }

    @Override
    public Integer criar(Usuario usuario) {
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        try {
            parametros.add(new Parametro().setString(usuario.getNome()));
            parametros.add(new Parametro().setString(usuario.getMatricula()));
            parametros.add(new Parametro().setString(usuario.getSenha()));
            parametros.add(new Parametro().setBoolean(usuario.getFuncionario()));
            parametros.add(new Parametro().setInteger(usuario.getMaxEmprestimo()));
            parametros.add(new Parametro().setBoolean(usuario.getAtivo()));
            ResultSet rs = executarSql(
                    "insert into bib_usuario (nome, matricula, senha, funcionario, maxEmprestimo, ativo)"
                    + " values (?, ?, ?, ?, ?, ?) returning cod"
                    , parametros, true);
            rs.next();
            usuario.setCod(rs.getInt(1));
            return rs.getInt(1);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Usuario buscaPorCodigo(Integer cod) {
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        try {
            parametros.add(new Parametro().setInteger(cod));
            ResultSet rs = executarSql(
                    "select cod, nome, matricula, senha, funcionario, maxEmprestimo, ativo"
                    + " from bib_usuario where cod = ?"
                    , parametros, true);
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCod(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setMatricula(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setFuncionario(rs.getBoolean(5));
                usuario.setMaxEmprestimo(rs.getInt(6));
                usuario.setAtivo(rs.getBoolean(7));
                return usuario;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Usuario buscaPorMatricula(String matricula) {
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        try {
            parametros.add(new Parametro().setString(matricula));
            ResultSet rs = executarSql(
                    "select cod, nome, matricula, senha, funcionario, maxEmprestimo, ativo"
                    + " from bib_usuario where matricula = ?"
                    , parametros, true);
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCod(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setMatricula(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setFuncionario(rs.getBoolean(5));
                usuario.setMaxEmprestimo(rs.getInt(6));
                usuario.setAtivo(rs.getBoolean(7));
                return usuario;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void remover(Usuario usuario) {
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        try {
            parametros.add(new Parametro().setInteger(usuario.getCod()));
            executarSql(
                    "delete from bib_usuario "
                    + " where cod = ?"
                    , parametros, false);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        try {
            parametros.add(new Parametro().setString(usuario.getNome()));
            parametros.add(new Parametro().setString(usuario.getMatricula()));
            parametros.add(new Parametro().setString(usuario.getSenha()));
            parametros.add(new Parametro().setBoolean(usuario.getFuncionario()));
            parametros.add(new Parametro().setInteger(usuario.getMaxEmprestimo()));
            parametros.add(new Parametro().setBoolean(usuario.getAtivo()));
            parametros.add(new Parametro().setInteger(usuario.getCod()));
            executarSql(
                    "update bib_usuario set nome = ?, matricula = ?, senha = ?,"
                    + "funcionario = ?, maxEmprestimo = ?, ativo = ?"
                    + " where cod = ?"
                    , parametros, false);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario getLoginValido(String matricula, String senha) {
        try {
            ResultSet rs = executarSql(
                "select cod, nome, matricula, senha from bib_usuario"
                , null, true);
            while (rs.next()) {
                if ((rs.getString(3).equals(matricula)) && (rs.getString(4).equals(senha))) {
                    Usuario usuario = this.buscaPorCodigo(rs.getInt(1));
                    return usuario;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> obtemTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            ResultSet rs = executarSql(
                    "select cod, nome, matricula, senha, funcionario, maxEmprestimo, ativo"
                    + " from bib_usuario"
                    , null, true);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCod(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setMatricula(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setFuncionario(rs.getBoolean(5));
                usuario.setMaxEmprestimo(rs.getInt(6));
                usuario.setAtivo(rs.getBoolean(7));
                usuarios.add(usuario);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDaoJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
}
