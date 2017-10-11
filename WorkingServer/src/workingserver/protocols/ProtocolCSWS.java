/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.protocols;

import static java.lang.Integer.max;
import workingserver.processing.report.Report;
import workingserver.processing.request.Request;
import workingserver.processing.request.RequestError;
import workingserver.processing.request.RequestOk;

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
            String[] commandArguments = splitedCommand[1].split(" ", 4);
            
            if (commandArguments.length != 4)
                return new RequestError("ERR");
            
            String pTC = commandArguments[0];
            String fileName = commandArguments[1];
            int size;
            try {
                size = Integer.parseInt(commandArguments[2]);
            }
            catch (NumberFormatException e) {
                return new RequestError("ERR");
            }
            String file = commandArguments[3];

            if (size == file.length()) {
                return new RequestOk("WRQ", pTC, size, file, fileName);
            }
        }
        return new RequestError("ERR");
    }

    /**
     *
     * @param request
     * @return
     */
    public String send(Report report) {
        return report.toString();
    }
}
