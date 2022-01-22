package org.editor4j.gui.listeners;

import org.editor4j.App;
import org.editor4j.gui.UIUtils;
import org.editor4j.managers.FileManager;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

public class OpenFileMenuItemListener implements ActionListener {
    private final App app;

    public OpenFileMenuItemListener(App e){
        app = e;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jFileChooser.showOpenDialog(app.jFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            app.setCodeEditorAsContentPane();
            FileManager.openedFile = jFileChooser.getSelectedFile();

            try {
                FileManager.openFileOffEDT(FileManager.openedFile.getPath(), s -> {
                    try {
                        app.openInEditor(UIUtils.getFileExtension(FileManager.openedFile), (String) s.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}