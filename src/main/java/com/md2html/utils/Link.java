package com.md2html.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 
 * Link parses markdown link and convert to html link
 */

public class Link {

    List<String> linksMatchList = new ArrayList<>();
    String linkText, link;
    boolean flag = false;

    public boolean checkLinkPattern(String inputText) {
        Pattern pattern = Pattern.compile("\\[.*?\\]\\(.*?\\)");
        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find()) {// Finds Matching Pattern in String
            linksMatchList.add(matcher.group(0));// Fetching Group from String
        }
        if (!linksMatchList.isEmpty()) {
            return true;
        }
        return false;
    }

    public void extractLinkText() {
        String text = linksMatchList.get(0);
        linkText = substringBetween(text, "[", "]");
        link = substringBetween(text, "(", ")");
        linksMatchList.remove(0);
    }

    public static String substringBetween(final String str, final String open, final String close) {
        final int start = str.indexOf(open);
        if (start != -1) {
            final int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    public String addLinkTag(String textAfterRemovingMarkDown) {
        return textAfterRemovingMarkDown.replaceFirst("\\[.*?\\]\\(.*?\\)",
                "<a href=\"" + link + "\">" + linkText + "</a>");
    }

}
