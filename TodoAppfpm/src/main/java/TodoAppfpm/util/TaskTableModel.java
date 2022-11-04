/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TodoAppfpm.util;

import TodoAppfpm.model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ty_jo
 */
public class TaskTableModel extends AbstractTableModel{

    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList<>();
    
    //Quantidade de tarefas ou sera linhas?
    @Override
    public int getRowCount() {
         return tasks.size();
      }

    //Quantidade de colunas
    @Override
    public int getColumnCount() {
            return columns.length;
    }
    //Método para por o nome das colunas
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    }
    
    //método para saber se a cédula vai ser editavel ou não.
    public boolean isCellEditable(int rowIndex, int columnIndex){
        //return true; //Se usarmos um true todas a cédulas serão editadas.
        
       //Assim se o valor da linha for 3 ele vai retornar true assim somente a linha tres de tarefa cconcluida sera editavel
        return columnIndex == 3;  
    }
    
    //Setar o tipo das colunas
    public Class<?> getColumnClass(int columnIndex){
        if(tasks.isEmpty()){
            return Object.class;
        }
        return this.getValueAt(0, columnIndex).getClass();
    }
    
  
    

    //Valores que teremos exibidos
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return simpleDateFormat.format(tasks.get(rowIndex).getDeadline());
               
            case 3:
                return tasks.get(rowIndex).isCompletedAt();
            case 4: return "";
            case 5: return "";
            default:
                return "Dado não encontrado";
        }
    }
    
    //Metodo para marcar como completa a tarefa //Quando clicamos o este metodo é chamado
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        tasks.get(rowIndex).setCompletedAt((boolean) aValue);
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
}
