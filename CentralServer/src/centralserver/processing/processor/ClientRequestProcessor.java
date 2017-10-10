/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor;

import centralserver.WSList;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.Report;
import centralserver.processing.report.ReportError;
import centralserver.processing.report.ReportOk;
import centralserver.processing.request.Request;
import centralserver.processing.request.RequestOk;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Asus
 */
public class ClientRequestProcessor implements RequestProcessor {
    private static int _counter = 0;
    private RequestOk _request;
    private WSList _list;

    @Override
    public Report process(Request request, WSList list) throws ConnectionException {
        try {
            _request = (RequestOk) request;
            _list = list;
            return process();
        }
        catch (ClassCastException e) {
            // should never happen
            e.printStackTrace();
            return null;
        }
    }
    
    private Report process() throws ConnectionException {
        switch (_request.getCommand()) {
            case "LST":
                System.out.println("List request: " + _request.getNameAdress() + " " + _request.getPort());
                String[] pTCs = _list.getPTCs();
                if (pTCs.length == 0)
                    return new ReportError(_request.getNameAdress(), _request.getIP(), _request.getPort(), "FPT EOF");
                else
                    return new ReportOk(_request.getNameAdress(), _request.getIP(), _request.getPort(), _request.getCommand(), pTCs);
            case "REQ":
                return requestCmd();
            case "ERR":
                return new ReportError(_request.getNameAdress(), _request.getIP(), _request.getPort(), "ERR");
            default:
                ; // Should never happen
        }
        return new ReportError(_request.getNameAdress(), _request.getIP(), _request.getPort(), "ERR");
    }
        
    private Report requestCmd() throws ConnectionException {
        String fileName;
        try {
            fileName = "input_files/" + intToString(_counter, 5) + ".txt";
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(fileName)));
            out.print(_request.getFile());
            out.close();
            _counter++;
            
        }
        catch (IOException e) {
            throw new ConnectionException("");
        }
        
        // Get IPs from list
        String[] iPs = _list.getIPs();
        if (iPs == null || iPs.length == 0)
            return new ReportError(_request.getNameAdress(), _request.getIP(), _request.getPort(), "REP EOF");
        
        
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
