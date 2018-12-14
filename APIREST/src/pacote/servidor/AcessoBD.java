package pacote.servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pacote.cliente.ConsultaApi;

public class AcessoBD {
	Connection con = null;

	public AcessoBD() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		//DADOS PARA CONEXAO COM O BANCO DE DADOS
		String url = "jdbc:mysql://localhost:3306/planetas";
		String user = "user_planetas";
		String password = "user_password";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection(url, user, password);
	}

	//RETORNA UM PLANETA CONFORME ID INFOMADO
	public Planeta getPlaneta(int id) {
		Planeta planeta = new Planeta();
		ConsultaApi capi = new ConsultaApi();
		int qtde=0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("select id,nome,clima,terreno,id_publico from planeta where id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();	
			if (rs.next()) {
				planeta.setId(rs.getInt(1));
				planeta.setNome(rs.getString(2));
				planeta.setClima(rs.getString(3));
				planeta.setTerreno(rs.getString(4));
				planeta.setIdPublico(rs.getInt(5));
				planeta.setIdPublico(rs.getInt(5));
				//CONSULTA API PARA PEGAR A QTDE DE FILMES CONFORME O ID PUBLICO QUE VEM DA BASE DE DADOS
				try {
					qtde = capi.ContaFilmes(rs.getInt(5));
				}catch(Exception e) {
					e.printStackTrace();
				}
				planeta.setQtdeFilmes(qtde);
				
			}
			con.close();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(AcessoBD.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return planeta;
	}

	//RETORNA UM PLANETA CONFORME NOME INFOMADO
	public Planeta getPlanetaNome(String nome) {
		Planeta planeta = new Planeta();
		ConsultaApi capi = new ConsultaApi();
		int qtde=0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("select id,nome,clima,terreno,id_publico from planeta where nome = ?");
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				planeta.setId(rs.getInt(1));
				planeta.setNome(rs.getString(2));
				planeta.setClima(rs.getString(3));
				planeta.setTerreno(rs.getString(4));
				planeta.setIdPublico(rs.getInt(5));
				//CONSULTA API PARA PEGAR A QTDE DE FILMES CONFORME O ID PUBLICO QUE VEM DA BASE DE DADOS
				try {
					qtde = capi.ContaFilmes(rs.getInt(5));
				}catch(Exception e) {
					e.printStackTrace();
				}
				planeta.setQtdeFilmes(qtde);
			}
			con.close();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(AcessoBD.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return planeta;
	}
	
	
	//ADICIONA UM PLANETA
	public Boolean adicionaPlaneta(Planeta planeta) {
		Boolean retorno = false;
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("insert into planeta(nome,clima,terreno,id_publico) values(?,?,?,?)");
			preparedStatement.setString(1, planeta.getNome());
			preparedStatement.setString(2, planeta.getClima());
			preparedStatement.setString(3, planeta.getTerreno());
			preparedStatement.setInt(4, planeta.getIdPublico());
			if (preparedStatement.executeUpdate() > 0) {
				retorno = true;
			}
			con.close();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(AcessoBD.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return retorno;
	}

	//LISTA PLANETAS
	public List<Planeta> listarPlanetas() {
		List<Planeta> planetas = new ArrayList<Planeta>();	
		ConsultaApi capi = new ConsultaApi();
		int qtde=0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement("select id, nome, clima, terreno, id_publico from planeta");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Planeta planeta = new Planeta();
				planeta.setId(rs.getInt(1));
				planeta.setNome(rs.getString(2));
				planeta.setClima(rs.getString(3));
				planeta.setTerreno(rs.getString(4));
				planeta.setIdPublico(rs.getInt(5));
				//CONSULTA API PARA PEGAR A QTDE DE FILMES CONFORME O ID PUBLICO QUE VEM DA BASE DE DADOS
				try {
					qtde = capi.ContaFilmes(rs.getInt(5));
				}catch(Exception e) {
					e.printStackTrace();
				}
				planeta.setQtdeFilmes(qtde);
				planetas.add(planeta);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planetas;
	}

	//EXCLUI UM PLANETA CONFORME ID INFORMADO
	public void excluiPlaneta(int id) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement("delete from planeta where id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			con.close();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(AcessoBD.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		}
	}
}