package procesor.wejœcie;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
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
        	listaMeili.add(new Meil("" + message.getFrom()[0], "" + message.getSentDate(), "" + message.getSubject(), "" + message.getContent().toString()));
        }
 
        return listaMeili;
        /*System.out.println("--------------");
        System.out.println("Body : " +  message.getContent());*/
    }
}
