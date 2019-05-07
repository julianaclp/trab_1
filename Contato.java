
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

//teste
// implements a interface Comparabel exige a implementação do método compareTo
public class Contato implements Comparable<Contato> {
	// implements Comparable<Contato> exigiu compareTo()
	
	private String nome;
	private GregorianCalendar dataNasc;
	private CPF cpf;
	//private String cpf; ou então atributo CPF, depende de como será a classe 
	//private ArrayList<Endereco> alEndereco;
	
	public Contato(String nome) {
		super();
		this.nome = nome;
	}
	//Rodrifo 
	public Contato(String nome, GregorianCalendar dataNasc) {
		super();
		this.nome = nome;
		this.dataNasc = dataNasc;
	}
	
	public Contato(String nome, int dia, int mes, int ano) {
		super();
		this.nome = nome;
		this.setDataNasc(dia, mes, ano); 
	}
	
	public String getNome() {
		return nome; 
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDataNasc (int dia, int mes, int ano){
		dataNasc = new GregorianCalendar (ano, mes-1, dia);
	}
	
	public boolean validaDataNasc (int dia, int mes, int ano) {
		GregorianCalendar data = new GregorianCalendar();
		GregorianCalendar hoje = new GregorianCalendar();
		if(ano > hoje.get(Calendar.YEAR)) return false;
		if(mes > 12) return false;
		data.set(Calendar.MONTH, mes - 1);
		data.set(Calendar.YEAR, ano);
		if(dia > data.getActualMaximum(Calendar.DAY_OF_MONTH)) return false;
		return true;
	}
	
	public String getDataNascString (){
		SimpleDateFormat data = new SimpleDateFormat ("dd/MM/yyyy");
		return data.format(dataNasc.getTime());
	}
	
	public GregorianCalendar getDataNasc(){
		return this.dataNasc;
	}
	
	public String getCPF() {
		if(cpf == null) return "CPF vazio";
		else return cpf.toString();
	}
	
	public void setCPF(String cpf) {
		CPF verificador = new CPF(cpf);
		if(verificador.isValid()) this.cpf = new CPF(cpf);
	}

	public String toString() {
		return nome;
	}

	// compareTo é comparar na ordem natural (escolher uma atributo da classe, aquele que indica a ordem natural)
	// nesta classe o candidato ideal é o nome
	// ou seja, nome deste objeto é comparado com o nome do objeto contato que chega por parâmetro
	public int compareTo(Contato contato) {
		return this.nome.compareToIgnoreCase(contato.nome);
		// usando método compareToIgnoreCase da classe String
	}
	// este método será usado pelo Collection.sort 

}
