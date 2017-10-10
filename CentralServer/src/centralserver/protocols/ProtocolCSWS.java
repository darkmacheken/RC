/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.protocols;

/**
 *
 * @author duartegalvao
 */
public class ProtocolCSWS {
    private final String _nameAdress;
    private final String _iP;
    private final int _port;

    /**
    *
    * @param nameAdress
    * @param iP
    * @param port
    */
    public ProtocolCSClient(String nameAdress, String iP, int port) {
        _nameAdress = nameAdress;
        _iP = iP;
        _port = port;
    }

    public Report receive(String sentence) {

    }

    public String send(RequestToWS request) {

    }
