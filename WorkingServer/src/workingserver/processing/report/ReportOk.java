package workingserver.processing.report;

/**
 *
 *  
 */
public class ReportOk extends Report {
    private final String _file;
    private final int _size;
    private final char _rT;

    /**
     *
     * @param file
     * @param size
     * @param rT
     */
    public ReportOk(String file, int size, char rT) {
        _file = file;
        _size = size;
        _rT = rT;
    }
    
    /**
     *
     * @return file
     */
    public String getFile() {
        return _file;
    }

    /**
     *
     * @return size
     */
    public int getSize() {
        return _size;
    }

    /**
     *
     * @return
     */
    public char getRT() {
        return _rT;
    }

    @Override
    public String toString() {
        System.out.println("Request WRQ: " + _size + " bytes to transmit.");
        return "REP " + _rT + " " + _size + " " + _file + "\n";
    }

}
