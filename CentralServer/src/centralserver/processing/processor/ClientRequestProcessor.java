/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor;

import centralserver.ConnectAddress;
import centralserver.GlobalFunctions;
import centralserver.WSList;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.processor.outputbuild.AddClientOutputBuilder;
import centralserver.processing.processor.outputbuild.ClientOutputBuilder;
import centralserver.processing.processor.outputbuild.ConcatClientOutputBuilder;
import centralserver.processing.processor.outputbuild.LongestClientOutputBuilder;
import centralserver.processing.report.Report;
import centralserver.processing.report.ReportError;
import centralserver.processing.report.ReportOk;
import centralserver.processing.request.Request;
import centralserver.processing.request.RequestOk;
import centralserver.processing.request.RequestToWS;
import java.util.Arrays;
import java.util.List;

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
        if (_request.getCommand().equals("LST")) {
                System.out.println("List request: " + _request.getNameAdress() + " " + _request.getPort());
                List<String> pTCs = _list.getPTCs();
                if (pTCs.isEmpty())
                    return reportError("FPT EOF");
                else{
                    String[] ptcs = new String[pTCs.size()];
                    pTCs.toArray(ptcs);
                    return new ReportOk(_request.getNameAdress(), _request.getIP(), _request.getPort(), _request.getCommand(), ptcs);
                }
        }
        else if (_request.getCommand().equals("REQ")) {
            System.out.println(_request.getPTC() + " request: " + _request.getNameAdress() + " " + _request.getPort());
            return requestCmd();
        }
        else if (_request.getCommand().equals("ERR")) {
            return reportError("ERR");
        }
        else {
            return reportError("ERR");
        }
    }
        
    private Report requestCmd() throws ConnectionException {
        String fileName = intToString(_counter, 5); // nnnnn.txt
        String file = _request.getFile();
        
        GlobalFunctions.writeToFile("input_files/" + fileName + ".txt", file);
        _counter++;
        
        // Get IPs from list
        ConnectAddress[] iPs = _list.getIPs(_request.getPTC());
        if (iPs == null || iPs.length == 0)
            return reportError("REP EOF");

        String[] fileLines = file.split("\r\n|\r|\n");
        int numLines = fileLines.length;
        int numRequests = iPs.length < numLines ? iPs.length : numLines;
        RequestToWS[] requests = new RequestToWS[numRequests];
        int curLine = 0;
        
        for (int i = 0; i < numRequests; i++) {
            // Divide work equally between servers
            int reqLines = numLines / numRequests;

            // Last server gets remainder
            if (i == iPs.length - 1 && numLines % iPs.length != 0)
                reqLines += numLines % iPs.length;
            
            String[] wSFragment = Arrays.copyOfRange(fileLines, curLine, curLine + reqLines);
            
            requests[i] = new RequestToWS(iPs[i].getIp(), iPs[i].getPort(),
                    fileName + intToString(i, 3) + ".txt",
                    _request.getPTC(),
                    String.join("\n", wSFragment),
                    new WorkingServerRequestProcessor());
            curLine += reqLines;
            requests[i].processSend();
        }
        
        ReportOk[] receivedReports = new ReportOk[numRequests];
        
        for (int i = 0; i < requests.length; i++) {
            try {
                receivedReports[i] = (ReportOk) requests[i].processReceive();
                GlobalFunctions.writeToFile("output_files/" + requests[i].getFileName() + ".txt", file);
            }
            catch (ClassCastException e) {
                return reportError("REP EOF");
            }
        }
        
        ClientOutputBuilder cob;
        
        if (_request.getPTC().equals("WCT"))
            cob = new AddClientOutputBuilder(fileName, receivedReports);
        else if (_request.getPTC().equals("FLW"))
            cob = new LongestClientOutputBuilder(fileName, receivedReports);
        else if (_request.getPTC().equals("UPP"))
            cob = new ConcatClientOutputBuilder(fileName, receivedReports);
        else if (_request.getPTC().equals("LOW"))
            cob = new ConcatClientOutputBuilder(fileName, receivedReports);
        else
            return reportError("ERR"); // should not happen
        
        cob.build();
        cob.saveFile();
        if (!cob.checkRT())
            return reportError("ERR");
        
        return new ReportOk(_request.getNameAdress(), _request.getIP(), _request.getPort(),
                _request.getCommand(), new String[]{_request.getPTC()},
                cob.getFile(), 
                cob.getFile().length(), 
                cob.getRT());
    }
    
    private ReportError reportError(String error) {
        return new ReportError(_request.getNameAdress(), _request.getIP(), _request.getPort(), error);
    }
    
    private String intToString(Integer num, int digits) {
        String res = num.toString();
        int zeroes = digits - res.length();
        for (int i = 0; i < zeroes; i++)
            res = "0" + res;
        return res;
    }
    
}
