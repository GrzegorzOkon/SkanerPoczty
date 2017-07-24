package procesor;

import procesor.wej�cie.Meil;
import static procesor.wej�cie.ImporterMeili.*;
import procesor.wej�cie.ImporterMeili;

import java.io.File;
import java.util.List;

public class Model {
	
	public List<Meil> wczytajDane(List<File> pliki) {
		List<Meil> meil = null;
		
		try {
			//meil = ImporterMeili.pobierzInstancj�().wczytajMeil(plikEml);
			meil = ImporterMeili.wczytajMeile(pliki);
		} catch(Exception ex) {			
		}
		
		return meil;
	}
}
