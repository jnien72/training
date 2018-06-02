package com.training.mr

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{LongWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

object JobLauncher {
  def main(args:Array[String]):Unit={

    val inputPath=new Path("hdfs/input")
    val outputPath=new Path("hdfs/output")

    val conf=new Configuration()

    try {
      val fs=FileSystem.get(conf)
      fs.delete(outputPath, true)
      val job = Job.getInstance(conf)
      FileInputFormat.setInputPaths(job, inputPath)
      job.setMapperClass(classOf[MyMapper])
      job.setMapOutputKeyClass(classOf[Text])
      job.setMapOutputValueClass(classOf[LongWritable])
      job.setOutputFormatClass(classOf[TextOutputFormat[Text,NullWritable]])
      job.setReducerClass(classOf[MyReducer])
      job.setNumReduceTasks(3)
      FileOutputFormat.setOutputPath(job, outputPath)
      job.setJarByClass(job.getMapperClass())
      job.waitForCompletion(true)
    } catch {
      case t:Throwable=> {
        t.printStackTrace()
        System.exit(1)
      }
    }
  }
}