package asm;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author ACER
 */
public class StudentManagement extends javax.swing.JFrame {

    private final ArrayList<Students> studentList = new ArrayList<>();
    private final DefaultTableModel tableModel;
    private int currentIndex = 0;
    private String imagePath;

    /**
     * Creates new form test
     */
    public StudentManagement() {
        initComponents();
        tableModel = (DefaultTableModel) tableStudents.getModel();
        setLocationRelativeTo(null);

        loadDataArrayList();
        displayStudentList();
        displayCurrentStudent(currentIndex > 0 ? currentIndex - 1 : 0);

        tableStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStudentsMouseClicked(evt);
            }
        });

    }

    private void tableStudentsMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tableStudents.getSelectedRow();

        if (row >= 0 && row < studentList.size()) {
            currentIndex = row;
            displayCurrentStudent(currentIndex);
        }
    }

    private void displayCurrentStudent(int index) {
        Students student = studentList.get(index);

        // Form
        textStudentID.setText(student.getStudentID());
        textUsername.setText(student.getUsername());
        textEmail.setText(student.getEmail());
        textPhoneNumber.setText(student.getPhoneNumber());
        textAddress.setText(student.getAddress());

        radioMale.setSelected(student.isSex());
        radioFemale.setSelected(!student.isSex());

        // Avatar 
        btnAvatar.setBorder(null); // Loại bỏ border
        btnAvatar.setPreferredSize(new Dimension(94, 94));

        String avatarPath = student.getAvatarPath();
        ImageIcon icon = new ImageIcon(avatarPath);
        Image image = icon.getImage().getScaledInstance(94, 94, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        btnAvatar.setIcon(scaledIcon);

        // Btn
        btnFirst.setEnabled(index != 0);
        btnPre.setEnabled(index != 0);
        btnNext.setEnabled(index != studentList.size() - 1);
        btnLast.setEnabled(index != studentList.size() - 1);
    }

    private void displayStudentList() {
        for (Students student : studentList) {
            Object[] rowData = {
                student.getStudentID(),
                student.getUsername(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.isSex() ? "Nam" : "Nữ",
                student.getAddress(),
                student.getAvatarPath()
            };

            tableModel.addRow(rowData);
        }
    }

    private void loadDataArrayList() {
        try (Connection conn = DBConnection.getConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery("SELECT * FROM STUDENTS")) {

            studentList.clear();

            while (rs.next()) {
                String studentID = rs.getString(1);
                String username = rs.getString(2);
                String email = rs.getString(3);
                String phoneNumber = rs.getString(4);
                String sexStr = rs.getString(5);
                String address = rs.getString(6);
                String avatar = rs.getString(7);

                Students student = new Students(studentID, username, email, phoneNumber, address, sexStr, avatar);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isStudentIdExists(String maSV) {
        String sql = "SELECT COUNT(*) FROM STUDENTS WHERE StudentID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, maSV);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void clearInputFields() {
        textStudentID.setText("");
        textUsername.setText("");
        textEmail.setText("");
        textPhoneNumber.setText("");
        textAddress.setText("");
    }

    private void resetImagePath() {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(btnAvatar.getWidth(), btnAvatar.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        btnAvatar.setIcon(scaledIcon);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jDialog1 = new javax.swing.JDialog();
        lblmasv = new javax.swing.JLabel();
        lblhoten = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        lblsodt = new javax.swing.JLabel();
        lblsex = new javax.swing.JLabel();
        lbldiachi = new javax.swing.JLabel();
        textStudentID = new javax.swing.JTextField();
        textUsername = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        textPhoneNumber = new javax.swing.JTextField();
        radioMale = new javax.swing.JRadioButton();
        radioFemale = new javax.swing.JRadioButton();
        textAddress = new javax.swing.JTextField();
        btnPre = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblttitile = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStudents = new javax.swing.JTable();
        btnFind = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnAvatar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ps31583");

        lblmasv.setText("Mã SV:");

        lblhoten.setText("Họ tên:");

        lblemail.setText("Email :");

        lblsodt.setText("Số ĐT:");

        lblsex.setText("Giới tính:");

        lbldiachi.setText("Địa chỉ :");

        textStudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textStudentIDActionPerformed(evt);
            }
        });

        radioMale.setText("Nam");
        radioMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMaleActionPerformed(evt);
            }
        });

        radioFemale.setText("Nữ");
        radioFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFemaleActionPerformed(evt);
            }
        });

        textAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAddressActionPerformed(evt);
            }
        });

        btnPre.setText("<<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblttitile.setFont(new java.awt.Font("Source Sans Pro SemiBold", 1, 30)); // NOI18N
        lblttitile.setForeground(new java.awt.Color(0, 51, 255));
        lblttitile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblttitile.setText("Quản Lí Sinh Viên");

        tableStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MãSV", "Họ tên", "Email", "Số ĐT", "Giới tính", "Địa chỉ", "Hình"
            }
        ));
        jScrollPane1.setViewportView(tableStudents);
        if (tableStudents.getColumnModel().getColumnCount() > 0) {
            tableStudents.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableStudents.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableStudents.getColumnModel().getColumn(4).setPreferredWidth(45);
            tableStudents.getColumnModel().getColumn(6).setPreferredWidth(40);
        }

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvatarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblmasv)
                                    .addComponent(lblhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblsodt)
                                    .addComponent(lblemail))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textStudentID)
                                    .addComponent(lblttitile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbldiachi)
                                    .addGap(18, 18, 18)
                                    .addComponent(textAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblsex)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioMale, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(radioFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFind, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblttitile)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblmasv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblsodt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioMale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblsex, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbldiachi))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        currentIndex--;

        if (currentIndex < 0) {
            return;
        }

        displayCurrentStudent(currentIndex > 0 ? currentIndex - 1 : 0);
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        currentIndex = 0;
        displayCurrentStudent(currentIndex > 0 ? currentIndex - 1 : 0);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentIndex = studentList.size() - 1;
        displayCurrentStudent(currentIndex);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        currentIndex++;
        if (currentIndex >= studentList.size()) {
            return;
        }

        displayCurrentStudent(currentIndex);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        imagePath = "C:\\Users\\admin\\Pictures\\avatar-demo.jpg";
        resetImagePath();
        clearInputFields();
        textStudentID.requestFocus();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String studentID = textStudentID.getText().toLowerCase();

        if (studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã sinh viên.");
            textStudentID.requestFocus();
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE STUDENTS SET Username=?, Email=?, PhoneNumber=?, AddressStudent=?, Sex=?, Avatar=? WHERE StudentID=?";

            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, textUsername.getText());
                st.setString(2, textEmail.getText());
                st.setString(3, textPhoneNumber.getText());
                st.setString(4, textAddress.getText());
                String gioiTinh = radioMale.isSelected() ? "Nam" : "Nu";
                st.setString(5, gioiTinh);
                st.setString(6, imagePath);
                st.setString(7, studentID);

                int rowsUpdated = st.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Update successfully.");
                    loadDataArrayList();
                    tableModel.setRowCount(0);
                    displayStudentList();
                    displayCurrentStudent(currentIndex > 0 ? currentIndex - 1 : 0);
                } else {
                    JOptionPane.showMessageDialog(this, "Error!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (textStudentID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã sinh viên.");
            textStudentID.requestFocus();
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sinh viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getConnection()) {
                String deleteGradeSQL = "DELETE FROM GRADE WHERE StudentID=?";
                String deleteStudentSQL = "DELETE FROM STUDENTS WHERE StudentID=?";

                try (PreparedStatement st = conn.prepareStatement(deleteGradeSQL)) {
                    conn.setAutoCommit(false);
                    st.setString(1, textStudentID.getText());
                    st.executeUpdate();
                }

                try (PreparedStatement st = conn.prepareStatement(deleteStudentSQL)) {
                    st.setString(1, textStudentID.getText());
                    st.executeUpdate();

                    conn.commit();
                    JOptionPane.showMessageDialog(this, "Xóa sinh viên thành công");
                    conn.setAutoCommit(true);

                    loadDataArrayList();
                    tableModel.setRowCount(0);
                    displayStudentList();
                    displayCurrentStudent(currentIndex > 0 ? currentIndex - 1 : 0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String studentID = textStudentID.getText();
        if (isStudentIdExists(studentID)) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại!");
            return;
        }

        if (studentID.isEmpty() || textUsername.getText().isEmpty() || textEmail.getText().isEmpty() || textPhoneNumber.getText().isEmpty() || textAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        String sexStr = radioMale.isSelected() ? "Nam" : "Nữ";

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO STUDENTS (StudentID, Username, Email, PhoneNumber, Sex, AddressStudent, Avatar) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, studentID);
                st.setString(2, textUsername.getText());
                st.setString(3, textEmail.getText());
                st.setString(4, textPhoneNumber.getText());
                st.setString(5, sexStr);
                st.setString(6, textAddress.getText());
                st.setString(7, imagePath);

                st.executeUpdate();
                JOptionPane.showMessageDialog(this, "Saved successfully!");
                loadDataArrayList();
                tableModel.setRowCount(0);
                displayStudentList();
                displayCurrentStudent(currentIndex + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void textStudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textStudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textStudentIDActionPerformed

    private void radioMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMaleActionPerformed
        Students student = studentList.get(currentIndex);
        student.setGioiTinh(true);

        radioMale.setSelected(student.isSex());
        radioFemale.setSelected(!student.isSex());
    }//GEN-LAST:event_radioMaleActionPerformed

    private void textAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAddressActionPerformed

    private void radioFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFemaleActionPerformed
        Students student = studentList.get(currentIndex);
        student.setGioiTinh(false);

        radioMale.setSelected(student.isSex());
        radioFemale.setSelected(!student.isSex());
    }//GEN-LAST:event_radioFemaleActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        try {
            String maSV = JOptionPane.showInputDialog(this, "Nhập mã sinh viên: ");

            if (maSV == null) {
                return;
            }

            String sql = "SELECT * FROM STUDENTS WHERE StudentID=?";
            try (Connection conn = DBConnection.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, maSV);

                try (ResultSet rs = st.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên có mã: " + maSV);
                        return;
                    }

                    textStudentID.setText(rs.getString("StudentID"));
                    textUsername.setText(rs.getString("Username"));
                    textEmail.setText(rs.getString("Email"));
                    textPhoneNumber.setText(rs.getString("PhoneNumber"));
                    textAddress.setText(rs.getString("AddressStudent"));

                    String SexStr = rs.getString("Sex");
                    radioMale.setSelected("Nam".equals(SexStr));
                    radioFemale.setSelected(!"Nam".equals(SexStr));

                    JOptionPane.showMessageDialog(this, "Đã tìm thấy sinh viên!");
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvatarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();// Chọn
            imagePath = selectedFile.getAbsolutePath(); // Lưu đường dẫn ảnh đã chọn
            resetImagePath();
        }
    }//GEN-LAST:event_btnAvatarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentManagement.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManagement.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManagement.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManagement.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new StudentManagement().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAvatar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldiachi;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblhoten;
    private javax.swing.JLabel lblmasv;
    private javax.swing.JLabel lblsex;
    private javax.swing.JLabel lblsodt;
    private javax.swing.JLabel lblttitile;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JTable tableStudents;
    private javax.swing.JTextField textAddress;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textPhoneNumber;
    private javax.swing.JTextField textStudentID;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
