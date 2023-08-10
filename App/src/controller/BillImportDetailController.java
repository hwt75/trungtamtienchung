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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BillImportDetail;

/**
 *
 * @author Dell
 */
public class BillImportDetailController {
    public static List<BillImportDetail> findAll(int billImportId) {
        List<BillImportDetail> billImportDetailList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            
            System.out.println(billImportId);
            
            String sql = "select billimport_detail.*, product.title productTitle "
                    + "from billimport_detail join product "
                    + "on billimport_detail.id_product = product.id "
                    + "join billimport "
                    + "on billimport_detail.id_billimport = billimport.id "
                    + "where billimport_detail.id_billimport = ?";
            
            statement = conn.prepareStatement(sql);
            statement.setInt(1, billImportId);
            
            ResultSet resultSet = statement.executeQuery();
            
            
            while (resultSet.next()) {
                BillImportDetail billImportDetail = new BillImportDetail(resultSet.getInt("id"),
                    resultSet.getInt("billimport_detail.id_billimport"),
                    resultSet.getInt("billimport_detail.id_product"),
                    resultSet.getInt("billimport_detail.price"),
                    resultSet.getInt("billimport_detail.count"),
                    resultSet.getString("productTitle")
                );
                billImportDetailList.add(billImportDetail);
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
        
        return billImportDetailList;
    }
    
    public static void insert(BillImportDetail billImportDetail) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "insert into billimport_detail (id_product, "
                    + "id_billimport, price, count) values(?, ?, ?, ?) ";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, billImportDetail.getIdProduct());
            statement.setInt(2, billImportDetail.getIdBillImport());
            statement.setInt(3, billImportDetail.getPrice());
            statement.setInt(4, billImportDetail.getCount());
            
            statement.execute();
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
    }
    
    public static void update(BillImportDetail billImportDetail) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "update billimport_detail set id_product = ?, count = ?, price = ? where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setInt(1, billImportDetail.getIdProduct());
            statement.setInt(2, billImportDetail.getCount());
            statement.setInt(3, billImportDetail.getPrice());
            statement.setInt(4, billImportDetail.getId());
            
            statement.execute();
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
    }
    
    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //lay tat ca danh sach sinh vien
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "delete from billimport_detail where id = ?";
            statement = conn.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
            
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
    }
    
    public static int findCount(int id) {
        int count = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
                        
            String sql = "select count from billimport_detail where id = ?";
            
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
