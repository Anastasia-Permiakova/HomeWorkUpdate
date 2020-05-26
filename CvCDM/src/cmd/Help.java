/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

public class Help extends Command{

    @Override
    public String execute(File actualDir) {
        
        String help = "HELP\n" 
                + String.format("%-7s %s\n", "dir", "Display list of files and folders")
                + String.format("%-7s %s\n", "cd [folder name]", "Change directory - move to a specific folder")
                + String.format("%-7s %s\n", "mkdir [folder name]", "Create new folders")
                + String.format("%-7s %s\n", "rename [nameFrom] [nameTo]", "Display help")
                + String.format("%-7s %s\n", "help", "Display help")
                + String.format("%-7s %s\n", "exit", "Exit cmd");
        return help;
    }
    
}
