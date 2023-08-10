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
import model.Staff;

/**
 *
 * @author Dell
 */
public class StaffController {
    public static List<Staff> findAll() {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static void insert(Staff staff) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "insert into staff(fullname, gender, email, phoneNumber, "
                    + "address, created_at, updated_at, role_id, status) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareCall(sql);
            
            statement.setString(1, staff.getFullName());
            statement.setString(2, staff.getGender());
            statement.setString(3, staff.getEmail());
            statement.setString(4, staff.getPhoneNumber());
            statement.setString(5, staff.getAddress());
            statement.setString(6, staff.getCreatedAt());
            statement.setString(7, staff.getUpdatedAt());
            statement.setInt(8, staff.getRoleId());
            statement.setString(9, staff.getStatus());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static void update(Staff staff) {
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            //lay tat ca danh sach sinh vien
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "update staff set fullname = ?, gender = ?, "
                    + "phoneNumber = ?, address = ?, updated_at = ?, "
                    + "role_id = ?, status = ? where id = ?";
            statement = conn.prepareCall(sql);
            
            statement.setString(1, staff.getFullName());
            statement.setString(2, staff.getGender());
            statement.setString(3, staff.getPhoneNumber());
            statement.setString(4, staff.getAddress());
            statement.setString(5, staff.getUpdatedAt());
            statement.setInt(6, staff.getRoleId());
            statement.setString(7, staff.getStatus());
            statement.setInt(8, staff.getId());
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
    }
    
    public static List<Staff> findByFullnameStaff(String fullname) {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where fullname like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" +fullname + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static List<Staff> findByFullnameCarrier(String fullname) {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where fullname like ? and staff.role_id = 4";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "%" +fullname + "%");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static List<Staff> findAllCarrier() {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where staff.role_id = 4";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                System.out.println(resultSet.getString("fullname"));
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static List<Staff> findAllConsulting() {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where staff.role_id = 2";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static List<Staff> findAllWareHouse() {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where staff.role_id = 3";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static List<Staff> findAllCarrierOnWork() {
        List<Staff> staffList = new ArrayList<>();
        
        Connection conn = null;
        Statement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where staff.role_id = 4 and staff.status = 'Đang làm'";
            statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return staffList;
    }
    
    public static boolean findAllCarrierOnWorkByID(int id) {
        List<Staff> staffList = new ArrayList<>();
        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select staff.*, role.name roleName, account.password "
                    + "from staff join role on role.id = staff.role_id "
                    + "join account on account.email = staff.email "
                    + "where staff.role_id = 4 and staff.status = 'Đang làm' and staff.id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            
            while (resultSet.next()) {
                Staff staff = new Staff(resultSet.getInt("staff.id"),
                    resultSet.getInt("staff.role_id"),
                    resultSet.getString("roleName"),
                    resultSet.getString("fullname"),
                    resultSet.getString("gender"),
                    resultSet.getString("email"),
                    resultSet.getString("account.password"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("address"),
                    resultSet.getString("created_at"),
                    resultSet.getString("updated_at"),
                    resultSet.getString("status")
                );
                staffList.add(staff);
            }
            if(staffList.isEmpty()) {
                check = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return check;
    }
    
   public static int findRoleID(int id) {
        int roleID = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trungtamtiemchung", "root", "trantuan123");
            
            //query
            String sql = "select role_id from staff where id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                roleID = resultSet.getInt("role_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc.
        
        return roleID;
    }
}
