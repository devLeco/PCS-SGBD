package view;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import dao.ServicoUsuario;
import modelo.SessaoUsuario;

//@Service("authService")
//@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AutenticacaoUsuario implements Initiator{

	private ServicoUsuario servicoUsuario;
	
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		SessaoUsuario sessao = servicoUsuario.usuarioAutenticado();
		if(sessao == null || !sessao.isLogado()){
			Executions.sendRedirect("/login.zul"); //nao ta logado
			return;
		}
}

	public ServicoUsuario getServicoUsuario() {
		return servicoUsuario;
	}

	public void setServicoUsuario(ServicoUsuario servicoUsuario) {
		this.servicoUsuario = servicoUsuario;
	}
}