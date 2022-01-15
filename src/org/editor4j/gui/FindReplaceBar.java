package org.editor4j.gui;

import org.editor4j.Editor;
import org.editor4j.gui.components.JField;
import org.editor4j.gui.components.ToolBar;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

import javax.swing.*;
import java.awt.*;

public class FindReplaceBar extends ToolBar {
    JTextField findTextField = new JTextField();
    JTextField replaceTextField = new JTextField();

    JField replacementTextJField = new JField("Replace With", replaceTextField);

    JPanel textFieldsPanel = new JPanel();

    JCheckBox findAndReplace = new JCheckBox();
    JButton button = new JButton("Find");

    JRadioButton searchUp = new JRadioButton(), searchDown = new JRadioButton();
    JCheckBox matchCase = new JCheckBox();

    public FindReplaceBar(Editor editor) {
        super(editor);

        findTextField.setColumns(10);
        replaceTextField.setColumns(10);

        textFieldsPanel.setLayout(new FlowLayout());
        textFieldsPanel.add(new JField("Find", findTextField));



        findAndReplace.addActionListener(e -> {
            if(findAndReplace.isSelected()) {
                textFieldsPanel.add(replacementTextJField);
                SwingUtilities.updateComponentTreeUI(replacementTextJField);
                button.setText("Replace");
            }
            else {
                textFieldsPanel.remove(replacementTextJField);
                button.setText("Find");
            }

            textFieldsPanel.revalidate();
            textFieldsPanel.repaint();

        });

        //Set up options
        JPanel searchOptions = new JPanel();
        searchOptions.setLayout(new FlowLayout());
        searchOptions.add(new JField("Find And Replace", findAndReplace));

        ButtonGroup upOrDown = new ButtonGroup();
        upOrDown.add(searchUp);
        upOrDown.add(searchDown);

        searchOptions.add(new JField("Up", searchUp));
        searchOptions.add(new JField("Down", searchDown));
        searchOptions.add(new JField("Match Case", matchCase));


        button.addActionListener(e -> {
            SearchContext searchContext = new SearchContext();

            searchContext.setSearchFor(findTextField.getText());
            searchContext.setMatchCase(matchCase.isSelected());
            searchContext.setSearchForward(searchDown.isSelected());
            searchContext.setWholeWord(false);


            if(findAndReplace.isSelected()) {
                searchContext.setReplaceWith(replaceTextField.getText());
                SearchEngine.replace(editor.codeEditor, searchContext);
            }
            else
                SearchEngine.find(editor.codeEditor, searchContext).wasFound();

        });

        addBarComponent(textFieldsPanel);
        addBarComponent(searchOptions);
        addBarComponent(button);
    }


}
