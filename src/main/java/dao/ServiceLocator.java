package dao;

public class ServiceLocator {

    // Declare os servicos conforme o modelo abaixo
    private static ServicoEstabelecimento servicoEstabelecimento;
    private static ServicoDadosDelirandoDeFome serve;
    private static ServicoUsuario servicoUsuario;

    public static ServicoEstabelecimento getServicoEstabelecimento() {
        return servicoEstabelecimento;
    }

    public static void setServicoEstabelecimento(ServicoEstabelecimento servicoEstabelecimento) {
        ServiceLocator.servicoEstabelecimento = servicoEstabelecimento;
    }

    public static ServicoDadosDelirandoDeFome getServe() {
        return serve;
    }

    public static void setServe(ServicoDadosDelirandoDeFome serve) {
        ServiceLocator.serve = serve;
    }

	public static ServicoUsuario getServicoUsuario() {
		return servicoUsuario;
	}

	public static void setServicoUsuario(ServicoUsuario servicoUsuario) {
		ServiceLocator.servicoUsuario = servicoUsuario;
	}

     
    
    
}
