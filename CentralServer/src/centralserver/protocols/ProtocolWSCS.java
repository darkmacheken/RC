/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.protocols;

import centralserver.ConnectAddress;
import centralserver.WSList;
import static java.lang.Integer.max;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pedro Daniel
 */
public class ProtocolWSCS {
    private WSList _list;

    public ProtocolWSCS(WSList _list) {
        this._list = _list;
    }
        
    public String process(String received){
        //remove last char from sentence (it should be '\n' from protocol)
        received = received.substring(0, max(0,received.length()-1));
        
        String[] splitedReceived = received.split(" ");
        int splitedReceivedLength = splitedReceived.length;
        
        if(splitedReceived.length == 0)
            return "ERR\n";
        else if(splitedReceived[0].equals("REG")){
            if(splitedReceived.length < 4)
                return "RAK ERR\n";
            
            //check portWS
            int portWS;
            try{
                portWS = Integer.parseInt(splitedReceived[splitedReceivedLength - 1]);
            }
            catch(NumberFormatException e){
                return "RAK ERR\n";
            }
            
            //get ip
            String ipWS = splitedReceived[splitedReceivedLength - 2];
            
            //get PTC's and check each one if it has 3 chars
            List<String> ptcs =  new LinkedList<String>();
            
            for(int i = 1; i < splitedReceivedLength - 2; i++){
                String ptc = splitedReceived[i];
                if(ptc.length() != 3)
                    return "RAK ERR\n";
                ptcs.add(ptc);
            }
            
            //Transform list ptcs do array
            String[] ptcsArray = (String[]) ptcs.toArray();
            
            //create ConnectAddress
            ConnectAddress addressWS = new ConnectAddress(ipWS, portWS);
            
            //add addressWS and ptcsArray to the public list
            _list.addIP(ptcsArray, addressWS);
            
            return "RAK OK\n";
        }
        else if(splitedReceived[0].equals("UNR")){
            if(splitedReceivedLength != 3)
                return "UAK ERR\n";
            
            //check portWS
            int portWS;
            try{
                portWS = Integer.parseInt(splitedReceived[2]);
            }
            catch(NumberFormatException e){
                return "RAK ERR\n";
            }
            
            //get ip
            String ipWS = splitedReceived[1];
            
            //create ConnectAddress
            ConnectAddress addressWS = new ConnectAddress(ipWS, portWS);
            
            //remove addressWS
            _list.removeIP(addressWS);
            
            return "UAK OK\n";
        }
            
        return "ERR\n";
    }
}
