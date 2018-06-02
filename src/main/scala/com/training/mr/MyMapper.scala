package com.training.mr

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class MyMapper extends Mapper[LongWritable,Text,Text,LongWritable] {

  val outputKey = new Text()
  val outputValue = new LongWritable(1)

  override def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, LongWritable]#Context): Unit = {
    try {
      val line = value.toString
      line.split(" ").foreach(tmp=>{
        val token=tmp.trim
        if(token.length>0){
          outputKey.set(token.toLowerCase)
          context.write(outputKey, outputValue)
        }
      })
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
