package pacote.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import pacote.cliente.*;

@WebServlet("/EXCLUIPLANETA")
public class ExcluiPlaneta extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");	 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");     
        try {
            ClientConfig config = new DefaultClientConfig();
    	    Client client = Client.create(config);
    	    WebResource service = client.resource("http://localhost:8080/APIREST/api/"); 
    	    String resposta = service.path("servico").path(MessageFormat.format("planeta/exclui/{0}",new Object[] {id})).accept(MediaType.APPLICATION_JSON).get(String.class);
       	        
	        response.setContentType("application/json; charset=utf-8");
	        //PEGA A SESSAO DO USUARIO E ARMAZENA A RESPOSTA DA API NA VARIAVEL RESPOSTA DA SESSAO
	        HttpSession session = request.getSession();  	  
	        session.setAttribute("resapi", resposta);
    	    
        }catch(Exception e){
			out.print(e.getMessage());
		} 
        response.sendRedirect("http://localhost:8080/APIREST/?status=1");
    }
	
}