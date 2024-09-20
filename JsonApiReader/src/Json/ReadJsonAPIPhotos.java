package Json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class ReadJsonAPIPhotos {
	public static void main(String[] args) {
		try {
		            int limit = 100;          
		            Scanner sc = new Scanner(System.in);
		            int start = 0;  
		            boolean morePhotos = true;

		            while (morePhotos) {
		                String apiUrl = "https://jsonplaceholder.typicode.com/photos?_start=" + start + "&_limit=" + limit;
		                JSONArray batchPhotos = fetchPhotos(apiUrl);
		                System.out.println("Number of comments fetched in batch starting at " + start + ": " + batchPhotos.length());
		                System.out.println("=============================================================");
		                printPhotos(batchPhotos);
		                if (batchPhotos.length() == limit) {
		                    System.out.println("Press 'Enter' to fetch the next batch of comments...");
		                    sc.nextLine();  
		                    start += limit;  
		                } else {
		                    morePhotos = false;  
		                }
		            }
		            sc.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    private static JSONArray fetchPhotos(String apiUrl) throws Exception {
		        URL url = new URL(apiUrl);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Accept", "application/json");
		        if (conn.getResponseCode() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		        }
		        StringBuilder response = new StringBuilder();
		        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
		            String line;
		            while ((line = br.readLine()) != null) {
		                response.append(line);
		            }
		        }
		        conn.disconnect();
		        return new JSONArray(response.toString());
		    }
		    private static void printPhotos(JSONArray photos) {
		        for (int i = 0; i < photos.length(); i++) {
		            JSONObject post = photos.getJSONObject(i);
		            System.out.println("Album Id      : " + post.getInt("albumId"));
		            System.out.println("ID            : " + post.getInt("id"));
		            System.out.println("Title         : " + post.getString("title"));
		            System.out.println("Url           : " + post.getString("url"));
		            System.out.println("Thumbnail Url : " + post.getString("thumbnailUrl"));
		            System.out.println("------------------------------------------------------------");
		        }
		    }
		}
