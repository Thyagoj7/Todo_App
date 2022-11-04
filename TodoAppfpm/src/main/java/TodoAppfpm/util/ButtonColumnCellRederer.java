/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TodoAppfpm.util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ty_jo
 */
public class ButtonColumnCellRederer extends DefaultTableCellRenderer{
    
    private String buttonType;   //ButtonTyp
    
    //Vamos criar um construtor para a Classe
    public ButtonColumnCellRederer(String buttonType){
        this.buttonType = buttonType;
    }

    public String getButtonType() {  
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        //Cells are by default rendered as a JLabel.
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        label.setHorizontalAlignment(JLabel.CENTER); //vai devolver o elemento na pagina cru e centralizado
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + buttonType + ".png")));  //setamos o icone na label

        //Return the JLabel which renders the cell.
        return label;
    }
    
}
