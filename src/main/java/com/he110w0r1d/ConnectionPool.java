package com.he110w0r1d;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static ConnectionPool pool=null;
    private static DataSource dataSource = null;

    private ConnectionPool(){
        Properties p = new Properties();
        InputStream stream = ConnectionPool.class.getClassLoader().getResourceAsStream("datasourcePool.properties");
        try {
            p.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //set datasource
        PoolConfiguration poolConfiguration = DataSourceFactory.parsePoolProperties(p);
        dataSource = new DataSource();
        dataSource.setPoolProperties(poolConfiguration);
    }



    public static synchronized ConnectionPool getInstance(){
        if(pool==null){
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
