package org.editor4j.managers;

import org.editor4j.Editor;
import org.editor4j.gui.UIUtils;
import org.editor4j.gui.styles.LightStyle;
import org.editor4j.models.Settings;
import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SettingsManager {
    public static Settings currentSettings;
    public static Settings STARTUP_DEFAULT_SETTINGS;


    static {
        try {
            STARTUP_DEFAULT_SETTINGS = new Settings();
            STARTUP_DEFAULT_SETTINGS.style = new LightStyle();
            STARTUP_DEFAULT_SETTINGS.font = new Font("JetBrains Mono Regular", Font.PLAIN, 20);
            STARTUP_DEFAULT_SETTINGS.lineWrapEnabled = false;
            STARTUP_DEFAULT_SETTINGS.tabSize = 4;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings loadSettingsFromFile(File settingsFile) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(settingsFile));
            return (Settings) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Never happens because deserialize is only called when loadSettings() finds editor.settingsFile
        return null;
    }
    public static void applySettings(Settings s, Editor editor){
        UIUtils.setLookAndFeel(s.style.lookAndFeel);
        UIUtils.updateComponentTreeUI(editor.jFrame);

        try {
            Theme theme = Theme.load(SettingsManager.class.getResourceAsStream(s.style.themePath), s.font);
            theme.apply(editor.codeEditor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.codeEditor.setTabSize(s.tabSize);
        editor.codeEditor.setLineWrap(s.lineWrapEnabled);
    }

    public static void saveSettingsToFile(Settings s) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("settings.e4j"));

            objectOutputStream.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
