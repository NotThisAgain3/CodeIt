package org.editor4j.gui.components;

import javax.swing.*;
import java.awt.*;

public class SaveIndicator extends JPanel {
    public boolean status = false;
    public JLabel indicator = new JLabel();
    public SaveIndicator(){
        //FlowLayout.RIGHT says "Hey, I don't care about components being placed left to right or right to left
        //Just grab everybody, and glue them to the RIGHT (while preserving order)
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        indicator.setForeground(Color.LIGHT_GRAY);
        add(indicator);
        setOpaque(false);
    }

    public void setStatus(boolean saved){
        status = saved;

        if(saved) {
            indicator.setText("Saved");
        }
        else {
            indicator.setText("Not Saved");
        }
    }
}
