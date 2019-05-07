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
		return alListaEndereco.iterator();
	}
	
	public Endereco buscaEndereco(int selecao)){
		
		return alListaEndereco.get(i);
	}
	
	public void removeEndereco(int i){
		alListaEndereco.remove(i);
	}
	
}
