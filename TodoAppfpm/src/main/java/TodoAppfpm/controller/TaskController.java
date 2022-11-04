/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TodoAppfpm.controller;

import TodoAppfpm.model.Task;
import TodoAppfpm.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author ty_jo
 */
public class TaskController {
    
    public List<Task> getAll(int idProject){
         String sql = "SELECT * FROM tasks where idProject = ?";

        List<Task> tasks = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);
            //Setando o valor que corresponde ao filtro de busca
            stmt.setInt(1, idProject);
            //Valor retornado pela execução da Query
            rset = stmt.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {

                Task task = new Task();

                task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProject"));
                task.setName(rset.getString("name"));
                task.setDescription(rset.getString("description"));
                task.setCompletedAt(rset.getBoolean("completed"));
                task.setNotes(rset.getString("notes"));
                task.setDeadline(rset.getDate("deadline"));
                task.setCreatedAt(rset.getDate("createdAt"));
                task.setCreatedAt(rset.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt,rset); 
        }
        
        //Lista de tarefas que foi criada e carregada do banco de dados 
        return tasks;
    }
    
    
    public void save(Task task){
        String sql = "INSERT INTO tasks(idProject, name, description, completed, notes, deadline, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conex�o com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, task.getIdProject());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getDescription());
            stmt.setBoolean(4, task.isCompletedAt());
            stmt.setString(5, task.getNotes());
            stmt.setDate(6, new Date(task.getDeadline().getTime()));
            stmt.setDate(7, new Date(task.getCreatedAt().getTime()));
            stmt.setDate(8, new Date(task.getUpdatedAt().getTime()));

            //Executa a sql para inser��o dos dados
            stmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            //Fecha as conex�es           
                ConnectionFactory.closeConnection(conn, stmt);            
        }
    }
    
    public void update (Task task){
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, completed = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conexão com o banco
            conn = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);
            //Setando os valores no Statement
            stmt.setInt     (1, task.getIdProject());
            stmt.setString  (2, task.getName());
            stmt.setString  (3, task.getDescription());
            stmt.setBoolean (4, task.isCompletedAt());
            stmt.setString  (5, task.getNotes());
            stmt.setDate    (6, new Date(task.getDeadline().getTime()));
            stmt.setDate    (7, new Date(task.getCreatedAt().getTime()));
            stmt.setDate    (8, new Date(task.getUpdatedAt().getTime()));
            stmt.setInt     (9, task.getId());

            //Executando a Query
            stmt.execute();
        } catch (Exception ex) {
           throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage(), ex);
        } finally {
            //Fecha as conex�es           
                ConnectionFactory.closeConnection(conn, stmt);            
        }
               
    }
    
    public void removeById(int taskId){
       String sql = "DELETE FROM tasks WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            
            //Criação da conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //Preparando a Query
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taskId);
            //Executando a Query
            stmt.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa", ex);
        } finally {
          
               ConnectionFactory.closeConnection(conn, stmt);           
        }      
   }
    
    
    
}
