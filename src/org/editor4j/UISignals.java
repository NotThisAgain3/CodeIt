package org.editor4j;

import org.editor4j.gui.FindReplaceBar;
import org.editor4j.models.Settings;

import javax.swing.*;

public interface EditorSignals {
    void setCodeEditorAsContentPane();
    void openInEditor(String fileExtension, String text);
    void updateSavedStatus(boolean saved);
    void applySettings(Settings s);
    void addToolBar(JPanel b);
    void removeToolBar(JPanel b);
}
