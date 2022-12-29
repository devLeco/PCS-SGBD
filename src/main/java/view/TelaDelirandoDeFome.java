/*
 * utf-8
 */
package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import dao.ServicoEstabelecimento;
import modelo.EnderecoEstabelecimento;
import modelo.Estabelecimento;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaDelirandoDeFome implements Serializable {
 
	//TelaPesquisarEstabelecimento
	private static final long serialVersionUID = 1L;
//   private static final String UREstabelecimento = "http://localhost:8080/delirandodefome-TT/telaEstabelecimentoSelecionado2.zul";
   private static final String UREstabelecimento = "http://localhost:8080/delirando-de-fome/telaEstabelecimentoSelecionado2.zul";
   
	    // vms
	    private String localizacao;
	    private String buscarEstabelecimento;
	    private List<String> tiposFiltros = new ArrayList<String>();


	    // atributos
	    private String nomeEstabelecimento;
	    private String urlFoto;
	    private String estrela;
	    private String isClosed;
	    private String endereco;
	    private String urlSite;
	    private String distancia;
	    private String preco;
	    private String telefone;
	    private EnderecoEstabelecimento enderecoEstabelecimento;

	    private String tipoFiltroSelecionado;

	    private List<Estabelecimento> estabelecimentos;
	    public Map<String, String> coordenadas = new HashMap<String, String>();
	    public Map<String, String> map = new HashMap<String, String>();

	    @Wire
	    private Div locationContainer;

	    
	    @WireVariable
	    private ServicoEstabelecimento servicoEstabelecimento;

	    @Init
	    public void init() {
	        tiposFiltros.add("Distância");
	        tiposFiltros.add("Melhores Avaliados");

	        String parametros = Executions.getCurrent().getParameter(SafeParams.DEFAULT_DATA_PARAM);
	        if (parametros != null) {
	            String local = new SafeParams(parametros).get("local");

	            if (StringUtils.isEmpty(local)) {
	                Map<String, String> coor = new SafeParams(parametros).get("coordenadas");
	                estabelecimentos = servicoEstabelecimento.pesquisarLocalMatch(coor.get("latitude"), coor.get("longitude"));

	                this.setLocalizacao(coor.get("latitude").concat(", " + coor.get("longitude")));
	                if (coor.get("latitude") == null) {
						estabelecimentos = servicoEstabelecimento.listarEstabelecimentosPreDefinidos();
					}
	            
	            }
	            else {
	                estabelecimentos = servicoEstabelecimento.pesquisarLocalMatch(local);
	                this.setLocalizacao(local);
	            }
	        }
	    }

	    public List<Estabelecimento> getEstabelecimentos() {
	        return estabelecimentos;
	    }

	    public String getLocalizacao() {
	        return localizacao;
	    }

	    public void setLocalizacao(String localizacao) {
	        this.localizacao = localizacao;
	    }

	    public String getBuscarEstabelecimento() {
	        return buscarEstabelecimento;
	    }

	    public void setBuscarEstabelecimento(String buscarEstabelecimento) {
	        this.buscarEstabelecimento = buscarEstabelecimento;
	    }

	    public String getTipoFiltroSelecionado() {
	        return tipoFiltroSelecionado;
	    }

	    @NotifyChange({"estabelecimentos", "tipoFiltroSelecionado"})
	    public void setTipoFiltroSelecionado(String tipoFiltroSelecionado) {
	        this.tipoFiltroSelecionado = tipoFiltroSelecionado;
	        if ("Melhores Avaliados".equals(tipoFiltroSelecionado)) {
	            if(!(StringUtils.isEmpty(this.getBuscarEstabelecimento()) && StringUtils.isEmpty(this.getLocalizacao()))){
	                estabelecimentos = servicoEstabelecimento.pesquisarEstabelecimentoPorAvaliacoes(buscarEstabelecimento, localizacao);
	            }
	            if (StringUtils.isEmpty(localizacao) && !StringUtils.isEmpty(this.getBuscarEstabelecimento())) {
	                estabelecimentos = servicoEstabelecimento.pesquisarEstabelecimentoPorAvaliacoes(buscarEstabelecimento, "centro rio de janeiro");
	            } 
	            if (StringUtils.isEmpty(this.getBuscarEstabelecimento())) {
	                estabelecimentos = servicoEstabelecimento.pesquisarPorAvaliacoes(localizacao);
	            }
	            if (StringUtils.isEmpty(localizacao) && StringUtils.isEmpty(buscarEstabelecimento)) {
	                estabelecimentos = servicoEstabelecimento.pesquisarEstabelecimentoPorAvaliacoes("food", "centro rio de janeiro");
	            }
	        } 
	        if ("Distância".equals(tipoFiltroSelecionado)) {
	            if(!(StringUtils.isEmpty(this.getBuscarEstabelecimento()) && StringUtils.isEmpty(this.getLocalizacao()))){
	                estabelecimentos = servicoEstabelecimento.pesquisar(buscarEstabelecimento, localizacao);
	            }
	            if (StringUtils.isEmpty(this.getLocalizacao()) && !StringUtils.isEmpty(this.getBuscarEstabelecimento())) {
	                estabelecimentos = servicoEstabelecimento.pesquisar(buscarEstabelecimento, "centro rio de janeiro");
	            } 
	            if (StringUtils.isEmpty(this.getBuscarEstabelecimento())) {
	                estabelecimentos = servicoEstabelecimento.pesquisarLocaisProximos(localizacao);
	            }
	            if (StringUtils.isEmpty(localizacao) && StringUtils.isEmpty(buscarEstabelecimento)) {
	                estabelecimentos = servicoEstabelecimento.pesquisar("food", "centro rio de janeiro");
	            }
	        }      
	    }

	    public String getNomeEstabelecimento() {
	        return nomeEstabelecimento;
	    }

	    public void setNomeEstabelecimento(String nomeEstabelecimento) {
	        this.nomeEstabelecimento = nomeEstabelecimento;
	    }

	    public String getUrlFoto() {
	        return urlFoto;
	    }

	    public void setUrlFoto(String urlFoto) {
	            this.urlFoto = urlFoto;
	    }

	    public String getEstrela() {
	        return estrela;
	    }

	    public String getIsClosed() {
	        return isClosed;
	    }

	    public void setIsClosed(String isClosed) {
	        this.isClosed = isClosed;
	    }

	    public String getEndereco() {
	        return endereco;
	    }

	    public void setEndereco(String endereco) {
	        this.endereco = endereco;
	    }

	    public String getUrlSite() {
	        return urlSite;
	    }

	    public void setUrlSite(String urlSite) {
	        this.urlSite = urlSite;
	    }

	    public String getDistancia() {
	        return distancia;
	    }

	    public void setDistancia(String distancia) {
	        this.distancia = distancia;
	    }

	    public String getPreco() {
	        return preco;
	    }

	    public void setPreco(String preco) {
	        this.preco = preco;
	    }

	    public String getTelefone() {
	        return telefone;
	    }

	    public void setTelefone(String telefone) {
	        this.telefone = telefone;
	    }

	    public ServicoEstabelecimento getServicoEstabelecimentos() {
	        return servicoEstabelecimento;
	    }

	    public void setServicoEstabelecimentos(ServicoEstabelecimento servicoEstabelecimentos) {
	        this.servicoEstabelecimento = servicoEstabelecimentos;
	    }

	    public List<String> getTiposFiltros() {
	        return tiposFiltros;
	    }

	    @Command
	    @NotifyChange("estabelecimentos")
	    public void pesquisarSelecionado() {
	        if(StringUtils.isEmpty(localizacao)){
	            estabelecimentos = servicoEstabelecimento.pesquisarLocaisProximos("centro rio de janeiro");
	        }
	        if(StringUtils.isEmpty(buscarEstabelecimento)){
	            estabelecimentos = servicoEstabelecimento.pesquisarLocaisProximos(localizacao);
	        }  
	        if(StringUtils.isEmpty(localizacao) && StringUtils.isEmpty(buscarEstabelecimento)){
	            estabelecimentos = servicoEstabelecimento.pesquisar("food", "centro rio de janeiro");
	        }
	        if(!StringUtils.isEmpty(localizacao)){
	            estabelecimentos = servicoEstabelecimento.pesquisarLocaisProximos(localizacao);
	        }
	    }

	    @Command
	    public void mostrarEstabelecimentosProximas() {
	        estabelecimentos = servicoEstabelecimento.pesquisarLocaisProximos(localizacao);
	    }

	    @Command
	    @NotifyChange("estabelecimentos")
	    public void buscarEstabelecimento() {
	        if(StringUtils.isEmpty(localizacao)){
	            estabelecimentos = servicoEstabelecimento.pesquisar(buscarEstabelecimento, "centro rio de janeiro");//testar
	        }
	        if(StringUtils.isEmpty(buscarEstabelecimento)){
	            estabelecimentos = servicoEstabelecimento.pesquisarLocaisProximos(localizacao);
	        } 
	        if(StringUtils.isEmpty(localizacao) && StringUtils.isEmpty(buscarEstabelecimento)){
	            estabelecimentos = servicoEstabelecimento.pesquisar("food", "centro rio de janeiro");
	        }
	        else{
	            estabelecimentos = servicoEstabelecimento.pesquisar(buscarEstabelecimento, localizacao);
	        }
	    }

	    @Command
	    public void verDetalhes(@BindingParam("estabelecimento") Estabelecimento estabelecimento) {
	        SafeParams params = new SafeParams("idEstabelecimento", estabelecimento.getId());
	        params.put("localizacaoUsuario", localizacao);
	        params.put("precoEstabelecimento", estabelecimento.getPreco());
	        params.put("distanciaEstabelecimento", estabelecimento.getEnderecoEstabelecimento().getDistancia());
	        params.put("phoneEstabelecimento", estabelecimento.getTelefone());
	        Executions.sendRedirect(UREstabelecimento + "?" + params);
	    }

	public EnderecoEstabelecimento getEnderecoEstab() {
		return enderecoEstabelecimento;
	}

	public void setEnderecoEstabelecimento(EnderecoEstabelecimento enderecoEstabelecimento) {
		this.enderecoEstabelecimento = enderecoEstabelecimento;
	}
	
}
