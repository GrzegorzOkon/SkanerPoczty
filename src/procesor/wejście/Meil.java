package procesor.wej�cie;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Meil {
	private final SimpleStringProperty nadawca;
	private final SimpleStringProperty odbiorcy;
	private final SimpleStringProperty dataPrzes�ania;
	private final SimpleStringProperty temat;
	private final SimpleStringProperty tre��;
	
	public Meil(String nadawca, String odbiorcy, String dataPrzes�ania, String temat, String tre��) {
		this.nadawca = new SimpleStringProperty(nadawca);
		this.odbiorcy = new SimpleStringProperty(odbiorcy);
		this.dataPrzes�ania = new SimpleStringProperty(dataPrzes�ania);	
		this.temat = new SimpleStringProperty(temat);
		this.tre�� = new SimpleStringProperty(tre��);		
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
