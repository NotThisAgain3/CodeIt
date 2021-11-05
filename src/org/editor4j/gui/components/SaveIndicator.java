package org.editor4j.gui.components;

import javax.swing.*;
import java.awt.*;

public class SaveIndicator extends JPanel {
    public boolean status = false;
    public JLabel indicator = new JLabel();
    public SaveIndicator(){
        setLayout(new BorderLayout());
        indicator.setForeground(Color.LIGHT_GRAY);
        add(indicator, BorderLayout.EAST);
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
