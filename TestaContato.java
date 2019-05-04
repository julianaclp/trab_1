
public class TestaContato {

	public static void main(String[] args) {
		Contato contato = new Contato("Juliana");
		//contato.setCPF("09489104965");
		//System.out.println(contato.getCPF());
		System.out.println(contato.validaDataNasc(30, 9, 2000));

	}

}
