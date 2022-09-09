package view;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Inventorylog;
import model.Product;
import service.ProductService;
import service.InventoryLogService;

public class NhapXuatJFrame extends javax.swing.JFrame {

    private InventoryLogService inSvc;
    private ProductService proSvc;
    public NhapXuatJFrame() {
        initComponents();
        this.inSvc = new InventoryLogService();
        this.proSvc = new ProductService();
        this.loadTable();
        this.clearForm();
    }
    
    private void clearForm(){
        this.txtMaPhieu.setText("");
        this.txtMaSP.setText("");
        this.txtNgayTao.setText("");
        this.txtSoLuong.setText("");
        this.rdoPhieuNhap.setSelected(true);
    }
    
    private void loadTable(){
        ArrayList<Inventorylog> lst = this.inSvc.getAll();
        DefaultTableModel dtm = (DefaultTableModel) this.tblNhapXuat.getModel();
        dtm.setRowCount(0);
        
        for (Inventorylog x : lst) {
            Inventorylog in = (Inventorylog) x;
            dtm.addRow(new Object[] {
                in.getForm_id(),
                in.getProduct_id(),
                in.getType_form() == 1 ? "Phiếu nhập" : "Phiếu xuất",
                in.getCreated_date(),
                in.getQuantity()
            });
        }
    }

    private Inventorylog getFormData(){
        String maPhieu = this.txtMaPhieu.getText().trim();
        String maSPStr = this.txtMaSP.getText().trim();
        int loaiPhieu = this.rdoPhieuNhap.isSelected() == true ? 1 : 0;
        String ngayTao = this.txtNgayTao.getText().trim();
        String soLuongStr = this.txtSoLuong.getText().trim();
        int soluong = -1;
        int maSP = -1;
        
        if (maPhieu.length() == 0 || maSPStr.length() == 0 || ngayTao.length() == 0 || soLuongStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Cái trường thông tin không được để trống");
            return null;
        }
        
        try {
            maSP = Integer.parseInt(maSPStr);
            if (maSP < 0) {
                JOptionPane.showMessageDialog(this, "Mã SP phải lớn hơn hoặc bằng 0");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã SP phải là số");
            e.printStackTrace();
            return null;
        } catch (HeadlessException e) {
            e.printStackTrace();
            return null;
        }
        
        Date date = null;
        Date dayNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!checkNgay(ngayTao)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày của ngày tạo phiếu");
            return null;
        }
        
        try {
            date = sdf.parse(ngayTao);
            if (date.getTime() > dayNow.getTime()) {
                JOptionPane.showMessageDialog(this, "Ngày tạo không tồn tại");
                return null;
            }
        } catch (ParseException ex) { 
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày của ngày tạo phiếu");
            ex.printStackTrace();
        }
        
        
        try {
            soluong = Integer.parseInt(soLuongStr);
            if (soluong < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 1");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            e.printStackTrace();
            return null;
        } catch (HeadlessException e) {
            e.printStackTrace();
            return null;
        }
        
        
        
        Inventorylog in = new Inventorylog(maSP, maPhieu, loaiPhieu, date, soluong);
        return in;
    }
    
    private boolean checkNgay(String txt) {
        Pattern p = Pattern.compile("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$");
        Matcher m = p.matcher(txt);
        return m.matches();
    }
    
    private boolean checkTrung(String txt){
        ArrayList<Product> lstFind = new ArrayList<>();
        Inventorylog in = this.inSvc.getById(txt);
        if (in == null) {
            return false;
        }
        this.loadTable();
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtMaPhieu = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        rdoPhieuNhap = new javax.swing.JRadioButton();
        rdoPhieuXuat = new javax.swing.JRadioButton();
        txtNgayTao = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapXuat = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã phiếu");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ngày tạo phiếu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Số lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã sản phẩm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Loại phiếu");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem))
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(btnSua)
                .addGap(73, 73, 73)
                .addComponent(btnXoa)
                .addGap(35, 35, 35))
        );

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setText("Exit");

        txtMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        buttonGroup1.add(rdoPhieuNhap);
        rdoPhieuNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoPhieuNhap.setText("Phiếu nhập");

        buttonGroup1.add(rdoPhieuXuat);
        rdoPhieuXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoPhieuXuat.setText("Phiếu xuất");

        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblNhapXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblNhapXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Mã sản phẩm", "Loại phiếu", "Ngày tạo phiếu", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhapXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhapXuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhapXuat);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Nhập xuất sản phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoPhieuNhap)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoPhieuXuat))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                            .addComponent(txtSoLuong))))))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnExit))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoPhieuNhap)
                            .addComponent(rdoPhieuXuat))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Inventorylog in = this.getFormData();
        if (in == null) {
            return;
        } else if (!checkTrung(in.getForm_id())) {
            JOptionPane.showMessageDialog(this, "Mã phiếu bị trùng", "Find fail", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        String maSPStr = this.txtMaSP.getText().trim();
        String soLuongStr = this.txtSoLuong.getText().trim();
        if (in.getType_form() == 1) {
            this.proSvc.addQuatity(Integer.parseInt(maSPStr), Integer.parseInt(soLuongStr));
        } else {
            this.proSvc.minusQuatity(Integer.parseInt(maSPStr), Integer.parseInt(soLuongStr));
        }
                
        this.inSvc.insert(in);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Thêm mới thành công");
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblNhapXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhapXuatMouseClicked
        // TODO add your handling code here:
        int row = this.tblNhapXuat.getSelectedRow();
        
        String maPhieu = this.tblNhapXuat.getValueAt(row, 0).toString();
        String maSp = this.tblNhapXuat.getValueAt(row, 1).toString();
        String loaiPhieu = this.tblNhapXuat.getValueAt(row, 2).toString();
        String ngayTao = this.tblNhapXuat.getValueAt(row, 3).toString();
        String soLuong = this.tblNhapXuat.getValueAt(row, 4).toString();
        
        this.txtMaPhieu.setText(maPhieu);
        this.txtMaSP.setText(maSp);
        this.txtNgayTao.setText(ngayTao);
        this.txtSoLuong.setText(soLuong);
        if (loaiPhieu.equals("Phiếu nhập")) {
            this.rdoPhieuNhap.setSelected(true);
        } else {
            this.rdoPhieuXuat.setSelected(true);
        }
        
    }//GEN-LAST:event_tblNhapXuatMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int row = this.tblNhapXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một dòng trên table");
            return;
        }
        Inventorylog in = this.getFormData();
        if (in == null) {
            return;
        }
        String soLuongCuStr = this.tblNhapXuat.getValueAt(row, 4).toString();
        int soLuongCu = Integer.parseInt(soLuongCuStr);
        String maPhieu = this.tblNhapXuat.getValueAt(row, 0).toString();
        this.inSvc.update(maPhieu, in);
        String maSPStr = this.tblNhapXuat.getValueAt(row, 1).toString();
        String soLuongStr = this.txtSoLuong.getText().trim();
        this.proSvc.updateQuatity(Integer.parseInt(maSPStr), Integer.parseInt(soLuongStr), soLuongCu);
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = this.tblNhapXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn một dòng trên table");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa bản ghi này không");
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        String maPhieu = this.tblNhapXuat.getValueAt(row, 0).toString();
        this.inSvc.delete(maPhieu);
        
        Inventorylog in = this.getFormData();
        String maSPStr = this.txtMaSP.getText().trim();
        String soLuongStr = this.txtSoLuong.getText().trim();
        if (in.getType_form() == 1) {
            this.proSvc.minusQuatity(Integer.parseInt(maSPStr), Integer.parseInt(soLuongStr));
        } else {
            this.proSvc.addQuatity(Integer.parseInt(maSPStr), Integer.parseInt(soLuongStr));
        }
        this.loadTable();
        this.clearForm();
        JOptionPane.showMessageDialog(this, "Xóa thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

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
            java.util.logging.Logger.getLogger(NhapXuatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapXuatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapXuatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapXuatJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhapXuatJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoPhieuNhap;
    private javax.swing.JRadioButton rdoPhieuXuat;
    private javax.swing.JTable tblNhapXuat;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
