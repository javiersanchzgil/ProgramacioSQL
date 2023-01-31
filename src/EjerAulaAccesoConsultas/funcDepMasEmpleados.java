package EjerAulaAccesoConsultas;

import java.sql.*;

public class funcDepMasEmpleados {
    /*
    DELIMITER //

CREATE FUNCTION obtener_departamento_con_mas_empleados()
RETURNS smallint
BEGIN
  DECLARE departamento_con_mas_empleados smallint;
  SELECT idD INTO departamento_con_mas_empleados
  FROM empleados
  GROUP BY idD
  ORDER BY count(*) DESC, idD DESC
  LIMIT 1;
  RETURN departamento_con_mas_empleados;
END//
DELIMITER //
*/

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Aqui cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");


        //Conectamos con la base de datos y nuestro usuario
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");

        String sql = "{ ?=call obtener_departamento_con_mas_empleados ()}";

        CallableStatement cs = conex.prepareCall(sql);

        cs.registerOutParameter(1, Types.SMALLINT);

        cs.execute();

        System.out.println(cs.getShort(1));





    }
}
