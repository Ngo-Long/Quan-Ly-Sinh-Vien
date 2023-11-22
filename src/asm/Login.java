package asm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Login {

    private static StudentManagement studentManagement;

    public static void main(String[] args) {

        // Form
        JFrame frame = new JFrame("Đăng nhập");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(null);

        // User name
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        frame.add(userText);

        // User password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        frame.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        frame.add(passwordText);

        // Button login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        frame.add(loginButton);

        // Button cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(190, 80, 80, 25);
        frame.add(cancelButton);

        // Check event click button login
        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            // Check empty
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập tên tài khoản!!");
                return;
            }

            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Vui lòng nhập mật khẩu!!");
                return;
            }

            // Kết nối database
            try {
                Connection conn = DBConnection.getConnection();
                String query = "SELECT * FROM USERS WHERE UsernameUser = ? AND PasswordUser = ?";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Failed!");
                    return;
                }

                String role = rs.getString("RoleUser");

                if ("giangvien".equals(role)) {
                    openGiangVien();
                    JOptionPane.showMessageDialog(frame, "Login successfully.");
                }

                if ("canbodaotao".equals(role)) {
                    openCanBoDaoTao();
                    JOptionPane.showMessageDialog(frame, "Login successfully.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error!");
                ex.printStackTrace();
            }
        });

        cancelButton.addActionListener(
                (ActionEvent e) -> {
                    userText.setText("");
                    passwordText.setText("");
                }
        );

        frame.setVisible(true);
    }

    private static void openGiangVien() {
    }

    private static void openCanBoDaoTao() {
        studentManagement = new StudentManagement();
        studentManagement.setVisible(true);
    }

}
