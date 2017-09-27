/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

public class ProtocolClientCS{
    
    public ProtocolClientCS(){}
    
    public String sendProtocol(String sentence){
        String[]splitedSentence = sentence.split(" ");
                
        if(splitedSentence.length==0)
            throw new UnknownCommandException("");
        
        switch (splitedSentence[0]){
            case "exit":
                throw new ExitCommandException();
            case "list":
                return "LST\n";
            case "request":
                
                return "";
            default:
                throw new UnknownCommandException(splitedSentence[0]);
      
            
        }
    }
}
