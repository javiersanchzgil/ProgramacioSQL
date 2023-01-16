import java.sql.*;

public class FuncionesSQL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");

        String sql = "{ ? = call func ()}"; // EL PRIMER PARENTESIS ES PARA EL PARAMETRO DE SALIDA Y SI QUIERES AÑADIR MAS PARAMETROS SE PONEN ENTRE LOS PARENTESIS

        CallableStatement cs = conex.prepareCall(sql);

        cs.registerOutParameter(1, Types.TINYINT); // AQUI INDICAMOS EL PARAMETRO DE SALIDA

        cs.executeUpdate();

        System.out.println("El siguiente departamento es: " + cs.getByte(1));

        System.out.println("Procedimientos ejecutados");
    }
}
