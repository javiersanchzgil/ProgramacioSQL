import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
DROP PROCEDURE IF EXISTS proc3;

        DELIMITER //
        CREATE PROCEDURE proc3 (IN p_bloque CHAR, IN p_bloque2 CHAR)

        BEGIN
        UPDATE dptos
        SET bloque = "X"
        WHERE bloque = p_bloque or bloque = p_bloque2;
        END //

        DELIMITER ;
        */

public class ProcedimientoSQLdosParametros {
    public static void main(String[] args) throws SQLException {
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");


        String sql3 = "{ call proc3 (?,?)}";

        CallableStatement cs3 = conex.prepareCall(sql3);

        cs3.setString(1, "X");

        cs3.executeUpdate();

        System.out.println("Procedimientos ejecutados");


    }
}
