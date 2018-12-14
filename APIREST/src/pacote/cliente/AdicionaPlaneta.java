package pacote.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.spi.dispatch.RequestDispatcher;

import pacote.cliente.*;

@WebServlet("/ADICIONAPLANETA")
public class AdicionaPlaneta extends HttpServlet{
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String clima = request.getParameter("clima");
        String terreno = request.getParameter("terreno");  
        String idPublico = request.getParameter("idPublico");     
        try {
            ClientConfig config = new DefaultClientConfig();
    	    Client client = Client.create(config);
    	    WebResource service = client.resource("http://localhost:8080/APIREST/api/servico"); 
    	    String resposta = service.path(MessageFormat.format("planeta/adiciona/{0}/{1}/{2}/{3}",new Object[] {nome,clima,terreno,idPublico})).accept(MediaType.APPLICATION_JSON).get(String.class);     
        }catch(Exception e){
			e.printStackTrace();
		}  
        response.sendRedirect("http://localhost:8080/APIREST/?status=1");
    }
	
}