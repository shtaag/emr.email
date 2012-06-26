/**
 * 
 */
package shtaag;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;

/**
 * @author takei_s
 * @date 2012/06/25
 */
public class DataContainer {
	
	private Properties props;
	private String mailFrom;
	private Text rawValue;
	
	private String body;
	private String subject;
	
	public DataContainer(Configuration conf, Text text) {
	
		props.setProperty("mail.smtp.host", conf.get("smtp-host"));
		props.setProperty("mail.host", conf.get("mail-host"));
		props.setProperty("mail.smtp.from", conf.get("smtp-from"));
		props.setProperty("mail.smtp.port", conf.get("smtp-port"));
		mailFrom = conf.get("mail-from");
		
		this.rawValue = text;
		parseText(rawValue);
	}
	
	public Address[] getRecipients() {
		try {
			return InternetAddress.parse(mailFrom, true);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String getMessageBody() {
		return body;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public Properties getProperties() {
		return props;
	}
	
	public Address[] getMailFrom() {
		try {
			return InternetAddress.parse(mailFrom, true);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void parseText(Text rawValue) {
		this.body = "not yet";
		this.subject = "not yet";
	}
	
}
