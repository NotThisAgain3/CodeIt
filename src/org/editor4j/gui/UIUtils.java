package org.editor4j.gui;


import org.fife.ui.rtextarea.Gutter;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static org.fife.ui.rsyntaxtextarea.SyntaxConstants.*;
public class UIUtils {
    public static final int osMenuMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

    public static void setLookAndFeel(String c){
        try {
            UIManager.setLookAndFeel(c);
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "LaF " + c + " caused an exception: " + e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static String formatTitleBar(String version, String fileName){
        return "CodeIt " + version + " - " + fileName;
    }
    public static String getFileExtension(File f){
        String[] tokens = f.getName().split("\\.");
        return tokens[tokens.length - 1];
    }
    public static String getSyntaxEditingStyle(String extension){

        switch (extension){
            case "java":
                return SYNTAX_STYLE_JAVA;
            case "py":
                return SYNTAX_STYLE_PYTHON;
            case "c":
                return SYNTAX_STYLE_C;
            case "cpp":
                return SYNTAX_STYLE_CPLUSPLUS;
            case "rb":
                return SYNTAX_STYLE_RUBY;
            case "as":
                return SYNTAX_STYLE_ACTIONSCRIPT;
            case "css":
                return SYNTAX_STYLE_CSS;
            case "html":
                return SYNTAX_STYLE_HTML;
            case "js":
                return SYNTAX_STYLE_JAVASCRIPT;
            case "kt":
                return SYNTAX_STYLE_KOTLIN;
            case "md":
                return SYNTAX_STYLE_MARKDOWN;
            case "sh":
                return SYNTAX_STYLE_UNIX_SHELL;
            case "bat":
                return SYNTAX_STYLE_WINDOWS_BATCH;
            default:
                return SYNTAX_STYLE_NONE;
        }
    }


    /***
     * This is a workaround for SwingUtilities.updateComponentTreeUI() not working
     * on RSyntaxTextArea instances when changing RSTA's Theme. Use this method when
     * updating the Look and Feel and then changing RSTA's Theme.
     * @param rootComponent
     */
    public static void updateComponentTreeUI(Component rootComponent) {

        if (rootComponent instanceof JComponent) {
            JComponent comp = (JComponent) rootComponent;
            comp.updateUI();
            JPopupMenu popupMenu = comp.getComponentPopupMenu();
            if(popupMenu != null)
                updateComponentTreeUI(popupMenu);
        }

        Component[] children = null;

        if (rootComponent instanceof JMenu)
            children = ((JMenu)rootComponent).getMenuComponents();

        else if (rootComponent instanceof Container)
            children = ((Container)rootComponent).getComponents();

        if (children != null) {
            for (Component child : children) {
                /*Gutters act weird if you try to call SwingUtilities.updateComponentTreeUI()
                On it, it defaults to Monospaced Font when you apply a given font twice, e.g Ani
                I don't think this issue has anything to do with Fonts, but rather the Gutter
                object losing it's state when it's UI is updated*/
                if(!(child instanceof Gutter))
                    updateComponentTreeUI(child);
            }
        }

        rootComponent.invalidate();
        rootComponent.validate();
        rootComponent.repaint();
    }
    
}
