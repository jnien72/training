package com.training.mr

import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper

class MyMapper extends Mapper[LongWritable,Text,Text,Text] {
  val outputKey = new Text()
  val outputValue = new Text()

  override def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    try {
      val line = value.toString
      outputKey.set(line)
      context.write(outputKey, outputValue)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
