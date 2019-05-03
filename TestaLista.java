import java.util.Iterator;
import java.util.Scanner;

public class TestaLista {

	public static void main(String[] args) {
		Lista lc = Lista.getInstance();
		lc.addContato(new Contato("Joao", 2, 1, 2000));
		lc.addContato(new Contato("Maria", 2, 2, 1999));
		lc.addContato(new Contato("Pedro", 5, 11, 2001));
		lc.addContato(new Contato("Tiago", 10, 12, 2000));
		lc.addContato(new Contato("Jose", 4, 4, 2000));
		lc.addContato(new Contato("Lucas", 22, 10, 2010));
		lc.addContato(new Contato("Mateus", 3, 1, 1950));
		lc.addContato(new Contato("F�tiMa"));
		
		Scanner teclado = new Scanner (System.in);
		String busca = teclado.nextLine();
		System.out.println(lc.search(busca));
		
		/*System.out.println("\n--- Ordem de insercao ---\n");
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
		}*/
	}

}
