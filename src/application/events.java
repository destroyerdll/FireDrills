package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class events extends Connecthttp {
	public ObservableList<String> data = FXCollections.observableArrayList();
	public ObservableList<String> cityarray = FXCollections.observableArrayList();
	public String apiKey = "/calendar.json?apikey=iF1N0jYrhI5wtG3n";
	public String linkEvents = "http://api.songkick.com/api/3.0/artists/";
	public String nameEvents; 
	public String city;
	
	
	public void Tours() throws Exception {
		Connect();
		System.out.println("-------"+getName());	
    	String link_url = linkEvents + getName() + apiKey;
    	URL url = new URL(link_url);
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
	    Clear();
	    String json = response.toString();
	    JsonElement jelement = new JsonParser().parse(json).getAsJsonObject();
	    JsonObject  jobject = jelement.getAsJsonObject();
	    //Цикл виводить тури в залежності в залежності від кількості результату пошуку користувача:
	    for(int i=0; i < jobject.getAsJsonObject("resultsPage").get("totalEntries").getAsInt(); i++) {
	    	nameEvents = jobject.getAsJsonObject("resultsPage").getAsJsonObject("results").getAsJsonArray("event").get(i).getAsJsonObject().get("displayName").getAsString();
	    	city = jobject.getAsJsonObject("resultsPage").getAsJsonObject("results").getAsJsonArray("event").get(i).getAsJsonObject().getAsJsonObject("location").get("city").getAsString();
	    	data.add(nameEvents);
	    	cityarray.add(city);
	    	
	    	
	     }
	    
	    
	
	}
	
	
	public void Clear() {
		data.clear();
		cityarray.clear();
	}
	
	

}
