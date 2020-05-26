/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.util.Scanner;


public class CmdUI {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CmdInterface cmd = new CmdEditor();
        
        String line;
        while(cmd.isRunning()){
            System.out.print(cmd.getActualDir() + "$ ");
            line = sc.nextLine();
            try {
                String outputFromParse = cmd.parseAndExecute(line);
                System.out.println(outputFromParse);
            }catch(RuntimeException e){
                System.out.print(e.getMessage());
            }
        }

    }
}


