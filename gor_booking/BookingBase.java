package widam.gor_booking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingBase extends JFrame {
    // Atribut umum untuk booking
    protected String NamaUser, AlamatUser, No_HPUser;
    
    // Kon  struktor untuk inisialisasi data user
    public BookingBase(String nama, String alamat, String noHp) {
        this.NamaUser = nama;
        this.AlamatUser = alamat;
        this.No_HPUser = noHp;
    }
    
    // Metode perhitungan jam selesai booking
    protected String hitungJam_selesai(String jam_mulai, int durasi) {
        String[] waktu = jam_mulai.split(":");
        int jam = Integer.parseInt(waktu[0]); // Mengambil jam
        int menit = Integer.parseInt(waktu[1]); // Mengambil menit
        
        jam += durasi;
        
        // Mengecek apakah jam melebihi batas jam operasional (22:00)
        if (jam > 22) {
            return "Melebihi jam operasional!";
        }
        
        return String.format("%02d:%02d", jam, menit);
    }
    
    // Metode untuk menampilkan jadwal booking yang tersimpan di database
    protected void tampilkanJadwal() {
        StringBuilder jadwalText = new StringBuilder("Jadwal Booking:\n");
        String sql = "SELECT Nama, tanggal, jam_mulai, jam_selesai, durasi FROM pembooking "
                   + "WHERE status = 'booking' ORDER BY tanggal ASC, jam_mulai ASC";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String tanggal = rs.getString("tanggal");
                String jamMulai = rs.getString("jam_mulai");
                String jamSelesai = rs.getString("jam_selesai");
                String durasi = rs.getString("durasi");
                jadwalText.append(String.format("%s | %s - %s | (%s)\n", tanggal, jamMulai, jamSelesai, durasi));
            }
        } catch (SQLException e) {
            jadwalText.append("Gagal mengambil data!");
        }

        JOptionPane.showMessageDialog(this, jadwalText.toString(), "Jadwal Booking", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Metode statis untuk menyimpan data booking ke database
    protected static void simpanData(String Nama, String Alamat, String No_HP, String tanggal, String jam_mulai,
                                     String jam_selesai, String durasi, int totalBiaya, String metodePembayaran) {
        String sql = "INSERT INTO pembooking (Nama, Alamat, No_HP, tanggal, jam_mulai, jam_selesai, durasi, totalBiaya, metodePembayaran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, Nama);
            stmt.setString(2, Alamat);
            stmt.setString(3, No_HP);
            stmt.setString(4, tanggal);
            stmt.setString(5, jam_mulai);
            stmt.setString(6, jam_selesai);
            stmt.setString(7, durasi);
            stmt.setInt(8, totalBiaya);
            stmt.setString(9, metodePembayaran);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
