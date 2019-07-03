package com.wukw.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class getFile {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getenv("HADOOP_HOME"));

        Configuration configuration= new Configuration();


        try {
            FileSystem fs = FileSystem.get(configuration);
            Path name = new Path("hdfs://192.168.31.156:9000/input/proxy0.class");
            FSDataInputStream inputStream = fs.open(name);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (bufferedReader.readLine() != null) {
                String line = bufferedReader.readLine();
                System.out.println("-----"+line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}
