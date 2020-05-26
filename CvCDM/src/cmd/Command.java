/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

public abstract class Command {
    public static String COMMAND_PACKAGE = "cmd";
    
    protected String[] params; //dir | -e | .java
    
    public void setParams(String[] params){
        this.params = new String[params.length];
        System.arraycopy(params, 0, this.params, 0, params.length);
    }
    
    public abstract String execute(File actualDir);

}
