package procesor.wejœcie;

import javafx.beans.property.SimpleStringProperty;

public class Meil {
	private final SimpleStringProperty od;
	private final SimpleStringProperty dataPrzes³ania;
	private final SimpleStringProperty temat;
	private final SimpleStringProperty treœæ;
	
	public Meil(String od, String dataPrzes³ania, String temat, String treœæ) {
		this.od = new SimpleStringProperty(od);
		this.dataPrzes³ania = new SimpleStringProperty(dataPrzes³ania);	
		this.temat = new SimpleStringProperty(temat);
		this.treœæ = new SimpleStringProperty(treœæ);		
	}

	public String getNadawca() {
		return od.get();
	}

    public void setNadawca(String od) {
    	this.od.set(od);
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
