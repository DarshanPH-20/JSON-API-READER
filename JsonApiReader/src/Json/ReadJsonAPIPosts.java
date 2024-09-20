package Json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
		
public class ReadJsonAPIPosts {
	public static void main(String[] args) {
		    	try {
				    		int start=0;
			                 System.out.println("Number of Posts is starting at " + start );
			                 System.out.println("==================================================");
				            String apiUrl= "https://jsonplaceholder.typicode.com/posts?_start _limit";
				            	URL url = new URL(apiUrl);
				            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				            conn.setRequestMethod("GET");
				            conn.setRequestProperty("Accept", "application/json");
				            if (conn.getResponseCode() != 200) {
				                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				            }
				            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				            StringBuilder response = new StringBuilder();
				            String line;

				            while ((line = br.readLine()) != null) {
				                response.append(line);
				            }
				            conn.disconnect();

				            JSONArray jsonPosts = new JSONArray(response.toString());
				            for (int i = 1; i < jsonPosts.length(); i++) {
				                JSONObject post = jsonPosts.getJSONObject(i);
				                System.out.println("User ID: " + post.getInt("userId"));
				                System.out.println("Id: " + post.getInt("id"));
				                System.out.println("Title: " + post.getString("title"));
				                System.out.println("Body: " + post.getString("body"));
				                System.out.println("-------------------------------------------------");
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
				}