package org.editor4j.gui;

import org.editor4j.Editor;
import org.editor4j.gui.components.JBaseDialog;
import org.editor4j.gui.components.JField;
import org.editor4j.managers.FileManager;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;

import javax.swing.*;
import java.awt.*;

public class FindReplaceDialog extends JBaseDialog {
    JPanel jPanel = new JPanel();
    JPanel optionsPanel = new JPanel();
    JButton doFindOrReplace = new JButton("Find");
    JTextField find = new JTextField();
    JTextField replaceWith = new JTextField();

    JRadioButton up = new JRadioButton("Up (from cursor)");
    JRadioButton down = new JRadioButton("Down (from cursor)");
    JCheckBox matchCase = new JCheckBox("Match Case");
    JCheckBox replace = new JCheckBox("Find and Replace");
    public FindReplaceDialog(Editor editor) {
        super("Find/Replace", 580, 200);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(new JField("Look For", find));
        jPanel.add(new JField("Replace With", replaceWith));




        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(up);
        buttonGroup.add(down);

        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(up);
        optionsPanel.add(down);
        optionsPanel.add(matchCase);
        optionsPanel.add(replace);

        replace.addActionListener(e -> {
            if(replace.isSelected()) {
                replaceWith.setEnabled(true);
                doFindOrReplace.setText("Replace");
            }
            else {
                doFindOrReplace.setText("Find");
                replaceWith.setEnabled(false);
            }
        });

        down.setSelected(true);
        jPanel.add(optionsPanel);



        doFindOrReplace.addActionListener(e -> {
            boolean searchDown;

            searchDown = !up.isSelected();

            SearchContext context = new SearchContext();

            String text = find.getText();
            if (text.length() == 0)
                return;

            context.setSearchFor(text);



            context.setMatchCase(matchCase.isSelected());

            context.setRegularExpression(false);
            //Here down is forward and up is backward
            context.setSearchForward(searchDown);
            context.setWholeWord(false);

            SearchResult searchResult;
            if(replace.isSelected()) {
                context.setReplaceWith(replaceWith.getText());
                searchResult = SearchEngine.replace(editor.codeEditor, context);
            }
            else {
                searchResult = SearchEngine.find(editor.codeEditor, context);
            }



            if (!searchResult.wasFound()) {
                String searchDirection = searchDown ? "down" : "up";
                JOptionPane.showMessageDialog(this, "No more matches found further " + searchDirection);
            }


        });
        if(FileManager.openedFile == null)
            doFindOrReplace.setEnabled(false);
        setModalityType(ModalityType.APPLICATION_MODAL);

        super.setContent(jPanel);
        super.setDefaultButtonOnly(doFindOrReplace);
    }




}
