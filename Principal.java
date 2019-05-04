import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	private static Scanner teclado = new Scanner (System.in);
	
	public static void main(String[] args) {
		int opcao;

		Lista lc = Lista.getInstance();
		lc.testeAddContato(new Contato("Joao", 2, 1, 2000));
		lc.testeAddContato(new Contato("Maria", 2, 2, 1999));
		lc.testeAddContato(new Contato("Pedro", 5, 11, 2001));
		lc.testeAddContato(new Contato("Tiago", 10, 12, 2000));
		lc.testeAddContato(new Contato("Jose", 4, 4, 2000));
		lc.testeAddContato(new Contato("Lucas", 22, 10, 2010));
		lc.testeAddContato(new Contato("Mateus", 3, 1, 1950));
		Contato teste = new Contato("Juliana", 1, 9, 1996);
		teste.setCPF("09489104965");
		lc.testeAddContato(teste);
		
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
						lc.addContato();
						break;
					case 2: //listar
						lc.imprimeLista();
						break;
					case 3: //ordenar
						lc.ordenaLista();
						lc.imprimeLista();
						break;
					case 4: // buscar
						lc.buscaContato();
						break;
					case 5: //editar
						lc.editarContato();
						break;
					case 6: //remover
						lc.removeContato();
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

}
