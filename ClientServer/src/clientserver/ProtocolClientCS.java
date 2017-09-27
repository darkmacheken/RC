package clientserver;

public class ProtocolClientCS{
    
    public ProtocolClientCS(){}
    
    public String sendProtocol(String sentence){
        String[]splitedSentence = sentence.split(" ");
                
        if(splitedSentence.length==0)
            throw new CommandException("");
        
        switch (splitedSentence[0]){
            case "exit":
                throw new ExitCommandException();
            case "list":
                return "LST\n";
            case "request":
                
                return "";
            default:
                throw new CommandException(splitedSentence[0]);
      
            
        }
    }
}
