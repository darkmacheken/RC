package user.protocols;

import user.ConnectionTCP;
import user.exceptions.ConnectionException;

/**
 * Reads the quantity of bytes according to the protocol (from CS to Client)
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
        String received;
        String line;
        int size, sizeCount;
        
        line = _connection.receiveLine();
        String[] lineSplit = line.split(" ", 4);
        
        if (lineSplit[0].equals("FPT"))
            return line;
        else if (lineSplit[0].equals("REP")) {
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
                return received;
            }
            catch(NumberFormatException e){
                return line;
            }
        }
        else {
            return line;
        }
    }
}
