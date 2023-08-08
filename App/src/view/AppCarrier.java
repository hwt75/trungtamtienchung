/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.BillExportController;
import controller.BillImportController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Category;
import model.Product;
import controller.ProductController;
import controller.SupplierController;
import model.Role;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.BillExport;
import model.BillImport;
import model.Custom;
import model.Staff;
import model.Supplier;
/**
 *
 * @author Admin
 */
public class AppCarrier extends javax.swing.JFrame {
    DefaultTableModel tableModelProduct;
    DefaultTableModel tableModelSupplier;
    DefaultTableModel tableModelBillExport;
    DefaultTableModel tableModelBillImport;
    
    
    List<Product> products = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();
    List<Staff> carrierList = new ArrayList<>();
    List<Custom> customList = new ArrayList<>();
    List<Role> roleList = new ArrayList<>();
    List<BillExport> billExportList = new ArrayList<>();
    List<BillImport> billImportList = new ArrayList<>();
    List<Supplier> supplierList = new ArrayList<>();
    
    BillExportDetailCarrierFrm billExportDetailCarrierFrm;
    BillImportDetailCarrierFrm billImportDetailCarrierFrm;
    ListCarrierFrm listCarrierFrm;
    ListConsultingFrm listConsultingFrm;
    ListWareHouseFrm listWareHouseFrm;
    
    int id_user = 0;

    /**
     * Creates new form NewJFrame
     */
    public AppCarrier() {
        
    }
    
    public AppCarrier(int id) {
        initComponents();
      
        id_user = id;
        this.setLocationRelativeTo(null);
//        loginFrm = new LoginFrm(this, rootPaneCheckingEnabled);
//        while(id_user == 0) {
//            loginFrm.setVisible(true);
//            id_user = loginFrm.getStaffId();
//            System.out.println(id_user);
//        }
    
        
        tableModelProduct = (DefaultTableModel) tableProduct.getModel();
        tableModelSupplier = (DefaultTableModel) tableSupplier.getModel();
        tableModelBillExport = (DefaultTableModel) tableBillExport.getModel();
        tableModelBillImport = (DefaultTableModel) tableBillImport.getModel();
        
        showProduct();
        showBillExport();
        showBillImport();
        showSupplier();
        
        
        
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
        
        tableBillExport.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
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
    
    private void showBillExport() {
        billExportList = BillExportController.findByID(id_user);
        
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
        billImportList = BillImportController.findByID(id_user);
        
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
        btnSelectImg = new javax.swing.JButton();
        jplSupplier = new javax.swing.JPanel();
        btnFindSupplier = new javax.swing.JButton();
        btnResetSupplier = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        jplBillManage = new javax.swing.JTabbedPane();
        jplBillExport = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableBillExport = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnShowProductOrder = new javax.swing.JButton();
        btnFindOrder = new javax.swing.JButton();
        btnResetOrder = new javax.swing.JButton();
        jplBillImport = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableBillImport = new javax.swing.JTable();
        btnResetBillImport = new javax.swing.JButton();
        btnFindBillImport = new javax.swing.JButton();
        btnShowListBillImport = new javax.swing.JButton();

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
                            .addComponent(txtTitleProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(infoProductLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(btnResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(14, 14, 14)
                        .addComponent(infoProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jplProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jplProductLayout.createSequentialGroup()
                                .addComponent(jlbThumbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(351, Short.MAX_VALUE))
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
                .addContainerGap(362, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý dược phẩm", jplProduct);

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
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jplSupplierLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnFindSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288)
                        .addComponent(btnResetSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        jplSupplierLayout.setVerticalGroup(
            jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplSupplierLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jplSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(547, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý nhà cung cấp", jplSupplier);

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnShowProductOrder)
                .addGap(96, 96, 96)
                .addComponent(btnFindOrder)
                .addGap(153, 153, 153)
                .addComponent(btnResetOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(528, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowProductOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jplBillExportLayout = new javax.swing.GroupLayout(jplBillExport);
        jplBillExport.setLayout(jplBillExportLayout);
        jplBillExportLayout.setHorizontalGroup(
            jplBillExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillExportLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(195, 195, 195))
            .addGroup(jplBillExportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jplBillExportLayout.setVerticalGroup(
            jplBillExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillExportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(731, Short.MAX_VALUE))
        );

        jplBillManage.addTab("Đặt lịch hẹn", jplBillExport);

        tableBillImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Nhà cung cấp", "Tổng tiền", "Người điều trị", "Ngày tạo", "Người tạo", "Ngày sửa", "Người sửa"
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

        btnResetBillImport.setText("Reset");
        btnResetBillImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetBillImportActionPerformed(evt);
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

        javax.swing.GroupLayout jplBillImportLayout = new javax.swing.GroupLayout(jplBillImport);
        jplBillImport.setLayout(jplBillImportLayout);
        jplBillImportLayout.setHorizontalGroup(
            jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillImportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
                .addGap(212, 212, 212))
            .addGroup(jplBillImportLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnShowListBillImport)
                .addGap(64, 64, 64)
                .addComponent(btnFindBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(btnResetBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jplBillImportLayout.setVerticalGroup(
            jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplBillImportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplBillImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowListBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFindBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetBillImport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(674, Short.MAX_VALUE))
        );

        jplBillManage.addTab("Hoá đơn nhập kho", jplBillImport);

        jTabbedPane1.addTab("Quản lý hóa đơn", jplBillManage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
            billImportDetailCarrierFrm = new BillImportDetailCarrierFrm(this, rootPaneCheckingEnabled);
            billImportDetailCarrierFrm.getIDBill(billID, id_user);
            billImportDetailCarrierFrm.setVisible(true);
            showBillImport();
        }
    }//GEN-LAST:event_btnShowListBillImportActionPerformed

    private void btnFindBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindBillImportActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this,"Nhập tên nhà cung cấp cần tìm kiếm!");
        if(input != null && input.length() > 0 ){
            billImportList = BillImportController.findByNameSupplierCarrier(input, id_user);

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
        } else {
            showBillImport();
        }
    }//GEN-LAST:event_btnFindBillImportActionPerformed

    private void btnResetBillImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetBillImportActionPerformed
        // TODO add your handling code here:
        showBillImport();
    }//GEN-LAST:event_btnResetBillImportActionPerformed

    private void btnResetSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSupplierActionPerformed
        // TODO add your handling code here:
        showSupplier();
    }//GEN-LAST:event_btnResetSupplierActionPerformed

    private void btnFindSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindSupplierActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this,"Nhập tên nhà cung cấp cần tìm kiếm:");
        if(input != null && input.length() > 0 ){
            supplierList = SupplierController.findByNameSupplier(input);

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
        } else {
            showSupplier();
        }
    }//GEN-LAST:event_btnFindSupplierActionPerformed

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
        
//        File file = new File("");
//        // lấy đường dẫn file
//        String pathFile = file.getAbsolutePath();
//        System.out.println(pathFile);
//        BufferedImage b;
//        try {
//            b = ImageIO.read(file);
//            jlbThumbProduct.setIcon(new ImageIcon(b.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
//            txtThumbProduct.setText(pathFile);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        
        showProduct();
    }//GEN-LAST:event_btnResetProductActionPerformed

    private void btnResetOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetOrderActionPerformed
        // TODO add your handling code here:
        btnShowProductOrder.setEnabled(false);
        showBillExport();
    }//GEN-LAST:event_btnResetOrderActionPerformed

    private void btnFindOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindOrderActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(this,"Nhập tên bệnh nhân cần tìm kiếm!");
        if(input != null && input.length() > 0 ){
            billExportList = BillExportController.findByNameCustomCarrier(input, id_user);

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
            billExportDetailCarrierFrm = new BillExportDetailCarrierFrm(this, rootPaneCheckingEnabled);
            billExportDetailCarrierFrm.getIDBill(billID, id_user);
            billExportDetailCarrierFrm.setVisible(true);
            showBillExport();
        }
    }//GEN-LAST:event_btnShowProductOrderActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(AppCarrier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppCarrier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppCarrier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppCarrier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AppCarrier().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category;
    private javax.swing.JLabel Desc;
    private javax.swing.JLabel Desc1;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JComboBox<String> boxCategory;
    private javax.swing.JButton btnFindBillImport;
    private javax.swing.JButton btnFindOrder;
    private javax.swing.JButton btnFindProduct;
    private javax.swing.JButton btnFindSupplier;
    private javax.swing.JButton btnResetBillImport;
    private javax.swing.JButton btnResetOrder;
    private javax.swing.JButton btnResetProduct;
    private javax.swing.JButton btnResetSupplier;
    private javax.swing.JButton btnSelectImg;
    private javax.swing.JButton btnShowListBillImport;
    private javax.swing.JButton btnShowProductOrder;
    private javax.swing.JPanel infoProduct;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlbThumbProduct;
    private javax.swing.JPanel jplBillExport;
    private javax.swing.JPanel jplBillImport;
    private javax.swing.JTabbedPane jplBillManage;
    private javax.swing.JPanel jplProduct;
    private javax.swing.JPanel jplSupplier;
    private javax.swing.JTable tableBillExport;
    private javax.swing.JTable tableBillImport;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTable tableSupplier;
    private javax.swing.JTextArea txtDescProduct;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtThumbProduct;
    private javax.swing.JTextField txtTitleProduct;
    // End of variables declaration//GEN-END:variables
}
