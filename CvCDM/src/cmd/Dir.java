/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;
import java.util.Date;

public class Dir extends Command{

    @Override
    public String execute(File actualDir) {
        File[] files;
        if(params.length == 1){
            files = actualDir.listFiles();
            return dirToString(files);
        }
        return null;
    }

    private String dirToString(File[] files) {
        StringBuilder sb = new StringBuilder("");
        for (File file : files) {
            if(file.isDirectory()){
                sb.append(String.format("%s%n",file.getName()));
            }else{
                sb.append(String.format("%-20s%6d", file.getName(), file.length())); 
                sb.append(new Date(file.lastModified())).append("\n");
            }
            
        }
        String st = sb.toString();
        return st;
    }
    
}
