package com.md2html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import com.md2html.processor.Md2HtmlProcessor;
import java.io.IOException;

public class Markdown2HtmlConverter {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = null;

        try {
            if (args.length < 2) {
                System.out.println("Please provide input and output file paths as arguments..");
                System.exit(1);
            }
            File file = new File(args[0]);
            Md2HtmlProcessor parseText = new Md2HtmlProcessor(args[1]);
            bufferedReader = new BufferedReader(new FileReader(file));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                parseText.parseInputText(string);
            }
            parseText.addFormattedTextListToFile();
            System.out.println("Markdown to Html Conversion Successful!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception While Converting Markdown to Html:" + e.toString());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
