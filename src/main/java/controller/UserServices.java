package controller;

import connector.Connector;
import model.Province;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServices implements CRUD<User>{
    private final String VIEW_USER_PROVINCE="select * from view_all";
    private final String CREATE_PROVINCE="insert into user(name,province_id) values(?,?)";
    private final String VIEW_1_USER="select * from view_all where id=?";
    private final String UPDATE_A_USER="update user set name=?,province_id=? where id=?";
    private final String DELETE_A_USER="delete from user where id=?";
    @Override
    public void create(User object) {
        try(Connection connection= connector.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(CREATE_PROVINCE)) {
            preparedStatement.setString(1,object.getName());
            preparedStatement.setInt(2,object.getProvince().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<User> readAll() {
        ArrayList<User> list = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(VIEW_USER_PROVINCE)) {
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = getUserInResultSet(resultSet);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static User getUserInResultSet(ResultSet resultSet) throws SQLException {
        int provinceId = resultSet.getInt("province_id");
        String provinceName = resultSet.getString("province_name");
        Province province=new Province(provinceId, provinceName);
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        User user=new User(id, name,province);
        return user;
    }

    @Override
    public User findByIndex(int index) {
        User user;
        try(Connection connection= getConnector().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(VIEW_1_USER)) {
            preparedStatement.setInt(1,index);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            user=getUserInResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void update(User object) {
        try(Connection connection= connector.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_A_USER)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2,object.getProvince().getId());
            preparedStatement.setInt(3,object.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int index) {
        try(Connection connection= connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_A_USER)) {
            preparedStatement.setInt(1,index);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }
}
