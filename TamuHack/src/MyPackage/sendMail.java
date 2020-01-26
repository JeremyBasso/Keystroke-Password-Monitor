package MyPackage;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;

public class sendMail {

	public sendMail(String recepient) throws MessagingException {
		System.out.println("Preparing to send mail");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		String myAccountEmail = "Jeremybasso12345@gmail.com";
		String password = "Icandothis";
			
			Session session = Session.getInstance(properties, new Authenticator()
					{
						@Override
						protected PasswordAuthentication getPasswordAuthentication()
						{
							return new PasswordAuthentication(myAccountEmail, password);
						}
					});
			Message message = prepareMEssage(session, myAccountEmail, recepient);
			
				Transport.send(message);
				System.out.println("Mail has been sent");
	}
		private static Message prepareMEssage(Session session, String myAccountEmail, String recepient ) {
		try {
	
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress(myAccountEmail));
	message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
	message.setSubject("CAUTION");
	message.setText("We noticed your password was entered a little differently this time and wanted to make sure it was you?");
	return message;
		}
	catch (Exception ex) {
		Logger.getLogger(sendMail.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
	}
}

	
