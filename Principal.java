import java.util.Scanner;

public class Principal {
	private static Scanner teclado = new Scanner (System.in);
	// static, variÃ¡vel de classe
	//teste teste teste

	public static void main(String[] args) {
		int opcao;
		int opcaoend;

		Lista lc = Lista.getInstance();
		ListaEndereco le = ListaEndereco.getInstance();
		
		do {
			// menu de opcoes contato
			System.out.println("\n--- Menu contatos---\n");
			System.out.println("0 - sair");
			System.out.println("1 - adicionar");
			System.out.println("2 - listar");
			System.out.println("3 - ordenar de A-Z");
			System.out.println("4 - ordenar de Z-A");
			System.out.print("\nOpcao: ");
			opcao = teclado.nextInt();
			// menu de opçoes endereco

			System.out.println("\n--- Menu endereços ---\n");
			System.out.println("0 - sair");
			System.out.println("1 - adicionar");
			System.out.println("2 - listar");
			System.out.println("3 - ordenar de A-Z");
			System.out.println("4 - ordenar de Z-A");
			System.out.print("\nOpcao: ");
			opcaoend = teclado.nextInt();
			
			// cada opcao chama uma funÃ§Ã£o static da main
			switch (opcao) {
			case 0:
				break;  // system.exit(0);
			case 1:
				lc.addContato(lerContato());
				break;
			case 2:
				System.out.println("\n--- Lista ---\n");
				for (int i = 0; i < lc.size(); i++) {
					System.out.println(i + ":" + lc.getContato(i));
				}
				// para remover o contato 1 => lc.removeContato(1);
				break;
			case 3:
				lc.ordenaAZ();
				break;
			case 4:
				lc.ordenaZA();
				break;
			case 5:
				
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
	
	private static Endereco VEnd() {
		String rua;
		Contato contato;
		teclado.nextLine(); // para consumir o enter
		System.out.println("\n--- Novo Contato ---");
		System.out.print("Nome: ");
		nome = teclado.nextLine();
		contato = new Contato(nome);
		return contato;
	}

}
