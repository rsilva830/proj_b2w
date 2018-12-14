package pacote.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ConsultaApi {
	
	int res = 0;
	
	public int ContaFilmes(int id) throws Exception {
		
		try {
			StringBuilder result = new StringBuilder();
			String param = String.valueOf(id);
			URL url = new URL("https://swapi.co/api/planets/"+param+"/?format=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// PARA CORRIGIR O ERRO QUE A API RETORNA 403 FORBIDDEN
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			String resPuroAPI = result.toString();		
	
			//PEGA QUANTIDADE DE FILMES QUE APARECEU
			try {
				JSONObject jObj = new JSONObject(resPuroAPI);
				JSONArray jArray = jObj.getJSONArray("films");
				res = jArray.length();
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return res;
	}

}
