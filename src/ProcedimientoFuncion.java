
//LLamadas a procediminetos y funciones en SQL

//CallableStatement --> llamadas a procedimientos y funciones
//prepareCall(String) --> prepara la llamada a un procedimiento o funcion

//{ call nombre_procedimiento(parametros)} --> no tiene porque llevar parametros
//{ call nombre_funcion(?,?,?...)}
//{ ?=call nombre_funcion } --> si no lleva parametros
//{ ?= call nombre_funcion(?,?,...) } --> si lleva parametros


//setInt(int, int) --> registra un parametro de entrada
//registerOutParameter(int, int) --> registra un parametro de salida
//execute() --> ejecuta la llamada
//getInt(int) --> obtiene el valor de un parametro de salida


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
DROP PROCEDURE IF EXISTS proc;

DELIMITER //
CREATE PROCEDURE proc ()

BEGIN
	UPDATE dptos
	SET bloque = "X"
	WHERE bloque = "C";
END //

DELIMITER ;

-------------------------------------

DROP PROCEDURE IF EXISTS proc2;

DELIMITER //
CREATE PROCEDURE proc2 (IN p_bloque CHAR)

BEGIN
	UPDATE dptos
	SET bloque = "Y"
	WHERE bloque = p_bloque;
END //

DELIMITER ;
*/


public class ProcedimientoFuncion {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");


        //Conectamos con la base de datos y nuestro usuario
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");

        String sql = "{ call proc }";
        String sql2 = "{ call proc2 (?)}";

       // CallableStatement cs = conex.prepareCall(sql);
        CallableStatement cs2 = conex.prepareCall(sql2);

        cs2.setString(1, "X");

        //cs.executeUpdate();
        cs2.executeUpdate();

        System.out.println("Procedimientos ejecutados");
    }
}
