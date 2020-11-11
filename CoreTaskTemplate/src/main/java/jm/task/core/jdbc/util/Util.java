package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = null;
    private static String userName = "root";
    private  static String password = "toor";


    public  static Connection connect(String db) throws SQLException {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" +
                        db + "?characterEncoding = utf8&serverTimezone=UTC", userName, password);
            }
            catch (Exception ex){
                System.out.println("Driver not found");
                ex.printStackTrace();
                System.exit(1);
            }

            return connection;
    }

    public static Connection getConnection () {
        return connection;
    }

    public static void RollBack (){
        try {
            connection = getConnection();
            connection.rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
