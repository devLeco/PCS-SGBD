package  repositorio;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

  /* Servico feito: pegar os dados da latitude e longitude e o raio entre eles e salvar num objeto */
        
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
                  Request request = new Request.Builder()
                    .url("https://api.yelp.com/v3/businesses/search?latitude=-22.753025&longitude=-43.454118&radius=410")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer imqQ1IKNBrDlyJXd6Av92TtPLYHw7J7DJgdJMFFTPmT1qJFj_X5S0spYWikh3qp-U_8_6CvQFsXmJaGW6r_mnkIX-8_rkE3j0Swpj7fmG5O8PAoTXxHMkkjLQoEDX3Yx")
                    .build();
                    Response response = client.newCall(request).execute();
                   
                    String res = response.body().string();
                    
                     JSONObject obj = new JSONObject(res);
                     JSONArray lojas = obj.getJSONArray("businesses");
                     int n = lojas.length();
                  
                     for (int i = 0; i < n; ++i) {
                     JSONObject p = lojas.getJSONObject(i);
                     System.out.println(p.get("id"));
                     }
                                      
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

}
