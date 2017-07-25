package procesor.wejœcie;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Meil {
	private final SimpleStringProperty nadawca;
	private final SimpleStringProperty odbiorcy;
	private final SimpleStringProperty dataPrzes³ania;
	private final SimpleStringProperty temat;
	private final SimpleStringProperty treœæ;
	
	public Meil(String nadawca, String odbiorcy, String dataPrzes³ania, String temat, String treœæ) {
		this.nadawca = new SimpleStringProperty(nadawca);
		this.odbiorcy = new SimpleStringProperty(odbiorcy);
		this.dataPrzes³ania = new SimpleStringProperty(dataPrzes³ania);	
		this.temat = new SimpleStringProperty(temat);
		this.treœæ = new SimpleStringProperty(treœæ);		
	}

	public String getNadawca() {
		return nadawca.get();
	}

    public void setNadawca(String nadawca) {
    	this.nadawca.set(nadawca);
    }

	public String getOdbiorcy() {
		return odbiorcy.get();
	}

    public void setOdbiorcy(String odbiorcy) {
    	this.odbiorcy.set(odbiorcy);
    }
    
	public String getDataPrzes³ania() {
		return dataPrzes³ania.get();
	}

    public void setDataPrzes³ania(String dataPrzes³ania) {
    	this.dataPrzes³ania.set(dataPrzes³ania);
    }
    
	public String getTemat() {
		return temat.get();
	}

    public void setTemat(String temat) {
    	this.temat.set(temat);
    }
	
	public String getTreœæ() {
		return treœæ.get();
	}

    public void setTreœæ(String treœæ) {
    	this.treœæ.set(treœæ);
    }
}
