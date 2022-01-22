package org.editor4j.gui.components;

import org.editor4j.Editor;

import javax.swing.*;
import java.awt.*;

public abstract class ToolBar extends JPanel {

    public boolean visible = false;
    BorderLayout baseBorderLayout = new BorderLayout();
    JButton closeBar = new JButton();
    JPanel barContent = new JPanel();
    public ToolBar(Editor editor){
        super();

        setLayout(baseBorderLayout);

        baseBorderLayout.setVgap(0);

        closeBar.setIcon(new ImageIcon("assets/x.png"));
        closeBar.setContentAreaFilled(false);
        closeBar.addActionListener(e -> {
            editor.removeToolBar(this);
            visible = false;
        });

        FlowLayout barContentFlowLayout = new FlowLayout(FlowLayout.LEFT);

        barContentFlowLayout.setVgap(0);

        barContent.setLayout(barContentFlowLayout);

        add(barContent, BorderLayout.CENTER);

        add(closeBar, BorderLayout.EAST);
    }




    public void addBarComponent(JComponent c){
        barContent.add(c);
    }
}
