
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Veritabani_Konfigrasyon {
    public static final String db_kullaniciadi="root";
    public static final String db_sifre="";
    public static final String db_ad="BankaSistemi";
    public static final String db_host="localhost";
    public static final int db_port=3306;
    public Connection con=null;
    public Statement sqlquery=null;
    public PreparedStatement psqlquery=null;

    public Veritabani_Konfigrasyon() {
        
        String url="jdbc:mysql://"+Veritabani_Konfigrasyon.db_host+":"+Veritabani_Konfigrasyon.db_port+"/"+Veritabani_Konfigrasyon.db_ad+"?useUnicode=true&characterEncoding=utf8";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.out.print("Sql driver is not found.");

        }
        try {
            con = DriverManager.getConnection(url, Veritabani_Konfigrasyon.db_kullaniciadi, Veritabani_Konfigrasyon.db_sifre);
            System.out.println("Connection is succesfuly.");
        } catch (SQLException e) {
            System.out.println("Connection is not succesfuly.");
        }
         
    }
    
    
    
    
    
}
