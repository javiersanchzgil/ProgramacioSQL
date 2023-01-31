package EjerAulaAccesoConsultas;

/*
* DROP PROCEDURE IF EXISTS procMasEmpleados;

DELIMITER //
CREATE PROCEDURE procMasEmpleados()

BEGIN
SELECT nom, bloque, pto, count(idD) as empleados_count
FROM dptos
JOIN empleados ON dptos.idD = empleados.idD
GROUP BY DPTOS.idD
ORDER BY empleados_count DESC;
END;
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class procMasEmpleados {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Aqui cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Conectamos con la base de datos y nuestro usuario
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");

        String sql = "{ call procMasEmpleados (?,?,?,?)}";

        CallableStatement cs = conex.prepareCall(sql);

        cs.execute();

        System.out.println("Procedimiento ejecutado");





    }
}
