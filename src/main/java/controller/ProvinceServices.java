package controller;

import connector.Connector;
import model.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceServices implements CRUD<Province>{

    private final String READ_ALL_PROVINCE="select * from province";
    private final String FIND_A_PROVINCE="select * from province where id=?";
    private final String ADD_A_PROVINCE="insert into province(name)values (?)";
    private final String UPDATE_A_PROVINCE="update province set name=? where id=?";
    private final String DELETE_A_PROVINCE="delete from province where id=?";

    @Override
    public void create(Province object) {
        try(Connection connection= connector.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(ADD_A_PROVINCE)) {
            preparedStatement.setString(1,object.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Province> readAll() {
        try(Connection connection= connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(READ_ALL_PROVINCE)) {
            ArrayList<Province>list=new ArrayList<>();
            ResultSet resultSet =preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new Province(id, name));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Province findByIndex(int index) {
        Province result;
        try(Connection connection= getConnector().getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(FIND_A_PROVINCE)) {
            preparedStatement.setInt(1,index);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            result=new Province(id,name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(Province object) {
        try(Connection connection= getConnector().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_A_PROVINCE)) {
            preparedStatement.setString(1,object.getName());
            preparedStatement.setInt(2,object.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }

    @Override
    public void delete(int index) {
//        try(Connection connection= getConnector().getConnection();
//        PreparedStatement preparedStatement=connection.prepareStatement(DELETE_A_PROVINCE)) {
//            preparedStatement.setInt(1,index);
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
