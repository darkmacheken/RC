package workingserver.processing.request;

import workingserver.processing.processor.RequestProcessor;

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
     * @param processor
     * @param command
     * @param pTC
     * @param file
     * @param size
     * @param fileName
     */
    public RequestOk(String command, String pTC, int size, String file, String fileName, 
                   RequestProcessor processor) {
        super(processor);
        
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
     * @param processor
     */
    public RequestOk(String command, String pTC, int size, String file, 
                   RequestProcessor processor) {
        this(command, pTC, size, file, null, processor);
    }
    
    /**
     *
     * @param command
     * @param processor
     */
    public RequestOk(String command, RequestProcessor processor){
        this(command, null, 0, null, null,processor);
    }
    
    /**
     *
     * @param processor
     */
    public RequestOk(RequestProcessor processor){
        this(null, null, 0, null, null,processor);
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
