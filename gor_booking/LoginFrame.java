package widam.gor_booking;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends BaseLoginFrame {
    private JTextField KolomUsername;
    private JPasswordField KolomPassword;
    private JLabel Pesan;
    private JPanel panelAwal, panelLoginAdmin;

    public LoginFrame() {
        // Panggil konstruktor BaseLoginFrame untuk pengaturan dasar
        super("SISTEM BOOKING GOR");
        getContentPane().setBackground(new Color(28, 107, 160)); // Warna latar belakang
        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        // Panel Awal (tombol Login Admin dan Login User)
        panelAwal = new JPanel();
        panelAwal.setBounds(190, 100, 380, 200);
        panelAwal.setLayout(null);
        panelAwal.setBackground(new Color(255, 140, 0));
        
        JLabel welcomeLabel = new JLabel("Selamat Datang di Sistem Booking GOR");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBounds(0, 10, 2000, 30);
        panelAwal.add(welcomeLabel);
        
        JButton loginAdminButton = new JButton("Login Admin");
        loginAdminButton.setBounds(100, 60, 150, 50);
        panelAwal.add(loginAdminButton);
        
        JButton loginUserButton = new JButton("Login User");
        loginUserButton.setBounds(100, 130, 150, 50);
        panelAwal.add(loginUserButton);
        add(panelAwal);
        
        // Panel Login Admin
        panelLoginAdmin = new JPanel();
        panelLoginAdmin.setBounds(0, 0, 400, 300);
        panelLoginAdmin.setLayout(null);
        panelLoginAdmin.setBackground(new Color(255, 140, 0));
        panelLoginAdmin.setVisible(false);
        
        JLabel adminLabel = new JLabel("Login Admin");
        adminLabel.setBounds(150, 20, 100, 25);
        panelLoginAdmin.add(adminLabel);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 80, 25);
        panelLoginAdmin.add(usernameLabel);
        
        KolomUsername = new JTextField();
        KolomUsername.setBounds(150, 50, 200, 25);
        panelLoginAdmin.add(KolomUsername);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 25);
        panelLoginAdmin.add(passwordLabel);
        
        KolomPassword = new JPasswordField();
        KolomPassword.setBounds(150, 100, 200, 25);
        panelLoginAdmin.add(KolomPassword);
        
        JButton loginAdminBtn = new JButton("Login");
        loginAdminBtn.setBounds(150, 150, 100, 25);
        panelLoginAdmin.add(loginAdminBtn);
        
        Pesan = new JLabel("");
        Pesan.setBounds(50, 200, 300, 25);
        panelLoginAdmin.add(Pesan);
        
        add(panelLoginAdmin);
        
        // Action Listener untuk tombol-tombol
        loginAdminButton.addActionListener(e -> {
            panelAwal.setVisible(false);
            panelLoginAdmin.setVisible(true);
        });
        
        loginUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Contoh polymorphism: meskipun LoginFrameUser adalah subclass BaseLoginFrame,
                // kita bisa langsung instansiasi dan menggunakannya.
                new LoginFrameUser();
                dispose();
            }
        });
        
        loginAdminBtn.addActionListener(e -> handleLoginAdmin());
    }

    private void handleLoginAdmin() {
        String Username = KolomUsername.getText();
        String Password = new String(KolomPassword.getPassword());

        LoginAdmin loginAdmin = new LoginAdmin();
        if (loginAdmin.validasi(Username, Password)) {
            Pesan.setText("Login Berhasil!");
            Pesan.setForeground(Color.GREEN);
            JOptionPane.showMessageDialog(this, "Selamat datang, " + Username + "!");
            dispose();
            new MenuAdmin();
        } else {
            Pesan.setText("Login Gagal! Periksa username/password.");
            Pesan.setForeground(Color.RED);
        }
    }
}
