package org.editor4j;

import org.editor4j.managers.SettingsManager;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            loadSettings();
            new Editor().createNewEditor();
        });
    }

    private static void loadSettings() {
        File startupSettingsFile = new File("settings.e4j");
        if(startupSettingsFile.exists())
            SettingsManager.currentSettings = SettingsManager.loadSettingsFromFile(startupSettingsFile);
        else
            SettingsManager.currentSettings = SettingsManager.STARTUP_DEFAULT_SETTINGS;
    }
}

