/**
 * 
 */
package shtaag;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author takei_s
 * @date 2012/06/21
 */
public class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {


	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
		throws IOException, InterruptedException {  
		int sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
		}
		
		context.write(key, new IntWritable(sum));
	}
	
}
