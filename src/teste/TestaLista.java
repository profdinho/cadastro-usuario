package teste;

import dao.UsuarioDao;
import java.util.List;
import model.Usuario;

public class TestaLista {
    public static void main(String[] args) {
		
        UsuarioDao dao = new UsuarioDao();

        List<Usuario> usuarios = dao.getLista();

        System.out.println("Dados:\n");

        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Celular: " + usuario.getCelular());
            System.out.println("Email: " + usuario.getEmail());
        }
    }
}
