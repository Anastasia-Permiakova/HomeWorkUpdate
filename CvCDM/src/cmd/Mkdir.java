/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

public class Mkdir extends Command {

    @Override
    public String execute(File actualDir) {
        File newFile = new File(params[1]);
        if (!newFile.exists()) {
            try {
                newFile.mkdir();
            } catch (Exception e) {
                throw new RuntimeException("There is a problem with creating the file");
            }
        }

        return "DIR created";

    }

}
