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
		lc.addContato(new Contato("Maria", 1, 3, 1999));
		lc.addContato(new Contato("Pedro", 5, 11, 2001));
		lc.addContato(new Contato("Tiago", 10, 12, 2000));
		lc.addContato(new Contato("Jose", 4, 4, 2000));
		lc.addContato(new Contato("Lucas", 22, 10, 2010));
		lc.addContato(new Contato("Mateus", 3, 1, 1950));
		Contato teste = new Contato("Juliana", 1, 9, 1996);
		teste.setCPF("09489104965");
		Endereco e = new Endereco("Casa", 
				"R Jacob Romanichen", 
				146,
				"Curitiba",
				"PR",
				"81710430",
				"--");
		teste.addEndereco(e);
		lc.addContato(teste);
		teste = new Contato("Willer", 27, 01, 1997);
		teste.setCPF("09992670940");
		Endereco e1 = new Endereco("Casa", 
				"R Jose Valle", 
				1766,
				"Curitiba",
				"PR",
				"82020250",
				"Apto 07");
		teste.addEndereco(e1);
		lc.addContato(teste);
		
		
		do {
			menu();
			System.out.print("\nOpcao: ");
			opcao = teclado.nextInt();
			
			switch (opcao) {
			case 1:
				addContato(lc);
				esperaParaContinuar();
				break;
			case 2: 
				editarContato(lc);
				esperaParaContinuar();
				break;
			case 3:
				removeContato(lc);
				esperaParaContinuar();
				break;
			case 4:
				ordenaLista(lc);
				esperaParaContinuar();
				break;
			case 5:
				imprimeAniversariantes(lc);
				esperaParaContinuar();
				break;
			case 6:
				imprimirEtiquetas(lc);
				esperaParaContinuar();
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Opcao inexistente");
				break;
			}
		} while (opcao != 7);
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
		Endereco endereco;
		int opcao;
		int dia; 
		int mes;
		int ano;
		CPF cpf;
		boolean flag = false;
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite o nome do contato: ");
		contato = new Contato(teclado.nextLine());
		System.out.println("Data de nascimento");
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
		teclado.nextLine();
		do {
			if(flag) System.out.println("CPF inválido! Verifique os dados e digite novamente.");
			System.out.print("Digite o CPF: ");
			cpf = new CPF(teclado.nextLine());
			flag = true;
		} while(!cpf.isValid());
		contato.setCPF(cpf.toString());
		System.out.println("Deseja adicionar um endereço?");
		do {
			System.out.println("1 - Sim \n2 - Não" );
			opcao = teclado.nextInt();
			switch (opcao) {
			case 1:
				addEndereco(contato);
				break;
			case 2:
				break;
			}
		} while (opcao !=1 && opcao !=2);
		System.out.println("Cadastro finalizado!");
		l.addContato(contato);
	}
	
	private static Contato selecionaContato(Lista l) {
		ArrayList<Contato> contatos = null;
		Scanner teclado = new Scanner(System.in);
		boolean flag = false;
		int selecao;
		int i;
		do {
			if(flag) System.out.println("Contato inexistente! Verifique os dados e digite novamente.");
			System.out.print("Digite o nome do contato: ");
			contatos = l.search(teclado.nextLine());
			flag = true;
		} while(contatos == null);
		flag = false;
		if(contatos.size() == 1) return contatos.get(0);
		do {
			if(flag) System.out.println("Opção indisponível. Verifique os dados e digite novamente.");
			System.out.println("Mais de uma opção disponível. Escolha o número do contato: ");
			i = 0;
			for(Contato c : contatos) {
				System.out.println(i + " - " + c);
				i++;
			}
			selecao = teclado.nextInt();
			flag = true;
		} while(selecao < 0 || selecao > contatos.size() - 1);
		return contatos.get(selecao);
	}
	
	private static void editarContato(Lista l) {
		String cpf;
		Endereco endereco;
		int opcao;
		boolean flag = false;
		boolean success = false;
		Scanner teclado = new Scanner(System.in);
		Contato contato = selecionaContato(l);
		System.out.println("Informe o dado que deseja alterar: ");
		do {
			if(flag) System.out.println("Opção inválida! Verifique os dados e digite novamente.");
			System.out.println("1 - Nome");
			System.out.println("2 - Data de nascimento");
			System.out.println("3 - CPF");
			System.out.println("4 - Endereço");
			opcao = teclado.nextInt();
			flag = true;
		} while(opcao < 1 || opcao > 4);
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
		case 4:
			do {
				System.out.println("1 - Adicionar endereço");
				System.out.println("2 - Excluir endereço");
				opcao = teclado.nextInt();
			} while(opcao < 1 || opcao > 2);
			switch(opcao) {
				case 1:
					addEndereco(contato);
					break;
				case 2:
					removeEndereco(contato);
					break;
			}
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
			System.out.println("3 - Data de nascimento");
			System.out.println();
			System.out.print("Opção: ");
			System.out.println();
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
			l.ordenaDataNascimento();
			break;
		}
		System.out.println(l.imprimeLista());
	}
	
	private static void removeContato(Lista l) {
		Contato contato = selecionaContato(l);
		if(contato == null) System.out.println("Contato inexistente");
		else {
			l.removeContato(contato);
			System.out.println("Contato removido com sucesso");
		}
	}
	
	private static void imprimeAniversariantes(Lista l) {
		System.out.println(l.imprimeAniversariantes());
	}
	
	private static void addEndereco(Contato contato) {
		Endereco endereco;
		teclado.nextLine();
		System.out.println("Digite o tipo do endereço(Ex: Casa, trabalho...): ");
		endereco = new Endereco(teclado.nextLine());
		System.out.println("Digite o logradouro: ");
		endereco.setLogradouro(teclado.nextLine());
		System.out.println("Digite o número: ");
		endereco.setNumero(teclado.nextInt());
		teclado.nextLine();
		System.out.println("Digite o complemento: ");
		endereco.setComplemento(teclado.nextLine());
		System.out.println("Digite a cidade: ");
		endereco.setCidade(teclado.nextLine());
		System.out.println("Digite o Estado: ");
		endereco.setEstado(teclado.nextLine());
		System.out.println("Digite o CEP: ");
		endereco.setCEP(teclado.nextLine());
		contato.getListaEndereco().addEndereco(endereco);
	}
	
	private static void removeEndereco(Contato contato) {
		int opcao;
		if(contato.getListaEndereco().size() == 0) {
			System.out.println("O contato não possui endereços.");
			return;
		}
		for (int i = 0 ; i < contato.getListaEndereco().size(); i++) {
			Endereco e = contato.getListaEndereco().getEndereco(i);
			System.out.println(i + ": " + e.getID() + "\n" + e.getLogradouro() + ", " + e.getNumero() + " - " + e.getCidade() + " - " + e.getEstado());
		}
		System.out.println("Selecione qual endereco você deseja excluir: ");
		do {
			opcao =teclado.nextInt();
		}while(opcao > contato.getListaEndereco().size());
		contato.getListaEndereco().removeEndereco(opcao);
	}
	
	private static void esperaParaContinuar() {
		System.out.println("Pressione enter para voltar ao menu");
		teclado.nextLine();
		teclado.nextLine();
	}
	
	private static void imprimirEtiquetas(Lista l) {
		String etiquetas = "";
		l.ordenaAZ();
		for(Contato c : l) {
			etiquetas += c.getEtiquetas();
		}
		if(etiquetas.length() == 0) System.out.println("Não há etiquetas para serem impressas.");
		else System.out.println(etiquetas);
	}
}
