/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TodoAppfpm.util;

import TodoAppfpm.model.Task;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ty_jo
 */
public class DeadlineColumnCelRenderer extends DefaultTableCellRenderer {
    
@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
//Este método devolve um compomente que sera mostrado na tela. //estemetodo recebe como parametro a tabela que vamos usar e o valor que sera se a celula esta selecionada com focos e etc..
    
//Vai retornar o componente que vai ser renderizado na tela
JLabel label; //Criamos um JLabel que vai ser a laibel que vai ser devolvida e vai aparecer no nosso grid
        label= (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col); //usamos o supor por estar pegando da Classe Pai.
        label.setHorizontalAlignment( JLabel.CENTER );  //vai centralizar a data que sera impressa
        
        //AGora vamos pegar a tarefa dentro do model
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        Task task = taskModel.getTasks().get(row);  //Vamos pegar a tarefa pelo parametro da Linha Uuma linha especifica)

        if (task.getDeadline().equals(new Date())) {  //Vamos pegar o deadline e ver se é igual a data atual
            label.setBackground(Color.YELLOW);
        } else {
            if (task.getDeadline().after(new Date())) {  // Ver se o deadline esta depois da data atual.(A tarefa ainda esta no prazo)
                label.setBackground(Color.GREEN);  // Se sim colocamos a cor green
            } else {
                label.setBackground(Color.RED); //Se não cor vermelha
            }
        }
return label;
}
        
}
