/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Custom;

/**
 *
 * @author Dell
 */
public class CustomController {
    public static List<Custom> findAll() {
        List<Custom> customList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select custom.*, staffCreate.fullname staffCreateName, "
                    + "staffUpdate.fullname staffUpdateName from custom join staff staffCreate "
                    + "on custom.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on custom.id_staff_updated = staffUpdate.id";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Custom custom = new Custom(resultSet.getInt("id"),
                    resultSet.getInt("id_staff_created"),
                    resultSet.getInt("id_staff_updated"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("staffCreateName"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffUpdateName"));
                customList.add(custom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return customList;
    }
    
    public static void insert(Custom custom) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "insert into custom(fullname, gender, email, phoneNumber, address, "
                    + "created_at, id_staff_created, updated_at, id_staff_updated) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareCall(sql);
            
            statement.setString(1, custom.getFullName());
            statement.setString(2, custom.getGender());
            statement.setString(3, custom.getEmail());
            statement.setString(4, custom.getPhoneNumber());
            statement.setString(5, custom.getAddress());
            statement.setString(6, custom.getCreated_at());
            statement.setInt(7, custom.getIdCreatedStaff());
            statement.setString(8, custom.getUpdated_at());
            statement.setInt(9, custom.getIdUpdatedStaff());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void update(Custom custom) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "update custom set fullname = ?, gender = ?, email = ?, "
                    + "phoneNumber = ?, address = ?, updated_at = ?, id_staff_updated = ? "
                    + "where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setString(1, custom.getFullName());
            statement.setString(2, custom.getGender());
            statement.setString(3, custom.getEmail());
            statement.setString(4, custom.getPhoneNumber());
            statement.setString(5, custom.getAddress());
            statement.setString(6, custom.getUpdated_at());
            statement.setInt(7, custom.getIdUpdatedStaff());
            statement.setInt(8, custom.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static List<Custom> findByNameCustom(String fullname) {
        List<Custom> customList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select custom.*, staffCreate.fullname staffCreateName, "
                        + "staffUpdate.fullname staffUpdateName from custom join staff staffCreate "
                        + "on custom.id_staff_created = staffCreate.id "
                        + "join staff staffUpdate "
                        + "on custom.id_staff_updated = staffUpdate.id "
                        + "where custom.fullname like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + fullname + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Custom custom = new Custom(resultSet.getInt("id"),
                    resultSet.getInt("id_staff_created"),
                    resultSet.getInt("id_staff_updated"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("staffCreateName"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffUpdateName"));
                customList.add(custom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return customList;
    }
    
    public static boolean findByIDCustom(int id) {
        boolean check = true;
        List<Custom> customList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select custom.*, staffCreate.fullname staffCreateName, "
                        + "staffUpdate.fullname staffUpdateName from custom join staff staffCreate "
                        + "on custom.id_staff_created = staffCreate.id "
                        + "join staff staffUpdate "
                        + "on custom.id_staff_updated = staffUpdate.id "
                        + "where custom.id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Custom custom = new Custom(resultSet.getInt("id"),
                    resultSet.getInt("id_staff_created"),
                    resultSet.getInt("id_staff_updated"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("staffCreateName"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("staffUpdateName"));
                customList.add(custom);
            }
            if(customList.isEmpty()) {
                check = false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CustomController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return check;
    }
}
