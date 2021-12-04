package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.gui.UIUtils;
import org.editor4j.managers.FileManager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

 public class NewFileMenuItemListener implements ActionListener {
    public Editor editor;
    public NewFileMenuItemListener(Editor e){
        this.editor = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jFileChooser.showOpenDialog(editor.jFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            editor.setCodeEditorAsContentPane();
            try {
                boolean isNewFileCreated = jFileChooser.getSelectedFile().createNewFile();
                if(isNewFileCreated){
                    FileManager.openedFile = jFileChooser.getSelectedFile();
                    editor.openInEditor(UIUtils.getFileExtension(FileManager.openedFile), FileManager.openFile(FileManager.openedFile.getPath()));

                }
                else {
                    JOptionPane.showMessageDialog(editor.jFrame, "Failed to create file " + jFileChooser.getSelectedFile());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}