package org.openjfx.Models.Filbehandling.FilSkriving;

import org.openjfx.Models.Interfaces.WriteToFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteTo implements WriteToFile{

    @Override
    public void writer(Object object, String path) throws IOException {
        PrintWriter writer = null;

        try {
            FileWriter fileWriter = new FileWriter(path, true); //True så den ikke overkjøres
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(object);
            printWriter.close();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void writeToCSVFile(WriteToFile writer, Object obj, String path) {
        //generell metode som skriver ti csv fil.
        try {
            writer.writer(obj, path);
        } catch (IOException e) {
            System.err.println("Could not write to file. Cause: " + e.getCause());
            //showAlertBox(Alert.AlertType.ERROR, "Kan ikke lese fil" + e.getCause(),"Lesefeil");
        }
    }


}


