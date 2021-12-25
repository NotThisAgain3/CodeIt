package org.editor4j.managers;

import org.editor4j.gui.listeners.FileLoadedListener;

import javax.swing.*;
import java.io.*;
import java.util.concurrent.ExecutionException;

public class FileManager {
    public static File openedFile = null;
    private FileManager(){}

    //TODO Avoid opening a new BufferedWriter everytime we save
    public static void saveTextToOpenFile(String text){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(openedFile));
            bufferedWriter.write(text);
            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String openFile(String path){
        openedFile = new File(path);

        String total = "";

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(openedFile));

            String line;
            while((line = bufferedReader.readLine()) != null){
                total += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
    public static void saveTextToOpenFileOffEDT(String text){
        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() {
                saveTextToOpenFile(text);
                return null;
            }
        };
        swingWorker.execute();
    }
    public static String openFileOffEDT(String path, FileLoadedListener fileLoadedListener) throws ExecutionException, InterruptedException {
        SwingWorker openWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() {
                String fileText = openFile(path);
                return fileText;
            }

            @Override
            protected void done() {
                fileLoadedListener.fileLoaded(this);
            }
        };
        openWorker.execute();

        return (String) openWorker.get();
    }
}
