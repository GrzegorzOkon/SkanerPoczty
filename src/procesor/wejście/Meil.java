package procesor.wejście;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Meil {
	private final SimpleStringProperty nadawca;
	private final SimpleStringProperty odbiorcy;
	private final SimpleStringProperty dataPrzesłania;
	private final SimpleStringProperty temat;
	private final SimpleStringProperty adresIP;
	
	public Meil(String nadawca, String odbiorcy, String dataPrzesłania, String temat, String adresIP) {
		this.nadawca = new SimpleStringProperty(nadawca);
		this.odbiorcy = new SimpleStringProperty(odbiorcy);
		this.dataPrzesłania = new SimpleStringProperty(dataPrzesłania);	
		this.temat = new SimpleStringProperty(temat);
		this.adresIP = new SimpleStringProperty(adresIP);		
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
    
	public String getDataPrzesłania() {
		return dataPrzesłania.get();
	}

    public void setDataPrzesłania(String dataPrzesłania) {
    	this.dataPrzesłania.set(dataPrzesłania);
    }
    
	public String getTemat() {
		return temat.get();
	}

    public void setTemat(String temat) {
    	this.temat.set(temat);
    }
	
	public String getAdresIP() {
		return adresIP.get();
	}

    public void setAdresIP(String adresIP) {
    	this.adresIP.set(adresIP);
    }
}
