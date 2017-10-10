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
public class ProtocolWSCS {
    private final String _nameAdress;
    private final String _iP;
    private final int _port;

    /**
    *
    * @param nameAdress
    * @param iP
    * @param port
    */
    public ProtocolWSCS(String nameAdress, String iP, int port) {
        _nameAdress = nameAdress;
        _iP = iP;
        _port = port;
    }

    public Report receive(String sentence) {
      //remove last char from sentence (it should be '\n' from protocol)
      sentence = sentence.substring(0, max(0,sentence.length()-1));

      //split sentence by space into array
      String[] splitedCommand = sentence.split(" ", 2);

      if (splitedCommand.length != 0) {
          return new ReportError(_nameAdress, _iP, _port, "ERR");
      }
      return null;
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
