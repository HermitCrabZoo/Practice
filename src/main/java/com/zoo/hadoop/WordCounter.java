package com.zoo.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * 自定义MapReduce程序：word count
 * @author ZOO
 *
 */
public class WordCounter {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		//获取配置信息
		Configuration conf = new Configuration();
	    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
	    if (otherArgs.length < 2) {
	      System.err.println("Usage: wordcount <in> [<in>...] <out>");
	      System.exit(2);
	    }
	    //创建job，设置配置和job名称
	    Job job = Job.getInstance(conf, "words counter");
	    //设置job运行的类
	    job.setJarByClass(WordCounter.class);
	    //设置Mapper和Reducer类
	    job.setMapperClass(SplitMapper.class);
	    //SumReducer中输入类型与输出类型不同，所以无需设置Combiner，使用默认的Combiner
//	    job.setCombinerClass(SumReducer.class);
	    job.setReducerClass(SumReducer.class);
	    
	    //设置输出结果的key和value类型
	    job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(IntWritable.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(LongWritable.class);
	    
	    //设置输入和输出文件的目录
	    for (int i = 0; i < otherArgs.length - 1; ++i) {
	      FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
	    }
	    FileOutputFormat.setOutputPath(job,
	      new Path(otherArgs[otherArgs.length - 1]));
	    
	    //提交job，等待运行结果，并在客户端显示运行信息
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}


/**
 * 字符串分割类
 * @author ZOO
 *
 */
class SplitMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	//声明为全局属性，减少方法中new对象的开销
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		//每行字符串按：" \t\n\r\f"分割(空格、制表符、换行符、回车符、换页符)
		StringTokenizer st=new StringTokenizer(value.toString());
		while (st.hasMoreTokens()) {
			word.set(st.nextToken());
			context.write(word, one);
		}
		
	}
}

/**
 * 累加和类
 * @author ZOO
 *
 */
class SumReducer extends Reducer<Text, IntWritable, Text, LongWritable>{
	
	private LongWritable result = new LongWritable();
	
	//map中输出的结果按key分组后每条结果key:[value1,value2...]，都会成为reduce的输入。
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}


