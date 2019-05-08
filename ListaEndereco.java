import java.util.ArrayList;
import java.util.Iterator;

public class ListaEndereco implements Iterable<Endereco> {
	
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
	
	public void addEndereco(Endereco endereco) {
		alListaEndereco.add(endereco);
	}

	@Override
	public Iterator<Endereco> iterator() {
		return alListaEndereco.iterator();
	}
	
	public void removeEndereco(int i){
		alListaEndereco.remove(i);
	}
	
	public int size(){
		return alListaEndereco.size();
	}
	
	public Endereco getEndereco(int i) {
		return alListaEndereco.get(i);
	}
}
