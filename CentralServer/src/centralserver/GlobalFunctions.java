package centralserver;

import centralserver.exceptions.ConnectionException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    
    /**
     *
     * @param fragments
     * @return
     */
    public static String stringJoin(String[] fragments) {
        String res = "";
        
        for (String fragment : fragments)
            res += fragment;
        
        return res;
    }
}
