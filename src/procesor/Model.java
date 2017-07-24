package procesor;

import procesor.wejœcie.Meil;
import static procesor.wejœcie.ImporterMeili.*;
import procesor.wejœcie.ImporterMeili;

import java.io.File;
import java.util.List;

public class Model {
	
	public List<Meil> wczytajDane(List<File> pliki) {
		List<Meil> meil = null;
		
		try {
			//meil = ImporterMeili.pobierzInstancjê().wczytajMeil(plikEml);
			meil = ImporterMeili.wczytajMeile(pliki);
		} catch(Exception ex) {			
		}
		
		return meil;
	}
}
