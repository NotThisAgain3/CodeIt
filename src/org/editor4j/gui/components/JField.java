package org.editor4j.gui.components;

import javax.swing.*;

public class JField extends JPanel {
    public JField(String text, JComponent component){
        JLabel jLabel = new JLabel(text);
        jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(jLabel);

        add(component);

    }
}
