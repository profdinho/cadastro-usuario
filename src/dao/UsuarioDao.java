package dao;

import interfaces.TelaUsuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConexaoBD;
import model.Usuario;

public class UsuarioDao {
    private Connection conexao;
    
    public UsuarioDao() {
            this.conexao = new ConexaoBD().getConnection();
    }
    
    public void adicionaUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario " +
                    "(nome, celular, email, senha) " +
                    "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCelular());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean login(String usuario, String senha) {
        String sql = "SELECT * FROM Usuario WHERE nome = ? AND senha = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Usuario> getLista() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Usuario";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
            rs.close();
            ps.close();
            return usuarios;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Usuario getUsuario(String nome) {
        Usuario usuario = new Usuario();
        try {
            String sql = "SELECT * FROM Usuario WHERE nome LIKE ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setNome(rs.getString("nome"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            return usuario;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void alteraUsuario (Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, celular=?, email=?, senha=?" +
                    " WHERE id=?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCelular());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setLong(5, usuario.getId());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    public void removeUsuario (Usuario usuario) {
        try {
            PreparedStatement ps = conexao.prepareStatement("DELETE" +
                            "FROM usuario WHERE id=?");
            ps.setLong(1, usuario.getId());
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
