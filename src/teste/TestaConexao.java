package teste;

import jdbc.ConexaoBD;
import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) throws SQLException {
        Connection conexao = new ConexaoBD().getConnection();
        System.out.println("Conexão estabelecida");
        conexao.close();
        
    }
}
