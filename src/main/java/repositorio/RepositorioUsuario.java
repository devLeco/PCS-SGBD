package repositorio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.util.Notification;

import modelo.Usuario;

@Repository
public class RepositorioUsuario extends RepositorioBase {

    private static final Log LOGGER = LogFactory.getLog(RepositorioUsuario.class);
	
	@Autowired
	private RepositorioBase repositorioBase;
	
	private List<Usuario> bd = new ArrayList<Usuario>();

	  /* PARA O USUARIO */
	 //extends RepositorioBase
	public Usuario buscarUsuario(String usuario, String senha) {
		// TODO buscar usuario no banco
		Usuario usuarioN = new Usuario();
		 try {
	            for (Usuario e : this.bd) {
	                if (e.getNomeUsuario().equals(usuario) && e.getSenha().equals(getHashMd5(senha)) ) {
	                    return e;
	                }
	            }
	            return usuarioN;

	        } catch (Exception e) {
	            return usuarioN;
	        }
	}
	
	    private void criarListaUsuarios() {
	        bd = new ArrayList<Usuario>();
	        bd.add(new Usuario(1L, "Ana", Date.valueOf("02/02/1997"), "abcde123!@#", "teste@gmail.com", "outroemail@gmail.com"));
	        bd.add(new Usuario(2L, "Lucas", Date.valueOf("02/02/1997"), "abcde123!@#", "teste@gmail.com", "outroemail@gmail.com"));
	        bd.add(new Usuario(3L, "Rodrigo", Date.valueOf("02/02/1997"), "abcde123!@#", "teste@gmail.com", "outroemail@gmail.com"));
	    }

	    public List<Usuario> list() {
	        this.criarListaUsuarios();
	        return this.bd;
	    }
	
	/* */ 
	    public static String getHashMd5(String value) {
	        MessageDigest md;
	        try {
	            md = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
	        return hash.toString(16);
	    }

		public void salvar(Usuario usuario) {
			repositorioBase.save(usuario); 
		}

    
  
    
	
	

}
