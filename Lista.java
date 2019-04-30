import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

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
	
	public void addContato(Contato contato){
		// eventuais regras antes da adição
		alLista.add(contato);
	}
	
	public Contato getContato(int i){
		return alLista.get(i);
	}
	
	public void removeContato(int i){
		alLista.remove(i);
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
	
	// método sort usará compareTo da Contato e ordenará o ArrayList alLista
	// pela ordem natural definida na classe Contato (no caso, o atributo nome)
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
			if(c.getNome().contains(nome)) {
				if(result == null) result = new ArrayList<Contato>(); 
				result.add(c);
			}
		}
		return result;
	}
	
	public void imprimeLista() {
		for(Contato c : alLista) {
			System.out.println(c);
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

}
