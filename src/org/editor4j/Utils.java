package org.editor4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Utils {
    /**
     * Serialization with FileInputStream doesn't accept
     * serializing to directories if it isn't already present.
     * This method fixes that by creating all parent directories
     * of the file, then serializing to the file.
     * @param file - Where to serialize to
     * @param o - Object to serialize
     */
    public static void serializeToPath(File file, Object o){
        if(!file.exists()) {
            //check for return val of true
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Not allowed to create file " + file.getName() + ". do you have permissions?");
                e.printStackTrace();
            }
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
