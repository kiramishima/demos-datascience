package org.kiramishima.labs

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.{Job, Mapper}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

import java.lang
import java.io.IOException

object SingleMapper {

  /*
        Date,Id,Temperature
        2018-01-01,1,21
        2018-01-01,2,22
        */
  object TemperatureMapper extends Mapper[Object, Text, Text, IntWritable] {
    override
    def map(key: Object, value: Text, context: Mapper[Object, Text, Text, IntWritable]#Context): Unit = {
      val txt = value.toString
      val tokens = txt.split(",")
      val date = tokens(0)
      val id = tokens(1).trim
      val temperature = tokens(2).trim
      System.out.println(String.format("%s, %s", id, temperature))
      System.out.println(temperature.compareTo("Temperature"))
      if (temperature.compareTo("Temperature") != 0) context.write(new Text(id), new IntWritable(temperature.toInt))
    }
  }

  def main(args: Array[String]): Unit = {
    println(args)
    val conf = new Configuration()
    val job = Job.getInstance(conf, "Temp Job")
    job.setJarByClass(SingleMapper.getClass)
    job.setMapperClass(SingleMapper.TemperatureMapper.getClass)
    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path(args(1)))
    System.exit(
      if (job.waitForCompletion(true)) 0
      else 1
    )
  }
}