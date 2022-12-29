package modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable=false)
	private String nomeUsuario;
	
	private Date dataNascimento;

	@Column(nullable=false)
	private String senha;
	
	@Lob
	private byte[] fotoPerfil;
	
	private String localizacao;
	
	@Column(nullable=false)
	private String email;
	
	private String emailRecuperacao;
	
	private String estabelecimentosFavoritos;
	
	public Usuario() {
		// pro Hibernate
	}
	
	public Usuario(Long id, String nomeUsuario, Date dataNascimento, String senha, String email, String emailRecuperacao) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.email = email;
		this.emailRecuperacao = emailRecuperacao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	 
	public byte[] getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(byte[] fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailRecuperação() {
		return emailRecuperacao;
	}
	public void setEmailRecuperação(String emailRecuperação) {
		this.emailRecuperacao = emailRecuperação;
	}
//	public List<String> getEstabelecimentosFavoritos() {
//		return estabelecimentosFavoritos;
//	}
//	public void setEstabelecimentosFavoritos(List<String> estabelecimentosFavoritos) {
//		this.estabelecimentosFavoritos = estabelecimentosFavoritos;
//	}

	public String getEstabelecimentosFavoritos() {
		return estabelecimentosFavoritos;
	}

	public void setEstabelecimentosFavoritos(String estabelecimentosFavoritos) {
		this.estabelecimentosFavoritos = estabelecimentosFavoritos;
	}
}
