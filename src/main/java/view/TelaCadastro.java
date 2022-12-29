package view;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
//import org.springframework.mail.SimpleMailMessage;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Notification;

import dao.ServicoUsuario;
import modelo.Usuario;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaCadastro extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	//vms
	
	private String email; 
	private String emailRecuperacao;
	private String nomeUsuario;
	private Date dataNascimento;
	private String senha;
	
	
	// atributos

	@WireVariable
    private ServicoUsuario servicoUsuario;
	
	private String validarCadastro;
	
	public static final String SENHA_REGEX = "(?=.*[}{,.^?~=+\\-_\\/*\\-+.\\|])(?=.*[a-zA-Z])(?=.*[0-9]).{8,}";
	public static final String USER_REGEX =".{8,}"; // no minimo 4 letras case sensitive
	
    @Init
    public void init() {
    }

    @Command
    @NotifyChange("validarCadastro")
    public void manterNovoUsuario() {
    	if (validarUsuario()) {
    		Usuario usuario = new Usuario();
    		usuario.setEmail(email);
    		usuario.setEmailRecuperação(emailRecuperacao);
    		usuario.setNomeUsuario(nomeUsuario);
    		usuario.setSenha(getHashMd5(senha));
    		usuario.setDataNascimento(dataNascimento);
    		servicoUsuario.salvarUsuario(usuario);
    		boolean esperaTempo = true; 
    		//colocar swal de sucesso 
    			Notification.show("Cadastrado com sucesso! Você será redirecionado...", "info",
        				null, "after_start", 2000, false);
    		if(esperaTempo) {	
        		//redirecionar automaticamente a pessoa
        		Executions.sendRedirect("/delirandodefome.zul");
    		}
		} 
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

    @NotifyChange("validarCadastro")
	private boolean validarUsuario() {
		boolean valida = false;
//		boolean matcherSenha = SENHA_REGEX.matches(senha);
		validarCadastro = "";
		if (StringUtils.isBlank(email)) {
			validarCadastro = "Email inválido.";
		}
		else if (StringUtils.isBlank(nomeUsuario) || nomeUsuario.length() < 8) {
			validarCadastro = "Nome do usuário não pode ser menor que 8 caracteres e não pode possuir espaço.";
		}
		else if (StringUtils.isBlank(senha) || senha.length() < 8) {
			validarCadastro = "Senha inválida. Ela deve conter pelo menos 1 letra, 1 número e 1 caracter especial.";
		}
		else {
			valida = true;
		}
		return valida;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ServicoUsuario getServicoUsuario() {
		return servicoUsuario;
	}

	public void setServicoUsuario(ServicoUsuario servicoUsuario) {
		this.servicoUsuario = servicoUsuario;
	}

	public String getValidarCadastro() {
		return validarCadastro;
	}

	public void setValidarCadastro(String validarCadastro) {
		this.validarCadastro= validarCadastro;
	}

	public String getEmailRecuperacao() {
		return emailRecuperacao;
	}

	public void setEmailRecuperacao(String emailRecuperacao) {
		this.emailRecuperacao = emailRecuperacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
