package com.wukw.hbase;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import javax.management.ObjectName;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class HbaseClient implements ClientInterface {
    public static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    public static ClientInterface client;


    static {
        HbaseClient target = new HbaseClient();
        ConnectionHandler connectionHandler = new ConnectionHandler(target);
        client = (ClientInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), connectionHandler);
    }

    public static class ConnectionHandler implements InvocationHandler {
        HbaseClient target;

        ConnectionHandler(HbaseClient hbaseClient) {
            target = hbaseClient;

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            if (!methodName.equals("getConnection")) {
                //获取连接
                Connection connection = target.getConnection();
                connectionThreadLocal.set(connection);
                Object obj = method.invoke(target, args);
                connection.close();
                return obj;
            }
            return method.invoke(target, args);
        }
    }


    static Admin admin;

    @Override
    public Connection getConnection() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "47.104.64.31");
        //configuration.set("hbase.master", "47.104.64.31:16010");
        try {
            return ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("获取连接");
        return null;

    }

    @Override
    /**
     * 创建表
     *
     * @param tableName
     * @param columnFamily
     * @return
     */
    public boolean creatTable(String tableName, List<String> columnFamily) {
        Admin admin = null;
        try {
            admin = connectionThreadLocal.get().getAdmin();

            List<ColumnFamilyDescriptor> familyDescriptors = new ArrayList<>(columnFamily.size());

            columnFamily.forEach(cf -> {
                familyDescriptors.add(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build());
            });

            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName))
                    .setColumnFamilies(familyDescriptors)
                    .build();

            if (admin.tableExists(TableName.valueOf(tableName))) {
                log.debug("table Exists!");
            } else {
                admin.createTable(tableDescriptor);
                log.debug("create table Success!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {

        }
        return true;
    }

    @Override
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
    public boolean insert(String tableNameStr, String rowKey, String columnFamily, String column, String value) {

        TableName tableName = TableName.valueOf(tableNameStr);
        try {
            Table table = connectionThreadLocal.get().getTable(tableName);
            Put put = new Put(rowKey.getBytes());
            put.addColumn(columnFamily.getBytes(), column == null ? null : column.getBytes(), value.getBytes());
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public Map<String, String> scan(String tableNameStr, Filter filter) {
        Connection connection = connectionThreadLocal.get();
        TableName tableName = TableName.valueOf(tableNameStr);
        try {
            Table table = connection.getTable(tableName);
            Scan scan = new Scan();
            scan.setFilter(filter);
            ResultScanner results = table.getScanner(scan);
            Iterator<Result> it = results.iterator();
            while (it.hasNext()) {
                Result result = it.next();
                String rowkey = Bytes.toString(result.getRow());
                for (Cell cell : result.listCells()) {
                    System.out.println("row-key:"
                            +rowkey+":"
                            +Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+"-"
                            +Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength())+":"
                            +Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
