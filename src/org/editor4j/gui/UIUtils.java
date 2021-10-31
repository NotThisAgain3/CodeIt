package org.editor4j.gui;


import org.editor4j.models.Settings;
import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UIUtils {
    public static final int osMenuMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

    public static void setLookAndFeel(String c){
        try {
            UIManager.setLookAndFeel(c);
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "LaF " + c + " caused an exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static String formatTitleBar(String text){
        return "Editor4J - " + text;
    }

}
