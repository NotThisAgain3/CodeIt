package org.editor4j.gui.listeners;

import org.editor4j.App;
import org.editor4j.managers.SavedManager;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SavedDocumentListener implements DocumentListener {
    private final App app;

    public SavedDocumentListener(App app) {
        this.app = app;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        SavedManager.saved = false;
        app.updateSavedStatus(SavedManager.saved);
    }
}
