/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryController {
    public static List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            //lay tat ca danh sach
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select * from category";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("id"),
                    resultSet.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return categoryList;
    }
    
   
}
