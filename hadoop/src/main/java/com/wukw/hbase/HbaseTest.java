package com.wukw.hbase;

import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.*;

import java.util.ArrayList;
import java.util.List;

public class HbaseTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("info");
        list.add("id");
        HbaseClient.client.creatTable("teacher", list);
        HbaseClient.client.insert("teacher", "wukw", "info", "age", "23");
        HbaseClient.client.insert("teacher", "wukw", "info", "address", "hangzhou");

        System.out.println("查询teacher");
        HbaseClient.client.scan("teacher", null);

        System.out.println("查询值等于杭州");
        HbaseClient.client.scan("teacher", new ValueFilter(CompareOperator.EQUAL, new BinaryComparator("hangzhou".getBytes())));

        System.out.println("查询rowkey等于wkw");
        HbaseClient.client.scan("teacher", new RowFilter(CompareOperator.EQUAL, new BinaryComparator("wukw".getBytes())));

        System.out.println("查询rowkey包含w");
        HbaseClient.client.scan("teacher", new RowFilter(CompareOperator.EQUAL, new BinaryPrefixComparator("w".getBytes())));

        System.out.println("查询column包含ad");
        HbaseClient.client.scan("teacher", new ColumnPrefixFilter("ad".getBytes()));
        System.out.println("结束");
    }
}
