import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpURLConnectionExample {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://192.168.100.6/cfe/buscar_producto.php?subestacion=NHE";

	//private static final String POST_URL = "https://localhost:9090/SpringMVCExample/home";

	//private static final String POST_PARAMS = "userName=Pankaj";

	public static void main(String[] args) throws IOException {

		sendGET();
		System.out.println("GET DONE");
		/*sendPOST();
		System.out.println("POST DONE");*/
	}

	private static void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			try {
				JSONArray getArray = new JSONArray(response.toString());
				for(int i = 0; i < getArray.length(); i++)
				{
				      JSONObject object = getArray.getJSONObject(i);
				      Equipo equipo = new Equipo(object.getString("subestacion"),object.getString("ubicacion_tecnica"),object.getString("fabricante"),object.getString("modelo"),object.getString("no_serie"),object.getString("equipo"),object.getString("texto_breve"),object.getString("orden"));
				      String archivo = deviceType(object.getString("texto_breve"));
				      if(object.getString("texto_breve").contains("MANTO")) {
				    	  archivo = "FORMULARIO/MANTO/"+archivo+".pdf";
				    	  System.out.println(archivo);
				      }else if (object.getString("texto_breve").contains("INSP")) {
				    	  archivo = "FORMULARIO/INSP/"+archivo+".pdf";
				    	  System.out.println(archivo);
				      }
				      try {
						Writer formato = new Writer(archivo,equipo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      //Iterate through the elements of the array i.
				      //Get thier value.
				      //Get the value for the first eçlement and the value for the last element.
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		} else {
			System.out.println("GET request not worked");
		}

	}
	private static String deviceType(String textoBreve) {
		if(textoBreve.contains("CARG")) {
			return "CARGADOR";
		}
		else if(textoBreve.contains("MCAD") || textoBreve.contains("ES0")) {
			return "MCAD";
		}
		else if(textoBreve.contains("SERVIDOR") || textoBreve.contains("SSR PPAL") || textoBreve.contains("SSR RESP")) {
			return "SERVIDOR";
		}
		else if(textoBreve.contains("INVERSOR")) {
			return "INVERSOR";
		}
		else if(textoBreve.contains("GPS")) {
			return "GPS";
		}
		else if(textoBreve.contains("SWITCH")) {
			return "SWITCH";
		}
		else if(textoBreve.contains("UPS")) {
			return "UPS";
		}
		else if(textoBreve.contains("CCL")) {
			return "CCL";
		}
		else if(textoBreve.contains("CI")) {
			return "CI";
		}
		else if(textoBreve.contains("FZA")) {
			return "PLANTA DE FUERZA";
		}
		else if(textoBreve.contains("SSR")) {
			return "SSR";
		}
		else if(textoBreve.contains("MES")) {
			return "USB";
		}
		else if(textoBreve.contains("CEV")) {
			return "CEV";
		}
		return "PITO";
	}
	/*
	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}
	*/

}