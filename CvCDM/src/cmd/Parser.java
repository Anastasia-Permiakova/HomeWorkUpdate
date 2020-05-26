/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

public class Parser {
    public static Command parse(String line){

        try{
        String[] p = line.split(" +");
        if(p.length == 0){
            
        }
        
        char first = Character.toUpperCase(p[0].charAt(0));
        String name = Command.COMMAND_PACKAGE + "." + first + p[0].substring(1);
        try{
        Class c = Class.forName(name);
        Command command = (Command) c.newInstance();
        command.setParams(p);
        return command;
        }catch (Exception e){
            throw new RuntimeException("Nepodarilo se prikaz naparsovat");
        }
        }catch (Exception f){
            throw new RuntimeException("");
        }
        
    }
}
