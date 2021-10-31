package org.editor4j.gui.components;

import javax.swing.*;
import java.awt.*;

public class SaveIndicator extends JPanel {
    public boolean status = false;
    public JLabel indicator = new JLabel();
    public SaveIndicator(){
        setLayout(new BorderLayout());
        indicator.
        add(indicator, BorderLayout.EAST);
        setOpaque(false);
    }

    public void setStatus(boolean saved){
        status = saved;

        if(saved) {
            indicator.setText("Saved");
            indicator.setForeground(Color.GREEN);
        }
        else {
            indicator.setText("Not Saved");
            indicator.setForeground(Color.RED);
        }
    }
}
