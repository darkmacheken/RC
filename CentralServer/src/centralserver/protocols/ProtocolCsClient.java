/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.protocols;

import centralserver.processing.ClientRequestProcessor;
import centralserver.processing.Request;
import static java.lang.Integer.max;

/**
 * Protocol beteween CS-Client, creates de messages to be sent and
 * confirms the protocol.
 * @author Pedro Daniel
 */
public class ProtocolCSClient {

    /**
     * Constructor default
     */
    public ProtocolCSClient(){}
    
    /**
     * Receive the string from the connection 
     * @param sentence
     * @return Request
     */
    public Request receive(String sentence){
        //remove last char from sentence (it should be '\n' from protocol)
        sentence = sentence.substring(0, max(0,sentence.length()-1));
        
        //split sentence by space into array
        String[] splitedSentence = sentence.split(" ");
        
        if(splitedSentence.length == 0){
            return new Request(new ClientRequestProcessor(), );
        }      
        else if(splitedSentence)
    }
    
}
