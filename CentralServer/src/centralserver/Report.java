package centralserver;

public class Report {
    String _command;
    String[] _pTCs;
    String _file;
    int _size;
    Boolean _status;

    public Report(String command, String[] pTCs, String file, int size, Boolean status) {
        _command = command;
        _pTCs = pTCs;
        _file = file;
        _size = size;
        _status = status;
    }
}
