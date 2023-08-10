/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductController {
    public static List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select product.*, category.name categoryName, "
                        + "staffCreated.fullname staffCreatedName, staffUpdated.fullname staffUpdatedName "
                        + "from product join category on product.category_id = category.id "
                        + "join staff staffCreated on staffCreated.id = product.id_staff_created "
                        + "join staff staffUpdated on staffUpdated.id = product.id_staff_updated";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Product prd = new Product(resultSet.getInt("id"),
                    resultSet.getInt("product.category_id"),
                    resultSet.getInt("price"),
                    resultSet.getInt("count"), 
                    resultSet.getInt("id_staff_created"), 
                    resultSet.getInt("id_staff_updated"), 
                    resultSet.getString("categoryName"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("linkimage"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffCreatedName"), 
                    resultSet.getString("staffUpdatedName"));
                productList.add(prd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return productList;
    }
    
    public static void insert(Product prd) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "insert into product(title, category_id, price, description, linkimage, "
                    + "created_at, updated_at, id_staff_created, id_staff_updated, count) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, prd.getTitle());
            statement.setInt(2, prd.getCategoryId());
            statement.setInt(3, prd.getPrice());
            statement.setString(4, prd.getDescription());
            statement.setString(5, prd.getThumbnail());
            statement.setString(6, prd.getCreatedAt());
            statement.setString(7, prd.getUpdatedAt());
            statement.setInt(8, prd.getIdStaffCreated());
            statement.setInt(9, prd.getIdUpdatedStaff());
            statement.setInt(10, prd.getCount());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }

    public static void update(Product prd) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "update product set title = ?, category_id = ?, price = ?, "
                    + "description = ?, linkimage = ?, id_staff_updated = ?,  updated_at = ? "
                    + "where id = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, prd.getTitle());
            statement.setInt(2, prd.getCategoryId());
            statement.setInt(3, prd.getPrice());
            statement.setString(4, prd.getDescription());
            statement.setString(5, prd.getThumbnail());
            statement.setInt(6, prd.getIdUpdatedStaff());
            statement.setString(7, prd.getUpdatedAt());
            statement.setInt(8, prd.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void updateCount(Product prd) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "update product set count = ?, id_staff_updated = ?,  updated_at = ? where id = ?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, prd.getCount());
            statement.setInt(2, prd.getIdUpdatedStaff());
            statement.setString(3, prd.getUpdatedAt());
            statement.setInt(4, prd.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }

    public static List<Product> findByTitleProduct(String title) {
        List<Product> productList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        try {
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select product.*, category.name categoryName, "
                        + "staffCreated.fullname staffCreatedName, staffUpdated.fullname staffUpdatedName "
                        + "from product join category on product.category_id = category.id "
                        + "join staff staffCreated on staffCreated.id = product.id_staff_created "
                        + "join staff staffUpdated on staffUpdated.id = product.id_staff_updated "
                        + "where title like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + title + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Product prd = new Product(resultSet.getInt("id"),
                    resultSet.getInt("product.category_id"),
                    resultSet.getInt("price"),
                    resultSet.getInt("count"), 
                    resultSet.getInt("id_staff_created"), 
                    resultSet.getInt("id_staff_updated"), 
                    resultSet.getString("categoryName"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("linkimage"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffCreatedName"), 
                    resultSet.getString("staffUpdatedName"));
                productList.add(prd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return productList;
    }
    
    public static boolean findByIdProduct(int id) {      
        Connection conn = null;
        boolean check = true;
        List<Product> productList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql =  "select product.*, category.name categoryName, "
                        + "staffCreated.fullname staffCreatedName, staffUpdated.fullname staffUpdatedName "
                        + "from product join category on product.category_id = category.id "
                        + "join staff staffCreated on staffCreated.id = product.id_staff_created "
                        + "join staff staffUpdated on staffUpdated.id = product.id_staff_updated "
                        + "where product.id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Product prd = new Product(resultSet.getInt("id"),
                    resultSet.getInt("product.category_id"),
                    resultSet.getInt("price"),
                    resultSet.getInt("count"), 
                    resultSet.getInt("id_staff_created"), 
                    resultSet.getInt("id_staff_updated"), 
                    resultSet.getString("categoryName"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("linkimage"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffCreatedName"), 
                    resultSet.getString("staffUpdatedName"));
                
                productList.add(prd);
            }
            if(productList.isEmpty()) {
                check = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return check;
    }
    
    public static boolean findByCountProduct(int id, int count) {      
        Connection conn = null;
        boolean check = true;
        List<Product> productList = new ArrayList<>();
        PreparedStatement statement = null;
        try {
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql =  "select product.*, category.name categoryName, "
                        + "staffCreated.fullname staffCreatedName, staffUpdated.fullname staffUpdatedName "
                        + "from product join category on product.category_id = category.id "
                        + "join staff staffCreated on staffCreated.id = product.id_staff_created "
                        + "join staff staffUpdated on staffUpdated.id = product.id_staff_updated "
                        + "where product.id = ? and product.count >= ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, count);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Product prd = new Product(resultSet.getInt("id"),
                    resultSet.getInt("product.category_id"),
                    resultSet.getInt("price"),
                    resultSet.getInt("count"), 
                    resultSet.getInt("id_staff_created"), 
                    resultSet.getInt("id_staff_updated"), 
                    resultSet.getString("categoryName"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("linkimage"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffCreatedName"), 
                    resultSet.getString("staffUpdatedName"));
                
                productList.add(prd);
            }
            if(productList.isEmpty()) {
                check = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return check;
    }
    
    public static int findCountProduct(int id) {      
        Connection conn = null;
        int count = 0;
        PreparedStatement statement = null;
        try {
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql =  "select count from product where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return count;
    }

    public static boolean  deleteProduct(int id){
         Connection conn = null;
        PreparedStatement statement = null;
        return true;
    }
    
}


