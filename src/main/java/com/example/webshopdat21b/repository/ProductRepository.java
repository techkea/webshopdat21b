package com.example.webshopdat21b.repository;

import com.example.webshopdat21b.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/webshop";
    private final static String UID = "root";
    private final static String PWD = "qJiw03K2zwJD";

    Connection connection = null;

    //get connection
    public Connection getConnection(){
        //connection er en singleton
        //connection already initialized?
        if (connection!=null) return connection;

        //initialize connection
        try{
            connection = DriverManager.getConnection(DB_URL, UID, PWD);

        }
        catch (SQLException e){
            System.out.println("Could not connect");
            e.printStackTrace();
        }
        return connection;

    }
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        getConnection();
        try {

            Statement s = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM product"; //" WHERE id = 1 OR 1=1; --";
            ResultSet rs = s.executeQuery(SQL_QUERY);


            //read data from resultset
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                //System.out.println(id + " " + name + " " + price);
                productList.add(new Product(id, name, price));

            }

            s.close();
        } catch (
                SQLException e) {
            System.out.println("Could not create connection");
            e.printStackTrace();
        }
        return productList;
    }
}
