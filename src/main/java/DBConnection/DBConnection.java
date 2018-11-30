package DBConnection;

import java.sql.*;

public class DBConnection {
  private static Connection connection=null;
  private static  PreparedStatement preparedStatement = null;
  private static ResultSet result=null;
  private static boolean isConnectable=false;

  private static final String SERVERIP="3.16.45.112";

    public static boolean connectDB() throws ClassNotFoundException {
        //TODO connection db
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection =DriverManager
                //   		          .getConnection("jdbc:mysql://mysql.stackcp.com:50680/csci370-3639d6cd?","csci370-3639d6cd","Www901008com");
                .getConnection("jdbc:mysql://18.224.190.100:3306/mysql?"
                                + "useSSL=true",
                        "zcai", "WWW123abccom")) {


        } catch (SQLException e) {
            e.printStackTrace();
        }

        isConnectable=true;
        return true;
    }
    public static boolean updateDB(){
        //TODO update db

        return true;
    }
    public static Connection getConnection(){
        return connection;
    }
    public static boolean closeDB(){
        //TODO close db
        if(isConnectable) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        isConnectable=false;
        return true;
    }
}
