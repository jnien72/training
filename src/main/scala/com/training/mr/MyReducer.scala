package com.training.mr

import java.lang
import org.apache.hadoop.io.{LongWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.{Mapper, Reducer}

class MyReducer extends Reducer[Text,Text,Text,NullWritable] {
  override def reduce(key: Text, values: lang.Iterable[Text], context: Reducer[Text, Text, Text, NullWritable]#Context): Unit = {
    val user=key.toString.split(",")(0)

    val it=values.iterator()
    var startTs:String=null
    var amount=0

    while(it.hasNext){
      val v=it.next().toString
      v match {
        case "in"=>{
          startTs=key.toString.split(",")(1)
          amount=0
        }
        case "out"=>{
          val endTs=key.toString.split(",")(1)
          val outputKey=new Text(user+","+startTs+","+endTs+","+amount)
          context.write(outputKey, NullWritable.get())
        }
        case _=>{
          amount+=v.toInt
        }
      }
    }

  }
}
