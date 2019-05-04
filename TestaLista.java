import java.util.Iterator;

public class TestaLista {

	public static void main(String[] args) {
		Lista lc = Lista.getInstance();
		lc.testeAddContato(new Contato("Joao", 2, 1, 2000));
		lc.testeAddContato(new Contato("Maria", 2, 2, 1999));
		lc.testeAddContato(new Contato("Pedro", 5, 11, 2001));
		lc.testeAddContato(new Contato("Tiago", 10, 12, 2000));
		lc.testeAddContato(new Contato("Jose", 4, 4, 2000));
		lc.testeAddContato(new Contato("Lucas", 22, 10, 2010));
		lc.testeAddContato(new Contato("Mateus", 3, 1, 1950));
		lc.testeAddContato(new Contato("FátiMa"));
		
		System.out.println("\n--- Ordem de insercao ---\n");
		for (int i = 0; i < lc.size(); i++) {
			System.out.println(i + ":" + lc.getContato(i));
		}
		
		lc.ordenaAZ();
		System.out.println("\n--- Alfabetica A-Z  ---\n");
		for (int i = 0; i < lc.size(); i++) {
			System.out.println(i + ":" + lc.getContato(i));
		}
		
		lc.ordenaZA();
		System.out.println("\n--- Alfabetica Z-A ---\n");
		for (int i = 0; i < lc.size(); i++) {
			System.out.println(i + ":" + lc.getContato(i));
		}
		
		lc.ordenaAniversarioJanDez();
		System.out.println("\n--- Ordem de aniversarios no ano ---\n");
		
		for (int i = 0; i < lc.size(); i++) {
			Contato c = lc.getContato(i);
			System.out.println(i + ":" + c.getNome() + " (" + c.getDataNascString()  + ")");
		}
		
		// IDEM loop acima, mas usando iterator()
		Iterator<Contato> it = lc.iterator();
		while (it.hasNext()) {
			Contato c = it.next();
			System.out.println(c.getNome() + " - " + c.getDataNascString());
		}

		// IDEM loop acima, mas usando o for each 
		for (Contato c : lc) {
			System.out.println(c.getNome() + " - " + c.getDataNascString());
		}
	}

}
