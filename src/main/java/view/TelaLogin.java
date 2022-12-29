package view;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.Window;

import dao.ServicoUsuario;
import modelo.SessaoUsuario;
import modelo.Usuario;

public class TelaLogin extends SelectorComposer<Component>{
 
	private static final long serialVersionUID = 1L;
	//vms
	private String nomeUsuario; // campo text referente ao usuario e login
	private String senha;

	@WireVariable
    private ServicoUsuario servicoUsuario;
	// atributos
	private String validarLogin;
	private String email; // campo text referente ao usuario e login
	private String emailRecuperacao;
	
	private Component view;
	
	@Wire
	private Window windowSenha;
	
	@Wire
	private Window windowUsu;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
		this.view = view;
    }

    @Command
    @NotifyChange("validarLogin")
    public void autenticarLogin() {
    	 if(StringUtils.isBlank(nomeUsuario)){ //ou se for diferente do banco---- || servicoLogin.
             Notification.show("Usuário inválido. Por favor, tente novamente ou escolha a opção 'Esqueci a senha'. ", "error",
       				null, "after_start", 2000, false);
         }
    	 else if(StringUtils.isBlank(senha)){
             Notification.show("Senha inválida. Por favor, tente novamente ou escolha a opção 'Esqueci a senha'.", "error",
      				null, "after_start", 2000, false);
         }
    	 else if(StringUtils.isBlank(nomeUsuario) && StringUtils.isBlank(senha)) {
    		 Notification.show("Usuário e senha inválidos. Por favor, tente novamente ou escolha a opção 'Esqueci a senha'.", "error",
     				null, "after_start", 2000, false);
    	 }
    	 else {
    		 if(nomeUsuario.contains("@")) {
    			 //eh email;
    		 }
			//procura usuario no banco e resgata o usuario
    		 Usuario usuario = servicoUsuario.buscarUsuario(nomeUsuario, getHashMd5(senha));
    		 if (usuario.equals(null)) {
//				validarLogin = "";
				Notification.show("Usuário não existente.", "error",
        				null, "after_start", 2000, false);
				return;
			}
    		 Session sessao = Sessions.getCurrent();
    		 SessaoUsuario sessaoUsuario = new SessaoUsuario(false, usuario); //ainda nao ta logado
    		 if(sessao.getAttribute("isLogado") == null) {//????
				 sessao.setAttribute("isLogado", true);
				 sessao.setAttribute("usuario", sessaoUsuario);
				 //colocamos na sessao
    		 }
    		 //direcionamos para outra pagina
    		 Executions.sendRedirect("/delirandodefome.zul");
		 }
	}//tem q criar um mapa de usuario????
    
    public void logout() {
		Session sessao = Sessions.getCurrent();
		sessao.removeAttribute("usuario");
		sessao.removeAttribute("isLogado");
		Executions.sendRedirect("/delirandodefome.zul"); //tela inicial
	}

    
    @NotifyChange("validarLogin")
    @Command
    public void abrirRecSenha(Event e) {
    	email = "";
    	this.windowSenha.doModal();
	}
    
    @NotifyChange("validarLogin")
    @Command
    public void abrirRecUsuario(Event e) {
    	emailRecuperacao = "";
    	this.windowUsu.doModal();
	}
    
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
    
	public String getValidarLogin() {
		return validarLogin;
	}

	public void setValidarLogin(String validarLogin) {
		this.validarLogin = validarLogin;
	}

	public String getnomeUsuario() {
		return nomeUsuario;
	}

	public void setnomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	@NotifyChange("validarLogin")
	@Command
    public void recuperarSenha() {
		if (StringUtils.isBlank(email)) {
			validarLogin = "Insira um email válido.";
		}else {
			servicoUsuario.recuperarSenha(email);
		}
	}
	 
	//outra página?
	@NotifyChange("validarLogin")
	public void recuperarEmailUsuario() {
		// TODO inserir email de recup
		//email = email de recuperacao
		if (StringUtils.isBlank(emailRecuperacao)) {
			validarLogin = "Insira um email válido.";
		}else {
			servicoUsuario.recuperarEmailUsuario(emailRecuperacao);
		}
	}
	
	public String getEmailRecuperacao() {
		return emailRecuperacao;
	}

	public void setEmailRecuperacao(String emailRecuperacao) {
		this.emailRecuperacao = emailRecuperacao;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
