package centralserver;

public class Request {
    RequestProcessor _processor;
    String _command;
    String[] _pTCs;
    String _file;
    int _size;
    String _fileName;
    String _iP;
    int _port;

    public Request(Request processor, String command, String[] pTCs, String file, int size, String fileName, String iP, String port) {
        _processor = processor;
        _command = command;
        _pTCs = pTCs;
        _file = file;
        _size = size;
        _fileName = filename;
        _iP = iP;
        _port = port;
    }

    Report process() {
        return _processor.process(this);
    }
}
