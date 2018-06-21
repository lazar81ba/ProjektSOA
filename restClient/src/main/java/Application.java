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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Application {

    public static void main(String args[]) throws IOException {
        while(true) {
            System.out.println();
            System.out.println();
            System.out.println("1. Get Categories");
            System.out.println("2. Create element");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();

            handleMenu(s);

        }
    }

    public static void handleMenu(String command) {
        if (command.equals("1"))
            getCategoryRequest();
        else if (command.equals("2"))
            postRequest();

    }



    public static void getCategoryRequest(){
        try {
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials
                    = new UsernamePasswordCredentials("223322", "user");
            provider.setCredentials(AuthScope.ANY, credentials);


            HttpGet request = new HttpGet("http://localhost:8080/rest/resources/category/");

            HttpClient client = HttpClientBuilder.create()
                    .setDefaultCredentialsProvider(provider)
                    .build();

            HttpResponse response = client.execute(request);
            JSONArray json = new JSONArray(EntityUtils.toString(response.getEntity()));
            System.out.println("Categories:");
            for (int i = 0; i < json.length(); ++i) {
                System.out.println();
                JSONObject category = json.getJSONObject(i);
                System.out.println("Category: " + category.getString("categoryLabel"));
                System.out.println("ID: "+ category.getLong("id"));
                System.out.println(category.getString("parameterLabel")+" = " + category.getInt("parameterValue"));
                System.out.println();
                System.out.println();
            }
            System.out.println("1. Show elements");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            if (s.equals("1"))
                System.out.println("Write category ID");
                s = br.readLine();
                getElementsRequest(Long.parseLong(s));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getElementsRequest(Long categoryID) throws IOException, JSONException {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials("223322", "user");
        provider.setCredentials(AuthScope.ANY, credentials);


        HttpGet request = new HttpGet("http://localhost:8080/rest/resources/category/"+categoryID+"/elements");

        HttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        HttpResponse response = client.execute(request);
        JSONArray json = new JSONArray(EntityUtils.toString(response.getEntity()));

        System.out.println("Elements:");
        for (int i = 0; i < json.length(); ++i) {
            System.out.println();
            JSONObject element = json.getJSONObject(i);
            System.out.println("Element: " + element.getString("elementLabel"));
            System.out.println("ID: "+ element.getLong("id"));
            System.out.println(element.getString("firstParameterLabel")+" = " + element.getString("firstParameterValue"));
            System.out.println(element.getString("secondParameterLabel")+" = " + element.getInt("secondParameterValue"));
            System.out.println(element.getString("thirdParameterLabel")+" = " + element.getInt("thirdParameterValue"));
            System.out.println(element.getString("fourthParameterLabel")+" = " + element.getInt("fourthParameterValue"));
            System.out.println();
            System.out.println();
        }
    }

    public static void postRequest(){
        try {

            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials
                    = new UsernamePasswordCredentials("223322", "user");
            provider.setCredentials(AuthScope.ANY, credentials);


            JSONObject json = new JSONObject();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write element label");
            String s = br.readLine();
            System.out.println("Write category ID");
            String id = br.readLine();
            json.put("elementLabel", s);
            System.out.println("Write first parameter label and value in [label]:[value] format");
            s = br.readLine();
            String[] sArray = s.split(":");
            json.put("firstParameterLabel", sArray[0]);
            json.put("firstParameterValue",  sArray[1]);
            System.out.println("Write second parameter label and value in [label]:[value] format. Value must be int!");
            s = br.readLine();
            sArray = s.split(":");
            json.put("secondParameterLabel", sArray[0]);
            json.put("secondParameterValue", sArray[1]);
            System.out.println("Write third parameter label and value in [label]:[value] format. Value must be int!");
            s = br.readLine();
            sArray = s.split(":");
            json.put("thirdParameterLabel", sArray[0]);
            json.put("thirdParameterValue", sArray[1]);
            System.out.println("Write fourth parameter label and value in [label]:[value] format.  Value must be int!");
            s = br.readLine();
            sArray = s.split(":");
            json.put("fourthParameterLabel", sArray[0]);
            json.put("fourthParameterValue", sArray[1]);
            StringEntity se = new StringEntity( json.toString());
            se.setContentType("application/json");
            HttpPost request = new HttpPost("http://localhost:8080/rest/resources/category/"+id+"/elements");
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
