package procesor.wej�cie;

import javafx.beans.property.SimpleStringProperty;

public class Meil {
	private final SimpleStringProperty od;
	private final SimpleStringProperty dataPrzes�ania;
	private final SimpleStringProperty temat;
	private final SimpleStringProperty tre��;
	
	public Meil(String od, String dataPrzes�ania, String temat, String tre��) {
		this.od = new SimpleStringProperty(od);
		this.dataPrzes�ania = new SimpleStringProperty(dataPrzes�ania);	
		this.temat = new SimpleStringProperty(temat);
		this.tre�� = new SimpleStringProperty(tre��);		
	}

	public String getNadawca() {
		return od.get();
	}

    public void setNadawca(String od) {
    	this.od.set(od);
    }

	public String getDataPrzes�ania() {
		return dataPrzes�ania.get();
	}

    public void setDataPrzes�ania(String dataPrzes�ania) {
    	this.dataPrzes�ania.set(dataPrzes�ania);
    }
    
	public String getTemat() {
		return temat.get();
	}

    public void setTemat(String temat) {
    	this.temat.set(temat);
    }
	
	public String getTre��() {
		return tre��.get();
	}

    public void setTre��(String tre��) {
    	this.tre��.set(tre��);
    }
}
