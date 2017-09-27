package clientserver;

import clientserver.ProtocolException;
import clientserver.UnknownCommand;
import clientserver.ExitCommand;

public class User {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientTCP client = new ClientTCP();
        ProtocolClientCS protocol = new ProtocolClientCS(); //THROWS ProtocolException -> UnknownCommand, ExitCommand

        try {
            while (true) {
                //in
                String commandP = protocol.sendProtocol(command);
                client.send(commandP);
                String responseP = client.receive();
                String response = protocol.receiveProtocol(responseP);
                //out
            }
        }
        catch (ExitCommand e) {
            // close socket
        }
        catch (ProtocolException e) {
            // erro total e completamente F A T A L
        }
    }

}
