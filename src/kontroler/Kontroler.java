package kontroler;

import java.io.File;
import java.util.List;

import procesor.Model;
import widok.Widok;
import widok.WidokWiadomoœci;

public class Kontroler {
	private Widok widok;
	private Model model;
	private WidokWiadomoœci widokWiadomoœci;
	
	public Kontroler(Model model, Widok widok, WidokWiadomoœci widokWiadomoœci) {
		this.widok = widok;
		this.model = model;
		this.widokWiadomoœci = widokWiadomoœci;
	}
	
	public void za³adujDane(List<File> pliki) {
		//model.wczytajDane(pliki);
		//System.out.println(model.wczytajDane(pliki).get(0).getNadawca());
		widokWiadomoœci.wyœwietlDane(model.wczytajDane(pliki));
		//widok.wyœwietlDane(model.wczytajDane(new File(œcie¿ka)).pobierzNadawcê());
	}
}
