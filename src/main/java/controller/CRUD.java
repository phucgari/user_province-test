package controller;

import connector.Connector;

import java.util.ArrayList;

public interface CRUD<E> {
    String jdbcURL = "jdbc:mysql://localhost:3306/user_province?";
    String jdbcUSER="root";
    String jdbcPW="123456";
    Connector connector=new Connector(jdbcURL,jdbcUSER,jdbcPW);
    public default Connector getConnector() {
        return connector;
    }
    void create(E object);
    ArrayList<E> readAll();
    E findByIndex(int index);
    void update(E object);
    void delete(int index);
}