package Json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class ReadJsonAPIComments {
	public static void main(String[] args) {
		try {
		            int limit = 250;          
		            Scanner sc = new Scanner(System.in);
		            int start = 0;  
		            boolean moreComments = true;

		            while (moreComments) {
		                String apiUrl = "https://jsonplaceholder.typicode.com/photos?_start=" + start + "&_limit=" + limit;
		                JSONArray batchComments = fetchComments(apiUrl);
		                System.out.println("Number of comments fetched in batch starting at " + start + ": " + batchComments.length());
		                System.out.println("=============================================================");
		                printComments(batchComments);
		                if (batchComments.length() == limit) {
		                    System.out.println("Press Enter to fetch the next batch of comments...");
		                    sc.nextLine(); 
		                    start += limit;  
		                } else {
		                    moreComments = false; 
		                }
		            }
		            sc.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    private static JSONArray fetchComments(String apiUrl) throws Exception {
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
		    private static void printComments(JSONArray comments) {
		        for (int i = 0; i < comments.length(); i++) {
		            JSONObject post = comments.getJSONObject(i);
		            System.out.println("Post Id : " + post.getInt("albumId"));
		            System.out.println("ID      : " + post.getInt("id"));
		            System.out.println("Name    : " + post.getString("title"));
		            System.out.println("Email   : " + post.getString("url"));
		            System.out.println("Body    : " + post.getString("thumbnailUrl"));
		            System.out.println("-------------------------------------------------------------------");
		        }
		    }
		}
