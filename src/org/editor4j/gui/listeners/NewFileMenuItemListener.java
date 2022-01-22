package org.editor4j.gui.listeners;

import org.editor4j.App;
import org.editor4j.gui.UIUtils;
import org.editor4j.managers.FileManager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class NewFileMenuItemListener implements ActionListener {
    public App app;
    public NewFileMenuItemListener(App e){
        this.app = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        jFileChooser.setDialogTitle("New");
        int returnValue = jFileChooser.showDialog(app.jFrame, "Create File");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            app.setCodeEditorAsContentPane();
            try {
                boolean isNewFileCreated = jFileChooser.getSelectedFile().createNewFile();
                if(isNewFileCreated){
                    FileManager.openedFile = jFileChooser.getSelectedFile();
                    FileManager.openFileOffEDT(FileManager.openedFile.getPath(), s -> {
                        try {
                            app.openInEditor(UIUtils.getFileExtension(FileManager.openedFile), (String) s.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    });


                }
                else {
                    //WIP, more descriptive error messages
                    String fileCreationErrorMsg = "Failed to create " + jFileChooser.getSelectedFile() + " because another file with that same name already exists";
                    JOptionPane.showMessageDialog(app.jFrame, fileCreationErrorMsg);
                }
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}