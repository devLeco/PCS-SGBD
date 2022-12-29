package dao;

import modelo.SessaoUsuario;
import modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import repositorio.RepositorioUsuario;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
public class ServicoUsuario {

    // Declare os servicos conforme o modelo abaixo
    private static Usuario usuario;
    @Autowired
    public RepositorioUsuario servicoUsuario;
    
    @Autowired
    private JavaMailSender mailSender;

    public static Usuario autenticarUsuario() {
		return usuario; //chamar metodo no banco
    	//buscar no banco de dados
    }

	public Usuario buscarUsuario(String usuario, String senha) {
		// TODO criar busca no banco para este usuario
		return servicoUsuario.buscarUsuario(usuario, senha);
	}
	
	// ??
	public SessaoUsuario usuarioAutenticado() {
		 Session sessao = Sessions.getCurrent();
		 SessaoUsuario sessaoUsuario = (SessaoUsuario) sessao.getAttribute("isLogado");
		 
		 if (sessaoUsuario == null) {
			 SessaoUsuario sessaoUsuario2 = new SessaoUsuario();
			 return sessaoUsuario2;
		}
		 else {	 
		 if (!sessaoUsuario.isLogado()) {
			sessaoUsuario = new SessaoUsuario();
			sessao.setAttribute("isLogado", false);
			sessao.setAttribute("usuario", sessaoUsuario);
		} 
		//senao ele possui autenticacao e esta logado
		 return sessaoUsuario;
		 }
	}

	public String recuperarSenha(String emailDestino) {
		try { 
			MimeMessage email = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(email, true);
			helper.setTo(emailDestino);

			helper.setSubject("Teste envio de e-mail");
			helper.setText("Somos o Delirando de Fome, olá!.");
			mailSender.send(email);

			return "Email enviado com sucesso! Verifique em sua caixa de email (e talvez no spam).";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Erro interno ao enviar e-mail.";
		}
			
	}

	public String recuperarEmailUsuario(String emailRecuperacao) {
		try { 
			MimeMessage email = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(email);
			helper.setTo(emailRecuperacao);

			helper.setSubject("Teste envio de e-mail");
			helper.setText("Somos o Delirando de Fome, olá!. Entre neste link para recuperar seu email com sua senha de acesso no site: ", true);
			helper.setText(" ");//enviar o link que leva a pessoa para recuperar a senha, se bater com o email de recuperação e senha, então damos acesso
			mailSender.send(email);

			return "Email enviado com sucesso! Verifique em sua caixa de email (e talvez no spam).";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Erro interno ao enviar e-mail.";
		}		
	}

	public void salvarUsuario(Usuario usuario2) {
		servicoUsuario.salvar(usuario2); 
		
	}

}
