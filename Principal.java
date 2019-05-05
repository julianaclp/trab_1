import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	private static Scanner teclado = new Scanner (System.in);
	
	public static void main(String[] args) {
		int opcao;

		Lista lc = Lista.getInstance();
		lc.addContato(new Contato("Joao", 2, 1, 2000));
		lc.addContato(new Contato("Maria", 2, 2, 1999));
		lc.addContato(new Contato("Pedro", 5, 11, 2001));
		lc.addContato(new Contato("Tiago", 10, 12, 2000));
		lc.addContato(new Contato("Jose", 4, 4, 2000));
		lc.addContato(new Contato("Lucas", 22, 10, 2010));
		lc.addContato(new Contato("Mateus", 3, 1, 1950));
		Contato teste = new Contato("Juliana", 1, 9, 1996);
		teste.setCPF("09489104965");
		lc.addContato(teste);
		
		ListaEndereco le = ListaEndereco.getInstance();
		
		menuPrinipal();
		System.out.print("\nOpcao: ");
		do {
			// menu de opcoes contato
			opcao = teclado.nextInt();
			
			// cada opcao chama uma funÃ§Ã£o static da main
			switch (opcao) {
			case 0:
				break;  // system.exit(0);
			case 1:
				menuSecundario("Contatos");
				do {
					opcao = teclado.nextInt();
					switch(opcao) {
					case 1: //add 
						addContato(lc);
						break;
					case 2: //listar
						lc.imprimeLista();
						break;
					case 3: //ordenar
						ordenaLista(lc);
						lc.imprimeLista();
						break;
					case 4: // buscar
						buscaContato(lc);
						break;
					case 5: //editar
						editarContato(lc);
						break;
					case 6: //remover
						removeContato(lc);
						break;
					case 7: //voltar
						menuPrinipal();
						break;
					case 8: //sair
						System.exit(0);
					default:
						System.out.println("Opção inexistente");
					}
				} while(opcao != 7 && opcao != 8);
				break;
			case 2: 
				menuSecundario("Endereço");
				break;
			default:
				System.out.println("Opcao inexistente");
				break;
			}
		} while (opcao!=0);
	}
	
	private static Endereco lerEndereco() {
		boolean numeric = false;
		String logradouro;
		Endereco endereco;
		teclado.nextLine(); // para consumir o enter
		System.out.println("\n--- Novo Endereco ---");
		System.out.print("Logradouro: ");
		logradouro = teclado.nextLine();
		do {
			try {
	            Double num = Double.parseDouble(logradouro);
	        } catch (NumberFormatException e) {
	            numeric = false;
	        }
		 if(numeric) {
			 System.out.println("Por favor digitar o nome da rua");
		 }
		} while(numeric);
		
		endereco = new Endereco(logradouro);
		return endereco;
	}
	
	private static void menuPrinipal() {
		System.out.println("\n--- Menu ---\n");
		System.out.println("0 - Sair");
		System.out.println("1 - Menu contatos");
		System.out.println("2 - Menu endereço");
	}
	
	private static void menuSecundario(String tipo) {
		System.out.println("\n--- " + tipo + " ---\n");
		System.out.println("1 - adicionar");
		System.out.println("2 - listar");
		System.out.println("3 - ordenar");
		System.out.println("4 - buscar");
		System.out.println("5 - editar");
		System.out.println("6 - remover");
		System.out.println("7 - voltar");
		System.out.println("8 - sair");
	}
	
	//--------------MENU CONTATOS--------------
	private static void addContato(Lista l){
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
		l.addContato(contato);
		teclado.close();
	}
	
	private static void editarContato(Lista l) {
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
		} while(!l.existeCpf(cpf));
		flag = false;
		int index = l.getIndex(cpf);
		switch(opcao) {
		case 1:
			System.out.print("Digite o novo nome: ");
			l.getContato(index).setNome(teclado.nextLine());
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
			} while(!l.getContato(index).validaDataNasc(dia, mes, ano));
			l.getContato(index).setDataNasc(dia, mes, ano);
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
			l.getContato(index).setCPF(novoCPF.toString());
			break;
		default:
			System.out.println("Campo inexistente");
		}
		teclado.close();
	}
	
	private static void buscaContato(Lista l) {
		String busca;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.print("Digite a busca: ");
			busca = teclado.nextLine();
		} while(busca == null || busca.length() == 0);
		ArrayList<Contato> resultado = l.search(busca);
		if(resultado == null) System.out.println("Contato inexistente");
		else System.out.println(resultado);
		teclado.close();
	}
	
	private static void ordenaLista(Lista l) {
		int opcao;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		do {
			if(flag) System.out.println("Opção inválida! Verifique os dados e digite novamente.");
			System.out.println("1 - A-Z");
			System.out.println("2 - Z-A");
			System.out.println("3 - Data de aniversário");
			opcao = teclado.nextInt();
			flag = true;
		} while(opcao < 1 || opcao > 3);
		switch(opcao) {
		case 1:
			l.ordenaAZ();
			break;
		case 2:
			l.ordenaZA();
			break;
		case 3:
			l.ordenaAniversarioJanDez();
			break;
		}
		teclado.close();
	}
	
	private static void removeContato(Lista l) {
		String cpf;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		do {
			if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente.");
			System.out.print("Digite o CPF do contato: ");
			cpf = teclado.nextLine();
			flag = true;
		} while(!l.existeCpf(cpf));
		l.getLista().remove(l.getIndex(cpf));
		teclado.close();
	}

}
