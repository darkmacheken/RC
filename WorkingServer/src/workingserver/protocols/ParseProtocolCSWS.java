/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.protocols;

import workingserver.connection.ConnectionTCP;
import workingserver.exceptions.ConnectionException;

/**
 *
 * @author Pedro Daniel
 */
public class ParseProtocolCSWS {
    private ConnectionTCP _connection;

    public ParseProtocolCSWS(ConnectionTCP _connection) {
        this._connection = _connection;
    }          
    

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
