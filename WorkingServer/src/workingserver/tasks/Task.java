package workingserver.tasks;

import workingserver.processing.report.Report;
import workingserver.processing.report.ReportOk;

/**
 * Abstract class of Task
 */
public abstract class Task {
    private final String _pTC;
    private final char _rT;
    
    /**
     *
     * @param pTC
     * @param rT
     */
    public Task(String pTC, char rT) {
        _pTC = pTC;
        _rT = rT;
    }

    /**
     *
     * @return
     */
    public String getPTC() {
        return _pTC;
    }

    /**
     *
     * @return
     */
    public char getRT() {
        return _rT;
    }

    /**
     *
     * @param pTC
     * @return
     */
    public boolean isPTC(String pTC) {
        return _pTC.equals(pTC);
    }

    abstract Report run(String file);
    
    /**
     *
     * @param file
     * @return
     */
    protected ReportOk createReportOk(String file) {
        return new ReportOk(file, file.length(), _rT);
    }
    
}
