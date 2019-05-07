import java.util.ArrayList;
import java.util.Scanner;
//testando comentário
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
		
		menu();
		System.out.print("\nOpcao: ");
		do {
			// menu de opcoes contato
			opcao = teclado.nextInt();
			// cada opcao chama uma funÃ§Ã£o static da main
			switch (opcao) {
			case 1:
				addContato(lc);
				break;
			case 2: 
				editarContato(lc);
				break;
			case 3:
				removeContato(lc);
				break;
			case 4:
				ordenaLista(lc);
				lc.imprimeLista();
				break;
			case 5:
				imprimeAniversariantes(lc);
				break;
			case 6:
				//imprimirEtiquetas(lc);
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Opcao inexistente");
				break;
			}
		} while (opcao != 7);
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
	
	private static void menu() {
		System.out.println("\n--- Menu ---\n");
		System.out.println("1 - Adicionar contato");
		System.out.println("2 - Editar contato");
		System.out.println("3 - Excluir contato");
		System.out.println("4 - Ordenar");
		System.out.println("5 - Imprimir lista de aniversariantes");
		System.out.println("6 - Imprimir etiquetas");
		System.out.println("7 - Sair");
	}
	
	private static void addContato(Lista l){
		Contato contato;
		int dia; 
		int mes;
		int ano;
		CPF cpf;
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
			cpf = new CPF(teclado.nextLine());
			flag = true;
		} while(!cpf.isValid());
		contato.setCPF(cpf.toString());
		l.addContato(contato);
	}
	
	private static void editarContato(Lista l) {
		String cpf;
		int opcao;
		boolean flag = false;
		boolean success = false;
		Scanner teclado = new Scanner(System.in);
		int contato = l.selecionaContato();
		System.out.println("Informe o dado que deseja alterar: ");
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
		switch(opcao) {
		case 1:
			do {
				if(flag) System.out.println("Nome inválido! Verifique os dados e digite novamente.");
				System.out.print("Digite o novo nome: ");
				success = l.editarNome(contato, teclado.nextLine());
				flag = true;
			} while(!success);
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
				success = l.editarDataNasc(contato, dia, mes, ano);
			} while(!success);
			break;
		case 3:
			flag = false;
			do {
				if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente!");
				System.out.print("Digite o CPF: ");
				cpf = teclado.nextLine();
				flag = true;
				success = l.editarCPF(contato, cpf);
			} while(!success);
			break;
		default:
			System.out.println("Campo inexistente");
		}
		if(success) System.out.println("Contato alterado com sucesso!");
	}
	
	private static void ordenaLista(Lista l) {
		int opcao;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		do {
			if(flag) System.out.println("Opção inválida! Verifique os dados e digite novamente.");
			System.out.println("1 - A-Z");
			System.out.println("2 - Z-A");
			opcao = teclado.nextInt();
			flag = true;
		} while(opcao < 1 || opcao > 2);
		switch(opcao) {
		case 1:
			l.ordenaAZ();
			break;
		case 2:
			l.ordenaZA();
			break;
		}
	}
	
	private static void removeContato(Lista l) {
		boolean flag = false;
		int selecao;
		int contato = l.selecionaContato();
		l.removeContato(contato);
	}
	
	private static void imprimeAniversariantes(Lista l) {
		l.imprimeAniversariantes();
	}

}
