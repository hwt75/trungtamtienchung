/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Supplier;

/**
 *
 * @author Dell
 */
public class SupplierController {
   public static List<Supplier> findAll() {
        List<Supplier> supplierList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            //lay tat ca danh sach sinh vien
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select supplier.*, staffCreate.fullname staffCreateName, "
                    + "staffUpdate.fullname staffUpdateName "
                    + "from supplier join staff staffCreate "
                    + "on supplier.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on supplier.id_staff_updated = staffUpdate.id";
            statement = (Statement) conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getInt("id"), 
                        resultSet.getInt("id_staff_created"),
                        resultSet.getInt("id_staff_updated"),
                        resultSet.getString("name"), 
                        resultSet.getString("phoneNumber"), 
                        resultSet.getString("email"), 
                        resultSet.getString("address"),
                        resultSet.getString("created_at"),
                        resultSet.getString("staffCreateName"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("staffUpdateName"));
                supplierList.add(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return supplierList;
    }
    
    public static void insert(Supplier supplier) {
        Connection conn = null;
        java.sql.PreparedStatement statement = null;
        try {
            //lay tat ca danh mục
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "insert into supplier(name, phoneNumber, email, address, "
                    + "created_at, id_staff_created, updated_at, id_staff_updated) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareCall(sql);
            
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getPhoneNumber());
            statement.setString(3, supplier.getEmail());
            statement.setString(4, supplier.getAddress());
            statement.setString(5, supplier.getCreatedAt());
            statement.setInt(6, supplier.getIdStaffCreated());
            statement.setString(7, supplier.getUpdatedAt());
            statement.setInt(8, supplier.getIdStaffUpdated());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void update(Supplier supplier) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //lay tat ca danh sach sinh vien
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "update supplier set name = ?, phoneNumber = ?, email = ?, "
                    + "address = ?, updated_at = ?, id_staff_updated = ? where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getPhoneNumber());
            statement.setString(3, supplier.getEmail());
            statement.setString(4, supplier.getAddress());
            statement.setString(5, supplier.getUpdatedAt());
            statement.setInt(6, supplier.getIdStaffUpdated());
            statement.setInt(7, supplier.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static List<Supplier> findByNameSupplier(String name) {
        List<Supplier> supplierList = new ArrayList<>();
        
        Connection conn = null;
        java.sql.PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select supplier.*, staffCreate.fullname staffCreateName, "
                    + "staffUpdate.fullname staffUpdateName from supplier join staff staffCreate "
                    + "on supplier.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on supplier.id_staff_updated = staffUpdate.id "
                    + "where supplier.name like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getInt("id"), 
                        resultSet.getInt("id_staff_created"),
                        resultSet.getInt("id_staff_updated"),
                        resultSet.getString("name"), 
                        resultSet.getString("phoneNumber"), 
                        resultSet.getString("email"), 
                        resultSet.getString("address"),
                        resultSet.getString("created_at"),
                        resultSet.getString("staffCreateName"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("staffUpdateName"));
                supplierList.add(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return supplierList;
    }
    
    
    public static boolean findByIDSupplier(int id) {
        List<Supplier> supplierList = new ArrayList<>();
        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select supplier.*, staffCreate.fullname staffCreateName, "
                    + "staffUpdate.fullname staffUpdateName from supplier join staff staffCreate "
                    + "on supplier.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on supplier.id_staff_updated = staffUpdate.id "
                    + "where supplier.id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getInt("id"), 
                        resultSet.getInt("id_staff_created"),
                        resultSet.getInt("id_staff_updated"),
                        resultSet.getString("name"), 
                        resultSet.getString("phoneNumber"), 
                        resultSet.getString("email"), 
                        resultSet.getString("address"),
                        resultSet.getString("created_at"),
                        resultSet.getString("staffCreateName"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("staffUpdateName"));
                supplierList.add(supplier);
            }
            
            if(supplierList.isEmpty()) {
                check = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return check;
    }
}
