/**
 * 
 */
package shtaag;


import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takei_s
 * @date 2012/06/26
 */
public class MailServiceTest {

	@Mocked
	private Configuration conf;
	@Mocked
	private Text text;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSendMail() throws Exception {
		
		new NonStrictExpectations() {
			{
				conf.get("smtp-host");
				result = "takei.shg@gmail.com";
			}
		};
		MailService service = new MailService();
		DataContainer data = new DataContainer(conf, text);
		service.sendMail(data);
	}

}
