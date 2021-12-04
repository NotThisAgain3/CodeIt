package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.managers.SavedManager;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SavedDocumentListener implements DocumentListener {
    private final Editor editor;

    public SavedDocumentListener(Editor editor) {
        this.editor = editor;
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
        editor.updateSavedStatus(SavedManager.saved);
    }
}
