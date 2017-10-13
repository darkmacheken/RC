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
        sentence = sentence.substring(0, 0 > sentence.length()-1 ? 0 : sentence.length()-1);

        //split sentence by space into array
        String[] splitedCommand = sentence.split(" ", 2);

        if (splitedCommand.length == 0) {
            System.out.println("WS " + _nameAdress + " " + _port + ": ERR - nothing received.");
            return new ReportError(_nameAdress, _iP, _port, "ERR");
        }
        if (splitedCommand[0].equals("ERR")) {
            System.out.println("WS " + _nameAdress + " " + _port + ": ERR - unexpected protocol from CS.");
            return new ReportError(_nameAdress, _iP, _port, "ERR");
        }
        else if (splitedCommand[0].equals("REP")) {
            String[] commandArguments = splitedCommand[1].split(" ", 3);
            if (commandArguments[0].equals("EOF")) {
                System.out.println("WS " + _nameAdress + " " + _port + ": REP EOF - request cannot be answered by WS.");
                return new ReportError(_nameAdress, _iP, _port, "REP EOF");
            }
            else if (commandArguments[0].equals("ERR")) {
                System.out.println("WS " + _nameAdress + " " + _port + ": REP ERR - message received doesnt follow protocol.");
                return new ReportError(_nameAdress, _iP, _port, "REP ERR");
            }
            else if (commandArguments[0].equals("F")) {
                 if (commandArguments.length != 3) {
                    System.out.println("WS " + _nameAdress + " " + _port + ": REP ERR - message received doesnt follow protocol.");
                    return new ReportError(_nameAdress, _iP, _port, "ERR");
                }
                 else {
                    char type = commandArguments[0].charAt(0);
                    int size;
                    try {
                        size = Integer.parseInt(commandArguments[1]);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("WS " + _nameAdress + " " + _port + ": ERR - size sent is not a number");
                        return new ReportError(_nameAdress, _iP, _port, "ERR");
                    }
                    String file = commandArguments[2];
                    if (size == file.length()) {
                        System.out.println("WS " + _nameAdress + " " + _port + ": File Type: " + type + "\n\t" + size + " bytes received.");
                        return new ReportOk(_nameAdress, _iP, _port, file, size, type);
                    }
                }
            }
            else if (commandArguments[0].equals("R")) {
                if (commandArguments.length != 3) {
                    System.out.println("WS " + _nameAdress + " " + _port + ": REP ERR - message received doesnt follow protocol.");
                    return new ReportError(_nameAdress, _iP, _port, "ERR");
                }
                else {
                    char type = commandArguments[0].charAt(0);
                    int size;
                    try {
                        size = Integer.parseInt(commandArguments[1]);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("WS " + _nameAdress + " " + _port + ": ERR - size sent is not a number");
                        return new ReportError(_nameAdress, _iP, _port, "ERR");
                    }
                    String file = commandArguments[2];

                    if (size == file.length()) {
                        System.out.println("WS " + _nameAdress + " " + _port + ": File Type: " + type + "\n\t" + size + " bytes received.");
                        return new ReportOk(_nameAdress, _iP, _port, file, size, type);
                    }
                }
            }
            else {
                System.out.println("WS " + _nameAdress + " " + _port + ": ERR - unexpected protocol error");
                return new ReportError(_nameAdress, _iP, _port, "ERR");
            }
        }
        System.out.println("WS " + _nameAdress + " " + _port + ": ERR - unexpected protocol error");
        return new ReportError(_nameAdress, _iP, _port, "ERR");
    }

    /**
     *
     * @param request
     * @return
     */
    public String send(RequestToWS request) {
        int size = request.getFile().length();
        return "WRQ" + " " + request.getpTC() + " " + request.getFileName() + " " + size + " " + request.getFile() + "\n";
    }
}
