/**
 * 
 */
package shtaag;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author takei_s
 * @date 2012/06/25
 */
public class MailService {
	
	public void sendMail(DataContainer data) throws MessagingException {
		
		Properties props = data.getProperties();
		Session session = Session.getInstance(props);
		
		MailSender sender = new MailSender(session);
		
		try {
			sender.connect(props.getProperty("mail.smtp.host"), 25, "", "");
			MimeMessage message = sender.createMessage(session,
					data.getSubject(), data.getMessageBody(), data.getRecipients(), data.getMailFrom());
			sender.send(message);
		} finally {
			sender.disconnect();
		}
		
	}

}
