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
    JField replacementTextField = new JField("Replace With", replacementText);
    JPanel textFieldsPanel = new JPanel();
    JCheckBox doFindAndReplace = new JCheckBox();
    JButton jButton = new JButton("Find");

    JRadioButton backward = new JRadioButton(), forward = new JRadioButton();
    JCheckBox matchCase = new JCheckBox();


    public FindReplaceDialog(Editor editor) {
        super("Find/Replace", 320, 240);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));
        textFieldsPanel.add(new JField("Find", textToFind));


        jPanel.add(textFieldsPanel);

        doFindAndReplace.addActionListener(e -> {
            if(doFindAndReplace.isSelected()) {
                textFieldsPanel.add(replacementTextField);
                jButton.setText("Replace");
            }
            else {
                textFieldsPanel.remove(replacementTextField);
                jButton.setText("Find");
            }

            textFieldsPanel.revalidate();
            textFieldsPanel.repaint();

        });

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(new JField("Find And Replace", doFindAndReplace));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(backward);
        buttonGroup.add(forward);
        optionsPanel.add(new JField("Backward (Up)", backward));
        optionsPanel.add(new JField("Forward (Down)", forward));
        optionsPanel.add(new JField("Match Case", matchCase));

        jPanel.add(optionsPanel);
        setModalityType(ModalityType.APPLICATION_MODAL);




        jButton.addActionListener(e -> {

            boolean fr = doFindAndReplace.isSelected();

            SearchContext context = new SearchContext();
            String text = textToFind.getText();
            if (text.length() == 0) {
                return;
            }
            context.setSearchFor(text);
            context.setMatchCase(matchCase.isSelected());
            context.setSearchForward(forward.isSelected());
            context.setWholeWord(false);

            if(fr)
                context.setReplaceWith(replacementText.getText());

            if(fr)
                SearchEngine.replace(editor.codeEditor, context);
            else
                SearchEngine.find(editor.codeEditor, context).wasFound();

        });



        super.setDefaultButtonOnly(jButton);
        super.setContent(jPanel);


    }




}
