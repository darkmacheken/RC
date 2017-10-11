/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.protocols;

import centralserver.processing.report.Report;
import centralserver.processing.report.ReportError;
import centralserver.processing.report.ReportOk;
import centralserver.processing.request.RequestToWS;
import static java.lang.Integer.max;

/**
 *
 * @author duartegalvao
 */
public class ProtocolCSWS {

    private final String _nameAdress;
    private final String _iP;
    private final int _port;

    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     */
    public ProtocolCSWS(String nameAdress, String iP, int port) {
        _nameAdress = nameAdress;
        _iP = iP;
        _port = port;
    }

    public Report receive(String sentence) {
        //remove last char from sentence (it should be '\n' from protocol)
        sentence = sentence.substring(0, max(0, sentence.length() - 1));

        //split sentence by space into array
        String[] splitedCommand = sentence.split(" ", 2);

        if (splitedCommand.length == 0) {
            return new ReportError(_nameAdress, _iP, _port, "ERR");
        }
        if (splitedCommand[0].equals("ERR")) {
            return new ReportError(_nameAdress, _iP, _port, "ERR");
        }
        else if (splitedCommand[0].equals("REP")) {
            String[] commandArguments = splitedCommand[1].split(" ", 3);
            if (commandArguments[0].equals("EOF")) {
                return new ReportError(_nameAdress, _iP, _port, "REP EOF");
            }
            else if (commandArguments[0].equals("ERR")) {
                System.out.println("received rep err");
                return new ReportError(_nameAdress, _iP, _port, "REP ERR");
            }
            else if (commandArguments[0].equals("F")) {

            }
            else if (commandArguments[0].equals("R")) {
                if (commandArguments.length != 3) {
                    return new ReportError(_nameAdress, _iP, _port, "ERR");
                }
                else {
                    char type = commandArguments[0].charAt(0);
                    int size;
                    try {
                        size = Integer.parseInt(commandArguments[1]);
                    }
                    catch (NumberFormatException e) {
                        return new ReportError(_nameAdress, _iP, _port, "ERR");
                    }
                    String file = commandArguments[2];

                    if (size == file.length()) {
                        return new ReportOk(_nameAdress, _iP, _port, file, size, type);
                    }
                }
            }
            else {
                return new ReportError(_nameAdress, _iP, _port, "ERR");
            }
        }
        return new ReportError(_nameAdress, _iP, _port, "ERR");
    }

    /**
     *
     * @param request
     * @return
     */
    public String send(RequestToWS request) {
        int size = request.getFile().length();
        return "WRQ" + request.getpTC() + request.getFileName() + size + request.getFile() + "\n";
    }
}
