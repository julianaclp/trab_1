
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Contato implements Comparable<Contato> {
	
	private String nome;
	private GregorianCalendar dataNasc;
	private CPF cpf; 
	private ListaEndereco alEndereco = ListaEndereco.getInstance();
	
	public Contato(String nome) {
		super();
		this.nome = nome;
	}
	
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
	
	public ListaEndereco getListaEndereco() {
		return alEndereco;
	}
	
	public void addEndereco(Endereco endereco) {
		alEndereco.add(endereco);
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
		if(mes > 12) return false;
		data.set(Calendar.MONTH, mes - 1);
		if(dia > data.getActualMaximum(Calendar.DAY_OF_MONTH)) return false;
		data.set(Calendar.DAY_OF_MONTH, dia);
		data.set(Calendar.YEAR, ano);
		if(data.getTimeInMillis() > hoje.getTimeInMillis()) return false;
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

	public int compareTo(Contato contato) {
		return this.nome.compareToIgnoreCase(contato.nome);
	}
	
	public String getEtiquetas() {
		String etiqueta = "";
		if(this.alEndereco.size() == 0) return etiqueta;
		for(Endereco e : this.getListaEndereco()) {
			etiqueta += this.getNome() + "\n";
			etiqueta += e.getLogradouro() + ", " + e.getNumero() + "\n";
			etiqueta += e.getCidade() + " - " + e.getEstado() + "\n";
			etiqueta += "CEP " + e.getCEP() + "\n\n";
		}
		return etiqueta;
	}

}
