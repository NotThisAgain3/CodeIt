package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.managers.FileManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileMenuItemListener implements ActionListener {
    private final Editor editor;
    public SaveFileMenuItemListener(Editor e){
        editor = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(FileManager.openedFile != null){
            String newText = editor.codeEditor.getText();
            FileManager.saveTextToOpenFile(newText);
        }
    }
}