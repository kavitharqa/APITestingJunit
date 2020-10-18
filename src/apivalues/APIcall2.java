package apivalues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.jayway.jsonpath.JsonPath;

import junit.framework.Assert;
import net.minidev.json.JSONArray;



public class APIcall2 {

	@Test
	public void validate() {
		System.out.println("welcome");
		
		try {

			URL url = new URL("https://gorest.co.in/public-api/users");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			
			String data="";
			
			while ((output = br.readLine()) != null) {
				data=data+output;
				//System.out.println(output);
			}
			System.out.println(data);
			
			JSONArray d=JsonPath.read(data,"$..data.[0]");
			Assert.assertTrue(d.size()>0);
			System.out.println("d--"+d.size());

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
	}
}
