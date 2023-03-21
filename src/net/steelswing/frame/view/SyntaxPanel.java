/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.function.Consumer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import net.steelswing.tree.FileNode;
import net.steelswing.util.SwingUtils;
import syntaxhighlighter.SyntaxHighlighterParser;
import syntaxhighlighter.brush.BrushBash;
import syntaxhighlighter.brush.BrushCSharp;
import syntaxhighlighter.brush.BrushCss;
import syntaxhighlighter.brush.BrushJSON;
import syntaxhighlighter.brush.BrushJScript;
import syntaxhighlighter.brush.BrushJava;
import syntaxhighlighter.brush.BrushPreudoC;
import syntaxhighlighter.brush.BrushPython;
import syntaxhighlighter.brush.BrushXml;
import syntaxhighlighter.syntaxhighlight.SyntaxHighlighter;
import syntaxhighlighter.syntaxhighlight.Theme;
import syntaxhighlighter.theme.ThemeRDark;

/**
 * File: SyntaxPanel.java
 * Created on 24.08.2022, 11:31:14
 *
 * @author LWJGL2
 */
public class SyntaxPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected SyntaxHighlighterParser parser = null;
    protected SyntaxHighlighter highlighter;
    protected Consumer<String> contentConsumer;


    public SyntaxPanel(FileNode fileNode) {

        // the SyntaxHighlighter parser
        String filename = fileNode.getFileName().toLowerCase();

        if (filename.endsWith(".class") || filename.endsWith(".java") || filename.endsWith(".txt")) {
            parser = new SyntaxHighlighterParser(new BrushJava());
        } else if (filename.endsWith(".xml") || filename.endsWith(".html") || filename.endsWith(".form")) {
            parser = new SyntaxHighlighterParser(new BrushXml());
        } else if (filename.endsWith(".json")) {
            parser = new SyntaxHighlighterParser(new BrushJSON());
        } else if (filename.endsWith(".css")) {
            parser = new SyntaxHighlighterParser(new BrushCss());
        } else if (filename.endsWith(".cs")) {
            parser = new SyntaxHighlighterParser(new BrushCSharp());
        } else if (filename.endsWith(".py")) {
            parser = new SyntaxHighlighterParser(new BrushPython());
        } else if (filename.endsWith(".bat") || filename.endsWith(".sh")) {
            parser = new SyntaxHighlighterParser(new BrushBash());
        } else if (filename.endsWith(".js")) {
            parser = new SyntaxHighlighterParser(new BrushJScript());
        } else if (filename.endsWith(".properties") || filename.endsWith(".cfg") || filename.endsWith(".conf") || filename.endsWith(".yml") || filename.endsWith(".yaml") || filename.endsWith(".mf")) {
            parser = new SyntaxHighlighterParser(new BrushJava());
        } else if (filename.endsWith(".c") || filename.endsWith(".cpp") || filename.endsWith(".h") || filename.endsWith(".hpp") || filename.endsWith(".glsl") || filename.endsWith(".vert") || filename.endsWith(".frag") || filename.endsWith(".vs") || filename.endsWith(".fs") || filename.endsWith(".shader")) {
            parser = new SyntaxHighlighterParser(new BrushPreudoC());
        }
//        parser = new SyntaxHighlighterParser(new BrushJava());
        final Theme themeRDark = new ThemeRDark();
        if (parser != null) {
            // initialize the highlighter and use RDark theme
            highlighter = new SyntaxHighlighter(parser, themeRDark);
            // set the line number count from 10 instead of 1
            highlighter.setFirstLine(1);
            setLayout(new BorderLayout());
            add(highlighter);
            contentConsumer = highlighter::setContent;
        } else {
            JTextPane textPane = new JTextPane();
            textPane.setEditable(false);
            textPane.setBackground(themeRDark.getBackground());
            setLayout(new BorderLayout());

            JScrollPane pane = new JScrollPane(textPane);
            pane.setAutoscrolls(false);
            add(pane);

            contentConsumer = textPane::setText;
        }
    }

    public void setContent(String content) {
        contentConsumer.accept(content);
    }

    public void selectAllText() {
        if (highlighter != null) {
            highlighter.getHighlighter().setCaretPosition(1);
            highlighter.getHighlighter().selectAll();
        }
    }

    public void copySelectedTextIntoClipboard() {
        if (highlighter != null) {
            try {
                String myString = highlighter.getHighlighter().getSelectedText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            } catch (Throwable e) {
                SwingUtils.showErrorDialog("Failed to copy text", e);
            }

        }
    }

}
