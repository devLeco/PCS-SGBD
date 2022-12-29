package dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Estabelecimento;

@Service
public class ServicoEstabelecimento {
    
    @SuppressWarnings("unused")
    private static final Log LOGGER = LogFactory.getLog(ServicoEstabelecimento.class);
    
    private ServicoDadosDelirandoDeFome serveDados = new YelpAPI(); 
    
    public List<Estabelecimento> pesquisarLocaisProximos(String latitude, String longitude) {
        return serveDados.pesquisarLocaisProximos(latitude, longitude);
    }
     
    public List<Estabelecimento> pesquisarLocaisProximos(String localizacao) {
        return serveDados.pesquisarLocaisProximos(localizacao);
    }
    
    public List<Estabelecimento> pesquisar(String estabelecimento, String localizacao) {
        return serveDados.pesquisar(estabelecimento, localizacao);
    }
 
    public List<Estabelecimento> pesquisarPorAvaliacoes(String localizacao) {
        return serveDados.pesquisarPorAvaliacoes(localizacao);
    }

    public List<Estabelecimento> pesquisarPorMenorPreco(String localizacao) {
        return serveDados.pesquisarPorMenorPreco(localizacao);
    }

    public Estabelecimento buscarEstabelecimento(String id) {
        return serveDados.buscarEstabelecimento(id);
    }
    
    public List<Estabelecimento> pesquisarLocalMatch(String latitude, String longitude) {
        return serveDados.pesquisarLocaisMatch(latitude, longitude);
    }
    
    public List<Estabelecimento> pesquisarLocalMatch(String localizacao) {
        return serveDados.pesquisarLocaisMatch(localizacao);
    }

    public List<Estabelecimento> pesquisarEstabelecimentoPorAvaliacoes(String buscarEstabelecimento, String localizacao) {
        return serveDados.pesquisarEstabelecimentoPorAvaliacoes( buscarEstabelecimento,  localizacao);
    }
    
    public List<Estabelecimento> listarEstabelecimentosPreDefinidos() {
    	return serveDados.listarEstabelecimentosPreDefinidos();
    }
}
