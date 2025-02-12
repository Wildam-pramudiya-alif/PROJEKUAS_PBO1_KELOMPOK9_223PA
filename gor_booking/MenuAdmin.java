package widam.gor_booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MenuAdmin extends JFrame {
    private JPanel mainPanel, panelMenu, panelKelolaJadwal, panelLaporanTransaksi, panelRiwayatBooking;
    private CardLayout cardLayout;
    private DefaultTableModel modelKelola, modelTransaksi, modelRiwayat;
    private JTable tableKelola, tableTransaksi, tableRiwayat;

    public MenuAdmin() {
        setTitle("Admin Dashboard - Booking GOR");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel, BorderLayout.CENTER);

        buildPanelMenu();
        buildPanelKelolaJadwal();
        buildPanelLaporanTransaksi();
        buildPanelRiwayatBooking();

        mainPanel.add(panelMenu, "MenuUtama");
        mainPanel.add(panelKelolaJadwal, "KelolaJadwal");
        mainPanel.add(panelLaporanTransaksi, "LaporanTransaksi");
        mainPanel.add(panelRiwayatBooking, "RiwayatBooking");
        setVisible(true);
    }

    private void buildPanelMenu() {
        panelMenu = new JPanel(null); // Null layout agar bisa setBounds()
        panelMenu.setBackground(new Color(28, 107, 160)); // Light Blue

        JLabel labelTitle = new JLabel("Admin Dashboard");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setBounds(300, 20, 200, 30);
        panelMenu.add(labelTitle);

        JButton btnKelolaJadwal = new JButton("Kelola Jadwal");
        btnKelolaJadwal.setBounds(300, 80, 200, 40);
        btnKelolaJadwal.addActionListener(e -> {
            loadDataKelola();
            cardLayout.show(mainPanel, "KelolaJadwal");
        });
        panelMenu.add(btnKelolaJadwal);

        JButton btnLaporanTransaksi = new JButton("Laporan Transaksi");
        btnLaporanTransaksi.setBounds(300, 140, 200, 40);
        btnLaporanTransaksi.addActionListener(e -> {
            loadDataTransaksi();
            cardLayout.show(mainPanel, "LaporanTransaksi");
        });
        panelMenu.add(btnLaporanTransaksi);

        JButton btnRiwayatBooking = new JButton("Riwayat Booking");
        btnRiwayatBooking.setBounds(300, 200, 200, 40);
        btnRiwayatBooking.addActionListener(e -> {
            loadDataRiwayat();
            cardLayout.show(mainPanel, "RiwayatBooking");
        });
        panelMenu.add(btnRiwayatBooking);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(300, 260, 200, 40);
        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logout Berhasil!");
            this.dispose();
            new LoginFrame().setVisible(true);
        });
        panelMenu.add(btnLogout);
    }

    private void buildPanelKelolaJadwal() {
        panelKelolaJadwal = new JPanel(new BorderLayout());
        String[] kolomKelola = {"ID_Booking", "Nama", "Alamat", "No_HP", "Tanggal", "Durasi", "jam_mulai", "jam_selesai"};
        modelKelola = new DefaultTableModel(kolomKelola, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableKelola = new JTable(modelKelola);
        JScrollPane scrollKelola = new JScrollPane(tableKelola);
        panelKelolaJadwal.add(scrollKelola, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton btnSelesaikan = new JButton("Selesaikan Booking");
        JButton btnBack = new JButton("Kembali");

        btnSelesaikan.addActionListener(e -> completeBooking());
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "MenuUtama"));

        bottomPanel.add(btnSelesaikan);
        bottomPanel.add(btnBack);
        panelKelolaJadwal.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void buildPanelLaporanTransaksi() {
        panelLaporanTransaksi = new JPanel(new BorderLayout());
        String[] kolomTransaksi = {"ID_Booking", "Nama", "Alamat", "Tanggal", "Harga", "Metode Pembayaran"};
        modelTransaksi = new DefaultTableModel(kolomTransaksi, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTransaksi = new JTable(modelTransaksi);
        JScrollPane scrollTransaksi = new JScrollPane(tableTransaksi);
        panelLaporanTransaksi.add(scrollTransaksi, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton btnBack = new JButton("Kembali");
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "MenuUtama"));
        bottomPanel.add(btnBack);
        panelLaporanTransaksi.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void buildPanelRiwayatBooking() {
        panelRiwayatBooking = new JPanel(new BorderLayout());
        String[] kolomRiwayat = {"ID_Booking", "Nama", "Alamat", "No_HP", "Tanggal", "Durasi", "jam_mulai", "jam_selesai"};
        modelRiwayat = new DefaultTableModel(kolomRiwayat, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRiwayat = new JTable(modelRiwayat);
        JScrollPane scrollRiwayat = new JScrollPane(tableRiwayat);
        panelRiwayatBooking.add(scrollRiwayat, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton btnBack = new JButton("Kembali");
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "MenuUtama"));
        bottomPanel.add(btnBack);
        panelRiwayatBooking.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadDataKelola() {
        modelKelola.setRowCount(0);
        try {
            Connection conn = KoneksiDatabase.getConnection();
            String sql = "SELECT id_booking, Nama, Alamat, No_HP, tanggal, durasi, jam_mulai, jam_selesai FROM pembooking WHERE status = 'booking'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_booking"),
                        rs.getString("Nama"),
                        rs.getString("Alamat"),
                        rs.getString("No_HP"),
                        rs.getString("tanggal"),
                        rs.getString("durasi"),
                        rs.getString("jam_mulai"),
                        rs.getString("jam_selesai")
                };
                modelKelola.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data kelola: " + e.getMessage());
        }
    }

    private void loadDataTransaksi() {
        modelTransaksi.setRowCount(0);
        try {
            Connection conn = KoneksiDatabase.getConnection();
            String sql = "SELECT id_booking, Nama, Alamat, Tanggal, TotalBiaya, MetodePembayaran FROM pembooking";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_booking"),
                        rs.getString("Nama"),
                        rs.getString("Alamat"),
                        rs.getString("Tanggal"),
                        rs.getInt("TotalBiaya"),
                        rs.getString("MetodePembayaran")
                };
                modelTransaksi.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data transaksi: " + e.getMessage());
        }
    }

    private void loadDataRiwayat() {
        modelRiwayat.setRowCount(0);
        try {
            Connection conn = KoneksiDatabase.getConnection();
            String sql = "SELECT id_booking, Nama, Alamat, No_HP, tanggal, durasi, jam_mulai, jam_selesai FROM pembooking WHERE status = 'selesai'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id_booking"),
                        rs.getString("Nama"),
                        rs.getString("Alamat"),
                        rs.getString("No_HP"),
                        rs.getString("Tanggal"),
                        rs.getString("Durasi"),
                        rs.getString("jam_mulai"),
                        rs.getString("jam_selesai")
                };
                modelRiwayat.addRow(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data riwayat: " + e.getMessage());
        }
    }

    // Admin menyelesaikan booking
    private void completeBooking() {
        int selectedRow = tableKelola.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diselesaikan!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menyelesaikan booking ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = KoneksiDatabase.getConnection();

                // Ambil ID booking
                int id_booking = (int) modelKelola.getValueAt(selectedRow, 0);

                // Update status menjadi selesai di tabel pembooking
                String sqlUpdateStatus = "UPDATE pembooking SET status = 'selesai' WHERE id_booking = ?";
                PreparedStatement pstmtUpdateStatus = conn.prepareStatement(sqlUpdateStatus);
                pstmtUpdateStatus.setInt(1, id_booking);
                pstmtUpdateStatus.executeUpdate();
                pstmtUpdateStatus.close();
                // Refresh tampilan Kelola Jadwal, Laporan Transaksi, dan Riwayat Booking
                loadDataKelola(); // Mengupdate status di kelola jadwal
                loadDataTransaksi(); // Menampilkan data yang sudah selesai di laporan transaksi
                loadDataRiwayat(); // Menampilkan data transaksi yang sudah selesai di riwayat booking

                JOptionPane.showMessageDialog(this, "Booking berhasil diselesaikan!");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyelesaikan booking: " + e.getMessage());
            }
        }
    }
    }
