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
import model.BillExport;

/**
 *
 * @author Dell
 */
public class BillExportController {    
    public static List<BillExport> findAll() {
        List<BillExport> billExportList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            String sql = "select billexport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "custom.* , carrier.fullname nameCarrier "
                    + "from billexport join staff staffCreate "
                    + "on billexport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billexport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billexport.id_carrier = carrier.id "
                    + "join custom "
                    + "on billexport.id_custom = custom.id";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                BillExport billExport = new BillExport( 
                    resultSet.getInt("billexport.id"),
                    resultSet.getInt("billexport.total"),
                    resultSet.getInt("billexport.id_staff_created"),
                    resultSet.getInt("billexport.id_staff_updated"),
                    resultSet.getInt("billexport.id_custom"),
                    resultSet.getInt("billexport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("custom.fullname"),
                    resultSet.getString("custom.gender"),
                    resultSet.getString("custom.email"),
                    resultSet.getString("custom.address"),
                    resultSet.getString("custom.phoneNumber"),
                    resultSet.getString("nameCarrier"),
                    resultSet.getString("note"),
                    resultSet.getString("time_start_borrowed"),
                    resultSet.getString("time_end_borrowed")
                );
                billExportList.add(billExport);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return billExportList;
    }
    
    public static void insert(BillExport billExport) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            String sql = "insert into billexport(total, created_at, id_staff_created, "
                    + "updated_at, id_staff_updated, id_custom, id_carrier, time_start_borrowed, "
                    + "time_end_borrowed) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareCall(sql);
            statement.setInt(1, billExport.getTotal());
            statement.setString(2, billExport.getCreatedAt());
            statement.setInt(3, billExport.getIdStaffCreated());
            statement.setString(4, billExport.getUpdatedAt());
            statement.setInt(5, billExport.getIdStaffUpdated());
            statement.setInt(6, billExport.getIdCustom());
            statement.setInt(7, billExport.getIdCarrier());
            statement.setString(8, billExport.getTimeStartedBorrowed());
            statement.setString(9, billExport.getTimeEndBorrowed());
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void update(BillExport billExport) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            String sql = "update billexport set updated_at = ?, id_staff_updated = ?, "
                    + "id_carrier = ?, time_start_borrowed = ?, "
                    + "note = ?, time_end_borrowed = ? "
                    + "where id = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, billExport.getUpdatedAt());
            statement.setInt(2, billExport.getIdStaffUpdated());
            statement.setInt(3, billExport.getIdCarrier());
            statement.setString(4, billExport.getTimeStartedBorrowed());
            statement.setString(5, billExport.getNote());
            statement.setString(6, billExport.getTimeEndBorrowed());
            statement.setInt(7, billExport.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            
            String sql = "delete from billexport where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static List<BillExport> findByNameCustom(String fullname) {
        List<BillExport> billExportList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            String sql = "select billexport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "custom.*, carrier.fullname nameCarrier "
                    + "from billexport join staff staffCreate "
                    + "on billexport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billexport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billexport.id_carrier = carrier.id "
                    + "join custom "
                    + "on billexport.id_custom = custom.id "
                    + "where custom.fullname like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + fullname + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillExport billExport = new BillExport( 
                    resultSet.getInt("billexport.id"),
                    resultSet.getInt("billexport.total"),
                    resultSet.getInt("billexport.id_staff_created"),
                    resultSet.getInt("billexport.id_staff_updated"),
                    resultSet.getInt("billexport.id_custom"),
                    resultSet.getInt("billexport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("custom.fullname"),
                    resultSet.getString("custom.gender"),
                    resultSet.getString("custom.email"),
                    resultSet.getString("custom.address"),
                    resultSet.getString("custom.phoneNumber"),
                    resultSet.getString("nameCarrier"),
                    resultSet.getString("note"),
                    resultSet.getString("time_start_borrowed"),
                    resultSet.getString("time_end_borrowed")
                );
                billExportList.add(billExport);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return billExportList;
    }
    
    public static List<BillExport> findByNameCustomCarrier(String fullname, int id) {
        List<BillExport> billExportList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            String sql = "select billexport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "custom.*, carrier.fullname nameCarrier "
                    + "from billexport join staff staffCreate "
                    + "on billexport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billexport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billexport.id_carrier = carrier.id "
                    + "join custom "
                    + "on billexport.id_custom = custom.id "
                    + "where custom.fullname like ? and billexport.id_carrier = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + fullname + "%");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillExport billExport = new BillExport( 
                    resultSet.getInt("billexport.id"),
                    resultSet.getInt("billexport.total"),
                    resultSet.getInt("billexport.id_staff_created"),
                    resultSet.getInt("billexport.id_staff_updated"),
                    resultSet.getInt("billexport.id_custom"),
                    resultSet.getInt("billexport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("custom.fullname"),
                    resultSet.getString("custom.gender"),
                    resultSet.getString("custom.email"),
                    resultSet.getString("custom.address"),
                    resultSet.getString("custom.phoneNumber"),
                    resultSet.getString("nameCarrier"),
                    resultSet.getString("note"),
                    resultSet.getString("time_start_borrowed"),
                    resultSet.getString("time_end_borrowed")
                );
                billExportList.add(billExport);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return billExportList;
    }
    
    public static List<BillExport> findByID(int id) {
        List<BillExport> billExportList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            String sql = "select billexport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "custom.*, carrier.fullname nameCarrier "
                    + "from billexport join staff staffCreate "
                    + "on billexport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billexport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billexport.id_carrier = carrier.id "
                    + "join custom "
                    + "on billexport.id_custom = custom.id "
                    + "where billexport.id_carrier = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillExport billExport = new BillExport( 
                    resultSet.getInt("billexport.id"),
                    resultSet.getInt("billexport.total"),
                    resultSet.getInt("billexport.id_staff_created"),
                    resultSet.getInt("billexport.id_staff_updated"),
                    resultSet.getInt("billexport.id_custom"),
                    resultSet.getInt("billexport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("custom.fullname"),
                    resultSet.getString("custom.gender"),
                    resultSet.getString("custom.email"),
                    resultSet.getString("custom.address"),
                    resultSet.getString("custom.phoneNumber"),
                    resultSet.getString("nameCarrier"),
                    resultSet.getString("note"),
                    resultSet.getString("time_start_borrowed"),
                    resultSet.getString("time_end_borrowed")
                );
                billExportList.add(billExport);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return billExportList;
    }
    
    public static void updateTotal(int billExportID, int staffID, String timeUpdate) {
        Connection conn = null;
        PreparedStatement statement = null;
        int total = 0;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            
            String sql = "select billexport_detail.*, product.price productPrice "
                    + "from billexport_detail join product "
                    + "on billexport_detail.id_product = product.id "
                    + "where billexport_detail.id_billexport = ?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, billExportID);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                total += resultSet.getInt("productPrice") * resultSet.getInt("billexport_detail.count");
            }
            
            
            sql = "update billexport set total = ?, id_staff_updated = ?, updated_at = ? "
                    + "where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, total);
            statement.setInt(2, staffID);
            statement.setString(3, timeUpdate);
            statement.setInt(4, billExportID);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
        
    public static int getAllTotal() {
        Connection conn = null;
        Statement statement = null;
        int total = 0;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_dam_hoi", "root", "trantuan123");
            
            //query
            
            String sql = "select total from billexport";
            statement = conn.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                total += resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillImportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillImportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillImportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return total;
    }
}
