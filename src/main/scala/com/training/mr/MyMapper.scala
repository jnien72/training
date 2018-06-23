package com.training.mr

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class MyMapper extends Mapper[LongWritable,Text,Text,Text] {

  val outputKey = new Text()
  val outputValue = new Text()

  override def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    try {
      val line = value.toString
      val tokens=line.split(",")

      val user=tokens(0)
      val ts=tokens(1)
      val amountOrInOut=tokens(2)

      outputKey.set(user+","+ts)
      outputValue.set(amountOrInOut)

      context.write(outputKey,outputValue)

    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
