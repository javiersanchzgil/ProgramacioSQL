

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConectorConsultaSQL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub


        Scanner sc = new Scanner (System.in);

        //Aqui cargamos el driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Class.forName("com.mysql.jdbc.Driver");

        //Conectamos con la base de datos y nuestro usuario
        Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?serverTimeZone=UTC","root","afuera");

        //Creamos una sentencia
        Statement sentencia = conex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        //Sentencia que te hace como una collecion que tu luego vas recorriendo
        //Statement sentencia2 = conex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //conex.createStat
        //Realizamos la consulta
        String sql = "SELECT * FROM dptos";

        //Metemos en rs la consulta realizada en la base de datos
        ResultSet rs = sentencia.executeQuery(sql);

        //Para añadir otro campo a la tabla
        rs.moveToInsertRow();
        rs.updateInt(1,56);
        rs.updateString(2,"Dpto56");
        rs.updateString(3,"B");
        rs.updateFloat(4,125.67F);
        rs.insertRow();

        //Borrar ultima fila
        rs.last();
        rs.deleteRow();




        while(rs.previous()) {
            System.out.println(rs.getInt(1)+"  " + rs.getString(2)+"  "+ rs.getString(3)+ "  "+ rs.getFloat(4));

        }


        sql = "UPDATE dptos SET bloque = 'C' WHERE bloque = 'X'";
        //otro tipo de formato para realizar la consulta
        //sql = String.format("UPDATE dptos SET bloque=%c WHERE bloque=%c",'E','X');


        boolean consulta = sentencia.execute(sql);
        //int numFilas = sentencia.executeUpdate(sql);

        //Si es TRUE la consulta te imprimira los datos y si es false te realizara el UPDATE
        //esto lo haremos cuando no sepamos que tipo de sentencia es

        if(consulta) {
            ResultSet rs1 = sentencia.getResultSet();
            while(rs1.next()) {
                System.out.println(rs1.getInt(1)+"  " + rs1.getString(2)+"  "+ rs1.getString(3)+ "  "+ rs1.getFloat(4));

            }
        }
        else {
            int numFilas = sentencia.getUpdateCount();
            System.out.println(numFilas);
        }


        //.execute(secuenciaSql) devuelve un booleano si es true devuelve una consulta y false si es actualizacion
        //si es consulta tenemos el metodo .getResultSet() y si es actualizacion .getUpdateCont()

        sentencia.close();
        conex.close();

        //NUMfILAS DEVUELVE FALSE PORQUE ES UN UPDATE
        System.out.println(consulta);


    }

}

 /*
        //Metemos en rs la consulta realizada en la base de datos
        ResultSet rs2 = sentencia2.executeQuery(sql);
        //Nos posicionamos en el ultimo registro
        rs2.last();
        //Nos posicionamos en el primer registro
        rs2.first();
        //Nos posicionamos en el registro 3
        rs2.absolute(3);
        //Nos posicionamos en el registro 3 contando desde el final
        rs2.relative(-3);
        //Nos posicionamos en el registro 3 contando desde el final
        rs2.relative(3);
        //Nos posicionamos en el registro 3 contando desde el final
        rs2.relative(3);
         */

//para posicionarnos en el princpio utilizamos, pero se tiene que utilizar
//Statement sentencia2 = conex.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//rs.first();	posicionamineto en el primero
//rs.beforeFirst(); posicionamiento antes del primero
//rs.last(); posicionamiento en el ultimo
//rs.getRow(); devuelve el numero de filas donde estas
//rs.previous();
//rs.isAfterLast(); devuelve true si esta despues del ultimo
