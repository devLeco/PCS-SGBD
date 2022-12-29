package view;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import dao.ServicoEstabelecimento;
import modelo.Estabelecimento;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaEstabelecimentoSelecionado implements Serializable{

    private static final long serialVersionUID = 1L;
//    private static final String URLEstabelecimento = "http://localhost:8080/delirando-de-fome/telaEstabelecimentoSelecionado.zul";

    // vms
    private Estabelecimento estabelecimento;

    @WireVariable
    private ServicoEstabelecimento servicoEstabelecimento;

    @Init
    public void init() {
        String parametros = Executions.getCurrent().getParameter(SafeParams.DEFAULT_DATA_PARAM);
        if (parametros != null) {
            String idEstabelecimento = new SafeParams(parametros).get("idEstabelecimento");
            String origem = new SafeParams(parametros).get("localizacaoUsuario");
            String precoEstabelecimento = new SafeParams(parametros).get("precoEstabelecimento");
            String distanciaEstabelecimento = new SafeParams(parametros).get("distanciaEstabelecimento");
            String phoneEstabelecimento = new SafeParams(parametros).get("phoneEstabelecimento");
            estabelecimento = servicoEstabelecimento.buscarEstabelecimento(idEstabelecimento);
            estabelecimento.setPreco(precoEstabelecimento);
            estabelecimento.setTelefone(phoneEstabelecimento);
            estabelecimento.getEnderecoEstabelecimento().setRotaMapa(origem);
            estabelecimento.getEnderecoEstabelecimento().setDistancia(distanciaEstabelecimento);
            return;
        }
        
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public ServicoEstabelecimento getServicoEstabelecimento() {
        return servicoEstabelecimento;
    }

    public void setServicoEstabelecimento(ServicoEstabelecimento servicoEstabelecimento) {
        this.servicoEstabelecimento = servicoEstabelecimento;
    }

}
