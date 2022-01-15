package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.gui.FindReplaceBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindReplaceMenuItemListener implements ActionListener {
    private final Editor editor;
    FindReplaceBar findReplaceBar;

    public FindReplaceMenuItemListener(Editor e) {
        this.editor = e;
        findReplaceBar = new FindReplaceBar(editor);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!findReplaceBar.visible) {
            editor.addToolBar(findReplaceBar);
            findReplaceBar.visible = true;
        }
        else {
            editor.removeToolBar(findReplaceBar);
            findReplaceBar.visible = false;
        }
    }
}
