package dao;

import modelo.EnderecoEstabelecimento;
import modelo.Estabelecimento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;


public class YelpAPI implements ServicoDadosDelirandoDeFome {
    private static final String API_KEY = "Bearer imqQ1IKNBrDlyJXd6Av92TtPLYHw7J7DJgdJMFFTPmT1qJFj_X5S0spYWikh3qp-U_8_6CvQFsXmJaGW6r_mnkIX-8_rkE3j0Swpj7fmG5O8PAoTXxHMkkjLQoEDX3Yx";
    private static final String URL = "https://api.yelp.com/v3/businesses/search?";
    private static final String URL_ID = "https://api.yelp.com/v3/businesses/";


    public List<Estabelecimento> pesquisarLocaisProximos(String localizacao) {
        // location
        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "location=" + localizacao + "&sort_by=distance")
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                // estabelecimento.setCategorias(p.get("categories"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            return estabelecimentosEncontrados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

    public List<Estabelecimento> pesquisarLocaisProximos(String latitude, String longitude) {

        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "latitude=" + latitude + "&longitude=" + longitude + "&sort_by=distance")
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                // Estabelecimento.setCategorias(p.get("categories"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            return estabelecimentosEncontrados;

        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

    @Override
    public List<Estabelecimento> pesquisar(String nomeEstabelecimento, String localizacao) {
        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "term=" + nomeEstabelecimento + "&sort_by=distance&location=" + localizacao)
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                // estabelecimento.setCategorias(p.get("categories"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            
            return estabelecimentosEncontrados;

        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

    @Override
    public List<Estabelecimento> pesquisarPorAvaliacoes(String localizacao) {
        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "location=" + localizacao)
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
               
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }

            for (int i = 0; i < estabelecimentosEncontrados.size(); i++) {
                Collections.sort(estabelecimentosEncontrados);
            }
            return estabelecimentosEncontrados;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return estabelecimentosEncontrados;
    }

    @Override
    public List<Estabelecimento> pesquisarPorMenorPreco(String localizacao) {
        // organizar menor valor

        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "sort_by=distance&location=" + localizacao)
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            return estabelecimentosEncontrados;

        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

    @Override
    public Estabelecimento buscarEstabelecimento(String id) {
        Estabelecimento estabelecimento = new Estabelecimento();
        EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
        List<String> categorias = new ArrayList<String>();
        List<String> fotosEstabelecimento = new ArrayList<String>();
        

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL_ID + id)
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();
            JSONObject p = new JSONObject(res);
            estabelecimento.setId(p.optString("id"));
            estabelecimento.setAlias(p.optString("alias"));
            estabelecimento.setNomeEstabelecimento(p.optString("name"));

            enderecoEstabelecimento.setCoordenadas(p.optJSONObject("coordinates"));
            estabelecimento.setEstrela(p.optDouble("rating"));
            estabelecimento.setIsClosed(p.optString("is_closed"));

            estabelecimento.setTelefone(p.optString("display_phone"));
            estabelecimento.setUrlFoto(p.optString("image_url"));
            estabelecimento.setUrlSite(p.optString("url"));
            estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

            String res1 = p.optString("location");
            JSONObject obj1 = new JSONObject(res1);
            enderecoEstabelecimento.setEndereco(obj1.optString("address1") + obj1.optString("address2"));
            enderecoEstabelecimento.setCidade(obj1.optString("city"));
            enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
            enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

            String res3 = p.optString("categories");
            for (int m = 0; m < res3.length(); m++) {
                res3 = res3.replace("[", " ").replace("]", " ");
            }
            JSONObject obj3 = new JSONObject(res3);
            for (int k = 0; k < obj3.length(); ++k) {
                categorias.add(obj3.optString("alias"));
            }
            estabelecimento.setCategorias(categorias);


            String res4 = p.optString("photos");
            for (int m = 0; m < res3.length(); m++) {
                res4 = res4.replace("[", "").replace("]", "").replace("\"", "");
            }
            String[] result = res4.split(",");
            for (int k = 0; k < result.length; k++) {
                fotosEstabelecimento.add(result[k]);
            }
            estabelecimento.setFotosEstabelecimento(fotosEstabelecimento);
            
            estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
            return estabelecimento;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return estabelecimento;
    }
    
    public List<Estabelecimento> pesquisarLocaisMatch(String localizacao) {
        // location
        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "location=" + localizacao + "&sort_by=best_match&radius=3000")
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                // estabelecimento.setCategorias(p.get("categories"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            return estabelecimentosEncontrados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

    public List<Estabelecimento> pesquisarLocaisMatch(String latitude, String longitude) {

        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "latitude=" + latitude + "&longitude=" + longitude + "&sort_by=best_match&radius=3000")
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                // estabelecimento.setCategorias(p.get("categories"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            return estabelecimentosEncontrados;

        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

    @Override
    public List<Estabelecimento> pesquisarEstabelecimentoPorAvaliacoes(String buscarEstabelecimento, String localizacao) {
        List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL + "term=" + buscarEstabelecimento + "&location=" + localizacao)
                    .method("GET", null)
                    .addHeader("Authorization", API_KEY)
                    .build();
            Response response = client.newCall(request).execute();

            String res = response.body().string();

            JSONObject obj = new JSONObject(res);
            JSONArray estabelecimentos = obj.getJSONArray("businesses");
            int n = estabelecimentos.length();

            for (int i = 0; i < n; ++i) {
                JSONObject p = estabelecimentos.getJSONObject(i);
                Estabelecimento estabelecimento = new Estabelecimento();
                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
                
                estabelecimento.setId(p.optString("id"));
                estabelecimento.setAlias(p.optString("alias"));
                estabelecimento.setNomeEstabelecimento(p.optString("name"));
                // estabelecimento.setCategorias(p.get("categories"));
                enderecoEstabelecimento.setDistancia(p.optString("distance"));
                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
                estabelecimento.setEstrela(p.optDouble("rating"));
                estabelecimento.setIsClosed(p.optString("is_closed"));
                estabelecimento.setPreco(p.optString("price"));

                estabelecimento.setTelefone(p.optString("display_phone"));
                estabelecimento.setUrlFoto(p.optString("image_url"));
                estabelecimento.setUrlSite(p.optString("url"));
                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

                String res1 = p.get("location").toString();
                JSONObject obj1 = new JSONObject(res1);
                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
                enderecoEstabelecimento.setCidade(obj1.optString("city"));
                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
                estabelecimentosEncontrados.add(estabelecimento);
            }
            
            for (int i = 0; i < estabelecimentosEncontrados.size(); i++) {
                Collections.sort(estabelecimentosEncontrados);
            }
            
            return estabelecimentosEncontrados;

        } catch (IOException e) {
            e.printStackTrace();
        }
        estabelecimentosEncontrados = null;
        return estabelecimentosEncontrados;
    }

	@Override
	public List<Estabelecimento> listarEstabelecimentosPreDefinidos() {
		 List<Estabelecimento> estabelecimentosEncontrados = new ArrayList<Estabelecimento>();

	        try {
	            OkHttpClient client = new OkHttpClient().newBuilder()
	                    .build();
	            Request request = new Request.Builder()
	                    .url(URL + "term=food&location=rio de janeiro")
	                    .method("GET", null)
	                    .addHeader("Authorization", API_KEY)
	                    .build();
	            Response response = client.newCall(request).execute();

	            String res = response.body().string();

	            JSONObject obj = new JSONObject(res);
	            JSONArray estabelecimentos = obj.getJSONArray("businesses");
	            int n = estabelecimentos.length();

	            for (int i = 0; i < n; ++i) {
	                JSONObject p = estabelecimentos.getJSONObject(i);
	                Estabelecimento estabelecimento = new Estabelecimento();
	                EnderecoEstabelecimento enderecoEstabelecimento = new EnderecoEstabelecimento();
	                
	                estabelecimento.setId(p.optString("id"));
	                estabelecimento.setAlias(p.optString("alias"));
	                estabelecimento.setNomeEstabelecimento(p.optString("name"));
	                // estabelecimento.setCategorias(p.get("categories"));
	                enderecoEstabelecimento.setDistancia(p.optString("distance"));
	                enderecoEstabelecimento.setCoordenadas(p.get("coordinates"));
	                estabelecimento.setEstrela(p.optDouble("rating"));
	                estabelecimento.setIsClosed(p.optString("is_closed"));
	                estabelecimento.setPreco(p.optString("price"));

	                estabelecimento.setTelefone(p.optString("display_phone"));
	                estabelecimento.setUrlFoto(p.optString("image_url"));
	                estabelecimento.setUrlSite(p.optString("url"));
	                estabelecimento.setAvaliacoesUsuariosQTD(p.optInt("review_count"));

	                String res1 = p.get("location").toString();
	                JSONObject obj1 = new JSONObject(res1);
	                enderecoEstabelecimento.setEndereco(obj1.optString("address1"));
	                enderecoEstabelecimento.setCidade(obj1.optString("city"));
	                enderecoEstabelecimento.setCep(obj1.optString("zip_code"));
	                enderecoEstabelecimento.setRegiao(obj1.optString("country") + " - " + obj1.optString("state"));

	                estabelecimento.setEnderecoEstabelecimento(enderecoEstabelecimento);
	                estabelecimentosEncontrados.add(estabelecimento);
	            }
	            
	            for (int i = 0; i < estabelecimentosEncontrados.size(); i++) {
	                Collections.sort(estabelecimentosEncontrados);
	            }
	            
	            return estabelecimentosEncontrados;

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        estabelecimentosEncontrados = null;
	        return estabelecimentosEncontrados;
	    }

}
