package org.example.dao;

import org.example.libs.DbConnection;
import org.example.libs.MySQLException;
import org.example.libs.User;
import org.example.libs.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UsersDAO implements DAO<User> {
    private List<User> users = new LinkedList<>();

    public UsersDAO() {
        read();
    }

    private void read() {
        try {
            Connection connection = DbConnection.getConnection();
            final String query = "SELECT * FROM siteusers";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(
                        new User(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("password"))
                );
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new MySQLException();
        }

    }
    public static void insert(){
        try {
            Connection connection = DbConnection.getConnection();
            final String query = "INSERT INTO siteusers (username,password) values (?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"root");
            preparedStatement.setString(2,"4813494D137E1631BBA301D5ACAB6E7BB7AA74CE1185D456565EF51D737677B2");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean add(User element) {
        return users.add(element);
    }

    @Override
    public User get(int index) {
        return users.get(index);
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Collection<User> getAll() {
        return users;
    }
}
