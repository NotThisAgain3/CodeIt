package org.editor4j.gui.styles;

import org.editor4j.gui.Style;
import org.fife.ui.rsyntaxtextarea.Theme;

import java.awt.*;
import java.io.IOException;

public class LightStyle extends Style {
    public LightStyle() throws IOException {
        this.name = "Light";
        this.lookAndFeel = "com.formdev.flatlaf.FlatLightLaf";
        this.themePath = "/org/fife/ui/rsyntaxtextarea/themes/idea.xml";
        this.font = new Font("JetBrains Mono Regular", Font.PLAIN, 20);

    }
}
