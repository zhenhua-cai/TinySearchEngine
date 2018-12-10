package DBConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {

    //private static final String SERVERIP="localhost";//18.218.112.101
    private static final String SERVERIP="18.218.112.101";//18.218.112.101
    private static final String USER="tinysearch";
    private static final String PASSWORD="tinysearch";
    private static final String DBNAME ="tinysearch";
    private static final int PORT=3306;

    private static Connection connection=null;
    private static PreparedStatement preparedStatement=null;
    private static boolean isConnectable=false;

    static{
        try {
            DBConnection.connectDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * connect to the mysql database;
     * @return return true if successfully connect DB, otherwise return false
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection =DriverManager
                .getConnection("jdbc:mysql://"+SERVERIP+":"+PORT+"/"+ DBNAME +"?",
                        USER, PASSWORD);
        isConnectable=true;
    }

    /**
     *  create table.
     *  If the db connection hasn't been established,this method throws NullPointerException
     *  @param table table name
     *  @param primaryKey primary key.
     *  @param pairs name and type pairs.
     * @throws SQLException
     */
    public static void createTables(String table,String primaryKey, NameTypePair...pairs) throws SQLException {
        StringBuilder command=new StringBuilder("create table "+ table+"( ");
        for(NameTypePair p:pairs)
            command.append(p.name+" "+p.type+", ");
        command.deleteCharAt(command.length()-2);
        command.append("primary key ("+primaryKey+")");
        command.append(");");
        preparedStatement = connection.prepareStatement(command.toString());
        preparedStatement.executeUpdate();

    }
    /**
     * select column from table.
     * If the db connection hasn't been established,this method throws NullPointerException
     * @param table table name
     * @param col column names
     * @return return ResultSet
     * @throws SQLException
     */
    public static ResultSet search(String table,String condition, String ...col) throws SQLException {
        //mysql select command
        StringBuilder command=new StringBuilder("select ");
        for(String s:col)
            command.append(s+", ");
        command.deleteCharAt(command.length()-2);
        command.append(" from "+table);
        if(col.length>0){
            command.append(" where "+condition);
        }
        command.append(";");

        //execute mysql select command.
      //  System.out.println(command.toString());
        ResultSet result=null;
        preparedStatement = connection.prepareStatement(command.toString());
        result = preparedStatement.executeQuery();
        return result;
    }

    public static ResultSet search(String command) throws SQLException {
        ResultSet result=null;
        preparedStatement = connection.prepareStatement(command);
        result = preparedStatement.executeQuery();
        return result;
    }

    /**
     * insert values into table.
     * If the db connection hasn't been established,this method throws NullPointerException
     * @param table table names
     * @param pairs name and value pairs.
     * @throws SQLException
     */
    public static void insert(String table, NameValuePair... pairs) throws SQLException {

        List<String> names=new LinkedList<>();
        List<Object> values= new LinkedList<>();
        for(NameValuePair p:pairs){
            names.add(p.name);
            values.add(p.value);
        }
        StringBuilder command=new StringBuilder("insert into "+table+"(");
        names.forEach(n->{
            command.append(n+", ");
        });
        command.deleteCharAt(command.length()-2);
        command.append(") values(");
        values.forEach(v->{
            command.append("\""+v+"\", ");
        });
        command.deleteCharAt(command.length()-2);
        command.append(");");
        //System.out.println(command.toString());
        preparedStatement= connection.prepareStatement(command.toString());
        preparedStatement.executeUpdate();
    }

    /**
     * insert data into db.
     * @param command insert command
     * @throws SQLException exception
     */
    public static void insert(String command) throws SQLException {
        preparedStatement = connection.prepareStatement(command);
        preparedStatement.executeUpdate();
    }
    /**
     * insert all columns into table.
     * @param table table name.
     * @param values values
     * @throws SQLException
     */
    public static void insertAll(String table,String ... values) throws SQLException {
        StringBuilder command=new StringBuilder("insert into "+table+" values(");
        for(String v:values){
            command.append("?,");
        }
        command.deleteCharAt(command.length()-1);
        command.append(")");
        preparedStatement=connection.prepareStatement(command.toString());
        setValus(values);
    }

    /**
     * set values for insertAll method.
     * call this method after calling inertAll method.
     * @param values
     * @throws SQLException
     */
    public static void setValus(String...values) throws SQLException {
        int index=1;
        for(String v:values){
            preparedStatement.setString(index++,v);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * delete command
     * @param command delete command
     * @throws SQLException exception
     */
    public static void delete(String command) throws SQLException {
        preparedStatement = connection.prepareStatement(command);
        preparedStatement.executeUpdate();
    }
    /**
     * delete row in the table.
     * @param table table name.
     * @param condition condition to delete row.
     * @throws SQLException
     */
    public static void delete(String table, String condition) throws SQLException {
        String command="delete from "+table+" where "+condition+";";
        preparedStatement=connection.prepareStatement(command);
        preparedStatement.executeUpdate();
    }

    /**
     * update DB
     * @param table table name
     * @param col column name
     * @param value value
     * @param condition condition to update table
     * @throws SQLException
     */
    public static void updateDB(String table,String col,String value,String condition) throws SQLException {
        String command="update "+table+" set "+col+"='"+value+"' where "+condition+";";
        preparedStatement=connection.prepareStatement(command);
        preparedStatement.executeUpdate();
    }
    public static void updateDB(String command) throws SQLException {
        preparedStatement=connection.prepareStatement(command);
       // System.out.println(command);
        preparedStatement.executeUpdate();
    }
    /**
     * get the mysql database connection.
     * @return return the connection.
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * close the database connection.
     * @throws SQLException
     */
    public static void closeDB() throws SQLException {
        //TODO close db
        if(isConnectable) {
            connection.close();
            if(preparedStatement!=null) preparedStatement.close();
            isConnectable=false;
        }
    }
}
