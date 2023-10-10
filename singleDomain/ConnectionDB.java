package com.example.notasjakarta.singleDomain;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionDB {
    private static final String url = "jdbc:mysql://localhost:3306/notas";
    private static final String username = "root";
    private static final String password = "";
    private static BasicDataSource pool;



    /*public static Connection getInstance() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }
    }*/
  /*public static BasicDataSource getInstance() throws SQLException {
       if (pool == null) {
           pool = new BasicDataSource();
           pool.setUrl(url);
           pool.setUsername(username);
           pool.setPassword(password);
           pool.setInitialSize(3);
           pool.setMinIdle(3);
           pool.setMaxIdle(8);
           pool.setMaxTotal(8);
       }
       return pool;
   }
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }*/
}
