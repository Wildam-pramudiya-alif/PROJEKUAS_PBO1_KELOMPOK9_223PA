package widam.gor_booking;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class BookingFrame extends BookingBase {
    private JComboBox<String> comboDurasi;
    private JComboBox<Integer> comboHarga;
    private JComboBox<String> comboMetodePembayaran;
    private JComboBox<String> comboJamMulai;
    private JDateChooser dateChooser;
    private JButton buttonBook, buttonLihatJadwal;
    private JLabel labelInfoBooking;

    public BookingFrame(String nama, String alamat, String noHp) {
        // Panggil konstruktor kelas dasar untuk inisialisasi data user
        super(nama, alamat, noHp);

        setTitle("Pemilihan Booking GOR");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 140, 0)); // Warna latar belakang

        labelInfoBooking = new JLabel("Pilih Opsi Booking");
        labelInfoBooking.setBounds(150, 20, 250, 25);
        add(labelInfoBooking);

        JLabel labelNamaUser = new JLabel("Nama: " + NamaUser);
        labelNamaUser.setBounds(50, 40, 300, 25);
        add(labelNamaUser);

        JLabel labelAlamatUser = new JLabel("Alamat: " + AlamatUser);
        labelAlamatUser.setBounds(50, 60, 300, 25);
        add(labelAlamatUser);

        JLabel labelNoHpUser = new JLabel("Nomor HP: " + No_HPUser);
        labelNoHpUser.setBounds(50, 80, 300, 25);
        add(labelNoHpUser);

        JLabel labelTanggal = new JLabel("Tanggal:");
        labelTanggal.setBounds(50, 120, 80, 25);
        add(labelTanggal);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(150, 120, 200, 25);
        add(dateChooser);

        JLabel labelJamMulai = new JLabel("Jam Mulai:");
        labelJamMulai.setBounds(50, 160, 80, 25);
        add(labelJamMulai);

        String[] jam_mulaiOptions = {
            "06:00", "07:00", "08:00", "09:00", "10:00",
            "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00", "20:00",
            "21:00", "22:00"
        };
        comboJamMulai = new JComboBox<>(jam_mulaiOptions);
        comboJamMulai.setBounds(150, 160, 200, 25);
        add(comboJamMulai);

        JLabel labelDurasi = new JLabel("Durasi (Jam):");
        labelDurasi.setBounds(50, 200, 100, 25);
        add(labelDurasi);

        comboDurasi = new JComboBox<>(new String[]{"1 Jam", "2 Jam", "3 Jam", "4 Jam"});
        comboDurasi.setBounds(150, 200, 200, 25);
        add(comboDurasi);

        JLabel labelHarga = new JLabel("Harga (PerJam):");
        labelHarga.setBounds(50, 240, 120, 25);
        add(labelHarga);

        comboHarga = new JComboBox<>(new Integer[]{30000});
        comboHarga.setBounds(150, 240, 200, 25);
        add(comboHarga);

        JLabel labelMetodePembayaran = new JLabel("Pembayaran:");
        labelMetodePembayaran.setBounds(50, 280, 140, 25);
        add(labelMetodePembayaran);

        comboMetodePembayaran = new JComboBox<>(new String[]{
            "Transfer Bank (901030610373)",
            "OVO (082326993127)",
            "GoPay(082326993127)",
            "Cash"
        });
        comboMetodePembayaran.setBounds(150, 280, 200, 25);
        add(comboMetodePembayaran);

        buttonBook = new JButton("Booking");
        buttonBook.setBounds(150, 340, 100, 30);
        add(buttonBook);
        buttonBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBooking();
            }
        });
        
        buttonLihatJadwal = new JButton("Lihat Jadwal");
        buttonLihatJadwal.setBounds(260, 340, 110, 30);
        add(buttonLihatJadwal);
        buttonLihatJadwal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanJadwal();
            }
        });

        setVisible(true);
    }

    // Metode untuk menangani proses booking ketika tombol "Booking" diklik
    private void handleBooking() {
        String tanggal = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String durasiStr = (String) comboDurasi.getSelectedItem();
        String jam_mulai = (String) comboJamMulai.getSelectedItem();
        int durasi = Integer.parseInt(durasiStr.split(" ")[0]);

        String jam_selesai = hitungJam_selesai(jam_mulai, durasi);
        if (jam_selesai.equals("Melebihi jam operasional!")) {
            JOptionPane.showMessageDialog(this, "Jam selesai melebihi batas operasional!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int totalBiaya = durasi * 30000;

        // Membuat JTextPane untuk menampilkan pesan booking
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet normal = new SimpleAttributeSet();
        SimpleAttributeSet blue = new SimpleAttributeSet();
        StyleConstants.setForeground(blue, Color.BLUE);
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);

        try {
            doc.insertString(doc.getLength(),
                    String.format("Booking berhasil!\nNama: %s\nAlamat: %s\nNomor HP: %s\nTanggal: %s\nDurasi: %s\nHarga: Rp%,d\nMetode Pembayaran: %s",
                            NamaUser, AlamatUser, No_HPUser, tanggal, durasiStr, totalBiaya, (String) comboMetodePembayaran.getSelectedItem()),
                    normal);
            doc.insertString(doc.getLength(), "\nSILAHKAN KIRIM BUKTI PEMBAYARAN KE WA 082326993127", blue);
            doc.insertString(doc.getLength(), "\nSIMPAN BUKTI PEMBAYARAN ATAU BUKTI BOOKING!!!", red);
        } catch (BadLocationException e) {
        }

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(textPane));

        JOptionPane.showMessageDialog(this, panel, "Konfirmasi Booking", JOptionPane.INFORMATION_MESSAGE);

        // Memanggil metode simpanData() dari kelas dasar untuk menyimpan data booking ke database
        simpanData(NamaUser, AlamatUser, No_HPUser, tanggal, jam_mulai, jam_selesai, durasiStr, totalBiaya,
                (String) comboMetodePembayaran.getSelectedItem());

        JOptionPane.showMessageDialog(this, "Data Disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);

        // Setelah proses booking, berpindah ke login frame
        setVisible(false);
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
