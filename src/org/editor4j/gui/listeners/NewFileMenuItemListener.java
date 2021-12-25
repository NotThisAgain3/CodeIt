package org.editor4j.gui.listeners;

import org.editor4j.Editor;
import org.editor4j.gui.UIUtils;
import org.editor4j.managers.FileManager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class NewFileMenuItemListener implements ActionListener {
    public Editor editor;
    public NewFileMenuItemListener(Editor e){
        this.editor = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        jFileChooser.setDialogTitle("New");
        int returnValue = jFileChooser.showDialog(editor.jFrame, "Create File");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            editor.setCodeEditorAsContentPane();
            try {
                boolean isNewFileCreated = jFileChooser.getSelectedFile().createNewFile();
                if(isNewFileCreated){
                    FileManager.openedFile = jFileChooser.getSelectedFile();
                    FileManager.openFileOffEDT(FileManager.openedFile.getPath(), s -> {
                        try {
                            editor.openInEditor(UIUtils.getFileExtension(FileManager.openedFile), (String) s.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    });


                }
                else {
                    //WIP, more descriptive error messages
                    String fileCreationErrorMsg = "Failed to create " + jFileChooser.getSelectedFile() + " because another file with that same name already exists";
                    JOptionPane.showMessageDialog(editor.jFrame, fileCreationErrorMsg);
                }
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}