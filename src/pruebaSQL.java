import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.Locale;

public class pruebaSQL {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        File f = new File("dptosD.dat");
        RandomAccessFile raf = new RandomAccessFile(f, "r");

        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC", "root", "afuera");

        Statement st = conex.createStatement();

        String query = "";

        while (raf.getFilePointer() < raf.length()) {
            try {
                query = String.format(Locale.UK,"INSERT INTO dptos VALUES (%d, '%s', '%s', %f)", raf.readShort(), leerCadena(raf, (short) 15), raf.readChar(), raf.readFloat());
                st.executeUpdate(query);

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Departamento duplicado: " + query);
            }
             catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }


    }
    public static String leerCadena(RandomAccessFile f, short longitud) throws IOException {
        String cadena = "";
        for (int i = 0; i < longitud; i++) {
            cadena += f.readChar();
        }
        return cadena;
    }



}

