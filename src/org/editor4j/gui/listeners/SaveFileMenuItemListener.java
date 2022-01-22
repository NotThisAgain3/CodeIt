package org.editor4j.gui.listeners;

import org.editor4j.App;
import org.editor4j.managers.FileManager;
import org.editor4j.managers.SavedManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileMenuItemListener implements ActionListener {
    private final App app;
    public SaveFileMenuItemListener(App e){
        app = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(FileManager.openedFile != null){
            String newText = app.codeEditor.getText();
            FileManager.saveTextToOpenFileOffEDT(newText);

            SavedManager.saved = true;
            app.updateSavedStatus(SavedManager.saved);
        }
    }
}