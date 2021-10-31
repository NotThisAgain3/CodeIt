package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.gui.UIUtils;
import org.editor4j.managers.FileManager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileMenuItemListener implements ActionListener {
    private final Editor editor;

    public OpenFileMenuItemListener(Editor e){
        editor = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jFileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            editor.jFrame.setContentPane(editor.codeEditorScrollPane);
            editor.jFrame.revalidate();
            editor.jFrame.repaint();
            FileManager.openedFile = jFileChooser.getSelectedFile();

            editor.codeEditor.setText(FileManager.openFile(jFileChooser.getSelectedFile().getPath()));
            editor.jFrame.setTitle(UIUtils.formatTitleBar(FileManager.openedFile.getPath()));

            editor.codeEditor.setSyntaxEditingStyle(UIUtils.getSyntaxEditingStyle(UIUtils.getFileExtension(FileManager.openedFile)));

        }

    }
}