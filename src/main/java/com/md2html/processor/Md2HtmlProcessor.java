package com.md2html.processor;

import com.md2html.utils.OutPutFileWriter;
import com.md2html.utils.Paragraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Md2HtmlProcessor parse the input markdown text, process and
 *   output to file. Checks for ```, # 
 * 
 */
public class Md2HtmlProcessor {

    List<String> outputLinesList = new ArrayList<>(); // Storing all the final formatted text
    // in list ready to put in output file
    StringBuilder sb = new StringBuilder();
    int level;
    boolean isStartOfPara = true;
    Paragraph para;
    String outputFileName;

    public Md2HtmlProcessor(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * parseInputText
     * 
     * @param input line
     */
    public void parseInputText(String line) {
        if (line != null) {
            line = line.trim();
        }

        if (line != null && line.startsWith("```")) {
            if (sb.length() > 0) {
                para = new Paragraph(sb.toString(), level);
                outputLinesList.add(para.toHtml());
            }
            outputLinesList.add("\n");
            outputLinesList.add("```");
            sb = new StringBuilder();
        }

        else if (line != null && line.startsWith("#")) {

            int lvl;
            for (lvl = 0; lvl < line.length() && line.charAt(lvl) == '#'; ++lvl) {
                ;
            }
            level = Character.isWhitespace(line.charAt(lvl)) ? lvl : 0;
            isStartOfPara = false;
            String textAfterRemovingMarkDown = removeMarkDown(line, level);
            para = new Paragraph(level > 0 ? textAfterRemovingMarkDown : line, level);
            outputLinesList.add(para.toHtml());
        } else if (line != null && line.length() > 0 && isStartOfPara) {
            sb.append(line);
            isStartOfPara = false;
        } else if (line != null && line.length() > 0 && !isStartOfPara) {
            sb.append("\n");
            sb.append(line);
        } else {
            if (sb.length() > 0) {
                para = new Paragraph(sb.toString(), level);
                outputLinesList.add(para.toHtml());
            }
            isStartOfPara = true;
            sb = new StringBuilder();
            outputLinesList.add("\n");
            level = 0;
        }

    }

    /**
     * @throws IOException
     */
    public void addFormattedTextListToFile() throws IOException {
        OutPutFileWriter.createFile(outputLinesList, outputFileName);
    }

    /**
     * @param text
     * @param level
     * @return String
     */
    public static String removeMarkDown(String text, int level) {
        StringBuilder markDownToRemove = new StringBuilder();
        if (level > 0) {
            for (int i = 0; i < level; i++) {
                markDownToRemove.append("#");
            }
            markDownToRemove.append(' ');
        }

        text = text.replaceFirst(markDownToRemove.toString(), "");
        return text;
    }

    /**
     * @return List<String>
     */
    public List<String> getOutputLinesList() {
        return outputLinesList;
    }

    /**
     * @param outputLinesList
     */
    public void setOutputLinesList(List<String> outputLinesList) {
        this.outputLinesList = outputLinesList;
    }

    /**
     * @return String
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @param outputFileName
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

}
