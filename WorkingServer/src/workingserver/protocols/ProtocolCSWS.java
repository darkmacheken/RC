/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.protocols;

import static java.lang.Integer.max;
import workingserver.processing.request.Request;
import workingserver.processing.request.RequestError;

/**
 *
 * @author duartegalvao
 */
public class ProtocolCSWS {

    public Request receive(String sentence) {
        //remove last char from sentence (it should be '\n' from protocol)
        sentence = sentence.substring(0, max(0, sentence.length() - 1));

        //split sentence by space into array
        String[] splitedCommand = sentence.split(" ", 2);

        if (splitedCommand.length == 0) {
            return new RequestError("ERR");
        }
        if (splitedCommand[0].equals("ERR")) {
            return new RequestError("ERR");
        }
        else if (splitedCommand[0].equals("WRQ")) {
            String[] commandArguments = splitedCommand[1].split(" ");
            
            if (commandArguments.length != 4)
                return new RequestError("ERR");
            
            char type = commandArguments[0].charAt(0);
            int size = Integer.parseInt(commandArguments[1]);
            String file = commandArguments[2];

            if (size == file.length()) {
                return new RequestOk(file, size, type);
            }
        }
        return new RequestError("ERR");
    }

    /**
     *
     * @param request
     * @return
     */
    public String send(RequestToWS request) {
        int size = request.getFile().length();
        return "REP" + request.getpTC() + request.getFileName() + size + request.getFile() + "\n";
    }
}
