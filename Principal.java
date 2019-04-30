import java.util.Scanner;

public class Principal {
	private static Scanner teclado = new Scanner (System.in);
	// static, variÃ¡vel de classe
	//teste teste teste

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
						lc.addContato(new Contato(teclado.nextLine()));
						break;
					case 2: //listar
						lc.imprimeLista();
						break;
					case 3: //ordenar
						do {
							System.out.println("1 - A-Z");
							System.out.println("2 - Z-A");
							opcao = teclado.nextInt();
						} while(opcao != 1 && opcao != 2);
						switch(opcao) {
						case 1:
							lc.ordenaAZ();
							lc.imprimeLista();
							break;
						case 2:
							lc.ordenaZA();
							lc.imprimeLista();
							break;
						}
						break;
					case 4: //editar
						String cpf;
						do {
							System.out.println("1 - Nome");
							System.out.println("2 - Data de nascimento");
							System.out.println("3 - CPF");
							opcao = teclado.nextInt();
						} while(opcao < 0 || opcao > 3);
						do {
							System.out.print("Digite o CPF do usuário: ");
							cpf = teclado.nextLine();
						} while(!lc.existeCpf(cpf));
						lc.editarContato(opcao, cpf);
						break;
					case 5: //remover
						break;
					case 6: //voltar
						menuPrinipal();
						break;
					case 7: //sair
						System.exit(0);
					default:
						System.out.println("Opção inexistente");
					}
				} while(opcao != 7 && opcao != 6);
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

	private static Contato lerContato() {
		String nome;
		Contato contato;
		teclado.nextLine(); // para consumir o enter
		System.out.println("\n--- Novo Contato ---");
		System.out.print("Nome: ");
		nome = teclado.nextLine();
		contato = new Contato(nome);
		return contato;
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
	
	private static void cls() {
		for(int i = 0; i < 100; i++) System.out.println();
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
		System.out.println("4 - editar");
		System.out.println("5 - remover");
		System.out.println("6 - voltar");
		System.out.println("7 - sair");
	}

}
