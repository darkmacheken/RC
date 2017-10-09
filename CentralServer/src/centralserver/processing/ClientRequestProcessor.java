/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

import centralserver.WSList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Asus
 */
public class ClientRequestProcessor implements RequestProcessor {
    private RequestOk _request;
    private WSList _list;

    @Override
    public Report process(RequestOk request, WSList list) {
        _request = request;
        _list = list;
        
        switch (_request.getCommand()) {
            case "LST":
                String[] ptc = _list.getPTC();
                return new Report(_request.getCommand(), ptc);
            case "REQ":
                
                return new Report(_request.getCommand(), null, );
            case "ERR":
                return new Report(_request.getCommand(), null, null, 0, null);
            default:
                ; // Should never happen
        }
    }
    
    private Report requestCmd() {
        try {
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(finalNameFile)));
            out.print(data2);
            System.out.println("received file " + finalNameFile + "\n\t" + size2 + " Bytes");
            out.close();
        }
        catch (IOException e) {
            throw new ConnectionException("");
        }
        _request.getPTCs();
    }
    
}
