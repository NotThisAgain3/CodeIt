package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.gui.FindReplaceDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindReplaceMenuItemListener implements ActionListener {
    private final Editor editor;

    public FindReplaceMenuItemListener(Editor e) {
        this.editor = e;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        FindReplaceDialog findReplaceDialog = new FindReplaceDialog(editor);

        findReplaceDialog.setVisible(true);
    }
}
