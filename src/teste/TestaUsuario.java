package teste;

import dao.UsuarioDao;
import model.Usuario;

public class TestaUsuario {
    public static void main(String[] args) {
		
        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.getUsuarioByNome("Dinho");
        System.out.println("Usuario:\n");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Celular: " + usuario.getCelular());
        System.out.println("Email: " + usuario.getEmail());
    }
}
