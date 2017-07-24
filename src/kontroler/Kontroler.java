package kontroler;

import java.io.File;
import java.util.List;

import procesor.Model;
import widok.Widok;
import widok.WidokWiadomo�ci;

public class Kontroler {
	private Widok widok;
	private Model model;
	private WidokWiadomo�ci widokWiadomo�ci;
	
	public Kontroler(Model model, Widok widok, WidokWiadomo�ci widokWiadomo�ci) {
		this.widok = widok;
		this.model = model;
		this.widokWiadomo�ci = widokWiadomo�ci;
	}
	
	public void za�adujDane(List<File> pliki) {
		//model.wczytajDane(pliki);
		//System.out.println(model.wczytajDane(pliki).get(0).getNadawca());
		widokWiadomo�ci.wy�wietlDane(model.wczytajDane(pliki));
		//widok.wy�wietlDane(model.wczytajDane(new File(�cie�ka)).pobierzNadawc�());
	}
}
