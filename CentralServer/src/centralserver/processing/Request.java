package centralserver.processing;

import centralserver.WSList;
import java.io.Reader;

/**
 *
 * @author Asus
 */
public class Request {  
    //Client Request identifier
    private final String _nameAdress;
    private final String _iP;
    private final int _port;
    
    //Request arguments
    private final String _command;
    private final String[] _pTCs;
    private final int _size;
    private final Reader _file; 
    private final String _fileName;
    
    //Processor class of the request
    private final RequestProcessor _processor;
    

    /**
     *
     * @param nameAdress
     * @param processor
     * @param command
     * @param pTCs
     * @param file
     * @param size
     * @param fileName
     * @param iP
     * @param port
     */
    public Request(String nameAdress, String iP, int port, 
                   String command, String[] pTCs, int size, Reader file, String fileName, 
                   RequestProcessor processor) {
        _nameAdress = nameAdress;
        _iP = iP;
        _port = port;
        
        _command = command;
        _pTCs = pTCs;
        _size = size;
        _file = file;
        _fileName = fileName;
        
        _processor = processor;
    }
    
    public Request(String nameAdress, String iP, int port, 
                   String command, String[] pTCs, int size, Reader file, 
                   RequestProcessor processor) {
        this(nameAdress, iP, port, command, pTCs, size, file, null, processor);
    }
    
    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param command
     * @param processor
     */
    public Request(String nameAdress, String iP, int port, 
                   String command, RequestProcessor processor){
        this(nameAdress, iP, port, command, null, 0, null, null,processor);
    }
    
    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param command
     * @param processor
     */
    public Request(String nameAdress, String iP, int port, 
                   RequestProcessor processor){
        this(nameAdress, iP, port, null, null, 0, null, null,processor);
    }

    /**
     *
     * @param list
     * @return
     */
    public Report process(WSList list) {
        return _processor.process(this, list);
    }
    
    //getters

    /**
     *
     * @return
     */
    public String getNameAdress() {
        return _nameAdress;
    }

    /**
     *
     * @return
     */
    public String getiP() {
        return _iP;
    }

    /**
     *
     * @return
     */
    public int getPort() {
        return _port;
    }

    /**
     *
     * @return
     */
    public String getCommand() {
        return _command;
    }

    /**
     *
     * @return
     */
    public String[] getpTCs() {
        return _pTCs;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return _size;
    }

    /**
     *
     * @return
     */
    public Reader getFile() {
        return _file;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return _fileName;
    }

    
}
