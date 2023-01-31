package EjerAulaAccesoConsultas;

import java.sql.*;

public class procediminetoAltaEmpleado {
    /*DROP PROCEDURE IF EXISTS procAltaEmpleado;

        DELIMITER //
        CREATE PROCEDURE procAltaEmpleado (IN apellido varchar(15), IN dir varchar(30), IN fecha_alta date, IN salario float(6,2),IN idD tinyint(4))

        BEGIN
	        INSERT INTO empleados (apellido, dir, fecha_alta, salario, idD) values (apellido, dir, fecha_alta, salario, id);
        END //

        DELIMITER ;*/

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Aqui cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");


        //Conectamos con la base de datos y nuestro usuario
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");

        String sql = "{ call procAltaEmpleado (?,?,?,?,?)}";

        CallableStatement cs = conex.prepareCall(sql);


        cs.setString(1, "Sanchez");
        cs.setString(2, "DIR01");
        cs.setDate(3, Date.valueOf("2021-01-01"));
        cs.setFloat(4, 1500.00F);
        cs.setShort(5, (short) 110);

        cs.executeUpdate();

        System.out.println("Procedimientos ejecutados");



    }
}
