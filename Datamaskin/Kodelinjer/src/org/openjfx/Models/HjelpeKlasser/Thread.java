package org.openjfx.Models.HjelpeKlasser;

import javafx.concurrent.Task;

public class Thread extends Task<Object> {

   public void threadKall(){
        try{
            java.lang.Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

   }
    @Override
    protected Object call() throws Exception {
        return null;
    }


}
