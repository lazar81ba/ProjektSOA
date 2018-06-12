import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Application {

    public static void main(String args[]){
        postRequest();
        getRequest();
    }

    public static void getRequest(){
        try {
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials
                    = new UsernamePasswordCredentials("223322", "user");
            provider.setCredentials(AuthScope.ANY, credentials);


            HttpGet request = new HttpGet("http://localhost:8080/rest/resources/category/2/elements");

            HttpClient client = HttpClientBuilder.create()
                    .setDefaultCredentialsProvider(provider)
                    .build();

            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void postRequest(){
        try {

            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials
                    = new UsernamePasswordCredentials("223322", "user");
            provider.setCredentials(AuthScope.ANY, credentials);


            JSONObject json = new JSONObject();
            json.put("elementLabel", "Test3");
            json.put("firstParameterLabel", "Test3");
            json.put("firstParameterValue", "12");
            json.put("secondParameterLabel", "Test3");
            json.put("secondParameterValue", "12");
            json.put("thirdParameterLabel", "Test3");
            json.put("thirdParameterValue", "12");
            json.put("fourthParameterLabel", "Test3");
            json.put("fourthParameterValue", "12");
            StringEntity se = new StringEntity( json.toString());
            se.setContentType("application/json");
            HttpPost request = new HttpPost("http://localhost:8080/rest/resources/category/2/elements");
            request.setEntity(se);

            HttpClient client = HttpClientBuilder.create()
                    .setDefaultCredentialsProvider(provider)
                    .build();

            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
