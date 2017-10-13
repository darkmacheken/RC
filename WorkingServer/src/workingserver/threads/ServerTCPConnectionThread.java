/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.threads;

import workingserver.connection.ConnectionTCP;
import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.request.Request;
import workingserver.protocols.ParseProtocolCSWS;
import workingserver.protocols.ProtocolCSWS;
import workingserver.tasks.Task;

/**
 *
 * @author leonardinho
 */
public class ServerTCPConnectionThread extends Thread {
    private ConnectionTCP _connection;
    private Task[] _tasks;

    /**
     *
     * @param connection
     * @param tasks
     */
    public ServerTCPConnectionThread(ConnectionTCP connection, Task[] tasks) {
        _connection = connection;
        _tasks = tasks;
    }

    @Override
    public void run() {
        ProtocolCSWS protocol = new ProtocolCSWS();
        try {
            ParseProtocolCSWS parser = new ParseProtocolCSWS(_connection);
            String received = parser.parse();
            Request request = protocol.receive(received);
            Report report = request.process(_tasks);
            String toSend = protocol.send(report);
            _connection.send(toSend);
            _connection.close();
        } catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
        }
    }
}
