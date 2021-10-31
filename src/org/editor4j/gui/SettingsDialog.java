package org.editor4j.gui;


import org.editor4j.gui.components.JBaseDialog;
import org.editor4j.gui.components.JField;
import org.editor4j.gui.components.JFontBox;
import org.editor4j.models.Settings;
import org.editor4j.gui.styles.DarkStyle;
import org.editor4j.gui.styles.LightStyle;
import org.editor4j.models.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static org.editor4j.managers.SettingsManager.currentSettings;

public class SettingsDialog extends JBaseDialog {

    public JButton apply = new JButton("Apply");
    JComboBox<Style> styles = new JComboBox<>();
    JFontBox fonts = new JFontBox(15, Font.PLAIN, GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
    JSpinner fontSizes = new JSpinner(new SpinnerNumberModel(20, 10, 50, 1));
    JPanel jPanel = new JPanel();

    
    public SettingsDialog(){
        super("Settings", 600, 440);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));


        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.add("Style", buildStyleOptions());
        jTabbedPane.add("Fonts", buildFontOptions());


        jPanel.add(jTabbedPane);
        SwingUtilities.getRootPane(this).setDefaultButton(apply);
        setModalityType(ModalityType.APPLICATION_MODAL);

        super.setContent(jPanel);
        super.setDefaultButtonOnly(apply);


        apply.addActionListener(new ReloadSettingsDialogListener(this));

        setSettings(currentSettings);
    }

    private void setSettings(Settings currentSettings) {

        styles.getModel().setSelectedItem(currentSettings.style);
        fonts.getModel().setSelectedItem(currentSettings.style.font.getFontName());
        fontSizes.setValue(currentSettings.style.font.getSize());
    }

    private JPanel buildStyleOptions() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        try {
            styles.addItem(new LightStyle());
            styles.addItem(new DarkStyle());

        } catch (IOException e){
            e.printStackTrace();
        }

        jPanel.add(new JField("Style", styles));

        return jPanel;
    }

    private JPanel buildFontOptions(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());

        jPanel.add(new JField("Font", fonts));
        jPanel.add(new JField("Size", fontSizes));

        return jPanel;
    }

    //Get all the values from the GUI controls into a Settings object
    public Settings getSettings() throws IOException {
        Settings settings = new Settings();
        settings.style = (Style) styles.getSelectedItem();
        int fontSize = (int) fontSizes.getValue();
        String fontName = (String) fonts.getSelectedItem();

        settings.style.font = new Font(fontName, Font.PLAIN, fontSize);
        return settings;
    }

    private class ReloadSettingsDialogListener implements ActionListener {
        private final SettingsDialog settingsDialog;

        public ReloadSettingsDialogListener(SettingsDialog settingsDialog) {
            this.settingsDialog = settingsDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.updateComponentTreeUI(settingsDialog);
        }
    }
}
