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

public class FindDialog extends JBaseDialog {
    JPanel jPanel = new JPanel();
    JPanel optionsPanel = new JPanel();
    JButton find = new JButton("Find");
    JTextField searchText = new JTextField();
    JRadioButton up = new JRadioButton("Up (from cursor)");
    JRadioButton down = new JRadioButton("Down (from cursor)");
    JCheckBox matchCase = new JCheckBox("Match Case");
    public FindDialog(Editor editor) {
        super("Find", 350, 200);

        searchText.setColumns(15);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(new JField("Search For", searchText));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(up);
        buttonGroup.add(down);

        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.add(up);
        optionsPanel.add(down);
        optionsPanel.add(matchCase);

        down.setSelected(true);
        jPanel.add(optionsPanel);


        //Are we even editing anything?
        if(FileManager.openedFile == null) {
            searchText.setEnabled(false);
            up.setEnabled(false);
            down.setEnabled(false);
            find.setEnabled(false);
        }

        find.addActionListener(e -> {
            boolean searchDown;

            searchDown = !up.isSelected();

            SearchContext context = new SearchContext();
            String text = searchText.getText();
            if (text.length() == 0)
                return;

            context.setSearchFor(text);
            context.setMatchCase(matchCase.isSelected());
            context.setRegularExpression(false);
            //Here down is forward and up is backward
            context.setSearchForward(searchDown);
            context.setWholeWord(false);

            SearchResult searchResult = SearchEngine.find(editor.codeEditor, context);
            if (!searchResult.wasFound()) {
                String searchDirection = searchDown ? "down" : "up";
                JOptionPane.showMessageDialog(this, "No more matches found further " + searchDirection);
            }


        });
        setModalityType(ModalityType.APPLICATION_MODAL);

        super.setContent(jPanel);
        super.setDefaultButtonOnly(find);
    }




}
