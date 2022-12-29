package modelo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class EnderecoEstabelecimento extends Estabelecimento {

	 	private String id;
	 	
		private String endereco; //location
	    private String cidade; 
	    private String cep;
	    private String regiao;
	    private Map<String, String> location;
	   
	    private Object coordenadas; //address
	    private String distancia; //distance
	    private String rotaMapa;
	    
	    
	    public Map<String,  String> getLocation() {
	        return location;
	    }
	   
	    public String getEndereco() {
	        return endereco;
	    }
	    public void setEndereco(String endereco) {
	        this.endereco = endereco;
	    }
	    public String getCidade() {
	        return cidade;
	    }
	    public void setCidade(String cidade) {
	        this.cidade = cidade;
	    }
	    public String getCep() {
	        return cep;
	    }
	    public void setCep(String cep) {
	        this.cep = cep;
	    }
	    public String getRegiao() {
	        return regiao;
	    }
	    public void setRegiao(String regiao) {
	        this.regiao = regiao;
	    }
	    
	    public Object getCoordenadas() {
	        return coordenadas;
	    }
	    public void setCoordenadas(Object coordenadas) {
	        this.coordenadas = coordenadas;
	    }
	    public String getDistancia() {
	        return distancia;
	    }
	    public void setDistancia(String distancia) {
	        if(distancia.contains("m aprox")){
	            this.distancia = distancia;
	        }else{
	        Double dist = Double.parseDouble(distancia); //em km
	        DecimalFormat df = new DecimalFormat("####");
	        this.distancia =  df.format(dist) + " m aprox.";
	        }
	    }

	    public void setRotaMapa(String origemUsuario) {
            String destino = endereco + " " + cidade + " " + regiao;
        
	        try {
	            this.rotaMapa = "https://www.google.com/maps/dir/?api=1&origin=" + URLEncoder.encode(origemUsuario, StandardCharsets.UTF_8.toString()) + "&destination=" + URLEncoder.encode(destino, StandardCharsets.UTF_8.toString()) + "&travelmode=walking";
	
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    public String getRotaMapa() {
	        return rotaMapa;
	    }
}
