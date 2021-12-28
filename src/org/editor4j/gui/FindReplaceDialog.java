package org.editor4j.gui;

import org.editor4j.Editor;
import org.editor4j.gui.components.JBaseDialog;
import org.editor4j.gui.components.JField;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

import javax.swing.*;
import java.awt.*;

public class FindReplaceDialog extends JBaseDialog {
    JPanel jPanel = new JPanel();


    JTextField textToFind = new JTextField();
    JTextField replacementText = new JTextField();

    JField replacementTextJField = new JField("Replace With", replacementText);

    JPanel textFieldsPanel = new JPanel();

    JCheckBox doFindAndReplace = new JCheckBox();
    JButton performOperation = new JButton("Find");

    JRadioButton searchBackward = new JRadioButton(), searchForward = new JRadioButton();
    JCheckBox matchCase = new JCheckBox();


    public FindReplaceDialog(Editor editor) {
        super("Find/Replace", 320, 240);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        textToFind.setColumns(10);
        replacementText.setColumns(10);

        textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));
        textFieldsPanel.add(new JField("Find", textToFind));


        jPanel.add(textFieldsPanel);

        doFindAndReplace.addActionListener(e -> {
            if(doFindAndReplace.isSelected()) {
                textFieldsPanel.add(replacementTextJField);
                performOperation.setText("Replace");
            }
            else {
                textFieldsPanel.remove(replacementTextJField);
                performOperation.setText("Find");
            }

            textFieldsPanel.revalidate();
            textFieldsPanel.repaint();

        });

        //Set up options

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(new JField("Find And Replace", doFindAndReplace));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(searchBackward);
        buttonGroup.add(searchForward);
        optionsPanel.add(new JField("Backward (Up)", searchBackward));
        optionsPanel.add(new JField("Forward (Down)", searchForward));
        optionsPanel.add(new JField("Match Case", matchCase));

        jPanel.add(optionsPanel);
        setModalityType(ModalityType.APPLICATION_MODAL);




        performOperation.addActionListener(e -> {

            boolean doingFindAndReplace = doFindAndReplace.isSelected();

            SearchContext searchContext = new SearchContext();

            searchContext.setSearchFor(textToFind.getText());
            searchContext.setMatchCase(matchCase.isSelected());
            searchContext.setSearchForward(searchForward.isSelected());
            searchContext.setWholeWord(false);


            if(doingFindAndReplace)
                searchContext.setReplaceWith(replacementText.getText());

            if(doingFindAndReplace)
                SearchEngine.replace(editor.codeEditor, searchContext);
            else
                SearchEngine.find(editor.codeEditor, searchContext).wasFound();

        });

        super.setDefaultButtonOnly(performOperation);
        super.setContent(jPanel);
    }
}
