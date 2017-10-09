package centralserver.processing;

import java.io.Reader;

/**
 *
 * @author Asus
 */
public class Report {
    private String _command;
    private String[] _pTCs;
    private Reader _file;
    private int _size;
    private Boolean _status;
    private char _rT;
  
    /**
     *
     * @param command
     * @param pTCs
     * @param file
     * @param size
     * @param status
     */
    public Report(String command, String[] pTCs, Reader file, int size, Boolean status, char rT) {
        _command = command;
        _pTCs = pTCs;
        _file = file;
        _size = size;
        _status = status;
        _rT = rT;
    }
    
    /**
     *
     * @param command
     * @param pTCs
     */
    public Report(String command, String[] pTCs) {
        _command = command;
        _pTCs = pTCs;
    }
    
    /**
     *
     * @return command
     */
    public String getCommand() {
        return _command;
    }

    /**
     *
     * @return pTCs
     */
    public String[] getpTCs() {
        return _pTCs;
    }

    /**
     *
     * @return file
     */
    public Reader getFile() {
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
     * @return status
     */
    public Boolean getStatus() {
        return _status;
    }
}
