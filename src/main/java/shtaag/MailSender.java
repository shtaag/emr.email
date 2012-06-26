/**
 * 
 */
package shtaag;

import java.util.Date;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * @author takei_s
 * @date 2012/06/25
 */
public class MailSender {
	
	private Transport transport;
	
	public MailSender(Session session) throws NoSuchProviderException {
		transport = session.getTransport("smtp");
	}
	
	public void connect(String host, int port, String user, String password) throws MessagingException {
		transport.connect(host, port, user, password);
	}
	
	public void disconnect() {
		if (transport != null) {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} 
	}
	
	public MimeMessage createMessage(Session session, String subject, String body,
			Address[] envelopeTo, Address[] from) throws MessagingException {
		
		MimeMessage msg = new MimeMessage(session);
		msg.addFrom(from);
		msg.setRecipients(MimeMessage.RecipientType.TO, envelopeTo);
		msg.setHeader("X-Mailer", "JavaMail Sender");
		msg.setSubject(subject, "ISO-2022-JP");
		msg.setText(body, "ISO-2022-JP");
		msg.saveChanges();
		
		return msg;
	}
	
	public void send(MimeMessage msg) throws MessagingException {
		msg.setSentDate(new Date());
		transport.sendMessage(msg, msg.getAllRecipients());
	}
	
}
