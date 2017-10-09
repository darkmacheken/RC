/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

import centralserver.WSList;
import centralserver.exceptions.ConnectionException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Asus
 */
public class ClientRequestProcessor implements RequestProcessor {
    private Request _request;
    private WSList _list;
    private static int _counter = 0;

    @Override
    public Report process(Request request, WSList list) {
        _request = request;
        _list = list;
        
        switch (_request.getCommand()) {
            case "LST":
                System.out.println("List request: " + _request.getNameAdress() + " " + _request.getPort());
                String[] pTCs = _list.getPTCs();
                return new Report(_request.getCommand(), pTCs);
            case "REQ":
                try {
                    ;
                }
                return new Report(_request.getCommand(), null, );
            case "ERR":
                return new Report(_request.getCommand(), null, null, 0, null);
            default:
                ; // Should never happen
        }
    }
    
    private Report requestCmd() throws ConnectionException {
        try {
            String fileName = intToString(_counter, 5);
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(fileName)));
            out.print(_request.getFile());
            out.close();
        }
        catch (IOException e) {
            throw new ConnectionException("");
        }
        _request.getPTCs();
    }
    
    private String intToString(int num, int digits) {
        StringBuilder s = new StringBuilder(digits);
        int zeroes = digits - (int) (Math.log(num) / Math.log(10)) - 1; 
        for (int i = 0; i < zeroes; i++) {
            s.append("0");
        }
        return s.append(num).toString();
    }
    
}
