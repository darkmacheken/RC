/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor.outputbuild;

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
    
    public ClientOutputBuilder(String fileName, ReportOk[] reports) {
        _fileName = fileName;
        _reports = reports;
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
            throw new ConnectionException(""); // TODO mensagem
        }
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
}
