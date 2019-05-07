import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Lista implements Iterable<Contato> {
	// implements Iterable<Contato> exigiu iterator()
	
	private ArrayList<Contato> alLista = null;
	private static Lista minhaLista;
	
	public static Lista getInstance(){
		if (minhaLista == null)
			minhaLista = new Lista();		
		return minhaLista;
	}

	private Lista() {
		alLista = new ArrayList<Contato>();
	}
	
	public void addContato(Contato contato) {
		alLista.add(contato);
	}
	
	public void removeContato(int i) {
		alLista.remove(i);
	}
	
	public Contato getContato(int i){
		return alLista.get(i);
	}
	
	public ArrayList<Contato> getLista() {
		return alLista;
	}
	
	public int size(){
		return alLista.size();
	}
	
	public boolean existeContato(String nome) {
		boolean existe = false;
		for(Contato c : alLista) {
			if(nome.equalsIgnoreCase(c.getNome())) existe = true;
		}
		return existe;
	}
	
	public boolean existeCpf(String cpf) {
		boolean existe = false;
		for(Contato c : alLista) {
			if(cpf.equals(c.getCPF())) existe = true;
		}
		return existe;
	}
	
	public int getIndex(String cpf) {
		int index = -1;
		if(this.existeCpf(cpf)) {
			for(int i = 0; i < this.size(); i++) {
				Contato c = this.getContato(i);
				if(cpf.equals(c.getCPF())) index = i;
			}
		}
		return index;
	}
	
	public void ordenaAZ(){
		Collections.sort(alLista);
	}
	
	public void ordenaZA(){
		Collections.sort(alLista, Collections.reverseOrder());
	}
	
	public void ordenaAniversarioJanDez(){
		Collections.sort(alLista, new Comparator<Contato>() {
			// aqui dentro vem a classe anônima
			@Override
			public int compare(Contato c1, Contato c2) {
				int diaC1 = c1.getDataNasc().get(Calendar.DAY_OF_YEAR);
				int diaC2 = c2.getDataNasc().get(Calendar.DAY_OF_YEAR);
				return Integer.compare(diaC1, diaC2);
			}
		});
	}
	
	public ArrayList<Contato> search(String nome) {
		ArrayList<Contato> result = null;
		for(Contato c : alLista) {
			if(c.getNome().toLowerCase().contains(nome.toLowerCase())) {
				if(result == null) result = new ArrayList<Contato>(); 
				result.add(c);
			}
		}
		return result;
	}
	
	public ArrayList<Integer> searchInt(String nome) {
		ArrayList<Integer> result = null;
		for(int i = 0; i < alLista.size(); i ++) {
			if(alLista.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {
				if(result == null) result = new ArrayList<Integer>(); 
				result.add(i);
			}
		}
		return result;
	}
	
	public boolean editarNome(int i, String nome) {
		if(nome.length() == 0) return false;
		this.getContato(i).setNome(nome);
		return true;
	}
	
	public boolean editarDataNasc(int i, int dia, int mes, int ano) {
		if(!this.getContato(i).validaDataNasc(dia, mes, ano)) return false;
		this.getContato(i).setDataNasc(dia, mes, ano);
		return true;
	}
	
	public boolean editarCPF(int i, String cpf) {
		CPF novoCPF = new CPF(cpf);
		if(!novoCPF.isValid()) return false;
		this.getContato(i).setCPF(cpf);
		return true;
	}
	
	public void imprimeAniversariantes() {
		this.ordenaAniversarioJanDez();
		int mes = 0;
		int i = 0;
		String[] meses = {"JANEIRO", 
				"FEVEREIRO",
				"MARÇO",
				"ABRIL",
				"MAIO",
				"JUNHO",
				"JULHO",
				"AGOSTO",
				"SETEMBRO",
				"OUTUBRO",
				"NOVEMBRO",
				"DEZEMBRO"};
		while(i < this.size()) {
			Contato c = this.getContato(i);
			if(i == 0 && mes == 0) System.out.println(meses[mes]);
			if(c.getDataNasc().get(Calendar.MONTH) == mes) {
				System.out.println(c.getNome() + " - " + c.getDataNascString());
				i++;
			}
			else {
				mes++;
				System.out.println();
				System.out.println(meses[mes]);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Lista [alLista=" + alLista.toString() + "]";
	}

	@Override
	public Iterator<Contato> iterator() {
		return alLista.iterator();
	}	
	
	public void imprimeLista() {
		for(Contato c : alLista) {
			System.out.println(c);
		}
	}
}
