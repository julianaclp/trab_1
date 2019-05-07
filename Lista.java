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
	
	public int selecionaContato() {
		ArrayList<Integer> contatos = null;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		int selecao;
		do {
			if(flag) System.out.println("Contato inexistente! Verifique os dados e digite novamente.");
			System.out.print("Digite o nome do contato: ");
			contatos = this.searchInt(teclado.nextLine());
			flag = true;
		} while(contatos.size() == 0);
		flag = false;
		if(contatos.size() == 1) return contatos.get(0);
		do {
			if(flag) System.out.println("Opção indisponível. Verifique os dados e digite novamente.");
			System.out.println("Mais de uma opção disponível. Escolha o número do contato: ");
			for(int i : contatos) {
				System.out.println(i + " - " + this.getContato(i));
			}
			selecao = teclado.nextInt();
			flag = true;
		} while(!contatos.contains(selecao));
		return selecao;
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
