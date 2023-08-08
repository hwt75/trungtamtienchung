/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.AccountController;
import controller.BillExportController;
import controller.BillImportController;
import controller.CategoryController;
import controller.CustomController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Category;
import model.Product;
import controller.ProductController;
import controller.RoleController;
import controller.StaffController;
import controller.SupplierController;
import model.Role;
import model.Account;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import model.BillExport;
import model.BillImport;
import model.Custom;
import model.Staff;
import model.Supplier;
/**
 *
 * @author Admin
 */
public class AppManager extends javax.swing.JFrame {
    DefaultTableModel tableModelProduct;
    DefaultTableModel tableModelStaff;
    DefaultTableModel tableModelFeedback; 
    DefaultTableModel tableModelSupplier;
    DefaultTableModel tableModelBillExport;
    DefaultTableModel tableModelBillImport;
    DefaultTableModel tableModelCustom;
    
    
    List<Product> products = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();
    List<Staff> carrierList = new ArrayList<>();
    List<Custom> customList = new ArrayList<>();
    List<Role> roleList = new ArrayList<>();
    List<BillExport> billExportList = new ArrayList<>();
    List<BillImport> billImportList = new ArrayList<>();
    List<Supplier> supplierList = new ArrayList<>();
    
    BillExportDetailFrm billExportDetailFrm;
    BillImportDetailFrm billImportDetailFrm;
    ListCarrierFrm listCarrierFrm;
    ListConsultingFrm listConsultingFrm;
    ListWareHouseFrm listWareHouseFrm;
    
    int id_user = 0;

    /**
     * Creates new form NewJFrame
     */
    public AppManager(int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        id_user = id;
        System.out.println(id_user);
        tableModelProduct = (DefaultTableModel) tableProduct.getModel();
        tableModelStaff = (DefaultTableModel) tableStaff.getModel();
        tableModelSupplier = (DefaultTableModel) tableSupplier.getModel();
        tableModelBillExport = (DefaultTableModel) tableBillExport.getModel();
        tableModelBillImport = (DefaultTableModel) tableBillImport.getModel();
        tableModelCustom = (DefaultTableModel) tableCustom.getModel();
        
        showProduct();
        showStaff();
        showBillExport();
        showBillImport();
        showSupplier();
        showCustom();
        showStatistical();
        
        showComboBox_Category();
        showComboBox_RoleUser();
        
        
        tableProduct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedIndex = tableProduct.getSelectedRow();
                Product prd = products.get(selectedIndex);
                
                txtTitleProduct.setText(prd.getTitle());
                boxCategory.setSelectedItem(prd.getCategoryName());
                txtPrice.setText(String.valueOf(prd.getPrice()));
                txtDescProduct.setText(prd.getDescription());
                txtThumbProduct.setText(prd.getThumbnail());
                
                File file = new File(prd.getThumbnail());
                // lấy đường dẫn file
                String pathFile = file.getAbsolutePath();
                System.out.println(pathFile);
                BufferedImage b;
                try {
                    b = ImageIO.read(file);
                    jlbThumbProduct.setIcon(new ImageIcon(b.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                    txtThumbProduct.setText(pathFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                btnUpdateProduct.setEnabled(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        });
        
        tableStaff.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedIndex = tableStaff.getSelectedRow();
                Staff staff = staffList.get(selectedIndex);
                
                txtFullname.setText(staff.getFullName());
                boxGender.setSelectedItem(staff.getGender());
                txtEmail.setText(staff.getEmail());
                txtPhonenumber.setText(staff.getPhoneNumber());
                txtAddress.setText(staff.getAddress());
                boxRole.setSelectedItem(staff.getRoleName());
                txtPassword.setText(staff.getPassword());
                cbStatusStaff.setSelectedItem(staff.getStatus());
                
                txtEmail.setEnabled(false);
                btnUpdateStaff.setEnabled(true);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        });
        
        tableCustom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedIndex = tableCustom.getSelectedRow();
                Custom custom = customList.get(selectedIndex);
                
                txtNameCustom.setText(custom.getFullName());
                cbGenderCustom.setSelectedItem(custom.getGender());
                txtEmailCustom.setText(custom.getEmail());
                txtPhoneCustom.setText(custom.getPhoneNumber());
                txtAddressCustom.setText(custom.getAddress());
                
               btnEditCustom.setEnabled(true);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        });
        
        
        tableBillExport.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedIndex = tableBillExport.getSelectedRow();
                BillExport billExport = billExportList.get(selectedIndex);
                
                txtIDCustom.setText(String.valueOf(billExport.getIdCustom()));
                txtNoteBillExport.setText(billExport.getNote());
                jtfDateStart.setText(billExport.getTimeStartedBorrowed());
                jtfDateEnd.setText(billExport.getTimeEndBorrowed());
                txtIDCarrierBillExport.setText(String.valueOf(billExport.getIdCarrier()));
                
                txtIDCustom.setEditable(false);
                btnUpdateBillExport.setEnabled(true);
                btnDeleteOrder.setEnabled(true);
                btnShowProductOrder.setEnabled(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        });
        
        tableBillImport.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedIndex = tableBillImport.getSelectedRow();
                BillImport billImport = billImportList.get(selectedIndex);
                
                txtIDSupplier.setText(String.valueOf(billImport.getIdSupplier()));
                txtIDCarrierBillImport.setText(String.valueOf(billImport.getIdCarrier()));
                
                txtIDSupplier.setEditable(false);
                btnEditBillImport.setEnabled(true);
                btnDeleteBillImport.setEnabled(true);
                btnShowListBillImport.setEnabled(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        });
        
        tableSupplier.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedIndex = tableSupplier.getSelectedRow();
                Supplier supplier = supplierList.get(selectedIndex);
                
                txtSupplier.setText(supplier.getName());
                txtEmailSupplier.setText(supplier.getEmail());
                txtPhoneSupplier.setText(supplier.getPhoneNumber());
                txtAddressSupplier.setText(supplier.getAddress());
                
                btnUpdateSupplier.setEnabled(true);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        });
        
    }

    private AppManager() {
    }
    
    public void showComboBox_Category() {
        DefaultComboBoxModel<String> comboBoxModelProduct = (DefaultComboBoxModel<String>) boxCategory.getModel();
        comboBoxModelProduct.removeAllElements();
        categoryList = CategoryController.findAll();
        categoryList.forEach(category -> {
            comboBoxModelProduct.addElement(category.getName());
        });
    }
    
    public void showComboBox_RoleUser() {
        DefaultComboBoxModel<String> comboBoxModelUser = (DefaultComboBoxModel<String>) boxRole.getModel();
        comboBoxModelUser.removeAllElements();
        roleList = RoleController.findAll();
        roleList.forEach(role -> {
            if(role.getId() != 5){
                comboBoxModelUser.addElement(role.getName());
            }
        });
    }
    
    private void showProduct() {
        products = ProductController.findAll();
        
        tableModelProduct.setRowCount(0);
        
        products.forEach((prd) -> {
            tableModelProduct.addRow(new Object[] {
                tableModelProduct.getRowCount() + 1,
                prd.getId(),
                prd.getTitle(),
                prd.getCategoryName(),
                prd.getPrice(),
                prd.getDescription(),
                prd.getThumbnail(),
                prd.getCount(),
                prd.getCreatedAt(),
                prd.getNameStaffCreated(),
                prd.getUpdatedAt(),
                prd.getNameStaffUpdated(),
            });
        });
    } 
    
    private void showStaff() {
        staffList = StaffController.findAll();
        
        tableModelStaff.setRowCount(0);
        
        staffList.forEach((staff) -> {
            tableModelStaff.addRow(new Object[] {tableModelStaff.getRowCount() + 1,
                staff.getId(),
                staff.getFullName(),
                staff.getGender(),
                staff.getEmail(),
                staff.getPhoneNumber(),
                staff.getAddress(),
                staff.getRoleName(),
                staff.getPassword(),
                staff.getCreatedAt(),
                staff.getUpdatedAt(),
                staff.getStatus()
            });
        });
    }
    
    private void showCustom() {
        customList = CustomController.findAll();
        
        tableModelCustom.setRowCount(0);
        
        customList.forEach((custom) -> {
            tableModelCustom.addRow(new Object[] {tableModelCustom.getRowCount() + 1,
                custom.getId(),
                custom.getFullName(),
                custom.getGender(),
                custom.getEmail(),
                custom.getPhoneNumber(),
                custom.getAddress(),
                custom.getCreated_at(),
                custom.getNameCreatedStaff(),
                custom.getUpdated_at(),
                custom.getNameUpdatedStaff()
            });
        });
    }
    
    
    private void showBillExport() {
        billExportList = BillExportController.findAll();
        
        tableModelBillExport.setRowCount(0);
        
        billExportList.forEach((billExport) -> {
            tableModelBillExport.addRow(new Object[] {tableModelBillExport.getRowCount() + 1,
                billExport.getNameCustom(),
                billExport.getGender(),
                billExport.getEmail(),
                billExport.getPhoneNumber(),
                billExport.getAddress(),
                billExport.getNote(),
                billExport.getTotal(),
                billExport.getNameCarrier(),
                billExport.getTimeStartedBorrowed(),
                billExport.getTimeEndBorrowed(),
                billExport.getCreatedAt(),
                billExport.getNameStaffCreated(),
                billExport.getUpdatedAt(),
                billExport.getNameStaffUpdated()
            });
        });
    }
    
    private void showBillImport() {
        billImportList = BillImportController.findAll();
        
        tableModelBillImport.setRowCount(0);
        
        billImportList.forEach((billImport) -> {
            tableModelBillImport.addRow(new Object[] {tableModelBillImport.getRowCount() + 1,
                billImport.getNameSupplier(),
                billImport.getTotal(),
                billImport.getNameCarrier(),
                billImport.getCreatedAt(),
                billImport.getNameStaffCreated(),
                billImport.getUpdatedAt(),
                billImport.getNameStaffUpdated()
            });
        });
    }
    
    private void showSupplier() {
        supplierList = SupplierController.findAll();
        
        tableModelSupplier.setRowCount(0);
        
        supplierList.forEach((supplier) -> {
            tableModelSupplier.addRow(new Object[] {tableModelSupplier.getRowCount() + 1,
                supplier.getId(),
                supplier.getName(),
                supplier.getPhoneNumber(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getCreatedAt(),
                supplier.getNameStaffCreated(),
                supplier.getUpdatedAt(),
                supplier.getNameStaffUpdated()
            });
        });
    }
    
    public void showStatistical() {
        NumberFormat currentLocale = NumberFormat.getInstance();
        int totalRevenue = BillExportController.getAllTotal();
        int totalEntered = BillImportController.getAllTotal();
        int interest = totalRevenue - totalEntered;
        
        jlbTotalRevenue.setText(String.valueOf(currentLocale.format(totalRevenue)));
        jlbToTalEntered.setText(String.valueOf(currentLocale.format(totalEntered)));
        jlbInterest.setText(String.valueOf(currentLocale.format(interest)));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jplProduct = new javax.swing.JPanel();
        infoProduct = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        txtTitleProduct = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        Desc = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        btnInsertProduct = new javax.swing.JButton();
        btnResetProduct = new javax.swing.JButton();
        btnFindProduct = new javax.swing.JButton();
        btnUpdateProduct = new javax.swing.JButton();
        boxCategory = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtDescProduct = new javax.swing.JTextArea();
        txtThumbProduct = new javax.swing.JTextField();
        Desc1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jlbThumbProduct = new javax.swing.JLabel();
        btnSelectImg = new javax.swing.JButton();
        jplStaff = new javax.swing.JPanel();
        jlbGender = new javax.swing.JLabel();
        jlbName = new javax.swing.JLabel();
        jlbPhoneNumber = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtFullname = new javax.swing.JTextField();
        txtPhonenumber = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnInsertStaff = new javax.swing.JButton();
        btnUpdateStaff = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableStaff = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnFindStaff = new javax.swing.JButton();
        btnResetStaff = new javax.swing.JButton();
        boxGender = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        jlbEmail = new javax.swing.JLabel();
        boxRole = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbStatusStaff = new javax.swing.JComboBox<>();
        btnConsulting = new javax.swing.JButton();
        btnWarhouse = new javax.swing.JButton();
        btnCarrier = new javax.swing.JButton();
        jplStatistic = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlbTotalRevenue = new javax.swing.JLabel();
        jlbToTalEntered = new javax.swing.JLabel();
        jlbInterest = new javax.swing.JLabel();
        jplSupplier = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSupplier = new javax.swing.JTextField();
        txtPhoneSupplier = new javax.swing.JTextField();
        txtEmailSupplier = new javax.swing.JTextField();
        txtAddressSupplier = new javax.swing.JTextField();
        btnAddSupplier = new javax.swing.JButton();
        btnUpdateSupplier = new javax.swing.JButton();
        btnFindSupplier = new javax.swing.JButton();
        btnResetSupplier = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        jplGuest = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableCustom = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNameCustom = new javax.swing.JTextField();
        txtEmailCustom = new javax.swing.JTextField();
        txtPhoneCustom = new javax.swing.JTextField();
        txtAddressCustom = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbGenderCustom = new javax.swing.JComboBox<>();
        btnFindCustom = new javax.swing.JButton();
        btnEditCustom = new javax.swing.JButton();
        btnResetCustom = new javax.swing.JButton();
        btnAddCustom = new javax.swing.JButton();
        jplBillManage = new javax.swing.JTabbedPane();
        jplBillExport = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableBillExport = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnDeleteOrder = new javax.swing.JButton();
        btnShowProductOrder = new javax.swing.JButton();
        btnFindOrder = new javax.swing.JButton();
        btnResetOrder = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtIDCustom = new javax.swing.JTextField();
        btnCreateBillExport = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jtfDateStart = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jtfDateEnd = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNoteBillExport = new javax.swing.JTextArea();
        btnUpdateBillExport = new javax.swing.JButton();
        txtIDCarrierBillExport = new javax.swing.JTextField();
        jplBillImport = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableBillImport = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnEditBillImport = new javax.swing.JButton();
        btnAddBillImport = new javax.swing.JButton();
        btnResetBillImport = new javax.swing.JButton();
        btnDeleteBillImport = new javax.swing.JButton();
        btnFindBillImport = new javax.swing.JButton();
        btnShowListBillImport = new javax.swing.JButton();
        txtIDSupplier = new javax.swing.JTextField();
        txtIDCarrierBillImport = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỆ THỐNG QUẢN LÝ TRUNG TÂM TIÊM CHỦNG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jTabbedPane1.setAutoscrolls(true);

        infoProduct.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập thông tin dược phẩm"));

        Name.setText("Tên dược phẩm:");

        Desc.setText("Mô Tả:");

        Price.setText("Giá:");

        Category.setText("Danh Mục dược phẩm:");

        btnInsertProduct.setText("Thêm");
        btnInsertProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertProductActionPerformed(evt);
            }
        });

        btnResetProduct.setText("Reset");
        btnResetProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetProductActionPerformed(evt);
            }
        });

        btnFindProduct.setText("Tìm kiếm");
        btnFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindProductActionPerformed(evt);
            }
        });

        btnUpdateProduct.setText("Sửa");
        btnUpdateProduct.setEnabled(false);
        btnUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductActionPerformed(evt);
            }
        });

        boxCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCategoryActionPerformed(evt);
            }
        });

        txtDescProduct.setColumns(20);
        txtDescProduct.setLineWrap(true);
        txtDescProduct.setRows(5);
        jScrollPane9.setViewportView(txtDescProduct);

        txtThumbProduct.setEditable(false);
        txtThumbProduct.setBackground(new java.awt.Color(204, 204, 204));
        txtThumbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThumbProductActionPerformed(evt);
            }
        });

        Desc1.setText("Link hình ảnh:");

        javax.swing.GroupLayout infoProductLayout = new javax.swing.GroupLayout(infoProduct);
        infoProduct.setLayout(infoProductLayout);
        infoProductLayout.setHorizontalGroup(
            infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProductLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoProductLayout.createSequentialGroup()
                        .addComponent(btnInsertProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoProductLayout.createSequentialGroup()
                        .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Desc1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtThumbProduct)
                            .addComponent(txtPrice)
                            .addComponent(boxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtTitleProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        infoProductLayout.setVerticalGroup(
            infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProductLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitleProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Desc1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(txtThumbProduct))
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID dược phẩm", "Tên dược phẩm", "Danh Mục dược phẩm", "Giá", "Mô Tả", "Link hình ảnh", "Số lượng tồn kho", "Ngày tạo", "Người tạo", "Ngày update", "Người update"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true, true, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableProduct.setRowHeight(28);
        tableProduct.setShowGrid(true);
        jScrollPane2.setViewportView(tableProduct);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(0).setMinWidth(5);
            tableProduct.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableProduct.getColumnModel().getColumn(0).setMaxWidth(100);
            tableProduct.getColumnModel().getColumn(2).setMinWidth(100);
            tableProduct.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableProduct.getColumnModel().getColumn(2).setMaxWidth(150);
            tableProduct.getColumnModel().getColumn(3).setMinWidth(100);
            tableProduct.getColumnModel().getColumn(3).setPreferredWidth(120);
            tableProduct.getColumnModel().getColumn(3).setMaxWidth(200);
            tableProduct.getColumnModel().getColumn(4).setMinWidth(50);
            tableProduct.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableProduct.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jlbThumbProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Hình ảnh")));

        btnSelectImg.setText("Chọn");
        btnSelectImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplProductLayout = new javax.swing.GroupLayout(jplProduct);
        jplProduct.setLayout(jplProductLayout);
        jplProductLayout.setHorizontalGroup(
            jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplProductLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplProductLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jplProductLayout.createSequentialGroup()
                        .addComponent(infoProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplProductLayout.createSequentialGroup()
                                .addComponent(jlbThumbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(425, Short.MAX_VALUE))
                            .addGroup(jplProductLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(btnSelectImg, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jplProductLayout.setVerticalGroup(
            jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplProductLayout.createSequentialGroup()
                        .addComponent(jlbThumbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelectImg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý dược phẩm", jplProduct);

        jlbGender.setText("Giới tính: ");

        jlbName.setText("Họ và tên:");

        jlbPhoneNumber.setText("Số điện thoại: ");

        jLabel7.setText("Địa chỉ: ");

        jLabel8.setText("Quyền: ");

        btnInsertStaff.setText("Thêm");
        btnInsertStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertStaffActionPerformed(evt);
            }
        });

        btnUpdateStaff.setText("Sửa");
        btnUpdateStaff.setEnabled(false);
        btnUpdateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStaffActionPerformed(evt);
            }
        });

        tableStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID bác sĩ", "Họ và tên", "Giới tính", "Email", "Số điện thoại", "Địa chỉ", "Quyền", "Mật khẩu", "Ngày tạo", "Ngày update", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableStaff.setRowHeight(28);
        tableStaff.setShowGrid(true);
        jScrollPane4.setViewportView(tableStaff);
        if (tableStaff.getColumnModel().getColumnCount() > 0) {
            tableStaff.getColumnModel().getColumn(0).setMinWidth(5);
            tableStaff.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableStaff.getColumnModel().getColumn(0).setMaxWidth(100);
            tableStaff.getColumnModel().getColumn(3).setResizable(false);
            tableStaff.getColumnModel().getColumn(7).setMinWidth(100);
            tableStaff.getColumnModel().getColumn(7).setPreferredWidth(70);
            tableStaff.getColumnModel().getColumn(7).setMaxWidth(120);
        }

        jLabel9.setText("Trạng thái:");

        btnFindStaff.setText("Tìm Kiếm");
        btnFindStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindStaffActionPerformed(evt);
            }
        });

        btnResetStaff.setText("Reset");
        btnResetStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetStaffActionPerformed(evt);
            }
        });

        boxGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        boxGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxGenderActionPerformed(evt);
            }
        });

        jlbEmail.setText("Email:");

        boxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxRoleActionPerformed(evt);
            }
        });

        jLabel13.setText("Mật Khẩu:");

        cbStatusStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Nghỉ việc" }));
        cbStatusStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusStaffActionPerformed(evt);
            }
        });

        btnConsulting.setText("DS bác sĩ tiếp tân");
        btnConsulting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultingActionPerformed(evt);
            }
        });

        btnWarhouse.setText("DS nhân viên kho");
        btnWarhouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWarhouseActionPerformed(evt);
            }
        });

        btnCarrier.setText("DS bác sĩ điều trị");
        btnCarrier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarrierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplStaffLayout = new javax.swing.GroupLayout(jplStaff);
        jplStaff.setLayout(jplStaffLayout);
        jplStaffLayout.setHorizontalGroup(
            jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplStaffLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addComponent(txtPhonenumber, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addComponent(txtFullname, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addComponent(boxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword))
                    .addComponent(boxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnUpdateStaff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFindStaff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResetStaff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCarrier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnWarhouse, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsulting, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInsertStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(350, 594, Short.MAX_VALUE))
            .addGroup(jplStaffLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jplStaffLayout.setVerticalGroup(
            jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplStaffLayout.createSequentialGroup()
                .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplStaffLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnInsertStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplStaffLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplStaffLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatusStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jplStaffLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnFindStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsulting, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnWarhouse, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCarrier, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý bác sĩ", jplStaff);

        jLabel6.setBackground(new java.awt.Color(153, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel6.setText("Tổng doanh thu:");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel10.setText("Tổng chi phí vận hành:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel11.setText("Tiền lãi:");

        jlbTotalRevenue.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N

        jlbToTalEntered.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N

        jlbInterest.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N

        javax.swing.GroupLayout jplStatisticLayout = new javax.swing.GroupLayout(jplStatistic);
        jplStatistic.setLayout(jplStatisticLayout);
        jplStatisticLayout.setHorizontalGroup(
            jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplStatisticLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbToTalEntered, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(481, Short.MAX_VALUE))
        );
        jplStatisticLayout.setVerticalGroup(
            jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplStatisticLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbToTalEntered, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jplStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(504, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống kê", jplStatistic);

        jLabel1.setText("Tên nhà cung cấp:");

        jLabel2.setText("SĐT:");

        jLabel3.setText("Email: ");

        jLabel4.setText("Địa chỉ:");

        txtSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierActionPerformed(evt);
            }
        });

        btnAddSupplier.setText("Thêm");
        btnAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupplierActionPerformed(evt);
            }
        });

        btnUpdateSupplier.setText("Sửa");
        btnUpdateSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSupplierActionPerformed(evt);
            }
        });

        btnFindSupplier.setText("Tìm kiếm");
        btnFindSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindSupplierActionPerformed(evt);
            }
        });

        btnResetSupplier.setText("Reset");
        btnResetSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSupplierActionPerformed(evt);
            }
        });

        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID nhà cung cấp", "Tên nhà cung cấp", "SĐT", "Email", "Địa chỉ", "Ngày tạo", "Người tạo", "Ngày sửa", "Người sửa"
            }
        ));
        jScrollPane3.setViewportView(tableSupplier);

        javax.swing.GroupLayout jplSupplierLayout = new javax.swing.GroupLayout(jplSupplier);
        jplSupplier.setLayout(jplSupplierLayout);
        jplSupplierLayout.setHorizontalGroup(
            jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplSupplierLayout.createSequentialGroup()
                .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplSupplierLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(63, 63, 63)
                        .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSupplier)
                            .addComponent(txtPhoneSupplier)
                            .addComponent(txtEmailSupplier)
                            .addComponent(txtAddressSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                        .addGap(93, 93, 93)
                        .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnResetSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdateSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFindSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                    .addGroup(jplSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(412, Short.MAX_VALUE))
        );
        jplSupplierLayout.setVerticalGroup(
            jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplSupplierLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnAddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplSupplierLayout.createSequentialGroup()
                        .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhoneSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jplSupplierLayout.createSequentialGroup()
                        .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplSupplierLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(txtEmailSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jplSupplierLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnUpdateSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFindSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplSupplierLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddressSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addComponent(btnResetSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(308, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý nhà cung cấp", jplSupplier);

        tableCustom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID bệnh nhân", "Họ và tên", "Giới tính", "Email", "Số điện thoại", "Địa chỉ", "Ngày tạo", "Người tạo", "Ngày update", "Người sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCustom.setRowHeight(28);
        tableCustom.setShowGrid(true);
        jScrollPane7.setViewportView(tableCustom);
        if (tableCustom.getColumnModel().getColumnCount() > 0) {
            tableCustom.getColumnModel().getColumn(0).setMinWidth(5);
            tableCustom.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableCustom.getColumnModel().getColumn(0).setMaxWidth(100);
            tableCustom.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel23.setText("Tên bệnh nhân:");

        jLabel24.setText("Email bệnh nhân:");

        jLabel25.setText("SĐT bệnh nhân: ");

        jLabel26.setText("Địa chỉ bệnh nhân:");

        txtPhoneCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneCustomActionPerformed(evt);
            }
        });

        jLabel29.setText("Giới tính");

        cbGenderCustom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btnFindCustom.setText("Tìm kiếm");
        btnFindCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindCustomActionPerformed(evt);
            }
        });

        btnEditCustom.setText("Sửa");
        btnEditCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomActionPerformed(evt);
            }
        });

        btnResetCustom.setText("Reset");
        btnResetCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCustomActionPerformed(evt);
            }
        });

        btnAddCustom.setText("Thêm");
        btnAddCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(txtNameCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddressCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhoneCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenderCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnFindCustom, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(btnEditCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResetCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnAddCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNameCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbGenderCustom, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhoneCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddressCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnFindCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnResetCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout jplGuestLayout = new javax.swing.GroupLayout(jplGuest);
        jplGuest.setLayout(jplGuestLayout);
        jplGuestLayout.setHorizontalGroup(
            jplGuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplGuestLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(520, Short.MAX_VALUE))
            .addGroup(jplGuestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1359, Short.MAX_VALUE)
                .addContainerGap())
        );
        jplGuestLayout.setVerticalGroup(
            jplGuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplGuestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý bệnh nhân", jplGuest);

        tableBillExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên bệnh nhân", "Giới tính", "Email bệnh nhân", "SĐT", "Địa chỉ bệnh nhân", "Ghi chú", "Tổng tiền ", "Bác sĩ điều trị", "Ngày mượn", "Ngày trả", "Ngày tạo", "Người tạo", "Ngày sửa", "Người sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tableBillExport);
        if (tableBillExport.getColumnModel().getColumnCount() > 0) {
            tableBillExport.getColumnModel().getColumn(0).setMinWidth(5);
            tableBillExport.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableBillExport.getColumnModel().getColumn(0).setMaxWidth(100);
            tableBillExport.getColumnModel().getColumn(8).setResizable(false);
            tableBillExport.getColumnModel().getColumn(9).setResizable(false);
            tableBillExport.getColumnModel().getColumn(12).setResizable(false);
        }

        btnDeleteOrder.setText("Xóa");
        btnDeleteOrder.setEnabled(false);
        btnDeleteOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteOrderActionPerformed(evt);
            }
        });

        btnShowProductOrder.setText("Show List Order");
        btnShowProductOrder.setEnabled(false);
        btnShowProductOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowProductOrderActionPerformed(evt);
            }
        });

        btnFindOrder.setText("Tìm kiếm");
        btnFindOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindOrderActionPerformed(evt);
            }
        });

        btnResetOrder.setText("Reset");
        btnResetOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetOrderActionPerformed(evt);
            }
        });

        jLabel15.setText("ID bệnh nhân");

        btnCreateBillExport.setText("Tạo lịch hẹn");
        btnCreateBillExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateBillExportActionPerformed(evt);
            }
        });

        jLabel14.setText("ID bác sĩ điều trị: ");

        jLabel21.setText("Ngày mượn (yyyy-mm-dd)");

        jtfDateStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDateStartActionPerformed(evt);
            }
        });

        jLabel22.setText("Ngày trả (yyyy-mm-dd)");

        jLabel16.setText("Note");

        txtNoteBillExport.setColumns(20);
        txtNoteBillExport.setRows(5);
        jScrollPane5.setViewportView(txtNoteBillExport);

        btnUpdateBillExport.setText("Sửa");
        btnUpdateBillExport.setEnabled(false);
        btnUpdateBillExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBillExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnCreateBillExport, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(33, 33, 33)))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIDCustom, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                    .addComponent(txtIDCarrierBillExport)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdateBillExport, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(59, 59, 59)
                .addComponent(btnShowProductOrder)
                .addGap(62, 62, 62)
                .addComponent(btnFindOrder)
                .addGap(50, 50, 50)
                .addComponent(btnResetOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(txtIDCarrierBillExport))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreateBillExport, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShowProductOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFindOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUpdateBillExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jplBillExportLayout = new javax.swing.GroupLayout(jplBillExport);
        jplBillExport.setLayout(jplBillExportLayout);
        jplBillExportLayout.setHorizontalGroup(
            jplBillExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillExportLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jplBillExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplBillExportLayout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addContainerGap())
                    .addGroup(jplBillExportLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(195, 195, 195))))
        );
        jplBillExportLayout.setVerticalGroup(
            jplBillExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillExportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
        );

        jplBillManage.addTab("Đặt lịch hẹn", jplBillExport);

        tableBillImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Nhà cung cấp", "Tổng tiền", "Bác sĩ điều trị", "Ngày tạo", "Người tạo", "Ngày sửa", "Người sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(tableBillImport);
        if (tableBillImport.getColumnModel().getColumnCount() > 0) {
            tableBillImport.getColumnModel().getColumn(0).setMinWidth(5);
            tableBillImport.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableBillImport.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel5.setText("ID Nhà cung cấp:");

        jLabel12.setText("ID bác sĩ điều trị:");

        btnEditBillImport.setText("Sửa");
        btnEditBillImport.setEnabled(false);
        btnEditBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditBillImportActionPerformed(evt);
            }
        });

        btnAddBillImport.setText("Thêm");
        btnAddBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBillImportActionPerformed(evt);
            }
        });

        btnResetBillImport.setText("Reset");
        btnResetBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetBillImportActionPerformed(evt);
            }
        });

        btnDeleteBillImport.setText("Xoá");
        btnDeleteBillImport.setEnabled(false);
        btnDeleteBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBillImportActionPerformed(evt);
            }
        });

        btnFindBillImport.setText("Tìm kiếm");
        btnFindBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindBillImportActionPerformed(evt);
            }
        });

        btnShowListBillImport.setText("Show List Order");
        btnShowListBillImport.setEnabled(false);
        btnShowListBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowListBillImportActionPerformed(evt);
            }
        });

        txtIDSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jplBillImportLayout = new javax.swing.GroupLayout(jplBillImport);
        jplBillImport.setLayout(jplBillImportLayout);
        jplBillImportLayout.setHorizontalGroup(
            jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillImportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1153, Short.MAX_VALUE)
                .addGap(212, 212, 212))
            .addGroup(jplBillImportLayout.createSequentialGroup()
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplBillImportLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnShowListBillImport))
                    .addGroup(jplBillImportLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnAddBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplBillImportLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
                .addGap(31, 31, 31)
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jplBillImportLayout.createSequentialGroup()
                            .addComponent(btnFindBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(67, 67, 67)
                            .addComponent(btnResetBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jplBillImportLayout.createSequentialGroup()
                            .addComponent(btnEditBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(67, 67, 67)
                            .addComponent(btnDeleteBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtIDSupplier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIDCarrierBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplBillImportLayout.setVerticalGroup(
            jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillImportLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIDSupplier)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(txtIDCarrierBillImport))
                .addGap(18, 18, 18)
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowListBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(472, Short.MAX_VALUE))
        );

        jplBillManage.addTab("Hoá đơn nhập kho", jplBillImport);

        jTabbedPane1.addTab("Quản lý hóa đơn", jplBillManage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowListBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowListBillImportActionPerformed
        // TODO add your handling code here:
        int selectedIndex = tableBillImport.getSelectedRow();
        if(selectedIndex >= 0) {
            int billID = billImportList.get(selectedIndex).getId();
            billImportDetailFrm = new BillImportDetailFrm(this, rootPaneCheckingEnabled);
            billImportDetailFrm.getIDBill(billID, id_user);
            billImportDetailFrm.setVisible(true);
            showBillImport();
            showProduct();
            showStatistical();
        }
    }//GEN-LAST:event_btnShowListBillImportActionPerformed

    private void btnFindBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindBillImportActionPerformed
        // TODO add your handling code here:
        txtIDSupplier.setText("");
        txtIDCarrierBillImport.setText("");

        btnEditBillImport.setEnabled(false);
        btnDeleteBillImport.setEnabled(false);
        btnShowListBillImport.setEnabled(false);
        String input = JOptionPane.showInputDialog(this,"Nhập tên nhà cung cấp cần tìm kiếm!");
        if(input != null && input.length() > 0 ){
            billImportList = BillImportController.findByNameSupplier(input);
            if(billImportList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy thông tin hóa đơn của nhà cung cấp này");
                showBillImport();
            } else {
                tableModelBillImport.setRowCount(0);

                billImportList.forEach((billImport) -> {
                    tableModelBillImport.addRow(new Object[] {tableModelBillImport.getRowCount() + 1,
                        billImport.getNameSupplier(),
                        billImport.getTotal(),
                        billImport.getNameCarrier(),
                        billImport.getCreatedAt(),
                        billImport.getNameStaffCreated(),
                        billImport.getUpdatedAt(),
                        billImport.getNameStaffUpdated()
                    });
                });
            }
        } else {
            showBillImport();
        }
    }//GEN-LAST:event_btnFindBillImportActionPerformed

    private void btnDeleteBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBillImportActionPerformed
        // TODO add your handling code here:
        int selectedIndex = tableBillImport.getSelectedRow();
        if (selectedIndex >= 0 ){
            BillImport billImport = billImportList.get(selectedIndex);

            int option = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa không?");

            if(option == 0){
                BillImportController.delete(billImport.getId());

                txtIDSupplier.setText("");
                txtIDCarrierBillImport.setText("");

                btnEditBillImport.setEnabled(false);
                btnDeleteBillImport.setEnabled(false);
                btnShowListBillImport.setEnabled(false);
                JOptionPane.showMessageDialog(rootPane, "Bạn đã xóa thành công");
                showBillImport();
            }
        }
    }//GEN-LAST:event_btnDeleteBillImportActionPerformed

    private void btnResetBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetBillImportActionPerformed
        // TODO add your handling code here:
        txtIDSupplier.setText("");
        txtIDCarrierBillImport.setText("");
        
        txtIDSupplier.setEditable(true);
        btnEditBillImport.setEnabled(false);
        btnDeleteBillImport.setEnabled(false);

        showBillImport();
    }//GEN-LAST:event_btnResetBillImportActionPerformed

    private void btnAddBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBillImportActionPerformed
        // TODO add your handling code here:
        int idSupplier = 0, idCarrier = 0;
        boolean isOK = true;
        
        if(txtIDSupplier.getText().length() > 0) {
            idSupplier = Integer.valueOf(txtIDSupplier.getText());
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ID nhà cung cấp");
        }
        
        if(txtIDCarrierBillImport.getText().length() > 0) {
            idCarrier = Integer.valueOf(txtIDCarrierBillImport.getText());
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ID bác sĩ điều trị");
        }
        if(isOK) {
            if(SupplierController.findByIDSupplier(idSupplier)) {
                if(StaffController.findAllCarrierOnWorkByID(idCarrier)) {
                    Date dateNow = new Date();
                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    BillImport billImport = new BillImport(0, id_user, id_user, idSupplier, 
                            idCarrier, formatDate.format(dateNow), formatDate.format(dateNow));

                    BillImportController.insert(billImport);
                    JOptionPane.showMessageDialog(rootPane, "Bạn đã thêm Hoá đơn nhập kho thành công");

                    txtIDSupplier.setText("");
                    txtIDCarrierBillImport.setText("");

                    btnEditBillImport.setEnabled(false);
                    btnDeleteBillImport.setEnabled(false);
                    btnShowListBillImport.setEnabled(false);

                    showBillImport();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "ID bác sĩ không tồn tại hoặc đã nghỉ việc!");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "ID nhà cung cấp không tồn tại");
            }
        }
    }//GEN-LAST:event_btnAddBillImportActionPerformed

    private void btnEditBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditBillImportActionPerformed
        // TODO add your handling code here:
        int idSupplier = 0, idCarrier = 0, id = 0;

        int selectedIndex = tableBillImport.getSelectedRow();
        if(selectedIndex >= 0) {
            id = supplierList.get(selectedIndex).getId();
            idSupplier = Integer.valueOf(txtIDSupplier.getText());
            idCarrier = Integer.valueOf(txtIDCarrierBillImport.getText());
            
            if(StaffController.findAllCarrierOnWorkByID(idCarrier)) {
                Date dateNow = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                BillImport billImport = new BillImport(id, id_user, idSupplier, 
                        idCarrier, formatDate.format(dateNow));

                BillImportController.update(billImport);
                JOptionPane.showMessageDialog(rootPane, "Bạn đã sửa Hoá đơn nhập kho thành công");

                txtIDSupplier.setText("");
                txtIDCarrierBillImport.setText("");

                btnEditBillImport.setEnabled(false);
                btnDeleteBillImport.setEnabled(false);
                btnShowListBillImport.setEnabled(false);

                showBillImport();
            } else {
                JOptionPane.showMessageDialog(rootPane, "ID bác sĩ không tồn tại hoặc đã nghỉ việc!");
            }
        }
    }//GEN-LAST:event_btnEditBillImportActionPerformed

    private void btnResetSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSupplierActionPerformed
        // TODO add your handling code here:
        txtSupplier.setText("");
        txtEmailSupplier.setText("");
        txtPhoneSupplier.setText("");
        txtAddressSupplier.setText("");

        btnUpdateSupplier.setEnabled(false);
        showSupplier();
    }//GEN-LAST:event_btnResetSupplierActionPerformed

    private void btnFindSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindSupplierActionPerformed
        // TODO add your handling code here:
        txtSupplier.setText("");
        txtEmailSupplier.setText("");
        txtPhoneSupplier.setText("");
        txtAddressSupplier.setText("");

        btnUpdateSupplier.setEnabled(false);
        
        String input = JOptionPane.showInputDialog(this,"Nhập tên nhà cung cấp cần tìm kiếm:");
        if(input != null && input.length() > 0 ){
            supplierList = SupplierController.findByNameSupplier(input);
            if(supplierList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Nhà cung cấp này không tồn tại");
                showSupplier();
            } else {
                tableModelSupplier.setRowCount(0);

                supplierList.forEach((supplier) -> {
                    tableModelSupplier.addRow(new Object[] {tableModelSupplier.getRowCount() + 1,
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getPhoneNumber(),
                        supplier.getEmail(),
                        supplier.getAddress(),
                        supplier.getCreatedAt(),
                        supplier.getNameStaffCreated(),
                        supplier.getUpdatedAt(),
                        supplier.getNameStaffUpdated()
                    });
                });
            }
        } else {
            showSupplier();
        }
    }//GEN-LAST:event_btnFindSupplierActionPerformed

    private void btnUpdateSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSupplierActionPerformed
        // TODO add your handling code here:
        String name = null, email = null, phoneNumber = null,
        address = null;
        int id = 0;
        boolean isOK = true;
        int selectedIndex = tableSupplier.getSelectedRow();
        if(selectedIndex >= 0) {
            id = supplierList.get(selectedIndex).getId();

            if(txtSupplier.getText().length() > 0) {
                name = txtSupplier.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên");
            }

            if(txtEmailSupplier.getText().length() > 0) {
                email = txtEmailSupplier.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email!!");
            }

            if(txtPhoneSupplier.getText().length() > 0) {
                phoneNumber = txtPhoneSupplier.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập SĐT");
            }

            if(txtAddressSupplier.getText().length() > 0) {
                address = txtAddressSupplier.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ");
            }

            if(isOK) {
                Date dateNow = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Supplier supplier = new Supplier(id, id_user, name, phoneNumber, email, address, formatDate.format(dateNow));

                SupplierController.update(supplier);
                JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật thành công!");

                txtSupplier.setText("");
                txtEmailSupplier.setText("");
                txtPhoneSupplier.setText("");
                txtAddressSupplier.setText("");

                btnUpdateSupplier.setEnabled(false);
                showSupplier();
            }
        }
    }//GEN-LAST:event_btnUpdateSupplierActionPerformed

    private void btnAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupplierActionPerformed
        // TODO add your handling code here:
        String name = null, email = null, phoneNumber = null,
        address = null;
        boolean isOK = true;
        if(txtSupplier.getText().length() > 0) {
            name = txtSupplier.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên");
        }

        if(txtEmailSupplier.getText().length() > 0) {
            email = txtEmailSupplier.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email!!");
        }

        if(txtPhoneSupplier.getText().length() > 0) {
            phoneNumber = txtPhoneSupplier.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số điện thoại");
        }

        if(txtAddressSupplier.getText().length() > 0) {
            address = txtAddressSupplier.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ");
        }

        if(isOK) {
            Date dateNow = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Supplier supplier = new Supplier(id_user, id_user, name, phoneNumber, 
                    email, address, formatDate.format(dateNow), formatDate.format(dateNow));

            SupplierController.insert(supplier);
            JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật thành công!");

            txtSupplier.setText("");
            txtEmailSupplier.setText("");
            txtPhoneSupplier.setText("");
            txtAddressSupplier.setText("");

            btnUpdateSupplier.setEnabled(false);

            showSupplier();
        }
    }//GEN-LAST:event_btnAddSupplierActionPerformed

    private void txtSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierActionPerformed

    private void boxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxRoleActionPerformed

    private void boxGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxGenderActionPerformed

    private void btnResetStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetStaffActionPerformed
        // TODO add your handling code here:
        txtFullname.setText("");
        txtEmail.setText("");
        txtPhonenumber.setText("");
        txtAddress.setText("");
        txtPassword.setText("");

        btnUpdateStaff.setEnabled(false);
        showStaff();
    }//GEN-LAST:event_btnResetStaffActionPerformed

    private void btnFindStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindStaffActionPerformed
        // TODO add your handling code here:
        txtFullname.setText("");
        boxGender.setSelectedIndex(0);
        txtEmail.setText("");
        txtPhonenumber.setText("");
        txtAddress.setText("");
        boxRole.setSelectedIndex(0);
        txtPassword.setText("");

        btnUpdateStaff.setEnabled(false);
        
        String input = JOptionPane.showInputDialog(this,"Nhập tên người dùng cần tìm kiếm:");
        if(input != null && input.length() > 0 ){
            staffList = StaffController.findByFullnameStaff(input);
            if(staffList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Không có bác sĩ này trong hệ thống");
                showStaff();
            } else {
                tableModelStaff.setRowCount(0);

                staffList.forEach((staff) -> {
                    tableModelStaff.addRow(new Object[] {tableModelStaff.getRowCount() + 1,
                        staff.getId(),
                        staff.getFullName(),
                        staff.getGender(),
                        staff.getEmail(),
                        staff.getPhoneNumber(),
                        staff.getAddress(),
                        staff.getRoleName(),
                        staff.getPassword(),
                        staff.getCreatedAt(),
                        staff.getUpdatedAt(),
                        staff.getStatus()
                    });
                });
            }
        } else {
            showStaff();
        }
    }//GEN-LAST:event_btnFindStaffActionPerformed

    private void btnUpdateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStaffActionPerformed
        // TODO add your handling code here:

        String fullname = null, gender = null, email = null, phoneNumber = null,
        address = null, password = null, roleName = null, status = null;
        int idRole = 0, idStaff = 0;
        boolean isOK = true;
        int selectedIndex = tableStaff.getSelectedRow();
        if(selectedIndex >= 0) {
            idStaff = staffList.get(selectedIndex).getId();

            if(txtFullname.getText().length() > 0) {
                fullname = txtFullname.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên");
            }

            gender = boxGender.getSelectedItem().toString();

            email = txtEmail.getText();

            if(txtPhonenumber.getText().length() > 0) {
                phoneNumber = txtPhonenumber.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số điện thoại");
            }

            if(txtAddress.getText().length() > 0) {
                address = txtAddress.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ");
            }

            roleName = boxRole.getSelectedItem().toString();
            for(Role role : roleList) {
                if(role.getName().equals(roleName)) {
                    idRole = role.getId();
                }
            }

            if(txtPassword.getPassword().length > 0) {
                password = new String(txtPassword.getPassword());
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập mật khẩu");
            }

            status = cbStatusStaff.getSelectedItem().toString();

            if(isOK) {
                Date dateNow = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Account account = new Account(email, password);
                AccountController.update(account);
                
                Staff staff = new Staff(idStaff, idRole, fullname, gender,  
                        phoneNumber, address, formatDate.format(dateNow), status);
                StaffController.update(staff);
               
                JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật thành công!");
            }
        }

        txtFullname.setText("");
        txtEmail.setText("");
        txtPhonenumber.setText("");
        txtAddress.setText("");
        txtPassword.setText("");

        btnUpdateStaff.setEnabled(false);
        showStaff();
    }//GEN-LAST:event_btnUpdateStaffActionPerformed

    private void btnInsertStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertStaffActionPerformed
        // TODO add your handling code here:
        int idRole = 0;
        String fullname = null, gender = null, email = null, phoneNumber = null, status = null,
        address = null, roleName = null, password = null;
        boolean isOK = true;
        if(txtFullname.getText().length() > 0) {
            fullname = txtFullname.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên");
        }

        gender = boxGender.getSelectedItem().toString();
        status = cbStatusStaff.getSelectedItem().toString();
        
        if(txtEmail.getText().length() > 0) {
            email = txtEmail.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email!!");
        }

        if(txtPhonenumber.getText().length() > 0) {
            phoneNumber = txtPhonenumber.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập so dien thoai");
        }

        if(txtAddress.getText().length() > 0) {
            address = txtAddress.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ");
        }

        roleName = boxRole.getSelectedItem().toString();
        for(Role role : roleList) {
            if(role.getName().equals(roleName)) {
                idRole = role.getId();
            }
        }

        if(txtPassword.getPassword().length > 0) {
            password = new String(txtPassword.getPassword());
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập mật khẩu");
        }

        if(isOK) {
            Date dateNow = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            
            Account account = new Account(email, password);
            AccountController.insert(account);
            
            Staff staff = new Staff(idRole, fullname, gender, email, phoneNumber, 
                    address, formatDate.format(dateNow), formatDate.format(dateNow), status);
            StaffController.insert(staff);
            JOptionPane.showMessageDialog(rootPane, "Bạn đã thêm thành công!");

            txtFullname.setText("");
            boxGender.setSelectedIndex(0);
            txtEmail.setText("");
            txtPhonenumber.setText("");
            txtAddress.setText("");
            boxRole.setSelectedIndex(0);
            txtPassword.setText("");

            btnUpdateStaff.setEnabled(false);
            showStaff();
        }
    }//GEN-LAST:event_btnInsertStaffActionPerformed

    private void btnFindCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindCustomActionPerformed
        // TODO add your handling code here:
        txtNameCustom.setText("");
        cbGenderCustom.setSelectedIndex(0);
        txtEmailCustom.setText("");
        txtPhoneCustom.setText("");
        txtAddressCustom.setText("");

        btnEditCustom.setEnabled(false);
        
        String input = JOptionPane.showInputDialog(this,"Nhập tên bệnh nhân cần tìm kiếm:");
        if(input != null && input.length() > 0 ){
            customList = CustomController.findByNameCustom(input);
            if(customList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Khách hàng này không tồn tại");
                showCustom();
            }
            else {
                tableModelCustom.setRowCount(0);
        
                customList.forEach((custom) -> {
                    tableModelCustom.addRow(new Object[] {tableModelCustom.getRowCount() + 1,
                        custom.getId(),
                        custom.getFullName(),
                        custom.getGender(),
                        custom.getEmail(),
                        custom.getPhoneNumber(),
                        custom.getAddress(),
                        custom.getCreated_at(),
                        custom.getNameCreatedStaff(),
                        custom.getUpdated_at(),
                        custom.getNameUpdatedStaff()
                    });
                });
            }
        } else {
            showCustom();
        }
    }//GEN-LAST:event_btnFindCustomActionPerformed

    private void btnSelectImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectImgActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // chỉ hiện thị file
        int returnValue = fileChooser.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // lấy đường dẫn file
            String pathFile = file.getAbsolutePath();
            System.out.println(pathFile);
            BufferedImage b;
            try {
                b = ImageIO.read(file);
                jlbThumbProduct.setIcon(new ImageIcon(b.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                txtThumbProduct.setText(pathFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSelectImgActionPerformed

    private void boxCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxCategoryActionPerformed

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        // TODO add your handling code here:

        String tenSP = null, danhmuc = null, mota = null, hinhanh = null;
        int id = 0, id_Cat = 0, gia = 0;
        boolean isOK = true;
        int selectedIndex = tableProduct.getSelectedRow();
        if(selectedIndex >= 0) {
            id = products.get(selectedIndex).getId();

            if(txtTitleProduct.getText().length() > 0) {
                tenSP = txtTitleProduct.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên dược phẩm");
            }

            danhmuc = boxCategory.getSelectedItem().toString();
            for(Category category : categoryList) {
                if(category.getName().equals(danhmuc)) {
                    id_Cat = category.getId();
                }
            }

            if(txtPrice.getText().length() > 0) {
                gia = Integer.valueOf(txtPrice.getText());
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập giá dược phẩm");
            }

            if(txtDescProduct.getText().length() > 0) {
                mota = txtDescProduct.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập mô tả dược phẩm");
            }

            if(txtThumbProduct.getText().length() > 0) {
                hinhanh = txtThumbProduct.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập hình ảnh dược phẩm");
            }

            if(isOK) {
                Date dateNow = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Product product = new Product(id, id_Cat, gia, id_user, tenSP, mota, 
                        hinhanh, formatDate.format(dateNow));
                ProductController.update(product);
                JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật thành công!");
                txtTitleProduct.setText("");
                txtPrice.setText("");
                txtDescProduct.setText("");
                txtThumbProduct.setText("");
                jlbThumbProduct.setIcon(null);

                btnUpdateProduct.setEnabled(false);
                showProduct();
            }
        }
    }//GEN-LAST:event_btnUpdateProductActionPerformed

    private void btnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProductActionPerformed
        // TODO add your handling code here:
        txtTitleProduct.setText("");
        txtPrice.setText("");
        txtDescProduct.setText("");
        txtThumbProduct.setText("");
        jlbThumbProduct.setIcon(null);
        
        btnUpdateProduct.setEnabled(false);
        
        String input = JOptionPane.showInputDialog(this,"Nhập tên dược phẩm cần tìm kiếm!");
        if(input != null && input.length() > 0 ){
            products = ProductController.findByTitleProduct(input);
            if(products.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "dược phẩm không tồn tại!");
                showProduct();
            }
            else {
                tableModelProduct.setRowCount(0);
                products.forEach((prd) -> {
                    tableModelProduct.addRow(new Object[] {
                        tableModelProduct.getRowCount() + 1,
                        prd.getId(),
                        prd.getTitle(),
                        prd.getCategoryName(),
                        prd.getPrice(),
                        prd.getDescription(),
                        prd.getThumbnail(),
                        prd.getCount(),
                        prd.getCreatedAt(),
                        prd.getNameStaffCreated(),
                        prd.getUpdatedAt(),
                        prd.getNameStaffUpdated(),
                    });
                });
            }
        } else {
            showProduct();
        }
    }//GEN-LAST:event_btnFindProductActionPerformed

    private void btnResetProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetProductActionPerformed
        txtTitleProduct.setText("");
        txtPrice.setText("");
        txtDescProduct.setText("");
        txtThumbProduct.setText("");
        jlbThumbProduct.setIcon(null);
        
        btnUpdateProduct.setEnabled(false);
        showProduct();
    }//GEN-LAST:event_btnResetProductActionPerformed

    private void btnInsertProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertProductActionPerformed
        // TODO add your handling code here:
        String tenSP = null, danhmuc = null, mota = null, hinhanh = null;
        int idCat = 0, gia = 0;
        boolean isOK = true;
           
        if(txtTitleProduct.getText().length() > 0) {
            tenSP = txtTitleProduct.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên dược phẩm");
        }

        danhmuc = boxCategory.getSelectedItem().toString();
        for(Category category : categoryList) {
            if(category.getName().equals(danhmuc)) {
                idCat = category.getId();
            }
        }

        if(txtPrice.getText().length() > 0) {
            gia = Integer.valueOf(txtPrice.getText());
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập giá dược phẩm");
        }

        if(txtDescProduct.getText().length() > 0) {
            mota = txtDescProduct.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập mô tả dược phẩm");
        }

        if(txtThumbProduct.getText().length() > 0) {
            hinhanh = txtThumbProduct.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập hình ảnh dược phẩm");
        }

       if(isOK) {
                Date dateNow = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Product product = new Product(idCat, gia, 0, id_user, id_user, 
                        tenSP, mota, hinhanh, formatDate.format(dateNow), formatDate.format(dateNow));
                ProductController.insert(product);
                JOptionPane.showMessageDialog(rootPane, "Bạn đã thêm thành công!");
                txtTitleProduct.setText("");
                txtPrice.setText("");
                txtDescProduct.setText("");
                txtThumbProduct.setText("");
                jlbThumbProduct.setIcon(null);

                btnUpdateProduct.setEnabled(false);
                showProduct();
            }
    }//GEN-LAST:event_btnInsertProductActionPerformed

    private void cbStatusStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusStaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusStaffActionPerformed

    private void txtPhoneCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneCustomActionPerformed

    private void jtfDateStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDateStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDateStartActionPerformed

    private void btnCreateBillExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateBillExportActionPerformed
        // TODO add your handling code here:
        String name = null, note = null, dateStartBorrow = null, 
                dateEndBorrow = null, nameCarrier = null;
        int idCustom = 0, idCarrier = 0;
        boolean isOK = true;

        if(txtIDCustom.getText().length() > 0) {
            idCustom = Integer.valueOf(txtIDCustom.getText());
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ID bệnh nhân");
        }
        
        if(txtIDCarrierBillExport.getText().length() > 0) {
            idCarrier = Integer.valueOf(txtIDCarrierBillExport.getText());
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ID bác sĩ điều trị");
        }

        
        note = txtNoteBillExport.getText();

        if(jtfDateStart.getText().length() > 0) {
            dateStartBorrow = jtfDateStart.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ngày mượn");
        }

        if(jtfDateEnd.getText().length() > 0) {
            dateEndBorrow = jtfDateEnd.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ngày trả");
        }
        

        if(isOK) {
            if(CustomController.findByIDCustom(idCustom)) {
                if(StaffController.findAllCarrierOnWorkByID(idCarrier)) {
                    Date dateNow = new Date();
                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    BillExport billExport = new BillExport(0, id_user, id_user, idCustom,
                        idCarrier, formatDate.format(dateNow), formatDate.format(dateNow), note,
                        dateStartBorrow, dateEndBorrow);
                    BillExportController.insert(billExport);

                    JOptionPane.showMessageDialog(rootPane, "Bạn đã thêm lịch hẹn thành công!");

                    txtIDCustom.setText("");
                    txtIDCarrierBillExport.setText("");
                    jtfDateStart.setText("");
                    jtfDateEnd.setText("");
                    txtNoteBillExport.setText("");

                    btnShowProductOrder.setEnabled(false);
                    btnUpdateBillExport.setEnabled(false);
                    btnDeleteOrder.setEnabled(false);

                    showBillExport();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "ID bác sĩ không tồn tại hoặc đã nghỉ việc!");
                }           
            } else {
                JOptionPane.showMessageDialog(rootPane, "ID bệnh nhân không tồn tại!");
            }
        }

    }//GEN-LAST:event_btnCreateBillExportActionPerformed

    private void btnResetOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetOrderActionPerformed
        // TODO add your handling code here:
        txtIDCustom.setText("");
        txtIDCarrierBillExport.setText("");
        jtfDateStart.setText("");
        jtfDateEnd.setText("");
        txtNoteBillExport.setText("");

        btnShowProductOrder.setEnabled(false);
        btnUpdateBillExport.setEnabled(false);
        btnDeleteOrder.setEnabled(false);
        showBillExport();
    }//GEN-LAST:event_btnResetOrderActionPerformed

    private void btnFindOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindOrderActionPerformed
        // TODO add your handling code here:
        txtIDCustom.setText("");
        txtIDCarrierBillExport.setText("");
        jtfDateStart.setText("");
        jtfDateEnd.setText("");
        txtNoteBillExport.setText("");

        btnShowProductOrder.setEnabled(false);
        btnUpdateBillExport.setEnabled(false);
        btnDeleteOrder.setEnabled(false);
        
        String input = JOptionPane.showInputDialog(this,"Nhập tên bệnh nhân cần tìm kiếm!");
        if(input != null && input.length() > 0 ){
            billExportList = BillExportController.findByNameCustom(input);
            if(billExportList.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy hóa đơn của bệnh nhân này");
                showBillExport();
            } else {
                tableModelBillExport.setRowCount(0);

                billExportList.forEach((billExport) -> {
                    tableModelBillExport.addRow(new Object[] {tableModelBillExport.getRowCount() + 1,
                        billExport.getNameCustom(),
                        billExport.getGender(),
                        billExport.getEmail(),
                        billExport.getPhoneNumber(),
                        billExport.getAddress(),
                        billExport.getNote(),
                        billExport.getTotal(),
                        billExport.getNameCarrier(),
                        billExport.getTimeStartedBorrowed(),
                        billExport.getTimeEndBorrowed(),
                        billExport.getCreatedAt(),
                        billExport.getNameStaffCreated(),
                        billExport.getUpdatedAt(),
                        billExport.getNameStaffUpdated()
                    });
                });
            }
        } else {
            showBillExport();
        }
    }//GEN-LAST:event_btnFindOrderActionPerformed

    private void btnShowProductOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowProductOrderActionPerformed
        // TODO add your handling code here:
        int selectedIndex = tableBillExport.getSelectedRow();
        if(selectedIndex >= 0) {
            int billID = billExportList.get(selectedIndex).getId();
            billExportDetailFrm = new BillExportDetailFrm(this, rootPaneCheckingEnabled);
            billExportDetailFrm.getIDBill(billID, id_user);
            billExportDetailFrm.setVisible(true);
            showBillExport();
            showProduct();
            showStatistical();
        }
    }//GEN-LAST:event_btnShowProductOrderActionPerformed

    private void btnDeleteOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteOrderActionPerformed
        // TODO add your handling code here:
        int selectedIndex = tableBillExport.getSelectedRow();
        if (selectedIndex >= 0 ){
            BillExport billExport = billExportList.get(selectedIndex);

            int option = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa không?");

            if(option == 0){
                BillExportController.delete(billExport.getId());

                txtIDCustom.setText("");
                txtIDCarrierBillExport.setText("");
                jtfDateStart.setText("");
                jtfDateEnd.setText("");
                txtNoteBillExport.setText("");

                btnShowProductOrder.setEnabled(false);
                btnUpdateBillExport.setEnabled(false);
                btnDeleteOrder.setEnabled(false);
                JOptionPane.showMessageDialog(rootPane, "Bạn đã xóa thành công");
                showBillExport();
            }
        }
    }//GEN-LAST:event_btnDeleteOrderActionPerformed

    private void btnAddCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomActionPerformed
        // TODO add your handling code here:
        String name = null, gender = null, email = null, phoneNumber = null,
        address = null;
        boolean isOK = true;

        if(txtNameCustom.getText().length() > 0) {
            name = txtNameCustom.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên bệnh nhân");
        }

        gender = cbGenderCustom.getSelectedItem().toString();

        if(txtEmailCustom.getText().length() > 0) {
            email = txtEmailCustom.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email bệnh nhân!!");
        }

        if(txtPhoneCustom.getText().length() > 0) {
            phoneNumber = txtPhoneCustom.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số điện thoại bệnh nhân");
        }

        if(txtAddressCustom.getText().length() > 0) {
            address = txtAddressCustom.getText();
        } else {
            isOK = false;
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ bệnh nhân");
        }

        if(isOK) {
            Date dateNow = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Custom custom = new Custom(id_user, id_user, name, gender, email, phoneNumber,
                address, formatDate.format(dateNow), formatDate.format(dateNow));
            CustomController.insert(custom);

            JOptionPane.showMessageDialog(rootPane, "Bạn đã thêm bệnh nhân thành công!");

            txtNameCustom.setText("");
            cbGenderCustom.setSelectedIndex(0);
            txtEmailCustom.setText("");
            txtPhoneCustom.setText("");
            txtAddressCustom.setText("");
            
            btnEditCustom.setEnabled(false);

            showCustom();
        }
    }//GEN-LAST:event_btnAddCustomActionPerformed

    private void btnEditCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomActionPerformed
        // TODO add your handling code here:
        String name = null, gender = null, email = null, phoneNumber = null,
        address = null;
        int idCustom = 0;
        boolean isOK = true;
        int selectedIndex = tableCustom.getSelectedRow();
        if(selectedIndex >= 0) {
            idCustom = customList.get(selectedIndex).getId();
            
            if(txtNameCustom.getText().length() > 0) {
                name = txtNameCustom.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên bệnh nhân");
            }

            gender = cbGenderCustom.getSelectedItem().toString();

            if(txtEmailCustom.getText().length() > 0) {
                email = txtEmailCustom.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập Email bệnh nhân!!");
            }

            if(txtPhoneCustom.getText().length() > 0) {
                phoneNumber = txtPhoneCustom.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số điện thoại bệnh nhân");
            }

            if(txtAddressCustom.getText().length() > 0) {
                address = txtAddressCustom.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ bệnh nhân");
            }

            if(isOK) {
                Date dateNow = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Custom custom = new Custom(idCustom, id_user, name, gender, email, phoneNumber,
                    address, formatDate.format(dateNow));
                CustomController.update(custom);

                JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật thông tin bệnh nhân thành công!");

                txtNameCustom.setText("");
                cbGenderCustom.setSelectedIndex(0);
                txtEmailCustom.setText("");
                txtPhoneCustom.setText("");
                txtAddressCustom.setText("");
                
                btnEditCustom.setEnabled(false);

                showCustom();
            }
        }
        
    }//GEN-LAST:event_btnEditCustomActionPerformed

    private void btnResetCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCustomActionPerformed
        // TODO add your handling code here:
        txtNameCustom.setText("");
        cbGenderCustom.setSelectedIndex(0);
        txtEmailCustom.setText("");
        txtPhoneCustom.setText("");
        txtAddressCustom.setText("");

        btnEditCustom.setEnabled(false);
        
        showCustom();
    }//GEN-LAST:event_btnResetCustomActionPerformed

    private void btnUpdateBillExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBillExportActionPerformed
        // TODO add your handling code here:
        String name = null, note = null, dateStartBorrow = null, 
                dateEndBorrow = null, nameCarrier = null;
        int idCustom = 0, idCarrier = 0, id = 0;
        boolean isOK = true;
        int selectedIndex = tableBillExport.getSelectedRow();
        if(selectedIndex >= 0) {
            id = billExportList.get(selectedIndex).getId();
            idCustom = Integer.valueOf(txtIDCustom.getText());
            idCarrier = Integer.valueOf(txtIDCarrierBillExport.getText());
            note = txtNoteBillExport.getText();

            if(jtfDateStart.getText().length() > 0) {
                dateStartBorrow = jtfDateStart.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ngày mượn");
            }

            if(jtfDateEnd.getText().length() > 0) {
                dateEndBorrow = jtfDateEnd.getText();
            } else {
                isOK = false;
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập ngày trả");
            }
            
            if(isOK) {
                if(StaffController.findAllCarrierOnWorkByID(idCarrier)) {
                    Date dateNow = new Date();
                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    BillExport billExport = new BillExport(id, id_user, idCustom,
                        idCarrier, formatDate.format(dateNow), note, dateStartBorrow, dateEndBorrow);
                    BillExportController.update(billExport);

                    JOptionPane.showMessageDialog(rootPane, "Bạn đã cập nhật lịch hẹn thành công!");

                    txtIDCustom.setText("");
                    txtIDCarrierBillExport.setText("");
                    jtfDateStart.setText("");
                    jtfDateEnd.setText("");
                    txtNoteBillExport.setText("");

                    btnShowProductOrder.setEnabled(false);
                    btnUpdateBillExport.setEnabled(false);
                    btnDeleteOrder.setEnabled(false);

                    showBillExport();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "ID bác sĩ không tồn tại hoặc đã nghỉ việc!");
                }
            }
        }
    }//GEN-LAST:event_btnUpdateBillExportActionPerformed

    private void txtIDSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSupplierActionPerformed

    private void btnCarrierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarrierActionPerformed
        // TODO add your handling code here:
        listCarrierFrm = new ListCarrierFrm(this, rootPaneCheckingEnabled);
        listCarrierFrm.showAll();
        listCarrierFrm.setVisible(true);
    }//GEN-LAST:event_btnCarrierActionPerformed

    private void btnWarhouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWarhouseActionPerformed
        // TODO add your handling code here:
        listWareHouseFrm = new ListWareHouseFrm(this, rootPaneCheckingEnabled);
        listWareHouseFrm.showAll();
        listWareHouseFrm.setVisible(true);
    }//GEN-LAST:event_btnWarhouseActionPerformed

    private void btnConsultingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultingActionPerformed
        // TODO add your handling code here:
        listConsultingFrm = new ListConsultingFrm(this, rootPaneCheckingEnabled);
        listConsultingFrm.showAll();
        listConsultingFrm.setVisible(true);
    }//GEN-LAST:event_btnConsultingActionPerformed

    private void txtThumbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThumbProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThumbProductActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AppManager().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category;
    private javax.swing.JLabel Desc;
    private javax.swing.JLabel Desc1;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JComboBox<String> boxCategory;
    private javax.swing.JComboBox<String> boxGender;
    private javax.swing.JComboBox<String> boxRole;
    private javax.swing.JButton btnAddBillImport;
    private javax.swing.JButton btnAddCustom;
    private javax.swing.JButton btnAddSupplier;
    private javax.swing.JButton btnCarrier;
    private javax.swing.JButton btnConsulting;
    private javax.swing.JButton btnCreateBillExport;
    private javax.swing.JButton btnDeleteBillImport;
    private javax.swing.JButton btnDeleteOrder;
    private javax.swing.JButton btnEditBillImport;
    private javax.swing.JButton btnEditCustom;
    private javax.swing.JButton btnFindBillImport;
    private javax.swing.JButton btnFindCustom;
    private javax.swing.JButton btnFindOrder;
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnFindStaff;
    private javax.swing.JButton btnFindSupplier;
    private javax.swing.JButton btnInsertProduct;
    private javax.swing.JButton btnInsertStaff;
    private javax.swing.JButton btnResetBillImport;
    private javax.swing.JButton btnResetCustom;
    private javax.swing.JButton btnResetOrder;
    private javax.swing.JButton btnResetProduct;
    private javax.swing.JButton btnResetStaff;
    private javax.swing.JButton btnResetSupplier;
    private javax.swing.JButton btnSelectImg;
    private javax.swing.JButton btnShowListBillImport;
    private javax.swing.JButton btnShowProductOrder;
    private javax.swing.JButton btnUpdateBillExport;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.JButton btnUpdateStaff;
    private javax.swing.JButton btnUpdateSupplier;
    private javax.swing.JButton btnWarhouse;
    private javax.swing.JComboBox<String> cbGenderCustom;
    private javax.swing.JComboBox<String> cbStatusStaff;
    private javax.swing.JPanel infoProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlbEmail;
    private javax.swing.JLabel jlbGender;
    private javax.swing.JLabel jlbInterest;
    private javax.swing.JLabel jlbName;
    private javax.swing.JLabel jlbPhoneNumber;
    private javax.swing.JLabel jlbThumbProduct;
    private javax.swing.JLabel jlbToTalEntered;
    private javax.swing.JLabel jlbTotalRevenue;
    private javax.swing.JPanel jplBillExport;
    private javax.swing.JPanel jplBillImport;
    private javax.swing.JTabbedPane jplBillManage;
    private javax.swing.JPanel jplGuest;
    private javax.swing.JPanel jplProduct;
    private javax.swing.JPanel jplStaff;
    private javax.swing.JPanel jplStatistic;
    private javax.swing.JPanel jplSupplier;
    private javax.swing.JTextField jtfDateEnd;
    private javax.swing.JTextField jtfDateStart;
    private javax.swing.JTable tableBillExport;
    private javax.swing.JTable tableBillImport;
    private javax.swing.JTable tableCustom;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTable tableStaff;
    private javax.swing.JTable tableSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddressCustom;
    private javax.swing.JTextField txtAddressSupplier;
    private javax.swing.JTextArea txtDescProduct;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailCustom;
    private javax.swing.JTextField txtEmailSupplier;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtIDCarrierBillExport;
    private javax.swing.JTextField txtIDCarrierBillImport;
    private javax.swing.JTextField txtIDCustom;
    private javax.swing.JTextField txtIDSupplier;
    private javax.swing.JTextField txtNameCustom;
    private javax.swing.JTextArea txtNoteBillExport;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhoneCustom;
    private javax.swing.JTextField txtPhoneSupplier;
    private javax.swing.JTextField txtPhonenumber;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSupplier;
    private javax.swing.JTextField txtThumbProduct;
    private javax.swing.JTextField txtTitleProduct;
    // End of variables declaration//GEN-END:variables
}
