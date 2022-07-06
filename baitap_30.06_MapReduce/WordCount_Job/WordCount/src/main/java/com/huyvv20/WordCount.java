package com.huyvv20;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount {
    public static class WCMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString().replaceAll("[^A-Za-z0-9]"," "));
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }


    public static class WCReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }


    public static void main(String[] args) throws Exception {
        String in = args[0];
        String out = args[1];

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WCMapper.class);
        job.setCombinerClass(WCReducer.class);
        job.setReducerClass(WCReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.getConfiguration().set("mapreduce.output.textoutputformat.separator",",");
        FileInputFormat.addInputPath(job, new Path(in));
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(new Path(out))) {
            fs.delete(new Path(out), true);
        }

        FileOutputFormat.setOutputPath(job, new Path(out));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}