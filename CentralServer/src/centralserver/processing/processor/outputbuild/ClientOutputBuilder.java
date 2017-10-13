package centralserver.processing.processor.outputbuild;

import centralserver.GlobalFunctions;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.ReportOk;

/**
 *
 *  
 *
 */
public abstract class ClientOutputBuilder {
    private final String _fileName;
    private String _file;
    private final ReportOk[] _reports;
    private final char _rT;
    
    /**
     *
     * @param fileName
     * @param reports
     * @param rT
     */
    public ClientOutputBuilder(String fileName, ReportOk[] reports, char rT) {
        _fileName = fileName;
        _reports = reports;
        _rT = rT;
    }
    
    /**
     *
     */
    public abstract void build();
    
    /**
     *
     * @throws ConnectionException
     */
    public void saveFile() throws ConnectionException {
        GlobalFunctions.writeToFile("output_files/" + _fileName + ".txt", _file);
    }
    
    /**
     *
     * @return
     */
    public Boolean checkRT() {
        for (ReportOk report : _reports)
            if (report.getRT() != _rT)
                return false;
        return true;
    }

    /**
     *
     * @return
     */
    protected ReportOk[] getReports() {
        return _reports;
    }
    
    /**
     *
     * @param file
     */
    protected void setFile(String file) {
        _file = file;
    }

    /**
     *
     * @return
     */
    public String getFile() {
        return _file;
    }

    /**
     *
     * @return
     */
    public char getRT() {
        return _rT;
    }
    
}
