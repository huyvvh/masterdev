package com.huyvv20;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistinctMapper
        extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text number = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        number.set("0");
        context.write(number, one);
    }
}
