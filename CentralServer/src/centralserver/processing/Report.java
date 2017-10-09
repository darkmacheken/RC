package centralserver.processing;

/**
 *
 * @author Asus
 */
public abstract class Report {
     //Client Request identifier
    private final String _nameAdress;
    private final String _iP;
    private final int _port;

    /**
     *
     * @param _nameAdress
     * @param _iP
     * @param _port
     * @param _processor
     */
    public Report(String _nameAdress, String _iP, int _port) {
        this._nameAdress = _nameAdress;
        this._iP = _iP;
        this._port = _port;
    }

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
}
