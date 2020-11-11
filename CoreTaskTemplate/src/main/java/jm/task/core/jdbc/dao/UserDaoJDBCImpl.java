package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS user(" +
                "id int(20) unsigned not null auto_increment," +
                "fname varchar(10) default null," +
                "lastName varchar(15) default null," +
                "age tinyint(10) default null," +
                "primary key(id)" +
                ") engine = innoDB auto_increment = 19 default charset = utf8;";
        try {
            Connection con = Util.connect("myDS");
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.execute();
            con.setAutoCommit(false);
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE user";
        try(Connection con = Util.connect("myDS");
            PreparedStatement pr = con.prepareStatement(query)) {
            pr.execute();
            con.setAutoCommit(false);
            con.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveUser( String name, String lastName, byte age) {
        String query = "INSERT INTO USER ( fname, lastName, age) VALUES( ?, ?, ?)";
        Connection con = null;
        try  {
            con = Util.connect("myDS");
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            con.setAutoCommit(false);
            con.commit();

        } catch (Exception ec) {
            Util.RollBack();
            System.out.println("Ошибка добавления данных");
            ec.printStackTrace();
        }
        finally {
            try{
                if (con != null){
                    con.close();
                }
            }catch (Exception r){
                r.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM USER WHERE id = ?";
        try(Connection connection = Util.connect("myDS");
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        }
        catch (Exception ex){
            Util.RollBack();
            ex.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try {
        Connection  con = Util.connect("myDS");
        Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT fname, lastName, age FROM user");
            while (resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString(1));
                user.setLastName(resultSet.getString(2));
                user.setAge(resultSet.getByte(3));
                userList.add(user);
            }
//            userList.add(user);

        }catch (Exception ex){
            ex.printStackTrace();

        }
        return userList;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE user";
        try(Connection connection = Util.connect("myDS");
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        }
        catch (Exception ex){
            Util.RollBack();
            ex.printStackTrace();
        }
    }
}
