package procesor.wejœcie;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import procesor.wejœcie.Meil;

public class ImporterMeili {
	
	public static List<Meil> wczytajMeile(List<File> pliki) throws Exception {
		List<Meil> listaMeili = new ArrayList<>();
		
        Properties props = System.getProperties();
        props.put("mail.host", "smtp.dummydomain.com");
        props.put("mail.transport.protocol", "smtp");

        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = null;
        MimeMessage message = null;
        
        for (File plik : pliki) {
        	source = new FileInputStream(plik);
        	message = new MimeMessage(mailSession, source);
        	
        	Address[] froms = message.getFrom();
        	String nadawca = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
        	
        	String odbiorcy = "";
        	Address[] adresy = message.getRecipients(Message.RecipientType.TO);
        	
        	for (Address adres : adresy) {
        	    odbiorcy += adres + "\n";
        	}
        	
        	String adresIP = pobierzIP(message.getAllHeaders());
        	
        	listaMeili.add(new Meil(nadawca, odbiorcy, "" + message.getSentDate(), "" + message.getSubject(), adresIP));
        }
 
        return listaMeili;
    }
	
	private static String pobierzIP(Enumeration nag³ówki) {
		String wynik = "";
		
  	  	while (nag³ówki.hasMoreElements()) {
		  Header h = (Header) nag³ówki.nextElement();
		  
		  if (h.getName().contains("Received") && h.getValue().contains("from")) {
			  wynik = h.getName() + ": " + h.getValue();
		  }		  
  	  	}
  	  	
  	  	return wynik.substring(wynik.indexOf('[') + 1, wynik.indexOf(']'));
	}
}
