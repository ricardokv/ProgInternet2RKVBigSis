package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import util.Parametro;

public class ConexaoJdbc<T> {

    private final String strConn = "jdbc:postgresql://localhost/";
    private final String usrConn = "postgres";
    private final String pwdConn = "senacrs";
    private final String strDatabase = "lpw2_bibsis";
    
    protected static Connection conn = null;

    public ConexaoJdbc() {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                try {
                    conn = DriverManager.getConnection(strConn, usrConn, pwdConn);
                    executarSql("create database " + strDatabase, null, false);
                    conn.close();
                    conn = DriverManager.getConnection(strConn + strDatabase, usrConn, pwdConn);
                    executarSql("CREATE TABLE bib_usuario (cod serial NOT NULL, "
                            + "nome character varying(80), matricula character varying(30),"
                            + "senha character varying(40), funcionario boolean,"
                            + "maxEmprestimo integer, ativo boolean)"
                            ,null, false);
                    executarSql("insert into bib_usuario (nome, matricula, senha, funcionario, maxEmprestimo, ativo) values "
                            + "('Administrador', 'admin', '123456', true, 5, true)", null, false);
                    conn.close();
                } catch (Exception e) {
                    // Do nothing, DB exists
                }
                conn = DriverManager.getConnection(strConn + strDatabase, usrConn, pwdConn);
            } catch (Exception e) {
                die(e, "Database connection failure.");
            }
        }
    }

    public ResultSet executarSql(String sql, List<Parametro> parametros, Boolean query) throws Exception {

        PreparedStatement stmt = conn.prepareStatement(sql);
        Integer ctd = 1;
        if (parametros != null) {
            for (Parametro parametro : parametros) {
                if (parametro.isString()) {
                    stmt.setString(ctd++, parametro.getString());
                } else {
                    stmt.setInt(ctd++, parametro.getInteger());
                }
            }
        }

        if (query) {
            ResultSet rs = stmt.executeQuery();
            return rs;
        } else {
            stmt.executeUpdate();
            return null;
        }
        
    }

    protected static void die(Exception e, String msg) {
        System.err.println(msg);
        System.err.println(e.getMessage());
        e.printStackTrace(System.err);
        System.exit(1);
    }
    
}
