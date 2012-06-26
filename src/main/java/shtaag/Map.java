/**
 * 
 */
package shtaag;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.mail.MessagingException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author takei_s
 * @date 2012/06/21
 */
public class Map extends Mapper<LongWritable, Text, Text, IntWritable>{

	private final static IntWritable one = new IntWritable();
	private Text word = new Text();

	private static final String CONF = "conf.xml";
	private Configuration conf; 
	private MailService service = new MailService();

	@Override
	public void setup(Context context) {
		this.conf = new Configuration();
		conf.addResource(CONF);

	}
	
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		Counter counter = context.getCounter("MyCounters", "NUM_TOKENS");
		DataContainer data = new DataContainer(conf, value);
		
		try {
			service.sendMail(data);
			counter.increment(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
//		String line = value.toString();
//		StringTokenizer tokenizer = new StringTokenizer(line);
//		while (tokenizer.hasMoreTokens()) {
//			word.set(tokenizer.nextToken());
//			context.write(word, one);
//		}
	}
	
}
