package com.example.webshopdat21b.repository;

import com.example.webshopdat21b.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    //database-url, user og pwd flyttes til application.properties - kan flyttes til config
    //private final static String DB_URL = "jdbc:mysql://localhost:3306/webshop";
    //private final static String UID = "webshop_user"; //"root";
    //private final static String PWD = "LangTekstDerErLetAtHuske27."; //"qJiw03K2zwJD";
    private static String DB_URL;
    private static String UID;
    private static String PWD;

    private Connection connection = null;
    private Environment environment;

    //dependency injection af environment variable
    //@Autowired //tvinger Spring til at bruge denne constructor
    public ProductRepository(Environment env){
        environment = env;
    }

    //extra constructor - vælges som default
    //Ypublic ProductRepository(){}

    //get connection
    public Connection getConnection() {
        //connection er en singleton
        //connection already initialized?
        DB_URL = environment.getProperty("spring.datasource.url");
        UID = environment.getProperty("spring.datasource.username");
        PWD = environment.getProperty("spring.datasource.password");

        if (connection != null) return connection;

        //initialize connection
        try {
            connection = DriverManager.getConnection(DB_URL, UID, PWD);

        } catch (SQLException e) {
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

    public void addProduct(Product product) {
        //connect
        getConnection();
        try {
            //prep statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO product(name, price) VALUES (?, ?)");
            //set attributer
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Could not create");
            sqlException.printStackTrace();
        }
    }

    public void deleteById(int id) {
        //connect
        getConnection();
        try {
            //create prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM product WHERE id = ?"
            );
            //set parameter
            preparedStatement.setInt(1, id);
            //execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("Could not delete!");
            sqlException.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        final String UPDATE_QUERY = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        int id = product.getId();
        String name = product.getName();
        int price = product.getPrice();
        Connection con = getConnection();    //Connection
        try {
            PreparedStatement psUpdateRow = con.prepareStatement(UPDATE_QUERY);  //prepared statement
            psUpdateRow.setString(1, name);
            psUpdateRow.setInt(2, price);
            psUpdateRow.setInt(3, id);
            psUpdateRow.executeUpdate();  // Execute query
            System.out.println("Row updated");
        } catch (SQLException e) {
            System.out.println("Could not update");
            e.printStackTrace();
        }
    }

    public Product findProductById(int id){
        Connection con = getConnection(); //connection
        try {

            Statement s = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM product WHERE id = ?"; //" WHERE id = 1 OR 1=1; --";

            PreparedStatement psProduct = con.prepareStatement(SQL_QUERY); //prepared statement

            psProduct.setInt(1, id); // set id der skal søges på
            ResultSet rs = psProduct.executeQuery();  // Execute query

            //read data from resultset
            rs.next();
            {
                int pId = rs.getInt(1);
                String pName = rs.getString(2);
                int pPrice = rs.getInt(3);
                //System.out.println(id + " " + name + " " + price);
                return new Product(pId, pName, pPrice);
            }

        } catch (SQLException e) {
            System.out.println("Could not create connection");
            e.printStackTrace();
        }
        return null; //product not found
    }

}
