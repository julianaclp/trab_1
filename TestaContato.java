
public class TestaContato {

	public static void main(String[] args) {
		Contato contato = new Contato("Juliana");
		//contato.setCPF("09489104965");
		//System.out.println(contato.getCPF());
		Endereco e = new Endereco("Casa", 
				"R Jacob Romanichen", 
				146,
				"Curitiba",
				"PR",
				"81710430",
				"--");
		contato.addEndereco(e);
		System.out.println(contato.validaDataNasc(1, 15, 2000));
		

	}

}
