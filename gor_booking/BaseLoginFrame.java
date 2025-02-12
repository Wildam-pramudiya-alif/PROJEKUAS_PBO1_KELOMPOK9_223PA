package widam.gor_booking;

import javax.swing.*;

public abstract class BaseLoginFrame extends JFrame {
    public BaseLoginFrame(String title) {
        super(title);
        // Pengaturan dasar untuk semua login frame
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
    }
    
    // Metode abstrak yang wajib diimplementasikan oleh subclass untuk menginisialisasi komponen UI
    protected abstract void initComponents();
}
