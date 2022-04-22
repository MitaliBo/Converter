package com.md2html.utils;

/*
 * 
 * Paragraph parses the markdown paragraph to html paragraph
 * 
 */
public class Paragraph {
    private final String markup;
    private final int level;

    public Paragraph(String markup, int level) {
        this.markup = markup;
        this.level = level;
    }

    /**
     * toHtml checks for link, paragraphs, multiple lines,header in paragraph
     * and converts them to corresponding html string
     * 
     * @return String
     */
    public String toHtml() {
        String markupLink = markup;
        Link link = new Link();

        boolean isLinkPresent = link.checkLinkPattern(markupLink);

        if (isLinkPresent) {
            // To handle multiple links on same line
            while (!link.linksMatchList.isEmpty()) {
                link.extractLinkText();
                markupLink = link.addLinkTag(markupLink);
            }
        }

        StringBuilder sb = new StringBuilder();
        String tag = level > 0 ? "h" + level : "p";
        sb.append("<").append(tag).append(">").append(markupLink).append("</").append(tag).append(">");
        return sb.toString();
    }
}
