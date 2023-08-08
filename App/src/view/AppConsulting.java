/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.BillExportController;
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
import controller.StaffController;
import model.Role;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import model.BillExport;
import model.Custom;
import model.Staff;
/**
 *
 * @author Admin
 */
public class AppConsulting extends javax.swing.JFrame {
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
    
    BillExportDetailFrm billExportDetailFrm;
    
    int id_user = 0;

    /**
     * Creates new form NewJFrame
     */
    public AppConsulting() {
        
    }
    
    public AppConsulting(int id) {
        initComponents();
      
        this.setLocationRelativeTo(null);
     
        id_user = id;
        
        tableModelProduct = (DefaultTableModel) tableProduct.getModel();
        tableModelStaff = (DefaultTableModel) tableStaff.getModel();
        tableModelBillExport = (DefaultTableModel) tableBillExport.getModel();
        tableModelCustom = (DefaultTableModel) tableCustom.getModel();
        
        showProduct();
        showStaff();
        showBillExport();
        showCustom();
        
        
        
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
        
    }
    
    public void showComboBox_Category() {
        DefaultComboBoxModel<String> comboBoxModelProduct = (DefaultComboBoxModel<String>) boxCategory.getModel();
        comboBoxModelProduct.removeAllElements();
        categoryList = CategoryController.findAll();
        categoryList.forEach(category -> {
            comboBoxModelProduct.addElement(category.getName());
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
        staffList = StaffController.findAllCarrier();
        
        tableModelStaff.setRowCount(0);
        
        staffList.forEach((staff) -> {
            System.out.println(staff.getFullName());
            tableModelStaff.addRow(new Object[] {tableModelStaff.getRowCount() + 1,
                staff.getId(),
                staff.getFullName(),
                staff.getGender(),
                staff.getEmail(),
                staff.getPhoneNumber(),
                staff.getAddress(),
                staff.getRoleName(),
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
        btnResetProduct = new javax.swing.JButton();
        btnFindProduct = new javax.swing.JButton();
        boxCategory = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtDescProduct = new javax.swing.JTextArea();
        txtThumbProduct = new javax.swing.JTextField();
        Desc1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jlbThumbProduct = new javax.swing.JLabel();
        jplStaff = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableStaff = new javax.swing.JTable();
        btnFindStaff = new javax.swing.JButton();
        btnResetStaff = new javax.swing.JButton();
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

        Desc1.setText("Link hình ảnh:");

        javax.swing.GroupLayout infoProductLayout = new javax.swing.GroupLayout(infoProduct);
        infoProduct.setLayout(infoProductLayout);
        infoProductLayout.setHorizontalGroup(
            infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoProductLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Desc1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoProductLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(infoProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtThumbProduct)
                            .addComponent(txtPrice)
                            .addComponent(boxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtTitleProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(129, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
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
                        .addComponent(jlbThumbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(331, Short.MAX_VALUE))))
        );
        jplProductLayout.setVerticalGroup(
            jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbThumbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý dược phẩm", jplProduct);

        tableStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID bác sĩ", "Họ và tên", "Giới tính", "Email", "Số điện thoại", "Địa chỉ", "Chức danh", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, true, true
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
        }

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

        javax.swing.GroupLayout jplStaffLayout = new javax.swing.GroupLayout(jplStaff);
        jplStaff.setLayout(jplStaffLayout);
        jplStaffLayout.setHorizontalGroup(
            jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplStaffLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnFindStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(btnResetStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1279, Short.MAX_VALUE)
        );
        jplStaffLayout.setVerticalGroup(
            jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplStaffLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jplStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(502, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách bác sĩ điều trị", jplStaff);

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
                .addContainerGap(428, Short.MAX_VALUE))
            .addGroup(jplGuestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1259, Short.MAX_VALUE)
                .addContainerGap())
        );
        jplGuestLayout.setVerticalGroup(
            jplGuestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplGuestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý bệnh nhân", jplGuest);

        tableBillExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên bệnh nhân", "Giới tính", "Email bệnh nhân", "SĐT", "Địa chỉ bệnh nhân", "Ghi chú", "Tổng tiền ", "Người điều trị", "Ngày mượn", "Ngày trả", "Ngày tạo", "Người tạo", "Ngày sửa", "Người sửa"
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
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
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
                .addContainerGap(183, Short.MAX_VALUE))
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
                .addContainerGap(396, Short.MAX_VALUE))
        );

        jplBillManage.addTab("Đặt lịch hẹn", jplBillExport);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } else {
            showCustom();
        }
    }//GEN-LAST:event_btnFindCustomActionPerformed

    private void txtPhoneCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneCustomActionPerformed

    private void boxCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxCategoryActionPerformed

    private void btnFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindProductActionPerformed
        // TODO add your handling code here:
        txtTitleProduct.setText("");
        txtPrice.setText("");
        txtDescProduct.setText("");
        txtThumbProduct.setText("");
        jlbThumbProduct.setIcon(null);
        
        
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
        showProduct();
    }//GEN-LAST:event_btnResetProductActionPerformed

    private void btnResetStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetStaffActionPerformed
        // TODO add your handling code here:
        showStaff();
    }//GEN-LAST:event_btnResetStaffActionPerformed

    private void btnFindStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindStaffActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this,"Nhập tên người dùng cần tìm kiếm:");
        if(input != null && input.length() > 0 ){
            staffList = StaffController.findByFullnameCarrier(input);

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
                    staff.getStatus()
                });
            });
        } else {
            showStaff();
        }
    }//GEN-LAST:event_btnFindStaffActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(AppConsulting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppConsulting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppConsulting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppConsulting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AppConsulting().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category;
    private javax.swing.JLabel Desc;
    private javax.swing.JLabel Desc1;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JComboBox<String> boxCategory;
    private javax.swing.JButton btnAddCustom;
    private javax.swing.JButton btnCreateBillExport;
    private javax.swing.JButton btnDeleteOrder;
    private javax.swing.JButton btnEditCustom;
    private javax.swing.JButton btnFindCustom;
    private javax.swing.JButton btnFindOrder;
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnFindStaff;
    private javax.swing.JButton btnResetCustom;
    private javax.swing.JButton btnResetOrder;
    private javax.swing.JButton btnResetProduct;
    private javax.swing.JButton btnResetStaff;
    private javax.swing.JButton btnShowProductOrder;
    private javax.swing.JButton btnUpdateBillExport;
    private javax.swing.JComboBox<String> cbGenderCustom;
    private javax.swing.JPanel infoProduct;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlbThumbProduct;
    private javax.swing.JPanel jplBillExport;
    private javax.swing.JTabbedPane jplBillManage;
    private javax.swing.JPanel jplGuest;
    private javax.swing.JPanel jplProduct;
    private javax.swing.JPanel jplStaff;
    private javax.swing.JTextField jtfDateEnd;
    private javax.swing.JTextField jtfDateStart;
    private javax.swing.JTable tableBillExport;
    private javax.swing.JTable tableCustom;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTable tableStaff;
    private javax.swing.JTextField txtAddressCustom;
    private javax.swing.JTextArea txtDescProduct;
    private javax.swing.JTextField txtEmailCustom;
    private javax.swing.JTextField txtIDCarrierBillExport;
    private javax.swing.JTextField txtIDCustom;
    private javax.swing.JTextField txtNameCustom;
    private javax.swing.JTextArea txtNoteBillExport;
    private javax.swing.JTextField txtPhoneCustom;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtThumbProduct;
    private javax.swing.JTextField txtTitleProduct;
    // End of variables declaration//GEN-END:variables
}
