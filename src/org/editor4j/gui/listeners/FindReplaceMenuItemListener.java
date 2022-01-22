package org.editor4j.gui.listeners;

import org.editor4j.App;
import org.editor4j.gui.FindReplaceBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindReplaceMenuItemListener implements ActionListener {
    private final App app;
    FindReplaceBar findReplaceBar;

    public FindReplaceMenuItemListener(App e) {
        this.app = e;
        findReplaceBar = new FindReplaceBar(app);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!findReplaceBar.visible) {
            app.addToolBar(findReplaceBar);
            findReplaceBar.visible = true;
        }
        else {
            app.removeToolBar(findReplaceBar);
            findReplaceBar.visible = false;
        }
    }
}
