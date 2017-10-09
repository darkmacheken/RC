package centralserver.processing;

import centralserver.WSList;
import java.io.Reader;

/**
 *
 * @author Asus
 */
public class Request {

    private final RequestProcessor _processor;
    private final String _command;
    private final String[] _pTCs;
    private final Reader _file;
    private final int _size;
    private final String _fileName;
    private final String _iP;
    private final int _port;

    /**
     *
     * @param processor
     * @param command
     * @param pTCs
     * @param file
     * @param size
     * @param fileName
     * @param iP
     * @param port
     */
    public Request(RequestProcessor processor, String command, String[] pTCs, Reader file, int size, String fileName, String iP, int port) {
        _processor = processor;
        _command = command;
        _pTCs = pTCs;
        _file = file;
        _size = size;
        _fileName = fileName;
        _iP = iP;
        _port = port;
    }

    public Report process(WSList list) {
        return _processor.process(this, list);
    }
    
    public RequestProcessor getProcessor() {
        return _processor;
    }

    public String getCommand() {
        return _command;
    }

    public String[] getPTCs() {
        return _pTCs;
    }
    
    public Reader getFile() {
        return _file;
    }

    public int getSize() {
        return _size;
    }

    public String getFileName() {
        return _fileName;
    }

    public String getiP() {
        return _iP;
    }

    public int getPort() {
        return _port;
    }
}
