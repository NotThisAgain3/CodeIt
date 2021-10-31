package org.editor4j.gui.styles;

import org.editor4j.gui.Style;
import org.fife.ui.rsyntaxtextarea.Theme;

import java.awt.*;
import java.io.IOException;

public class DarkStyle extends Style {
    public DarkStyle() throws IOException {
        this.name = "Dark";
        this.lookAndFeel = "com.formdev.flatlaf.FlatDarkLaf";
        this.themePath = "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml";
        this.font = new Font("JetBrains Mono Regular", Font.PLAIN, 20);

    }
}
