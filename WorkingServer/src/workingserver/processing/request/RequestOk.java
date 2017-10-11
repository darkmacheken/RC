package workingserver.processing.request;

import workingserver.processing.processor.OkRequestProcessor;

/**
 *
 * @author Asus
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
     * @param command
     * @param pTC
     * @param file
     * @param size
     * @param fileName
     */
    public RequestOk(String command, String pTC, int size, String file, String fileName) {
        super(new OkRequestProcessor());
        
        _command = command;
        _pTC = pTC;
        _size = size;
        _file = file;
        _fileName = fileName;
    }
    
    /**
     *
     * @param command
     * @param pTC
     * @param size
     * @param file
     */
    public RequestOk(String command, String pTC, int size, String file) {
        this(command, pTC, size, file, null);
    }
    
    /**
     *
     * @param command
     */
    public RequestOk(String command){
        this(command, null, 0, null, null);
    }
    
    /**
     *
     */
    public RequestOk(){
        this(null, null, 0, null, null);
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
