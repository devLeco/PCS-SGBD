package modelo;

public class SessaoUsuario {

	private boolean isLogado;
	private Usuario usuario;
	
	public SessaoUsuario(boolean isLogado) {
		this.isLogado = isLogado;
	}
	
	public SessaoUsuario() {
	}
	
	public SessaoUsuario(boolean isLogado, Usuario usuario) {
		this.isLogado = isLogado;
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isLogado() {
		return isLogado;
	}
	public void setLogado(boolean isLogado) {
		this.isLogado = isLogado;
	}
}
