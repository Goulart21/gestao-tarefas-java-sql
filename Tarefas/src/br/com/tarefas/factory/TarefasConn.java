package br.com.tarefas.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TarefasConn {

    private static String nome = "root";
    private static String senha = "5555";
    private static String url = "jdbc:mysql://localhost:3306/GerenciamentoDeTarefas";


    public static Connection conn() throws Exception{

        Connection con = DriverManager.getConnection(url,nome,senha);
        return con;

    }

    public static void main(String[] args) throws SQLException {
        Connection con = null;

        try{
            con = conn();
            if(con != null){
                System.out.println("Conexão feita");
            }
        }catch (Exception ex){

            System.out.println("Conexão não feita");
            System.out.println(ex.getMessage());
        }
        finally {
            con.close();
        }
    }
}