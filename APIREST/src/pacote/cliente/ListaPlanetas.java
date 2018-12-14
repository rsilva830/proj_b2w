package pacote.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import org.apache.tomcat.util.json.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import pacote.servidor.Planeta;

@WebServlet("/LISTAPLANETAS")
public class ListaPlanetas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource("http://localhost:8080/APIREST/api/");
			String resposta =service.path("servico").path("planeta/listar").accept(MediaType.APPLICATION_JSON).get(String.class);
							
			// FORMATA A STRING PARA JSON E IMPRIME
			ObjectMapper mapper = new ObjectMapper();
			Object jsonObject = mapper.readValue(resposta, Object.class);
			String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
			//out.println("<pre>"+prettyJson+"</pre>");

	        response.setContentType("application/json; charset=utf-8");
	        //PEGA A SESSAO DO USUARIO E ARMAZENA A RESPOSTA DA API NA VARIAVEL RESPOSTA DA SESSAO
	        HttpSession session = request.getSession();  	  
	        session.setAttribute("resapi", prettyJson);
			
		} catch (Exception e) {
			out.print(e.getMessage());
		}
		
		response.sendRedirect("http://localhost:8080/APIREST/?status=0");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}