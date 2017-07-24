package procesor;

import procesor.wejście.Meil;
import static procesor.wejście.ImporterMeili.*;
import procesor.wejście.ImporterMeili;

import java.io.File;
import java.util.List;

public class Model {
	
	public List<Meil> wczytajDane(List<File> pliki) {
		List<Meil> meil = null;
		
		try {
			//meil = ImporterMeili.pobierzInstancję().wczytajMeil(plikEml);
			meil = ImporterMeili.wczytajMeile(pliki);
		} catch(Exception ex) {			
		}
		
		return meil;
	}
}
