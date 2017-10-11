/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.tasks;

import workingserver.processing.report.Report;
import workingserver.processing.report.ReportOk;

/**
 *
 * @author duartegalvao
 */
public abstract class Task {
    private final String _pTC;
    private final char _rT;
    
    public Task(String pTC, char rT) {
        _pTC = pTC;
        _rT = rT;
    }

    public String getPTC() {
        return _pTC;
    }

    public char getRT() {
        return _rT;
    }

    boolean isPTC(String pTC) {
        return _pTC.equals(pTC);
    }

    abstract Report run(String file);
    
    protected ReportOk createReportOk(String file) {
        return new ReportOk(file, file.length(), _rT);
    }
    
}
