package pacote.servidor;

public class Planeta {
	private int id;
	private String nome;
	private String clima;
	private String terreno;	
	private int idPublico;
	private int qtdeFilmes;
	
	Planeta(){
		this.id = 0;
		this.nome = "";
		this.clima = "";
		this.terreno = "";	
		this.idPublico = 0;
		this.qtdeFilmes = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getClima() {
		return clima;
	}
	
	public String getTerreno() {
		return terreno;
	}
	
	public int getIdPublico() {
		return idPublico;
	}
	
	public int getQtdeFilmes() {
		return qtdeFilmes;
	}
	
	public void setId(int pId) {
		this.id = pId;
	} 
	
	public void setNome(String pNome) {
		this.nome = pNome;
	} 
	
	public void setClima(String pClima) {
		this.clima = pClima;
	} 
	
	public void setTerreno(String pTerreno) {
		this.terreno = pTerreno;
	} 
	
	public void setIdPublico(int pId) {
		this.idPublico = pId;
	} 
	
	public void setQtdeFilmes(int pQtde) {
		this.qtdeFilmes = pQtde;
	} 
}