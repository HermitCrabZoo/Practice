package com.zoo.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 流量统计的MapReduce程序
 * @author ZOO
 *
 */
public class TrafficAdder extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new TrafficAdder(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		if (args.length < 2) {
			System.err.println("Usage: wordcount <in> [<in>...] <out>");
			return 0;
		}
		// 在这步之前ToolRunner已经设置/创建了Configuration对象
		Configuration conf = getConf();
		// 创建job，设置配置和job名称
		Job job = Job.getInstance(conf, "traffic adder");
		// 设置job运行的类
		job.setJarByClass(TrafficAdder.class);
		// 设置Mapper和Reducer类
		job.setMapperClass(TrafficMapper.class);
		job.setCombinerClass(TrafficReducer.class);
		job.setReducerClass(TrafficReducer.class);

		// 设置输出结果的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(TrafficWritable.class);

		// 设置输入和输出文件的目录
		for (int i = 0; i < args.length - 1; ++i) {
			FileInputFormat.addInputPath(job, new Path(args[i]));
		}
		FileOutputFormat.setOutputPath(job, new Path(args[args.length - 1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static class TrafficMapper extends Mapper<LongWritable, Text, Text, TrafficWritable>{
		private Text phone=new Text();
		private TrafficWritable traffic=new TrafficWritable();
		
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] values=value.toString().split("\t");
			phone.set(values[0]);
			traffic.set(toInt(values[1]), toInt(values[2]), toInt(values[3]), toInt(values[4]));
			context.write(phone, traffic);
		}
	}
	
	public static class TrafficReducer extends Reducer<Text, TrafficWritable, Text, TrafficWritable>{
		
		private TrafficWritable traffic=new TrafficWritable();
		
		@Override
		protected void reduce(Text key, Iterable<TrafficWritable> values,
				Context context) throws IOException, InterruptedException {
			int upPackNum=0;
			int upPayLoad=0;
			int downPackNum=0;
			int downPayLoad=0;
			
			for (TrafficWritable tw:values) {
				upPackNum+=tw.getUpPackNum();
				upPayLoad+=tw.getUpPayLoad();
				downPackNum+=tw.getDownPackNum();
				downPayLoad+=tw.getDownPayLoad();
			}
			traffic.set(upPackNum, upPayLoad, downPackNum, downPayLoad);
			context.write(key, traffic);
		}
	}
	
	public static int toInt(String str) {
		try {
			int v = Integer.parseInt(str);
			return v;
		} catch (Exception e) {
		}
		return 0;
	}
}
