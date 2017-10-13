package workingserver.processing.report;

/**
 * Reports that don't follow the protocol or errors occured
 */
public class ReportError extends Report {
    //Error
    private String _error;

    /**
     *
     * @param _nameAdress
     * @param _port
     */
    public ReportError(String error) {
        _error = error;
    }

    /**
     *
     * @return
     */
    public String getError() {
        return _error;
    }
    
    @Override
    public String toString() {
        System.out.println("Error: " + _error);
        return _error + "\n";
    }
    
}
