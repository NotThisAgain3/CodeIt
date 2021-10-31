package org.editor4j.gui.components;

import javax.swing.*;
import java.awt.*;

/***
 *  A JVoid is an empty JPanel with ghost text
 */
public class JEmptyPanel extends JPanel {
    public JEmptyPanel(String text){
        JLabel jLabel = new JLabel(text);
        jLabel.setForeground(Color.LIGHT_GRAY);

        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setVerticalAlignment(SwingConstants.CENTER);

        add(jLabel);
    }
}
