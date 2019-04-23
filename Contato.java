
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

// implements a interface Comparabel exige a implementação do método compareTo
public class Contato implements Comparable<Contato> {
	// implements Comparable<Contato> exigiu compareTo()
	
	private String nome;
	private GregorianCalendar dataNasc;
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
	
	public int getIdade (){
		GregorianCalendar dataHoje = new GregorianCalendar(); // data corrente
		int idade = dataHoje.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);
		
		if (dataNasc.get(Calendar.DAY_OF_YEAR) > dataHoje.get(Calendar.DAY_OF_YEAR)) {
			idade--;
		}
		return idade;
	}
	
	public String getDataNascString (){
		SimpleDateFormat data = new SimpleDateFormat ("dd/MM/yyyy");
		return data.format(dataNasc.getTime());
	}
	
	public GregorianCalendar getDataNasc(){
		return this.dataNasc;
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
