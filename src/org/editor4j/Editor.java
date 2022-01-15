package org.editor4j;

import org.editor4j.gui.FindReplaceBar;
import org.editor4j.gui.UIUtils;
import org.editor4j.gui.components.JEmptyPanel;
import org.editor4j.gui.components.SaveIndicator;
import org.editor4j.gui.listeners.*;
import org.editor4j.managers.FileManager;
import org.editor4j.managers.SettingsManager;
import org.editor4j.models.Settings;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.editor4j.gui.UIUtils.osMenuMask;

public class Editor implements EditorSignals {

    public static final String version = "2022.2";

    public JPanel contentPane = new JPanel();
    public JPanel toolBars = new JPanel();
    public RSyntaxTextArea codeEditor;
    public RTextScrollPane codeEditorScrollPane;
    public JFrame jFrame = new JFrame("Editor4J " + version);

    public JMenuBar jMenuBar;
    public JMenu fileMenu, editorMenu, editMenu;
    public JMenuItem newFileMenuItem, openFileMenuItem, saveFileMenuItem;
    public JMenuItem findSlashReplace;
    public JMenuItem settingsMenuItem;

    public SaveIndicator saveIndicator;

    public void createNewEditor() {
        //Workaround, codeEditorScrollPane isn't attached on startup, so it doesn't
        //get the new LaF
        UIUtils.setLookAndFeel(SettingsManager.currentSettings.style.lookAndFeel);

        createCodeEditor();
        createMenuItems();

        jFrame.setJMenuBar(jMenuBar);

        toolBars.setLayout(new BoxLayout(toolBars, BoxLayout.Y_AXIS));

        contentPane.setLayout(new BorderLayout());
        contentPane.add(toolBars, BorderLayout.NORTH);
        contentPane.add(codeEditorScrollPane, BorderLayout.CENTER);

        jFrame.setContentPane(new JEmptyPanel("Get Started by going to File > New File or File > Open File"));

        applySettings(SettingsManager.currentSettings);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        jFrame.setVisible(true);
    }

    private void createCodeEditor() {
        codeEditor = new RSyntaxTextArea();
        codeEditorScrollPane = new RTextScrollPane(codeEditor);
        codeEditor.setCodeFoldingEnabled(true);
        codeEditor.getDocument().addDocumentListener(new SavedDocumentListener(this));
    }

    public void createMenuItems(){
        jMenuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        newFileMenuItem = new JMenuItem("New File");
        openFileMenuItem = new JMenuItem("Open File");
        saveFileMenuItem = new JMenuItem("Save File");

        newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_N, osMenuMask));
        openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_O, osMenuMask));
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_S, osMenuMask));

        openFileMenuItem.addActionListener(new OpenFileMenuItemListener(this));
        newFileMenuItem.addActionListener(new NewFileMenuItemListener(this));
        saveFileMenuItem.addActionListener(new SaveFileMenuItemListener(this));

        fileMenu.add(openFileMenuItem);
        fileMenu.add(newFileMenuItem);
        fileMenu.add(saveFileMenuItem);

        editMenu = new JMenu("Edit");
        findSlashReplace = new JMenuItem("Find/Replace");
        findSlashReplace.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_F, osMenuMask));
        findSlashReplace.addActionListener(new FindReplaceMenuItemListener(this));
        editMenu.add(findSlashReplace);

        editorMenu = new JMenu("Editor");
        settingsMenuItem = new JMenuItem("Settings");

        settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_S, osMenuMask | InputEvent.ALT_DOWN_MASK));
        settingsMenuItem.addActionListener(new SettingsMenuItemListener(this));

        editorMenu.add(settingsMenuItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(editMenu);
        jMenuBar.add(editorMenu);
        saveIndicator = new SaveIndicator();
        jMenuBar.add(saveIndicator);
    }

    @Override
    public void setCodeEditorAsContentPane() {
        jFrame.setContentPane(contentPane);
        jFrame.revalidate();
        jFrame.repaint();
    }

    @Override
    public void openInEditor(String fileExtension, String text) {
        codeEditor.setText(text);
        jFrame.setTitle(UIUtils.formatTitleBar(version, FileManager.openedFile.getPath()));
        codeEditor.setSyntaxEditingStyle(UIUtils.getSyntaxEditingStyle(fileExtension));
    }

    @Override
    public void updateSavedStatus(boolean saved) {
        saveIndicator.setStatus(saved);
    }

    @Override
    public void applySettings(Settings s) {
        UIUtils.setLookAndFeel(s.style.lookAndFeel);
        UIUtils.updateComponentTreeUI(jFrame);
        try {
            Theme theme = Theme.load(SettingsManager.class.getResourceAsStream(s.style.themePath), s.font);
            theme.apply(codeEditor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        codeEditor.setTabSize(s.tabSize);
        codeEditor.setLineWrap(s.wordWrapEnabled);
    }

    @Override
    public void addToolBar(JPanel b) {
        toolBars.add(b);
        toolBars.revalidate();
        toolBars.repaint();

        //updateComponentTreeUI() is called because b isn't part of the main
        //component tree so it doesn't get reloaded on a LaF change. Don't
        //use update UI as it only updates the Bar, not the bar's components
        SwingUtilities.updateComponentTreeUI(b);
    }

    @Override
    public void removeToolBar(JPanel b) {
        toolBars.remove(b);
        toolBars.revalidate();
        toolBars.repaint();
    }
}
