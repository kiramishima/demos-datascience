package com.example.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SingleMapper {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // conf.set("yarn.resourcemanager.address", "http://localhost:8032");

        // Job job = new Job(conf, "Temp Job");
        Job job = Job.getInstance(conf, "Temp Job");
        // job.setJarByClass(SingleMapper.class);
        job.setMapperClass(TemperatureMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    /*
    Date,Id,Temperature
    2018-01-01,1,21
    2018-01-01,2,22
    */
    private static class TemperatureMapper extends Mapper<Object, Text, Text, IntWritable> {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String txt = value.toString();
            String[] tokens = txt.split(",");
            String date = tokens[0];
            String id = tokens[1].trim();
            String temperature = tokens[2].trim();
            // System.out.println(String.format("%s, %s", id, temperature));
            // System.out.println(temperature.compareTo("Temperature"));
            if(temperature.compareTo("Temperature") != 0) {
                context.write(new Text(id), new IntWritable(Integer.parseInt(temperature)));
            }
        }
    }
}
