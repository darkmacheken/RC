/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.protocols;

import centralserver.processing.ClientRequestErrorProcessor;
import centralserver.processing.ClientRequestProcessor;
import centralserver.processing.Request;
import centralserver.processing.RequestError;
import java.io.StringReader;
import static java.lang.Integer.max;

/**
 * Protocol beteween CS-Client, creates de messages to be sent and
 * confirms the protocol.
 * @author Pedro Daniel
 */
public class ProtocolCSClient {
    //Client Request identifier
    private final String _nameAdress;
    private final String _iP;
    private final int _port;

    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     */
    public ProtocolCSClient(String nameAdress, String iP, int port) {
        _nameAdress = nameAdress;
        _iP = iP;
        _port = port;
    }
    

    
    /**
     * Receive the string from the connection 
     * @param sentence
     * @return Request
     */
    public Request receive(String sentence){
        //remove last char from sentence (it should be '\n' from protocol)
        sentence = sentence.substring(0, max(0,sentence.length()-1));
        
        //split sentence by space into array
        String[] splitedCommand = sentence.split(" ",1);
        
        if(splitedCommand.length == 0){
            return new RequestError(_nameAdress, _iP, _port, "ERR", new ClientRequestErrorProcessor());
        }
        else if(splitedCommand[0] == "LST"){
            if( splitedCommand.length == 1)
                return new Request(_nameAdress, _iP, _port, "LST", new ClientRequestProcessor());
            else
                return new RequestError(_nameAdress, _iP, _port, "FPT ERR", new ClientRequestErrorProcessor());
        }
        else if(splitedCommand[0] == "REQ" && splitedCommand.length == 2){
            String[] commandArguments = splitedCommand[1].split(" ", 2);
            
            if(commandArguments.length != 3){
                return new RequestError(_nameAdress, _iP, _port, "REP ERR", new ClientRequestErrorProcessor());
            }
            else{
                return new Request(_nameAdress, _iP, _port,
                                   "REQ", new String[]{commandArguments[0]}, Integer.parseInt(commandArguments[1]),
                                   new StringReader(commandArguments[2]),
                                   new ClientRequestProcessor());
            }
        }
        else{
            return new RequestError(_nameAdress, _iP, _port, "ERR", new ClientRequestErrorProcessor());
        }
    }
    
}
