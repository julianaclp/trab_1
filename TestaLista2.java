public class TestaLista2 {

	public static void main(String[] args) {
		Lista lc1 = Lista.getInstance();

		lc1.addContato(new Contato("Joao", 2, 1, 2000));
		lc1.addContato(new Contato("Maria", 2, 2, 1999));
		lc1.addContato(new Contato("Pedro", 5, 11, 2001));
		
		Lista lc2 = Lista.getInstance();
		
		lc2.addContato(new Contato("Tiago", 10, 12, 2000));
		lc2.addContato(new Contato("Jose", 4, 4, 2000));
		lc2.addContato(new Contato("Lucas", 22, 10, 2010));
		lc2.addContato(new Contato("Mateus", 3, 1, 1950));
		
		System.out.println("\n--- Ordem de insercao ---\n");
		for (Contato c : lc1) {
			System.out.println(c.getNome() + " - " + c.getDataNascString());
		}

	}

}
