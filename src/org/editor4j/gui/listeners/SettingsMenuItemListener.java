package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.managers.SettingsManager;
import org.editor4j.models.Settings;
import org.editor4j.gui.SettingsDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SettingsMenuItemListener implements ActionListener {

    private final Editor editor;

    public SettingsMenuItemListener(Editor e){
        editor = e;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        SettingsDialog settingsDialog = new SettingsDialog();

        settingsDialog.apply.addActionListener(actionEvent1->{
            try {
                Settings chosenSettings = settingsDialog.getSettings();
                SettingsManager.currentSettings = chosenSettings;
                SettingsManager.saveSettingsToFile(chosenSettings);
                editor.applySettings(chosenSettings);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        settingsDialog.setVisible(true);
    }
}