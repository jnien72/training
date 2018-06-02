package com.training.mr

import java.lang

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.{Mapper, Reducer}

class MyReducer extends Reducer[Text,LongWritable,Text,LongWritable] {
  override def reduce(key: Text, values: lang.Iterable[LongWritable], context: Reducer[Text, LongWritable, Text, LongWritable]#Context): Unit = {
    var count=0L
    val it=values.iterator()
    while(it.hasNext){
      count+=it.next().get()
    }
    val outputValue=new LongWritable(count)
    context.write(key,outputValue)
  }
}
