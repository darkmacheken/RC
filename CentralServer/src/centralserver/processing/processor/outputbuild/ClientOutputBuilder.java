/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor.outputbuild;

import centralserver.GlobalFunctions;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.ReportOk;

/**
 *
 * @author duartegalvao
 */
public abstract class ClientOutputBuilder {
    private final String _fileName;
    private String _file;
    private final ReportOk[] _reports;
    private final char _rT;
    
    public ClientOutputBuilder(String fileName, ReportOk[] reports, char rT) {
        _fileName = fileName;
        _reports = reports;
        _rT = rT;
    }
    
    public abstract void build();
    
    public void saveFile() throws ConnectionException {
        GlobalFunctions.writeToFile("output_files/" + _fileName + ".txt", _file);
    }
    
    public Boolean checkRT() {
        for (ReportOk report : _reports)
            if (report.getRT() != _rT)
                return false;
        return true;
    }

    protected ReportOk[] getReports() {
        return _reports;
    }
    
    protected void setFile(String file) {
        _file = file;
    }

    public String getFile() {
        return _file;
    }

    public char getRT() {
        return _rT;
    }
    
}
