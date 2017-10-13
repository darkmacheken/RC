package centralserver.protocols;

import centralserver.connection.ConnectionTCP;
import centralserver.exceptions.ConnectionException;

/**
 * Reads the quantity of bytes according to the protocol (from Client to CS)
 */
public class ParseProtocolClientCS {
    ConnectionTCP _connection;
    
    /**
     *
     * @param connection
     */
    public ParseProtocolClientCS(ConnectionTCP connection) {
        _connection = connection;
    }
    
    /**
     *
     * @return
     * @throws ConnectionException
     */
    public String parse() throws ConnectionException {
        String received = "";
        String line;
        int size, sizeCount;
        
        line = _connection.receiveLine();
        String[] lineSplit = line.split(" ", 4);
        
        if (lineSplit[0].equals("LST\n"))
            return line;
        else if (lineSplit[0].equals("REQ")) {
            if(lineSplit.length != 4)
                return line;
                
            try {
                size = Integer.parseInt(lineSplit[2]);
                    
                received = line;                
                
                sizeCount = lineSplit[3].length();
                while(sizeCount <= size){
                    line = _connection.receiveLine();
                    received+= line;
                    sizeCount+= line.length();
                }
            }
            catch(NumberFormatException e){
                return line;
            }
        }
        
        return received;
    }
}
