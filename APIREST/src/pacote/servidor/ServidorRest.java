package pacote.servidor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.google.gson.Gson;

@Path("servico")
public class ServidorRest {

	//SERVICO PARA LISTAR UM PLANETA CONFORME O ID INFORMADO
	@GET
	@Path("planeta/{p1}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPlaneta(@PathParam("p1") String param)throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		AcessoBD conexao = new AcessoBD();
		Planeta planeta = new Planeta();	
		//TESTA SE FOI DIGITADO ID OU NOME
		if(param.matches("^[0-9]*$")){
			planeta = conexao.getPlaneta(Integer.parseInt(param));
		}else {
			planeta = conexao.getPlanetaNome(param);
		}
		Gson g = new Gson();
		return g.toJson(planeta);
	}

	//SERVICO PARA LISTAR TODOS OS PLANETAS
	@GET
	@Path("planeta/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public String listar() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Planeta> lista;
		AcessoBD conexao = new AcessoBD();
		lista = conexao.listarPlanetas();
		Gson g = new Gson();
		return g.toJson(lista);
	}

	//SERVICO PARA ADICIONAR UM PLANETA
	@GET
	@Path("planeta/adiciona/{p1}/{p2}/{p3}/{p4}")
	@Produces(MediaType.APPLICATION_JSON)
	public void adicionarPlaneta(@PathParam("p1") String p1, @PathParam("p2") String p2, @PathParam("p3") String p3, @PathParam("p4") String p4)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		AcessoBD conexao = new AcessoBD();
		Planeta planeta = new Planeta();
		planeta.setNome(p1);
		planeta.setClima(p2);
		planeta.setTerreno(p3);	
		planeta.setIdPublico(Integer.parseInt(p4));
		conexao.adicionaPlaneta(planeta);
	}

	//SERVICO PARA EXCLUIR UM PLANETA CONFORME O ID INFORMADO
	@GET
	@Path("planeta/exclui/{p1}")
	@Produces(MediaType.APPLICATION_JSON)
	public void exluirPlaneta(@PathParam("p1") int a)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		AcessoBD conexao = new AcessoBD();
		conexao.excluiPlaneta(a);
	}

}
