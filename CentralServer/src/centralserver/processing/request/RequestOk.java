package centralserver.processing.request;

import centralserver.processing.processor.RequestProcessor;

/**
 * Requests that follow the protocol
 */
public class RequestOk extends Request{      
    //Request arguments
    private final String _command;
    private final String _pTC;
    private final int _size;
    private final String _file; 
    private final String _fileName;
    

    /**
     *
     * @param nameAdress
     * @param processor
     * @param command
     * @param pTC
     * @param file
     * @param size
     * @param fileName
     * @param iP
     * @param port
     */
    public RequestOk(String nameAdress, String iP, int port, 
                   String command, String pTC, int size, String file, String fileName, 
                   RequestProcessor processor) {
        super(nameAdress, iP, port, processor);
        
        _command = command;
        _pTC = pTC;
        _size = size;
        _file = file;
        _fileName = fileName;
    }
    
    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param command
     * @param pTC
     * @param size
     * @param file
     * @param processor
     */
    public RequestOk(String nameAdress, String iP, int port, 
                   String command, String pTC, int size, String file, 
                   RequestProcessor processor) {
        this(nameAdress, iP, port, command, pTC, size, file, null, processor);
    }
    
    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param command
     * @param processor
     */
    public RequestOk(String nameAdress, String iP, int port, 
                   String command, RequestProcessor processor){
        this(nameAdress, iP, port, command, null, 0, null, null,processor);
    }
    
    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param processor
     */
    public RequestOk(String nameAdress, String iP, int port, 
                   RequestProcessor processor){
        this(nameAdress, iP, port, null, null, 0, null, null,processor);
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
    public String getPTC() {
        return _pTC;
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
    public String getFile() {
        return _file;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return _fileName;
    }

    @Override
    public String toString(){
        return _command;
               
    }
}
