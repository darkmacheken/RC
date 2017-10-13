/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import workingserver.exceptions.ConnectionException;

/**
 *
 *  
 */
public final class GlobalFunctions {

    /**
     *
     * @param path
     * @param file
     * @throws ConnectionException
     */
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
}
