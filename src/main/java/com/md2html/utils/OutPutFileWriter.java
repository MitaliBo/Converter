package com.md2html.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 * 
 * OutPutFileWriter writes the list of tokens to the output file.
 */

public class OutPutFileWriter {

    public static void createFile(List<String> textToAddList, String outputFileName) throws IOException {
        FileWriter writer = new FileWriter(outputFileName);
        for (String string : textToAddList) {
            writer.write(string + System.lineSeparator());
        }
        writer.close();
    }
}
