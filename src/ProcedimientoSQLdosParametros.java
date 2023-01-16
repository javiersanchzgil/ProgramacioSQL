import java.sql.*;

/*
 DROP PROCEDURE IF EXISTS nextDep;

DELIMITER //

CREATE PROCEDURE nextDep (IN p_idD tinyint, OUT p_idDSiguiente tinyint)

BEGIN
	SELECT IdD INTO p_idDSiguiente FROM dptos where idD > p_idD order by idD ASC limit 1;

END //
DELIMITER ;

 */

public class ProcedimientoSQLdosParametros {
    public static void main(String[] args) throws SQLException {
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");


        String sql3 = "{ call nextDep (?,?)}"; // SE PONE UN PARENTESIS POR CADA PARAMETRO QUE SE LE PASA

        CallableStatement cs3 = conex.prepareCall(sql3);

        cs3.setByte(1,(byte) 100);  // SE PONE EL NUMERO DE PARAMETRO Y EL VALOR QUE SE LE QUIERE DAR

        cs3.registerOutParameter(2, Types.TINYINT); // AQUI INDICAMOS EL PARAMETRO DE SALIDA

        cs3.executeUpdate();

        System.out.println("El siguiente departamento es: " + cs3.getByte(2));

        System.out.println("Procedimientos ejecutados");

        //CON UN INOUT SOLO HACE FALTA PONER UNA INTERROGACION Y UN PARAMETRO TANTO DE ENTRADA COMO DE SALIDA


    }
}
