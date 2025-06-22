package br.com.tarefas.repository;

import br.com.tarefas.exception.TarefaNaoEncontrada;
import br.com.tarefas.factory.TarefasConn;
import br.com.tarefas.model.Tarefas;
import jdk.jshell.spi.SPIResolutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDAO {

    public void save(Tarefas tarefas) throws SQLException {

        String sql = "INSERT INTO tarefas (descricao,concluida) VALUES (?,?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = TarefasConn.conn();
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, tarefas.getDescricao());
            pstm.setBoolean(2, tarefas.isConcluida());
            pstm.executeUpdate();
        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = TarefasConn.conn();
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, String descricao, boolean concluida) throws TarefaNaoEncontrada{

        String sql = "UPDATE tarefas SET descricao = ? , concluida = ? WHERE id = ?";
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = TarefasConn.conn();
            pstm = (PreparedStatement) con.prepareStatement(sql);


            pstm.setString(1, descricao);
            pstm.setBoolean(2, concluida);
            pstm.setInt(3, id);
            pstm.execute();

            int percorridas = pstm.executeUpdate();
            if (percorridas == 0) {
                throw new TarefaNaoEncontrada("Tarefa não encontrada");

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void show() {

        String sql = "SELECT * FROM tarefas";

        Connection con = null;
        PreparedStatement pstm = null;


        try {
            con = TarefasConn.conn();
            pstm = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            boolean tarefa = false;

            while (rs.next()) {

                tarefa = true;
                int id = rs.getInt(1);
                String descricao = rs.getNString(2);
                boolean concluida = rs.getBoolean(3);
                String status = concluida ? "Concluida" : "Em andamento";
                System.out.println("ID: " + id + " Descrição: " + descricao + " Status: " + status);
            }
            if (!tarefa) {
                throw new TarefaNaoEncontrada("Nenhuma tarefa cadastrada");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void completed(int id, boolean concluida) {

        Connection con = null;
        PreparedStatement pstm = null;

        String sql = "UPDATE tarefas SET concluida = ? WHERE id = ?";

        try {
            con = TarefasConn.conn();
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setBoolean(1, true);
            pstm.setInt(2, id);
            pstm.execute();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void filter(boolean concluido) {

        String sql = "SELECT * FROM tarefas WHERE concluida = ?";

        PreparedStatement pstm = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = TarefasConn.conn();
            pstm = con.prepareStatement(sql);
            pstm.setBoolean(1, concluido);
            rs = pstm.executeQuery();

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;

                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                boolean status = rs.getBoolean("concluida");
                String statusTexto = status ? "concluido" : "andamento";

                System.out.println("ID: " + id + " Descrição " + descricao + " Status: " + statusTexto);
            }

            if (!encontrou) {
                throw new TarefaNaoEncontrada("Nenhuma tarefa encontrada");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteAll(){
        String sql = "DELETE FROM tarefas";
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = TarefasConn.conn();
            pstm = (PreparedStatement)  con.prepareStatement(sql);
            pstm.execute();
        }catch (Exception ex){

        }
    }
}

