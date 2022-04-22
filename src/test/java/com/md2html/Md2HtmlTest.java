package com.md2html;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.md2html.processor.Md2HtmlProcessor;

public class Md2HtmlTest {

    Md2HtmlProcessor parser = new Md2HtmlProcessor("");

    @Test
    void parseInputNonHeaderText() {
        parser.parseInputText("```");
        parser.parseInputText("");
        parser.parseInputText("Testing non header text");
        parser.parseInputText("");
        parser.parseInputText("```");
        Assertions.assertArrayEquals(new String[] { "<p>Testing non header text</p>" },
                new String[] { parser.getOutputLinesList().get(3) });
    }

    @Test
    void parseInputEmptyText() {
        parser.parseInputText("");
        String output = parser.getOutputLinesList().get(0);
        Assertions.assertEquals("", output.trim());
    }

    @Test
    void parseInputHeaderText() {
        parser.parseInputText("```");
        parser.parseInputText("");
        parser.parseInputText("# Testing h1 header text");
        parser.parseInputText("## Testing h2 header text");
        parser.parseInputText("### Testing h3 header text");
        parser.parseInputText("#### Testing h4 header text");
        parser.parseInputText("##### Testing h5 header text");
        parser.parseInputText("###### Testing h6 header text");
        parser.parseInputText("###This is not a header text");
        parser.parseInputText("");
        parser.parseInputText("```");

        Assertions.assertArrayEquals(new String[] { "<h1>Testing h1 header text</h1>" },
                new String[] { parser.getOutputLinesList().get(3) });
        Assertions.assertArrayEquals(new String[] { "<h2>Testing h2 header text</h2>" },
                new String[] { parser.getOutputLinesList().get(4) });
        Assertions.assertArrayEquals(new String[] { "<h3>Testing h3 header text</h3>" },
                new String[] { parser.getOutputLinesList().get(5) });
        Assertions.assertArrayEquals(new String[] { "<h4>Testing h4 header text</h4>" },
                new String[] { parser.getOutputLinesList().get(6) });
        Assertions.assertArrayEquals(new String[] { "<h5>Testing h5 header text</h5>" },
                new String[] { parser.getOutputLinesList().get(7) });
        Assertions.assertArrayEquals(new String[] { "<h6>Testing h6 header text</h6>" },
                new String[] { parser.getOutputLinesList().get(8) });
        Assertions.assertArrayEquals(new String[] { "<p>###This is not a header text</p>" },
                new String[] { parser.getOutputLinesList().get(9) });
    }

    @Test
    void parseInputTextEdgeCases() {
        parser.parseInputText("```");
        parser.parseInputText("");
        parser.parseInputText("This is not# a header");
        parser.parseInputText("");

        parser.parseInputText("This is not (a link)[https://yahoo.com]");
        parser.parseInputText("");

        parser.parseInputText("Text with #$Ym|30L$. !!");
        parser.parseInputText("");

        parser.parseInputText("Text with [Empty Link]()");
        parser.parseInputText("");
        parser.parseInputText("```");

        Assertions.assertArrayEquals(new String[] { "<p>This is not# a header</p>" },
                new String[] { parser.getOutputLinesList().get(3) });
        Assertions.assertArrayEquals(new String[] { "<p>This is not (a link)[https://yahoo.com]</p>" },
                new String[] { parser.getOutputLinesList().get(5) });
        Assertions.assertArrayEquals(new String[] { "<p>Text with #$Ym|30L$. !!</p>" },
                new String[] { parser.getOutputLinesList().get(7) });
        Assertions.assertArrayEquals(new String[] { "<p>Text with <a href=\"\">Empty Link</a></p>" },
                new String[] { parser.getOutputLinesList().get(9) });

    }

    /**
     * This method tests text with link
     */
    @Test
    void parseInputTextWithLink() {
        parser.parseInputText("```");
        parser.parseInputText("");
        parser.parseInputText("This is a text [with a link](http://yahoo.com)");
        parser.parseInputText("");
        parser.parseInputText("```");
        Assertions.assertArrayEquals(
                new String[] { "<p>This is a text <a href=\"http://yahoo.com\">with a link</a></p>" },
                new String[] { parser.getOutputLinesList().get(3) });
    }

    /**
     * This method tests text with multiple link
     */
    @Test
    void parseInputTextWithMultipleLinks() {
        parser.parseInputText("```");
        parser.parseInputText("");

        parser.parseInputText(
                "This is a [First link](http://first.com) [Second link](http://second.com) [Third link](http://third.com)");

        parser.parseInputText("");
        parser.parseInputText("```");

        Assertions.assertArrayEquals(new String[] {
                "<p>This is a <a href=\"http://first.com\">First link</a> <a href=\"http://second.com\">Second link</a> <a href=\"http://third.com\">Third link</a></p>" },
                new String[] { parser.getOutputLinesList().get(3) });
    }

    /**
     * This method tests header text with link
     */
    @Test
    void parseInputTextWithHeaderLink() {
        parser.parseInputText("```");
        parser.parseInputText("");
        parser.parseInputText("# This is a text [with a link](http://yahoo.com)");
        parser.parseInputText("");
        parser.parseInputText("```");

        Assertions.assertArrayEquals(
                new String[] { "<h1>This is a text <a href=\"http://yahoo.com\">with a link</a></h1>" },
                new String[] { parser.getOutputLinesList().get(3) });
    }

    /**
     * This method tests text with multiple lines
     */
    @Test
    void parseInputTextWithMultipleLines() {
        parser.parseInputText("```");
        parser.parseInputText("");
        parser.parseInputText("# This is first line");
        parser.parseInputText("");
        parser.parseInputText("This is a line [with a link](http://yahoo.com) ");
        parser.parseInputText("");
        parser.parseInputText("```");
        Assertions.assertArrayEquals(new String[] { "<h1>This is first line</h1>" },
                new String[] { parser.getOutputLinesList().get(3) });
        Assertions.assertArrayEquals(
                new String[] {"<p>This is a line <a href=\"http://yahoo.com\">with a link</a></p>" },
                new String[] {parser.getOutputLinesList().get(5) });
    }

}
