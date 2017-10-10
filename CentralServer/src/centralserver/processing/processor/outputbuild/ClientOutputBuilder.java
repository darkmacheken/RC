/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor.outputbuild;

import centralserver.Constants;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.ReportOk;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
        try {
            PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter("output_files/" + _fileName + ".txt")));

            out.print(_file);
            out.close();
        }
        catch (IOException ex) {
            throw new ConnectionException(Constants.FILE_CNTWRT); // TODO mensagem
        }
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
