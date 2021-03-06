package kontroler;

import java.io.File;
import java.util.List;

import procesor.Model;
import widok.Widok;
import widok.WidokWiadomości;

public class Kontroler {
	private Widok widok;
	private Model model;
	private WidokWiadomości widokWiadomości;
	
	public Kontroler(Model model, Widok widok, WidokWiadomości widokWiadomości) {
		this.widok = widok;
		this.model = model;
		this.widokWiadomości = widokWiadomości;
	}
	
	public void załadujDane(List<File> pliki) {
		//model.wczytajDane(pliki);
		//System.out.println(model.wczytajDane(pliki).get(0).getNadawca());
		widokWiadomości.wyświetlDane(model.wczytajDane(pliki));
		//widok.wyświetlDane(model.wczytajDane(new File(ścieżka)).pobierzNadawcę());
	}
}
