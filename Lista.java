import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Lista implements Iterable<Contato> {
	
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
	
	public void removeContato(Contato contato) {
		alLista.remove(contato);
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
	
	public void ordenaDataNascimento(){
		Collections.sort(alLista, new Comparator<Contato>() {
			@Override
			public int compare(Contato c1, Contato c2) {
				long diaC1 = c1.getDataNasc().getTimeInMillis();
				long diaC2 = c2.getDataNasc().getTimeInMillis();
				return Long.compare(diaC1, diaC2);
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
	
	
	public boolean editarNome(Contato c, String nome) {
		if(nome.length() == 0) return false;
		c.setNome(nome);
		return true;
	}
	
	public boolean editarDataNasc(Contato c, int dia, int mes, int ano) {
		if(!c.validaDataNasc(dia, mes, ano)) return false;
		c.setDataNasc(dia, mes, ano);
		return true;
	}
	
	public boolean editarCPF(Contato c, String cpf) {
		CPF novoCPF = new CPF(cpf);
		if(!novoCPF.isValid()) return false;
		c.setCPF(cpf);
		return true;
	}
	
	public String imprimeAniversariantes() {
		this.ordenaAniversarioJanDez();
		int mes = 0;
		int i = 0;
		int aniversariantesNoMes = 0;
		String lista = "";
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
			if(c.getDataNasc().get(Calendar.MONTH) == mes) {
				if(aniversariantesNoMes == 0) {
					if(mes > 0) lista += "\n";
					lista += meses[mes] + "\n";
				}
				lista += c.getNome() + " - " + c.getDataNasc().get(Calendar.DAY_OF_MONTH) + "\n";
				i++;
				aniversariantesNoMes++;
			}
			else {
				mes++;
				aniversariantesNoMes = 0;
			}
		}
		return lista;
	}
	
	@Override
	public String toString() {
		return "Lista [alLista=" + alLista.toString() + "]";
	}

	@Override
	public Iterator<Contato> iterator() {
		return alLista.iterator();
	}	
	
	public String imprimeLista() {
		String lista = "";
		for(Contato c : alLista) {
			lista += c + "\n";
		}
		return lista;
	}
}
