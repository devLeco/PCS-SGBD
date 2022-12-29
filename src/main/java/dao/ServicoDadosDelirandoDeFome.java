package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import modelo.Estabelecimento;

@Service
public interface ServicoDadosDelirandoDeFome {
	List<Estabelecimento> pesquisarLocaisProximos(String localizacao);

    List<Estabelecimento> pesquisarLocaisProximos(String latitude, String longitude);
    
    List<Estabelecimento> pesquisar(String Estabelecimento, String localizacao);
    
    List<Estabelecimento> pesquisarPorAvaliacoes(String localizacao);

    List<Estabelecimento> pesquisarPorMenorPreco(String localizacao);

    Estabelecimento buscarEstabelecimento(String id);
    
    List<Estabelecimento> pesquisarLocaisMatch(String localizacao);
    
    List<Estabelecimento> pesquisarLocaisMatch(String latitude, String longitude);
    
    List<Estabelecimento> pesquisarEstabelecimentoPorAvaliacoes(String buscarEstabelecimento, String localizacao);
    
    List<Estabelecimento> listarEstabelecimentosPreDefinidos();
    
}
