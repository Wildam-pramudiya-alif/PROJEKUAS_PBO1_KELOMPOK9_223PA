package widam.gor_booking;

import java.awt.Color;
import javax.swing.*;

public class LoginFrameUser extends BaseLoginFrame {
    private JTextField KolomNama, KolomNo_HP, KolomAlamat;
    private JLabel Pesan;
    private JPanel panelLoginUser;

    public LoginFrameUser() {
        super("Login User - Aplikasi GOR");
        getContentPane().setBackground(new Color(28, 107, 160)); // Warna latar belakang
        initComponents();
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        // Panel Login User
        panelLoginUser = new JPanel();
        panelLoginUser.setBounds(0, 0, 400, 300);
        panelLoginUser.setLayout(null);
        panelLoginUser.setBackground(new Color(255, 140, 0));

        JLabel userLabel = new JLabel("Login User");
        userLabel.setBounds(150, 20, 100, 25);
        panelLoginUser.add(userLabel);

        JLabel namaLabel = new JLabel("Nama:");
        namaLabel.setBounds(50, 50, 80, 25);
        panelLoginUser.add(namaLabel);

        KolomNama = new JTextField();
        KolomNama.setBounds(150, 50, 200, 25);
        panelLoginUser.add(KolomNama);

        JLabel nomorHpLabel = new JLabel("Nomor HP:");
        nomorHpLabel.setBounds(50, 100, 80, 25);
        panelLoginUser.add(nomorHpLabel);

        KolomNo_HP = new JTextField();
        KolomNo_HP.setBounds(150, 100, 200, 25);
        panelLoginUser.add(KolomNo_HP);

        JLabel alamatLabel = new JLabel("Alamat:");
        alamatLabel.setBounds(50, 150, 80, 25);
        panelLoginUser.add(alamatLabel);

        KolomAlamat = new JTextField();
        KolomAlamat.setBounds(150, 150, 200, 25);
        panelLoginUser.add(KolomAlamat);

        JButton loginUserBtn = new JButton("Login");
        loginUserBtn.setBounds(150, 200, 100, 25);
        panelLoginUser.add(loginUserBtn);

        Pesan = new JLabel("");
        Pesan.setBounds(50, 250, 300, 25);
        panelLoginUser.add(Pesan);

        add(panelLoginUser);

        loginUserBtn.addActionListener(e -> handleLoginUser());
    }

    private void handleLoginUser() {
        String Nama = KolomNama.getText();
        String Alamat = KolomAlamat.getText();
        String No_HP = KolomNo_HP.getText();

        if (Nama.isEmpty() || Alamat.isEmpty() || No_HP.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua kolom!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Menutup frame login dan membuka BookingFrame
        dispose();
        new BookingFrame(Nama, Alamat, No_HP);
    }
}
                