import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListaEndereco implements Iterable<Endereco> {
	// implements Iterable<Contato> exigiu iterator()
	
	private ArrayList<Endereco> alListaEndereco = null;
	private static ListaEndereco minhaListaEndereco;
	
	public static ListaEndereco getInstance(){
		if (minhaListaEndereco == null)
			minhaListaEndereco = new ListaEndereco();		
		return minhaListaEndereco;
	}

	private ListaEndereco() {
		alListaEndereco = new ArrayList<Endereco>();
	}
	
	public void addEndereco(Endereco endereco){
		// eventuais regras antes da adição
		alListaEndereco.add(endereco);
	}

	@Override
	public Iterator<Endereco> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addContato(Contato lerContato) {
		// TODO Auto-generated method stub
		
	}
	
/*	public Contato getContato(int i){
		return alLista.get(i);
	}
	
	public void removeContato(int i){
		alLista.remove(i);
	}
	
	public int size(){
		return alLista.size();
	}
	
	// método sort usará compareTo da Contato e ordenará o ArrayList alLista
	// pela ordem natural definida na classe Contato (no caso, o atributo nome)
	public void ordenaAZ(){
		Collections.sort(alLista);
	}
	
	public void ordenaZA(){
		Collections.sort(alLista, Collections.reverseOrder());
	}
	@Override
	public String toString() {
		return "Lista [alLista=" + alLista.toString() + "]";
	}

	@Override
	public Iterator<Contato> iterator() {
		return alLista.iterator();
	}	
*/
}
