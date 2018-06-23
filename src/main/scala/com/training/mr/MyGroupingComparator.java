package com.training.mr;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupingComparator extends WritableComparator {

    public MyGroupingComparator() {
        super(Text.class,true);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        String userA=w1.toString().split(",")[0];
        String userB=w2.toString().split(",")[0];
        return userA.compareTo(userB);
    }
}