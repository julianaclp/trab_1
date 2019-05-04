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
	
	public void testeAddContato(Contato contato) {
		alLista.add(contato);
	}
	
	public Contato getContato(int i){
		return alLista.get(i);
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
			// aqui dentro vem a classe anÃ´nima
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
	
	@Override
	public String toString() {
		return "Lista [alLista=" + alLista.toString() + "]";
	}

	@Override
	public Iterator<Contato> iterator() {
		return alLista.iterator();
	}	
	
	//---------------AÇÕES DO MENU---------------
	public void addContato(){
		Contato contato;
		int dia; 
		int mes;
		int ano;
		String cpf;
		boolean flag = false;
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o nome do contato: ");
		contato = new Contato(teclado.nextLine());
		System.out.println("Data de nascimento:");
		do {
			if(flag) System.out.println("Data inválida! Verifique os dados e digite novamente.");
			System.out.print("Digite o dia: ");
			dia = teclado.nextInt();
			System.out.print("Digite o mês: ");
			mes = teclado.nextInt();
			System.out.print("Digite o ano: ");
			ano = teclado.nextInt();
			flag = true;
		} while(!contato.validaDataNasc(dia, mes, ano));
		contato.setDataNasc(dia, mes, ano);
		flag = false;
		do {
			if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente.");
			System.out.print("Digite o CPF: ");
			cpf = teclado.nextLine();
			flag = true;
		} while(!contato.verificaCPF(cpf));
		contato.setCPF(cpf);
		alLista.add(contato);
		teclado.close();
	}
	
	public void editarContato() {
		String cpf;
		int opcao;
		boolean flag = false;
		Scanner teclado = new Scanner(System.in);
		do {
			if(flag) System.out.println("Opção inválida! Verifique os dados e digite novamente.");
			System.out.println("1 - Nome");
			System.out.println("2 - Data de nascimento");
			System.out.println("3 - CPF");
			opcao = teclado.nextInt();
			flag = true;
		} while(opcao < 0 || opcao > 3);
		teclado.nextLine();
		flag = false;
		do {
			if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente.");
			System.out.print("Digite o CPF do contato: ");
			cpf = teclado.nextLine();
			flag = true;
		} while(!this.existeCpf(cpf));
		flag = false;
		int index = this.getIndex(cpf);
		switch(opcao) {
		case 1:
			System.out.print("Digite o novo nome: ");
			this.getContato(index).setNome(teclado.nextLine());
			break;
		case 2:
			int dia;
			int mes;
			int ano;
			flag = false;
			do {
				if(flag) System.out.println("Data inválida! Verifique os dados e digite novamente!");
				System.out.print("Digite o dia: ");
				dia = teclado.nextInt();
				System.out.print("Digite o mês: ");
				mes = teclado.nextInt();
				System.out.print("Digite o ano: ");
				ano = teclado.nextInt();
				flag = true;
			} while(!this.getContato(index).validaDataNasc(dia, mes, ano));
			this.getContato(index).setDataNasc(dia, mes, ano);
			break;
		case 3:
			CPF novoCPF; 
			flag = false;
			do {
				if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente!");
				System.out.print("Digite o CPF: ");
				novoCPF = new CPF(teclado.nextLine());
				flag = true;
			} while(!novoCPF.isValid());
			this.getContato(index).setCPF(novoCPF.toString());
			break;
		default:
			System.out.println("Campo inexistente");
		}
		teclado.close();
	}
	
	public void buscaContato() {
		String busca;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.print("Digite a busca: ");
			busca = teclado.nextLine();
		} while(busca == null || busca.length() == 0);
		ArrayList<Contato> resultado = this.search(busca);
		if(resultado == null) System.out.println("Contato inexistente");
		else System.out.println(resultado);
		teclado.close();
	}
	
	public void ordenaLista() {
		int opcao;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		do {
			if(flag) System.out.println("Opção inválida! Verifique os dados e digite novamente.");
			System.out.println("1 - A-Z");
			System.out.println("2 - Z-A");
			opcao = teclado.nextInt();
			flag = true;
		} while(opcao != 1 && opcao != 2);
		switch(opcao) {
		case 1:
			this.ordenaAZ();
			break;
		case 2:
			this.ordenaZA();
			break;
		}
		teclado.close();
	}
	
	public void removeContato() {
		String cpf;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		do {
			if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente.");
			System.out.print("Digite o CPF do contato: ");
			cpf = teclado.nextLine();
			flag = true;
		} while(!this.existeCpf(cpf));
		this.alLista.remove(this.getIndex(cpf));
		teclado.close();
	}
	
	public void imprimeLista() {
		for(Contato c : alLista) {
			System.out.println(c);
		}
	}
}
