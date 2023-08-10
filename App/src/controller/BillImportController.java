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
import model.BillImport;

/**
 *
 * @author Dell
 */
public class BillImportController {
    public static List<BillImport> findAll() {
        List<BillImport> billImportList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select billimport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "supplier.name nameSupplier, carrier.fullname nameCarrier "
                    + "from billimport join staff staffCreate "
                    + "on billimport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billimport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billimport.id_carrier = carrier.id "
                    + "join supplier "
                    + "on billimport.id_supplier = supplier.id";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                BillImport billImport = new BillImport( 
                    resultSet.getInt("billimport.id"),
                    resultSet.getInt("billimport.total"),
                    resultSet.getInt("billimport.id_staff_created"),
                    resultSet.getInt("billimport.id_staff_updated"),
                    resultSet.getInt("billimport.id_supplier"),
                    resultSet.getInt("billimport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("nameSupplier"),
                    resultSet.getString("nameCarrier")
                );
                billImportList.add(billImport);
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
        
        return billImportList;
    }
    
    public static void insert(BillImport billImport) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //lay tat ca danh mục
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "insert into billimport(total, created_at, id_staff_created, "
                    + "updated_at, id_staff_updated, id_supplier, id_carrier) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareCall(sql);
            statement.setInt(1, billImport.getTotal());
            statement.setString(2, billImport.getCreatedAt());
            statement.setInt(3, billImport.getIdStaffCreated());
            statement.setString(4, billImport.getUpdatedAt());
            statement.setInt(5, billImport.getIdStaffUpdated());
            statement.setInt(6, billImport.getIdSupplier());
            statement.setInt(7, billImport.getIdCarrier());
            statement.execute();
            
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
    }
    
    public static void update(BillImport billImport) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "update billimport set updated_at = ?, id_staff_updated = ?, "
                    + "id_carrier = ?, id_supplier = ? "
                    + "where id = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, billImport.getUpdatedAt());
            statement.setInt(2, billImport.getIdStaffUpdated());
            statement.setInt(3, billImport.getIdCarrier());
            statement.setInt(4, billImport.getIdSupplier());
            statement.setInt(5, billImport.getId());
            
            statement.execute();
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
        //ket thuc.
    }
    
    public static void delete(int id) {
        Connection conn = null;
        java.sql.PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            
            String sql = "delete from billimport where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, id);
            statement.execute();
            
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
    }
    
    public static List<BillImport> findByNameSupplier(String name) {
        List<BillImport> billImportList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select billimport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "supplier.name nameSupplier, carrier.fullname nameCarrier "
                    + "from billimport join staff staffCreate "
                    + "on billimport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billimport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billimport.id_carrier = carrier.id "
                    + "join supplier "
                    + "on billimport.id_supplier = supplier.id "
                    + "where supplier.name like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillImport billImport = new BillImport( 
                    resultSet.getInt("billimport.id"),
                    resultSet.getInt("billimport.total"),
                    resultSet.getInt("billimport.id_staff_created"),
                    resultSet.getInt("billimport.id_staff_updated"),
                    resultSet.getInt("billimport.id_supplier"),
                    resultSet.getInt("billimport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("nameSupplier"),
                    resultSet.getString("nameCarrier")
                );
                billImportList.add(billImport);

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
        //ket thuc.
        
        return billImportList;
    }
    
    public static List<BillImport> findByNameSupplierCarrier(String name, int id) {
        List<BillImport> billImportList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select billimport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "supplier.name nameSupplier, carrier.fullname nameCarrier "
                    + "from billimport join staff staffCreate "
                    + "on billimport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billimport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billimport.id_carrier = carrier.id "
                    + "join supplier "
                    + "on billimport.id_supplier = supplier.id "
                    + "where supplier.name like ? and billimport.id_carrier = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setInt(2, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillImport billImport = new BillImport( 
                    resultSet.getInt("billimport.id"),
                    resultSet.getInt("billimport.total"),
                    resultSet.getInt("billimport.id_staff_created"),
                    resultSet.getInt("billimport.id_staff_updated"),
                    resultSet.getInt("billimport.id_supplier"),
                    resultSet.getInt("billimport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("nameSupplier"),
                    resultSet.getString("nameCarrier")
                );
                billImportList.add(billImport);

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
        //ket thuc.
        
        return billImportList;
    }
    
    public static List<BillImport> findByID(int id) {
        List<BillImport> billImportList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            String sql = "select billimport.*,"
                    + "staffCreate.fullname nameStaffCreate, staffUpdate.fullname nameStaffUpdate, "
                    + "supplier.name nameSupplier, carrier.fullname nameCarrier "
                    + "from billimport join staff staffCreate "
                    + "on billimport.id_staff_created = staffCreate.id "
                    + "join staff staffUpdate "
                    + "on billimport.id_staff_updated = staffUpdate.id "
                    + "join staff carrier "
                    + "on billimport.id_carrier = carrier.id "
                    + "join supplier "
                    + "on billimport.id_supplier = supplier.id "
                    + "where billimport.id_carrier = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillImport billImport = new BillImport( 
                    resultSet.getInt("billimport.id"),
                    resultSet.getInt("billimport.total"),
                    resultSet.getInt("billimport.id_staff_created"),
                    resultSet.getInt("billimport.id_staff_updated"),
                    resultSet.getInt("billimport.id_supplier"),
                    resultSet.getInt("billimport.id_carrier"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("nameStaffCreate"),
                    resultSet.getString("nameStaffUpdate"),
                    resultSet.getString("nameSupplier"),
                    resultSet.getString("nameCarrier")
                );
                billImportList.add(billImport);

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
        //ket thuc.
        
        return billImportList;
    }
    
    public static void updateTotal(int billImportID, int staffID, String timeUpdate) {
        Connection conn = null;
        PreparedStatement statement = null;
        int total = 0;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            
            String sql = "select * from billimport_detail where id_billimport = ?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, billImportID);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                total += resultSet.getInt("price") * resultSet.getInt("count");
            }
            
            
            sql = "update billimport set total = ?, id_staff_updated = ?, updated_at = ? "
                    + "where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, total);
            statement.setInt(2, staffID);
            statement.setString(3, timeUpdate);
            statement.setInt(4, billImportID);
            statement.execute();
            
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
    }
    
    public static int getAllTotal() {
        Connection conn = null;
        Statement statement = null;
        int total = 0;
        
        try {
            conn = DriverManager.getConnection(jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "lamtung123);
            
            //query
            
            String sql = "select total from billimport";
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
