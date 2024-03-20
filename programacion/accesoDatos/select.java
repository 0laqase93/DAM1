import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class select{
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/sanitat", "root", "123");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DOCTOR");
            while (rs.next()) {
                String nombre = rs.getString("COGNOM");
                String especialidad = rs.getString("ESPECIALITAT");
                System.out.println(nombre + " ; " + especialidad);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}