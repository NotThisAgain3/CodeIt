package org.editor4j;

import org.editor4j.gui.components.JEmptyPanel;
import org.editor4j.gui.components.SaveIndicator;
import org.editor4j.gui.listeners.*;
import org.editor4j.managers.SettingsManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.editor4j.gui.UIUtils.osMenuMask;

public class Editor {

    public RSyntaxTextArea codeEditor;
    public RTextScrollPane codeEditorScrollPane;
    public JFrame jFrame = new JFrame("Editor4J");
    public JMenuBar jMenuBar;
    public JMenu fileMenu;
    public JMenuItem newFileMenuItem, openFileMenuItem, saveFileMenuItem;
    public JMenu editorMenu;
    public JMenuItem settingsMenuItem;
    public SaveIndicator saveIndicator;
    public void createNewEditor() {

        codeEditor = new RSyntaxTextArea();
        codeEditorScrollPane = new RTextScrollPane(codeEditor);
        codeEditor.setCodeFoldingEnabled(true);
        codeEditor.getDocument().addDocumentListener(new SavedDocumentListener(this));

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

        editorMenu = new JMenu("Editor");
        settingsMenuItem = new JMenuItem("Settings");

        settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_S, osMenuMask | InputEvent.ALT_DOWN_MASK));
        settingsMenuItem.addActionListener(new SettingsMenuItemListener(this));

        editorMenu.add(settingsMenuItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(editorMenu);
        saveIndicator = new SaveIndicator();
        jMenuBar.add(saveIndicator);


        jFrame.setJMenuBar(jMenuBar);

        jFrame.setContentPane(new JEmptyPanel("Get Started by going to File > New File or File > Open File"));
        SettingsManager.applySettings(SettingsManager.currentSettings, this);


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(640, 480);
        jFrame.setVisible(true);
    }
}
