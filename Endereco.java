
public class Endereco {
	// atributos:
	// logradouro
	// numero
	// complemento (opcional)
	// cidade
	// estado
	// CEP
	
	private String logradouro;
	private int numero;
	private String complemento;
	private String cidade;
	private String estado;
	private String CEP;
	
	public Endereco(String logradouro,int numero,String cidade, String estado, String CEP, String complemento) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.CEP = CEP;
		this.complemento = complemento;
	}
	
	public Endereco(String logradouro) {
		super();
		this.logradouro = logradouro;
	}

}
