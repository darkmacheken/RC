/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import centralserver.exceptions.ConnectionException;

/**
 *
 * @author duartegalvao
 */
public final class GlobalFunctions {
    public static void writeToFile(String path, String file) throws ConnectionException {
        try {
            File filepath = new File(path);
            filepath.getParentFile().mkdirs();
            
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(filepath)));
            out.print(file);
            out.close();
            
            
        }
        catch (IOException e) {
            throw new ConnectionException(Constants.FILE_CNTWRT);
        }
    }
    
    public static String stringJoin(String[] fragments) {
        String res = "";
        
        for (String fragment : fragments)
            res += fragment;
        
        return res;
    }
}
