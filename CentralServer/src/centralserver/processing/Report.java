package centralserver.processing;

/**
 *
 * @author Asus
 */
public class Report {
    String _command;
    String[] _pTCs;
    String _file;
    int _size;
    Boolean _status;
  
    /**
     *
     * @param command
     * @param pTCs
     * @param file
     * @param size
     * @param status
     */
    public Report(String command, String[] pTCs, String file, int size, Boolean status) {
        _command = command;
        _pTCs = pTCs;
        _file = file;
        _size = size;
        _status = status;
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
     * @return status
     */
    public Boolean getStatus() {
        return _status;
    }
}
