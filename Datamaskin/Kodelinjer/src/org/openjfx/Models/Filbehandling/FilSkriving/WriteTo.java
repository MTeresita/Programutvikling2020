package org.openjfx.Models.Filbehandling.FilSkriving;

import org.openjfx.Models.Interfaces.SkrivTilFil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteTo implements SkrivTilFil {

    @Override
    public void writer(Object object, String path, boolean append) throws IOException {
        PrintWriter writer = null;

        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file, append); //False = overskriver
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(object);
            printWriter.close();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void writeToCSVFile(SkrivTilFil writer, Object obj, String path, boolean append) {
        //generell metode som skriver til csv fil.
        try {
            writer.writer(obj, path, append);

        } catch (IOException e) {
            System.err.println("Could not write to file. Cause: " + e.getCause());
        }
    }


}


