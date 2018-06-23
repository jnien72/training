package com.training.mr

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner

class MyPartitioner extends HashPartitioner[Text,Text] {

  val myPartitionKey=new Text()
  override def getPartition(key: Text, value: Text, numReduceTasks: Int): Int = {
    val user=key.toString.split(",")(0)
    myPartitionKey.set(user)
    super.getPartition(myPartitionKey, value, numReduceTasks)
  }
}
