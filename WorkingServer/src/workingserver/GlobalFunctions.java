package workingserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import workingserver.exceptions.ConnectionException;

/**
 * Global static functions 
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
