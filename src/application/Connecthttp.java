package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.Cleaner;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Connecthttp {
	public String query;
	private int name;
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	String link_url = "http://api.songkick.com/api/3.0/search/artists.json?&query=";
    String apiKey = "&apikey=iF1N0jYrhI5wtG3n";

    public void Connect()  throws Exception {
    	String link = link_url + query.replace(" ", "_") + apiKey;
    	URL url = new URL(link);
    	HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	con.setRequestMethod("GET");
    	BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	    }
	    in.close();
	    
	     String json = response.toString();
	     JsonElement jelement = new JsonParser().parse(json).getAsJsonObject();
	     JsonObject  jobject = jelement.getAsJsonObject();
	     //Виводить айді артиста:
	    	name = jobject.getAsJsonObject("resultsPage").getAsJsonObject("results").getAsJsonArray("artist").get(0).getAsJsonObject().get("id").getAsInt();   	

    }
    
     
  
}
