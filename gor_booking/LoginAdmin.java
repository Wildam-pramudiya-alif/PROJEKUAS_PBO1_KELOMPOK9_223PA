package widam.gor_booking;
                                                    
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginAdmin {
    public boolean validasi(String Username, String Password) {
        String isiUsers = "SELECT * FROM Users WHERE Username = ? AND Password = ?";

        try (Connection conn = KoneksiDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(isiUsers)) {

            stmt.setString(1, Username);
            stmt.setString(2, Password);

            ResultSet hasilcek = stmt.executeQuery();
            return hasilcek.next();
        } catch (Exception e) {
            return false;
        }
    }
}