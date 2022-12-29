package modelo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Estabelecimento implements Comparable<Estabelecimento>{

    private String id;
    private String alias;
    
    private List<HorarioEstabelecimento> horarios;
    private EnderecoEstabelecimento enderecoEstabelecimento;
    
    private String nomeEstabelecimento; //name
    private String urlFoto; //image_url
    private Double estrela; //rating
    private String isClosed; //is_closed
    private String urlSite;//url
    private String preco; //price
    private String telefone; //display_phone
    private String tipoFiltroSelecionado;
    
    private Integer avaliacoesUsuariosQTD; //review_count
    private List<String> categorias; //categories
    private List<String> fotosEstabelecimento;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }
    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }
    
    public String getIsClosed() {
        return isClosed;
    }
    
    public void setIsClosed(String isClosed) {
       if("false".equals(isClosed)){
           this.isClosed = "Aberto";
       }else{
           this.isClosed = "Fechado";
       }
    }
    
    public String getUrlFoto() {
        return urlFoto;
    }
    public void setUrlFoto(String urlFoto) {
        if (StringUtils.isEmpty(urlFoto)) {
            this.urlFoto = "https://www.trt21.jus.br/sites/default/files/default_images/sem_foto%20%281%29.png";
        }else{
            this.urlFoto = urlFoto;
        }
    }
    public Double getEstrela() {
        return estrela;
    }
    public void setEstrela(Double rating) {
        this.estrela = rating;
    }
      
    public String getUrlSite() {
        return urlSite;
    }
    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }
    
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        if (StringUtils.isEmpty(preco)) {
            this.preco = "Não informado";
        }else{
            this.preco = preco;
        }
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        if(StringUtils.isEmpty(telefone)){
            this.telefone = "Não informado";
        }
        else{
            this.telefone = telefone;
        }
    }
    public String getTipoFiltroSelecionado() {
        return tipoFiltroSelecionado;
    }
    public void setTipoFiltroSelecionado(String tipoFiltroSelecionado) {
        this.tipoFiltroSelecionado = tipoFiltroSelecionado;
    }
    public Integer getAvaliacoesUsuariosQTD() {
        return avaliacoesUsuariosQTD;
    }
    public void setAvaliacoesUsuariosQTD(Integer avaliacoesUsuariosQTD) {
        this.avaliacoesUsuariosQTD = avaliacoesUsuariosQTD;
    }
     
    public List<HorarioEstabelecimento> getHorarios() {
        return horarios;
    }
    public void setHorarios(List<HorarioEstabelecimento> horarios) {
        this.horarios = horarios;
    }
    public EnderecoEstabelecimento getEnderecoEstabelecimento() {
		return enderecoEstabelecimento;
	}
	public void setEnderecoEstabelecimento(EnderecoEstabelecimento enderecoEstabelecimento) {
		this.enderecoEstabelecimento = enderecoEstabelecimento;
	}
	public List<String> getCategorias() {
        return categorias;
    }
    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
    
    public void setFotosEstabelecimento(List<String> fotosEstabelecimento) {
        this.fotosEstabelecimento = fotosEstabelecimento;
    }
    
    public List<String> getFotosEstabelecimento() {
        return fotosEstabelecimento;
    }
    @Override
    public int compareTo(Estabelecimento o) {
        if (this.estrela > o.estrela) {
            return -1;
        }
        if (this.estrela < o.estrela) {
            return 1;
        }
        return 0;
    }    
    
    
    
    
}