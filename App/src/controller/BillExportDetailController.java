/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BillExportDetail;

/**
 *
 * @author Admin
 */
public class BillExportDetailController {
    
    public static List<BillExportDetail> findAll(int billExportId) {
        List<BillExportDetail> billExportDetailList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            
            System.out.println(billExportId);
            
            String sql = "select billexport_detail.*, product.title productTitle, product.price productPrice "
                    + "from billexport_detail join product "
                    + "on billexport_detail.id_product = product.id "
                    + "join billexport "
                    + "on billexport_detail.id_billexport = billexport.id "
                    + "where billexport_detail.id_billexport = ?";
            
            statement = conn.prepareStatement(sql);
            statement.setInt(1, billExportId);
            
            ResultSet resultSet = statement.executeQuery();
            
            
            while (resultSet.next()) {
                BillExportDetail billExportDetail = new BillExportDetail(resultSet.getInt("id"),
                    resultSet.getInt("billexport_detail.id_billexport"),
                    resultSet.getInt("billexport_detail.id_product"),
                    resultSet.getInt("productPrice"),
                    resultSet.getInt("billexport_detail.count"),
                    resultSet.getString("productTitle")
                );
                billExportDetailList.add(billExportDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return billExportDetailList;
    }
    
    public static void insert(BillExportDetail billExportDetail) {
        Connection conn = null;
        java.sql.PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "insert into billexport_detail (id_product, "
                    + "id_billexport, count) values(?, ?, ?) ";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, billExportDetail.getIdProduct());
            statement.setInt(2, billExportDetail.getIdBillExport());
            statement.setInt(3, billExportDetail.getCount());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void update(BillExportDetail billExportDetail) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "update billexport_detail set id_product = ?, count = ? where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, billExportDetail.getIdProduct());
            statement.setInt(2, billExportDetail.getCount());
            statement.setInt(3, billExportDetail.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "delete from billexport_detail where id = ?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillExportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static int findCount(int id) {
        int count = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
                        
            String sql = "select count from billexport_detail where id = ?";
            
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            
            while (resultSet.next()) {
                count = resultSet.getInt("count");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillImportDetailController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillImportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BillImportDetailController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return count;
    }
}
