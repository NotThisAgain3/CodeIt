package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.gui.FindDialog;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class FindMenuListener implements MenuListener {
    private final Editor editor;

    public FindMenuListener(Editor e) {
        this.editor = e;
    }


    @Override
    public void menuSelected(MenuEvent e) {
        FindDialog findDialog = new FindDialog(editor);

        findDialog.setVisible(true);
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
