/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

public class Cd extends Command{

    @Override
    public String execute(File actualDir) {
        File directory = new File(params[1]).getAbsoluteFile();
        if(!params[1].equals("..") && directory.exists()){            
            CmdEditor.changeActualDir(directory);
            System.out.println("Prvni");
            return "Directory changed";
            
        }
        else if(params[1].equals("..")){
            System.out.println("Druha");
            String oldDir = actualDir.getAbsolutePath();
            String[] newDir = oldDir.split("\\");
            
            String finalDir = newDir[newDir.length-2];
            System.out.println(finalDir);
            File f = new File(finalDir).getAbsoluteFile();
            CmdEditor.changeActualDir(f);
            
            return "Directory changed";
            
        }
        else{
            return "Directory does not exist";
        }
        
            
        
    }
    
}
