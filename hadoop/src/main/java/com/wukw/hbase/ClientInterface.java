package com.wukw.hbase;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.filter.Filter;

import java.util.List;
import java.util.Map;

public interface ClientInterface {

    Connection getConnection();

    /**
     * 创建表
     *
     * @param tableName
     * @param columnFamily
     * @return
     */
    boolean creatTable(String tableName, List<String> columnFamily);

    /**
     * 插入数据
     *
     * @param tableNameStr
     * @param rowKey
     * @param columnFamily
     * @param column
     * @param value
     * @return
     */
    boolean insert(String tableNameStr, String rowKey, String columnFamily, String column, String value);

    Map<String, String> scan(String tableNameStr, Filter filter);
}
