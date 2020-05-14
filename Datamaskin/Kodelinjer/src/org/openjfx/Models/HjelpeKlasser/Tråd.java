package org.openjfx.Models.HjelpeKlasser;

import javafx.concurrent.Task;

public class Tråd extends Task<Object> {

   public void trådKall(){
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

   }
    @Override
    protected Object call() throws Exception {
        return null;
    }


}
