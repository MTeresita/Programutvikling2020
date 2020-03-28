package org.openjfx.Models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileReaderHelper {
    public static ArrayList<String> readFromFiles(String path){
        ArrayList<String> nextLine = new ArrayList<>();

        String line;
        try(RandomAccessFile reader = new RandomAccessFile(path, "r")) {
            while ((line = reader.readLine()) != null){
                nextLine.add(line);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Could not find the specified file");
        }
        catch (IOException e) {
            System.err.println("Could not read the specified file. Cause: " + e.getCause());
        }
        return nextLine;
    }
}
