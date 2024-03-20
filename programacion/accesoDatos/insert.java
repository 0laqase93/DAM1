import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class insert {
    public static void main(String[] args) {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/33006/sanitat", "root", "123");
        Statement st = con.createStatement();

        int numfilas=st.executeQuery("INSERT INTO sanitat.DOTOR (HOSPITAL_COD, DOCTOR_NO, COGNOM, ESPECIALITAT) VALUES ()");
    }
}
