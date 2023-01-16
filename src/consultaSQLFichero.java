import java.io.*;
import java.sql.*;
import java.sql.Statement;

    public class consultaSQLFichero {

        public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC", "root", "afuera");

            File f = new File("C:\\Users\\AlumnoM\\Desktop\\Consultas.txt");
            Statement sentencia = conex.createStatement();

            BufferedReader bF = new BufferedReader(new FileReader(f));

            String consulta= bF.readLine();
            while (consulta!=null){
                if (sentencia.execute(consulta)){
                    ResultSet rs = sentencia.executeQuery(consulta);
                    while(rs.next())
                        System.out.println("ID DEP: " + rs.getInt(1) + " NOMBRE DEP: " + rs.getString(2) + " BLOQUE DEP: " + rs.getString(3) + " PTO: " + rs.getFloat(4));
                }else {
                    int rs = sentencia.getUpdateCount();
                    System.out.println(rs);
                }
                consulta= bF.readLine();
            }

        }
    }

