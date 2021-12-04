package org.editor4j;

import org.editor4j.models.Settings;

public interface EditorSignals {
    void setCodeEditorAsContentPane();
    void openInEditor(String fileExtension, String text);
    void updateSavedStatus(boolean saved);
    void applySettings(Settings s);
}
