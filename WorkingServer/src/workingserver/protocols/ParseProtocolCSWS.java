package workingserver.protocols;

import workingserver.connection.ConnectionTCP;
import workingserver.exceptions.ConnectionException;

/**
 * Reads the quantity of bytes according to the protocol (from CS to WS)
 */
public class ParseProtocolCSWS {
    private ConnectionTCP _connection;

    /**
     *
     * @param _connection
     */
    public ParseProtocolCSWS(ConnectionTCP _connection) {
        this._connection = _connection;
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

        String[] lineSplit = line.split(" ", 5);
        
        if (lineSplit[0].equals("WRQ")){
            if(lineSplit.length != 5)
                return line;
            
            try {
                size = Integer.parseInt(lineSplit[3]);
                    
                received = line;                
                
                sizeCount = lineSplit[4].length();
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
        
        return line;
            
    }
    
}
