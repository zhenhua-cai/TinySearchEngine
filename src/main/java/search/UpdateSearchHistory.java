package search;

import DBConnection.DBConnection;
import sun.security.pkcs11.Secmod;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateSearchHistory extends Thread {
    private String word;
    private long responseTime;
    public UpdateSearchHistory(String word,long responseTime){
        this.word=word.toLowerCase();
        this.responseTime=responseTime;
    }
    public void run(){
        try {
            ResultSet result=DBConnection.search("select * from user_history " +
                    "where word='"+word+"';");
            if(result.next()){
                DBConnection.updateDB("update user_history " +
                        "set frequency=frequency+1 , " +
                        "responseTime= "+responseTime+" where word='"+word+"';");
                result.close();
            }
            else{
                result.close();
                DBConnection.insert("insert into user_history values('"+word+"',"+1+","+responseTime+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
