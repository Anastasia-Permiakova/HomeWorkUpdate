/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

public class Rename extends Command{

    @Override
    public String execute(File actualDir) {
        File f = new File(params[1]);
        File newF = new File(params[2]);
        f.renameTo(newF);
        return "File renamed";
    }
    
}
